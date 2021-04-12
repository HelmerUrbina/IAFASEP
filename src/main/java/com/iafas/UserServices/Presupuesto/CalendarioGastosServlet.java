/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.UserServices.Presupuesto;

import com.iafas.BusinessServices.Beans.BeanCalendarioGastos;
import com.iafas.BusinessServices.Beans.BeanUsuario;
import com.iafas.DataService.Despachadores.CalendarioGastosDAO;
import com.iafas.DataService.Despachadores.CombosDAO;
import com.iafas.DataService.Despachadores.Impl.CalendarioGastosDAOImpl;
import com.iafas.DataService.Despachadores.Impl.CombosDAOImpl;
import com.iafas.Utiles.Utiles;
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
@WebServlet(name = "CalendarioGastosServlet", urlPatterns = {"/CalendarioGastos"})
public class CalendarioGastosServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanCalendarioGastos objBnCalendarioGastos;
    private Connection objConnection;
    private CalendarioGastosDAO objDsCalendarioGastos;
    private CombosDAO objDsCombo;

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
        String result = null;
        // VERIFICAMOS LA SESSION DE LA SOLICITUD DE CREDITO
        BeanUsuario objUsuario = (BeanUsuario) session.getAttribute("objUsuario" + session.getId());
        if (objUsuario == null) {
            dispatcher = request.getRequestDispatcher("FinSession.jsp");
            dispatcher.forward(request, response);
        }
        objConnection = (Connection) context.getAttribute("objConnection");
        objBnCalendarioGastos = new BeanCalendarioGastos();
        objBnCalendarioGastos.setMode(request.getParameter("mode"));
        objBnCalendarioGastos.setPeriodo(request.getParameter("periodo"));
        objBnCalendarioGastos.setPresupuesto(Utiles.checkNum(request.getParameter("presupuesto")));
        objBnCalendarioGastos.setCodigo(request.getParameter("codigo"));
        objDsCalendarioGastos = new CalendarioGastosDAOImpl(objConnection);
        // DE ACUERO AL MODO, OBTENEMOS LOS DATOS NECESARIOS.
        if (objBnCalendarioGastos.getMode().equals("G")) {
            if (request.getAttribute("objCalendarioGastos") != null) {
                request.removeAttribute("objCalendarioGastos");
            }
            if (request.getAttribute("objCalendarioGastosDetalle") != null) {
                request.removeAttribute("objCalendarioGastosDetalle");
            }
            request.setAttribute("objCalendarioGastos", objDsCalendarioGastos.getListaCalendarioGastos(objBnCalendarioGastos));
            request.setAttribute("objCalendarioGastosDetalle", objDsCalendarioGastos.getListaCalendarioGastosDetalle(objBnCalendarioGastos));
            objDsCombo = new CombosDAOImpl(objConnection);
            if (request.getAttribute("objTareasPresupuestales") != null) {
                request.removeAttribute("objTareasPresupuestales");
            }
            request.setAttribute("objTareasPresupuestales", objDsCombo.getTarea());
        }
        if (objBnCalendarioGastos.getMode().equals("B")) {
            result = "" + objDsCalendarioGastos.getListaCalendarioGastoDetalle(objBnCalendarioGastos);
        }
        //SE ENVIA DE ACUERDO AL MODO SELECCIONADO
        switch (request.getParameter("mode")) {
            case "calendarioGastos":
                dispatcher = request.getRequestDispatcher("Presupuesto/CalendarioGastos.jsp");
                break;
            case "G":
                dispatcher = request.getRequestDispatcher("Presupuesto/ListaCalendarioGastos.jsp");
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
