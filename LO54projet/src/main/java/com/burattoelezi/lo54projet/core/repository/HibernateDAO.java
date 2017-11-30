/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burattoelezi.lo54projet.core.repository;

import com.burattoelezi.lo54projet.core.entity.Client;
import com.burattoelezi.lo54projet.core.entity.Location;
import com.burattoelezi.lo54projet.core.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author aelez
 */
public class HibernateDAO {
    
    public Client getInfoClient(int idClient){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Client client = (Client) session.get(Client.class, idClient);
            session.getTransaction().commit();
            return client;
                        
        }
            catch (HibernateException he){
                he.printStackTrace();
                if(session.getTransaction() != null){
                    try{
                        session.getTransaction().commit();
                    }
                    catch (HibernateException he2){
                        he2.printStackTrace();
                    }
                }
            }
            finally{
                if (session != null){
                    try{
                        
                        session.close();
                        HibernateUtil.getSessionFactory().close();
                    
                    

                    }
                    catch (HibernateException he){
                        he.printStackTrace();
                    }
                }
            }
        return null;
    }
    
    public List<Location> getAllLoc(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            List<Location> listloc = (List<Location>) session.createQuery("from Location").list();
            session.getTransaction().commit();
            session.close();
            return listloc;
                        
        }
            catch (HibernateException he){
                he.printStackTrace();
                if(session.getTransaction() != null){
                    try{
                        session.getTransaction().commit();
                    }
                    catch (HibernateException he2){
                        he2.printStackTrace();
                    }
                }
            }
            finally{
                if (session != null){
                    try{
                        
                        session.close();
                        HibernateUtil.getSessionFactory().close();
                    
                    

                    }
                    catch (HibernateException he){
                        he.printStackTrace();
                    }
                }
            }
        return null;
    }
        
}
