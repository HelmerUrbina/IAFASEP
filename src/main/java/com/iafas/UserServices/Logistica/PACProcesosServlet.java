/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.UserServices.Logistica;

import com.iafas.BusinessServices.Beans.BeanPACProcesos;
import com.iafas.BusinessServices.Beans.BeanUsuario;
import com.iafas.DataService.Despachadores.CombosDAO;
import com.iafas.DataService.Despachadores.Impl.CombosDAOImpl;
import com.iafas.DataService.Despachadores.Impl.PACProcesosDAOImpl;
import com.iafas.DataService.Despachadores.PACProcesosDAO;
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
@WebServlet(name = "PACProcesosServlet", urlPatterns = {"/PACProcesos"})
public class PACProcesosServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanPACProcesos objBnPACProcesos;
    private Connection objConnection;
    private PACProcesosDAO objDsPACProcesos;

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
        //VERIFICAMOS LA SESSION DEL USUARIO
        if (objUsuario == null) {
            dispatcher = request.getRequestDispatcher("/FinSession.jsp");
            dispatcher.forward(request, response);
        }
        objConnection = (Connection) context.getAttribute("objConnection");
        String result = null;
        objBnPACProcesos = new BeanPACProcesos();
        objBnPACProcesos.setMode(request.getParameter("mode"));
        objBnPACProcesos.setPeriodo(request.getParameter("periodo"));
        objBnPACProcesos.setPresupuesto(Utiles.checkNum(request.getParameter("presupuesto")));
        objBnPACProcesos.setCodigo(Utiles.checkNum(request.getParameter("codigo")));
        objBnPACProcesos.setDetalle(Utiles.checkNum(request.getParameter("detalle")));
        objDsPACProcesos = new PACProcesosDAOImpl(objConnection);
        // DE ACUERO AL MODO, OBTENEMOS LOS DATOS NECESARIOS.          
        if (objBnPACProcesos.getMode().equals("G")) {
            if (request.getAttribute("objPACProcesos") != null) {
                request.removeAttribute("objPACProcesos");
            }
            request.setAttribute("objPACProcesos", objDsPACProcesos.getListaPACProcesos(objBnPACProcesos, objUsuario.getUsuario()));
            CombosDAO objCombos = new CombosDAOImpl(objConnection);
            if (request.getAttribute("objTipoProcesoContratacion") != null) {
                request.removeAttribute("objTipoProcesoContratacion");
            }
            request.setAttribute("objTipoProcesoContratacion", objCombos.getTipoProcesoContratacion());
            if (request.getAttribute("objProcesoDocumento") != null) {
                request.removeAttribute("objProcesoDocumento");
            }
            request.setAttribute("objProcesoDocumento", objCombos.getProcesoDocumento());
        }
        if (objBnPACProcesos.getMode().equals("U")) {
            objBnPACProcesos = objDsPACProcesos.getPACProcesos(objBnPACProcesos, objUsuario.getUsuario());
            result = objBnPACProcesos.getNumeroPAAC() + "+++"
                    + objBnPACProcesos.getTipoProcesoContratacion() + "+++"
                    + objBnPACProcesos.getProcesoEtapa() + "+++"
                    + objBnPACProcesos.getProcesoDocumento() + "+++"
                    + objBnPACProcesos.getTipoProcedimiento() + "+++"
                    + objBnPACProcesos.getNumeroProceso() + "+++"
                    + objBnPACProcesos.getDescripcion() + "+++"
                    + objBnPACProcesos.getMontoProceso() + "+++"
                    + objBnPACProcesos.getConvocatoria() + "+++"
                    + objBnPACProcesos.getParticipantes() + "+++"
                    + objBnPACProcesos.getObservaciones() + "+++"
                    + objBnPACProcesos.getAbsolucion() + "+++"
                    + objBnPACProcesos.getIntegracion() + "+++"
                    + objBnPACProcesos.getOfertas() + "+++"
                    + objBnPACProcesos.getEvaluacion() + "+++"
                    + objBnPACProcesos.getBuenaPro() + "+++"
                    + objBnPACProcesos.getConsentimiento() + "+++"
                    + objBnPACProcesos.getContrato() + "+++"
                    + objBnPACProcesos.getCompras();
        }
        if (objBnPACProcesos.getMode().equals("M")) {
            objBnPACProcesos = objDsPACProcesos.getPACProcesosDetalle(objBnPACProcesos, objUsuario.getUsuario());
            result = objBnPACProcesos.getCertificado() + "+++"
                    + objBnPACProcesos.getNumeroContrato() + "+++"
                    + objBnPACProcesos.getMontoContrato() + "+++"
                    + objBnPACProcesos.getFechaInicio() + "+++"
                    + objBnPACProcesos.getFechaFin() + "+++"
                    + objBnPACProcesos.getAcumulado() + "+++"
                    + objBnPACProcesos.getPendiente() + "+++"
                    + objBnPACProcesos.getEnero() + "+++"
                    + objBnPACProcesos.getFebrero() + "+++"
                    + objBnPACProcesos.getMarzo() + "+++"
                    + objBnPACProcesos.getAbril() + "+++"
                    + objBnPACProcesos.getMayo() + "+++"
                    + objBnPACProcesos.getJunio() + "+++"
                    + objBnPACProcesos.getJulio() + "+++"
                    + objBnPACProcesos.getAgosto() + "+++"
                    + objBnPACProcesos.getSetiembre() + "+++"
                    + objBnPACProcesos.getOctubre() + "+++"
                    + objBnPACProcesos.getNoviembre() + "+++"
                    + objBnPACProcesos.getDiciembre();
        }
        if (objBnPACProcesos.getMode().equals("B")) {
            result = "" + objDsPACProcesos.getListaPACProcesosDetalle(objBnPACProcesos, objUsuario.getUsuario());
        }
        if (objBnPACProcesos.getMode().equals("C")) {
            result = "" + objDsPACProcesos.getListaPACProcesosAfectacion(objBnPACProcesos, objUsuario.getUsuario());
        }
        //SE ENVIA DE ACUERDO AL MODO SELECCIONADO
        switch (request.getParameter("mode")) {
            case "pacProcesos":
                dispatcher = request.getRequestDispatcher("Logistica/PACProcesos.jsp");
                break;
            case "G":
                dispatcher = request.getRequestDispatcher("Logistica/ListaPACProcesos.jsp");
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
