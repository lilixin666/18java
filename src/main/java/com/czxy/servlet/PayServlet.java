package com.czxy.servlet;

import cn.itcast.servlet.BaseServlet;
import com.czxy.domain.Order;
import com.czxy.service.OrderService;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/payServlet")
public class PayServlet extends BaseServlet {


//	//查询支付是否成功
//	public void getPayStatus() throws IOException {
//		//1.获取浏览器传递的 oid
//		String oid = this.getRequest().getParameter("oid");
//		//2.调用支付工具类 获取 该oid的支付状态
//		String rel = PayUtils.getOrderPayStatus(oid);
//
//		//3.如果 返回的结果为 SUCCESS 代表支付成功 此时 给前端 响应一个 true , 否则响应false
//		//  在支付成功的情况下 要更新数据库该订单的支付状态
//		if("SUCCESS".equalsIgnoreCase(rel)){
//			//支付成功
//			//更新数据库的支付状态
//			OrderService orderService = new OrderService();
//			orderService.updateStateByOid(oid);
//
//			//响应支付成功
//			this.getResponse().getWriter().print(true);
//		}else{
//			//支付失败
//			this.getResponse().getWriter().print(false);
//		}
//
//	}
//
//
//	// 支付
//	public String pay(){
//		//1.获取参数
//		Order order = toBean(Order.class);
//		//2.更新收货人数据
//		OrderService os = new OrderService();
//		os.updateInfo(order);
//		//3.获取支付码字符串
//		String payStr = PayUtils.pay(order.getOid(), "127.0.0.1", "1", "订单号" + order.getOid(), "ssssss");
//		//4.把数据存到request域中 请求转发到支付页面
//		this.getRequest().setAttribute("order", order);
//		this.getRequest().setAttribute("payStr", payStr);
//		return "/jsp/confirm02.jsp";
//	}

}