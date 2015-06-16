package com.example.petsupplies.core.backend.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.example.petsupplies.core.backend.dao.BaseDAO;
import com.example.petsupplies.core.backend.dao.ProductDAO;
import com.example.petsupplies.core.backend.entity.ProductEntity;
import com.example.petsupplies.core.common.constants.Constants;
import com.example.petsupplies.core.model.ProductSearchFilter;
/**
 * 
 * @author Jeetendra
 * ProductDAOImpl is used to call the operations related to product.
 */
public class ProductDAOImpl extends BaseDAO implements ProductDAO {

  public List<ProductEntity> getProducts(ProductSearchFilter searchFilter) {
    
    return new ArrayList<ProductEntity>();
  }
/**
   * @param user name and password
   * @return list of products
   * This method is used for user login based in user name and password
   */
  public List<ProductEntity> getAllProducts() {
    logger.info("ProductDAOImpl :: getAllProducts method called");
    TypedQuery<ProductEntity> queryResults =
        entityManager.createNamedQuery(Constants.QueryConstants.SHOW_ALL_PRODUCTS,
            ProductEntity.class);
    try {
      return queryResults.getResultList();
    } catch (NoResultException e) {
    	logger.log(Level.SEVERE, "ProductDAOImpl :: getAllProducts ends with NoResultException :: ",e);
        
    }catch (PersistenceException ex) {
    	logger.log(Level.SEVERE, "ProductDAOImpl :: getAllProducts ends with PersistenceException :: ",ex);
	}
    return new ArrayList<ProductEntity>();
  }
/**
   * @param product
   * @return boolean
   * This method is used to create product.
   */
  public boolean createProduct(ProductEntity productEntity) {
	  logger.info("ProductDAOImpl :: createProduct method called");
    try {
      entityManager.persist(productEntity);
      return true;
    } catch (PersistenceException e) {
    	logger.log(Level.SEVERE, "ProductDAOImpl :: createProduct ends with PersistenceException :: ",e);
    }

    return false;
  }
/**
   * @param product
   * @return boolean
   * This method is used to delete product from the DB
   */
  public boolean deleteProduct(ProductEntity productEntity) {
	  logger.info("ProductDAOImpl :: deleteProduct method called");
    try {
        ProductEntity productInDB = entityManager.find(ProductEntity.class, productEntity.getProductId());
        entityManager.remove(productInDB);
        return true;
    } catch (PersistenceException e) {
    	logger.log(Level.SEVERE, "ProductDAOImpl :: deleteProduct ends with PersistenceException :: ",e); 
    	}
    return false;
  }
 /**
   * @param product
   * @return boolean
   * This method is used to edit product from the DB
   */
  public boolean editProduct(ProductEntity productEntity) {
    logger.info("ProductDAOImpl :: editProduct method called");
    try {
      entityManager.merge(productEntity);
      return true;
    } catch (PersistenceException e) {
    	logger.log(Level.SEVERE, "ProductDAOImpl :: editProduct ends with PersistenceException :: ",e);
    }
    return false;
  }

}
