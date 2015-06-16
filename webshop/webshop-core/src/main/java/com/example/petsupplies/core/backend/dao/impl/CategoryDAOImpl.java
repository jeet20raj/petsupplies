  package com.example.petsupplies.core.backend.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.example.petsupplies.core.backend.dao.BaseDAO;
import com.example.petsupplies.core.backend.dao.CategoryDAO;
import com.example.petsupplies.core.backend.entity.CategoryEntity;
import com.example.petsupplies.core.common.constants.Constants;
/**
 * 
 * @author Jeetendra
 * CategoryDAOImpl is used to call the operations related to category
 */
public class CategoryDAOImpl extends BaseDAO implements CategoryDAO {
/**
	 * @param
	 * @return returns the list of product categories This method is used to
	 *         register user into application
	 */
	public List<CategoryEntity> getCategories() {
		logger.log(Level.INFO, "CategoryDAOImpl getCategories Method Starts");
		TypedQuery<CategoryEntity> queryResults = entityManager
				.createNamedQuery(Constants.QueryConstants.FIND_CATEGORIES,
						CategoryEntity.class);
		try {
			return queryResults.getResultList();
		} catch (NoResultException e) {
			logger.log(Level.SEVERE,
					"CategoryDAOImpl getCategories Method Ends With NoResultException :: "
							,e);
			
		} catch (PersistenceException e) {
			logger.log(Level.SEVERE,
					"CategoryDAOImpl getCategories Method Ends With PersistenceException :: ",
							 e);
		}
		return new ArrayList<CategoryEntity>();
	}
/**
	 * @param category
	 * @return boolean 
	 * This method is used to create product category
	 */
	public boolean createCategory(CategoryEntity categoryEntity){
		logger.log(Level.INFO, "CategoryDAOImpl getCategories Method Starts");
		try {
			entityManager.persist(categoryEntity);
			return true;
		} catch (PersistenceException e) {
			logger.log(Level.SEVERE,
					"CategoryDAOImpl getCategories Method Ends With PersistenceException :: "
							,e);
		}

		return false;
	}
/**
	 * @param category
	 * @return boolean
	 * This method is used to edit the product category
	 */
	public boolean editCategory(CategoryEntity categoryEntity) {
		logger.log(Level.INFO,"CategoryDAOImpl :: edit category method called");
	    try {
	    	CategoryEntity categoEntityInDB = entityManager.find(CategoryEntity.class, categoryEntity.getCategoryId());
	        entityManager.remove(categoEntityInDB);
	        return true;
	    } catch (PersistenceException e) {
	    	logger.log(Level.SEVERE, "CategoryDAOImpl :: edit category ends with PersistenceException :: ",e); 
	    	}
	    return false;
	}
	
	/*
	 * (non-Javadoc)
	 * This method is doing soft delete for category using obsolete column
	 */

	public boolean deleteCategory(CategoryEntity categoryEntity){
		logger.log(Level.INFO,"CategoryDAOImpl :: deleteProduct method called");
	    try {
	        entityManager.merge(categoryEntity);
	        return true;
	    } catch (PersistenceException e) {
	    	logger.log(Level.SEVERE, "CategoryDAOImpl :: deleteCategory ends with PersistenceException :: ",e); 
	    	}
	    return false;
	}
}
