package com.petsupplies.webshop.admin.model;

import java.io.Serializable;

import javax.inject.Inject;

import org.primefaces.model.SelectableDataModel;

import com.example.petsupplies.core.backend.entity.ProductEntity;
import com.petsupplies.webshop.admin.ProductHolder;

public class ProductVO implements Serializable, SelectableDataModel<ProductVO>{
  
  private ProductEntity productEntity;
  
  public ProductVO() {
    // TODO Auto-generated constructor stub
  }
  
  public ProductVO(ProductEntity productEntity) {
    this.productEntity = productEntity;
  }

  @Inject
  private ProductHolder productHolder;

  public ProductEntity getProductEntity() {
    return productEntity;
  }

  public ProductHolder getProductHolder() {
    return productHolder;
  }

  public void setProductHolder(ProductHolder productHolder) {
    this.productHolder = productHolder;
  }

  public String getProductName() {
    return this.getProductEntity().getProductName();
  }

  public Object getRowKey(ProductVO productVO) {
    return productVO.getProductEntity().getProductName();
  }

  public ProductVO getRowData(String string) {
    for (ProductVO productVO : productHolder.getProducts()) {
      if (productVO.getProductEntity().getProductName().equals(this.getProductEntity().getProductName())) {
        return productVO;
      }
    }
    return null;
  }
}
