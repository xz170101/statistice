package com.dyz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dyz.dao.StatisticeRepository;
import com.dyz.service.StatisticsService;

@RestController
public class StatisticsController {

	@Autowired
	private StatisticeRepository statisticeRepository;
	@Autowired
	private StatisticsService statisticsService;
	@RequestMapping("/num")
	public List<Object> selectCountdeptment(){		
		
		List<Map<String,Object>> a=statisticeRepository.selectNum();
		List<Object> list=new ArrayList<>();
		list.add(a);
		/*List<Map<String,Object>> list=new ArrayList<>();		
		
		for(int i=0;i<a.size();i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			Integer[] data=new Integer[7];
			for(int j=0;j<7;j++) {
				data[j]=Integer.parseInt(a.get(i).get("people_nums").toString());
			}
			map.put("name", a.get(i).get("readroom_name").toString());
			map.put("data", data);
			list.add(map);
		}*/
		return list;
	}
	
	
	
	 @RequestMapping(value="/ECharts",method=RequestMethod.POST)
	public List<Map<String,Object>> selectEChartsByRoom(){
		return statisticsService.selectEChartsByReadroom();
	} 
	 
	 
	 @RequestMapping(value="/selectEChartsByRoomMonth",method=RequestMethod.POST)
	 public List<List<String>> selectEChartsByRoomMonth(){
		 return statisticsService.selectEChartsByReadroomMonth();
	 } 
	 /**
	  * 统计每一周内每天去游泳馆的人数
	  * @return
	  */
	 @RequestMapping(value="/selectEChartsBySwimmingPoolWeek",method =RequestMethod.POST) 
	 public List<String> selectEChartsBySwimmingPoolWeek() {
	 return statisticsService.selectEChartsBySwimmingPoolWeek(); 
	 }
}
