package com.example.petsupplies.core.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@SuppressWarnings("serial")
@Entity
@Table(name = "webshop_users")
@NamedQueries({
    @NamedQuery(name="findUserByName",query="select user from UserEntity user where user.userName = :userName"),
    @NamedQuery(name="findUser",query="select user from UserEntity user where user.userName = :userName and user.password = :password")
})
public class UserEntity extends AbstractJPAEntity{
  
  private Long userId;
  
  private String userName;
 
  private String firstName;
 
  private String lastName;
  
  private String password;
  
  @Transient
  private String confirmPassword;
  
  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  private Boolean isAdmin;

  @Id @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="user_id",length=10)
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Column(name="user_name",length=50,unique=true)
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Column(name="first_name",length=150,unique=true)
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  @Column(name="last_name",length=150,unique=true)
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Column(name="password",length=15)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name="isAdminUser",length=1)
  @Type(type="org.hibernate.type.BooleanType")
  public Boolean getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(Boolean isAdmin) {
    this.isAdmin = isAdmin;
  }
  
  @Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.userName.hashCode();
	}
  @Override
	public boolean equals(Object obj) {
		if(obj instanceof UserEntity){
			UserEntity userEntity = (UserEntity)obj;
			if(userEntity.getUserName().equals(this.getUserName())){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
}
