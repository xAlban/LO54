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
            out.println("<link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb\" crossorigin=\"anonymous\">");
            out.println("<form action=\"./Recherches\" method=\"post\">");
            
            out.println("<div>");
            out.println("<p>(Pour acceder à la liste de clients, cliquez <a href=\"./ClientList\">ici</a>)</p>");
            //Ci-dessous on teste si l'on vient déjà d'une saisie dans laquelle des champs n'ont pas été 
            //correctement renseignés. Auquel cas on informe l'utilisateur du champ mal saisi,
            //et on recharge les valeurs des champs remplis au préalable avec des valeurs correctes...
            String validiteMotCle=(String)request.getAttribute("validiteMotCle");
            if(validiteMotCle!=null) 
                if(validiteMotCle.equals("false")) out.println("<p>Saisie incorrecte : mot clé trop long</p>");
            
            out.println("<label for=\"login\">Recherche par mot clé dans l'intitlué de la session :</label>");
            String varTemp = request.getParameter("keyword");
            if (varTemp==null) out.println("<input type=\"text\" name=\"keyword\" />");
            else if (varTemp.isEmpty()) out.println("<input type=\"text\" name=\"keyword\" />");
                 else out.println("<input type=\"text\" name=\"keyword\" value="+varTemp+" />");
            out.println("</div>"); 
            
            out.println("<div>");
            
            out.println("<p>Recherche par dates de session disponible : </p>");

            String validiteDateDebut=(String)request.getAttribute("validiteDateDebut");
            if (validiteDateDebut!=null)
                if(validiteDateDebut.equals("false")) out.println("<p>Saisie incorrecte : date de début incorrecte</p>");
            
            out.println("<p><label for=\"login\">Date début</label>");
            varTemp = request.getParameter("dateDebut");
            if (varTemp==null) out.println("<input type=\"date\" name=\"dateDebut\" /></p>");
            else if (varTemp.isEmpty()) out.println("<input type=\"date\" name=\"dateDebut\" /></p>");
                 else out.println("<input type=\"date\" name=\"dateDebut\" value="+varTemp+" /></p>");
            
            String validiteDateFin=(String)request.getAttribute("validiteDateFin");
            if (validiteDateFin!=null)
                if(validiteDateFin.equals("false")) out.println("<p>Saisie incorrecte : date de fin incorrecte</p>");
            
            out.println("<p><label for=\"login\">Date fin</label>");
            varTemp = request.getParameter("dateFin");
            if (varTemp==null) out.println("<input type=\"date\" name=\"dateFin\" /></p>");
            else if (varTemp.isEmpty()) out.println("<input type=\"date\" name=\"dateFin\" /></p>");
                 else out.println("<input type=\"date\" name=\"dateFin\" value="+varTemp+" /></p>");
            
            out.println("</div>");
            
            out.println("<div>");
            out.println("<label for=\"login\">Liste des lieux de sessions disponibles :</label>");
            
            // Lancer une méthode pour lancer la liste des lieux de session à venir, dedoublonnee
            
            out.println("<SELECT name=\"location\">");
            //faire une boucle pour afficher chaque occurrence a coté d'une balise <OPTION>+-
            out.println("<OPTION>Tout les lieux</OPTION> ");
            List<Location> listloc = mesServ.getAllLoc();
            for(Location l : listloc){
                out.println("<OPTION>"+l.getCity()+"</OPTION>");
               
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