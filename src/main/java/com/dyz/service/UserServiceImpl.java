package com.dyz.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dyz.dao.ModulesRepository;
import com.dyz.dao.UserRepository;
import com.dyz.entity.Modules;
import com.dyz.entity.User;
import com.dyz.util.Msg;
import com.dyz.util.TokenUtile;
import com.dyz.util.TreeModel;
import com.dyz.util.TreeNode;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModulesRepository modulesRepository;

 
	@Override
	public Msg findLoginUser(User user) {
		// TODO Auto-generated method stub
		Msg msg=new Msg();
		//user.setUser_number(user.getUser_number());
 		Integer u = userRepository.findUserByName(user);
		 
		if(u > 0) {
  			User uu=userRepository.findUser(user);
 			if(uu !=null){
				String createToken = TokenUtile.createToken(
							uu.getUser_number(),
							String.valueOf(uu.getUser_id()),
							new Date()
							);
				msg.setToken(createToken);
				msg.setSuccess(true);
 				return msg;
			}
			msg.setMessage("密码不正确");
			msg.setSuccess(false);
			return msg;
		}else {
			msg.setMessage("管理编号不存在");
			msg.setSuccess(false);
			return msg;
		}
	}

	/**
	 * 查询树菜单
	 */
	@Override
	public ArrayList<TreeModel> selectUsersByTree() {
		 ArrayList<Modules> list = (ArrayList<Modules>) modulesRepository.findAll();
		 System.out.println("list:"+list);
		 ArrayList<TreeModel> tree = new ArrayList<>();
         for (Modules module : list) {
             TreeModel node = new TreeModel();
             node.setId(module.getModules_Id());
             node.setText(module.getModules_Name());
             node.setModules_path(module.getModules_path());
             node.setParent_id(module.getParentId());
             tree.add(node);
         }//简单的来说，就是把数据库里所有数据查出来之后，然后一条一条的封装，扔进TreeModel里，作为一个个节点，然后放在ArrayList里
         return TreeNode.getTree(tree);
	}
	
 

}
