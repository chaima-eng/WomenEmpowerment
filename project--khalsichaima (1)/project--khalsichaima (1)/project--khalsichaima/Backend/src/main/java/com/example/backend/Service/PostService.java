package com.example.backend.Service;


import com.example.backend.Entity.Post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

public interface PostService {
     Post createpost( MultipartFile file,String post,int idUser) throws IOException;
     Post assignUserToPost(int idUser,int idPost);

     Collection<Post> getAll();

     Post getPostById(int idPost);
     ResponseEntity<Post> updatePerso(int id, Post post);
    void deletepost(int id);






    }
