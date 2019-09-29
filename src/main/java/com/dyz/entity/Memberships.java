package com.dyz.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="memberships")
public class Memberships implements Serializable{

	@Id
	@GeneratedValue
	private Integer MembershipId;
	private String department;
	private String specialty;
	private String degree;
}
