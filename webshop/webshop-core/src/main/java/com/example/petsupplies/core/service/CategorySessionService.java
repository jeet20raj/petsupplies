package com.example.petsupplies.core.service;

import java.util.List;

import com.example.petsupplies.core.backend.entity.CategoryEntity;
import com.example.petsupplies.core.exceptions.WebshopException;
/**
 * @author Jeetendra
 * CategorySessionService is used expose methods to search/add/edit/delete categories.
 */
public interface CategorySessionService {
	
  public List<CategoryEntity> getCategories();

  public boolean createCategory(CategoryEntity categoryEntity);
  
  public boolean editCategory(CategoryEntity categoryEntity);
  
  public boolean deleteCategory(CategoryEntity categoryId);
}
