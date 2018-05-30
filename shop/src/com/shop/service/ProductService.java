package com.shop.service;

import java.util.List;

import com.shop.entity.Product;
import com.shop.util.PageBean;

public interface ProductService {
	List<Product>  findProductByCategorySecond(Integer csid);
	List<Product> findAllHotProduct();
	List<Product> findAllNewProduct();
	Product findProductById(Integer pid);
	Integer  findCountsByCsId(Integer csid);
	
    PageBean<Product> findByCsid(Integer csid, Integer page);
    PageBean<Product> findByPage(Integer cid, Integer page);
}
