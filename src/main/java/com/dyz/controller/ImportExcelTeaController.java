package com.dyz.controller;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dyz.dao.StudentRepository;
import com.dyz.service.ImportExcelTeacherService;
import com.dyz.service.StudentService;
/**
 * TeacherExcelImpro
 * @author 皮特朱
 *
 */
@RestController
public class ImportExcelTeaController {
	
	@Autowired
	private ImportExcelTeacherService importExcelTeacherService;
	@Autowired
	private StudentService studentService;
	@RequestMapping("ImportExcelTeacher")
	public Integer ImportExcelTeacher( @RequestParam(value="invoiceFile") MultipartFile file) {
 		
		return importExcelTeacherService.ImportExcelTeacher(file);
	}
	
	
	@RequestMapping("ImportExcelStudent")
	public Integer ImportExcelStudent( @RequestParam(value="invoiceFile") MultipartFile file) {
 
		System.out.println("文件名：：：："+file.getOriginalFilename());
		return studentService.ImportExcelStudent(file);
	}

}
