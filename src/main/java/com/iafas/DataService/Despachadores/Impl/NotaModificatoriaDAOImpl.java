/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.DataService.Despachadores.Impl;

import com.iafas.BusinessServices.Beans.BeanMsgerr;
import com.iafas.BusinessServices.Beans.BeanNotaModificatoria;
import com.iafas.DataService.Despachadores.MsgerrDAO;
import com.iafas.DataService.Despachadores.NotaModificatoriaDAO;
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
public class NotaModificatoriaDAOImpl implements NotaModificatoriaDAO {

    private final Connection objConnection;
    private List lista;
    private String sql;
    private ResultSet objResultSet;
    private BeanNotaModificatoria objBnNotaModificatoria;
    private PreparedStatement objPreparedStatement;
    private MsgerrDAO objDsMsgerr;
    private BeanMsgerr objBnMsgerr;
    private int s = 0;

    public NotaModificatoriaDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public List getListaNotasModificatorias(BeanNotaModificatoria objBeanNotaModificatoria, String usuario) {
        lista = new LinkedList<>();
        sql = "SELECT NNOTA_MODIFICATORIA_CODIGO AS CODIGO, "
                + "`PK_UTIL.FUN_TIPO_NOTA_MODIFICATORIA_NOMBRE`(NTIPO_NOTA_MODIFICATORIA_CODIGO) AS TIPO, "
                //  + "OPRE_NEW.FUN_TOTAL_DETALLE_NOTA(CPERIODO_CODIGO,CUNIDAD_OPERATIVA_CODIGO,NNOTA_MODIFICATORIA_CODIGO,'C') AS IMP_CREDITO, "
                //  + "OPRE_NEW.FUN_TOTAL_DETALLE_NOTA(CPERIODO_CODIGO,CUNIDAD_OPERATIVA_CODIGO,NNOTA_MODIFICATORIA_CODIGO,'A') AS IMP_ANULACION, "
                + "UPPER(VNOTA_MODIFICATORIA_JUSTIFICACION) AS JUSTIFICACION, "
                + "DNOTA_MODIFICATORIA_FECHA AS FECHA_NOTA, "
                + "`PK_UTIL.FUN_ESTADO_NOMBRE`(CESTADO_CODIGO) AS ESTADO, "
                //   + "IFNULL(UTIL_NEW.FUN_DESC_USUARIO(VUSUARIO_TERMINA),'--') AS USU_CERRAR, "
                //   + "DUSUARIO_TERMINA AS FECHA_CERRAR, "
                //  + "IFNULL(UTIL_NEW.FUN_DESC_USUARIO(VUSUARIO_VERIFICA),'--') AS USU_VERIFICA, "
                //    + "DUSUARIO_VERIFICA AS FECHA_VERIFICA, "
                //   + "IFNULL(UTIL_NEW.FUN_DESC_USUARIO(VUSUARIO_ENVIO),'--') AS USU_APRUEBA, "
                //   + "DNOTA_MODIFICATORIA_ENVIO AS FECHA_APRUEBA, "
                /*  + "IFNULL(UTIL_NEW.FUN_DESC_USUARIO(VUSUARIO_RECHAZO),'--') AS USU_RECHAZO, "
                + "DUSUARIO_RECHAZO AS FECHA_RECHAZO, "*/
                + "VNOTA_MODIFICATORIA_RECHAZO, "
                + "NCONSOLIDADO_CODIGO AS NCONSOLIDADO_CODIGO "
                + "FROM IAFAS_NOTAS_MODIFICATORIAS WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CMES_CODIGO=? "
                + "ORDER BY CODIGO DESC";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanNotaModificatoria.getPeriodo());
            objPreparedStatement.setString(2, objBeanNotaModificatoria.getMes());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnNotaModificatoria = new BeanNotaModificatoria();
                objBnNotaModificatoria.setCodigo(objResultSet.getString("CODIGO"));
                objBnNotaModificatoria.setTipo(objResultSet.getString("TIPO"));
                //  objBnNotaModificatoria.setImporteCredito(objResultSet.getDouble("IMP_CREDITO"));
                //   objBnNotaModificatoria.setImporteAnulacion(objResultSet.getDouble("IMP_ANULACION"));
                objBnNotaModificatoria.setJustificacion(objResultSet.getString("JUSTIFICACION"));
                objBnNotaModificatoria.setFecha(objResultSet.getDate("FECHA_NOTA"));
                objBnNotaModificatoria.setEstado(objResultSet.getString("ESTADO"));
                /*   objBnNotaModificatoria.setUsuarioCierre(objResultSet.getString("USU_CERRAR"));
                objBnNotaModificatoria.setFechaCierre(objResultSet.getDate("FECHA_CERRAR"));
                objBnNotaModificatoria.setUsuarioVerifica(objResultSet.getString("USU_VERIFICA"));
                objBnNotaModificatoria.setFechaVerifica(objResultSet.getDate("FECHA_VERIFICA"));
                objBnNotaModificatoria.setUsuarioAprobacion(objResultSet.getString("USU_APRUEBA"));
              //  objBnNotaModificatoria.setFechaAprobacion(objResultSet.getDate("FECHA_APRUEBA"));*/
                //objBnNotaModificatoria.setUsuarioRechazo(objResultSet.getString("USU_RECHAZO"));
                //objBnNotaModificatoria.setFechaRechazo(objResultSet.getDate("FECHA_RECHAZO"));
                objBnNotaModificatoria.setDependencia(objResultSet.getString("VNOTA_MODIFICATORIA_RECHAZO"));
                objBnNotaModificatoria.setConsolidado(objResultSet.getString("NCONSOLIDADO_CODIGO"));
                lista.add(objBnNotaModificatoria);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaNotasModificatorias(objBeanNotaModificatoria) : " + e.getMessage());
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
    public List getListaNotasModificatoriasDetalle(BeanNotaModificatoria objBeanNotaModificatoria, String usuario) {
        lista = new LinkedList<>();
        sql = "SELECT NM.NNOTA_MODIFICATORIA_CODIGO AS CODIGO, ND.NNOTA_MODIFICATORIA_DETALLE AS DETALLE, "
                + "`PK_UTIL.FUN_FUENTE_FINANCIAMIENTO_NOMBRE`(NFUENTE_FINANCIAMIENTO_CODIGO) AS PPTO,"
                + "`PK_UTIL.FUN_TAREA_PRESUPUESTAL_NOMBRE`(NTAREA_PRESUPUESTAL_CODIGO) AS TAREA_PRESUPUESTAL, "
                + "`PK_UTIL.FUN_CLASIFICA_PRESUPUESTAL_NOMBRE`(NCLASIFICADOR_PRESUPUESTAL_CODIGO) AS CLASIFICADOR_PRESUPUESTAL, "
                + "AVG(CASE CNOTA_MODIFICATORIA_TIPO WHEN 'A' THEN NNOTA_MODIFICATORIA_DETALLE_IMPORTE ELSE 0 END) AS IMPORTE_ANULACION,  "
                + "AVG(CASE CNOTA_MODIFICATORIA_TIPO WHEN 'C' THEN NNOTA_MODIFICATORIA_DETALLE_IMPORTE ELSE 0 END) AS IMPORTE_CREDITO, "
                + "UPPER(ND.VNOTA_MODIFICATORIA_DETALLE_JUSTIFICACION) AS JUTIFICACION "
                + "FROM IAFAS_NOTAS_MODIFICATORIAS NM INNER JOIN IAFAS_NOTAS_MODIFICATORIAS_DETALLE ND ON ("
                + "NM.CPERIODO_CODIGO=ND.CPERIODO_CODIGO AND "
                + "NM.NNOTA_MODIFICATORIA_CODIGO=ND.NNOTA_MODIFICATORIA_CODIGO) WHERE "
                + "NM.CPERIODO_CODIGO=? AND "
                + "NM.CMES_CODIGO=? "
                + "GROUP BY NM.CPERIODO_CODIGO, NM.NNOTA_MODIFICATORIA_CODIGO, ND.NNOTA_MODIFICATORIA_DETALLE, "
                + "ND.NFUENTE_FINANCIAMIENTO_CODIGO, NTAREA_PRESUPUESTAL_CODIGO, NCLASIFICADOR_PRESUPUESTAL_CODIGO, "
                + "VNOTA_MODIFICATORIA_DETALLE_JUSTIFICACION "
                + "ORDER BY CODIGO DESC, DETALLE ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanNotaModificatoria.getPeriodo());
            objPreparedStatement.setString(2, objBeanNotaModificatoria.getMes());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnNotaModificatoria = new BeanNotaModificatoria();
                objBnNotaModificatoria.setCodigo(objResultSet.getString("CODIGO"));
                objBnNotaModificatoria.setDetalle(objResultSet.getInt("DETALLE"));
                objBnNotaModificatoria.setPresupuesto(objResultSet.getString("PPTO"));
                objBnNotaModificatoria.setTarea(objResultSet.getString("TAREA_PRESUPUESTAL"));
                objBnNotaModificatoria.setCadenaGasto(objResultSet.getString("CLASIFICADOR_PRESUPUESTAL"));
                objBnNotaModificatoria.setImporteAnulacion(objResultSet.getDouble("IMPORTE_ANULACION"));
                objBnNotaModificatoria.setImporteCredito(objResultSet.getDouble("IMPORTE_CREDITO"));
                objBnNotaModificatoria.setJustificacion(objResultSet.getString("JUTIFICACION"));
                lista.add(objBnNotaModificatoria);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaNotasModificatoriasDetalle(objBeanNotaModificatoria) : " + e.getMessage());
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
    public ArrayList getListaNotaModificatoriaDetalle(BeanNotaModificatoria objBeanNotaModificatoria, String usuario) {
        ArrayList<String> Arreglo = new ArrayList<>();
        ArrayList<String> Filas = new ArrayList<>();
        sql = "SELECT CONCAT(NFUENTE_FINANCIAMIENTO_CODIGO,'---',NRESOLUCION_CODIGO,'---',NTAREA_PRESUPUESTAL_CODIGO, '---',NCLASIFICADOR_PRESUPUESTAL_CODIGO) AS CODIGO, "
                + "CASE CNOTA_MODIFICATORIA_TIPO WHEN 'A' THEN 'Anulación' WHEN 'C' THEN 'Crédito' ELSE 'Verifique' END AS TIPO, "
                + "`PK_UTIL.FUN_FUENTE_FINANCIAMIENTO_NOMBRE`(NFUENTE_FINANCIAMIENTO_CODIGO) AS PPTO,"
                + "`PK_UTIL.FUN_RESOLUCION_DESCRIPCION`(CPERIODO_CODIGO, NRESOLUCION_CODIGO) AS RESOLUCION, "
                + "`PK_UTIL.FUN_TAREA_PRESUPUESTAL_NOMBRE`(NTAREA_PRESUPUESTAL_CODIGO) AS TAREA_PRESUPUESTAL, "
                + "`PK_UTIL.FUN_CLASIFICA_PRESUPUESTAL_NOMBRE`(NCLASIFICADOR_PRESUPUESTAL_CODIGO) AS CLASIFICADOR_PRESUPUESTAL, "
                + "SUM(CASE CNOTA_MODIFICATORIA_TIPO WHEN 'A' THEN NNOTA_MODIFICATORIA_DETALLE_IMPORTE ELSE 0 END) AS IMPORTE_ANULACION, "
                + "SUM(CASE CNOTA_MODIFICATORIA_TIPO WHEN 'C' THEN NNOTA_MODIFICATORIA_DETALLE_IMPORTE ELSE 0 END) AS IMPORTE_CREDITO, "
                + "VNOTA_MODIFICATORIA_DETALLE_JUSTIFICACION AS JUTIFICACION "
                + "FROM IAFAS_NOTAS_MODIFICATORIAS_DETALLE WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NNOTA_MODIFICATORIA_CODIGO=? "
                + "GROUP BY CPERIODO_CODIGO, CNOTA_MODIFICATORIA_TIPO, NFUENTE_FINANCIAMIENTO_CODIGO, "
                + "NTAREA_PRESUPUESTAL_CODIGO, NCLASIFICADOR_PRESUPUESTAL_CODIGO, "
                + "VNOTA_MODIFICATORIA_DETALLE_JUSTIFICACION, NRESOLUCION_CODIGO, NNOTA_MODIFICATORIA_DETALLE "
                + "ORDER BY NNOTA_MODIFICATORIA_DETALLE ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanNotaModificatoria.getPeriodo());
            objPreparedStatement.setString(2, objBeanNotaModificatoria.getCodigo());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                Filas.clear();
                String arreglo = objResultSet.getString("CODIGO") + "+++"
                        + objResultSet.getString("TIPO") + "+++"
                        + objResultSet.getString("PPTO") + "+++"
                        + objResultSet.getString("RESOLUCION") + "+++"
                        + objResultSet.getString("TAREA_PRESUPUESTAL") + "+++"
                        + objResultSet.getString("CLASIFICADOR_PRESUPUESTAL") + "+++"
                        + objResultSet.getDouble("IMPORTE_ANULACION") + "+++"
                        + objResultSet.getDouble("IMPORTE_CREDITO") + "+++"
                        + objResultSet.getString("JUTIFICACION");
                Filas.add(arreglo);
                Arreglo.add("" + Filas);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaNotaModificatoriaDetalle(objBeanNotaModificatoria) : " + e.getMessage());
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
    public BeanNotaModificatoria getNotaModificatoria(BeanNotaModificatoria objBeanNotaModificatoria, String usuario) {
        sql = "SELECT NTIPO_NOTA_MODIFICATORIA_CODIGO AS TIPO_NOTA, "
                + "DNOTA_MODIFICATORIA_FECHA AS FECHA_NOTA, "
                + "VNOTA_MODIFICATORIA_JUSTIFICACION AS JUSTIFICADO "
                + "FROM IAFAS_NOTAS_MODIFICATORIAS WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NNOTA_MODIFICATORIA_CODIGO=?";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanNotaModificatoria.getPeriodo());
            objPreparedStatement.setString(2, objBeanNotaModificatoria.getCodigo());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanNotaModificatoria.setTipo(objResultSet.getString("TIPO_NOTA"));
                objBeanNotaModificatoria.setJustificacion(objResultSet.getString("JUSTIFICADO"));
                objBeanNotaModificatoria.setFecha(objResultSet.getDate("FECHA_NOTA"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getNotaModificatoria(objBeanNotaModificatoria) : " + e.getMessage());
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
        return objBeanNotaModificatoria;
    }

    @Override
    public String getNumeroNotaModificatoria(BeanNotaModificatoria objBeanNotaModificatoria, String usuario) {
        String codigo = "1";
        sql = "SELECT IFNULL(MAX(NNOTA_MODIFICATORIA_CODIGO+1),'1') AS CODIGO "
                + "FROM IAFAS_NOTAS_MODIFICATORIAS WHERE "
                + "CPERIODO_CODIGO=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanNotaModificatoria.getPeriodo());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                codigo = objResultSet.getString("CODIGO");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getNumeroNotaModificatoria(objBeanNotaModificatoria) : " + e.getMessage());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(usuario);
            objBnMsgerr.setTabla("IAFAS_NOTAS_MODIFICATORIAS");
            objBnMsgerr.setTipo(objBeanNotaModificatoria.getMode().toUpperCase());
            objBnMsgerr.setDescripcion(e.getMessage());
            s = objDsMsgerr.iduMsgerr(objBnMsgerr);
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
        return codigo;
    }

    @Override
    public int iduNotaModificatoria(BeanNotaModificatoria objBeanNotaModificatoria, String usuario) {
        sql = "{CALL SP_IDU_NOTA_MODIFICATORIA(?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanNotaModificatoria.getPeriodo());
            cs.setString(2, objBeanNotaModificatoria.getCodigo());
            cs.setString(3, objBeanNotaModificatoria.getMes());
            cs.setString(4, objBeanNotaModificatoria.getTipo());
            cs.setDate(5, objBeanNotaModificatoria.getFecha());
            cs.setString(6, objBeanNotaModificatoria.getJustificacion());
            cs.setString(7, objBeanNotaModificatoria.getEstado());
            cs.setString(8, usuario);
            cs.setString(9, objBeanNotaModificatoria.getMode());
            s = cs.executeUpdate();
            cs.close();
            s++;
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduNotaModificatoria : " + e.getMessage());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(usuario);
            objBnMsgerr.setTabla("IAFAS_NOTAS_MODIFICATORIAS");
            objBnMsgerr.setTipo(objBeanNotaModificatoria.getMode().toUpperCase());
            objBnMsgerr.setDescripcion(e.getMessage());
            s = objDsMsgerr.iduMsgerr(objBnMsgerr);
            return 0;
        }
        return s;
    }

    @Override
    public int iduNotaModificatoriaDetalle(BeanNotaModificatoria objBeanNotaModificatoria, String usuario) {
        sql = "{CALL SP_IDU_NOTA_MODIFICATORIA_DETALLE(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanNotaModificatoria.getPeriodo());
            cs.setString(2, objBeanNotaModificatoria.getCodigo());
            cs.setInt(3, objBeanNotaModificatoria.getDetalle());
            cs.setString(4, objBeanNotaModificatoria.getTipo());
            cs.setString(5, objBeanNotaModificatoria.getPresupuesto());
            cs.setInt(6, objBeanNotaModificatoria.getResolucion());
            cs.setString(7, objBeanNotaModificatoria.getTarea());
            cs.setString(8, objBeanNotaModificatoria.getCadenaGasto());
            cs.setDouble(9, objBeanNotaModificatoria.getImporte());
            cs.setString(10, objBeanNotaModificatoria.getJustificacion());
            cs.setString(11, usuario);
            cs.setString(12, objBeanNotaModificatoria.getMode());
            s = cs.executeUpdate();
            cs.close();
            s++;
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduNotaModificatoriaDetalle : " + e.getMessage());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(usuario);
            objBnMsgerr.setTabla("IAFAS_NOTAS_MODIFICATORIAS");
            objBnMsgerr.setTipo(objBeanNotaModificatoria.getMode().toUpperCase());
            objBnMsgerr.setDescripcion(e.toString());
            s = objDsMsgerr.iduMsgerr(objBnMsgerr);
            return 0;
        }
        return s;
    }

    @Override
    public int iduNotaModificatoriaDetalleMensualizacion(BeanNotaModificatoria objBeanNotaModificatoria, String usuario) {
        sql = "{CALL SP_IDU_NOTA_MODIFICATORIA_MESU(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanNotaModificatoria.getPeriodo());
            cs.setString(2, objBeanNotaModificatoria.getUnidadOperativa());
            cs.setString(3, objBeanNotaModificatoria.getCodigo());
            cs.setInt(4, objBeanNotaModificatoria.getDetalle());
            cs.setDouble(5, objBeanNotaModificatoria.getEnero());
            cs.setDouble(6, objBeanNotaModificatoria.getFebrero());
            cs.setDouble(7, objBeanNotaModificatoria.getMarzo());
            cs.setDouble(8, objBeanNotaModificatoria.getAbril());
            cs.setDouble(9, objBeanNotaModificatoria.getMayo());
            cs.setDouble(10, objBeanNotaModificatoria.getJunio());
            cs.setDouble(11, objBeanNotaModificatoria.getJulio());
            cs.setDouble(12, objBeanNotaModificatoria.getAgosto());
            cs.setDouble(13, objBeanNotaModificatoria.getSetiembre());
            cs.setDouble(14, objBeanNotaModificatoria.getOctubre());
            cs.setDouble(15, objBeanNotaModificatoria.getNoviembre());
            cs.setDouble(16, objBeanNotaModificatoria.getDiciembre());
            cs.setString(17, usuario);
            cs.setString(18, objBeanNotaModificatoria.getMode().toUpperCase());
            s = cs.executeUpdate();
            cs.close();
            s++;
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduNotaModificatoriaDetalleMensualizacion : " + e.getMessage());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(usuario);
            objBnMsgerr.setTabla("IAFAS_NOTAS_MODIFICATORIAS_MENSUA");
            objBnMsgerr.setTipo(objBeanNotaModificatoria.getMode().toUpperCase());
            objBnMsgerr.setDescripcion(e.getMessage());
            s = objDsMsgerr.iduMsgerr(objBnMsgerr);
            return 0;
        }
        return s;
    }

    @Override
    public int iduNotaModificatoriaVerifica(BeanNotaModificatoria objBeanNotaModificatoria, String usuario) {
        sql = "{CALL SP_VERIFICA_NOTA_MODIFICATORIA(?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanNotaModificatoria.getPeriodo());
            cs.setString(2, objBeanNotaModificatoria.getUnidadOperativa());
            cs.setString(3, objBeanNotaModificatoria.getCodigo());
            cs.setDate(4, objBeanNotaModificatoria.getFecha());
            cs.setString(5, objBeanNotaModificatoria.getJustificacion());
            cs.setString(6, usuario);
            cs.setString(7, objBeanNotaModificatoria.getMode());
            s = cs.executeUpdate();
            cs.close();
            s++;
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduNotaModificatoriaVerifica : " + e.getMessage());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(usuario);
            objBnMsgerr.setTabla("IAFAS_NOTAS_MODIFICATORIAS");
            objBnMsgerr.setTipo(objBeanNotaModificatoria.getMode().toUpperCase());
            objBnMsgerr.setDescripcion(e.getMessage());
            s = objDsMsgerr.iduMsgerr(objBnMsgerr);
            return 0;
        }
        return s;
    }

}
