package com.shop.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dao.ProductMapper;
import com.shop.entity.Product;
import com.shop.service.ProductService;
import com.shop.util.PageBean;

@Service("productService")
public class ProductServiceImp implements ProductService {
	@Autowired
	private ProductMapper productMapper;

	@Override
	public List<Product> findProductByCategorySecond(Integer csid) {
		// TODO Auto-generated method stub
		return productMapper.findProductByCategorySecond(csid);
	}

	@Override
	public List<Product> findAllHotProduct() {
		// TODO Auto-generated method stub
		return productMapper.findProductsByHot();
	}

	@Override
	public List<Product> findAllNewProduct() {
		// TODO Auto-generated method stub
		return productMapper.findProductByNew();
	}

	@Override
	public Product findProductById(Integer pid) {
		// TODO Auto-generated method stub
		return productMapper.findProductById(pid);
	}

	@Override
	public Integer findCountsByCsId(Integer csid) {
		// TODO Auto-generated method stub
		return productMapper.findCountsByCsId(csid);
	}

	@Override
	public PageBean<Product> findByCsid(Integer csid, Integer page) {
		// TODO Auto-generated method stub
		int limit = 8; // 每页显示记录数.
		int totalPage = 0; // 总页数
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		// 总记录数
		Integer totalCount = productMapper.findCountsByCsId(csid);
		pageBean.setTotalCount(totalCount);
		// 计算总页数:
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 数据的集合:
		int begin = (page - 1) * limit;
		List<Product> list = productMapper.findProductByCsidAndPage(csid,
				begin, limit);
		pageBean.setList(list);
		return pageBean;

	}

	@Override
	public PageBean<Product> findByPage(Integer cid, Integer page) {
		// TODO Auto-generated method stub
		int limit = 12; // 每页显示记录数.
		int totalPage = 0; // 总页数
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		// 查询总记录数:
		Integer totalCount = productMapper.findCountByCid(cid);
		
		pageBean.setTotalCount(totalCount);
		// 总页数的封装
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
			
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 商品数据集合:
		int begin = (page - 1) * limit;
		List<Product> list = productMapper.findByPage(cid,begin ,limit);
		pageBean.setList(list);
		return pageBean;
	
	}
}
