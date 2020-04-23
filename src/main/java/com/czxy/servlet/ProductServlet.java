package com.czxy.servlet;

import cn.itcast.servlet.BaseServlet;
import com.czxy.domain.Product;
import com.czxy.service.ProductService;
import com.github.pagehelper.PageInfo;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/productServlet")
public class ProductServlet extends BaseServlet {


	// 根据 商品的 主键 pid 查询商品详情
	public String findProductByPid(){
		//1.获取浏览器传递的 pid
		String pid = this.getRequest().getParameter("pid");
		//2.调用service方法 得到结果
		ProductService productService = new ProductService();
		Product product = productService.findProductByPid(pid);

		//3.把product存储到request域中 请求 转发到 productInfo.jsp 页面
		this.getRequest().setAttribute("product", product);

		return "/jsp/product_info.jsp";

	}





	// 根据分类 cid 查询相关商品的信息
	public String findProductsByCid(){

		//1.获取浏览器传递的 cid
		String cid = this.getRequest().getParameter("cid");

		// 获取 页码
		String pageNumber = this.getRequest().getParameter("pageNumber");


		//2.调用service方法  得到商品信息 pageInfo
		ProductService productService = new ProductService();
		PageInfo<Product> pageInfo = productService.findPageInfoByCid(cid,pageNumber);

		//3.把pageInfo 存储到 request域中
		this.getRequest().setAttribute("pageInfo", pageInfo);
		this.getRequest().setAttribute("cid", cid);
		//4.把请求转发给 product_list.jsp
		return "/jsp/product_list.jsp";
	}






	// 返回热门商品和最新商品
	public String findHotAndNewProducts(){

		//1.调用 service方法 得到热门商品和最新商品集合
		ProductService productService = new ProductService();

		List<Product> hotProducts = productService.findHotProducts();
		List<Product> newProduts = productService.findNewProduts();

		//2.把俩集合存储到 request域中
		this.getRequest().setAttribute("hotProducts", hotProducts);
		this.getRequest().setAttribute("newProduts", newProduts);


		//3.把请求转发给 index.jsp
		return "/jsp/index.jsp";

	}


}
