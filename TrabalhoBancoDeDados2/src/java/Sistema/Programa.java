/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import Dados.Dados;
import Log.Log;
import Log.TipoLog;
import Models.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 0084158
 */
@WebServlet(name = "Programa", urlPatterns = {"/Programa"})
public class Programa extends HttpServlet {

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
        System.out.println("ENTROU NNO SERVLET PROGRAMA!!!!!!!! <<-------------------------------");
        response.setContentType("text/html;charset=UTF-8");
        
        int totalDadosDisco = Dados.getTotalDadosDisco();
        String aux = Integer.toString(totalDadosDisco);
        request.setAttribute("totalDadosDisco", aux);
        
        int totalTransacoes = Log.getTotalTransacoes();
        String aux2 = Integer.toString(totalTransacoes);
        request.setAttribute("totalTransacoes", aux2);
                
        
        
        //abre os Logs
        List<TipoLog> logBuffer = Log.getLogBuffer();
        request.setAttribute("logBuffer", logBuffer);
        List<TipoLog> logDisco = Log.getLogDisco();
        request.setAttribute("logDisco", logDisco);
        
        List<Produto> dadosBuffer = Dados.getDadosBuffer();
        request.setAttribute("dadosBuffer", dadosBuffer);
        List<Produto> dadosDisco = Dados.getDadosDisco();
        request.setAttribute("dadosDisco", dadosDisco);
        
        RequestDispatcher rd = request.getRequestDispatcher("programa.jsp");
        rd.forward(request, response);
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
