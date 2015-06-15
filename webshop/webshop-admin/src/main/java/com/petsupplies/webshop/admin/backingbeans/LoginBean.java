package com.petsupplies.webshop.admin.backingbeans;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.example.petsupplies.core.backend.entity.UserEntity;
import com.example.petsupplies.core.service.UserSessionService;
import com.petsupplies.webshop.admin.model.LoginForm;
import com.petsupplies.webshop.admin.qualifiers.UserLoggedIn;

@Named
@SessionScoped
public class LoginBean implements Serializable {

  @Inject
  private LoginForm loginForm;

  @Inject
  private UserSessionService userSessionService;

  private UserEntity loggedInUser;

  @Inject
  private transient Logger logger;

  public String login() {
    String viewPage=null;
    logger.log(Level.INFO, "LoginBean :: login starts");
    HttpSession httpSession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    if(null == httpSession || (null != httpSession && null == httpSession.getAttribute("userLogedIn"))){
        httpSession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
      loggedInUser =
          userSessionService.login(loginForm.getUserName(), loginForm.getPassword());
      httpSession.setAttribute("userLogedIn", loggedInUser);
      viewPage = "adminHome";
    }else{
      loggedInUser =
          (UserEntity)httpSession.getAttribute("userLogedIn");
    }
    if (null != loggedInUser) {
      viewPage = "adminHome";
    } else {
      loginForm.setMessage("Invalid user name passowrd");
    }
    logger.log(Level.INFO, "LoginBean :: login ends");
    return viewPage;
  }
  
  public String logout() {
    String viewPage="login";
    logger.log(Level.INFO, "LoginBean :: logout starts");
    HttpSession httpSession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    if(null != httpSession && null != httpSession.getAttribute("userLogedIn")){
      httpSession.setAttribute("userLogedIn", null);
    }
    logger.log(Level.INFO, "LoginBean :: logout ends");
    return viewPage;
  }

  @Produces
  @UserLoggedIn
  UserEntity getLoggedInUser() {
    return loggedInUser;
  }
}
