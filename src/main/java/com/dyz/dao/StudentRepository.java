package com.dyz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.dyz.entity.Search;
import com.dyz.entity.Student;
import com.dyz.entity.Teacher;


public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	@Query(value="select s.* from students s INNER join memberships m on"
			+ " s.membership_id=m.membership_id limit :#{#search.page},:#{#search.pageSize}", nativeQuery=true)	
	@Transactional
	public List<Student> selectStudent(Search search);
	
	@Query(value="select count(1) from students", nativeQuery=true)
	@Transactional
	public Integer selectCount(Search search);
	
	@Query(value="update students set scardno=:#{#stu.SCardNO},sstatus=:#{#stu.Sstatus},"
			+ "stuno=:#{#stu.stuNO},student_name=:#{#stu.studentName},"
			+ "student_remark=:#{#stu.studentRemark},student_sex=:#{#stu.studentSex},"
			+ "membership_id=:#{#stu.meber.sectionId} "
			+ "where student_id= :#{#tea.teacherId}",nativeQuery=true)
	@Modifying
	@Transactional
	public int updateStudent(Student stu);
	
	@Query(value = "INSERT INTO students(scardno,sstatus,stuno,"
			+ "student_name,student_remark,student_sex,membership_id) "
			+ "VALUES (:#{#stu.SCardNO}, :#{#stu.Sstatus}, "
			+ ":#{#stu.stuNO}, :#{#stu.studentName}, :#{#stu.studentRemark},"
			+ ":#{#stu.studentSex},:#{#stu.meber.MembershipId})",nativeQuery=true)
	@Modifying
	@Transactional
	public int addStudent(Student stu);
	/**
	 * 查询学生卡号
	 * @param code
	 * @return
	 */
	public Student findByscardNO(String code);
	/**
	 * 
	 */
	public Student save(Student s);
}
