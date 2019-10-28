package com.dyz.service;

import java.util.ArrayList;

import com.dyz.entity.User;
import com.dyz.util.Msg;
import com.dyz.util.TreeModel;

public interface UserService { 
	
	Msg findLoginUser(User user);
	/**
	 * 查询树
	 * @return
	 */
	ArrayList<TreeModel> selectUsersByTree();

}
