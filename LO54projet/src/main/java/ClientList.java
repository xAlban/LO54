/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.burattoelezi.lo54projet.core.entity.Client;
import com.burattoelezi.lo54projet.core.entity.Course_Session;
import com.burattoelezi.lo54projet.core.service.ClientService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Hibernate;

/**
 *
 * @author fburatto
 */
@WebServlet(urlPatterns = {"/restricted/ClientList"})
public class ClientList extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            ClientService service = new ClientService();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ClientList</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>LISTE DES CLIENTS</h1>");
            
            List<Client> listcl = service.getListClients();
            for(Client cl : listcl){
                Hibernate.initialize(cl.getFkCourseSession());
                Course_Session laSession = cl.getFkCourseSession();
                if (laSession!=null){
                    out.println("<p>"+ cl.getFirstName()+" "+cl.getLastName()+" est inscrit(e) à la session de formation n°"+laSession.getId()+"</p>");
                    out.println("<p>Lieu de la session :"+laSession.getFkLocation().getCity()+"</p>");
                    out.println("<p>Intitulé :"+laSession.getFkCourse().getTitle()+"</p>");
                    out.println("<p>La session se déroulera du "+laSession.getStartDate() +" au "+laSession.getEndDate()+ "</p>");
                    out.println("******************");
                }
                else
                {
                    out.println("<p>Le client "+ cl.getFirstName()+" "+cl.getLastName()+" n'est inscrit(e) à aucune session de formation </p>");
                    out.println("******************");
                }

 
                //out.println("<p><b>  "+cl.getFirstName()+"</b><a href=\"./DetailSession?idsession="+cs.getId()+"&start="+cs.getStartDate()+"&end="+cs.getEndDate()+"&title="+cs.getFkCourse().getTitle()+"&location="+cs.getFkLocation().getCity()+"\">  -> Cliquez pour voir le détail</a></p>");
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
