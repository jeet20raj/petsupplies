package com.petsupplies.webshop.admin;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.petsupplies.webshop.admin.model.CategoryVO;

@Named("categoriesHolder")
@SessionScoped
public class CategoryHolder implements Serializable{

	List<CategoryVO> categoryVOs;
	
	public CategoryHolder() {
	}

	public List<CategoryVO> getCategoryVOs() {
		return categoryVOs;
	}

	public void setCategoryVOs(List<CategoryVO> categoryVOs) {
		this.categoryVOs = categoryVOs;
	}
}
