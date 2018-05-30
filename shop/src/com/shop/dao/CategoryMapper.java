package com.shop.dao;

import java.util.List;

import com.shop.entity.Category;
import com.shop.entity.CategorySecond;
import com.shop.entity.Product;

public interface CategoryMapper {
    
	
	List<Category>  findAllCategory();
	
	List<CategorySecond> findAllCategorySeconds(Integer cid);
	
	Category findCategoryById(Integer cid);
	
	
	
	
}
