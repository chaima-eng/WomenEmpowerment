package com.example.backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subject;
    private String content;
    private LocalDateTime createdAt=LocalDateTime.now();
    private LocalDateTime modifiedAt;
    String file;


    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "post_comment",cascade ={CascadeType.PERSIST,CascadeType.REMOVE})
    @JsonIgnore
    private List<Comment> comments;


    @OneToMany(mappedBy = "post",cascade ={CascadeType.PERSIST,CascadeType.REMOVE})
    @JsonIgnore
    private List<Reaction> reactions;




}
