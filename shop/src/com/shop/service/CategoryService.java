package com.shop.service;

import java.util.List;

import com.shop.entity.Category;
import com.shop.entity.CategorySecond;

public interface CategoryService {
    List<Category>  findAllCategory();
	
	List<CategorySecond> findAllCategorySeconds(Integer cid);
	
	Category findCategoryById(Integer cid);
	
}
