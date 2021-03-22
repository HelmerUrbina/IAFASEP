/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.DataService.Despachadores.Impl;

import com.iafas.BusinessServices.Beans.BeanMsgerr;
import com.iafas.BusinessServices.Beans.BeanComun;
import com.iafas.DataService.Despachadores.MsgerrDAO;
import com.iafas.DataService.Despachadores.TareasPresupuestalesDAO;
import com.iafas.Utiles.Utiles;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author H-URBINA-M
 */
public class TareasPresupuestalesDAOImpl implements TareasPresupuestalesDAO {

    private final Connection objConnection;
    private List lista;
    private String sql;
    private ResultSet objResultSet;
    private BeanComun objBnTareas;
    private PreparedStatement objPreparedStatement;
    private MsgerrDAO objDsMsgerr;
    private BeanMsgerr objBnMsgerr;
    private int s = 0;

    public TareasPresupuestalesDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public List getListaTareasPresupuestales() {
        lista = new LinkedList<>();
        sql = "SELECT LPAD(NTAREA_PRESUPUESTAL_CODIGO,4,0) AS CODIGO, VTAREA_PRESUPUESTAL_NOMBRE AS DESCRIPCION, "
                + "VTAREA_PRESUPUESTAL_ABREVIATURA AS ABREVIATURA, "
                + "`PK_UTIL.FUN_UNIDAD_MEDIDA_NOMBRE`(CUNIDAD_MEDIDA_CODIGO) AS UNIDAD_MEDIDA, "
                + "`PK_UTIL.FUN_ESTADO_NOMBRE`(CESTADO_CODIGO) ESTADO "
                + "FROM IAFAS_TAREA_PRESUPUESTAL "
                + "ORDER BY NTAREA_PRESUPUESTAL_CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnTareas = new BeanComun();
                objBnTareas.setCodigo(objResultSet.getString("CODIGO"));
                objBnTareas.setDescripcion(objResultSet.getString("DESCRIPCION"));
                objBnTareas.setAbreviatura(objResultSet.getString("ABREVIATURA"));
                objBnTareas.setUnidadMedida(objResultSet.getString("UNIDAD_MEDIDA"));
                objBnTareas.setEstado(objResultSet.getString("ESTADO"));
                lista.add(objBnTareas);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaTareasPresupuestales() : " + e.getMessage());
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
    public BeanComun getTareasPresupuestales(BeanComun objBeanTareas) {
        sql = "SELECT VTAREA_PRESUPUESTAL_NOMBRE, VTAREA_PRESUPUESTAL_ABREVIATURA, "
                + "CUNIDAD_MEDIDA_CODIGO, CESTADO_CODIGO "
                + "FROM IAFAS_TAREA_PRESUPUESTAL WHERE "
                + "NTAREA_PRESUPUESTAL_CODIGO=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanTareas.getCodigo());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanTareas.setDescripcion(objResultSet.getString("VTAREA_PRESUPUESTAL_NOMBRE"));
                objBeanTareas.setAbreviatura(objResultSet.getString("VTAREA_PRESUPUESTAL_ABREVIATURA"));
                objBeanTareas.setUnidadMedida(objResultSet.getString("CUNIDAD_MEDIDA_CODIGO"));
                objBeanTareas.setEstado(objResultSet.getString("CESTADO_CODIGO"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTareas(objBeanTareas) : " + e.getMessage());
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
        return objBeanTareas;
    }

    @Override
    public int iduTareasPresupuestales(BeanComun objBeanTareas, String usuario) {
        /*
         * EJECUTAMOS EL PROCEDIMIENTO ALMACENADO PARA LA TABLA TAPAIN, EN EL
         * CUAL PODEMOS INSERTAR, MODIFICAR O ELIMINAR UN REGISTRO DE LA TABLA,
         * EN CASO DE OBTENER ALGUN ERROR ACTIVARA LA OPCION DE
         * EXCEPCIONES EN LA CUAL SE REGISTRARA EL ERROR EL MOTIVO DEL ERROR.
         */
        sql = "{CALL SP_IDU_TAREA_PRESUPUESTAL(?,?,?,?,?,?)}";
        try {
            try (CallableStatement cs = objConnection.prepareCall(sql)) {
                cs.setInt(1, Utiles.checkNum(objBeanTareas.getCodigo()));
                cs.setString(2, objBeanTareas.getDescripcion());
                cs.setString(3, objBeanTareas.getAbreviatura());
                cs.setString(4, objBeanTareas.getUnidadMedida());
                cs.setString(5, usuario);
                cs.setString(6, objBeanTareas.getMode());
                s = cs.executeUpdate();
                cs.close();
                s++;
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduTareasPresupuestales : " + e.getMessage());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(usuario);
            objBnMsgerr.setTabla("IAFAS_TAREA_PRESUPUESTAL");
            objBnMsgerr.setTipo(objBeanTareas.getMode().toUpperCase());
            objBnMsgerr.setDescripcion(e.getMessage());
            s = objDsMsgerr.iduMsgerr(objBnMsgerr);
            return 0;
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return s;
    }

}
