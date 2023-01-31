package com.example.basicapi.login;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
public class LoginDTO {

	private String uuid;
	private String username;
	private String password;
	private Date cretime;
	private String delflg;
	
	public LoginDTO(Login login) {
		if (login != null) {
			this.setUuid(login.getUuid() );
			this.setUsername(login.getUsername() );
			this.setPassword(login.getPassword() );
			this.setCretime(login.getCretime() );
			this.setDelflg(login.getDelflg() );
		}
	}
	
	public Login toEntity() {
		Login login = new Login();
		login.setUuid(this.getUuid() );
		login.setUsername(this.getUsername() );
		login.setPassword(this.getPassword() );
		login.setCretime(this.getCretime() );
		login.setDelflg(this.getDelflg() );
		
		return login;
	}
}
