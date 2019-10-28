package com.dyz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dyz.dao.ReadroomRepository;
import com.dyz.entity.Readroom;

@RestController
public class ReadroomController {

	@Autowired
	private ReadroomRepository readroomRepository;
	
	@RequestMapping(value="/readroomName",method=RequestMethod.POST)
	public List<Readroom> selectName(){
		return readroomRepository.findAll();
	}
}
