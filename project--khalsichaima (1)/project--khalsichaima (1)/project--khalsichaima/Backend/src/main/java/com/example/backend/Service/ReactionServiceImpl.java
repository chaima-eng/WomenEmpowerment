package com.example.backend.Service;

import com.example.backend.Entity.Post;
import com.example.backend.Entity.Reaction;
import com.example.backend.Repository.PostRepository;
import com.example.backend.Repository.ReactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@Slf4j
@CrossOrigin("*")
public class ReactionServiceImpl  implements ReactionService{


    @Autowired
    private ReactionRepository reactionRepository;
    @Autowired
    private PostRepository postRepository;



    @Override
    public void addReactToPost(Reaction reaction, int idPost, Long idUser) {


        Reaction isReacted=null;
        Reaction isReactedSame=null;
        Post p=postRepository.findById(idPost).orElse(null);
        reaction.setIdUser(idUser);
        reaction.setPost(p);
        for(Reaction r:reactionRepository.findAll()){
            if(r.getIdUser()==idUser && r.getPost().getId()==idPost ){
                if( r.getReactionType().equals(reaction.getReactionType())){
                    isReactedSame=r;
                }
                isReacted=r;

            }
        }
        if(isReactedSame!=null){
            System.out.println("You have already reacted to this post");
        }
        else if(isReacted!=null){
            isReacted.setReactionType(reaction.getReactionType());
            reactionRepository.save(isReacted);
        }
        else{
            reactionRepository.save(reaction);
        }


    }


    @Override
    public void deleteReaction(int idReaction) {

        Reaction reaction=reactionRepository.findById(idReaction).orElse(null);
        reactionRepository.delete(reaction);


    }




}