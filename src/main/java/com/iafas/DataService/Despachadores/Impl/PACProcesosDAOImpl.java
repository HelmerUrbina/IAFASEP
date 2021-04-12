/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.DataService.Despachadores.Impl;

import com.iafas.BusinessServices.Beans.BeanMsgerr;
import com.iafas.BusinessServices.Beans.BeanPACProcesos;
import com.iafas.DataService.Despachadores.MsgerrDAO;
import com.iafas.DataService.Despachadores.PACProcesosDAO;
import com.iafas.Utiles.Utiles;
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
public class PACProcesosDAOImpl implements PACProcesosDAO {

    private final Connection objConnection;
    private List lista;
    private String sql;
    private ResultSet objResultSet;
    private BeanPACProcesos objBnPACProcesos;
    private PreparedStatement objPreparedStatement;
    private MsgerrDAO objDsMsgerr;
    private BeanMsgerr objBnMsgerr;
    private int s = 0;

    public PACProcesosDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public List getListaPACProcesos(BeanPACProcesos objBeanPACProcesos, String usuario) {
        lista = new LinkedList<>();
        sql = "SELECT NPAC_PROCESOS_CODIGO AS CODIGO, IFNULL(REPLACE(UPPER(VPAC_PROCESOS_NUMERO_PAAC),'NULL',''),' ') AS PAAC, "
                + "UPPER(VPAC_PROCESOS_DESCRIPCION) AS DESCRIPCION, NPAC_PROCESOS_MONTO_PROCESO AS MONTO_PROCESO, NPAC_PROCESOS_CERTIFICADO AS CERTIFICADO, "
                + "CASE CPAC_PROCESOS_ACOMPRAS WHEN '1' THEN 'SI' WHEN '0' THEN 'NO' ELSE 'VERIFICA' END AS COMPRA, "
                + "NTIPO_PROCESO_CONTRATACION_CODIGO, NPROCESO_DOCUMENTO_CODIGO, "
                + "`PK_UTIL.FUN_NOMBRE_TIPO_PROCESO_CONTRA`(NTIPO_PROCESO_CONTRATACION_CODIGO) AS TIPO_PROCESO_CONTRATACION, "
                + "`PK_UTIL.FUN_NOMBRE_PROCESO_ETAPA`(NTIPO_PROCESO_CONTRATACION_CODIGO, NPROCESO_ETAPA_CODIGO) AS PROCESO_ETAPA,"
                + "`PK_UTIL.FUN_NOMBRE_PROCESO_DOCUMENTO`(NPROCESO_DOCUMENTO_CODIGO) AS PROCESO_DOCUMENTO,"
                + "`PK_UTIL.FUN_NOMBRE_TIPO_PROCEDIMIENTO`(CTIPO_PROCEDIMIENTO_CODIGO) AS TIPO_PROCEDIMIENTO "
              //  + "UTIL_NEW.FUN_SOLICITUD_CREDITO_SIAF(CPERIODO_CODIGO, CUNIDAD_OPERATIVA_CODIGO, NPAC_PROCESOS_CERTIFICADO) AS SOLICITUD_CREDITO "
                + "FROM IAFAS_PAC_PROCESOS WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO DESC";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanPACProcesos.getPeriodo());
            objPreparedStatement.setInt(2, objBeanPACProcesos.getPresupuesto());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnPACProcesos = new BeanPACProcesos();
                objBnPACProcesos.setCodigo(objResultSet.getInt("CODIGO"));
                objBnPACProcesos.setNumeroPAAC(objResultSet.getString("PAAC"));
                objBnPACProcesos.setDescripcion(objResultSet.getString("DESCRIPCION"));
                objBnPACProcesos.setMontoProceso(objResultSet.getDouble("MONTO_PROCESO"));
                objBnPACProcesos.setCertificado(objResultSet.getString("CERTIFICADO"));
                objBnPACProcesos.setCompras(objResultSet.getString("COMPRA"));
                objBnPACProcesos.setPeriodo(objResultSet.getString("NTIPO_PROCESO_CONTRATACION_CODIGO"));
                objBnPACProcesos.setPresupuesto(objResultSet.getInt("NPROCESO_DOCUMENTO_CODIGO"));
                objBnPACProcesos.setTipoProcesoContratacion(objResultSet.getString("TIPO_PROCESO_CONTRATACION"));
                objBnPACProcesos.setProcesoEtapa(objResultSet.getString("PROCESO_ETAPA"));
                objBnPACProcesos.setProcesoDocumento(objResultSet.getString("PROCESO_DOCUMENTO"));
                objBnPACProcesos.setTipoProcedimiento(objResultSet.getString("TIPO_PROCEDIMIENTO"));
             //   objBnPACProcesos.setDependencia(objResultSet.getString("SOLICITUD_CREDITO"));
                lista.add(objBnPACProcesos);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaPACProcesos(objBeanPACProcesos) : " + e.getMessage());
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
    public ArrayList getListaPACProcesosDetalle(BeanPACProcesos objBeanPACProcesos, String usuario) {
        ArrayList<String> Arreglo = new ArrayList<>();
        ArrayList<String> Filas = new ArrayList<>();
        sql = "SELECT NPAC_PROCESOS_DETALLE AS DETALLE, "
                + "NVL(UTIL_NEW.FUN_DOCUMENTO_COMPROMISO(CPERIODO_CODIGO, CUNIDAD_OPERATIVA_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, VPAC_PROCESOS_COMPROMISO),'SIN COMPROMISO') AS COMPROMISO, "
                + "VPAC_PROCESOS_NUMERO_CONTRATO AS NUMERO_CONTRATO, NPAC_PROCESOS_MONTO_CONTRATO AS MONTO_CONTRATO, "
                + "CSECUENCIA_FUNCIONAL_CODIGO||':'||UTIL_NEW.FUN_DESMET(CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, CSECUENCIA_FUNCIONAL_CODIGO) AS SECUENCIA_FUNCIONAL, "
                + "CTAREA_PRESUPUESTAL_CODIGO||':'||UTIL_NEW.FUN_NTAREA(CTAREA_PRESUPUESTAL_CODIGO) AS TAREA_PRESUPUESTAL, "
                + "VCADENA_GASTO_CODIGO||':'||UTIL_NEW.FUN_NOCLAS(VCADENA_GASTO_CODIGO) AS CADENA_GASTO, "
                + "UTIL_NEW.FUN_ABRDEP(CUNIDAD_OPERATIVA_CODIGO, CDEPENDENCIA_CODIGO) AS DEPENDENCIA  "
                + "FROM IAFAS_PAC_PROCESOS_DETALLE WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? "
                + "NPAC_PROCESOS_CODIGO=?  AND "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY DETALLE";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanPACProcesos.getPeriodo());
            objPreparedStatement.setInt(2, objBeanPACProcesos.getPresupuesto());
            objPreparedStatement.setInt(3, objBeanPACProcesos.getCodigo());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                Filas.clear();
                String arreglo = objResultSet.getString("DETALLE") + "+++"
                        + objResultSet.getString("COMPROMISO") + "+++"
                        + objResultSet.getString("NUMERO_CONTRATO") + "+++"
                        + objResultSet.getDouble("MONTO_CONTRATO") + "+++"
                        + objResultSet.getString("SECUENCIA_FUNCIONAL") + "+++"
                        + objResultSet.getString("TAREA_PRESUPUESTAL") + "+++"
                        + objResultSet.getString("CADENA_GASTO") + "+++"
                        + objResultSet.getString("DEPENDENCIA");
                Filas.add(arreglo);
                Arreglo.add("" + Filas);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaPACProcesosDetalle(BeanPACProcesos) : " + e.getMessage());
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
    public ArrayList getListaPACProcesosAfectacion(BeanPACProcesos objBeanPACProcesos, String usuario) {
        ArrayList<String> Arreglo = new ArrayList<>();
        ArrayList<String> Filas = new ArrayList<>();
        sql = "SELECT NPAC_PROCESOS_AFECTACION AS DETALLE, "
                + "UTIL_NEW.FUN_ABRDEP(CUNIDAD_OPERATIVA_CODIGO, CDEPENDENCIA_CODIGO) AS DEPENDENCIA, "
                + "CSECUENCIA_FUNCIONAL_CODIGO||'-'||UTIL_NEW.FUN_DESMET(CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, CSECUENCIA_FUNCIONAL_CODIGO) AS SECUENCIA_FUNCIONAL, "
                + "CTAREA_PRESUPUESTAL_CODIGO||'-'||UTIL_NEW.FUN_NTAREA(CTAREA_PRESUPUESTAL_CODIGO) AS TAREA_PRESUPUESTAL, "
                + "VCADENA_GASTO_CODIGO||'-'||UTIL_NEW.FUN_NOCLAS(VCADENA_GASTO_CODIGO) AS CADENA_GASTO, NPAC_PROCESOS_AFECTACION_IMPO AS IMPORTE, "
                + "NTIPO_CALENDARIO_CODIGO||':'||UTIL_NEW.FUN_DESTIP(NTIPO_CALENDARIO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO) AS TIPO_CALENDARIO "
                + "FROM IAFAS_PAC_PROCESOS_AFECTACION WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "NPAC_PROCESOS_CODIGO=? "
                + "ORDER BY DETALLE";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanPACProcesos.getPeriodo());
            objPreparedStatement.setInt(2, objBeanPACProcesos.getPresupuesto());
            objPreparedStatement.setInt(3, objBeanPACProcesos.getCodigo());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                Filas.clear();
                String arreglo = objResultSet.getString("DETALLE") + "+++"
                        + objResultSet.getString("DEPENDENCIA") + "+++"
                        + objResultSet.getString("SECUENCIA_FUNCIONAL") + "+++"
                        + objResultSet.getString("TAREA_PRESUPUESTAL") + "+++"
                        + objResultSet.getString("CADENA_GASTO") + "+++"
                        + objResultSet.getDouble("IMPORTE") + "+++"
                        + objResultSet.getString("TIPO_CALENDARIO");
                Filas.add(arreglo);
                Arreglo.add("" + Filas);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaPACProcesosAfectacion(BeanPACProcesos) : " + e.getMessage());
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
    public BeanPACProcesos getPACProcesos(BeanPACProcesos objBeanPACProcesos, String usuario) {
        sql = "SELECT VPAC_PROCESOS_NUMERO_PAAC AS PAAC, NTIPO_PROCESO_CONTRATACION_CODIGO TIPO_CONTRATACION,"
                + "NPROCESO_ETAPA_CODIGO PROCESO_ETAPA, NPROCESO_DOCUMENTO_CODIGO AS PROCESO_DOCUMENTO,"
                + "CTIPO_PROCEDIMIENTO_CODIGO AS TIPO_PROCEDIMIENTO, VPAC_PROCESOS_NUMERO_PROCESO AS NUMERO_PROCESO, "
                + "VPAC_PROCESOS_DESCRIPCION AS DESCRIPCION, NPAC_PROCESOS_MONTO_PROCESO AS MONTO_PROCESO, "
                + "DPAC_PROCESOS_CONVOCATORIA AS CONVOCATORIA, DPAC_PROCESOS_PARTICIPANTES AS PARTICIPANTES, "
                + "DPAC_PROCESOS_OBSERVACIONES AS OBERVACIONES, DPAC_PROCESOS_ABSOLUCION AS ABSOLUCION, "
                + "DPAC_PROCESOS_INTEGRACION AS INTEGRACION, DPAC_PROCESOS_OFERTAS AS OFERTAS, "
                + "DPAC_PROCESOS_EVALUACION AS EVALUACION, DPAC_PROCESOS_BUENA_PRO AS BUENA_PRO, "
                + "DPAC_PROCESOS_CONSENTIMIENTO AS CONSENTIMIENTO, DPAC_PROCESOS_CONTRATO AS CONTRATO, "
                + "CPAC_PROCESOS_ACOMPRAS AS COMPRAS "
                + "FROM IAFAS_PAC_PROCESOS WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "NPAC_PROCESOS_CODIGO=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanPACProcesos.getPeriodo());
            objPreparedStatement.setInt(2, objBeanPACProcesos.getPresupuesto());
            objPreparedStatement.setInt(3, objBeanPACProcesos.getCodigo());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanPACProcesos.setNumeroPAAC(objResultSet.getString("PAAC"));
                objBeanPACProcesos.setTipoProcesoContratacion(objResultSet.getString("TIPO_CONTRATACION"));
                objBeanPACProcesos.setProcesoEtapa(objResultSet.getString("PROCESO_ETAPA"));
                objBeanPACProcesos.setProcesoDocumento(objResultSet.getString("PROCESO_DOCUMENTO"));
                objBeanPACProcesos.setTipoProcedimiento(objResultSet.getString("TIPO_PROCEDIMIENTO"));
                objBeanPACProcesos.setNumeroProceso(objResultSet.getString("NUMERO_PROCESO"));
                objBeanPACProcesos.setDescripcion(objResultSet.getString("DESCRIPCION"));
                objBeanPACProcesos.setMontoProceso(objResultSet.getDouble("MONTO_PROCESO"));
                objBeanPACProcesos.setConvocatoria(objResultSet.getDate("CONVOCATORIA"));
                objBeanPACProcesos.setParticipantes(objResultSet.getDate("PARTICIPANTES"));
                objBeanPACProcesos.setObservaciones(objResultSet.getDate("OBERVACIONES"));
                objBeanPACProcesos.setAbsolucion(objResultSet.getDate("ABSOLUCION"));
                objBeanPACProcesos.setIntegracion(objResultSet.getDate("INTEGRACION"));
                objBeanPACProcesos.setOfertas(objResultSet.getDate("OFERTAS"));
                objBeanPACProcesos.setEvaluacion(objResultSet.getDate("EVALUACION"));
                objBeanPACProcesos.setBuenaPro(objResultSet.getDate("BUENA_PRO"));
                objBeanPACProcesos.setConsentimiento(objResultSet.getDate("CONSENTIMIENTO"));
                objBeanPACProcesos.setContrato(objResultSet.getDate("CONTRATO"));
                objBeanPACProcesos.setCompras(objResultSet.getString("COMPRAS"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getPAACProceso(objBeanPACProcesos) : " + e.getMessage());
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
        return objBeanPACProcesos;
    }

    @Override
    public BeanPACProcesos getPACProcesosDetalle(BeanPACProcesos objBeanPACProcesos, String usuario) {
        sql = "SELECT VPAC_PROCESOS_COMPROMISO, VPAC_PROCESOS_NUMERO_CONTRATO , NPAC_PROCESOS_MONTO_CONTRATO, "
                + "DPAC_PROCESOS_FECHA_INICIO, DPAC_PROCESOS_FECHA_FIN, NPAC_PROCESOS_ACUMULADO, NPAC_PROCESOS_PENDIENTE, "
                + "NPAC_PROCESOS_ENERO, NPAC_PROCESOS_FEBRERO, NPAC_PROCESOS_MARZO, NPAC_PROCESOS_ABRIL, "
                + "NPAC_PROCESOS_MAYO, NPAC_PROCESOS_JUNIO, NPAC_PROCESOS_JULIO, NPAC_PROCESOS_AGOSTO, "
                + "NPAC_PROCESOS_SETIEMBRE, NPAC_PROCESOS_OCTUBRE, NPAC_PROCESOS_NOVIEMBRE, NPAC_PROCESOS_DICIEMBRE "
                + "FROM IAFAS_PAC_PROCESOS_DETALLE WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "CUNIDAD_OPERATIVA_CODIGO=? AND "
                + "NPAC_PROCESOS_DETALLE=?";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanPACProcesos.getPeriodo());
            objPreparedStatement.setInt(2, objBeanPACProcesos.getPresupuesto());
            objPreparedStatement.setInt(4, objBeanPACProcesos.getCodigo());
            objPreparedStatement.setInt(5, objBeanPACProcesos.getDetalle());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanPACProcesos.setCertificado(objResultSet.getString("VPAC_PROCESOS_COMPROMISO"));
                objBeanPACProcesos.setNumeroContrato(objResultSet.getString("VPAC_PROCESOS_NUMERO_CONTRATO"));
                objBeanPACProcesos.setMontoContrato(objResultSet.getDouble("NPAC_PROCESOS_MONTO_CONTRATO"));
                objBeanPACProcesos.setFechaInicio(objResultSet.getDate("DPAC_PROCESOS_FECHA_INICIO"));
                objBeanPACProcesos.setFechaFin(objResultSet.getDate("DPAC_PROCESOS_FECHA_FIN"));
                objBeanPACProcesos.setAcumulado(objResultSet.getDouble("NPAC_PROCESOS_ACUMULADO"));
                objBeanPACProcesos.setPendiente(objResultSet.getDouble("NPAC_PROCESOS_PENDIENTE"));
                objBeanPACProcesos.setEnero(objResultSet.getDouble("NPAC_PROCESOS_ENERO"));
                objBeanPACProcesos.setFebrero(objResultSet.getDouble("NPAC_PROCESOS_FEBRERO"));
                objBeanPACProcesos.setMarzo(objResultSet.getDouble("NPAC_PROCESOS_MARZO"));
                objBeanPACProcesos.setAbril(objResultSet.getDouble("NPAC_PROCESOS_ABRIL"));
                objBeanPACProcesos.setMayo(objResultSet.getDouble("NPAC_PROCESOS_MAYO"));
                objBeanPACProcesos.setJunio(objResultSet.getDouble("NPAC_PROCESOS_JUNIO"));
                objBeanPACProcesos.setJulio(objResultSet.getDouble("NPAC_PROCESOS_JULIO"));
                objBeanPACProcesos.setAgosto(objResultSet.getDouble("NPAC_PROCESOS_AGOSTO"));
                objBeanPACProcesos.setSetiembre(objResultSet.getDouble("NPAC_PROCESOS_SETIEMBRE"));
                objBeanPACProcesos.setOctubre(objResultSet.getDouble("NPAC_PROCESOS_OCTUBRE"));
                objBeanPACProcesos.setNoviembre(objResultSet.getDouble("NPAC_PROCESOS_NOVIEMBRE"));
                objBeanPACProcesos.setDiciembre(objResultSet.getDouble("NPAC_PROCESOS_DICIEMBRE"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getPAACProcesoDetalle(objBeanPACProcesos) : " + e.getMessage());
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
        return objBeanPACProcesos;
    }

    @Override
    public int iduPACProcesos(BeanPACProcesos objBeanEvento, String usuario) {
        /*
         * EJECUTAMOS EL PROCEDIMIENTO ALMACENADO PARA LOS PROVEEDORES, EN EL
         * CUAL PODEMOS INSERTAR, MODIFICAR O ELIMINAR UN REGISTRO DE LA TABLA
         * EN CASO DE OBTENER ALGUN ERROR ACTIVARA LA OPCION DE
         * EXCEPCIONES EN LA CUAL SE REGISTRARA EL ERROR EL MOTIVO DEL ERROR.
         */
        sql = "{CALL SP_IDU_PAC_PROCESOS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanEvento.getPeriodo());
            cs.setInt(2, objBeanEvento.getPresupuesto());
            cs.setInt(3, objBeanEvento.getCodigo());
            cs.setString(4, objBeanEvento.getNumeroPAAC());
            cs.setInt(5, Utiles.checkNum(objBeanEvento.getTipoProcesoContratacion()));
            cs.setInt(6, Utiles.checkNum(objBeanEvento.getProcesoEtapa()));
            cs.setInt(7, Utiles.checkNum(objBeanEvento.getProcesoDocumento()));
            cs.setString(8, objBeanEvento.getTipoProcedimiento());
            cs.setString(9, objBeanEvento.getDescripcion());
            cs.setString(10, objBeanEvento.getNumeroProceso());
            cs.setDouble(11, objBeanEvento.getMontoProceso());
            cs.setString(12, objBeanEvento.getCompras());
            cs.setString(13, objBeanEvento.getCertificado());
            cs.setDate(14, objBeanEvento.getConvocatoria());
            cs.setDate(15, objBeanEvento.getParticipantes());
            cs.setDate(16, objBeanEvento.getObservaciones());
            cs.setDate(17, objBeanEvento.getAbsolucion());
            cs.setDate(18, objBeanEvento.getIntegracion());
            cs.setDate(19, objBeanEvento.getOfertas());
            cs.setDate(20, objBeanEvento.getEvaluacion());
            cs.setDate(21, objBeanEvento.getBuenaPro());
            cs.setDate(22, objBeanEvento.getConsentimiento());
            cs.setDate(23, objBeanEvento.getContrato());
            cs.setString(24, usuario);
            cs.setString(25, objBeanEvento.getMode());
            s = cs.executeUpdate();
            cs.close();
            s++;
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduPACProcesos : " + e.getMessage());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(usuario);
            objBnMsgerr.setTabla("IAFAS_PAC_PROCESOS");
            objBnMsgerr.setTipo(objBeanEvento.getMode().toUpperCase());
            objBnMsgerr.setDescripcion(e.getMessage());
            s = objDsMsgerr.iduMsgerr(objBnMsgerr);
            return 0;
        }
        return s;
    }

    @Override
    public int iduPACProcesosDetalle(BeanPACProcesos objBeanEvento, String usuario) {
        /*
         * EJECUTAMOS EL PROCEDIMIENTO ALMACENADO PARA LOS PROVEEDORES, EN EL
         * CUAL PODEMOS INSERTAR, MODIFICAR O ELIMINAR UN REGISTRO DE LA TABLA
         * EN CASO DE OBTENER ALGUN ERROR ACTIVARA LA OPCION DE
         * EXCEPCIONES EN LA CUAL SE REGISTRARA EL ERROR EL MOTIVO DEL ERROR.
         */
        sql = "{CALL SP_IDU_PAC_PROCESOS_DETALLE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanEvento.getPeriodo());
            cs.setInt(2, objBeanEvento.getPresupuesto());
            cs.setInt(3, objBeanEvento.getCodigo());
            cs.setInt(4, objBeanEvento.getDetalle());
            cs.setString(5, objBeanEvento.getCertificado());
            cs.setString(6, objBeanEvento.getNumeroContrato());
            cs.setDouble(7, objBeanEvento.getMontoContrato());
            cs.setDate(8, objBeanEvento.getFechaInicio());
            cs.setDate(9, objBeanEvento.getFechaFin());
            cs.setDouble(10, objBeanEvento.getAcumulado());
            cs.setDouble(11, objBeanEvento.getPendiente());
            cs.setDouble(12, objBeanEvento.getEnero());
            cs.setDouble(13, objBeanEvento.getFebrero());
            cs.setDouble(14, objBeanEvento.getMarzo());
            cs.setDouble(15, objBeanEvento.getAbril());
            cs.setDouble(16, objBeanEvento.getMayo());
            cs.setDouble(17, objBeanEvento.getJunio());
            cs.setDouble(18, objBeanEvento.getJulio());
            cs.setDouble(19, objBeanEvento.getAgosto());
            cs.setDouble(20, objBeanEvento.getSetiembre());
            cs.setDouble(21, objBeanEvento.getOctubre());
            cs.setDouble(22, objBeanEvento.getNoviembre());
            cs.setDouble(23, objBeanEvento.getDiciembre());
            cs.setString(24, usuario);
            cs.setString(25, objBeanEvento.getMode());
            s = cs.executeUpdate();
            cs.close();
            s++;
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduPACProcesosDetalle : " + e.getMessage());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(usuario);
            objBnMsgerr.setTabla("IAFAS_PAC_PROCESOS_DETALLE");
            objBnMsgerr.setTipo(objBeanEvento.getMode().toUpperCase());
            objBnMsgerr.setDescripcion(e.getMessage());
            s = objDsMsgerr.iduMsgerr(objBnMsgerr);
            return 0;
        }
        return s;
    }

    @Override
    public int iduPACProcesosAfectacion(BeanPACProcesos objBeanEvento, String usuario) {
        /*
         * EJECUTAMOS EL PROCEDIMIENTO ALMACENADO PARA LOS PROVEEDORES, EN EL
         * CUAL PODEMOS INSERTAR, MODIFICAR O ELIMINAR UN REGISTRO DE LA TABLA
         * EN CASO DE OBTENER ALGUN ERROR ACTIVARA LA OPCION DE
         * EXCEPCIONES EN LA CUAL SE REGISTRARA EL ERROR EL MOTIVO DEL ERROR.
         */
        sql = "{CALL SP_IDU_PAC_PROCESOS_AFECTACIO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanEvento.getPeriodo());
            cs.setInt(2, objBeanEvento.getPresupuesto());
            cs.setInt(3, objBeanEvento.getCodigo());
            cs.setInt(4, objBeanEvento.getDetalle());
            cs.setString(5, objBeanEvento.getTipoCalendario());
            cs.setString(6, objBeanEvento.getResolucion());
            cs.setString(7, objBeanEvento.getDependencia());
            cs.setString(8, objBeanEvento.getSecuenciaFuncional());
            cs.setString(9, objBeanEvento.getTareaPresupuestal());
            cs.setString(10, objBeanEvento.getCadenaGasto());
            cs.setDouble(11, objBeanEvento.getMontoProceso());
            cs.setDouble(12, objBeanEvento.getEnero());
            cs.setDouble(13, objBeanEvento.getFebrero());
            cs.setDouble(14, objBeanEvento.getMarzo());
            cs.setDouble(15, objBeanEvento.getAbril());
            cs.setDouble(16, objBeanEvento.getMayo());
            cs.setDouble(17, objBeanEvento.getJunio());
            cs.setDouble(18, objBeanEvento.getJulio());
            cs.setDouble(19, objBeanEvento.getAgosto());
            cs.setDouble(20, objBeanEvento.getSetiembre());
            cs.setDouble(21, objBeanEvento.getOctubre());
            cs.setDouble(22, objBeanEvento.getNoviembre());
            cs.setDouble(23, objBeanEvento.getDiciembre());
            cs.setString(24, usuario);
            cs.setString(25, objBeanEvento.getMode());
            s = cs.executeUpdate();
            cs.close();
            s++;
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduPACProcesosAfectacion : " + e.getMessage());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(usuario);
            objBnMsgerr.setTabla("IAFAS_PAC_PROCESOS_AFECTACION");
            objBnMsgerr.setTipo(objBeanEvento.getMode().toUpperCase());
            objBnMsgerr.setDescripcion(e.getMessage());
            s = objDsMsgerr.iduMsgerr(objBnMsgerr);
            return 0;
        }
        return s;
    }

    @Override
    public int iduPACProcesosDetalleContratoAfectacion(BeanPACProcesos objBeanEvento, String usuario) {
        /*
         * EJECUTAMOS EL PROCEDIMIENTO ALMACENADO PARA LOS PROVEEDORES, EN EL
         * CUAL PODEMOS INSERTAR, MODIFICAR O ELIMINAR UN REGISTRO DE LA TABLA
         * EN CASO DE OBTENER ALGUN ERROR ACTIVARA LA OPCION DE
         * EXCEPCIONES EN LA CUAL SE REGISTRARA EL ERROR EL MOTIVO DEL ERROR.
         */
        sql = "{CALL SP_IDU_PAC_PROCESOS_DET_AFECT(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanEvento.getPeriodo());
            cs.setInt(2, objBeanEvento.getPresupuesto());
            cs.setInt(3, objBeanEvento.getCodigo());
            cs.setInt(4, objBeanEvento.getDetalle());
            cs.setString(5, objBeanEvento.getTipoCalendario());
            cs.setString(6, objBeanEvento.getResolucion());
            cs.setString(7, objBeanEvento.getDependencia());
            cs.setString(8, objBeanEvento.getSecuenciaFuncional());
            cs.setString(9, objBeanEvento.getTareaPresupuestal());
            cs.setString(10, objBeanEvento.getCadenaGasto());
            cs.setString(11, usuario);
            cs.setString(12, objBeanEvento.getMode());
            s = cs.executeUpdate();
            cs.close();
            s++;
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduPACProcesosDetalleContratoAfectacion : " + e.getMessage());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(usuario);
            objBnMsgerr.setTabla("IAFAS_PAC_PROCESOS_DETALLE");
            objBnMsgerr.setTipo(objBeanEvento.getMode().toUpperCase());
            objBnMsgerr.setDescripcion(e.getMessage());
            s = objDsMsgerr.iduMsgerr(objBnMsgerr);
            return 0;
        }
        return s;
    }

}
