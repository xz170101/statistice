package com.dyz.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.dyz.entity.Memberships;
import com.dyz.entity.Search;


public interface MembershipsService {
	/**
	 * 学生分页模糊查询
	 * @param search
	 * @param pageable
	 * @return
	 */
	public List<Memberships> queryByMemberPage(Search search,Pageable pageable);
	/**
	 * 根据id修改学生信息
	 * @param student
	 * @return
	 */
	//Integer updateStudent(Student student);
	/**
	 * 根据id删除一条学生信息
	 * @param studentId
	 * @return
	 */
	//Integer deleteStudent(Integer studentId);
	
	
	/**
	 * eCharts饼图
	 */
	List<Object> selectdeptmentAndDegreeCount();
}
