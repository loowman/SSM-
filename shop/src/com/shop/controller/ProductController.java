package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shop.entity.Category;
import com.shop.entity.Product;
import com.shop.service.CategoryService;
import com.shop.service.ProductService;
import com.shop.util.PageBean;

@Controller
public class ProductController {

	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	PageBean<Product> pageBean=new PageBean<Product>();
	
	
	
	@RequestMapping("/productfindByPid")
	public ModelAndView productfindByPid(@RequestParam("pid")Integer pid){
		ModelAndView mv=new ModelAndView("desc");
		List<Category> lists=categoryService.findAllCategory();
		mv.addObject("categoryList", lists);
		Product product=productService.findProductById(pid);
		mv.addObject("product", product);
		
		
		
		return mv;
		
	}
	
	
	@RequestMapping("/productfindByCsid")
	public ModelAndView productfindByCsid(ModelAndView mv,@RequestParam("csid")Integer csid,@RequestParam("page")Integer page){
		List<Category> categoryList=categoryService.findAllCategory();
		mv.addObject("categoryList", categoryList);
		pageBean = productService.findByCsid(csid, page);
		 mv.addObject("pageBean", pageBean);
		mv.addObject("csid", csid);
		mv.setViewName("clist");
		
		return mv;
	}
	@RequestMapping("/productfindByCid")
	public ModelAndView productfindByCid(ModelAndView mv,@RequestParam("cid")Integer cid,@RequestParam("page")Integer page){
		List<Category> categoryList = categoryService.findAllCategory();
		mv.addObject("categoryList", categoryList);
		mv.addObject("cid", cid);
		//查询商品
		pageBean=productService.findByPage(cid,page);
		mv.addObject("pageBean", pageBean);
		mv.setViewName("list");
		return mv;
	}
}
