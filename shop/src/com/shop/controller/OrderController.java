package com.shop.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shop.entity.Cart;
import com.shop.entity.CartItem;
import com.shop.entity.Order;
import com.shop.entity.OrderItem;
import com.shop.entity.User;
import com.shop.service.OrderService;
import com.shop.service.Imp.OrderServiceImp;

@Controller
public class OrderController {
     
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/saveOrder")
	public String saveOrder(Model model,HttpSession session){
		OrderItem item=new OrderItem();
		Order order=new Order();
		
		/* 设置基础属性 */
		order.setOrdertime(new Date());
		order.setState(1);
		
		Cart cart=(Cart) session.getAttribute("cart");
		if(cart==null){
			model.addAttribute("message", "您还未购物,请先购物!");
			return "msg";
		}
		
		order.setTotal(cart.getTotal());
		User user=(User) session.getAttribute("user");
	    order.setAddr(user.getPhone());
	    order.setName(user.getUsername());
	    order.setPhone(user.getPhone());
	    order.setUser(user);
	    Integer oid=orderService.save(order);
	    Integer oids=order.getOid();
	    for (CartItem cartItem : cart.getCartItems()) {
    		item.setCount(cartItem.getCount());
    		item.setSubtotal(cartItem.getSubtotal());
    		item.setProduct(cartItem.getProduct());
    		item.setOrder(order);
			//保存订单条目
			Integer itemId=orderService.saveOrderItem(item);
		}
	    
//	    List<OrderItem> orderItems=orderService.findOrderItemByOid(oid);
//		order.setOrderItems(orderItems);
//		System.out.println(order);
	    cart.clearCart();
	    List<OrderItem> orderItems=orderService.findOrderItemByOid(oids);
	    order.setOrderItems(orderItems);
	    model.addAttribute("order", order);
	    return "order";
		
		
		
		
	}
	  @RequestMapping("/orderPayOrder")
	    public String orderPayOrder(Integer orderId,String name,String orderPhone,String orderAddr,Model model){
			//修改订单
	    	//根据订单id查询订单
	    	Order currOrder=orderService.findOrderByoid(orderId);
	    	currOrder.setAddr(orderAddr);
	    	currOrder.setName(name);
	    	currOrder.setPhone(orderPhone);
	    	orderService.updateOrder(currOrder);
	    	// 付款:
			// 定义付款的参数:
	    	/**********此处不做付款功能,实际项目中可根据公司的支付技术选型接入相应支付接口即可***********/
	    	//付款成功后重定向到回调方法
	    	//假设已付款
	    	
	    	return "redirect:callBack";
	    }
	  @RequestMapping("/callBack")
	    public String callBack(String r3_Amt,String r6_Order,Model model){
	    	/***注意：r3_Amt和r6_Order在本项目中都为null,因为我们没有实现支付功能***/
	    	//r3_Amt代表支付成功后，支付平台传过来的支付金额，r6_Order代表传过来的订单id,
	    	//修改订单状态
	    	//Order currOrder = orderService.findOrderByoid(Integer.parseInt(r6_Order));
	    	//实际项目中订单编号发生修改,是因为没有查询出订单。
	    	//currOrder.setState(2);//2代表已付款
	    	//修改订单状态
	    	//orderService.updateOrder(currOrder);
	    	model.addAttribute("message", "订单付款成功!订单号:"+r6_Order+"支付金额:"+r3_Amt);
	    	//返回消息页面
			return "msg";
	    }
	  @RequestMapping("/myOrder")
	    public String myOrder(HttpSession session,Model model){
	    	User user=(User) session.getAttribute("user");
	    	if(user==null){
	    		model.addAttribute("message","您还没有登录，请先去登录");
	    		return "msg";
	    	}
	    	Integer userId=user.getUid();
	    	List<Order> orderList=orderService.findOrderByUserId(userId);
	    	System.out.println(orderList);
	    	model.addAttribute("orderList", orderList);
			return "orderlist";
	    }
}
