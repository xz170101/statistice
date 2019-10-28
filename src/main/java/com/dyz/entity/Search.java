package com.dyz.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Search {
	private Integer page;
	private Integer pageSize;
	private Integer total;
	private List<?> rows;
	/**
	 * 学生的模糊查询
	 * 根据姓名性别 系专业
	 */
	private String studentName;
	private Integer studentSex;
	private Memberships meber;
	
	/**
	 * 老师的模糊查询
	 * 根据姓名 性别 科室
	 */			    
	private String teacherName;
	private Integer teacherSex;
	private Section section;
	
	/**
	 * 系别的模糊查询
	 */
	private String department;
	private String specialty;
}
