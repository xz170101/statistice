package com.dyz.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dyz.dao.MembershipsRepository;
import com.dyz.entity.Memberships;
import com.dyz.entity.Search;
import com.dyz.service.MembershipsService;

@RestController
public class MembershipsController {

	@Autowired
	private MembershipsService mservice;
	@Autowired
	private MembershipsRepository repository; 
	/**
	 * 模糊分页查询
	 * @param studentSearch
	 * @return
	 */
	@RequestMapping("/mPage")
	public List<Memberships> selectStuByPage(Search search,
			@RequestParam("page") Integer page,
			@RequestParam("row") Integer size){	
		Pageable pagea=new PageRequest(page-1, size);
		List<Memberships> list = mservice.queryByMemberPage(search, pagea);
		return list;
	}
	
	@RequestMapping("/selectdegree")
	public List<Map<String, Object>> selectCountdegree() {
		
		return repository.selectCountdegree();
	}
	
	@RequestMapping("/selectdeptment")
	public List<Map<String, Object>> selectCountdeptment() {
		
		return repository.selectCountdeptment();
	}
	
	/**
	 * eCharts饼图
	 * @return
	 */
	@RequestMapping("/selectdeptmentAndDegreeCount")
	public List<Object> selectCount() {
		return mservice.selectdeptmentAndDegreeCount();
	}
}
