package com.dyz.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dyz.entity.Search;
import com.dyz.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>,JpaSpecificationExecutor<Teacher>{

	@Query(value="select t.* from teachers t INNER join sections s on t.section_id=s.section_id limit :#{#search.page},:#{#search.pageSize}"
			, nativeQuery=true)	
	@Transactional
	public List<Teacher> selectTeacher(Search search);
	//多条件的分页查询
	public List<Teacher> findByTeacherSex(Integer teacherSex);
	public List<Teacher> findByTeacherName(String teacherName);
	public List<Teacher> findByTeacherSexAndTeacherName(Integer teacherSex,String teacherName);
	
	@Query(value="select count(1) from teachers", nativeQuery=true)
	@Transactional
	public Integer selectCount(Search search);
	@Query(value="select count(1) from teachers where teacher_sex=?1", nativeQuery=true)
	@Transactional
	public Integer selectByTeacherSexCount(Integer teacherSex);
	@Query(value="select count(1) from teachers where teacher_name like ?1", nativeQuery=true)
	@Transactional
	public Integer selectByTeacherNameCount(String teacherName);
	@Query(value="select count(1) from teachers where teacher_name=?2 and teacher_sex=?1 ", nativeQuery=true)
	@Transactional
	public Integer selectByTSexAndTNameCount(Integer teacherSex,String teacherName);
		/**
	 * 修改教师
	 * @param tea
	 * @return
	 */
	@Query(value="update teachers set tcardno=:#{#tea.tcardno},teacher_name=:#{#tea.teacherName},"
			+ "teacher_sex=:#{#tea.teacherSex},tstatus=:#{#tea.tstatus},"
			+ "remark=:#{#tea.remark},section_id=:#{#tea.section.sectionId} where teacher_id= :#{#tea.teacherId}",nativeQuery=true)
	@Modifying
	@Transactional
	public int updateTeacher(Teacher tea);
	/**
	 * 添加老师
	 * @param tea
	 * @return
	 * 
	 */	
	@Query(value = "INSERT INTO teachers( tcardno,teacher_Name,teacher_Sex,tstatus,remark,section_id) "
			+ "VALUES (:#{#teacher.tcardno}, :#{#teacher.teacherName}, :#{#teacher.teacherSex}, :#{#teacher.tstatus}, :#{#teacher.remark},:#{#teacher.section.sectionId})",nativeQuery = true)
	@Modifying
	@Transactional
	public int addTeacher(@Param("teacher") Teacher teacher);

	/**
	 * 查询卡号
	 * @param code
	 * @return
	 */
	public Teacher findBytcardno(String code);
	
	public Teacher save(Teacher t);
 
	
}
