/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.DataService.Despachadores.Impl;

import com.iafas.BusinessServices.Beans.BeanMesaParte;
import com.iafas.BusinessServices.Beans.BeanMsgerr;
import com.iafas.DataService.Despachadores.DecretosDAO;
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
public class DecretosDAOImpl implements DecretosDAO {

    private final Connection objConnection;
    private List lista;
    private String sql;
    private ResultSet objResultSet;
    private BeanMesaParte objBnDecreto;
    private PreparedStatement objPreparedStatement;
    private MsgerrDAO objDsMsgerr;
    private BeanMsgerr objBnMsgerr;
    private int s = 0;

    public DecretosDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public List getListaDocumentosPendientes(BeanMesaParte objBeanDecreto, String usuario) {
        lista = new LinkedList<>();
        sql = "SELECT LPAD(NMESA_PARTE_CODIGO,5,0) AS CODIGO, "
                + "CONCAT(`PK_UTIL.FUN_DOCUMENTO_NOMBRE`(NDOCUMENTO_CODIGO),'-',VMESA_PARTE_NUMERO) AS NUMERO, "
                + "UPPER(VMESA_PARTE_ASUNTO) AS ASUNTO, `PK_UTIL.FUN_PRIORIDAD_NOMBRE`(NPRIORIDAD_CODIGO) AS PRIORIDAD, "
                + "`PK_UTIL.FUN_INSTITUCION_NOMBRE`(NINSTITUCION_CODIGO) AS INSTITUCION, "
                + "DMESA_PARTE_FECHA AS FECHA_MESA_PARTE, DMESA_PARTE_RECEPCION AS FECHA_RECEPCION, "
                + "UPPER(VMESA_PARTE_POST_FIRMA) AS FIRMA, "
                + "VMESA_PARTE_DIGITAL AS MESA_PARTE "
                + "FROM IAFAS_MESA_PARTES WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CMES_CODIGO=? AND "
                + "CMESA_PARTE_TIPO=? AND "
                + "CESTADO_CODIGO='PE'"
                + "ORDER BY CODIGO DESC";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanDecreto.getPeriodo());
            objPreparedStatement.setString(2, objBeanDecreto.getMes());
            objPreparedStatement.setString(3, objBeanDecreto.getTipo());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnDecreto = new BeanMesaParte();
                objBnDecreto.setNumero(objResultSet.getString("CODIGO"));
                objBnDecreto.setNumeroDocumento(objResultSet.getString("NUMERO"));
                objBnDecreto.setAsunto(objResultSet.getString("ASUNTO"));
                objBnDecreto.setPrioridad(objResultSet.getString("PRIORIDAD"));
                objBnDecreto.setInstitucion(objResultSet.getString("INSTITUCION"));
                objBnDecreto.setFecha(objResultSet.getDate("FECHA_MESA_PARTE"));
                objBnDecreto.setFechaRegistro(objResultSet.getDate("FECHA_RECEPCION"));
                objBnDecreto.setPostFirma(objResultSet.getString("FIRMA"));
                objBnDecreto.setArchivo(objResultSet.getString("MESA_PARTE"));
                lista.add(objBnDecreto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaDocumentosPendientes(objBnDecreto) : " + e.getMessage());
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
    public List getListaDocumentosDecretados(BeanMesaParte objBeanDecreto, String usuario) {
        lista = new LinkedList<>();
        sql = "SELECT LPAD(IAFAS_MESA_PARTES.NMESA_PARTE_CODIGO,5,0) AS CODIGO, IAFAS_MESA_PARTES_DECRETOS.NMESA_PARTE_DECRETO_CODIGO AS DECRETO, "
                + "CONCAT(`PK_UTIL.FUN_DOCUMENTO_NOMBRE`(IAFAS_MESA_PARTES.NDOCUMENTO_CODIGO),'-',IAFAS_MESA_PARTES.VMESA_PARTE_NUMERO) AS NUMERO, "
                + "UPPER(IAFAS_MESA_PARTES.VMESA_PARTE_ASUNTO) AS ASUNTO, `PK_UTIL.FUN_PRIORIDAD_NOMBRE`(IAFAS_MESA_PARTES_DECRETOS.NPRIORIDAD_CODIGO) AS PRIORIDAD, "
                + "`PK_UTIL.FUN_INSTITUCION_NOMBRE`(IAFAS_MESA_PARTES.NINSTITUCION_CODIGO) AS INSTITUCION, "
                + "IAFAS_MESA_PARTES.DMESA_PARTE_FECHA AS FECHA_MESA_PARTE,  "
                + "UPPER(IAFAS_MESA_PARTES_DECRETOS.VMESA_PARTE_DECRETO_DESCRIPCION) AS COMENTARIO, "
                + "`PK_UTIL.FUN_USUARIO_NOMBRE`(IAFAS_MESA_PARTES_DECRETOS.VUSUARIO_RESPONSABLE) AS USUARIO_RESPONSABLE,"
                + "IAFAS_MESA_PARTES.VMESA_PARTE_DIGITAL AS MESA_PARTE "
                + "FROM IAFAS_MESA_PARTES INNER JOIN IAFAS_MESA_PARTES_DECRETOS  ON ("
                + "IAFAS_MESA_PARTES.CPERIODO_CODIGO=IAFAS_MESA_PARTES_DECRETOS.CPERIODO_CODIGO AND "
                + "IAFAS_MESA_PARTES.CMESA_PARTE_TIPO=IAFAS_MESA_PARTES_DECRETOS.CMESA_PARTE_TIPO AND "
                + "IAFAS_MESA_PARTES.NMESA_PARTE_CODIGO=IAFAS_MESA_PARTES_DECRETOS.NMESA_PARTE_CODIGO) WHERE "
                + "IAFAS_MESA_PARTES.CPERIODO_CODIGO=? AND "
                + "IAFAS_MESA_PARTES.CMES_CODIGO=? AND "
                + "IAFAS_MESA_PARTES.CMESA_PARTE_TIPO=? AND "
                + "IAFAS_MESA_PARTES_DECRETOS.CESTADO_CODIGO!='AN' "
                + "ORDER BY CODIGO DESC";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanDecreto.getPeriodo());
            objPreparedStatement.setString(2, objBeanDecreto.getMes());
            objPreparedStatement.setString(3, objBeanDecreto.getTipo());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnDecreto = new BeanMesaParte();
                objBnDecreto.setNumero(objResultSet.getString("CODIGO"));
                objBnDecreto.setDecreto(objResultSet.getInt("DECRETO"));
                objBnDecreto.setNumeroDocumento(objResultSet.getString("NUMERO"));
                objBnDecreto.setAsunto(objResultSet.getString("ASUNTO"));
                objBnDecreto.setPrioridad(objResultSet.getString("PRIORIDAD"));
                objBnDecreto.setInstitucion(objResultSet.getString("INSTITUCION"));
                objBnDecreto.setFecha(objResultSet.getDate("FECHA_MESA_PARTE"));
                objBnDecreto.setPostFirma(objResultSet.getString("COMENTARIO"));
                objBnDecreto.setUsuarioResponsable(objResultSet.getString("USUARIO_RESPONSABLE"));
                objBnDecreto.setArchivo(objResultSet.getString("MESA_PARTE"));
                lista.add(objBnDecreto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaDocumentosDecretados(objBnDecreto) : " + e.getMessage());
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
    public List getListaDocumentosRespuesta(BeanMesaParte objBeanDocumento, String usuario) {
        lista = new LinkedList<>();
        sql = "SELECT IAFAS_MESA_PARTES.CPERIODO_CODIGO AS PERIODO, LPAD(IAFAS_MESA_PARTES.NMESA_PARTE_CODIGO,5,0) AS NUMERO, "
                + "IAFAS_MESA_PARTES_DECRETOS.NMESA_PARTE_DECRETO_CODIGO AS CODIGO, "
                + "`PK_UTIL.FUN_INSTITUCION_NOMBRE`(IAFAS_MESA_PARTES.NINSTITUCION_CODIGO) AS INSTITUCION, "
                + "CONCAT(`PK_UTIL.FUN_DOCUMENTO_NOMBRE`(IAFAS_MESA_PARTES.NDOCUMENTO_CODIGO),'-',IAFAS_MESA_PARTES.VMESA_PARTE_NUMERO) AS MESA_PARTE, "
                + "UPPER(IAFAS_MESA_PARTES.VMESA_PARTE_ASUNTO) AS ASUNTO, `PK_UTIL.FUN_PRIORIDAD_NOMBRE`(IAFAS_MESA_PARTES_DECRETOS.NPRIORIDAD_CODIGO) AS PRIORIDAD, "
                + "IAFAS_MESA_PARTES.DMESA_PARTE_FECHA AS FECHA_MESA_PARTE, "
                + "IAFAS_MESA_PARTES.VMESA_PARTE_POST_FIRMA AS POST_FIRMA, "
                + "IAFAS_MESA_PARTES.NMESA_PARTE_LEGAJOS AS LEGAJO,"
                + "IAFAS_MESA_PARTES.NMESA_PARTE_FOLIOS AS FOLIO,"
                + "IAFAS_MESA_PARTES.VMESA_PARTE_DIGITAL AS ARCHIVO, "
                + "IAFAS_MESA_PARTES_DECRETOS.VMESA_PARTE_DECRETO_DESCRIPCION AS COMENTARIO, "
                + "`PK_UTIL.FUN_ESTADO_NOMBRE`(IAFAS_MESA_PARTES_DECRETOS.CESTADO_CODIGO) AS ESTADO "
                + "FROM IAFAS_MESA_PARTES INNER JOIN IAFAS_MESA_PARTES_DECRETOS ON ("
                + "IAFAS_MESA_PARTES.CPERIODO_CODIGO=IAFAS_MESA_PARTES_DECRETOS.CPERIODO_CODIGO AND "
                + "IAFAS_MESA_PARTES.CMESA_PARTE_TIPO=IAFAS_MESA_PARTES_DECRETOS.CMESA_PARTE_TIPO AND "
                + "IAFAS_MESA_PARTES.NMESA_PARTE_CODIGO=IAFAS_MESA_PARTES_DECRETOS.NMESA_PARTE_CODIGO) WHERE "
                + "IAFAS_MESA_PARTES.CPERIODO_CODIGO=? AND "
                + "IAFAS_MESA_PARTES_DECRETOS.VUSUARIO_RESPONSABLE=? AND "
                + "IAFAS_MESA_PARTES_DECRETOS.CESTADO_CODIGO IN ('AC','RE') AND "
                + "IAFAS_MESA_PARTES.CMESA_PARTE_TIPO='E' "
                + "ORDER BY IAFAS_MESA_PARTES_DECRETOS.CESTADO_CODIGO, IAFAS_MESA_PARTES.NMESA_PARTE_CODIGO DESC";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanDocumento.getPeriodo());
            objPreparedStatement.setString(2, objBeanDocumento.getUsuarioResponsable());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnDecreto = new BeanMesaParte();
                objBnDecreto.setPeriodo(objResultSet.getString("PERIODO"));
                objBnDecreto.setNumero(objResultSet.getString("NUMERO"));
                objBnDecreto.setDecreto(objResultSet.getInt("CODIGO"));
                objBnDecreto.setInstitucion(objResultSet.getString("INSTITUCION"));
                objBnDecreto.setNumeroDocumento(objResultSet.getString("MESA_PARTE"));
                objBnDecreto.setAsunto(objResultSet.getString("ASUNTO"));
                objBnDecreto.setPrioridad(objResultSet.getString("PRIORIDAD"));
                objBnDecreto.setFecha(objResultSet.getDate("FECHA_MESA_PARTE"));
                objBnDecreto.setPostFirma(objResultSet.getString("POST_FIRMA"));
                objBnDecreto.setLegajo(objResultSet.getInt("LEGAJO"));
                objBnDecreto.setFolio(objResultSet.getInt("FOLIO"));
                objBnDecreto.setMes(objResultSet.getString("COMENTARIO"));
                objBnDecreto.setEstado(objResultSet.getString("ESTADO"));
                objBnDecreto.setArchivo(objResultSet.getString("ARCHIVO"));
                lista.add(objBnDecreto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaDocumentosRespuesta(objBeanDocumento) : " + e.getMessage());
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
    public int iduDecreto(BeanMesaParte objBnDecreto, String usuario) {
        sql = "{CALL SP_IDU_MESA_PARTES_DECRETO(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBnDecreto.getPeriodo());
            cs.setString(2, objBnDecreto.getTipo());
            cs.setString(3, objBnDecreto.getNumero());
            cs.setInt(4, objBnDecreto.getDecreto());
            cs.setString(5, objBnDecreto.getUsuario());
            cs.setString(6, objBnDecreto.getAsunto());
            cs.setString(7, objBnDecreto.getPrioridad());
            cs.setString(8, objBnDecreto.getArea());
            cs.setString(9, objBnDecreto.getUsuarioResponsable());
            cs.setString(10, usuario);
            cs.setString(11, objBnDecreto.getMode().toUpperCase());
            cs.registerOutParameter(12, java.sql.Types.VARCHAR);
            cs.executeUpdate();
            s = cs.getInt(12);
            cs.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduDecreto : " + e.getMessage());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(usuario);
            objBnMsgerr.setTabla("IAFAS_MESA_PARTES_DECRETOS");
            objBnMsgerr.setTipo(objBnDecreto.getMode().toUpperCase());
            objBnMsgerr.setDescripcion(e.getMessage());
            s = objDsMsgerr.iduMsgerr(objBnMsgerr);
            return 0;
        }
        return s;
    }

    @Override
    public int iduDecretarTipoDecreto(BeanMesaParte objBnDecreto, String usuario) {
        sql = "{CALL SP_IDU_DECRETO_TIPO_DECRETO(?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBnDecreto.getPeriodo());
            cs.setString(2, objBnDecreto.getTipo());
            cs.setString(3, objBnDecreto.getNumero());
            cs.setInt(4, objBnDecreto.getDecreto());
            cs.setString(5, objBnDecreto.getDocumento());
            cs.setString(6, usuario);
            cs.setString(7, objBnDecreto.getMode().toUpperCase());
            s = cs.executeUpdate();
            cs.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduDecretar : " + e.getMessage());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(usuario);
            objBnMsgerr.setTabla("IAFAS_MESA_PARTES_DECRETOS");
            objBnMsgerr.setTipo(objBnDecreto.getMode().toUpperCase());
            objBnMsgerr.setDescripcion(e.getMessage());
            s = objDsMsgerr.iduMsgerr(objBnMsgerr);
            return 0;
        }
        return s;
    }

    @Override
    public String getDocumentosPendientes(String usuario) {
        String result = "0";
        sql = "SELECT COUNT(*) AS MESA_PARTE "
                + "FROM IAFAS_MESA_PARTES_DECRETOS WHERE "
                + "VUSUARIO_RESPONSABLE=? AND "
                + "CESTADO_CODIGO='AC'";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, usuario);
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                result = objResultSet.getString("MESA_PARTE");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDocumentosPendientes(objBnDocumento) : " + e.getMessage());
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
        return result;
    }

    @Override
    public BeanMesaParte getDecreto(BeanMesaParte objBeanDecreto, String usuario) {
        sql = "SELECT VUSUARIO_DECRETA, NPRIORIDAD_CODIGO, CAREA_LABORAL_CODIGO, "
                + "VUSUARIO_RESPONSABLE, VMESA_PARTE_DECRETO_DESCRIPCION "
                + "FROM IAFAS_MESA_PARTES_DECRETOS WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CMESA_PARTE_TIPO=? AND "
                + "NMESA_PARTE_CODIGO=? AND "
                + "NMESA_PARTE_DECRETO_CODIGO=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanDecreto.getPeriodo());
            objPreparedStatement.setString(2, objBeanDecreto.getTipo());
            objPreparedStatement.setString(3, objBeanDecreto.getNumero());
            objPreparedStatement.setInt(4, objBeanDecreto.getDecreto());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanDecreto.setUsuario(objResultSet.getString("VUSUARIO_DECRETA"));
                objBeanDecreto.setPrioridad(objResultSet.getString("NPRIORIDAD_CODIGO"));
                objBeanDecreto.setArea(objResultSet.getString("CAREA_LABORAL_CODIGO"));
                objBeanDecreto.setUsuarioResponsable(objResultSet.getString("VUSUARIO_RESPONSABLE"));
                objBeanDecreto.setAsunto(objResultSet.getString("VMESA_PARTE_DECRETO_DESCRIPCION"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDecreto(objBeanDecreto) : " + e.getMessage());
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
        return objBeanDecreto;
    }

    @Override
    public ArrayList getListaDecretoTipoDecretos(BeanMesaParte objBeanDecreto, String usuario) {
        ArrayList<String> Arreglo = new ArrayList<>();
        sql = "SELECT NTIPO_DECRETO_CODIGO "
                + "FROM IAFAS_MESA_PARTES_DECRETOS_TIPO_DECRETO WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CMESA_PARTE_TIPO=? AND "
                + "NMESA_PARTE_CODIGO=? AND "
                + "NMESA_PARTE_DECRETO_CODIGO=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanDecreto.getPeriodo());
            objPreparedStatement.setString(2, objBeanDecreto.getTipo());
            objPreparedStatement.setString(3, objBeanDecreto.getNumero());
            objPreparedStatement.setInt(4, objBeanDecreto.getDecreto());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                Arreglo.add(objResultSet.getString("NTIPO_DECRETO_CODIGO"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaDecretoTipoDecretos(objBnDecreto) : " + e.getMessage());
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
    public ArrayList getListaDetalleDocumentoDecretado(BeanMesaParte objBeanDecreto, String usuario) {
        ArrayList<String> Arreglo = new ArrayList<>();
        ArrayList<String> Filas = new ArrayList<>();
        sql = "SELECT NMESA_PARTE_DECRETO_CODIGO AS CODIGO, "
                + "PK_UTIL.FUN_USUARIO(VUSUARIO_DECRETA) AS DECRETADOR,"
                + "PK_UTIL.FUN_USUARIO(VUSUARIO_RESPONSABLE) AS USUARIO_RESPONSABLE,"
                + "PK_UTIL.FUN_PRIORIDAD(NPRIORIDAD_CODIGO) AS PRIORIDAD,"
                + "VMESA_PARTE_DECRETO_DESCRIPCIO AS COMENTARIO, "
                + "DMESA_PARTE_DECRETO_FECHA AS FECHA_DECRETO,"
                + "DMESA_PARTE_DECRETO_RECEPCION AS FECHA_RECEPCION,"
                + "PK_UTIL.FUN_DESCRIPCION_ESTADO(CESTADO_CODIGO) AS ESTADO "
                + "FROM IAFAS_MESA_PARTES_DECRETOS WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CMESA_PARTE_TIPO=? AND "
                + "NMESA_PARTE_CODIGO=? AND "
                + "CESTADO_CODIGO!='AN' "
                + "ORDER BY CODIGO ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanDecreto.getPeriodo());
            objPreparedStatement.setString(2, objBeanDecreto.getTipo());
            objPreparedStatement.setString(3, objBeanDecreto.getNumero());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                Filas.clear();
                String arreglo = objResultSet.getString("CODIGO") + "+++"
                        + objResultSet.getString("DECRETADOR") + "+++"
                        + objResultSet.getString("USUARIO_RESPONSABLE") + "+++"
                        + objResultSet.getString("PRIORIDAD") + "+++"
                        + objResultSet.getString("COMENTARIO") + "+++"
                        + objResultSet.getDate("FECHA_DECRETO") + "+++"
                        + objResultSet.getDate("FECHA_RECEPCION") + "+++"
                        + objResultSet.getString("ESTADO");
                Filas.add(arreglo);
                Arreglo.add("" + Filas);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaDetalleDocumentoDecretado(objBnDecreto) : " + e.getMessage());
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

}
