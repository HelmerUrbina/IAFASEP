/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.DataService.Despachadores.Impl;

import com.iafas.BusinessServices.Beans.BeanMsgerr;
import com.iafas.BusinessServices.Beans.BeanTareaEspecifica;
import com.iafas.DataService.Despachadores.MsgerrDAO;
import com.iafas.DataService.Despachadores.TareaEspecificaDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author H-URBINA-M
 */
public class TareaEspecificaDAOImpl implements TareaEspecificaDAO {

    private final Connection objConnection;
    private List lista;
    private String sql;
    private ResultSet objResultSet;
    private BeanTareaEspecifica objBnTareaEspecifica;
    private PreparedStatement objPreparedStatement;
    private MsgerrDAO objDsMsgerr;
    private BeanMsgerr objBnMsgerr;
    private int s = 0;

    public TareaEspecificaDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public List getListaTareasEspecificas(BeanTareaEspecifica objBeanTareaEspecifica, String usuario) {
        lista = new LinkedList<>();
        sql = "SELECT CONCAT(NTAREA_PRESUPUESTAL_CODIGO,'.',NCLASIFICADOR_PRESUPUESTAL_CODIGO) AS CODIGO, "
                + "CONCAT(NTAREA_PRESUPUESTAL_CODIGO,'-',`PK_UTIL.FUN_TAREA_PRESUPUESTAL_NOMBRE`(NTAREA_PRESUPUESTAL_CODIGO)) AS TAREA_PRESUPUESTAL, "
                + "`PK_UTIL.FUN_CLASIFICA_PRESUPUESTAL_NOMBRE`(NCLASIFICADOR_PRESUPUESTAL_CODIGO) AS CLASIFICADOR_PRESUPUESTAL "
                + "FROM IAFAS_TAREA_CLASIFICADOR_PRESUPUESTAL WHERE "
                + "CPERIODO_CODIGO=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanTareaEspecifica.getPeriodo());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnTareaEspecifica = new BeanTareaEspecifica();
                objBnTareaEspecifica.setCodigo(objResultSet.getString("CODIGO"));
                objBnTareaEspecifica.setTarea(objResultSet.getString("TAREA_PRESUPUESTAL"));
                objBnTareaEspecifica.setCadenaGasto(objResultSet.getString("CLASIFICADOR_PRESUPUESTAL"));
                lista.add(objBnTareaEspecifica);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaTareasEspecificas(usuario) : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objPreparedStatement.close();
                    objResultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public ArrayList getCadenaGasto(BeanTareaEspecifica objBeanTareaEspecifica, String usuario) {
        ArrayList<String> Arreglo = new ArrayList<>();
        ArrayList<String> Filas = new ArrayList<>();
        sql = "SELECT NCLASIFICADOR_PRESUPUESTAL_CODIGO AS CODIGO, "
                + "CONCAT(VCLASIFICADOR_PRESUPUESTAL_CADENA,':',VCLASIFICADOR_PRESUPUESTAL_NOMBRE) AS DESCRIPCION "
                + "FROM IAFAS_CLASIFICADOR_PRESUPUESTAL WHERE "
                + "CTIPO_TRANSACCION_CODIGO='2' AND "
                + "NCLASIFICADOR_PRESUPUESTAL_CODIGO NOT IN ("
                + "SELECT NCLASIFICADOR_PRESUPUESTAL_CODIGO AS CODIGO "
                + "FROM IAFAS_TAREA_CLASIFICADOR_PRESUPUESTAL WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NTAREA_PRESUPUESTAL_CODIGO=?) "
                + "ORDER BY VCLASIFICADOR_PRESUPUESTAL_CADENA";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanTareaEspecifica.getPeriodo());
            objPreparedStatement.setString(2, objBeanTareaEspecifica.getCodigo());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                Filas.clear();
                String arreglo = objResultSet.getString("CODIGO") + "+++"
                        + objResultSet.getString("DESCRIPCION");
                Filas.add(arreglo);
                Arreglo.add("" + Filas);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getCadenaGasto(objBeanTareaEspecifica, usuario) : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objPreparedStatement.close();
                    objResultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return Arreglo;
    }

    @Override
    public int iduTareasEspecificas(BeanTareaEspecifica objBeanTareaEspecifica, String usuario) {
        /*
         * EJECUTAMOS EL PROCEDIMIENTO ALMACENADO PARA LA TABLA TAPAIN, EN EL
         * CUAL PODEMOS INSERTAR, MODIFICAR O ELIMINAR UN REGISTRO DE LA TABLA,
         * EN CASO DE OBTENER ALGUN ERROR ACTIVARA LA OPCION DE
         * EXCEPCIONES EN LA CUAL SE REGISTRARA EL ERROR EL MOTIVO DEL ERROR.
         */
        sql = "{CALL SP_IDU_TAREA_CLASIFICADOR_PRESUPUESTAL(?,?,?,?,?)}";
        try {
            try (CallableStatement cs = objConnection.prepareCall(sql)) {
                cs.setString(1, objBeanTareaEspecifica.getPeriodo());
                cs.setString(2, objBeanTareaEspecifica.getTarea());
                cs.setString(3, objBeanTareaEspecifica.getCadenaGasto());
                cs.setString(4, usuario);
                cs.setString(5, objBeanTareaEspecifica.getMode());
                s = cs.executeUpdate();
                cs.close();
                s++;
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduTareasEspecificas : " + e.toString());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(usuario);
            objBnMsgerr.setTabla("IAFAS_TAREA_CLASIFICADOR_PRESUPUESTAL");
            objBnMsgerr.setTipo(objBeanTareaEspecifica.getMode());
            objBnMsgerr.setDescripcion(e.toString());
            s = objDsMsgerr.iduMsgerr(objBnMsgerr);
            return 0;
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return s;
    }
}
