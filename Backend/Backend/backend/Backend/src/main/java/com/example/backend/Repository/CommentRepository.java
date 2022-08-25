package com.example.backend.Repository;

import com.example.backend.Entity.Comment;
import com.example.backend.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {


    @Query("SELECT count(c) FROM Comment c where c.post_comment=:post")
     int NbrCommentByPost(Post post);






}
