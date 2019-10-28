package com.dyz.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Entity
@Table(name="students")
@Data
public class Student implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer studentId;
	private String scardNO;
	private String studentName;
	private Integer studentSex;
	private Integer Sstatus;
	private String studentRemark;
	private String stuNO;
	private Integer MembershipId;
	@ManyToOne(targetEntity=Memberships.class,
			cascade=CascadeType.ALL,
			fetch=FetchType.EAGER)
	@JoinColumn(name="MembershipId",referencedColumnName="MembershipId",insertable=false,updatable=false) 
	private Memberships meber;
}
