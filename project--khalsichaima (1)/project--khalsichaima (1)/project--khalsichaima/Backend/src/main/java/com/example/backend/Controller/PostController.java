package com.example.backend.Controller;

import com.example.backend.Entity.Post;
import com.example.backend.Entity.Reaction;
import com.example.backend.Repository.PostRepository;
import com.example.backend.Repository.ReactionRepository;
import com.example.backend.Service.PostService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/Post")
@CrossOrigin("*")

public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService PS;
    @Autowired
    PostRepository RP;
    @Autowired
    ReactionRepository reactionRepository;




    @GetMapping("/getPostByUser/{idUser}")
    public List<Post> getPostByUser(@PathVariable("idUser")int idUser)
    {
        return PS.getPostByUser(idUser);
    }









    @PostMapping("/createPost/{idUser}")
    public Post createPost (@RequestPart(value="file",required=false) MultipartFile file ,
                              @RequestParam("post") String post,@PathVariable int idUser) throws JsonParseException, JsonMappingException, Exception
    {

        return PS.createpost(file, post,idUser);
    }


    @GetMapping("/all-post")
    public Collection<Post> getAllPosts() {
        return PS.getAll();
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





























/*
    @GetMapping ("/getAll")
    public ResponseEntity<List<String>> getAll()
    {
        List<String> listArt = new ArrayList<String>();
        String filesPath = context.getRealPath("/Images");
        File filefolder = new File(filesPath);
        if (filefolder != null)
        {
            for (File file :filefolder.listFiles())
            {
                if(!file.isDirectory())
                {
                    String encodeBase64 = null;
                    try {
                        String extension = FilenameUtils.getExtension(file.getName());
                        FileInputStream fileInputStream = new FileInputStream(file);
                        byte[] bytes = new byte[(int)file.length()];
                        fileInputStream.read(bytes);
                        encodeBase64 = Base64.getEncoder().encodeToString(bytes);
                        listArt.add("data:image/"+extension+";base64,"+encodeBase64);
                        fileInputStream.close();


                    }catch (Exception e){

                    }
                }
            }
        }
        return new ResponseEntity<List<String>>(listArt,HttpStatus.OK);



    }



 */












}
