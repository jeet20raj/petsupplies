package com.example.petsupplies.core;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ResourceProducer {

  @Produces
  @PersistenceContext(name="webshopPU")
  private EntityManager entityManager;
  
  /** 
   * @param injectionPoint 
   * @return logger 
   */  
   @Produces  
   public Logger produceLogger(InjectionPoint injectionPoint) {  
       return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());  
   }  
}
