package com.example.backend.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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


    public User(String userName,String firstName, String lastName, String email, String password,Role role) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;

    }
}
