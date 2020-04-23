package com.czxy.service;

import cn.itcast.commons.CommonUtils;
import com.czxy.dao.UserMapper;
import com.czxy.domain.User;
import com.czxy.utils.MyBatisUtils;

import java.util.List;

public class UserService {

	// 根据 uid 更新密码
	public void updatePwdByUid(String uid,String pwd){
		//1.获取mapper
		UserMapper mapper = MyBatisUtils.getMapper(UserMapper.class);
		//2.执行查询
		mapper.updatePwd(pwd, uid);
		//3.释放资源
		MyBatisUtils.commitAndClose();
	}




	/**
	 * 登录用的, 接受浏览器传递 用户信息
	 * 登录成功则返回 数据库中查询的 user信息 登录失败返回 null
	 * @param user
	 * @return
	 */
	public User login(User user){

		//1.获取mapper
		UserMapper mapper = MyBatisUtils.getMapper(UserMapper.class);
		//2.执行查询
		List<User> list = mapper.findUsersByUser(user);
		//3.释放资源
		MyBatisUtils.close();
		//4. 如果 查询有结果 代表登录成功,  把 查到的用户信息返回
		// 如果 查询结果为空 代表登录失败, 把null 返回

		if(list.isEmpty()){
			return  null;
		}else{
			return  list.get(0);
		}

	}



	// 校验用户名是否可用

	public boolean checkUserName(String username){

		//1.获取mapper
		UserMapper mapper = MyBatisUtils.getMapper(UserMapper.class);
		//2.执行查询
		List<User> list = mapper.findUsersByUserName(username);

		//3.释放资源
		MyBatisUtils.close();

		//4.
		// 如果查询结果 无数据 则返回 true 代表用户名 可用
		// 如果 查询结果 有数据 则返回 false 代表用户名不可用
		//

		if(list.isEmpty()){
			return true;
		}else{
			return  false;
		}


	}


	// 注册方法
	public void register(User user){
		//1.获取mapper
		UserMapper mapper = MyBatisUtils.getMapper(UserMapper.class);
		//2.执行查询
		// 添加id
		user.setUid(CommonUtils.uuid());

		mapper.insert(user);
		//3.释放资源
		MyBatisUtils.commitAndClose();
	}
}
