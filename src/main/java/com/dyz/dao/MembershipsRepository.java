package com.dyz.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.dyz.entity.Memberships;



public interface MembershipsRepository extends JpaRepository<Memberships, Integer>,JpaSpecificationExecutor<Memberships>{

	/**
	 * value="update teachers set tcardno=:#{#tea.tcardno},teacher_name=:#{#tea.teacherName},"
			+ "teacher_sex=:#{#tea.teacherSex},tstatus=:#{#tea.tstatus},"
			+ "remark=:#{#tea.remark},section_id=:#{#tea.section.sectionId} where teacher_id= :#{#tea.teacherId}",nativeQuery=true
	 * @param member
	 * @return
	 */
	@Query(value="update memberships set degree=:#{#member.degree},"
			+ "department=:#{#member.department},specialty=:#{#member.specialty}"
			+ " where membership_id=:#{#member.membershipId}",nativeQuery=true)
	@Modifying
	@Transactional
	public Integer updateMember(Memberships member);
	
	@Query(value="insert into memberships(degree,department,specialty)values()",nativeQuery=true)
	@Modifying
	@Transactional
	public Integer insertMember(Memberships member);
	
	/**
	 * 查询每个系下的专业个数、系名
	 * @return
	 */
	@Query(value="SELECT COUNT(s.student_name) 'value',m.degree 'name'\r\n" + 
			"from students s ,memberships m\r\n" + 
			"where s.membership_id=m.membership_id\r\n" + 
			"GROUP BY m.degree",nativeQuery=true)
	@Transactional
	public List<Map<String, Object>> selectCountdegree();
	/**
	 * 查询每个专业的人数 、系别、专业名称
	 * @return
	 */
	@Query(value="SELECT COUNT(m.department) 'value',m.department 'name' \r\n" + 
			"from students s ,memberships m\r\n" + 
			"where s.membership_id=m.membership_id\r\n" + 
			"GROUP BY m.department",nativeQuery=true)
	@Transactional
	public List<Map<String, Object>> selectCountdeptment();
	
	
}
