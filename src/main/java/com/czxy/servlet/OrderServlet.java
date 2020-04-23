package com.czxy.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import com.czxy.domain.*;
import com.czxy.service.OrderService;
import com.github.pagehelper.PageInfo;

import javax.servlet.annotation.WebServlet;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet("/orderServlet")
public class OrderServlet extends BaseServlet {

		//未支付 --> 订单详情
	public String findOrderByOid(){

		//1.获取浏览器传递的 oid
		String oid = this.getRequest().getParameter("oid");
		//2.调用Service方法 得到 Oder对象
		OrderService orderService = new OrderService();
		Order order = orderService.findByOid(oid);
		//3.把order对象 存储到 request域中,把请求发给   订单详情页面
		this.getRequest().setAttribute("order", order);
		return "/jsp/order_info.jsp";
	}


	// 我的订单 带分页查询的
	public String findAllOrdersByPageNumber(){
		//1.从session中获取 用户的 uid
		User loginU = (User) this.getSession().getAttribute("loginU");

		if(loginU == null){
			return "/jsp/login.jsp";
		}

		// 获取页码
		String pageNumber = this.getRequest().getParameter("pageNumber");

		//2.调用service查询 得到 pageInfo
		OrderService orderService = new OrderService();
		PageInfo<Order> pageInfo = orderService.findPageInfoByUid(loginU.getUid(),pageNumber);

		//3.把pageInfo存储到 request域中 转发给 order_list.jsp
		this.getRequest().setAttribute("pageInfo", pageInfo);
		return "/jsp/order_list.jsp";

	}

	// 我的订单 根据 用户的 uid 获取 order相关 pageInfo
	public String findAllOrders(){
		//1.从session中获取 用户的 uid
		User loginU = (User) this.getSession().getAttribute("loginU");

		if(loginU == null){
			return "/jsp/login.jsp";
		}

		//2.调用service查询 得到 pageInfo
		OrderService orderService = new OrderService();
		PageInfo<Order> pageInfo = orderService.findPageInfoByUid(loginU.getUid());

		//3.把pageInfo存储到 request域中 转发给 order_list.jsp
		this.getRequest().setAttribute("pageInfo", pageInfo);
		return "/jsp/order_list.jsp";
	}


	// 生成订单

	/**
	 *     	//1.从session域中 获取 购物车对象   cart
	 *
	 *
	 * 		//2.创建 一个order对象 把 cart中的数据 存储到 order
	 *
	 * 		//3.调用service方法  把 order对象 存进 数据库
	 *
	 * 		//4.把order存到request域中 请求转发给 订单详情页面
	 *
	 *
	 * 	   防止订单重复提交:
	 * 	      1. 订单存储完毕之后  清除掉购物车.
	 * 	      2.在获取完购物车之后 加健壮性判断,如果cart == null 直接跳转到购物车页面 进行购物车为空提示 .
	 *
	 * @return
	 */
	public String createOrder(){

		//1.从session域中 获取 购物车对象   cart
		Cart cart = (Cart) this.getSession().getAttribute("cart");

		if(cart == null){
			//当购物车是空的时候 跳转到购物车页面   防止订单重复提交时 空指针异常
			return "/jsp/cart.jsp";
		}


		User loginU = (User) this.getSession().getAttribute("loginU");

		//2.创建 一个order对象 把 cart中的数据 存储到 order

		Order o = new Order();

		//基础数据设置
		o.setOid(CommonUtils.uuid());
		o.setOrdertime(new Date());
		o.setTotal_price(cart.getTotalMoney());
		o.setState(1);//未付款
		o.setUid(loginU.getUid());//

		//把购物车项的数据 填装到 订单项集合中
		Map<String, CartItem> map = cart.getMap();
		List<OrderItem> orderItemList = o.getOrderItemList();

		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			//购物车项
			CartItem cartItem = map.get(key);
			//订单项
			OrderItem oi = new OrderItem();
			//数据填装
			oi.setItemid(CommonUtils.uuid());
			oi.setQuantity(cartItem.getCount());
			oi.setSub_price(cartItem.getSubTotal());
			oi.setOid(o.getOid());
			oi.setPid(cartItem.getProduct().getPid());
			oi.setProduct(cartItem.getProduct());

			orderItemList.add(oi);

		}



		//3.调用service方法  把 order对象 存进 数据库
		OrderService orderService = new OrderService();
		orderService.saveOrder(o);

		//订单数据准备好了 清除购物车  <- 目的是防止订单重复提交
		this.getSession().removeAttribute("cart");


		//4.把order存到request域中 请求转发给 订单详情页面
		this.getRequest().setAttribute("order", o);
		return "/jsp/order_info.jsp";
	}
}
