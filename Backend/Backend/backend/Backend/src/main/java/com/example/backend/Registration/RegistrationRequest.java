package com.example.backend.Registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    private final String cin;

    private final Date dateOfBirth;
    private final String civilState;
    private final String adresse;
    private final int postalcode;
    private final String studyLevel;
    private final String job;
    private final int monthlyUncome;
    private final int nbrOffamilly;
    private final String tel;
}
