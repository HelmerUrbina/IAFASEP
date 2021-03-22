/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.UserServices.Logistica;

import com.iafas.BusinessServices.Beans.BeanMsgerr;
import com.iafas.BusinessServices.Beans.BeanPACProcesos;
import com.iafas.BusinessServices.Beans.BeanUsuario;
import com.iafas.DataService.Despachadores.Impl.MsgerrDAOImpl;
import com.iafas.DataService.Despachadores.Impl.PACProcesosDAOImpl;
import com.iafas.DataService.Despachadores.MsgerrDAO;
import com.iafas.DataService.Despachadores.PACProcesosDAO;
import com.iafas.Utiles.Utiles;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "IduPACProcesosServlet", urlPatterns = {"/IduPACProcesos"})
public class IduPACProcesosServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanPACProcesos objBnPACProcesos;
    private Connection objConnection;
    private PACProcesosDAO objDsPACProcesos;
    private BeanMsgerr objBnMsgerr = null;
    private MsgerrDAO objDsMsgerr;

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
        session = request.getSession();
        BeanUsuario objUsuario = (BeanUsuario) session.getAttribute("objUsuario" + session.getId());
        if (objUsuario == null) {
            dispatcher = request.getRequestDispatcher("/FinSession.jsp");
            dispatcher.forward(request, response);
        }
        objConnection = (Connection) context.getAttribute("objConnection");
        String result = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); //No Complaciente en Fecha  
        java.util.Date convocatoria_util = sdf.parse(Utiles.checkFecha(request.getParameter("convocatoria")));
        sdf.setLenient(false); //No Complaciente en Fecha  
        java.util.Date participantes_util = sdf.parse(Utiles.checkFecha(request.getParameter("participantes")));
        sdf.setLenient(false); //No Complaciente en Fecha  
        java.util.Date observaciones_util = sdf.parse(Utiles.checkFecha(request.getParameter("observaciones")));
        sdf.setLenient(false); //No Complaciente en Fecha  
        java.util.Date absolucion_util = sdf.parse(Utiles.checkFecha(request.getParameter("absolucion")));
        sdf.setLenient(false); //No Complaciente en Fecha  
        java.util.Date integracion_util = sdf.parse(Utiles.checkFecha(request.getParameter("integracion")));
        sdf.setLenient(false); //No Complaciente en Fecha  
        java.util.Date ofertas_util = sdf.parse(Utiles.checkFecha(request.getParameter("ofertas")));
        sdf.setLenient(false); //No Complaciente en Fecha  
        java.util.Date evaluacion_util = sdf.parse(Utiles.checkFecha(request.getParameter("evaluacion")));
        sdf.setLenient(false); //No Complaciente en Fecha  
        java.util.Date buenaPro_util = sdf.parse(Utiles.checkFecha(request.getParameter("buenaPro")));
        sdf.setLenient(false); //No Complaciente en Fecha  
        java.util.Date consentimiento_util = sdf.parse(Utiles.checkFecha(request.getParameter("consentimiento")));
        sdf.setLenient(false); //No Complaciente en Fecha  
        java.util.Date contrato_util = sdf.parse(Utiles.checkFecha(request.getParameter("contrato")));
        objBnPACProcesos = new BeanPACProcesos();
        objBnPACProcesos.setMode(request.getParameter("mode"));
        objBnPACProcesos.setPeriodo(request.getParameter("periodo"));
        objBnPACProcesos.setPresupuesto(Utiles.checkNum(request.getParameter("presupuesto")));
        objBnPACProcesos.setCodigo(Utiles.checkNum(request.getParameter("codigo")));
        objBnPACProcesos.setNumeroPAAC(request.getParameter("numeroPAAC"));
        objBnPACProcesos.setTipoProcesoContratacion(request.getParameter("tipoContratacion"));
        objBnPACProcesos.setProcesoEtapa(request.getParameter("procesoEtapa"));
        objBnPACProcesos.setProcesoDocumento(request.getParameter("procesoDocumento"));
        objBnPACProcesos.setTipoProcedimiento(request.getParameter("tipoProcedimiento"));
        objBnPACProcesos.setDescripcion(request.getParameter("descripcion"));
        objBnPACProcesos.setNumeroProceso(request.getParameter("numeroProceso"));
        objBnPACProcesos.setCompras(request.getParameter("compras"));
        objBnPACProcesos.setCertificado(request.getParameter("certificado"));
        objBnPACProcesos.setMontoProceso(Utiles.checkDouble(request.getParameter("montoProceso")));
        objBnPACProcesos.setConvocatoria(new java.sql.Date(convocatoria_util.getTime()));
        objBnPACProcesos.setParticipantes(new java.sql.Date(participantes_util.getTime()));
        objBnPACProcesos.setObservaciones(new java.sql.Date(observaciones_util.getTime()));
        objBnPACProcesos.setAbsolucion(new java.sql.Date(absolucion_util.getTime()));
        objBnPACProcesos.setIntegracion(new java.sql.Date(integracion_util.getTime()));
        objBnPACProcesos.setOfertas(new java.sql.Date(ofertas_util.getTime()));
        objBnPACProcesos.setEvaluacion(new java.sql.Date(evaluacion_util.getTime()));
        objBnPACProcesos.setBuenaPro(new java.sql.Date(buenaPro_util.getTime()));
        objBnPACProcesos.setConsentimiento(new java.sql.Date(consentimiento_util.getTime()));
        objBnPACProcesos.setContrato(new java.sql.Date(contrato_util.getTime()));
        objDsPACProcesos = new PACProcesosDAOImpl(objConnection);
        // EJECUTAMOS EL PROCEDIMIENTO SEGUN EL MODO QUE SE ESTA TRABAJANDO
        int k = 0;
        if (objBnPACProcesos.getMode().equals("A")) {
            String arreglo[][] = Utiles.generaLista(request.getParameter("lista"), 2);
            for (String[] item : arreglo) {
                objBnPACProcesos.setCodigo(Utiles.checkNum(item[0].trim()));
                if (item[1].trim().equals("true")) {
                    objBnPACProcesos.setMode("A");
                } else {
                    objBnPACProcesos.setMode("R");
                }
                k = objDsPACProcesos.iduPACProcesos(objBnPACProcesos, objUsuario.getUsuario());
            }
        } else {
            k = objDsPACProcesos.iduPACProcesos(objBnPACProcesos, objUsuario.getUsuario());
        }
        System.out.println(k+" "+result);
        if (k != 0) {
        } else {
            // EN CASO DE HABER PROBLEMAS DESPACHAMOS UNA VENTANA DE ERROR, MOSTRANDO EL ERROR OCURRIDO.
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(objUsuario.getUsuario());
            objBnMsgerr.setTabla("IAFAS_PAC_PROCESOS");
            objBnMsgerr.setTipo(objBnPACProcesos.getMode());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = objDsMsgerr.getMsgerr(objBnMsgerr);
            result = objBnMsgerr.getDescripcion();
        }
        System.out.println(k+" "+result);
        // EN CASO DE NO HABER PROBLEMAS RETORNAMOS UNA NUEVA CONSULTA CON TODOS LOS DATOS.
        response.setContentType("text/html;charset=UTF-8");
        if (result == null) {
            try (PrintWriter out = response.getWriter()) {
                out.print("GUARDO");
            }
        } else {
            //PROCEDEMOS A ELIMINAR TODO;
            try (PrintWriter out = response.getWriter()) {
                out.print(result);
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
            Logger.getLogger(IduPACProcesosServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IduPACProcesosServlet.class.getName()).log(Level.SEVERE, null, ex);
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
