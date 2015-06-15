package com.example.petsupplies.core.service;

import com.example.petsupplies.core.backend.entity.UserEntity;
import com.example.petsupplies.core.exceptions.WebshopException;

public interface UserSessionService {
  public UserEntity login(String userName, String password);

  public UserEntity createUser(UserEntity userEntity) throws WebshopException;
}
