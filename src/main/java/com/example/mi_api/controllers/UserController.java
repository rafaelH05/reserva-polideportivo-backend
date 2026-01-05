package com.example.mi_api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import com.example.mi_api.config.JwtUtil;
import com.example.mi_api.entities.Login;
import com.example.mi_api.entities.User;
import com.example.mi_api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> users() {
        return userRepository.findAll();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.status(409).body("El nombre de usuario ya está en uso");
        }
        if (userRepository.findAll().stream().anyMatch(u -> u.getEmail().equals(user.getEmail()))) {
            return ResponseEntity.status(409).body("El email ya está en uso");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userSaved = userRepository.save(user);
        String token = jwtUtil.generateToken(user.getUsername(), user.getUserId());
        return ResponseEntity.ok(Map.of("username", userSaved.getUsername(), "token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Login log) {

        List<User> res = userRepository.findAll();
        User user = null;

        user = res.stream().filter(a -> a.getUsername().equals(log.getUsername())).findFirst().orElse(null);

        if (user == null || !passwordEncoder.matches(log.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getUserId());

        return ResponseEntity.ok(Map.of("username", user.getUsername(), "token", token));
    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Integer id) {
        return userRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        User userUpdate = userRepository.findById(user.getUserId()).get();
        userUpdate.setUsername(user.getUsername());
        userUpdate.setFirstName(user.getFirstName());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setLastName(user.getLastName());
        userUpdate.setPassword(user.getPassword());
        userUpdate.setTelephone(user.getTelephone());
        userUpdate.setVerified(user.getVerified());

        return userRepository.save(userUpdate);
    }
}
