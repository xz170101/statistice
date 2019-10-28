package com.dyz.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dyz.dao.ConsumeLogsRepository;
import com.dyz.dao.ReadroomRepository;
import com.dyz.dao.StatisticeRepository;
import com.dyz.dao.StudentRepository;
import com.dyz.dao.TeacherRepository;
import com.dyz.entity.ConsumeLogs;
import com.dyz.entity.Readroom;
import com.dyz.entity.Statistics;
import com.dyz.entity.Student;
import com.dyz.entity.Teacher;
import com.dyz.service.ConsumeLogsService;

import net.bytebuddy.agent.builder.AgentBuilder.Identified.Narrowable;

@RestController
public class ConsumeLogsController {
	@Autowired
	private ReadroomRepository readroomRepository;
	@Autowired
	private ConsumeLogsRepository consumeLogsRepository;
	@Autowired
	private ConsumeLogsService consumeLogsService;
	@Autowired
	private TeacherRepository trepository;
	@Autowired
	private StudentRepository srepository;
	@Autowired 
	private StatisticeRepository ssrepository;
	@RequestMapping("/addlogs")
	public Integer addLogs(ConsumeLogs logs,@RequestParam(value="readroomId") Integer rearoomId) {		
		/**
		 * 1.先去获取卡号
		 * 2.判断卡号是否包含t和s
		 * 3.如果包含t或s就修改teacher或student表中的状态
		 * 再修改日志表
		 * 
		 */
		String code=logs.getCCardNO();
		if(code.contains("t")) {
			Teacher t=trepository.findBytcardno(code);
			t.setTstatus(rearoomId);
			trepository.save(t);
		}
		if(code.contains("s")) {
			Student s=srepository.findByscardNO(code);
			s.setSstatus(rearoomId);
			srepository.save(s);
		}
		Date d=new Date();
		Calendar a=Calendar.getInstance();
		Statistics s=ssrepository.findByYearAndMonthAndDayAndReadroomId(a.get(Calendar.YEAR),a.get(Calendar.MONTH) + 1, a.get(Calendar.DATE), rearoomId);
		if(s==null) {
			Statistics ss=new Statistics();
			ss.setYear(a.get(Calendar.YEAR));
			ss.setPeopleNums(1);
			ss.setMonth(a.get(Calendar.MONTH) + 1);
			ss.setDay(a.get(Calendar.DATE));
			ss.setReadroomId(rearoomId);
			ssrepository.save(ss);
		}else {
 			s.setPeopleNums(s.getPeopleNums()+1);
			ssrepository.save(s);
		}
		Readroom r=readroomRepository.findById(rearoomId).get();
				
				logs.setReadroom(r);
				logs.setCstatus(rearoomId);
				logs.setInTime(d);
		return consumeLogsService.addLogs(logs);
	}
	/**
	 * 打卡离开
	 * @param logs
	 * @return
	 */
	@RequestMapping("/updatelogs")
	public Integer updateLogs(ConsumeLogs logs ){
		return consumeLogsService.updateLogs(logs);
	}
	
	
	@RequestMapping("/selectEchartsByStuAndTea")
	public List<List<String>> selectEchartsBySex() {
		
		return consumeLogsService.selectEchartsBySex();
	}
}
