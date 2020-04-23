package com.czxy.servlet;

import cn.itcast.servlet.BaseServlet;
import cn.itcast.vcode.VerifyCode;
import com.czxy.domain.User;
import com.czxy.service.UserService;
import com.czxy.utils.RandomCode;
import com.czxy.utils.SMSUtils;


import javax.servlet.annotation.WebServlet;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet("/userServlet")
public class UserServlet extends BaseServlet {

  // 发送验证码
	public void sendPhoneCode(){

		//1.获取浏览器传递的 手机号
		String telephone = this.getRequest().getParameter("telephone");
		//2.生成验证码
		// 456790
		String pCode = RandomCode.getCode();
		//3.把验证码存储到 session中
		this.getSession().setAttribute("pCode", pCode);
		//4.发短信
		SMSUtils.sendSms(telephone, pCode);
	}

	// 校验验证码
	public void checkPhoneCode() throws IOException {
		//1.获取浏览器的
		String phoneCode = this.getRequest().getParameter("phoneCode");
		//2.从session获取
		String pCode = (String) this.getSession().getAttribute("pCode");
		//3.校验 返回结果
		this.getResponse().getWriter().print(pCode.equals(phoneCode));
	}

  // 修改密码
	public String changePwd(){

		//1.获取浏览器传递 新密码 newPwd
		String newPwd = this.getRequest().getParameter("newPwd");
		//2.获取当前用户的  uid
		User loginU = (User) this.getSession().getAttribute("loginU");

		if(loginU == null){
			return "/jsp/login.jsp";
		}

		//3.调用Service 修改密码
		UserService userService = new UserService();
		userService.updatePwdByUid(loginU.getUid(), newPwd);

		//4.移除session中的 loginU
		this.getSession().removeAttribute("loginU");

		//5.跳转到 登录页面
		return "redirect:/jsp/login.jsp";



	}






	public String logOut(){
		// 移除session域中的 loginU
		this.getSession().removeAttribute("loginU");
		return "redirect:/jsp/login.jsp";
	}


	public String login(){
		//1.获取浏览器传递的参数  封装成user对象
		User user = toBean(User.class);
//		//获取浏览器传递验证码
		String uyzm = this.getRequest().getParameter("uyzm");
//		//获取session传递的验证码
//		String vcCode = (String) this.getSession().getAttribute("vcCode");
//		//如果两个验证码不同 则直接返回 错误信息
//		if(uyzm == null || "".equals(uyzm) || !uyzm.equalsIgnoreCase(vcCode)){
//			this.getRequest().setAttribute("msg", "验证码 错误");
//			return "/jsp/login.jsp";
//		}


		//2.调用service方法 得到登录结果
		UserService userService  = new UserService();
		User loginU = userService.login(user);

		//3.根据结果做出不同的响应
		if(loginU != null){
			//登录成功了
			this.getSession().setAttribute("loginU", loginU);
			return "redirect:/productServlet?method=findHotAndNewProducts";
		}else{
			//登录失败
			this.getRequest().setAttribute("msg", "用户名或密码错误");
			return "/jsp/login.jsp";
		}


	}




	// 产生验证码
	public void getYzm() throws IOException {
		//1.产生验证码 图片
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();
		//2.把验证码 码值存储到 session中
		String vcText = vc.getText();
		this.getSession().setAttribute("vcCode", vcText);
		//3.把验证码图片 响应给前端
		VerifyCode.output(image, this.getResponse().getOutputStream());
	}

	//校验验证码

	public void checkYzm() throws IOException {
		//1.获取浏览器传递的参数 uyzm
		String uyzm = this.getRequest().getParameter("uyzm");
		//2.获取session域中的 码值  vcCode
		String vcCode = (String) this.getSession().getAttribute("vcCode");

		// 从session中 获取验证码之后 直接从session中移除该验证码防止 用户恶意访问
		this.getSession().removeAttribute("vcCode");

		//3.判断二者是否相同 响应结果
		if("".equals(uyzm) || uyzm == null){
			this.getResponse().getWriter().print(false);
			return;
		}

		if(uyzm.equalsIgnoreCase(vcCode)){
			this.getResponse().getWriter().print(true);
		}else{
			this.getResponse().getWriter().print(false);
		}

	}





	// 校验用户名
	public void checkUserName() throws IOException {
		//1.获取浏览器传递的参数  username
		String username = this.getRequest().getParameter("username");
		//2.调用service方法 校验用户名 得到结果
		UserService  userService = new UserService();
		boolean rel = userService.checkUserName(username);
		//3.根据结果 进行响应
		if(rel){
			// {"message":"用户名可用","flag":"true"}
			this.getResponse().getWriter().write("{\"message\":\"用户名可用\",\"flag\":\"true\"}");
		}else{
			this.getResponse().getWriter().write("{\"message\":\"用户名不可用\",\"flag\":\"false\"}");
		}


	}


	// 注册

	public String register(){

		//1.获取浏览器传递的参数
		User user = toBean(User.class);
		//2.调用service 执行注册
		UserService userService = new UserService();
		userService.register(user);
		//3.重定向到 登录页面
		return "redirect:/jsp/login.jsp";

	}

}
