package com.example.backend.Service;


import com.example.backend.Entity.Reaction;

public interface ReactionService {
     void addReactToPost(Reaction reaction, int idPost, int idUser);
     void deleteReaction(int idReaction);

    int getNbrReactionByPost(int idPost);


    int getNbrLike(int idPost);

    int getnbrDislike(int idPost);
   //  int AddYes(Reaction v, int postID, int userId);





}
