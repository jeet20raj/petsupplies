package com.example.petsupplies.core.service;

import com.example.petsupplies.core.backend.entity.UserEntity;
import com.example.petsupplies.core.exceptions.WebshopException;
/**
 * @author Jeetendra
 * UserSessionService is used expose methods to login into the system 
 * Its also used to create user and user address.
 */
public interface UserSessionService {
  public UserEntity login(String userName, String password);

  public UserEntity createUser(UserEntity userEntity) throws WebshopException;
}
