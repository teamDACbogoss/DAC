import fr.ensimag.projetjava.entity.Client;
import fr.ensimag.projetjava.entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.*;

/**
 *
 * @author malacarc
 */
@WebServlet(urlPatterns = {"/init"})
public class Init extends HttpServlet {    
    @EJB
    private fr.ensimag.projetjava.stateless.ClientFacadeLocal clientFacade;
    private fr.ensimag.projetjava.stateless.ParamDateFacadeLocal paramDateFacade;
    
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Init</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Init at " + request.getContextPath() + "</h1>");
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
        Client client;
        client = new Client("clement@imag.fr", 
                            "clement", 
                            true, 
                            "Malacarne", 
                            "Clément", 
                            SecretQuestion.q1, 
                            "Montmuzard");
        clientFacade.create(client); 
        
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(92, 04, 17);
        Date dateForParamDate;
        dateForParamDate = myCalendar.getTime();
        ParamDate paramDate = new ParamDate(dateForParamDate, "Anniv Didier");
        paramDateFacade.create(paramDate);
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
