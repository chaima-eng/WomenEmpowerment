package com.example.backend.Service;


import com.example.backend.Entity.Post;
import com.example.backend.Entity.User;
import com.example.backend.Repository.CommentRepository;
import com.example.backend.Repository.IntUserRepo;
import com.example.backend.Repository.PostRepository;
import com.example.backend.Repository.ReactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


import javafx.geometry.Pos;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    ServletContext context;

    @Autowired
    private IntUserRepo userRepository;

    @Override
    public Post createpost( MultipartFile file,String post,int idUser) throws IOException {
      //  String fileData = firebaseStorage.uploadFile(file);


        Post p = new ObjectMapper().readValue(post, Post.class);
        boolean isExit = new File(context.getRealPath("/Images/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("/Images/")).mkdir();
            System.out.println("mkdir success.............");
        }







        if(file != null) {
            String filename = file.getOriginalFilename();
            String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
            File serverFile = new File(context.getRealPath("/Images/" + File.separator + newFileName));

            try {
                System.out.println("Image");
                FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
                p.setFile(newFileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {

            p.setFile("");
        }




            p.setCreatedAt(LocalDateTime.now());
            postRepository.save(p);

            return assignUserToPost(idUser,p.getId());



    }


    @Override
    public Post assignUserToPost(int idUser, int idPost) {
        Post p=postRepository.findById(idPost).orElse(null);
        User u=userRepository.findById(idUser).orElse(null);
        u.getPosts().add(p);
        p.setUser(u);

        postRepository.save(p);
        return p;
    }

    @Autowired
    CommentRepository ComR;








@Autowired
    ReactionRepository reactionRepository;

    @Override
    public Collection<Post> getAll() {

        return postRepository.findAll();
    }

    @Override
    public Post getPostById(int idPost) {
        return postRepository.findById(idPost).orElse(null);
    }

    @Override
    public ResponseEntity<Post> updatePerso(int id, Post post) {
        Post post1 = postRepository.getById(id);


        post1.setSubject(post.getSubject());
        post1.setContent(post.getContent());

        post1.setModifiedAt(LocalDateTime.now());

        Post update = postRepository.save(post1);

        return ResponseEntity.ok().body(update);

    }

    @Override
    public void deletepost(int id) {
        postRepository.delete(postRepository.findById(id).orElse(null));
    }

    @Override
    public List<Post> getPostByUser(int idUser) {
        List p =new ArrayList();

        for (Post ps:postRepository.findAll())
        {

            if(ps.getUser().getIdUser()==idUser)
            {
                p.add(ps);
            }
        }
        return p;
    }











}
