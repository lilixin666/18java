package com.czxy.service;

import com.czxy.dao.CategoryMapper;
import com.czxy.domain.Category;
import com.czxy.utils.MyBatisUtils;

import java.util.List;

public class CategoryService {



	// 查询所有分类
	public List<Category> findAll(){
		//1.获取mapper
		CategoryMapper mapper = MyBatisUtils.getMapper(CategoryMapper.class);
		//2.执行查询
		List<Category> list = mapper.selectAll();
		//3.释放资源
		MyBatisUtils.close();
		//4.返回结果
		return  list;

	}

}
