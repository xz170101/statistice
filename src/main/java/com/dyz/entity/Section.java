package com.dyz.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="sections")
@Data
public class Section implements Serializable{
	@Id
	@GeneratedValue	
	private Integer sectionId;
	private String sectionName;
	private String sectionRemark;
}
