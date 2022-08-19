package com.example.backend.Repository;

import com.example.backend.Entity.Post;
import com.example.backend.Entity.Reaction;
import com.example.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository  extends JpaRepository<Reaction,Integer> {

    @Query("SELECT count(r) FROM Reaction r where  r.post=:post")
     int NbrReactionByPost(Post post);

    @Query(value = "select count(*) from reaction where nb_like=1 and post_id= ?1", nativeQuery = true)
    int countYes(int idPost);

    @Query(value = "select count(*) from reaction where nb_dislike=1 and post_id= ?1", nativeQuery = true)
    int countNo(int idPost);






}
