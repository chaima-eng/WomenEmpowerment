package com.example.backend.Controller;


import com.example.backend.Entity.*;
import com.example.backend.Repository.IntUserRepo;
import com.example.backend.Service.JwtService;
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
	private JwtService jwts;

	@Autowired
	private IntUserRepo userRepo;


	@Autowired
	private PasswordEncoder pc;




	@PostMapping("/auth")
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		return jwts.createJwtToken(jwtRequest);
	}

	/*
	@PostMapping("/add")
	public void save(@RequestBody User user) throws Exception{

		user.setPassword(pc.encode(user.getPassword()));
		userRepo.save(user);
	}

	 */




	@PostConstruct
	public void initRoleAndUser() {
		jwts.initRoleAndUser();
	}







}
