package com.example.backend.Controller;


import com.example.backend.Entity.Reaction;
import com.example.backend.Service.ReactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/Reaction")
@CrossOrigin("*")
public class ReactionController {
    @Autowired
    private ReactionService reactionService;


    @PostMapping("/addReaction/{idPost}/{idUser}")
    public void addReactToPost(@RequestBody Reaction reaction, @PathVariable int idPost, @PathVariable Long idUser){
        reactionService.addReactToPost(reaction,idPost,idUser);
    }

    @DeleteMapping("/deleteReaction/{idReaction}")
    public void deleteReaction(@PathVariable int idReaction){
        reactionService.deleteReaction(idReaction);
    }










}
