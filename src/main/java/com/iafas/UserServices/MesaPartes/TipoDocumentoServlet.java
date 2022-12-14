/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.UserServices.MesaPartes;

import com.iafas.BusinessServices.Beans.BeanTipoDocumento;
import com.iafas.BusinessServices.Beans.BeanUsuario;
import com.iafas.DataService.Despachadores.Impl.TipoDocumentoDAOImpl;
import com.iafas.DataService.Despachadores.TipoDocumentoDAO;
import com.iafas.Utiles.Utiles;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
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
 * @author H-TECCSI-V
 */
@WebServlet(name = "TipoDocumentoServlet", urlPatterns = {"/TipoDocumento"})
public class TipoDocumentoServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private List objTipoDocumento;
    private BeanTipoDocumento objBnTipoDocumento;
    private Connection objConnection;
    private TipoDocumentoDAO ObjDsTipoDocumento;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        config = this.getServletConfig();
        context = config.getServletContext();
        session = request.getSession();
        BeanUsuario objUsuario = (BeanUsuario) session.getAttribute("objUsuario" + session.getId());
        if (objUsuario == null) {
            dispatcher = request.getRequestDispatcher("../FinSession.jsp");
            dispatcher.forward(request, response);
        }
        objConnection = (Connection) context.getAttribute("objConnection");
        String result = null;
        objBnTipoDocumento = new BeanTipoDocumento();
        objBnTipoDocumento.setMode(request.getParameter("mode"));
        objBnTipoDocumento.setCodigo(Utiles.checkNum(request.getParameter("codigo")));
        ObjDsTipoDocumento = new TipoDocumentoDAOImpl(objConnection);
        // DE ACUERO AL MODO, OBTENEMOS LOS DATOS NECESARIOS.  
        if (objBnTipoDocumento.getMode().equals("G")) {
            objTipoDocumento = ObjDsTipoDocumento.getListaTipoDocumento(objBnTipoDocumento, objUsuario.getUsuario());
            if (request.getAttribute("objTipoDocumento") != null) {
                request.removeAttribute("objTipoDocumento");
            }
            request.setAttribute("objTipoDocumento", objTipoDocumento);
        }
        if (objBnTipoDocumento.getMode().equals("U")) {
            objBnTipoDocumento = ObjDsTipoDocumento.getTipoDocumento(objBnTipoDocumento, objUsuario.getUsuario());
            result = objBnTipoDocumento.getDescripcion() + "+++"
                    + objBnTipoDocumento.getAbreviatura();
        }
        //SE ENVIA DE ACUERDO AL MODO SELECCIONADO
        switch (request.getParameter("mode")) {
            case "tipoDocumento":
                dispatcher = request.getRequestDispatcher("MesaPartes/TipoDocumento.jsp");
                break;
            case "G":
                dispatcher = request.getRequestDispatcher("MesaPartes/ListaTipoDocumento.jsp");
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
