package com.example.backend.Entity;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
	
	private User user;
	private String token;
	private String role;
	public JwtResponse(String role) {
		super();
		this.role = role;
	}
	
	

}
