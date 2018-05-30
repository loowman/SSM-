package com.shop.service;

import java.util.List;




import com.shop.entity.CartItem;
import com.shop.entity.Order;
import com.shop.entity.OrderItem;

public interface OrderService {
  
	Integer save(Order order);
	 Integer saveOrderItem(OrderItem item);
		List<OrderItem> findOrderItemByOid(Integer oids);
		void updateOrder(Order currOrder);
		Order findOrderByoid(Integer oids);
		List<Order> findOrderByUserId(Integer userId);
}
