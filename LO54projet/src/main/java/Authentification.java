/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.burattoelezi.lo54projet.core.service.ClientService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
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
@WebServlet(urlPatterns = {"/authentification"})
public class Authentification extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    private Integer test_identifiants(String email, String password){
        //Cette fonction va tester l'existence de l'utilisateur dans la base.
        //Il faudra rajouter un champ mot de passe.
        //La fonction renvoie l'ID du client (PKEY dans la table CLIENT) qui servira pour l'enregistrement
        //eventuel a une session de formation
        
        ClientService serv = new ClientService();
        return serv.rechercheID(email, password);
        
    }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            HttpSession session=request.getSession();
            String email = new String(request.getParameter("email"));
            String password = new String(request.getParameter("password"));
            
            int id=test_identifiants(email,password);
            if (id!=-1){
                session.setAttribute("id_user",String.valueOf(id));
                session.setAttribute("authenticated",true);
                HttpServletResponse hresp = null;
                if (response instanceof HttpServletResponse) hresp = (HttpServletResponse) response;
                hresp.sendRedirect(request.getContextPath()+"/restricted/Recherche_Sessions");
            }else
            {
//                out.println("<script type=\"text/javascript\">");
//                out.println("alert('User or password incorrect');");
//                out.println("</script>");
                RequestDispatcher dis = request.getRequestDispatcher("./login_failed.html");
                dis.forward(request, response);
            }
        

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
