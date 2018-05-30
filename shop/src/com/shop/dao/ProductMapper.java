package com.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shop.entity.Product;

public interface ProductMapper {
	List<Product>  findProductByCategorySecond(Integer csid);
	List<Product> findProductsByHot();
	List<Product> findProductByNew();
	
	Product findProductById(Integer pid);
	Integer  findCountsByCsId(Integer csid);
	List<Product> findProductByCsidAndPage(@Param("csid")Integer csid,@Param("begin")Integer begin,@Param("limit")Integer limit);
	List<Product> findByPage(@Param("cid")Integer cid, @Param("begin")int begin, @Param("limit")int limit);
	Integer findCountByCid(Integer cid);
}
