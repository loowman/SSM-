package com.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.User;
import com.shop.service.UserService;
@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(@RequestParam("username")String username,@RequestParam("password")String password,@RequestParam("checkCode") String checkCode,HttpServletRequest request,HttpServletResponse response){
//		System.out.println(11);
		HttpSession session=request.getSession(false);
		String Code=(String) session.getAttribute("checkCode");
		if("".equals(checkCode)){
			request.setAttribute("msg", "验证码不能为空");
			return "login";
		}else{
			if(!checkCode.equals(Code)){
				request.setAttribute("msg", "验证码不正确");
				return "login";
			}
		}
		
		if(username!=null&&password!=null){
			User user=userService.chectLoginUser(username, password);
			if(user==null){
				request.setAttribute("msg", "用户名或密码错误");
				return "login";
			}else{
//				System.out.println(user.toString());
				session.setAttribute("user", user);
				return "redirect:index.jsp";
			}
		}
		return "login";
	
	}
	
	@RequestMapping(value="logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:index.jsp";
	}
}
