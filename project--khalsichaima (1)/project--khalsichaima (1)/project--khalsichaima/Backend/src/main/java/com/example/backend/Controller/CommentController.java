package com.example.backend.Controller;

import com.example.backend.Entity.Comment;
import com.example.backend.Entity.Post;
import com.example.backend.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin("*")
@RestController("/commentController")

public class CommentController {




    @Autowired
    private CommentService commentService;

    @PostMapping("/add-comment/{idPost}/{idUser}")
    public ResponseEntity<Comment> creatCom(@RequestBody Comment comment, @PathVariable int idPost, @PathVariable Long idUser) {
        return new ResponseEntity<>(commentService.createcomment(comment,idPost,idUser), HttpStatus.OK);
    }
    @GetMapping("/all-comment")
    public ResponseEntity<Collection<Comment>> getAllCom() {
        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete-comment/{id}")
    public ResponseEntity deletCom(@PathVariable int id) {
        this.commentService.deletecomment(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @PutMapping("/updatePost/{id}")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment p, @PathVariable("id") int id)

    {
        return commentService.updatePerso(id,p);
    }




}
