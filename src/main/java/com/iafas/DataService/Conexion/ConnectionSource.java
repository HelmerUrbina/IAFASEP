/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.DataService.Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author H-URBINA-M
 */
public class ConnectionSource {

    public Connection getConnection() {
        Connection objConnection = null;
        try {
            Context c = new InitialContext();
            DataSource ds = (DataSource) c.lookup("java:comp/env/IAFAS_MySQL");
            objConnection = ds.getConnection();
        } catch (NamingException | SQLException e) {
            System.out.println("Problemas!: Fallo la creacion del Pool de Conexiones... IAFAS \n" + e.getMessage());
        }
        return objConnection;
    }
    

}
