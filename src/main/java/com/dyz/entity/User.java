package com.dyz.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Entity
@Component
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer user_id ;
	private String user_number;
	private String user_password;
	private String user_salt;
	
}
