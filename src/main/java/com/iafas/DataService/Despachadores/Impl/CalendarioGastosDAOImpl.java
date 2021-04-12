/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.DataService.Despachadores.Impl;

import com.iafas.BusinessServices.Beans.BeanCalendarioGastos;
import com.iafas.BusinessServices.Beans.BeanMsgerr;
import com.iafas.DataService.Despachadores.CalendarioGastosDAO;
import com.iafas.DataService.Despachadores.MsgerrDAO;
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
public class CalendarioGastosDAOImpl implements CalendarioGastosDAO {

    private final Connection objConnection;
    private List lista;
    private String sql;
    private ResultSet objResultSet;
    private BeanCalendarioGastos objBnCalendario;
    private PreparedStatement objPreparedStatement;
    private MsgerrDAO objDsMsgerr;
    private BeanMsgerr objBnMsgerr;
    private int s = 0;

    public CalendarioGastosDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public List getListaCalendarioGastos(BeanCalendarioGastos objBeanCalendarioGastos) {
        lista = new LinkedList<>();
        sql = "SELECT CONCAT(NTAREA_PRESUPUESTAL_CODIGO,'-',NRESOLUCION_CODIGO) AS CODIGO, "
                + "`PK_UTIL.FUN_TAREA_PRESUPUESTAL_NOMBRE`(NTAREA_PRESUPUESTAL_CODIGO) AS TAREA, "
                + "SUM(PIA) AS PIA, SUM(PIM) AS PIM,  "
                + "SUM(CERTIFICADO) AS CERTIFICACION_ANUAL, "
                + "SUM(COMPROMISO_ANUAL) AS COMPROMISO, "
                + "SUM(DEVENGADO) AS DEVENGADO, "
                + "`PK_UTIL.FUN_CLASIFICADOR_GENERICA`(NCLASIFICADOR_PRESUPUESTAL_CODIGO) AS GENERICA,"
                + "`PK_UTIL.FUN_RESOLUCION_DESCRIPCION`(CPERIODO_CODIGO, NRESOLUCION_CODIGO) AS RESOLUCION "
                + "FROM V_PIM_CERTIFICADO_MENSUAL WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? "
                + "GROUP BY CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, NTAREA_PRESUPUESTAL_CODIGO, NRESOLUCION_CODIGO,"
                + "`PK_UTIL.FUN_CLASIFICADOR_GENERICA`(NCLASIFICADOR_PRESUPUESTAL_CODIGO) "
                + "ORDER BY NRESOLUCION_CODIGO, NTAREA_PRESUPUESTAL_CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanCalendarioGastos.getPeriodo());
            objPreparedStatement.setInt(2, objBeanCalendarioGastos.getPresupuesto());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnCalendario = new BeanCalendarioGastos();
                objBnCalendario.setCodigo(objResultSet.getString("CODIGO"));
                objBnCalendario.setTarea(objResultSet.getString("TAREA"));
                objBnCalendario.setProgramado(objResultSet.getDouble("PIA"));
                objBnCalendario.setImporte(objResultSet.getDouble("PIM"));
                objBnCalendario.setEnero(objResultSet.getDouble("CERTIFICACION_ANUAL"));
                objBnCalendario.setFebrero(objResultSet.getDouble("COMPROMISO"));
                objBnCalendario.setMarzo(objResultSet.getDouble("DEVENGADO"));
                objBnCalendario.setGenericaGasto(objResultSet.getString("GENERICA"));
                objBnCalendario.setResolucion(objResultSet.getString("RESOLUCION"));
                lista.add(objBnCalendario);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaCalendarioGastos(objBeanCalendarioGastos) : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getListaCalendarioGastosDetalle(BeanCalendarioGastos objBeanCalendarioGastos) {
        lista = new LinkedList<>();
        sql = "SELECT CONCAT(NTAREA_PRESUPUESTAL_CODIGO,'-',NRESOLUCION_CODIGO) AS CODIGO, "
                + "`PK_UTIL.FUN_CLASIFICA_PRESUPUESTAL_NOMBRE`(NCLASIFICADOR_PRESUPUESTAL_CODIGO) AS CLASIFICADOR_PRESUPUESTAL, "
                + "SUM(PIM) AS IMPORTE, "
                + "SUM(CASE CMES_CODIGO WHEN '01' THEN PIM ELSE 0 END) AS ENERO, "
                + "SUM(CASE CMES_CODIGO WHEN '02' THEN PIM ELSE 0 END) AS FEBRERO, "
                + "SUM(CASE CMES_CODIGO WHEN '03' THEN PIM ELSE 0 END) AS MARZO, "
                + "SUM(CASE CMES_CODIGO WHEN '04' THEN PIM ELSE 0 END) AS ABRIL, "
                + "SUM(CASE CMES_CODIGO WHEN '05' THEN PIM ELSE 0 END) AS MAYO, "
                + "SUM(CASE CMES_CODIGO WHEN '06' THEN PIM ELSE 0 END) AS JUNIO, "
                + "SUM(CASE CMES_CODIGO WHEN '07' THEN PIM ELSE 0 END) AS JULIO, "
                + "SUM(CASE CMES_CODIGO WHEN '08' THEN PIM ELSE 0 END) AS AGOSTO, "
                + "SUM(CASE CMES_CODIGO WHEN '09' THEN PIM ELSE 0 END) AS SETIEMBRE, "
                + "SUM(CASE CMES_CODIGO WHEN '10' THEN PIM ELSE 0 END) AS OCTUBRE, "
                + "SUM(CASE CMES_CODIGO WHEN '11' THEN PIM ELSE 0 END) AS NOVIEMBRE, "
                + "SUM(CASE CMES_CODIGO WHEN '12' THEN PIM ELSE 0 END) AS DICIEMBRE "
                + "FROM V_CALENDARIO_GASTOS WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? "
                + "GROUP BY CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, NTAREA_PRESUPUESTAL_CODIGO, NRESOLUCION_CODIGO, NCLASIFICADOR_PRESUPUESTAL_CODIGO "
                + "ORDER BY NCLASIFICADOR_PRESUPUESTAL_CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanCalendarioGastos.getPeriodo());
            objPreparedStatement.setInt(2, objBeanCalendarioGastos.getPresupuesto());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnCalendario = new BeanCalendarioGastos();
                objBnCalendario.setCodigo(objResultSet.getString("CODIGO"));
                objBnCalendario.setCadenaGasto(objResultSet.getString("CLASIFICADOR_PRESUPUESTAL"));
                objBnCalendario.setImporte(objResultSet.getDouble("IMPORTE"));
                objBnCalendario.setEnero(objResultSet.getDouble("ENERO"));
                objBnCalendario.setFebrero(objResultSet.getDouble("FEBRERO"));
                objBnCalendario.setMarzo(objResultSet.getDouble("MARZO"));
                objBnCalendario.setAbril(objResultSet.getDouble("ABRIL"));
                objBnCalendario.setMayo(objResultSet.getDouble("MAYO"));
                objBnCalendario.setJunio(objResultSet.getDouble("JUNIO"));
                objBnCalendario.setJulio(objResultSet.getDouble("JULIO"));
                objBnCalendario.setAgosto(objResultSet.getDouble("AGOSTO"));
                objBnCalendario.setSetiembre(objResultSet.getDouble("SETIEMBRE"));
                objBnCalendario.setOctubre(objResultSet.getDouble("OCTUBRE"));
                objBnCalendario.setNoviembre(objResultSet.getDouble("NOVIEMBRE"));
                objBnCalendario.setDiciembre(objResultSet.getDouble("DICIEMBRE"));
                lista.add(objBnCalendario);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaCalendarioGastosDetalle(objBeanCalendarioGastos) : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public ArrayList getListaCalendarioGastoDetalle(BeanCalendarioGastos objBeanCalendarioGastos) {
        ArrayList<String> Arreglo = new ArrayList<>();
        ArrayList<String> Filas = new ArrayList<>();
        sql = "SELECT NCLASIFICADOR_PRESUPUESTAL_CODIGO AS CODIGO, "
               + "`PK_UTIL.FUN_CLASIFICA_PRESUPUESTAL_NOMBRE`(NCLASIFICADOR_PRESUPUESTAL_CODIGO) AS CLASIFICADOR_PRESUPUESTAL,  "
                + "SUM(PIM) AS IMPORTE, "
                + "SUM(CASE CMES_CODIGO WHEN '01' THEN PIM ELSE 0 END) AS ENERO, "
                + "SUM(CASE CMES_CODIGO WHEN '02' THEN PIM ELSE 0 END) AS FEBRERO, "
                + "SUM(CASE CMES_CODIGO WHEN '03' THEN PIM ELSE 0 END) AS MARZO, "
                + "SUM(CASE CMES_CODIGO WHEN '04' THEN PIM ELSE 0 END) AS ABRIL, "
                + "SUM(CASE CMES_CODIGO WHEN '05' THEN PIM ELSE 0 END) AS MAYO, "
                + "SUM(CASE CMES_CODIGO WHEN '06' THEN PIM ELSE 0 END) AS JUNIO, "
                + "SUM(CASE CMES_CODIGO WHEN '07' THEN PIM ELSE 0 END) AS JULIO, "
                + "SUM(CASE CMES_CODIGO WHEN '08' THEN PIM ELSE 0 END) AS AGOSTO, "
                + "SUM(CASE CMES_CODIGO WHEN '09' THEN PIM ELSE 0 END) AS SETIEMBRE, "
                + "SUM(CASE CMES_CODIGO WHEN '10' THEN PIM ELSE 0 END) AS OCTUBRE, "
                + "SUM(CASE CMES_CODIGO WHEN '11' THEN PIM ELSE 0 END) AS NOVIEMBRE, "
                + "SUM(CASE CMES_CODIGO WHEN '12' THEN PIM ELSE 0 END) AS DICIEMBRE "
                + "FROM V_CALENDARIO_GASTOS WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "CONCAT(NTAREA_PRESUPUESTAL_CODIGO,'-',NRESOLUCION_CODIGO)=? "
                + "GROUP BY NCLASIFICADOR_PRESUPUESTAL_CODIGO "
                + "ORDER BY NCLASIFICADOR_PRESUPUESTAL_CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanCalendarioGastos.getPeriodo());
            objPreparedStatement.setInt(2, objBeanCalendarioGastos.getPresupuesto());
            objPreparedStatement.setString(3, objBeanCalendarioGastos.getCodigo());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                Filas.clear();
                String arreglo =  objResultSet.getString("CODIGO") + "+++"
                        + objResultSet.getString("CLASIFICADOR_PRESUPUESTAL") + "+++"
                        + objResultSet.getDouble("IMPORTE") + "+++"
                        + objResultSet.getDouble("ENERO") + "+++"
                        + objResultSet.getDouble("FEBRERO") + "+++"
                        + objResultSet.getDouble("MARZO") + "+++"
                        + objResultSet.getDouble("ABRIL") + "+++"
                        + objResultSet.getDouble("MAYO") + "+++"
                        + objResultSet.getDouble("JUNIO") + "+++"
                        + objResultSet.getDouble("JULIO") + "+++"
                        + objResultSet.getDouble("AGOSTO") + "+++"
                        + objResultSet.getDouble("SETIEMBRE") + "+++"
                        + objResultSet.getDouble("OCTUBRE") + "+++"
                        + objResultSet.getDouble("NOVIEMBRE") + "+++"
                        + objResultSet.getDouble("DICIEMBRE");
                Filas.add(arreglo);
                Arreglo.add("" + Filas);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaCalendarioGastoDetalle(BeanCertificadoCreditoPresupuestal) : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return Arreglo;
    }

    @Override
    public int iduCalendarioGastoDetalle(BeanCalendarioGastos objBeanCalendarioGastos, String usuario) {
        sql = "{CALL SP_UPD_CALENDARIO_GASTO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanCalendarioGastos.getPeriodo());
            cs.setInt(2, objBeanCalendarioGastos.getPresupuesto());
            cs.setString(3, objBeanCalendarioGastos.getResolucion());
            cs.setString(4, objBeanCalendarioGastos.getTarea());
            cs.setString(5, objBeanCalendarioGastos.getCadenaGasto());
            cs.setDouble(6, objBeanCalendarioGastos.getEnero());
            cs.setDouble(7, objBeanCalendarioGastos.getFebrero());
            cs.setDouble(8, objBeanCalendarioGastos.getMarzo());
            cs.setDouble(9, objBeanCalendarioGastos.getAbril());
            cs.setDouble(10, objBeanCalendarioGastos.getMayo());
            cs.setDouble(11, objBeanCalendarioGastos.getJunio());
            cs.setDouble(12, objBeanCalendarioGastos.getJulio());
            cs.setDouble(13, objBeanCalendarioGastos.getAgosto());
            cs.setDouble(14, objBeanCalendarioGastos.getSetiembre());
            cs.setDouble(15, objBeanCalendarioGastos.getOctubre());
            cs.setDouble(16, objBeanCalendarioGastos.getNoviembre());
            cs.setDouble(17, objBeanCalendarioGastos.getDiciembre());
            cs.setString(18, usuario);
            cs.setString(19, objBeanCalendarioGastos.getMode());
            s = cs.executeUpdate();
            cs.close();
            s++;
        } catch (SQLException e) {
            System.out.println("Error al obtener iduCalendarioGastoDetalle(objBeanCalendarioGastos, " + usuario + ") : " + e.getMessage());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(usuario);
            objBnMsgerr.setTabla("IAFAS_CALENDARIO_GASTOS");
            objBnMsgerr.setTipo(objBeanCalendarioGastos.getMode());
            objBnMsgerr.setDescripcion(e.getMessage());
            s = objDsMsgerr.iduMsgerr(objBnMsgerr);
            return 0;
        }
        return s;
    }
}
