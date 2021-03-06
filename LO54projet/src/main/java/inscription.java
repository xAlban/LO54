/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.burattoelezi.lo54projet.core.service.ClientService;
import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fburatto
 */
@WebServlet(urlPatterns = {"/restricted/inscription"})
public class inscription extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private final MetricRegistry metrics = new MetricRegistry();
    private final Counter counter = new Counter();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String id = request.getParameter("idsession");
      
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet inscription</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"row\">");
            out.println("<div class=\"col\"></div>");
            out.println("<div class=\"col\">");
            out.println("<h1 style=\"text-align:center\" class=\"text-success\">Inscription réussie !</h1>");
            out.println("</div>");
            out.println("<div class=\"col\"></div>");
            out.println("</div>");
            out.println("<link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\" rel=\"stylesheet\">");
            
            out.println("<div class=\"row\">");
            out.println("<div class=\"col\"></div>");
            out.println("<div class=\"col\">");
             if (id.isEmpty()) {
                out.println("<p class=\"text-danger\"><b>Erreur ! Contactez votre administrateur....</b></p>");
             }else{
                ClientService serv = new ClientService();
                HttpSession session=request.getSession();
                serv.affecteSession((String)session.getAttribute("id_user"),id);
                counter.inc();
                long nbInscrits = counter.getCount();
                out.println("<p style=\"text-align:center\">Votre inscription a été prise en compte....</p>");
                if(nbInscrits == 1) out.println("<p> Vous êtes la 1ière personne inscrite à une formation aujourd'hui.</p>");
                else out.println("<p> Vous êtes la "+nbInscrits+"ième personne inscrite à une formation aujourd'hui.</p>");
                out.println("<a style=\"text-align:center\" href=\"./Recherche_Sessions\" class=\"btn btn-lg btn-primary btn-block\">Retour page recherche</a>");            
               
             }
            out.println("</div>");
            out.println("<div class=\"col\"></div>");
            out.println("</div>");
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
