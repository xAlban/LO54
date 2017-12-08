/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.burattoelezi.lo54projet.core.entity.Course_Session;
import com.burattoelezi.lo54projet.core.service.ClientService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Hibernate;

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            HttpSession session=request.getSession();
            ClientService service = new ClientService();
               
            
            //récupération des paramètres du formulaire
            String motCle = request.getParameter("keyword");
            String dateDebut = request.getParameter("dateDebut");
            String dateFin = request.getParameter("dateFin");
            String location = request.getParameter("location");
           // String lieu = new String(request.getParameter("lieu"));
            
            // appel d'une fonction qui renvoit la collection des sessions répondant aux critères
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Search</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Ci-dessous les sessions correspondant à vos critères </h1>");
            out.println("<link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb\" crossorigin=\"anonymous\">");
           /* 
            if (motCle.equals("") && dateDebut != "" && dateFin != "" && location != ""){
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date debut = new java.sql.Date(formatter.parse(dateDebut).getTime());
                Date fin = new java.sql.Date(formatter.parse(dateFin).getTime());
                List<Course_Session> listcs = service.getCourse_SessionWithParam(debut, fin, location);
            }
           */ 
            List<Course_Session> listcs;
            if(!dateDebut.isEmpty() && !dateFin.isEmpty()){
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date debut = new java.sql.Date(formatter.parse(dateDebut).getTime());
                Date fin = new java.sql.Date(formatter.parse(dateFin).getTime());
                listcs = service.getCourse_SessionWithParam(debut, fin, motCle, location);
            }else{
                listcs = service.getCourse_SessionWithParam(motCle, location);
            }
            
            for(Course_Session cs : listcs){
                Hibernate.initialize(cs.getFkCourse());
                Hibernate.initialize(cs.getFkLocation());
                out.println("<p><b>  "+cs.getFkCourse().getTitle()+"</b><a class=\"btn btn-primary\" href=\"./DetailSession?idsession="+cs.getId()+"&start="+cs.getStartDate()+"&end="+cs.getEndDate()+"&title="+cs.getFkCourse().getTitle()+"&location="+cs.getFkLocation().getCity()+"\" role=\"button\">  -> Cliquez pour voir le détail</a></p>");
            }

            // Faire une boucle d'affichage des sessions trouvées avec un lien pour s'y inscrire.
            out.println("<p>Mot clé de la recherche : "+ motCle +"</p>");
            out.println("<p>Date de début de la recherche : "+ dateDebut +"</p>");
            out.println("<p>Date de fin de la recherche : "+ dateFin +"</p>");
            out.println("<p>Lieu de session de la recherche : "+ location +"</p>");
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
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
