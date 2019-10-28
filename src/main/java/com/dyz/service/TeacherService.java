package com.dyz.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.dyz.entity.Search;
import com.dyz.entity.Teacher;

public interface TeacherService {
	/**
	 *分页模糊查询所有老师的信息
	 * @param search
	 * @param pageable
	 * @return
	 */
	public Search selectTeacher(Search search);
	/**
	 * 添加老师信息
	 * @param tea
	 * @return
	 */
	public int addTeacher(Teacher tea);
	/**
	 *修改教师信息
	 * @param tea
	 * @return
	 */
	public int updateTeacher(Teacher tea);
}
