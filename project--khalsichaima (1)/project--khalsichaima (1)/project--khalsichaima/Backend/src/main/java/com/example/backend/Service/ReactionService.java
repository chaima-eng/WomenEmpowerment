package com.example.backend.Service;


import com.example.backend.Entity.Reaction;

public interface ReactionService {
    public void addReactToPost(Reaction reaction, int idPost, Long idUser);
    public void deleteReaction(int idReaction);

}
