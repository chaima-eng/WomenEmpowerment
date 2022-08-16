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





    @GetMapping("getNbrReactionByPost/{idPost}")
    public int getNbrReactionByPost(@PathVariable("idPost") int idPost){

        return reactionService.getNbrReactionByPost(idPost);
    }




    @GetMapping("getNbrLike/{idPost}")
    public int getNbrLike(@PathVariable("idPost") int idPost){
        return reactionService.getNbrLike(idPost);
    }




    @GetMapping("getNbrDisLike/{idPost}")
    public int getNbrDislike(@PathVariable("idPost") int idPost){
        return reactionService.getnbrDislike(idPost);
    }




}
