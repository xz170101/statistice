package com.dyz.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dyz.dao.ConsumeLogsRepository;
import com.dyz.dao.ReadroomRepository;
import com.dyz.dao.StudentRepository;
import com.dyz.dao.TeacherRepository;
import com.dyz.entity.ConsumeLogs;
import com.dyz.entity.Readroom;
import com.dyz.entity.Student;
import com.dyz.entity.Teacher;

@Service
public class ConsumeLogsServiceImpl implements ConsumeLogsService {

	@Autowired
	private ConsumeLogsRepository repository;
	@Autowired
	private TeacherRepository trepository;
	@Autowired
	private StudentRepository srepository;
	
	@Transactional
	@Override
	public Integer addLogs(ConsumeLogs logs) {
		
		return repository.addLogs(logs);
	}
/**
 * 打卡离开
 */
	@Override
	public Integer updateLogs(ConsumeLogs logs) {
		// TODO Auto-generated method stub
		String code=logs.getCCardNO();
		if(repository.updateLogs(logs)>0) {
			if(code.contains("t")) {
 				Teacher t=trepository.findBytcardno(code);
				t.setTstatus(0);
				trepository.save(t);
			}
			if(code.contains("s")) {
 				Student s=srepository.findByscardNO(code);
				s.setSstatus(0);
				srepository.save(s);
			}		
 			return 1;
		}
		 
		return 0;
	}

	/**
	 * CHARTS
	 */
	@Override
	public List<List<String>> selectEchartsBySex() {
		// TODO Auto-generated method stub
		String []sz= {"RS", "教师", "学生"};
		List<String>  list= new ArrayList<>(Arrays.asList(sz)) ;
		List<List<String>> dataList = repository.selectEchartsBySex();
 		dataList.add(0, list);
 		System.out.println("数据："+dataList);
		return dataList;
	}
	
	
	
}
