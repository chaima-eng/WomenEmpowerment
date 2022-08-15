package com.example.backend.Repository;

import com.example.backend.Entity.Reaction;
import com.example.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository  extends JpaRepository<Reaction,Integer> {
}
