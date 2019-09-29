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
@Data
@Table(name="computers")
public class Computers implements Serializable{

	@Id
	@GeneratedValue
	private Integer computerId;
	private String computerName;
	private String Ip;
	private String computerRemark;
	@ManyToOne(targetEntity=Readroom.class,
			cascade=CascadeType.ALL,
			fetch=FetchType.EAGER)
	@JoinColumn(name="readroomId",referencedColumnName="readroomId") 
	private Readroom readroom;
}
