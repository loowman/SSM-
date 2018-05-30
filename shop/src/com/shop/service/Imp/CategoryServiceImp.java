package com.shop.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dao.CategoryMapper;
import com.shop.entity.Category;
import com.shop.entity.CategorySecond;
import com.shop.service.CategoryService;
@Service("categoryService")
public class CategoryServiceImp implements CategoryService {
     @Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public List<Category> findAllCategory() {
		
		return  categoryMapper.findAllCategory();
	}

	@Override
	public List<CategorySecond> findAllCategorySeconds(Integer cid) {
		
		return categoryMapper.findAllCategorySeconds(cid);
	}

	@Override
	public Category findCategoryById(Integer cid) {
		
		return categoryMapper.findCategoryById(cid);
	}

}
