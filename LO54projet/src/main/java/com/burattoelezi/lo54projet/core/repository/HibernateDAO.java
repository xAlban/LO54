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
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
            Integer result=-1;
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
        
    
    public List<Course_Session> getCourse_SessionWithParam(Date debut, Date fin, String keyword, String location){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            String strquery;

            if(location.equals("Tout les lieux")){
               strquery = "from Course_Session c where";            
                if(debut.toString() !=""){
                    strquery = strquery.concat(" c.startDate >= '"+debut.toString()+"'");
                }
                if(fin.toString() != "" && strquery.endsWith("'")){
                    strquery = strquery.concat(" and c.endDate <= '"+fin.toString()+"'");
                }else{
                    strquery = strquery.concat(" c.endDate <= '"+fin.toString()+"'");
                }
                if(keyword != "" && strquery.endsWith("'")){
                    strquery = strquery.concat(" and c.fkCourse.title like '%"+keyword+"%'");
                }else{
                    strquery.concat(" c.fkCourse.title like '%"+keyword+"%'");
                }
            }else{
                strquery = "from Course_Session c where c.fkLocation.city ='"+location+"' ";            
                if(debut.toString() !=""){
                    strquery = strquery.concat("and c.startDate >= '"+debut.toString()+"' ");
                }
                if(fin.toString() != ""){
                    strquery = strquery.concat("and c.endDate <= '"+fin.toString()+"' ");
                }
                if(keyword != ""){
                    strquery = strquery.concat("and c.fkCourse.title like '%"+keyword+"%'");
                }
            }
            Query query = session.createQuery(strquery);
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
    
    public List<Course_Session> getCourse_SessionWithParam(String keyword, String location){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            String strquery;

            if(location.equals("Tout les lieux")){
                strquery = "from Course_Session c where";                           
                strquery = strquery.concat(" c.fkCourse.title like '%"+keyword+"%'");
            }else{
                strquery = "from Course_Session c where c.fkLocation.city ='"+location+"' ";            
                strquery = strquery.concat("and c.fkCourse.title like '%"+keyword+"%'");
            }
            Query query = session.createQuery(strquery);
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
    
    public void affecteSession(String idClient, String idSession){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            
            Integer idC= Integer.valueOf(idClient);
            Integer idS= Integer.valueOf(idSession);
            Client client = (Client) session.get(Client.class,idC);
            Course_Session cc = (Course_Session) session.get(Course_Session.class,idS);
        
            client.setFkCourseSession(cc);
            session.persist(client);
            session.getTransaction().commit();
            session.close();
 
                        
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


        
    }

    public List<Client> getListClients() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            List<Client> listloc = (List<Client>) session.createQuery("from Client").list();
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