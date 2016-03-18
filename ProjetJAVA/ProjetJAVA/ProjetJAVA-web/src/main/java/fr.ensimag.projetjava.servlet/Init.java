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

/**
 *
 * @author malacarc
 */
@WebServlet(urlPatterns = {"/init"})
public class Init extends HttpServlet {    
    @EJB
    private fr.ensimag.projetjava.stateless.ClientFacadeLocal clientFacade;
    @EJB
    private fr.ensimag.projetjava.stateless.ParamDateFacadeLocal paramDateFacade;
    @EJB
    private fr.ensimag.projetjava.stateless.ParamDoubleFacadeLocal paramDoubleFacade;
    @EJB 
    private fr.ensimag.projetjava.stateless.StockFacadeLocal stockFacade;
    @EJB
    private fr.ensimag.projetjava.stateless.ParamStockFacadeLocal paramStockFacade;
    
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
        
        //Test insertion paramDate
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(1992, 04, 17);
        ParamDate paramDate = new ParamDate(myCalendar, "Anniv Didier");
        paramDateFacade.create(paramDate);
        
        //Test insertion paramDouble
        ParamDouble paramDouble = new ParamDouble(3.14, "pi");
        paramDoubleFacade.create(paramDouble);
        
        //Test insertion stock
        Stock stock = new Stock("CAC40", ".FCHI");
        stockFacade.create(stock);
        
        //Test insertion paramStock
        Stock stock2 = new Stock("DAX", ".TOTO");
        ParamStock paramStock = new ParamStock(stock2, "stock");
        paramStockFacade.create(paramStock);
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
