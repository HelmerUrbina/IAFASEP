/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.UserServices.MesaPartes;

import com.iafas.BusinessServices.Beans.BeanMesaParte;
import com.iafas.BusinessServices.Beans.BeanUsuario;
import com.iafas.DataService.Despachadores.CombosDAO;
import com.iafas.DataService.Despachadores.Impl.CombosDAOImpl;
import com.iafas.DataService.Despachadores.Impl.MesaParteDAOImpl;
import com.iafas.DataService.Despachadores.MesaParteDAO;
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
@WebServlet(name = "MesaParteServlet", urlPatterns = {"/MesaParte"})
public class MesaParteServlet extends HttpServlet {

    private ServletConfig config = null;
    private ServletContext context = null;
    private HttpSession session = null;
    private RequestDispatcher dispatcher = null;
    private BeanMesaParte objBnMesaParte;
    private Connection objConnection;
    private MesaParteDAO objDsMesaParte;
    private CombosDAO objDsCombo;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        config = this.getServletConfig();
        context = config.getServletContext();
        session = request.getSession(false);
        BeanUsuario objUsuario = (BeanUsuario) session.getAttribute("objUsuario" + session.getId());
        //VERIFICAMOS LA SESSION DEL USUARIO
        if (objUsuario == null) {
            dispatcher = request.getRequestDispatcher("/FinSession.jsp");
            dispatcher.forward(request, response);
        }
        objConnection = (Connection) context.getAttribute("objConnection");
        String result = null;
        objBnMesaParte = new BeanMesaParte();
        objBnMesaParte.setMode(request.getParameter("mode"));
        objBnMesaParte.setPeriodo(request.getParameter("periodo"));
        objBnMesaParte.setMes(request.getParameter("mes"));
        objBnMesaParte.setTipo(request.getParameter("tipo"));
        objBnMesaParte.setNumero(request.getParameter("codigo"));
        objDsMesaParte = new MesaParteDAOImpl(objConnection);
        if (objBnMesaParte.getMode().equals("G")) {
            objDsCombo = new CombosDAOImpl(objConnection);
            if (request.getAttribute("objPrioridades") != null) {
                request.removeAttribute("objPrioridades");
            }
            request.setAttribute("objPrioridades", objDsCombo.getPrioridades());
            if (request.getAttribute("objDocumentos") != null) {
                request.removeAttribute("objDocumentos");
            }
            request.setAttribute("objDocumentos", objDsCombo.getDocumentos());
            if (request.getAttribute("objClasificaciones") != null) {
                request.removeAttribute("objClasificaciones");
            }
            request.setAttribute("objClasificaciones", objDsCombo.getClasificacionDocumento());
            if (request.getAttribute("objInstitucion") != null) {
                request.removeAttribute("objInstitucion");
            }
            request.setAttribute("objInstitucion", objDsCombo.getInstituciones());
            if (request.getAttribute("objMesaPartes") != null) {
                request.removeAttribute("objMesaPartes");
            }
            request.setAttribute("objMesaPartes", objDsMesaParte.getListaMesaPartes(objBnMesaParte, objUsuario.getUsuario()));
        }
        if (objBnMesaParte.getMode().equals("I")) {
            result = objDsMesaParte.getNumeroMesaParte(objBnMesaParte, objUsuario.getUsuario());
        }
        if (objBnMesaParte.getMode().equals("U")) {
            objBnMesaParte = objDsMesaParte.getMesaParte(objBnMesaParte, objUsuario.getUsuario());
            result = objBnMesaParte.getNumero() + "+++"
                    + objBnMesaParte.getInstitucion() + "+++"
                    + objBnMesaParte.getReferencia() + "+++"
                    + objBnMesaParte.getPrioridad() + "+++"
                    + objBnMesaParte.getDocumento() + "+++"
                    + objBnMesaParte.getNumeroDocumento() + "+++"
                    + objBnMesaParte.getClasificacion() + "+++"
                    + objBnMesaParte.getFecha() + "+++"
                    + objBnMesaParte.getFechaRegistro() + "+++"
                    + objBnMesaParte.getAsunto() + "+++"
                    + objBnMesaParte.getPostFirma() + "+++"
                    + objBnMesaParte.getLegajo() + "+++"
                    + objBnMesaParte.getFolio();
        }
        if (objBnMesaParte.getMode().equals("L")) {
            if (request.getAttribute("objMesaPartesConsulta") != null) {
                request.removeAttribute("objMesaPartesConsulta");
            }
            request.setAttribute("objMesaPartesConsulta", objDsMesaParte.getListaMesaPartesConsulta(objBnMesaParte, objUsuario.getUsuario()));
        }
        //SE ENVIA DE ACUERDO AL MODO SELECCIONADO
        switch (request.getParameter("mode")) {
            case "mesaParte":
                dispatcher = request.getRequestDispatcher("MesaPartes/MesaPartes.jsp");
                break;
            case "G":
                dispatcher = request.getRequestDispatcher("MesaPartes/ListaMesaPartes.jsp");
                break;
            case "consultaMesaParte":
                dispatcher = request.getRequestDispatcher("MesaPartes/MesaPartesConsulta.jsp");
                break;
            case "L":
                dispatcher = request.getRequestDispatcher("MesaPartes/ListaMesaPartesConsulta.jsp");
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
