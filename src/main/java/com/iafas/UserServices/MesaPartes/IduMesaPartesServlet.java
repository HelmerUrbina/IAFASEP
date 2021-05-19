/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.UserServices.MesaPartes;

import com.iafas.BusinessServices.Beans.BeanMesaParte;
import com.iafas.BusinessServices.Beans.BeanMsgerr;
import com.iafas.BusinessServices.Beans.BeanUsuario;
import com.iafas.DataService.Despachadores.Impl.MesaParteDAOImpl;
import com.iafas.DataService.Despachadores.Impl.MsgerrDAOImpl;
import com.iafas.DataService.Despachadores.MsgerrDAO;
import com.iafas.Utiles.Utiles;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import com.iafas.DataService.Despachadores.MesaParteDAO;

/**
 *
 * @author H-URBINA-M
 */
@WebServlet(name = "IduMesaPartesServlet", urlPatterns = {"/IduMesaPartes"})
//@MultipartConfig(location = "D:/IAFAS/MesaPartes/Documentos")
@MultipartConfig(location = "/IAFASEP/MesaPartes/Documentos",
        fileSizeThreshold = 1024 * 1024 * 10,       // 10 MB 
        maxFileSize = 1024 * 1024 * 500,            // 500 MB
        maxRequestSize = 1024 * 1024 * 1000)        // 1000 MB
public class IduMesaPartesServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanMesaParte objBnMesaParte;
    private Connection objConnection;
    private MesaParteDAO objDsMesaParte;
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
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.awt.print.PrinterException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, JRException, JRException, PrinterException {
        config = this.getServletConfig();
        context = config.getServletContext();
        session = request.getSession(true);
        String result = null;
        String k = "0";
        String resulDetalle = null;
        BeanUsuario objUsuario = (BeanUsuario) session.getAttribute("objUsuario" + session.getId());
        if (objUsuario == null) {
            dispatcher = request.getRequestDispatcher("/FinSession.jsp");
            dispatcher.forward(request, response);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); //No Complaciente en Fecha
        java.util.Date fecha_doc = sdf.parse(Utiles.checkFecha(request.getParameter("fechaDocumento")));
        objConnection = (Connection) context.getAttribute("objConnection");
        objBnMesaParte = new BeanMesaParte();
        objBnMesaParte.setMode(request.getParameter("mode"));
        objBnMesaParte.setPeriodo(request.getParameter("periodo"));
        objBnMesaParte.setTipo(request.getParameter("tipo"));
        objBnMesaParte.setNumero(request.getParameter("numero"));
        objBnMesaParte.setMes(request.getParameter("mes"));
        objBnMesaParte.setInstitucion(request.getParameter("institucion"));
        objBnMesaParte.setPrioridad(request.getParameter("prioridad"));
        objBnMesaParte.setDocumento(request.getParameter("documento"));
        objBnMesaParte.setNumeroDocumento(request.getParameter("numeroDocumento"));
        objBnMesaParte.setClasificacion(request.getParameter("clasificacion"));
        objBnMesaParte.setFecha(new java.sql.Date(fecha_doc.getTime()));
        objBnMesaParte.setAsunto(request.getParameter("asunto"));
        objBnMesaParte.setPostFirma(request.getParameter("postFirma"));
        objBnMesaParte.setLegajo(Utiles.checkNum(request.getParameter("legajos")));
        objBnMesaParte.setFolio(Utiles.checkNum(request.getParameter("folios")));
        objBnMesaParte.setArea(request.getParameter("area"));
        objBnMesaParte.setUsuarioResponsable(request.getParameter("usuario"));
        objBnMesaParte.setReferencia(request.getParameter("referencia"));
        objDsMesaParte = new MesaParteDAOImpl(objConnection);
        System.out.println(objBnMesaParte.getTipo() + " " + objBnMesaParte.getMode());
        if (objBnMesaParte.getTipo().equals("E") && !objBnMesaParte.getMode().equals("D")) {
            Collection<Part> parts = request.getParts();
            for (Part part : parts) {
                if (null != Utiles.getFileName(part)) {
                    objBnMesaParte.setArchivo(Utiles.stripAccents(Utiles.getFileName(part)));
                    part.write(objBnMesaParte.getPeriodo() + "-" + objBnMesaParte.getTipo() + "-" + objBnMesaParte.getNumero() + "-" + objBnMesaParte.getArchivo());
                }
            }
        } else {
            if (objBnMesaParte.getMode().equals("C") || objBnMesaParte.getTipo().equals("S")) {
                Collection<Part> parts = request.getParts();
                for (Part part : parts) {
                    if (null != Utiles.getFileName(part)) {
                        objBnMesaParte.setArchivo(Utiles.stripAccents(Utiles.getFileName(part)));
                        part.write(objBnMesaParte.getPeriodo() + "-" + objBnMesaParte.getTipo() + "-" + objBnMesaParte.getNumero() + "-" + objBnMesaParte.getArchivo());
                    }
                }
            }
        }
        k = "" + objDsMesaParte.iduMesaParte(objBnMesaParte, objUsuario.getUsuario());
        if (k.equals("0")) {
            // EN CASO DE HABER PROBLEMAS DESPACHAMOS UNA VENTANA DE ERROR, MOSTRANDO EL ERROR OCURRIDO.
            result = "ERROR";
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(objUsuario.getUsuario());
            objBnMsgerr.setTabla("IAFAS_MESA_PARTES");
            objBnMsgerr.setTipo(objBnMesaParte.getMode());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = objDsMsgerr.getMsgerr(objBnMsgerr);
            resulDetalle = objBnMsgerr.getDescripcion();
        }
        // EN CASO DE NO HABER PROBLEMAS RETORNAMOS UNA NUEVA CONSULTA CON TODOS LOS DATOS.
        if (result == null) {
            try (PrintWriter out = response.getWriter()) {
                out.print("GUARDO");
                if (objBnMesaParte.getMode().equals("I")) {
                    InputStream stream = context.getResourceAsStream("/Reportes/Documentos/MPA0003.jasper");
                    Map parametro = new HashMap();
                    parametro.put("REPORT_LOCALE", new Locale("en", "US"));
                    parametro.put("PERIODO", objBnMesaParte.getPeriodo());
                    parametro.put("CODIGO", objBnMesaParte.getNumero());
                    parametro.put("TIPO", objBnMesaParte.getTipo());
                    parametro.put("USUARIO", objUsuario.getUsuario());
                    parametro.put("SUBREPORT_DIR", "D:\\OPREFA\\Reportes");
                    JasperPrint reporte = JasperFillManager.fillReport(stream, parametro, objConnection);
                    /*  Utiles u = new Utiles();
                    u.printTicket(reporte);*/
                }
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
        } catch (ParseException | JRException | PrinterException ex) {
            Logger.getLogger(IduMesaPartesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException | JRException | PrinterException ex) {
            Logger.getLogger(IduMesaPartesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
