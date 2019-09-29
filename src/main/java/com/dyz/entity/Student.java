package com.dyz.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="students")
@Data
public class Student implements Serializable{
	
	@Id
	@GeneratedValue	
	private Integer studentId;
	private String SCardNO;
	private String studentName;
	private Integer studentSex;
	private Integer Sstatus;
	private String studentRemark;
	private String stuNO;
	@ManyToOne(targetEntity=Memberships.class,
			cascade=CascadeType.ALL,
			fetch=FetchType.EAGER)
	@JoinColumn(name="MembershipId",referencedColumnName="MembershipId") 
	private Memberships meber;
}
