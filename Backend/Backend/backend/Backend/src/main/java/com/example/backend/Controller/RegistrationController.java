package com.example.backend.Controller;

import com.example.backend.Registration.RegistrationRequest;
import com.example.backend.Registration.RegistrationService;
import com.example.backend.Registration.token.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
@CrossOrigin("*")

public class RegistrationController {

    private final RegistrationService registrationService;


    @PostMapping
    public Optional<ConfirmationToken> register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }


    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }






}
