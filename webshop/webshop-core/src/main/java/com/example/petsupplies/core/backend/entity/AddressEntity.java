package com.example.petsupplies.core.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

@Entity
@Table(name = "user_address")
public class AddressEntity extends AbstractJPAEntity {

  private Long addressId;

  private Long userId;

  private String addressLine1;

  private String addressLine2;

  private String city;

  private String state;

  private String country;

  private String zipCode;

  private String addressType;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "address_id", length = 10)
  public Long getAddressId() {
    return addressId;
  }

  public void setAddressId(Long addressId) {
    this.addressId = addressId;
  }

  @Column(name = "user_id", length = 10)
  @JoinColumn(name = "user_id")
  @JoinTable(name = "webshop_users")
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Column(name = "address_line1", length = 100)
  public String getAddressLine1() {
    return addressLine1;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  @Column(name = "address_line2", length = 100)
  public String getAddressLine2() {
    return addressLine2;
  }

  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  @Column(name = "city", length = 30)
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @Column(name = "state", length = 30)
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Column(name = "country", length = 100)
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Column(name = "zipcode", length = 50)
  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  @Column(name = "user_name", length = 50)
  public String getAddressType() {
    return addressType;
  }

  public void setAddressType(String addressType) {
    this.addressType = addressType;
  }
}
