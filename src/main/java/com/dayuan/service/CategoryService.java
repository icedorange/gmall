package com.dayuan.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dayuan.bean.Category;
import com.dayuan.dao.CategoryMapper;
import com.dayuan.vo.CategoryVo;

@Service
public class CategoryService {

	@Resource
	private CategoryMapper categoryMapper;

	public List<Category> selectCategory(Integer pid) {
		return categoryMapper.selectCategoryByPid(pid);
	}

	public List<CategoryVo> selectCategoryVo(Integer long1) {
		List<Category> category = selectCategory(long1);
		List<CategoryVo> categoryVoList = new ArrayList<>();
		for (Category category_rank : category) {
			CategoryVo categoryVo = new CategoryVo(category_rank.getId(), category_rank.getName(),
					category_rank.getPid(), selectCategoryVo(category_rank.getId()));
			categoryVoList.add(categoryVo);
	
		}
		return categoryVoList;
	}
}
