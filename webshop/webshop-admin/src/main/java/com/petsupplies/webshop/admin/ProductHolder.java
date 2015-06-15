package com.petsupplies.webshop.admin;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.petsupplies.webshop.admin.model.ProductVO;

@Named("productsHolder")
@SessionScoped
public class ProductHolder implements Serializable {

	List<ProductVO> products;

	public ProductHolder() {
	}

	public List<ProductVO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductVO> products) {
		this.products = products;
	}
}
