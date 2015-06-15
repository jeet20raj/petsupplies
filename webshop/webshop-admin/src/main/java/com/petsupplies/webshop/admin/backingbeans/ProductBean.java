 package com.petsupplies.webshop.admin.backingbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import com.example.petsupplies.core.backend.entity.CategoryEntity;
import com.example.petsupplies.core.backend.entity.ProductEntity;
import com.example.petsupplies.core.backend.entity.UserEntity;
import com.example.petsupplies.core.exceptions.WebshopException;
import com.example.petsupplies.core.service.CategorySessionService;
import com.example.petsupplies.core.service.ProductSessionService;
import com.petsupplies.webshop.admin.ProductHolder;
import com.petsupplies.webshop.admin.model.ProductVO;
import com.petsupplies.webshop.admin.qualifiers.UserLoggedIn;

@SuppressWarnings("serial")
@Named
@ConversationScoped
public class ProductBean implements Serializable {

	private @Inject
	Conversation conversation;

	@Named
	@RequestScoped
	@Produces
	private ProductEntity productEntity = new ProductEntity();

	@Inject
	private ProductSessionService productSessionService;

	@Inject
	private CategorySessionService categorySessionService;

	@Inject
	private ProductHolder productHolder;

	@Inject
	@UserLoggedIn
	private UserEntity loggedInUser;

	private Long categoryId;

	private UploadedFile productImage;
	
	@Inject
	private transient Logger logger;

	public List<CategoryEntity> getProductCategories() {
		return productCategories;
	}

	private List<CategoryEntity> productCategories;

	private List<ProductEntity> productsList;

	private ProductVO selectedProductVO;

	public String createProduct() {
		logger.log(Level.INFO,"ProductBean :: createProduct method starts");
		try {
			for (CategoryEntity categoryEntity : productCategories) {
				if (categoryEntity.getCategoryId().longValue() == (null != getCategoryId() ? getCategoryId()
						.longValue() : new Long(0))) {
					productEntity.setCategoryEntity(categoryEntity);
					break;
				}
			}
			productSessionService.createProduct(productEntity);

		} catch (WebshopException e) {
			logger.log(Level.SEVERE,"Product has not been saved" ,e);
		}
		List<ProductVO> products = new ArrayList<ProductVO>();
		productsList = productSessionService.getAllProducts();
		makeProductVOList(productsList, products);
		productHolder.setProducts(products);
		endConversation();
		logger.log(Level.INFO,"ProductBean :: createProduct method ends");
		return "showProducts";
	}

	public String showPordEditPage() {
		logger.log(Level.INFO,"ProductBean :: showPordEditPage method starts");
		if (null != getSelectedProductVO()) {
			productCategories = categorySessionService.getCategories();
			productEntity = getSelectedProductVO().getProductEntity();
			startConversation();
			return "editProduct";
		} else {
			String errorMessage = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(),"message").getString("webshop.errmsg.selectfromgridtoedit");
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,errorMessage, null));
		}
		logger.log(Level.INFO,"ProductBean :: showPordEditPage method ends");
		return null;
	}

	public String editProduct() {
		logger.log(Level.INFO,"ProductBean :: editProduct method starts");
		if (null != getSelectedProductVO().getProductEntity()) {
			productSessionService.editProduct(getSelectedProductVO()
					.getProductEntity());
			productsList = productSessionService.getAllProducts();
			List<ProductVO> products = new ArrayList<ProductVO>();
			productsList = productSessionService.getAllProducts();
			makeProductVOList(productsList, products);
			productHolder.setProducts(products);
			endConversation();
			logger.log(Level.INFO,"ProductBean :: editProduct method ends");
			return "showProducts";
		}
		return null;
	}

	public String deleteProduct() {
		logger.log(Level.INFO,"ProductBean :: deleteProduct method starts");
		if (null != getSelectedProductVO()) {
			productSessionService.deleteProduct(getSelectedProductVO()
					.getProductEntity());
			productsList = productSessionService.getAllProducts();
			List<ProductVO> products = new ArrayList<ProductVO>();
			productsList = productSessionService.getAllProducts();
			makeProductVOList(productsList, products);
			productHolder.setProducts(products);
			logger.log(Level.INFO,"ProductBean :: deleteProduct method ends");
			return "showProducts";
		} else {
			String errorMessage = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(),"message").getString("webshop.errmsg.selectfromgrid");
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,errorMessage, null));
		}
		return null;
	}

	public String showProductPage() {
		productCategories = categorySessionService.getCategories();
		startConversation();
		return "addProduct";
	}

	public String showAllProducts() {
		List<ProductVO> products = new ArrayList<ProductVO>();
		productsList = productSessionService.getAllProducts();
		makeProductVOList(productsList, products);
		productHolder.setProducts(products);
		return "showProducts";
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	private void startConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}

	private void endConversation() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public ProductVO getSelectedProductVO() {
		return selectedProductVO;
	}

	public void setSelectedProductVO(ProductVO selectedProductVO) {
		this.selectedProductVO = selectedProductVO;
	}

	public void setProductCategories(List<CategoryEntity> productCategories) {
		this.productCategories = productCategories;
	}

	public ProductHolder getProductHandler() {
		return productHolder;
	}

	public UserEntity getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(UserEntity loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public void setProductHandler(ProductHolder productHolder) {
		this.productHolder = productHolder;
	}

	public UploadedFile getProductImage() {
		return productImage;
	}

	public void setProductImage(UploadedFile productImage) {
		this.productImage = productImage;
	}

	private void makeProductVOList(List<ProductEntity> productEntities,
			List<ProductVO> productVOs) {
		for (ProductEntity productEntity : productEntities) {
			ProductVO productVO = new ProductVO(productEntity);
			productVOs.add(productVO);
		}
	}
}
