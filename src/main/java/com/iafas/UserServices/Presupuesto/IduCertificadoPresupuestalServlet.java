/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.UserServices.Presupuesto;

import com.iafas.BusinessServices.Beans.BeanEjecucionPresupuestal;
import com.iafas.BusinessServices.Beans.BeanMsgerr;
import com.iafas.BusinessServices.Beans.BeanUsuario;
import com.iafas.DataService.Despachadores.CertificadoPresupuestalDAO;
import com.iafas.DataService.Despachadores.Impl.CertificadoPresupuestalDAOImpl;
import com.iafas.DataService.Despachadores.Impl.MsgerrDAOImpl;
import com.iafas.DataService.Despachadores.MsgerrDAO;
import com.iafas.Utiles.Utiles;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author H-URBINA-M
 */
@WebServlet(name = "IduCertificadoPresupuestalServlet", urlPatterns = {"/IduCertificadoPresupuestal"})
//@MultipartConfig(location = "D:/IAFAS/Presupuesto/CertificadoPresupuestal")
@MultipartConfig(location = "/IAFASEP/Presupuesto/CertificadoPresupuestal")
public class IduCertificadoPresupuestalServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanEjecucionPresupuestal objBnSolicitud;
    private Connection objConnection;
    private CertificadoPresupuestalDAO objDsSolicitud;
    private BeanMsgerr objBnMsgerr = null;
    private MsgerrDAO objDsMsgerr;
    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        config = this.getServletConfig();
        context = config.getServletContext();
        session = request.getSession(true);
        String result = null;
        String resulDetalle = null;
        // VERIFICAMOS LA SESSION DE LA SOLICITUD DE CREDITO
        BeanUsuario objUsuario = (BeanUsuario) session.getAttribute("objUsuario" + session.getId());
        if (objUsuario == null) {
            dispatcher = request.getRequestDispatcher("FinSession.jsp");
            dispatcher.forward(request, response);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); //No Complaciente en Fecha
        java.util.Date fecha_util = sdf.parse(Utiles.checkFecha(request.getParameter("fecha")));
        objConnection = (Connection) context.getAttribute("objConnection");
        objBnSolicitud = new BeanEjecucionPresupuestal();
        objBnSolicitud.setMode(request.getParameter("mode"));
        objBnSolicitud.setPeriodo(request.getParameter("periodo"));
        objBnSolicitud.setPresupuesto(Utiles.checkNum(request.getParameter("presupuesto")));
        objBnSolicitud.setCertificado(request.getParameter("certificado"));
        objBnSolicitud.setAnexoCertificado(request.getParameter("solicitudCredito"));
        objBnSolicitud.setDocumentoReferencia(request.getParameter("documentoReferencia"));
        objBnSolicitud.setConcepto(request.getParameter("detalle"));
        objBnSolicitud.setObservacion(request.getParameter("observacion"));
        objBnSolicitud.setFecha(new java.sql.Date(fecha_util.getTime()));
        objBnSolicitud.setImporte(Utiles.checkDouble(request.getParameter("importe")));
        objBnSolicitud.setTipoMoneda(request.getParameter("tipoMoneda"));
        objBnSolicitud.setTipoCambio(Utiles.checkDouble(request.getParameter("tipoCambio")));
        objBnSolicitud.setMonedaExtranjera(Utiles.checkDouble(request.getParameter("monedaExtranjera")));
        objBnSolicitud.setTipo(request.getParameter("tipoSolicitud"));
        objBnSolicitud.setDisponibilidadPresupuestal(request.getParameter("informeDisponibilidad"));
        objBnSolicitud.setProcesoSeleccion(request.getParameter("paac"));
        objDsSolicitud = new CertificadoPresupuestalDAOImpl(objConnection);
        String k = "0";
        // EJECUTAMOS EL PROCEDIMIENTO SEGUN EL MODO QUE SE ESTA TRABAJANDO
        switch (objBnSolicitud.getMode()) {
            case "D":
                k = objDsSolicitud.iduCertificado(objBnSolicitud, objUsuario.getUsuario());
                break;
            case "C": {
                response.setContentType("text/html;charset=UTF-8");
                Collection<Part> parts = request.getParts();
                for (Part part : parts) {
                    if (null != Utiles.getFileName(part)) {
                        objBnSolicitud.setDocumentoReferencia(Utiles.stripAccents(Utiles.getFileName(part)));
                        part.write(objBnSolicitud.getPeriodo() + "-" + objBnSolicitud.getPresupuesto() +"-" + objBnSolicitud.getCertificado()+ "-" + objBnSolicitud.getDocumentoReferencia());
                    }
                }
                k = objDsSolicitud.iduCertificado(objBnSolicitud, objUsuario.getUsuario());
                break;
            }
            default:
                k = objDsSolicitud.iduCertificado(objBnSolicitud, objUsuario.getUsuario());
                if (objBnSolicitud.getMode().equals("I")) {
                    objBnSolicitud.setCertificado(k);
                }
                if (!k.equals("0")) {
                    objBnSolicitud.setCorrelativo(0);
                    objBnSolicitud.setResolucion("0");
                    objBnSolicitud.setMode("D");
                    k = "" + objDsSolicitud.iduCertificadoDetalle(objBnSolicitud, objUsuario.getUsuario());
                    String lista[][] = Utiles.generaLista(request.getParameter("lista"), 6);
                    for (String[] item : lista) {
                        objBnSolicitud.setMode("I");
                        objBnSolicitud.setResolucion(item[1]);
                        objBnSolicitud.setTareaPresupuestal(item[2]);
                        objBnSolicitud.setCadenaGasto(item[3]);
                        objBnSolicitud.setImporte(Utiles.checkDouble(item[4]));
                        objBnSolicitud.setMonedaExtranjera(Utiles.checkDouble(item[5]));
                        k = "" + objDsSolicitud.iduCertificadoDetalle(objBnSolicitud, objUsuario.getUsuario());
                    }
                    if (k.equals("0")) {
                        result = "ERROR";
                        objBnMsgerr = new BeanMsgerr();
                        objBnMsgerr.setUsuario(objUsuario.getUsuario());
                        objBnMsgerr.setTabla("IAFAS_CERTIFICADO_PRESUPUESTAL");
                        objBnMsgerr.setTipo(objBnSolicitud.getMode());
                        objDsMsgerr = new MsgerrDAOImpl(objConnection);
                        objBnMsgerr = objDsMsgerr.getMsgerr(objBnMsgerr);
                        resulDetalle = objBnMsgerr.getDescripcion();
                    }
                }
                break;
        }
        // EN CASO DE HABER PROBLEMAS DESPACHAMOS UNA VENTANA DE ERROR, MOSTRANDO EL ERROR OCURRIDO.
        if (k.equals("0")) {
            result = "ERROR";
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(objUsuario.getUsuario());
            objBnMsgerr.setTabla("IAFAS_CERTIFICADO_PRESUPUESTAL");
            objBnMsgerr.setTipo(objBnSolicitud.getMode());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = objDsMsgerr.getMsgerr(objBnMsgerr);
            resulDetalle = objBnMsgerr.getDescripcion();
        }
        // EN CASO DE NO HABER PROBLEMAS RETORNAMOS UNA NUEVA CONSULTA CON TODOS LOS DATOS.
        response.setContentType("text/html;charset=UTF-8");
        if (result == null) {
            try (PrintWriter out = response.getWriter()) {
                out.print("GUARDO");
            }
        } else {
            //PROCEDEMOS A ELIMINAR TODO;
            try (PrintWriter out = response.getWriter()) {
                out.print(resulDetalle);
            }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(IduCertificadoPresupuestalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(IduCertificadoPresupuestalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
