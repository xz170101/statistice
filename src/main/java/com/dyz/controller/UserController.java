package com.dyz.controller;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dyz.entity.User;
import com.dyz.service.UserService;
import com.dyz.util.Msg;
import com.dyz.util.TreeModel;
/**
 * 
 * @author 皮特朱
 */
 
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value ="/login", method = RequestMethod.POST)
	@ResponseBody
	public Msg login(User user) {
		System.out.println(user.getUser_number());
		
		return userService.findLoginUser(user);
		}
	
	
	@RequestMapping(value="/getSysRightsHtmlTree", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<TreeModel> getModules(String token) {
 		return userService.selectUsersByTree();
	} 
 
}
