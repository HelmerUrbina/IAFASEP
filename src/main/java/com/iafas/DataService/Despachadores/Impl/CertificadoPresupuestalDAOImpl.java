/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.DataService.Despachadores.Impl;

import com.iafas.BusinessServices.Beans.BeanMsgerr;
import com.iafas.BusinessServices.Beans.BeanEjecucionPresupuestal;
import com.iafas.DataService.Despachadores.MsgerrDAO;
import com.iafas.DataService.Despachadores.CertificadoPresupuestalDAO;
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
public class CertificadoPresupuestalDAOImpl implements CertificadoPresupuestalDAO {

    private final Connection objConnection;
    private List lista;
    private String sql;
    private ResultSet objResultSet;
    private BeanEjecucionPresupuestal objBnCertificado;
    private PreparedStatement objPreparedStatement;
    private MsgerrDAO objDsMsgerr;
    private BeanMsgerr objBnMsgerr;
    private int s = 0;

    public CertificadoPresupuestalDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public List getListaCertificados(BeanEjecucionPresupuestal objBeanCertificado, String usuario) {
        lista = new LinkedList<>();
        sql = "SELECT NCERTIFICADO_CODIGO, NCERTIFICADO_ANEXO, UPPER(VCERTIFICADO_DOCUMENTO) AS VCERTIFICADO_DOCUMENTO, "
                + "UPPER(VCERTIFICADO_CONCEPTO) AS VCERTIFICADO_CONCEPTO, IFNULL(DCERTIFICADO_FECHA,'DD/MM/YYYY HH24:MM') AS FECHA, "
                + "CASE CCERTIFICADO_TIPO_REGISTRO WHEN 'RE' THEN (-1)*NCERTIFICADO_IMPORTE ELSE NCERTIFICADO_IMPORTE END AS IMPORTE, NCERTIFICADO_TIPO_CAMBIO, "
                + "CASE CCERTIFICADO_TIPO_REGISTRO WHEN 'RE' THEN (-1)*NCERTIFICADO_EXTRANJERA ELSE NCERTIFICADO_EXTRANJERA END AS EXTRANJERA, "
                + "CASE CESTADO_CODIGO WHEN 'PE' THEN 'PENDIENTE' WHEN 'CE' THEN 'CERRADO' WHEN 'AT' THEN 'ATENDIDO' WHEN 'AN' THEN 'ANULADA' ELSE ' ' END AS ESTADO, "
                + "CASE CCERTIFICADO_TIPO_REGISTRO WHEN 'CE' THEN 'CERTIFICADO' WHEN 'AM' THEN 'AMPLIACION' WHEN 'RE' THEN 'REBAJA' ELSE '' END AS TIP_SOL, "
                + "NINFORME_DISPONIBILIDAD_CODIGO, "
                + "`PK_UTIL.FUN_PAC_PROCESO_DESCRIPCION`(CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, NPAC_PROCESOS_CODIGO) AS PROCESO, "
                + "VCERTIFICADO_ARCHIVO AS ARCHIVO "
                + "FROM IAFAS_CERTIFICADO_PRESUPUESTAL WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? "
                + "ORDER BY NCERTIFICADO_CODIGO DESC ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanCertificado.getPeriodo());
            objPreparedStatement.setInt(2, objBeanCertificado.getPresupuesto());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnCertificado = new BeanEjecucionPresupuestal();
                objBnCertificado.setCertificado(objResultSet.getString("NCERTIFICADO_CODIGO"));
                objBnCertificado.setAnexoCertificado(objResultSet.getString("NCERTIFICADO_ANEXO"));
                objBnCertificado.setDocumentoReferencia(objResultSet.getString("VCERTIFICADO_DOCUMENTO"));
                objBnCertificado.setConcepto(objResultSet.getString("VCERTIFICADO_CONCEPTO"));
                objBnCertificado.setFecha(objResultSet.getDate("FECHA"));
                objBnCertificado.setImporte(objResultSet.getDouble("IMPORTE"));
                objBnCertificado.setTipoCambio(objResultSet.getDouble("NCERTIFICADO_TIPO_CAMBIO"));
                objBnCertificado.setMonedaExtranjera(objResultSet.getDouble("EXTRANJERA"));
                objBnCertificado.setEstado(objResultSet.getString("ESTADO"));
                objBnCertificado.setTipo(objResultSet.getString("TIP_SOL"));
                objBnCertificado.setDependencia(objResultSet.getString("NINFORME_DISPONIBILIDAD_CODIGO"));
                objBnCertificado.setProcesoSeleccion(objResultSet.getString("PROCESO"));
                objBnCertificado.setArchivo(objResultSet.getString("ARCHIVO"));
                lista.add(objBnCertificado);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaCertificados(BeanCertificadoCreditoPresupuestal) : " + e.getMessage());
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
    public List getListaCertificadosDetalle(BeanEjecucionPresupuestal objBeanCertificado, String usuario) {
        lista = new LinkedList<>();
        sql = "SELECT NCERTIFICADO_CODIGO, `PK_UTIL.FUN_RESOLUCION_DESCRIPCION`(CPERIODO_CODIGO, NRESOLUCION_CODIGO) AS RESOLUCION,  "
                + "`PK_UTIL.FUN_TAREA_PRESUPUESTAL_NOMBRE`(NTAREA_PRESUPUESTAL_CODIGO) AS TAREA_PRESUPUESTAL, "
                + "`PK_UTIL.FUN_CLASIFICA_PRESUPUESTAL_NOMBRE`(NCLASIFICADOR_PRESUPUESTAL_CODIGO) AS CLASIFICADOR_PRESUPUESTAL, "
                + "NCERTIFICADO_DETALLE_IMPORTE, NCERTIFICADO_DETALLE_TIPO_CAMBIO, NCERTIFICADO_DETALLE_EXTRANJERA "
                + "FROM IAFAS_CERTIFI_PRESUPUESTAL_DETAL WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? "
                + "ORDER BY NCERTIFICADO_CODIGO, RESOLUCION, TAREA_PRESUPUESTAL, CLASIFICADOR_PRESUPUESTAL";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanCertificado.getPeriodo());
            objPreparedStatement.setInt(2, objBeanCertificado.getPresupuesto());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnCertificado = new BeanEjecucionPresupuestal();
                objBnCertificado.setCertificado(objResultSet.getString("NCERTIFICADO_CODIGO"));
                objBnCertificado.setResolucion(objResultSet.getString("RESOLUCION"));
                objBnCertificado.setTareaPresupuestal(objResultSet.getString("TAREA_PRESUPUESTAL"));
                objBnCertificado.setCadenaGasto(objResultSet.getString("CLASIFICADOR_PRESUPUESTAL"));
                objBnCertificado.setImporte(objResultSet.getDouble("NCERTIFICADO_DETALLE_IMPORTE"));
                objBnCertificado.setTipoCambio(objResultSet.getDouble("NCERTIFICADO_DETALLE_TIPO_CAMBIO"));
                objBnCertificado.setMonedaExtranjera(objResultSet.getDouble("NCERTIFICADO_DETALLE_EXTRANJERA"));
                lista.add(objBnCertificado);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaCertificadosDetalle(BeanCertificadoCreditoPresupuestal) : " + e.getMessage());
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
    public ArrayList getListaCertificadoDetalle(BeanEjecucionPresupuestal objBeanCertificado, String usuario) {
        ArrayList<String> Arreglo = new ArrayList<>();
        ArrayList<String> Filas = new ArrayList<>();
        sql = "SELECT CONCAT(NRESOLUCION_CODIGO,'---',NTAREA_PRESUPUESTAL_CODIGO,'---',NCLASIFICADOR_PRESUPUESTAL_CODIGO) AS CODIGO, "
                + "`PK_UTIL.FUN_RESOLUCION_DESCRIPCION`(CPERIODO_CODIGO, NRESOLUCION_CODIGO) AS RESOLUCION,  "
                + "`PK_UTIL.FUN_TAREA_PRESUPUESTAL_NOMBRE`(NTAREA_PRESUPUESTAL_CODIGO) AS TAREA_PRESUPUESTAL, "
                + "`PK_UTIL.FUN_CLASIFICA_PRESUPUESTAL_NOMBRE`(NCLASIFICADOR_PRESUPUESTAL_CODIGO) AS CLASIFICADOR_PRESUPUESTAL, "
                + "NCERTIFICADO_DETALLE_IMPORTE AS IMPORTE, NCERTIFICADO_DETALLE_EXTRANJERA AS MONEDA_EXTRANJERA "
                + "FROM IAFAS_CERTIFI_PRESUPUESTAL_DETAL WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "NCERTIFICADO_CODIGO=? "
                + "ORDER BY NRESOLUCION_CODIGO, NTAREA_PRESUPUESTAL_CODIGO, NCLASIFICADOR_PRESUPUESTAL_CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanCertificado.getPeriodo());
            objPreparedStatement.setInt(2, objBeanCertificado.getPresupuesto());
            objPreparedStatement.setString(3, objBeanCertificado.getCertificado());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                Filas.clear();
                String arreglo = objResultSet.getString("CODIGO") + "+++"
                        + objResultSet.getString("RESOLUCION") + "+++"
                        + objResultSet.getString("TAREA_PRESUPUESTAL") + "+++"
                        + objResultSet.getString("CLASIFICADOR_PRESUPUESTAL") + "+++"
                        + objResultSet.getDouble("IMPORTE") + "+++"
                        + objResultSet.getDouble("MONEDA_EXTRANJERA");
                Filas.add(arreglo);
                Arreglo.add("" + Filas);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaCertificadoDetalle(BeanCertificadoCreditoPresupuestal) : " + e.getMessage());
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
    public String getNumeroCertificado(BeanEjecucionPresupuestal objBeanCertificado, String usuario) {
        String codigo = "";
        sql = "SELECT LPAD(IFNULL(MAX(NCERTIFICADO_CODIGO),0)+1,7,0) AS CODIGO "
                + "FROM IAFAS_CERTIFICADO_PRESUPUESTAL WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanCertificado.getPeriodo());
            objPreparedStatement.setInt(2, objBeanCertificado.getPresupuesto());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                codigo = objResultSet.getString("CODIGO");
            } else {
                codigo = "0000001";
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getNumeroCertificado(BeanCertificadoCreditoPresupuestal) : " + e.getMessage());
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
    public BeanEjecucionPresupuestal getCertificado(BeanEjecucionPresupuestal objBeanCertificado, String usuario) {
        sql = "SELECT NCERTIFICADO_CODIGO, NCERTIFICADO_ANEXO, NINFORME_DISPONIBILIDAD_CODIGO AS NINFORME_DISPONIBILIDAD_CODIGO, "
                + "DCERTIFICADO_FECHA, NPAC_PROCESOS_CODIGO, "
                + "IFNULL(VCERTIFICADO_DOCUMENTO, ' ') AS VCERTIFICADO_DOCUMENTO, "
                + "IFNULL(VCERTIFICADO_CONCEPTO, ' ') AS VCERTIFICADO_CONCEPTO, "
                + "IFNULL(VCERTIFICADO_OBSERVACION, ' ') AS VCERTIFICADO_OBSERVACION, "
                + "NCERTIFICADO_IMPORTE, NCERTIFICADO_TIPO_CAMBIO, NCERTIFICADO_EXTRANJERA, "
                + "NMONEDA_CODIGO, IFNULL(NCERTIFICADO_ANEXO, ' ') AS NCERTIFICADO_ANEXO "
                + "FROM IAFAS_CERTIFICADO_PRESUPUESTAL WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "NCERTIFICADO_CODIGO=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanCertificado.getPeriodo());
            objPreparedStatement.setInt(2, objBeanCertificado.getPresupuesto());
            objPreparedStatement.setString(3, objBeanCertificado.getCertificado());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanCertificado.setCertificado(objResultSet.getString("NCERTIFICADO_CODIGO"));
                objBeanCertificado.setAnexoCertificado(objResultSet.getString("NCERTIFICADO_ANEXO"));
                objBeanCertificado.setDocumentoReferencia(objResultSet.getString("NINFORME_DISPONIBILIDAD_CODIGO"));
                objBeanCertificado.setFecha(objResultSet.getDate("DCERTIFICADO_FECHA"));
                objBeanCertificado.setProcesoSeleccion(objResultSet.getString("NPAC_PROCESOS_CODIGO"));
                objBeanCertificado.setDocumentoReferencia(objResultSet.getString("VCERTIFICADO_DOCUMENTO"));
                objBeanCertificado.setConcepto(objResultSet.getString("VCERTIFICADO_CONCEPTO"));
                objBeanCertificado.setObservacion(objResultSet.getString("VCERTIFICADO_OBSERVACION"));
                objBeanCertificado.setImporte(objResultSet.getDouble("NCERTIFICADO_IMPORTE"));
                objBeanCertificado.setTipoMoneda(objResultSet.getString("NMONEDA_CODIGO"));
                objBeanCertificado.setTipoCambio(objResultSet.getDouble("NCERTIFICADO_TIPO_CAMBIO"));
                objBeanCertificado.setMonedaExtranjera(objResultSet.getDouble("NCERTIFICADO_EXTRANJERA"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getCertificado(BeanCertificadoCreditoPresupuestal) : " + e.getMessage());
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
        return objBeanCertificado;
    }

    @Override
    public String iduCertificado(BeanEjecucionPresupuestal objBnCertificado, String usuario) {
        String codigo = "";
        /*
         * EJECUTAMOS EL PROCEDIMIENTO ALMACENADO PARA LOS PROVEEDORES, EN EL
         * CUAL PODEMOS INSERTAR, MODIFICAR O ELIMINAR UN REGISTRO DE LA TABLA
         * USUARIO, EN CASO DE OBTENER ALGUN ERROR ACTIVARA LA OPCION DE
         * EXCEPCIONES EN LA CUAL SE REGISTRARA EL ERROR EL MOTIVO DEL ERROR.
         */
        sql = "{CALL SP_IDU_CERTIFICADO_PRESUPUESTAL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBnCertificado.getPeriodo());
            cs.setInt(2, objBnCertificado.getPresupuesto());
            cs.setString(3, objBnCertificado.getCertificado());
            cs.setString(4, objBnCertificado.getTipo());
            cs.setString(5, objBnCertificado.getProcesoSeleccion());
            cs.setString(6, objBnCertificado.getAnexoCertificado());
            cs.setString(7, objBnCertificado.getDisponibilidadPresupuestal());
            cs.setString(8, objBnCertificado.getDocumentoReferencia());
            cs.setString(9, objBnCertificado.getConcepto());
            cs.setString(10, objBnCertificado.getObservacion());
            cs.setDate(11, objBnCertificado.getFecha());
            cs.setString(12, objBnCertificado.getTipoMoneda());
            cs.setDouble(13, objBnCertificado.getTipoCambio());
            cs.setString(14, usuario);
            cs.setString(15, objBnCertificado.getMode());
            cs.registerOutParameter(16, java.sql.Types.VARCHAR);
            cs.executeUpdate();
            codigo = cs.getString(16);
            cs.close();
            s++;
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduCertificado : " + e.getMessage());
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(usuario);
            objBnMsgerr.setTabla("IAFAS_CERTIFICADO_PRESUPUESTAL");
            objBnMsgerr.setTipo(objBnCertificado.getMode().toUpperCase());
            objBnMsgerr.setDescripcion(e.getMessage());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            s = objDsMsgerr.iduMsgerr(objBnMsgerr);
            return "0";
        }
        return codigo;
    }

    @Override
    public int iduCertificadoDetalle(BeanEjecucionPresupuestal objBeanCertificado, String usuario) {
        /*
         * EJECUTAMOS EL PROCEDIMIENTO ALMACENADO PARA LOS PROVEEDORES, EN EL
         * CUAL PODEMOS INSERTAR, MODIFICAR O ELIMINAR UN REGISTRO DE LA TABLA
         * USUARIO, EN CASO DE OBTENER ALGUN ERROR ACTIVARA LA OPCION DE
         * EXCEPCIONES EN LA CUAL SE REGISTRARA EL ERROR EL MOTIVO DEL ERROR.
         */
        sql = "{CALL SP_IDU_CERTIFICADO_PRESUPUESTAL_DETALLE(?,?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanCertificado.getPeriodo());
            cs.setInt(2, objBeanCertificado.getPresupuesto());
            cs.setString(3, objBeanCertificado.getCertificado());
            cs.setString(4, objBeanCertificado.getResolucion());
            cs.setString(5, objBeanCertificado.getTareaPresupuestal());
            cs.setString(6, objBeanCertificado.getCadenaGasto());
            cs.setDouble(7, objBeanCertificado.getImporte());
            cs.setDouble(8, objBeanCertificado.getTipoCambio());
            cs.setDouble(9, objBeanCertificado.getMonedaExtranjera());
            cs.setString(10, usuario);
            cs.setString(11, objBeanCertificado.getMode());
            s = cs.executeUpdate();
            cs.close();
            s++;
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduCertificadoDetalle : " + e.getMessage());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(usuario);
            objBnMsgerr.setTabla("IAFAS_CERTIFICADO_PRESUPUESTAL");
            objBnMsgerr.setTipo(objBeanCertificado.getMode().toUpperCase());
            objBnMsgerr.setDescripcion(e.getMessage());
            s = objDsMsgerr.iduMsgerr(objBnMsgerr);
            return 0;
        }
        return s;
    }
}
