/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.UserServices.Login;

import com.iafas.DataService.Conexion.ConnectionSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author H-URBINA-M
 */
public class InicioServletListener implements ServletContextListener {
    
    private Connection objConnection = null;
    private ConnectionSource connection;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        //Creacion de Conexion a Base de Datos
        connection = new ConnectionSource();
        objConnection = connection.getConnection();
        if (objConnection != null) {
            ctx.setAttribute("objConnection", objConnection);
            System.out.println("SE HA CONECTADO SATISFACTORIAMENTE............ IAFAS");
        } else {
            System.out.println("PROBLEMAS AL CONECTAR............ IAFAS");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        try {
            objConnection = (Connection) ctx.getAttribute("objConnection");
            objConnection.close();
        } catch (SQLException ex) {
            System.out.println("ERROR AL CERRAR CONEXION " + ex.getMessage());
        }
        System.out.println("CONEXION IAFAS............ CERRADO CORRECTAMENTE.");
    }
}
