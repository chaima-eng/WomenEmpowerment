package com.example.backend.Controller;


import com.example.backend.Entity.*;
import com.example.backend.Repository.IntUserRepo;
import com.example.backend.Repository.PasswordTokenRepository;
import com.example.backend.Service.JwtService;
import com.example.backend.Service.PasswordResetService;
import com.example.backend.config.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;


@RestController
@CrossOrigin
@RequestMapping("/log")
@Slf4j
public class JwtController {


	private static final String SECRET_KEY = "secretkey";
	private static final int TOKEN_VALIDITY = 3600*5;





	@Autowired
	private JwtUtil jwtUtil;


	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	PasswordResetService passwordResetService;

	@Autowired
	PasswordTokenRepository passwordTokenRepository;


	@Autowired
	private JwtService jwts;

	@Autowired
	private IntUserRepo userRepo;


	@Autowired
	private PasswordEncoder pc;




	@PostMapping("/auth")
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		return jwts.createJwtToken(jwtRequest);
	}

	@PostConstruct
	public void initRoleAndUser() {
		jwts.initRoleAndUser();
	}

	@PostMapping("/forget-password")
	public JwtResponse ForgetPass(@RequestBody String email) {
		return passwordResetService.forgetPassword(email);

	}

	@PostMapping("/reset-password")
	public JwtResponse changePassword(@RequestBody PasswordReset passwordDto, @RequestParam String token)
	{
		System.out.print(passwordDto );
		String result = passwordResetService.validatePasswordResetToken(token);
		if (result != null) {
			return new JwtResponse("Error Token is : " + result);

		}

		User user =  passwordTokenRepository.findByToken(token).getUser();
		if (user.getEmail() != null) {
			passwordResetService.changeUserPassword(user, passwordDto.getNewPassword());
			passwordTokenRepository.delete(passwordTokenRepository.findByToken(token));
			return new JwtResponse("Password changed successfully!");
		} else {
			return new JwtResponse("Error Token is : " + result);

		}
	}








}
