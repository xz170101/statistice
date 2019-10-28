package com.dyz.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.dyz.entity.Statistics;

public interface StatisticeRepository extends JpaRepository<Statistics, Integer>{
	
	
	//查询当天各阅览室来的人数
	//Statistics selectByYearAndMonthAndDayAndReadroomIdCount();
	/*@Query(value="select * from statistics s \r\n" + 
			"where  s.`day`=?3 and s.`month`=?2 and s.`year`=?1 and s.readroom_id=?4"
			,nativeQuery=true)*/
	Statistics findByYearAndMonthAndDayAndReadroomId(Integer year,Integer month,Integer day,Integer readroomId);
	
	/*@Query(value="select `year`,`month`,`day`,readroom_id from statistics"
			+ " where `year`=:#{#s.year} and `month`=:#{#s.month} "
			+ "and `day`=:#{#s.day} and readroom_id=:#{#s.readroom.readroomId}",nativeQuery=true)
	@Transactional
	public Statistics selectCount(Statistics s)
	
	select count(*),m.degree
	from memberships m 
	INNER JOIN students s 
	on s.membership_id=m.membership_id 
	GROUP BY m.degree
	
	*/
	
	@Query(value="select readroom.readroom_name,\r\n" + 
			"sum(case when a.click_date='2019-10-10' then statistics.people_nums else 0 end) as '2019-10-10',\r\n" + 
			"sum(case when a.click_date='2019-10-09' then statistics.people_nums else 0 end) as '2019-10-09',\r\n" + 
			"sum(case when a.click_date='2019-10-08' then statistics.people_nums else 0 end) as '2019-10-08',\r\n" + 
			"sum(case when a.click_date='2019-10-07' then statistics.people_nums else 0 end) as '2019-10-07',\r\n" + 
			"sum(case when a.click_date='2019-10-06' then statistics.people_nums else 0 end) as '2019-10-06',\r\n" + 
			"sum(case when a.click_date='2019-10-05' then statistics.people_nums else 0 end) as '2019-10-05'\r\n" + 
			"from (SELECT CURDATE() AS click_date		   \r\n" + 
			"UNION ALL\r\n" + 
			"SELECT DATE_SUB(CURDATE(), INTERVAL 1 DAY) AS click_date\r\n" + 
			"UNION ALL\r\n" + 
			"SELECT DATE_SUB(CURDATE(), INTERVAL 2 DAY) AS click_date\r\n" + 
			"UNION ALL\r\n" + 
			"SELECT DATE_SUB(CURDATE(), INTERVAL 3 DAY) AS click_date\r\n" + 
			"UNION ALL\r\n" + 
			"SELECT DATE_SUB(CURDATE(), INTERVAL 4 DAY) AS click_date\r\n" + 
			"UNION ALL\r\n" + 
			"SELECT DATE_SUB(CURDATE(), INTERVAL 5 DAY) AS click_date\r\n" + 
			"UNION ALL\r\n" + 
			"SELECT DATE_SUB(CURDATE(), INTERVAL 6 DAY) AS click_date) a left join\r\n" + 
			"statistics on a.click_date = DATE_FORMAT(statistics.nowTime,\"%Y-%m-%d\") \r\n" + 
			"LEFT JOIN readroom on statistics.readroom_id=readroom.readroom_id GROUP BY readroom.readroom_name\r\n"  
			,nativeQuery=true)
	@Transactional
	public List<Map<String, Object>> selectNum();
	

	/**
	 * 查询各个阅览室你每日的来人数
	 */
	 @Query(value="select  r.readroom_name readName ,s.people_nums num, dayname(date_format(concat(s.`year`,'-',s.`month`,'-',s.`day`), '%Y-%m-%d'))  sdate " + 
			"from statistics s,readroom r " + 
			"where s.readroom_id=r.readroom_id " + 
			"and YEARWEEK( date_format(concat(s.`year`,'-',s.`month`,'-',s.`day`), '%Y-%m-%d'),1) = YEARWEEK(now(),1)  " + 
			"ORDER BY  dayname(date_format(concat(s.`year`,'-',s.`month`,'-',s.`day`), '%Y-%m-%d'))"
			, nativeQuery=true)
	 List<Map<String,Object>> selectEChartsByReadroom(); 
	 
	 /**
	  * *查询各月份每个阅览室的来人数
	  */
	 @Query(value="select \r\n" + 
	 		"a.readNAme,\r\n" + 
	 		"sum(case when a.amonth=1 then  a.monthNum else 0 end) 一月,\r\n" + 
	 		"sum(case when a.amonth=2 then  a.monthNum else 0 end) 二月,\r\n" + 
	 		"sum(case when a.amonth=3 then  a.monthNum else 0 end) 三月,\r\n" + 
	 		"sum(case when a.amonth=4 then a.monthNum else 0 end) 四月,\r\n" + 
	 		"sum(case when a.amonth=5 then a.monthNum else 0 end) 五月,\r\n" + 
	 		"sum(case when a.amonth=6 then  a.monthNum else 0 end) 六月,\r\n" + 
	 		"sum(case when a.amonth=7 then  a.monthNum else 0 end) 七月,\r\n" + 
	 		"sum(case when a.amonth=8 then  a.monthNum else 0 end) 八月,\r\n" + 
	 		"sum(case when a.amonth=9 then  a.monthNum else 0 end) 九月,\r\n" + 
	 		"sum(case when a.amonth=10 then  a.monthNum else 0 end) 十月,\r\n" + 
	 		"sum(case when a.amonth=11 then  a.monthNum else 0 end) 十一月,\r\n" + 
	 		"sum(case when a.amonth=12 then  a.monthNum else 0 end) 十二月\r\n" + 
	 		"FROM  (SELECT COUNT( s.`month`) monthNum,  s.`month` amonth ,r.readroom_name readNAme\r\n" + 
	 		"			FROM  statistics s ,readroom r\r\n" + 
	 		"			where r.readroom_id=s.readroom_id and  s.`year`=YEAR(NOW())\r\n" + 
	 		"			GROUP BY  s.`month` ,r.readroom_name ORDER BY s.`month`) a\r\n" + 
	 		"GROUP BY a.readNAme"
			 , nativeQuery=true)
		 List<List<String>> selectEChartsByReadroomMonth(); 
	 @Query(value="select " + 
	 		"	sum(case when a.rdate='Monday' then  a.rnum else 0 end) 周一,\r\n" + 
	 		"	sum(case when a.rdate='Tuesday' then  a.rnum else 0 end) 周二,\r\n" + 
	 		"	sum(case when a.rdate='Wednesday' then  a.rnum else 0 end) 周三,\r\n" + 
	 		"	sum(case when a.rdate='Thursday' then  a.rnum else 0 end) 周四,\r\n" + 
	 		"	sum(case when a.rdate='Friday' then  a.rnum else 0 end) 周五,\r\n" + 
	 		"	sum(case when a.rdate='Saturday' then  a.rnum else 0 end) 周六,\r\n" + 
	 		"	sum(case when a.rdate='Sunday' then  a.rnum else 0 end) 周日\r\n" + 
	 		"from(\r\n" + 
	 		"	SELECT r.readroom_name rname,s.people_nums rnum,dayname(date_format(concat(s.`year`,'-',s.`month`,'-',s.`day`), '%Y-%m-%d')) rdate  \r\n" + 
	 		"	from readroom r ,statistics s\r\n" + 
	 		"	where  r.readroom_name='游泳馆' and r.readroom_id=s.readroom_id and   YEARWEEK( date_format(concat(s.`year`,'-',s.`month`,'-',s.`day`), '%Y-%m-%d'),1) = YEARWEEK(now(),1)) a", nativeQuery=true)
	     List<String> selectEChartsBySwimmingPoolWeek();
	
}
