package com.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shop.entity.CartItem;
import com.shop.entity.Order;
import com.shop.entity.OrderItem;

public interface OrderMapper {
    Integer save(Order order);
    Integer saveOrderItem(OrderItem items);
	List<OrderItem> findOrderItemByOid(@Param("oids")Integer oids);
	void updateOrder(Order currOrder);
	Order findOrderByoid(@Param("oids")Integer oids);
	List<Order> findOrderByUserId(@Param("userId")Integer userId);
}
