package com.dyz.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="readroom")
public class Readroom implements Serializable{
	
	@Id
	@GeneratedValue
	private Integer readroomId;
	private String readroomName;
	private String readroomRemark;
	
}
