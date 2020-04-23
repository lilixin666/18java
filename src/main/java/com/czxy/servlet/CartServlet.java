package com.czxy.servlet;

import cn.itcast.servlet.BaseServlet;
import com.czxy.domain.Cart;
import com.czxy.domain.Product;
import com.czxy.service.ProductService;

import javax.servlet.annotation.WebServlet;

@WebServlet("/cartServlet")
public class CartServlet extends BaseServlet {

	// 根据 pid  删除购物车项
	public String removeCartItemByPid(){
		//1.接受浏览器传递的 pid
		String pid = this.getRequest().getParameter("pid");

		//2.获取购物车 执行 移除
		Cart cart = (Cart) this.getSession().getAttribute("cart");
		cart.removeCartItem(pid);
		this.getSession().setAttribute("cart", cart);

		//3.重定向到 购物车页面
		return "redirect:/jsp/cart.jsp";
	}


	public String clearCart(){
		//1.把session中的 cart 移除
		Cart cart = (Cart) this.getSession().getAttribute("cart");
		cart.clear();
		this.getSession().removeAttribute("cart");
		//2.把页面重定向到 购物车页面
		return "redirect:/jsp/cart.jsp";

	}



	//添加购物车
	public String  addCart(){

		//1.获取 参数 pid  count
		String pid = this.getRequest().getParameter("pid");
		String sCount = this.getRequest().getParameter("count");
		int count = 0;
		if(sCount != null){
			 count = Integer.parseInt(sCount);
		}

		//2.更新购物车
		// 根据商品的 pid 去数据库中获取 product对象
		ProductService productService = new ProductService();
		Product product = productService.findProductByPid(pid);

		// 从session中获取购物车
		Cart cart = (Cart) this.getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
		}
		// 调用购物车的 add方法 添加商品
		cart.addCart(product, count);

		// 把购物车放回session
		this.getSession().setAttribute("cart", cart);

		//3.重定向到 cart.jsp  购物车页面
		return "redirect:/jsp/cart.jsp";

	}

}
