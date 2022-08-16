package com.example.backend.Service;

import com.example.backend.Entity.Comment;
import com.example.backend.Entity.Post;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface CommentService {
     Comment createcomment(Comment comment, int idPost, Long idUser);
     Collection<Comment> getAll();
     public ResponseEntity<Comment> updatePerso(int id, Comment comment);
     void deletecomment(int id);


     int getNbrCommentByPost(int idPost);




}
