package com.example.petsupplies.core.service;

import java.util.List;

import com.example.petsupplies.core.backend.entity.ProductEntity;
import com.example.petsupplies.core.model.ProductSearchFilter;

public interface ProductSessionService {

  public List<ProductEntity> getProducts(ProductSearchFilter searchFilter);
  
  public List<ProductEntity> getAllProducts();

  public boolean createProduct(ProductEntity productEntity);

  public boolean editProduct(ProductEntity productEntity);

  public boolean deleteProduct(ProductEntity productEntity);
}
