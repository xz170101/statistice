package com.dyz.service;

import org.springframework.web.multipart.MultipartFile;

import com.dyz.entity.Search;
import com.dyz.entity.Student;

public interface StudentService {

	/**
	 * 分页模糊查询学生信息
	 * @param search
	 * @return
	 */
	public Search selectStudent(Search search);
	/**
	 * 修改学生信息
	 * @param stu
	 * @return
	 */
	public int updateStudent(Student stu);
	/**
	 * 添加学生信息
	 * @param stu
	 * @return
	 */
	public int addStudent(Student stu);
	
	/**
	 * 导入学生
	 * @param file
	 * @return
	 */
	Integer ImportExcelStudent(MultipartFile file);
}
