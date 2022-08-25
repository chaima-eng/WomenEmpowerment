package com.example.backend.Service;

import com.example.backend.Entity.Post;
import com.example.backend.Entity.Reaction;
import com.example.backend.Entity.ReactionType;
import com.example.backend.Entity.User;
import com.example.backend.Repository.IntUserRepo;
import com.example.backend.Repository.PostRepository;
import com.example.backend.Repository.ReactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@CrossOrigin("*")
public class ReactionServiceImpl  implements ReactionService{


    @Autowired
    private ReactionRepository reactionRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    IntUserRepo userRepo;

    @Override
    public void addReactToPost(Reaction reaction, int idPost, int idUser) {

        boolean userExists = userRepo
                .findById(idUser).isPresent();


        int nb=0;
        Reaction isReacted=null;
        Reaction isReactedSame=null;
        Post p=postRepository.findById(idPost).orElse(null);

        for(Reaction r:reactionRepository.findAll()){
            if(r.getIdUser()==idUser && r.getPost().getId()==idPost ){
                if( r.getReactionType().equals(reaction.getReactionType())){
                    isReactedSame=r;
                    reactionRepository.delete(r);
                }

                isReacted=r;

            }
        }


        if(isReactedSame==null)
        {

            reaction.setIdUser((long) idUser);
            reaction.setPost(p);
            if (reaction.getReactionType().equals(ReactionType.LIKE))
            {
                reaction.setNbLike(nb+1);
                reactionRepository.save(reaction);
            }
            else
            {
                reaction.setNbDislike(nb+1);
                reactionRepository.save(reaction);
            }

        }

    }


    @Override
    public void deleteReaction(int idReaction) {

        Reaction reaction=reactionRepository.findById(idReaction).orElse(null);
        reactionRepository.delete(reaction);


    }


    @Override
    public int getNbrReactionByPost(int idPost) {
        Post p=postRepository.findById(idPost).orElse(null);
        return reactionRepository.NbrReactionByPost(p);
    }



    @Override
    public int getNbrLike(int idPost) {
        return reactionRepository.countYes(idPost);
    }

    @Override
    public int getnbrDislike(int idPost) {
        return reactionRepository.countNo(idPost);
    }





/*
    @Override
    public int AddYes(Reaction v, int postID, int userId) {
        v.setNbLike(1);
        v.setNbDislike(0);
        Post sujet = postRepository.findById(postID).orElse(null);
        User user =userRepo.findById(userId).orElse(null);
        v.setIdUser((long) userId);
        v.setPost(sujet);
        reactionRepository.save(v);

        return 0;
    }



 */



}
