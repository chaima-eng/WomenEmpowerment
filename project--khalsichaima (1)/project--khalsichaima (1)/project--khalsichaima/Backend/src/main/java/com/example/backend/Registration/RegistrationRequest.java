package com.example.backend.Registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

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

    private final String dateOfBirth;
    private final String civilState;
    private final String adresse;
    private final String postalcode;
    private final String studyLevel;
    private final String job;
    private final String monthlyUncome;
    private final String nbrOffamilly;

}
