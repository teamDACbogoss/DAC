package fr.ensimag.projetjava.servlet;

import fr.ensimag.projetjava.entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

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
    @EJB
    private fr.ensimag.projetjava.stateless.StrategyFacadeLocal strategyFacade;
    @EJB
    private fr.ensimag.projetjava.stateless.VanillaPutFacadeLocal vanillaPutFacade;
    @EJB 
    private fr.ensimag.projetjava.stateless.PortfolioFacadeLocal portfolioFacade;
    
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
                            "mdp", 
                            true, 
                            "Malacarne", 
                            "Clément", 
                            SecretQuestion.q1, 
                            "Montmuzard",
                            1000.0,
                            0);
        clientFacade.create(client);
        client = new Client("didier@imag.fr", 
                            "mdp", 
                            true, 
                            "Didier", 
                            "Yeung", 
                            SecretQuestion.q1, 
                            "Montmuzard",
                            1000.0,
                            0);
        clientFacade.create(client);
        client = new Client("baptiste@imag.fr", 
                            "mdp", 
                            true, 
                            "Baptiste", 
                            "Josi", 
                            SecretQuestion.q1, 
                            "Montmuzard",
                            1000000.0,
                            1);
        clientFacade.create(client);
        client = new Client("johanna@imag.fr", 
                            "mdp", 
                            true, 
                            "Johanna", 
                            "Gogo Dago", 
                            SecretQuestion.q1, 
                            "Montmuzard",
                            1000.0,
                            0);
        clientFacade.create(client);
        client = new Client("theophile@imag.fr", 
                            "mdp", 
                            true, 
                            "Théophile", 
                            "Random", 
                            SecretQuestion.q1, 
                            "Montmuzard",
                            1000.0,
                            0);
        clientFacade.create(client);
        client = new Client("kevin@imag.fr", 
                            "mdp", 
                            true, 
                            "Kevin", 
                            "Bonkoski", 
                            SecretQuestion.q1, 
                            "Montmuzard",
                            1000.0,
                            2);
        clientFacade.create(client);
        client = new Client("chunli@imag.fr", 
                            "mdp", 
                            true, 
                            "Chunli", 
                            "Li", 
                            SecretQuestion.q1, 
                            "Montmuzard",
                            1000.0,
                            0);
        clientFacade.create(client);
        client = new Client("test", 
                            "test", 
                            false, 
                            "Xin", 
                            "Riu", 
                            SecretQuestion.q1, 
                            "Montmuzard",
                            1000.0,
                            3);
        clientFacade.create(client);
        
        // Insertion des actifs du CAC40
        Stock CAC40 = new Stock("Accor", "AC");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Air Liquide", "AI");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Alcatel-Lucent", "ALU");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Alstom", "ALO");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Arcelormital", "MT");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Axa", "CS");
        stockFacade.create(CAC40);
        CAC40 = new Stock("BNP Paribas", "BNP");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Bouygues", "EN");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Cap Gemini", "CAP");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Carrefour", "CA");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Crédit Agricole", "ACA");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Danone", "BN");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Dexia", "DEXB");
        stockFacade.create(CAC40);
        CAC40 = new Stock("EADS", "EAD");
        stockFacade.create(CAC40);
        CAC40 = new Stock("EDF", "EDF");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Essilor", "EI");
        stockFacade.create(CAC40);
        CAC40 = new Stock("France Telecom", "FTE");
        stockFacade.create(CAC40);
        CAC40 = new Stock("GDF Suez", "GSZ");
        stockFacade.create(CAC40);
        CAC40 = new Stock("L'Oréal", "OR");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Lafarge", "LG");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Lagardere", "MMB");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Legrand", "LR");
        stockFacade.create(CAC40);
        CAC40 = new Stock("LVMH", "MC");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Michelin", "ML");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Pernod Ricard", "RI");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Peugeot", "UG");
        stockFacade.create(CAC40);
        CAC40 = new Stock("PPR", "PP");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Renault", "RNO");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Saint Gobain", "SGO");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Sanofi", "SAN");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Schneider Electric", "SU");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Société Générale", "GLE");
        stockFacade.create(CAC40);
        CAC40 = new Stock("STMicroElectronic", "STM");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Technip", "TEC");
        stockFacade.create(CAC40);
        CAC40 = new Stock("TOTAL", "FP");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Unibail-Rodamco", "UL");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Vallourec", "VK");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Veolio", "VIE");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Vinci", "DG");
        stockFacade.create(CAC40);
        CAC40 = new Stock("Vivendi", "VIV");
        stockFacade.create(CAC40);
        
        /* //Test insertion d'une stratégie dont le sous-jacent est une stock
        List<ParamAssetInteger> stocks = new ArrayList<>();
        Stock stockInStrategy = stockFacade.find("TOTAL");
        ParamAssetInteger param = new ParamAssetInteger(stockInStrategy, 1);
        stocks.add(param);
        Strategy strategy = new Strategy("Stratégie Avec Stock En Base", stocks);
        strategyFacade.create(strategy);
        
        //Test récupération de la stratégie créer précédemment
        Strategy retrievedStrategy = strategyFacade.find(strategy.getName());
        System.out.println("Stratégie récupérée : " + retrievedStrategy.getAssets().get(0).getAsset().getName());
        
        //Test insertion d'une stratégie dont le sous-jacent est un put
        Stock stockInStrategy2 = stockFacade.find("Vallourec");
        if (stockInStrategy2 == null)
            System.out.println("vallourec non trouvé");
        Calendar maturityPutInStrategy2 = Calendar.getInstance();
        maturityPutInStrategy2.set(1992, 4, 17);
        VanillaPut vanillaPutInStrategy = new VanillaPut("Vanilla put in strategy", stockInStrategy2, 100.0, maturityPutInStrategy2);
        vanillaPutFacade.create(vanillaPutInStrategy);
        ParamAssetInteger param = new ParamAssetInteger(vanillaPutInStrategy, 2);
        List<ParamAssetInteger> params = new ArrayList<>();
        params.add(param);
        Strategy strategy2 = new Strategy("Strategie avec Put", params);
        strategyFacade.create(strategy2);
        
        //Test récupération de la stratégie créer précédemment
        Strategy retrievedStrategy2 = strategyFacade.find(strategy2.getName());
        System.out.println("Sous jacent de stratégie récupérée : " + retrievedStrategy2.getAssets().get(0).getAsset().getName());*/
       
        /* //Test création d'un portfolio contenant deux stratégies
        //Ajout de la première stratégie
        Portfolio portfolio = new Portfolio();
        List<ParamAssetInteger> stocks = new ArrayList<>();
        Stock stockInStrategy = stockFacade.find("TOTAL");
        ParamAssetInteger param = new ParamAssetInteger(stockInStrategy, 1);
        stocks.add(param);
        Strategy strategy = new Strategy("Stratégie1", stocks);
        strategyFacade.create(strategy);
        portfolio.getStrategies().add(strategy);
        
        //Ajout de la deuxième stratégie
        stocks = new ArrayList<>();
        stockInStrategy = stockFacade.find("Essilor");
        param = new ParamAssetInteger(stockInStrategy, 1);
        stocks.add(param);
        strategy = new Strategy("Stratégie2", stocks);
        strategyFacade.create(strategy);
        portfolio.getStrategies().add(strategy);
        portfolioFacade.create(portfolio);
        
        //Test récupération Portfolio
        Portfolio retrievedPortfolio = portfolioFacade.find(portfolio.getId());
        if (retrievedPortfolio == null)
        {
            System.out.println("Portfolio null !");
        }
        for (Strategy strategyPort : retrievedPortfolio.getStrategies())
        {
            System.out.println("Stratégie récupérée : " + strategyPort.getName());
        }*/
    
        //Test insertion dans un portfolio chez un client    
        //Stratégie 1
        List<ParamAssetInteger> stocks = new ArrayList<>();
        Stock stockInStrategy = stockFacade.find("TOTAL");
        ParamAssetInteger param = new ParamAssetInteger(stockInStrategy, 1);
        stocks.add(param);
        Strategy strategy = new Strategy("Stratégie1", stocks);
        strategyFacade.create(strategy);
        client.getPortfolio().getStrategies().add(strategy);
        //Stratégie 2
        stocks = new ArrayList<>();
        stockInStrategy = stockFacade.find("Arcelormital");
        param = new ParamAssetInteger(stockInStrategy, 2);
        stocks.add(param);
        strategy = new Strategy("Stratégie2", stocks);
        strategyFacade.create(strategy);
        client.getPortfolio().getStrategies().add(strategy);
        clientFacade.edit(client);
        
        //Test récupération dans un portfolio chez un client
        Client retrievedClient = clientFacade.find(client.getEmail());
        for (Strategy strategyPort : retrievedClient.getPortfolio().getStrategies())
        {
            System.out.println("Stratégie récupérée : " + strategyPort.getName());
        }
        
        //Test insertion paramDate
        /*Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(1992, 04, 17);
        ParamDate paramDate = new ParamDate(myCalendar, "Anniv Didier");
        paramDateFacade.create(paramDate);
        
        //Test insertion paramDouble
        ParamDouble paramDouble = new ParamDouble(3.14, "pi");
        paramDoubleFacade.create(paramDouble);
       
        
        //Test récupération stock
        Stock stock_test = stockFacade.find(CAC40.getId());
        System.out.println("Nom : " + stock_test.getName());
        
        ParamStock paramStock = new ParamStock(CAC40, "stock");
        paramStockFacade.create(paramStock);
        
        //Test récupération tous les stocks
        List<Stock> listStocks = stockFacade.findAll();
        for (Stock stock : listStocks) {
            System.out.println(stock.getName());
        }*/
        
        //Test insertion 
        
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
