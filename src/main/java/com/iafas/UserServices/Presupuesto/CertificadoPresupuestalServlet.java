/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.UserServices.Presupuesto;

import com.iafas.BusinessServices.Beans.BeanEjecucionPresupuestal;
import com.iafas.BusinessServices.Beans.BeanUsuario;
import com.iafas.DataService.Despachadores.Impl.CertificadoPresupuestalDAOImpl;
import com.iafas.DataService.Despachadores.CertificadoPresupuestalDAO;
import com.iafas.DataService.Despachadores.CombosDAO;
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
@WebServlet(name = "CertificadoPresupuestalServlet", urlPatterns = {"/CertificadoPresupuestal"})
public class CertificadoPresupuestalServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanEjecucionPresupuestal objBnCertificado;
    private Connection objConnection;
    private CertificadoPresupuestalDAO objDsCertificado;
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
        objBnCertificado = new BeanEjecucionPresupuestal();
        objBnCertificado.setMode(request.getParameter("mode"));
        objBnCertificado.setPeriodo(request.getParameter("periodo"));
        objBnCertificado.setPresupuesto(Utiles.checkNum(request.getParameter("presupuesto")));
        objBnCertificado.setCertificado(request.getParameter("codigo"));
        objDsCertificado = new CertificadoPresupuestalDAOImpl(objConnection);
        // DE ACUERO AL MODO, OBTENEMOS LOS DATOS NECESARIOS.    
        if (objBnCertificado.getMode().equals("G")) {
            if (request.getAttribute("objCertificado") != null) {
                request.removeAttribute("objCertificado");
            }
            request.setAttribute("objCertificado", objDsCertificado.getListaCertificados(objBnCertificado, objUsuario.getUsuario()));
            if (request.getAttribute("objCertificadoDetalle") != null) {
                request.removeAttribute("objCertificadoDetalle");
            }
            request.setAttribute("objCertificadoDetalle", objDsCertificado.getListaCertificadosDetalle(objBnCertificado, objUsuario.getUsuario()));
            objDsCombo = new CombosDAOImpl(objConnection);
            if (request.getAttribute("objTipoMoneda") != null) {
                request.removeAttribute("objTipoMoneda");
            }
            request.setAttribute("objTipoMoneda", objDsCombo.getTipoMonedas());
        }
        if (objBnCertificado.getMode().equals("I") || objBnCertificado.getMode().equals("AM") || objBnCertificado.getMode().equals("RE")) {
            result = objDsCertificado.getNumeroCertificado(objBnCertificado, objUsuario.getUsuario());
        }
        if (objBnCertificado.getMode().equals("U")) {
            objBnCertificado = objDsCertificado.getCertificado(objBnCertificado, objUsuario.getUsuario());
            result = objBnCertificado.getCertificado() + "+++"
                    + objBnCertificado.getFecha() + "+++"
                    + objBnCertificado.getDocumentoReferencia() + "+++"
                    + objBnCertificado.getConcepto() + "+++"
                    + objBnCertificado.getObservacion() + "+++"
                    + objBnCertificado.getProcesoSeleccion() + "+++"
                    + objBnCertificado.getTipoMoneda() + "+++"
                    + objBnCertificado.getTipoCambio() + "+++"
                    + objBnCertificado.getMonedaExtranjera() + "+++"
                    + objBnCertificado.getDependencia();
        }
        if (objBnCertificado.getMode().equals("B")) {
            result = "" + objDsCertificado.getListaCertificadoDetalle(objBnCertificado, objUsuario.getUsuario());
        }
        //SE ENVIA DE ACUERDO AL MODO SELECCIONADO
        switch (objBnCertificado.getMode()) {
            case "G":
                dispatcher = request.getRequestDispatcher("Presupuesto/ListaCertificadoPresupuestal.jsp");
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
