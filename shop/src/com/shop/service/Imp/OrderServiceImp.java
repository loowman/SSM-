package com.shop.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.OrderMapper;
import com.shop.entity.CartItem;
import com.shop.entity.Order;
import com.shop.entity.OrderItem;
import com.shop.service.OrderService;
@Service
public class OrderServiceImp implements OrderService{

	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,rollbackFor=RuntimeException.class)
	public Integer save(Order order) {
		// TODO Auto-generated method stub
	     Integer flag=orderMapper.save(order);
	     if(flag<=0){
	    	 throw new RuntimeException();
	     }
	     return flag;
	    
	}

	

	@Override
	public Integer saveOrderItem(OrderItem item) {
		// TODO Auto-generated method stub
		return orderMapper.saveOrderItem(item);
	}



	@Override
	public List<OrderItem> findOrderItemByOid(Integer oids) {
		// TODO Auto-generated method stub
		return orderMapper.findOrderItemByOid(oids);
	}



	@Override
	public void updateOrder(Order currOrder) {
		// TODO Auto-generated method stub
		  orderMapper.updateOrder(currOrder);
	}



	@Override
	public Order findOrderByoid(Integer oids) {
		// TODO Auto-generated method stub
		return orderMapper.findOrderByoid(oids);
	}



	@Override
	public List<Order> findOrderByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return orderMapper.findOrderByUserId(userId);
	}

}
