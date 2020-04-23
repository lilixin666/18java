package com.czxy.dao;

import com.czxy.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {



	//根据用户名查询
	@Select("select * from user where username = #{username}")
	public List<User> findUsersByUserName(@Param("username") String username);


	// 查询条件: 用户名和密码  用于登录
	@Select(" select * from user where username=#{username} and password = #{password}")
	public List<User> findUsersByUser(User user);


	//根据uid 更新密码
	@Update("update user set password = #{pwd} where uid =#{uid}")
	public void updatePwd(@Param("pwd") String pwd, @Param("uid") String uid);

}
