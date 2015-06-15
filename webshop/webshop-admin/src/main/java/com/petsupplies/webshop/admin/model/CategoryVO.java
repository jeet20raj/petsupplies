package com.petsupplies.webshop.admin.model;

import java.io.Serializable;

import javax.inject.Inject;

import org.primefaces.model.SelectableDataModel;

import com.example.petsupplies.core.backend.entity.CategoryEntity;
import com.petsupplies.webshop.admin.CategoryHolder;

public class CategoryVO implements Serializable, SelectableDataModel<CategoryVO>{
  
  private CategoryEntity  categoryEntity ;
  
  public CategoryVO() {
    // TODO Auto-generated constructor stub
  }
  
  public CategoryVO(CategoryEntity categoryEntity) {
    this.categoryEntity = categoryEntity;
  }

  @Inject
  private CategoryHolder categoryHolder;

public CategoryHolder getCategoryHolder() {
	return categoryHolder;
}

public void setCategoryHolder(CategoryHolder categoryHolder) {
	this.categoryHolder = categoryHolder;
}

public CategoryEntity getCategoryEntity() {
	return categoryEntity;
}

  

  public Object getRowKey(CategoryVO categoryVO) {
    return categoryVO.getCategoryEntity().getCategoryName();
  }

  public CategoryVO getRowData(String string) {
    for (CategoryVO categoryVO : categoryHolder.getCategoryVOs()) {
      if (categoryVO.getCategoryEntity().getCategoryName().equals(this.getCategoryEntity().getCategoryName())) {
        return categoryVO;
      }
    }
    return null;
  }
}
