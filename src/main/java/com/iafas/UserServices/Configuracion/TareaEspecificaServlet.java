/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.UserServices.Configuracion;

import com.iafas.BusinessServices.Beans.BeanTareaEspecifica;
import com.iafas.BusinessServices.Beans.BeanUsuario;
import com.iafas.DataService.Despachadores.CombosDAO;
import com.iafas.DataService.Despachadores.Impl.CombosDAOImpl;
import com.iafas.DataService.Despachadores.Impl.TareaEspecificaDAOImpl;
import com.iafas.DataService.Despachadores.TareaEspecificaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author H-URBINA-M
 */
@WebServlet(name = "TareaEspecificaServlet", urlPatterns = {"/TareaEspecifica"})
public class TareaEspecificaServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanTareaEspecifica objBnTareaEspecifica;
    private Connection objConnection;
    private TareaEspecificaDAO objDsTareaEspecifica;
    private CombosDAO objDsCombos;

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
        config = this.getServletConfig();
        context = config.getServletContext();
        session = request.getSession();
        BeanUsuario objUsuario = (BeanUsuario) session.getAttribute("objUsuario" + session.getId());
        if (objUsuario == null) {
            dispatcher = request.getRequestDispatcher("/FinSession.jsp");
            dispatcher.forward(request, response);
        }
        objConnection = (Connection) context.getAttribute("objConnection");
        String result = null;
        objBnTareaEspecifica = new BeanTareaEspecifica();
        objBnTareaEspecifica.setMode(request.getParameter("mode"));
        objBnTareaEspecifica.setPeriodo(request.getParameter("periodo"));
        objBnTareaEspecifica.setCodigo(request.getParameter("codigo"));
        objDsTareaEspecifica = new TareaEspecificaDAOImpl(objConnection);
        // DE ACUERO AL MODO, OBTENEMOS LOS DATOS NECESARIOS.  
        if (objBnTareaEspecifica.getMode().equals("G")) {
            if (request.getAttribute("objTareaEspecifica") != null) {
                request.removeAttribute("objTareaEspecifica");
            }
            request.setAttribute("objTareaEspecifica", objDsTareaEspecifica.getListaTareasEspecificas(objBnTareaEspecifica, objUsuario.getUsuario()));
            objDsCombos = new CombosDAOImpl(objConnection);
            if (request.getAttribute("objTarea") != null) {
                request.removeAttribute("objTarea");
            }
            request.setAttribute("objTarea", objDsCombos.getTarea());
        }
        if (objBnTareaEspecifica.getMode().equals("M")) {
            result = "" + objDsTareaEspecifica.getCadenaGasto(objBnTareaEspecifica, objUsuario.getUsuario());
        }
        //SE ENVIA DE ACUERDO AL MODO SELECCIONADO
        switch (request.getParameter("mode")) {
            case "tareaEspecifica":
                dispatcher = request.getRequestDispatcher("Configuracion/TareaEspecifica.jsp");
                break;
            case "G":
                dispatcher = request.getRequestDispatcher("Configuracion/ListaTareaEspecifica.jsp");
                break;
            default:
                dispatcher = request.getRequestDispatcher("error.jsp");
                break;
        }
        if (result != null) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.print(result);
            }
        } else {
            dispatcher.forward(request, response);
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
