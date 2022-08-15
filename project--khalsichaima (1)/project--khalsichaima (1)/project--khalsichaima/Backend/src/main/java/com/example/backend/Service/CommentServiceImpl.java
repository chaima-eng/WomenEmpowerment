package com.example.backend.Service;

import com.example.backend.Entity.Comment;
import com.example.backend.Entity.Post;
import com.example.backend.Repository.CommentRepository;
import com.example.backend.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Service
public class CommentServiceImpl implements CommentService{








    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;


    @Override
    public Comment createcomment(Comment comment, int idPost, Long idUser) {
        Post p=postRepository.findById(idPost).orElse(null);
        if(p.getComments()==null){
            p.setComments(Collections.singletonList( comment ));
        }
        else{
            p.getComments().add(comment);
        }
        comment.setPost_comment(p);
        comment.setIdUser(idUser);
        return commentRepository.save(comment);
    }

    @Override
    public Collection<Comment> getAll() {
        return (Collection<Comment>) commentRepository.findAll();
    }

    @Override
    public ResponseEntity<Comment> updatePerso(int id, Comment post) {
        Comment post1 = commentRepository.getById(id);


        post1.setCommentContent(post.getCommentContent());


        post1.setModifyDate(LocalDateTime.now());

        Comment update = commentRepository.save(post1);

        return ResponseEntity.ok().body(update);

    }


    @Override
    public void deletecomment(int id) {
        commentRepository.delete(commentRepository.findById(id).orElse(null));

    }


}
