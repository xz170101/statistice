package com.dyz.dao;

import java.util.List;

import org.hibernate.annotations.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dyz.entity.ConsumeLogs;

public interface ConsumeLogsRepository extends JpaRepository<ConsumeLogs, Integer>{

	@Query(value="insert into cosumelogs "
			+ " (ccardno,in_time,readroom_id,cstatus) "
			+ "values(:#{#logs.CCardNO}, "
			+ ":#{#logs.InTime}, "
			+ ":#{#logs.readroom.readroomId}, "
			+ ":#{#logs.Cstatus}) "
			,nativeQuery=true)
	@Modifying
	@Transactional
	public Integer addLogs(ConsumeLogs logs);
	
	@Query(value="UPDATE cosumelogs c set  c.out_time=NOW(),c.cstatus=0\r\n" + 
			"where c.cstatus=:#{#logs.Cstatus} and c.ccardno=:#{#logs.CCardNO}\r\n" + 
			"ORDER BY c.in_time DESC\r\n" + 
			"LIMIT 1 "
			,nativeQuery=true)
	@Modifying
	@Transactional
	public Integer updateLogs(ConsumeLogs logs);
	
	@Transactional
	@Query(value = "SELECT * FROM cosumelogs WHERE ccardno = :logs and cstatus=1",nativeQuery=true)
	public ConsumeLogs findBycode(@Param(value="logs") String logs);
	
	@Query(value="select \r\n" + 
			"x.readroom_name,\r\n" + 
			"sum(case when x.a='t' then   x.ac else 0 end) 教师,\r\n" + 
			"sum(case when x.a='s' then  x.ac else 0 end) 同学\r\n" + 
			"from \r\n" + 
			"(\r\n" + 
			"SELECT substring(c.ccardno,1,1) a,r.readroom_name,COUNT(c.ccardno) ac from cosumelogs c ,readroom r \r\n" + 
			"where c.readroom_id=r.readroom_id and c.cstatus>0\r\n" + 
			"GROUP BY substring(c.ccardno,1,1),r.readroom_name\r\n" + 
			") x\r\n" + 
			"GROUP BY readroom_name",nativeQuery=true)
	List<List<String>> selectEchartsBySex();
	
}
