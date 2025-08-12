package com.example.mi_api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mi_api.entities.Photos;
import com.example.mi_api.repository.PhotoRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PhotosController {

    private final PhotoRepository photoRepository;

    public PhotosController(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @GetMapping("/photos")
    public List<Photos> photos() {
        return photoRepository.findAll();
    }

    @GetMapping("/photo/{idPhoto}")
    public Photos photoById(@PathVariable(name = "idPhoto") Integer id) {
        return photoRepository.findById(id).get();
    }

    @PostMapping("/createPhoto")
    public Photos createPhoto(@RequestBody Photos photo) {
        return photoRepository.save(photo);
    }

    @DeleteMapping("/deletePhoto/{idPhoto}")
    public void deletePhoto(@PathVariable Integer idPhoto) {
        photoRepository.deleteById(idPhoto);
    }

    @PutMapping("/updatePhoto")
    public Photos updatePhoto(@RequestBody Photos photo) {
        Photos photoUP = photoRepository.findById(photo.getPhoto_id()).get();

        photoUP.setTitle(photo.getTitle());
        photoUP.setDescription(photo.getDescription());
        photoUP.setDate(photo.getDate());
        photoUP.setUrl(photo.getUrl());
        photoUP.setUser_id(photo.getUser_id());
        photoUP.setVisibility(photo.getVisibility());

        return photoRepository.save(photoUP);
    }
}
