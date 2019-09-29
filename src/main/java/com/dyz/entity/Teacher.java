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
@Table(name="teachers")
@Data
public class Teacher implements Serializable{
	
	@Id
	@GeneratedValue	
	private Integer teacherId;
	private String TCardNO;
	private String teacherName;
	private Integer TeacnerSex;	
	private Integer Tstatus;
	private String remark;
	@ManyToOne(targetEntity=Section.class,
			cascade=CascadeType.ALL,
			fetch=FetchType.EAGER)
	@JoinColumn(name="sectionId",referencedColumnName="sectionId") 
	private Section section;
}
