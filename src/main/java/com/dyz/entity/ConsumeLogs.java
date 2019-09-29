package com.dyz.entity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="cosumelogs")
public class ConsumeLogs implements Serializable{

	@Id
	@GeneratedValue
	private Integer cosumelogsId;
	private String CCardNO;
	private Date InTime;
	private Date OutTime;
	private Integer Cstatus;
	@ManyToOne(targetEntity=Readroom.class,
			cascade=CascadeType.ALL,
			fetch=FetchType.EAGER)
	@JoinColumn(name="readroomId",referencedColumnName="readroomId") 
	private Readroom readroom;
}
