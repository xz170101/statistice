package com.dyz.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dyz.dao.SectionRepository;
import com.dyz.dao.TeacherRepository;
import com.dyz.entity.Search;
import com.dyz.entity.Section;
import com.dyz.entity.Student;
import com.dyz.entity.Teacher;

@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private TeacherRepository repository;
	
	@Autowired
	private SectionRepository sectionRepository;
	@Override
	public Search selectTeacher(Search search) {
		Integer teacherSex=search.getTeacherSex();
 		String teacherName=search.getTeacherName();
 		 
		if( teacherSex<0 && teacherName==null || "".equals(teacherName)) {
			 
			System.out.println("查询所有……");
			Integer total=repository.selectCount(search);
			List<Teacher> rows=repository.selectTeacher(search);
			search.setTotal(total);
			search.setRows(rows);
			return search;
		}else if(teacherName !=null && teacherSex<0 ){
			System.out.println("查询名字……");
			Integer total=repository.selectByTeacherNameCount(teacherName);
			List<Teacher> rows=repository.findByTeacherName(teacherName);
			search.setTotal(total);
			search.setRows(rows);
			return search;
		}else if(teacherName==null && teacherSex>=0 ){
			System.out.println("查询性别……");
			Integer total=repository.selectByTeacherSexCount(teacherSex);
			List<Teacher> rows=repository.findByTeacherSex(teacherSex);
			search.setTotal(total);
			search.setRows(rows);
			return search;
		}else {
			Integer total=repository.selectByTSexAndTNameCount(teacherSex, teacherName);
			List<Teacher> rows=repository.findByTeacherSexAndTeacherName(teacherSex, teacherName);
			search.setTotal(total);
			search.setRows(rows);
			return search;
		}
	}
	
	@Override
	public int addTeacher(Teacher tea) {
		// TODO Auto-generated method stub
		//System.out.println(tea.toString());
		Section section = sectionRepository.findById(1).get();
		tea.setSection(section);
		return repository.addTeacher(tea);
	}
	@Override
	public int updateTeacher(Teacher tea) {
		// TODO Auto-generated method stub
		Section section = sectionRepository.findById(1).get();
		tea.setSection(section);
		return repository.updateTeacher(tea);
	}

}
