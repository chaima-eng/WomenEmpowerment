package com.example.backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public  class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idUser;
    private String userName;
    private String password;
    private String cin;
    private String tel;
    private String email;
    private String lastName;
    private String firstName;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String civilState;
    private String adresse;
    private int postalcode;
    private String studyLevel;
    private String job;
    private int monthlyUncome;
    private int nbrOffamilly;



    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean locked = false;
    private Boolean enabled = false;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;

    public User(String userName,String firstName, String lastName, String email,String cin,String adresse,Date dateOfBirth,
                String civilState ,
                int postalcode,
                String studyLevel,
                String job,
                int monthlyUncome,

               int nbrOffamilly,
                String tel
            ,String password,Role role) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cin= cin;
        this.adresse=adresse;
        this.dateOfBirth=dateOfBirth;
        this.civilState=civilState;
        this.postalcode=postalcode;
        this.studyLevel=studyLevel;
        this.job=job;
        this.monthlyUncome=monthlyUncome;
        this.nbrOffamilly=nbrOffamilly;
        this.tel=tel;
        this.password = password;
        this.role = role;

    }
}
