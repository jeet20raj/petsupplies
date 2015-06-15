package com.petsupplies.webshop.admin.backingbeans;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.example.petsupplies.core.backend.entity.UserEntity;
import com.example.petsupplies.core.common.constants.Constants;
import com.example.petsupplies.core.exceptions.WebshopException;
import com.example.petsupplies.core.service.UserSessionService;

@Named
@RequestScoped
public class UserBean {

	@Named
	@Produces
	@RequestScoped
	private UserEntity userEntity = new UserEntity();
	
	private UserEntity loggedInUser;

	@Inject
	private UserSessionService userSessionService;

	@Inject
	private transient Logger logger;

	public String createUser() {
		logger.log(Level.INFO, "UserBean :: createUser starts");
		try {
			if (!userEntity.getPassword().equals(
					userEntity.getConfirmPassword())) {
				String errorMessage = FacesContext
						.getCurrentInstance()
						.getApplication()
						.getResourceBundle(FacesContext.getCurrentInstance(),
								"message").getString("webshop.errmsg.password");
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								errorMessage, null));
				return null;
			}
			loggedInUser = userSessionService.createUser(userEntity);
			HttpSession httpSession = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext()
					.getSession(false);
			if (null == httpSession) {
				httpSession = (HttpSession) FacesContext.getCurrentInstance()
						.getExternalContext().getSession(true);
			} 
			httpSession.setAttribute("userLogedIn", loggedInUser);
		} catch (WebshopException e) {
			if (Constants.USER_ALREADY_EXISTS.equals(e.getErrorCode())) {
				logger.log(Level.SEVERE,
						"UserBean :: Exception Occured User Aleady Exist ",e);
			}
		}
		logger.log(Level.INFO, "UserBean :: createUser ends");
		return "adminHome";
	}
}
