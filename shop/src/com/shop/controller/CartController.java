package com.shop.controller;

import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shop.entity.Cart;
import com.shop.entity.CartItem;
import com.shop.entity.Product;
import com.shop.service.ProductService;
@Controller
public class CartController {
     @Autowired
     private ProductService productService;
     
     
     public Cart getCart(HttpSession session){
    	 
    	 Cart cart=(Cart) session.getAttribute("cart");
    	 if(cart==null){
    		 cart=new Cart();
    		 session.setAttribute("cart", cart);
    	 }
    	 
    	 return cart;
     }
     
     
     @RequestMapping(value="/addCart",method=RequestMethod.POST)
     public String addCart(HttpSession session,@RequestParam("pid")Integer pid,@RequestParam("count")Integer count){
    	 Cart cart=getCart(session);
    	 Product product=productService.findProductById(pid);
    	 CartItem item=new CartItem();
    	 item.setProduct(product);
    	 item.setCount(count);
    	 cart.addCart(item);
    	 return "cart";
     }
     
     @RequestMapping(value="/addCart",method=RequestMethod.GET)    
     public String addCart(){
    	 return "cart";
     }

     
     @RequestMapping("clearCart")
     public String clearCart(HttpSession session){
    	 Cart cart=getCart(session);
    	 cart.clearCart();
    	 return "cart";
     }
     @RequestMapping("/removeCart")
     public String removeCart(HttpSession session,Integer pid){
     	//获取购物车
     	Cart cart=getCart(session);
    
     	if(pid!=null){
     		cart.removeCart(pid);
     	}
     	return "cart";
    
     }
     
}
