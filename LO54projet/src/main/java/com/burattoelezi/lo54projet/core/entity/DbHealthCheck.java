package com.burattoelezi.lo54projet.core.entity;


import com.burattoelezi.lo54projet.core.repository.HibernateDAO;
import com.codahale.metrics.health.HealthCheck;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fburatto
 */

public class DbHealthCheck extends HealthCheck{
private final HibernateDAO database;
 
 public DbHealthCheck(HibernateDAO database){
     this.database = database;
 }
 
 @Override 
 public HealthCheck.Result check() throws Exception { 
  if (database.isConnected()){
      return HealthCheck.Result.healthy("Connected to database ");
  }else {
      return HealthCheck.Result.unhealthy("Can't connect to database ");
  }

 } 
}
