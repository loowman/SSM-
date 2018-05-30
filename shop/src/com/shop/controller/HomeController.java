package com.shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.entity.Category;
import com.shop.entity.Product;
import com.shop.service.CategoryService;
import com.shop.service.ProductService;

@Controller
public class HomeController {
	@Autowired 
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	//热门商品的集合
	private List<Product> hotList;
	//最新商品集合
	private List<Product> newList;
	
	@RequestMapping("/index")
	public String index(HttpSession session,Model model) throws Exception{
		//1.查询所有一级分类
		List<Category> categoryList = categoryService.findAllCategory();
//		System.out.println(categoryList.toString());
		//2.将查询出来的所有存入session
		session.setAttribute("categoryList", categoryList);
		
		//查询热门商品
		hotList=productService.findAllHotProduct();
		model.addAttribute("hotList", hotList);
		//查询最新商品
		newList=productService.findAllNewProduct();
		model.addAttribute("newList", newList);
		return "index";
	}
	
	 @RequestMapping("loginPage")
	 public String login(){
		 return "login";
	 }
	 
	 @RequestMapping("userRegist")
	 public String register(){
		 return "regist";
	 }
	
}
