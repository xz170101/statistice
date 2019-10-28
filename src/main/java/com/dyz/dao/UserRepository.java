package com.dyz.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dyz.entity.Modules;
import com.dyz.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	/**
	 * 根据用户名查id
	 */
	@Query(value="select user_id from user where user_number=:#{#user.user_number}",
			nativeQuery=true)
	Integer findUserByName(User user);	
	/**
	 * 根据用户名和密码查询用户
	 * @param user
	 * @return
	 */
	@Query(value="select * from user where user_number=:#{#user.user_number} and user_password=:#{#user.user_password} ",
				nativeQuery=true)
	User findUser(User user);
	
	
 
}
