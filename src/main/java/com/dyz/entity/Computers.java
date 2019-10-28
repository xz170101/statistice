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
@Data
@Table(name="computers")
public class Computers implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
