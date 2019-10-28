package com.dyz.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Component
@Entity
@Data
@Table(name="cosumelogs")
@EntityListeners(AuditingEntityListener.class)
public class ConsumeLogs implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cosumelogsId;
	private String cCardNO;
	
	@CreatedDate
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date InTime;
	
	@CreatedDate
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date OutTime;
	private Integer Cstatus;
	 
	@ManyToOne(targetEntity=Readroom.class,
			cascade=CascadeType.ALL,
			fetch=FetchType.EAGER)
	@JoinColumn(name="readroomId",referencedColumnName="readroomId") 
	private Readroom readroom;
}
