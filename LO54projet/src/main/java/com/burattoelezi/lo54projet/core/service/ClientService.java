/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burattoelezi.lo54projet.core.service;

import com.burattoelezi.lo54projet.core.entity.Client;
import com.burattoelezi.lo54projet.core.entity.Location;
import com.burattoelezi.lo54projet.core.repository.HibernateDAO;
import java.util.List;

/**
 *
 * @author aelez
 */
public class ClientService {
    
    private final HibernateDAO dao = new HibernateDAO();
    
 
    public Client getInfoClient(int idClient){
    
        return dao.getInfoClient(idClient);
        
    }
    
    public List<Location> getAllLoc(){
        
        return dao.getAllLoc();
    }
    
}
