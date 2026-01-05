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

import com.example.mi_api.entities.Comment;
import com.example.mi_api.repository.CommentRepository;

@RestController
@CrossOrigin(origins = "*")
public class CommentController {

    private final CommentRepository commentRepository;
    

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/comments")
    public List<Comment> comments(){
        return commentRepository.findAll();
    }

    @GetMapping("/comments/{id}")
    public Comment commentById(@PathVariable Integer id){
        return commentRepository.findById(id).get();
    }

    @PostMapping("/comments/create")
    public Comment createComment(@RequestBody Comment comment){
        return commentRepository.save(comment);
    }

    @PutMapping("/comments/update")
    public Comment updateComment(@RequestBody Comment comment){
        Comment commentUpdate = commentRepository.findById(comment.getComment_id()).get();
        commentUpdate.setDate(comment.getDate());
        commentUpdate.setPhoto_id(comment.getPhoto_id());
        commentUpdate.setText(comment.getText());
        commentUpdate.setUser_id(comment.getUser_id());

        return commentRepository.save(commentUpdate);
    }

    @DeleteMapping("/comments/delete/{id}")
    public void deleteComment(@PathVariable Integer id){
        commentRepository.deleteById(id);
    }
}
