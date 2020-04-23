package com.czxy.servlet;

import cn.itcast.servlet.BaseServlet;
import com.czxy.domain.Category;
import com.czxy.service.CategoryService;
import net.sf.json.JSONArray;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/categoryServlet")
public class CategoryServlet extends BaseServlet {

	// 查询所有分类
	public void findAllCategoty() throws IOException {
		//1.调用Service方法 获取分类集合
		CategoryService categoryService = new CategoryService();
		List<Category> all = categoryService.findAll();
		//2.转化成json 响应
		JSONArray jsonArray = JSONArray.fromObject(all);

		String str = jsonArray.toString();

		this.getResponse().getWriter().write(str);

	}

}
