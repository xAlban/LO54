/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burattoelezi.lo54projet.core.repository;

import com.burattoelezi.lo54projet.core.entity.Client;
import com.burattoelezi.lo54projet.core.entity.Course_Session;
import com.burattoelezi.lo54projet.core.entity.Location;
import com.burattoelezi.lo54projet.core.util.HibernateUtil;
import java.sql.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
            session.close();
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
    
    
    public Integer rechercheID(String email, String password){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            Integer result=0;
            session.beginTransaction();
            
            Query query = session.createQuery("from Client c where c.email = ? ");
            query.setParameter(0,email);
            List<Client> clientList = query.list();
            session.getTransaction().commit();
            session.close();
            if(clientList.isEmpty()) result=-1;
            else {
                if (password.contentEquals(clientList.get(0).getPassword()))
                result=clientList.get(0).getId();
            }
            
            return result;            
            
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
        return -1;
    }
        
    
    public List<Course_Session> getCourse_SessionWithParam(Date debut, Date fin, String keyword){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Query query = session.createQuery("from Course_Session c where c.startDate >= '"+debut.toString()+"' and c.endDate <= '"+fin.toString()+"'");
            List<Course_Session> listcs = (List<Course_Session>) query.list();
            session.getTransaction().commit();
            session.close();
            return listcs;
                        
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