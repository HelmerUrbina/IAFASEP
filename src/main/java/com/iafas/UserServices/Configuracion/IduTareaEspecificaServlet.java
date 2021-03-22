/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.UserServices.Configuracion;

import com.iafas.BusinessServices.Beans.BeanMsgerr;
import com.iafas.BusinessServices.Beans.BeanTareaEspecifica;
import com.iafas.BusinessServices.Beans.BeanUsuario;
import com.iafas.DataService.Despachadores.Impl.TareaEspecificaDAOImpl;
import com.iafas.DataService.Despachadores.Impl.MsgerrDAOImpl;
import com.iafas.DataService.Despachadores.MsgerrDAO;
import com.iafas.DataService.Despachadores.TareaEspecificaDAO;
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
@WebServlet(name = "IduTareaEspecificaServlet", urlPatterns = {"/IduTareaEspecifica"})
public class IduTareaEspecificaServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanTareaEspecifica objBnTareaEspecifica;
    private Connection objConnection;
    private TareaEspecificaDAO objDsTareaEspecifica;
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
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        config = this.getServletConfig();
        context = config.getServletContext();
        session = request.getSession();
        BeanUsuario objUsuario = (BeanUsuario) session.getAttribute("objUsuario" + session.getId());
        if (objUsuario == null && !request.getParameter("mode").equals("I")) {
            dispatcher = request.getRequestDispatcher("/FinSession.jsp");
            dispatcher.forward(request, response);
        }
        objConnection = (Connection) context.getAttribute("objConnection");
        String result = null;
        objBnTareaEspecifica = new BeanTareaEspecifica();
        objBnTareaEspecifica.setMode(request.getParameter("mode"));
        objBnTareaEspecifica.setCodigo(request.getParameter("codigo"));
        objBnTareaEspecifica.setPeriodo(request.getParameter("periodo"));
        objBnTareaEspecifica.setTarea(request.getParameter("tarea"));
        objDsTareaEspecifica = new TareaEspecificaDAOImpl(objConnection);
        int k = 0;
        // EJECUTAMOS EL PROCEDIMIENTO SEGUN EL MODO QUE SE ESTA TRABAJANDO   
        if (objBnTareaEspecifica.getMode().equals("D")) {
            objBnTareaEspecifica.setTarea(objBnTareaEspecifica.getCodigo().substring(0, objBnTareaEspecifica.getCodigo().lastIndexOf(".")));
            objBnTareaEspecifica.setCadenaGasto(objBnTareaEspecifica.getCodigo().substring(objBnTareaEspecifica.getCodigo().lastIndexOf(".") + 1));
            k = objDsTareaEspecifica.iduTareasEspecificas(objBnTareaEspecifica, objUsuario.getUsuario());
        } else {
            String lista[][] = Utiles.generaLista(request.getParameter("lista"), 1);
            for (String[] item : lista) {
                objBnTareaEspecifica.setCadenaGasto(item[0].trim());
                k = objDsTareaEspecifica.iduTareasEspecificas(objBnTareaEspecifica, objUsuario.getUsuario());
            }
        }
        if (k != 0) {
        } else {
            // EN CASO DE HABER PROBLEMAS DESPACHAMOS UNA VENTANA DE ERROR, MOSTRANDO EL ERROR OCURRIDO.
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(objUsuario.getUsuario());
            objBnMsgerr.setTabla("IAFAS_TAREA_CLASIFICADOR_PRESUPUESTAL");
            objBnMsgerr.setTipo(objBnTareaEspecifica.getMode());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = objDsMsgerr.getMsgerr(objBnMsgerr);
            result = objBnMsgerr.getDescripcion();
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
