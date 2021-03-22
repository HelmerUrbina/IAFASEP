/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.UserServices.Presupuesto;

import com.iafas.BusinessServices.Beans.BeanNotaModificatoria;
import com.iafas.BusinessServices.Beans.BeanUsuario;
import com.iafas.DataService.Despachadores.NotaModificatoriaDAO;
import com.iafas.DataService.Despachadores.Impl.NotaModificatoriaDAOImpl;
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
@WebServlet(name = "NotaModificatoriaServlet", urlPatterns = {"/NotaModificatoria"})
public class NotaModificatoriaServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanNotaModificatoria objBnNotaModificatoria;
    private Connection objConnection;
    private NotaModificatoriaDAO objDsNotaModificatoria;

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
        objBnNotaModificatoria = new BeanNotaModificatoria();
        objBnNotaModificatoria.setMode(request.getParameter("mode"));
        objBnNotaModificatoria.setPeriodo(request.getParameter("periodo"));
        objBnNotaModificatoria.setMes(request.getParameter("mes"));
        objBnNotaModificatoria.setCodigo(request.getParameter("codigo"));
        objDsNotaModificatoria = new NotaModificatoriaDAOImpl(objConnection);
        // DE ACUERO AL MODO, OBTENEMOS LOS DATOS NECESARIOS.
        if (objBnNotaModificatoria.getMode().equals("G")) {
            if (request.getAttribute("objNotaModificatoria") != null) {
                request.removeAttribute("objNotaModificatoria");
            }
            request.setAttribute("objNotaModificatoria", objDsNotaModificatoria.getListaNotasModificatorias(objBnNotaModificatoria, objUsuario.getUsuario()));
            if (request.getAttribute("objNotaModificatoriaDetalle") != null) {
                request.removeAttribute("objNotaModificatoriaDetalle");
            }
            request.setAttribute("objNotaModificatoriaDetalle", objDsNotaModificatoria.getListaNotasModificatoriasDetalle(objBnNotaModificatoria, objUsuario.getUsuario()));
        }
        if (objBnNotaModificatoria.getMode().equals("U")) {
            objBnNotaModificatoria = objDsNotaModificatoria.getNotaModificatoria(objBnNotaModificatoria, objUsuario.getUsuario());
            result = objBnNotaModificatoria.getTipo() + "+++"
                    + objBnNotaModificatoria.getJustificacion() + "+++"
                    + objBnNotaModificatoria.getFecha() ;
        }
        if (objBnNotaModificatoria.getMode().equals("B")) {
            result = "" + objDsNotaModificatoria.getListaNotaModificatoriaDetalle(objBnNotaModificatoria, objUsuario.getUsuario());
        }
        //SE ENVIA DE ACUERDO AL MODO SELECCIONADO
        switch (objBnNotaModificatoria.getMode()) {
            case "notaModificatoria":
                dispatcher = request.getRequestDispatcher("Presupuesto/NotaModificatoria.jsp");
                break;
            case "G":
                dispatcher = request.getRequestDispatcher("Presupuesto/ListaNotaModificatoria.jsp");
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
