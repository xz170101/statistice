package com.dyz.service;

import java.util.List;

import com.dyz.entity.ConsumeLogs;

public interface ConsumeLogsService {

	/**
	 * 打卡进入
	 * @param logs
	 * @return
	 */
	Integer addLogs(ConsumeLogs logs);
	
	/**
	 * 打卡离开
	 * @param logs
	 * @return
	 */
	Integer updateLogs(ConsumeLogs logs);
	/**
	 * ECHARTS
	 * @return
	 */
	List<List<String>> selectEchartsBySex();
}
