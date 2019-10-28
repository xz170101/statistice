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
@Table(name="teachers")
@Data
public class Teacher implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer teacherId;
	private String tcardno;
	private String teacherName; 
	private Integer teacherSex;	
	private Integer tstatus;
	private String remark;
	private Integer sectionId;
	@ManyToOne(targetEntity=Section.class,
			cascade=CascadeType.ALL,
			fetch=FetchType.EAGER)
	@JoinColumn(name="sectionId",referencedColumnName="sectionId",insertable=false,updatable=false) 
	private Section section;
}
