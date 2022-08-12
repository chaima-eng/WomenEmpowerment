package com.example.backend.Service;


import com.example.backend.Entity.*;

import com.example.backend.Registration.token.ConfirmationToken;
import com.example.backend.Registration.token.ConfirmationTokenService;
import com.example.backend.Repository.IntUserRepo;
import com.example.backend.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Primary
@Service
public class JwtService implements UserDetailsService {



	@Autowired
	private IntUserRepo ur;
	
	@Autowired
	private JwtUtil jwtu;
	
	@Autowired
	private AuthenticationManager am;

	@Autowired
	private PasswordEncoder pc;


	private final ConfirmationTokenService confirmationTokenService;

	public JwtService(ConfirmationTokenService confirmationTokenService) {
		this.confirmationTokenService = confirmationTokenService;
	}
	public void initRoleAndUser() {
		User adminUser = new User();
			adminUser.setUserName("admin");
			adminUser.setFirstName("admin");
			adminUser.setPassword(pc.encode("admin"));
			adminUser.setLastName("admin");
			adminUser.setEmail("admin@gmail.com");
			adminUser.setEnabled(true);
			adminUser.setRole(Role.Admin);
			ur.save(adminUser);

	}


	public JwtResponse createJwtToken(JwtRequest jwtRequest)throws Exception{
		System.out.print(jwtRequest);
		String userName=jwtRequest.getUserName();
		System.out.print(userName);
		String userPassword=jwtRequest.getUserpassword();
		authenticate(userName, userPassword);

		final	UserDetails userDetails = loadUserByUsername(userName);

		String newGeneratedToken=jwtu.generateToken(userDetails);
		User user = ur.findByUserName(userName);

		System.out.print(user);


		return new JwtResponse(user, newGeneratedToken,user.getRole().name());
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = ur.findByUserName(username);
		
		if(user!=null){
			return new org.springframework.security.core.userdetails.User(
					user.getUserName(),
					user.getPassword(), 
					getAuthorities(user));
		}
		else
		{
			throw new UsernameNotFoundException("user name is not valid");
		}
		
	}
	
	
	public User getFulluser (String username) throws UsernameNotFoundException{
		return ur.findByUserName(username);
	}
	
	private Set getAuthorities (User user){
		Set authorities = new HashSet();
		
		return authorities;
	}
	
	private void authenticate (String userName,String userPassword) throws Exception{
		try{
			am.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));

		}catch(DisabledException e){
			throw new Exception("User is disabled ");
		}catch(BadCredentialsException e ){
			throw new Exception("bad credentials from User");
		}
	}





	public String signUpUser(User appUser)
	{
		boolean userExists = ur
				.findByEmail(appUser.getEmail()).isPresent();


		if (userExists) {
			// TODO check of attributes are the same and
			// TODO if email not confirmed send confirmation email.

			throw new IllegalStateException("email already taken");
		}

		String encodedPassword = pc
				.encode(appUser.getPassword());

		appUser.setPassword(encodedPassword);

		ur.save(appUser);

		// TODO Send Confirmation Token ...
		String token = UUID.randomUUID().toString();

		ConfirmationToken confirmationToken = new ConfirmationToken(
				token,
				LocalDateTime.now(),
				LocalDateTime.now().plusDays(2),
				appUser
		);

		confirmationTokenService.saveConfirmationToken(
				confirmationToken);


		// TODO Send Email  ...







		return token;
	}

	public int enableAppUser(String email) {
		return ur.enableAppUser(email);
	}























}
