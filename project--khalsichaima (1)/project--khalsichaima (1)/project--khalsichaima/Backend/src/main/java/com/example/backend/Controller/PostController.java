package com.example.backend.Controller;

import com.example.backend.Entity.Post;
import com.example.backend.Repository.PostRepository;
import com.example.backend.Service.PostService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import javafx.geometry.Pos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/Post")
@CrossOrigin("*")

public class PostController {

    @Autowired
    PostService PS;
    @Autowired
    PostRepository RP;



    @PostMapping("/createPost/{idUser}")
    public Post createPost (@RequestPart(value="file",required=false) MultipartFile file ,
                              @RequestParam("post") String post,@PathVariable int idUser) throws JsonParseException, JsonMappingException, Exception
    {

        return PS.createpost(file, post,idUser);
    }


    @GetMapping("/all-post")
    public ResponseEntity<Collection<Post>> getAllPosts() {
        return new ResponseEntity<>(PS.getAll(), HttpStatus.OK);
    }


    @GetMapping("/postByID/{idpost}")
    public Post getPostById(@PathVariable("idpost") int idPost)
    {
        return PS.getPostById(idPost);
    }

    @PutMapping("/updatePost/{id}")
    public ResponseEntity<Post> updatePost(@RequestBody Post p,@PathVariable("id") int id)

    {
        return PS.updatePerso(id,p);
    }


    @DeleteMapping("/deletePOst/{idPOst}")
    public void deletepost(@PathVariable("idPOst") int id)
    {
        PS.deletepost(id);
    }

    @Autowired
    ServletContext context;

    @GetMapping(path="/Imgarticles/{id}")
    public byte[] getPhoto(@PathVariable("id") int id) throws Exception{
        Post p   = RP.findById(id).get();
        return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+p.getFile()));
    }







}
