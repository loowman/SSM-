package com.shop.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shop.entity.User;
import com.shop.service.UserService;
import com.shop.util.CheckCodeGenerator;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private CheckCodeGenerator code;
	
	@Autowired
	private UserService userService;
	
	@SuppressWarnings("unused")
	@RequestMapping(value="checkCode/{time}",method=RequestMethod.GET)
	public void getCheckImage(@PathParam("time") String time,HttpServletResponse response, HttpServletRequest request){
		BufferedImage bfImg=null;
		String checkCode=null;
		HashMap<String, Object> map=(HashMap<String, Object>) code.generlateCheckCode();
		
		if(map!=null){
			bfImg=(BufferedImage) map.get("checkCodeImage");
			checkCode=(String) map.get("checkCodeString");
		}
		
		if(bfImg!=null&&checkCode!=null){
			
			HttpSession session=request.getSession();
			session.setAttribute("checkCode",checkCode);
			try(ServletOutputStream out=response.getOutputStream()) {			
		   
		    ImageIO.write(bfImg, "jpeg", out);
		    response.setHeader("Pragma", "no-cache");     
	        response.setHeader("Cache-Control", "no-cache");     
	        response.setDateHeader("Expires", 0);     
	        response.setContentType("image/jpeg");
	        out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	@RequestMapping("/CheckUserName")
	@ResponseBody
	public Map<String, String> checkUserName(@RequestParam("username")String username,HttpServletRequest request){
        
		 
		Map<String, String> result=new HashMap<String, String>();
		if(username==null||"".equals(username)){
			result.put("msg", "null");
		}else{
			User user=userService.getUserByName(username);
			if(user!=null){
				result.put("msg", "false");
			}else{
				result.put("msg", "true");
			}
		}
		return result;
		
		
	}
	
	
	@RequestMapping(value="regist",method=RequestMethod.POST)
	public ModelAndView regist(User user,@RequestParam("checkCode")String checkCode,HttpSession session){
//		System.out.println(user.toString());
		String code=(String) session.getAttribute("checkCode");
		ModelAndView mv=new ModelAndView();
		if(checkCode!=null&&code.equalsIgnoreCase(checkCode)){
			int flag=userService.addUser(user);
			if(flag>=0){
				mv.setViewName("msg");
				mv.addObject("message", "注册成功，请重新登录");
			}else{
				mv.setViewName("regist");
				mv.addObject("err", "注册失败，抱歉");
			}
		}else{
			mv.setViewName("regist");
			mv.addObject("err", "验证码错误");
		}
		return mv;
	}
	
}

