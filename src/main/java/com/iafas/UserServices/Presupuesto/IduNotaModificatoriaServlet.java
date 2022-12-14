/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.UserServices.Presupuesto;

import com.iafas.BusinessServices.Beans.BeanNotaModificatoria;
import com.iafas.BusinessServices.Beans.BeanMsgerr;
import com.iafas.BusinessServices.Beans.BeanUsuario;
import com.iafas.DataService.Despachadores.NotaModificatoriaDAO;
import com.iafas.DataService.Despachadores.Impl.NotaModificatoriaDAOImpl;
import com.iafas.DataService.Despachadores.Impl.MsgerrDAOImpl;
import com.iafas.DataService.Despachadores.MsgerrDAO;
import com.iafas.Utiles.Utiles;
import java.io.File;
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
@WebServlet(name = "IduNotaModificatoriaServlet", urlPatterns = {"/IduNotaModificatoria"})
//@MultipartConfig(location = "D:/IAFASEP/Presupuesto/NotasModificatorias")
@MultipartConfig(location = "/IAFASEP/Presupuesto/NotasModificatorias")
public class IduNotaModificatoriaServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanNotaModificatoria objBnNotaModificatoria;
    private Connection objConnection;
    private NotaModificatoriaDAO objDsNotaModificatoria;
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
        objBnNotaModificatoria = new BeanNotaModificatoria();
        objBnNotaModificatoria.setMode(request.getParameter("mode"));
        objBnNotaModificatoria.setPeriodo(request.getParameter("periodo"));
        objBnNotaModificatoria.setMes(request.getParameter("mes"));
        objBnNotaModificatoria.setCodigo(request.getParameter("codigo"));
        objBnNotaModificatoria.setTipo(request.getParameter("tipo"));
        objBnNotaModificatoria.setFecha(new java.sql.Date(fecha_util.getTime()));
        objBnNotaModificatoria.setJustificacion(request.getParameter("motivo"));
        objDsNotaModificatoria = new NotaModificatoriaDAOImpl(objConnection);
        int k = 0;
        // EJECUTAMOS EL PROCEDIMIENTO SEGUN EL MODO QUE SE ESTA TRABAJANDO
        switch (objBnNotaModificatoria.getMode()) {
            case "D":
                k = objDsNotaModificatoria.iduNotaModificatoria(objBnNotaModificatoria, objUsuario.getUsuario());
                break;
            case "C":
                response.setContentType("text/html;charset=UTF-8");
                Collection<Part> parts = request.getParts();
                for (Part part : parts) {
                    if (null != Utiles.getFileName(part)) {
                        objBnNotaModificatoria.setJustificacion(Utiles.getFileName(part));
                        //File fichero = new File("D:/IAFASEP/Presupuesto/NotasModificatorias/" + objBnNotaModificatoria.getPeriodo() + "-" + objBnNotaModificatoria.getCodigo() + "-" + objBnNotaModificatoria.getJustificacion().toUpperCase());
                        File fichero = new File("/IAFASEP/Presupuesto/NotasModificatorias/" + objBnNotaModificatoria.getPeriodo() + "-" + objBnNotaModificatoria.getCodigo() + "-" + objBnNotaModificatoria.getJustificacion().toUpperCase());
                        if (fichero.exists()) {
                            fichero.delete();
                        }
                        part.write(objBnNotaModificatoria.getPeriodo() + "-" + objBnNotaModificatoria.getCodigo() + "-" + objBnNotaModificatoria.getJustificacion().toUpperCase());
                    }
                }
                k = objDsNotaModificatoria.iduNotaModificatoriaVerifica(objBnNotaModificatoria, objUsuario.getUsuario());
                break;
            case "V":
                k = objDsNotaModificatoria.iduNotaModificatoriaVerifica(objBnNotaModificatoria, objUsuario.getUsuario());
                break;
            case "R":
                k = objDsNotaModificatoria.iduNotaModificatoriaVerifica(objBnNotaModificatoria, objUsuario.getUsuario());
                break;
            case "A":
                k = objDsNotaModificatoria.iduNotaModificatoriaVerifica(objBnNotaModificatoria, objUsuario.getUsuario());
                break;
            default:
                if (objBnNotaModificatoria.getMode().equals("I")) {
                    objBnNotaModificatoria.setCodigo(objDsNotaModificatoria.getNumeroNotaModificatoria(objBnNotaModificatoria, objUsuario.getUsuario()));
                }
                k = objDsNotaModificatoria.iduNotaModificatoria(objBnNotaModificatoria, objUsuario.getUsuario());
                if (k != 0) {
                    String lista[][] = Utiles.generaLista(request.getParameter("lista"), 8);
                    objBnNotaModificatoria.setMode("D");
                    objBnNotaModificatoria.setDetalle(0);
                    objBnNotaModificatoria.setResolucion(0);
                    objBnNotaModificatoria.setImporte(0.0);
                    k = objDsNotaModificatoria.iduNotaModificatoriaDetalle(objBnNotaModificatoria, objUsuario.getUsuario());
                    for (String[] item : lista) {
                        objBnNotaModificatoria.setMode("I");
                        objBnNotaModificatoria.setDetalle(Utiles.checkNum(item[0].trim()) + 1);
                        objBnNotaModificatoria.setTipo(item[1].trim());
                        objBnNotaModificatoria.setPresupuesto(item[2].trim());
                        objBnNotaModificatoria.setResolucion(Utiles.checkNum(item[3].trim()));
                        objBnNotaModificatoria.setTarea(item[4].trim());
                        objBnNotaModificatoria.setCadenaGasto(item[5].trim());
                        objBnNotaModificatoria.setImporte(Utiles.checkDouble(item[6]));
                        objBnNotaModificatoria.setJustificacion(item[7].trim());
                        k = objDsNotaModificatoria.iduNotaModificatoriaDetalle(objBnNotaModificatoria, objUsuario.getUsuario());
                    }
                }
                break;
        }
        // EN CASO DE HABER PROBLEMAS DESPACHAMOS UNA VENTANA DE ERROR, MOSTRANDO EL ERROR OCURRIDO.
        if (k == 0 || result != null) {
            result = "ERROR";
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(objUsuario.getUsuario());
            objBnMsgerr.setTabla("IAFAS_NOTAS_MODIFICATORIAS");
            objBnMsgerr.setTipo(objBnNotaModificatoria.getMode());
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
            Logger.getLogger(IduNotaModificatoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IduNotaModificatoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
