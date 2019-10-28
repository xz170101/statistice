package com.dyz.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dyz.entity.Search;
import com.dyz.entity.Teacher;
import com.dyz.service.TeacherService;

@RestController
public class TeacherController {

	/*@Autowired
	private TeacherRepository repository;*/
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping("/teaPage")
	public Search selectTeaByPage(Search search,
			@RequestParam("page") Integer page,
			@RequestParam("rows")Integer rows){	
		search.setPage((page-1)*rows);
		search.setPageSize(rows);
		//System.out.println(teacherService.selectTeacher(search)+"=======================");
		return teacherService.selectTeacher(search);
	}
	
	@RequestMapping("/addTea")
	public int save(Teacher tea) {
		
		return teacherService.addTeacher(tea);				
	}
	
	@RequestMapping("/updTea")
	public int update(Teacher tea) {			
		return teacherService.updateTeacher(tea);				
	}
}
