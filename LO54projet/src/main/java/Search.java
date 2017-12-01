/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.burattoelezi.lo54projet.core.service.ClientService;
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
@WebServlet(urlPatterns = {"/restricted/Recherches"})
public class Search extends HttpServlet {

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
            
            HttpSession session=request.getSession();
            ClientService service = new ClientService();
               
            
            //récupération des paramètres du formulaire
            String motCle = new String(request.getParameter("keyword"));
            String dateDebut = new String(request.getParameter("dateDebut"));
            String dateFin = new String(request.getParameter("dateFin"));
           // String lieu = new String(request.getParameter("lieu"));
            
            // appel d'une fonction qui renvoit la collection des sessions répondant aux critères
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Search</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Ci-dessous les sessions correspondant à vos critères </h1>");
            
                      

            // Faire une boucle d'affichage des sessions trouvées avec un lien pour s'y inscrire.
            out.println("<p>Mot clé de la recherche : "+ motCle +"</p>");
            out.println("<p>Date de début de la recherche : "+ dateDebut +"</p>");
            out.println("<p>Date de fin de la recherche : "+ dateFin +"</p>");
           // out.println("<p>Lieu de session de la recherche : "+ lieu +"</p>");
            out.println("<p>Code User : "+ session.getAttribute("id_user") +"</p>");
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
