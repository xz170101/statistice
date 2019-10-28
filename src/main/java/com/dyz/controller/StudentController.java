package com.dyz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dyz.entity.Search;
import com.dyz.entity.Student;
import com.dyz.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/stuPage")
	public Search selectStuByPage(Search search,
			@RequestParam("page") Integer page,
			@RequestParam("rows") Integer rows){	
		search.setPage((page-1)*rows);
		search.setPageSize(rows);
		return studentService.selectStudent(search);
	}
	@RequestMapping("/addStu")
	public int save(Student stu) {
		
		return studentService.addStudent(stu);				
	}
	
	@RequestMapping("/updStu")
	public int update(Student stu) {			
		return studentService.updateStudent(stu);		
	}
}
