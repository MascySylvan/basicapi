package com.example.basicapi.login;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import lombok.Data;

@Entity
@Table(name = Login.ENTITY_NAME)
@Data
public class Login implements java.io.Serializable {

	private static final long serialVersionUID = 7296202732672303479L;
	
	public static final String ENTITY_NAME = "Login";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "cretime")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date cretime;
	
	@Column(name = "delflg")
	private String delflg = "N";
	
	public void updateEntity(LoginDTO dto) {
		this.setUsername(dto.getUsername() );
		this.setPassword(dto.getPassword() );
		//this.setUuid(dto.getUuid() );
		//this.setCretime(dto.getCretime() );
		//this.setDelflg(dto.getDelflg() );
	}
	
}
