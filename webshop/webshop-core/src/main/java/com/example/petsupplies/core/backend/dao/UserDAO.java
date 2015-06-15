package com.example.petsupplies.core.backend.dao;

import com.example.petsupplies.core.backend.entity.UserEntity;

public interface UserDAO {
  public UserEntity login(String userName, String password);
  public UserEntity createUser(UserEntity userEntity);
}
