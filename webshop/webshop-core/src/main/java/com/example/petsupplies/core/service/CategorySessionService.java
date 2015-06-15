package com.example.petsupplies.core.service;

import java.util.List;

import com.example.petsupplies.core.backend.entity.CategoryEntity;
import com.example.petsupplies.core.exceptions.WebshopException;

public interface CategorySessionService {
	
  public List<CategoryEntity> getCategories();

  public boolean createCategory(CategoryEntity categoryEntity);
  
  public boolean editCategory(CategoryEntity categoryEntity);
  
  public boolean deleteCategory(CategoryEntity categoryId);
}
