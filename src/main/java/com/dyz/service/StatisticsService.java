package com.dyz.service;

import java.util.List;
import java.util.Map;


public interface StatisticsService {
	 List<Map<String,Object>> selectEChartsByReadroom();
	 List<List<String>> selectEChartsByReadroomMonth(); 
	 //当前周内每天去游泳馆的人数
	 List<String> selectEChartsBySwimmingPoolWeek(); 
}
