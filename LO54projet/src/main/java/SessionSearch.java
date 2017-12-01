/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.burattoelezi.lo54projet.core.entity.Location;
import com.burattoelezi.lo54projet.core.service.ClientService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(urlPatterns = {"/restricted/Recherche_Sessions"})
public class SessionSearch extends HttpServlet {
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
             ClientService mesServ = new ClientService();
             List<Location> locs;
            
            HttpSession session=request.getSession();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            
            out.println("<head>");
            out.println("<title>Servlet Session_Search</title>");            
            out.println("</head>");
            
            out.println("<body>");
            out.println("<h1>SECTION DE RECHERCHE DE SESSIONS DE FORMATIONS</h1>");
            
            out.println("<form action=\"./restricted/Recherches\" method=\"post\">");
            
            out.println("<div>");
            out.println("<label for=\"login\">Recherche par mot clé dans l'intitlué de la session :</label>");
            out.println("<input type=\"text\" name=\"keyword\" />");
            out.println("</div>"); 
            
            out.println("<div>");
            out.println("<p>Recherche par dates de session disponible : </p>");
            out.println("<p><label for=\"login\">Date début</label>");
            out.println("<input type=\"date\" name=\"dateDebut\" /></p>");
            out.println("<p><label for=\"login\">Date fin</label>");
            out.println("<input type=\"date\" name=\"dateFin\" /></p>");
            out.println("</div>");
            
            out.println("<div>");
            out.println("<label for=\"login\">Liste des lieux de sessions disponibles :</label>");
            
            // Lancer une méthode pour lancer la liste des lieux de session à venir, dedoublonnee
            
            out.println("<SELECT>");
            //faire une boucle pour afficher chaque occurrence a coté d'une balise <OPTION>
            List<Location> listloc = mesServ.getAllLoc();
            out.println("<p>test</p>");
            for(Location l : listloc){
                out.println("<OPTION>"+l.getCity());
               
            }

            out.println("</SELECT>"); 
            out.println("</div><br>"); 
                              
            out.println("<div class=\"button\">"); 
            out.println("<button type=\"submit\">Lancer la recherche</button>"); 
            out.println("</div>"); 
            
            out.println("</form");                    
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