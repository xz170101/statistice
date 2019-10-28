package com.dyz.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dyz.dao.StatisticeRepository;
 import com.mysql.fabric.xmlrpc.base.Array;
@Service
public class StatisticsServiceImpl implements StatisticsService{

	@Autowired
	private StatisticeRepository statisticsRepository;
	 @Override
	public List<Map<String,Object>> selectEChartsByReadroom() {
		// TODO Auto-generated method stub
		return statisticsRepository.selectEChartsByReadroom();
	}
	 
	@Override
	public List<List<String>> selectEChartsByReadroomMonth() {
		// TODO Auto-generated method stub
		String []sz= {"month", "Jan", "Feb", "Mar", "Apr", "May", "Jun","Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		List<String>  list= new ArrayList<>(Arrays.asList(sz)) ;
		List<List<String>> dataList = statisticsRepository.selectEChartsByReadroomMonth();
 		dataList.add(0, list);
		return dataList;
	} 
	@Override
	public List<String> selectEChartsBySwimmingPoolWeek() {
		// TODO Auto-generated method stub
		List<String> poolList = statisticsRepository.selectEChartsBySwimmingPoolWeek();
		return poolList;
	} 
}
