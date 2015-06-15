package com.example.petsupplies.core.backend.dao;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;


public abstract class BaseDAO {
  @Inject
  protected EntityManager entityManager;

  @Inject  
  protected transient Logger logger; 
}
