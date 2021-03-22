/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.DataService.Despachadores.Impl;

import com.iafas.BusinessServices.Beans.BeanMesaParte;
import com.iafas.BusinessServices.Beans.BeanMsgerr;
import com.iafas.DataService.Despachadores.MesaParteDAO;
import com.iafas.DataService.Despachadores.MsgerrDAO;
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
 * @author H-TECCSI-V
 */
public class MesaParteDAOImpl implements MesaParteDAO {

    private final Connection objConnection;
    private List lista;
    private String sql;
    private ResultSet objResultSet;
    private BeanMesaParte objBnMesaParte;
    private PreparedStatement objPreparedStatement;
    private MsgerrDAO objDsMsgerr;
    private BeanMsgerr objBnMsgerr;
    private int s = 0;

    public MesaParteDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public List getListaMesaPartes(BeanMesaParte objBeanMesaParte, String usuario) {
        lista = new LinkedList<>();
        sql = "SELECT LPAD(NMESA_PARTE_CODIGO,5,0) AS CODIGO, CONCAT(`PK_UTIL.FUN_DOCUMENTO_NOMBRE`(NDOCUMENTO_CODIGO),'-',VMESA_PARTE_NUMERO) AS NUMERO, "
                + "UPPER(VMESA_PARTE_ASUNTO) AS ASUNTO, `PK_UTIL.FUN_PRIORIDAD_NOMBRE`(NPRIORIDAD_CODIGO) AS PRIORIDAD, "
                + "`PK_UTIL.FUN_INSTITUCION_NOMBRE`(NINSTITUCION_CODIGO) AS INSTITUCION, DMESA_PARTE_FECHA FECHA_DOCUMENTO, "
                + "`PK_UTIL.FUN_ESTADO_NOMBRE`(CESTADO_CODIGO) AS ESTADO, DMESA_PARTE_RECEPCION AS FECHA_RECEPCION, "
                + "UPPER(VMESA_PARTE_POST_FIRMA) AS POST_FIRMA, NMESA_PARTE_LEGAJOS AS LEGAJO, NMESA_PARTE_FOLIOS AS FOLIO, "
                + "UPPER(VMESA_PARTE_DIGITAL) AS ARCHIVO_DIGITAL  "
                + "FROM IAFAS_MESA_PARTES WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CMES_CODIGO=? AND "
                + "CMESA_PARTE_TIPO=? AND "
                + "DATE_FORMAT(DMESA_PARTE_RECEPCION,'%d')=LPAD(?,2,0) AND "
                + "CESTADO_CODIGO NOT IN ('AN') "
                + "ORDER BY CODIGO DESC";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanMesaParte.getPeriodo());
            objPreparedStatement.setString(2, objBeanMesaParte.getMes());
            objPreparedStatement.setString(3, objBeanMesaParte.getTipo());
            objPreparedStatement.setString(4, objBeanMesaParte.getNumero());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnMesaParte = new BeanMesaParte();
                objBnMesaParte.setNumero(objResultSet.getString("CODIGO"));
                objBnMesaParte.setNumeroDocumento(objResultSet.getString("NUMERO"));
                objBnMesaParte.setAsunto(objResultSet.getString("ASUNTO"));
                objBnMesaParte.setPrioridad(objResultSet.getString("PRIORIDAD"));
                objBnMesaParte.setInstitucion(objResultSet.getString("INSTITUCION"));
                objBnMesaParte.setFecha(objResultSet.getDate("FECHA_DOCUMENTO"));
                objBnMesaParte.setEstado(objResultSet.getString("ESTADO"));
                objBnMesaParte.setFechaRegistro(objResultSet.getDate("FECHA_RECEPCION"));
                objBnMesaParte.setPostFirma(objResultSet.getString("POST_FIRMA"));
                objBnMesaParte.setLegajo(objResultSet.getInt("LEGAJO"));
                objBnMesaParte.setFolio(objResultSet.getInt("FOLIO"));
                objBnMesaParte.setArchivo(objResultSet.getString("ARCHIVO_DIGITAL"));
                lista.add(objBnMesaParte);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaMesaPartes(objBeanMesaParte) : " + e.getMessage());
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
    public List getListaMesaPartesConsulta(BeanMesaParte objBeanMesaParte, String usuario) {
        lista = new LinkedList<>();
        sql = "SELECT LPAD(NMESA_PARTE_CODIGO,5,0) AS CODIGO, CONCAT(`PK_UTIL.FUN_DOCUMENTO_NOMBRE`(NDOCUMENTO_CODIGO),'-',VMESA_PARTE_NUMERO) AS NUMERO, "
                + "UPPER(VMESA_PARTE_ASUNTO) AS ASUNTO, `PK_UTIL.FUN_PRIORIDAD_NOMBRE`(NPRIORIDAD_CODIGO) AS PRIORIDAD, "
                + "`PK_UTIL.FUN_INSTITUCION_NOMBRE`(NINSTITUCION_CODIGO) AS INSTITUCION, "
                + "DMESA_PARTE_FECHA AS FECHA_MESA_PARTE, DMESA_PARTE_RECEPCION AS FECHA_RECEPCION, "
                + "`PK_UTIL.FUN_ESTADO_NOMBRE`(CESTADO_CODIGO) AS ESTADO, "
                + "UPPER(VMESA_PARTE_POST_FIRMA) AS POST_FIRMA,  "
                + "NMESA_PARTE_LEGAJOS AS LEGAJO, NMESA_PARTE_FOLIOS AS FOLIO,"
              /*  + "PK_UTIL.FUN_MESA_PARTE_USUARIO_RESPONS(CPERIODO_CODIGO, CMESA_PARTE_TIPO, NMESA_PARTE_CODIGO) AS USUARIO_RESPONABLE, "*/
                + "VMESA_PARTE_DIGITAL AS MESA_PARTE "
                + "FROM IAFAS_MESA_PARTES WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CMES_CODIGO LIKE ? AND "
                + "CMESA_PARTE_TIPO=? "
                + "ORDER BY CODIGO DESC";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanMesaParte.getPeriodo());
            objPreparedStatement.setString(2, objBeanMesaParte.getMes().replace("00", "%"));
            objPreparedStatement.setString(3, objBeanMesaParte.getTipo());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnMesaParte = new BeanMesaParte();
                objBnMesaParte.setNumero(objResultSet.getString("CODIGO"));
                objBnMesaParte.setNumeroDocumento(objResultSet.getString("NUMERO"));
                objBnMesaParte.setAsunto(objResultSet.getString("ASUNTO"));
                objBnMesaParte.setInstitucion(objResultSet.getString("INSTITUCION"));
                objBnMesaParte.setPrioridad(objResultSet.getString("PRIORIDAD"));
                objBnMesaParte.setFecha(objResultSet.getDate("FECHA_MESA_PARTE"));
                objBnMesaParte.setEstado(objResultSet.getString("ESTADO"));
                objBnMesaParte.setFechaRegistro(objResultSet.getDate("FECHA_RECEPCION"));
                objBnMesaParte.setPostFirma(objResultSet.getString("POST_FIRMA"));
                objBnMesaParte.setLegajo(objResultSet.getInt("LEGAJO"));
                objBnMesaParte.setFolio(objResultSet.getInt("FOLIO"));
             //   objBnMesaParte.setUsuarioResponsable(objResultSet.getString("USUARIO_RESPONABLE"));
                objBnMesaParte.setArchivo(objResultSet.getString("MESA_PARTE"));
                lista.add(objBnMesaParte);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaMesaPartesConsulta(objBeanMesaParte) : " + e.getMessage());
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
    public BeanMesaParte getMesaParte(BeanMesaParte objBeanMesaParte, String usuario) {
        sql = "SELECT LPAD(NMESA_PARTE_CODIGO,5,0) AS NUMERO, NINSTITUCION_CODIGO,"
                + "`PK_UTIL.FUN_INSTITUCION_NOMBRE`(NINSTITUCION_CODIGO) AS INSTITUCION, "
                + "NPRIORIDAD_CODIGO, NDOCUMENTO_CODIGO, VMESA_PARTE_NUMERO, "
                + "NCLASIFICACION_DOCUMENTO_CODIGO, DMESA_PARTE_FECHA, DMESA_PARTE_RECEPCION, "
                + "VMESA_PARTE_ASUNTO, VMESA_PARTE_POST_FIRMA, NMESA_PARTE_LEGAJOS, NMESA_PARTE_FOLIOS "
                + "FROM IAFAS_MESA_PARTES WHERE "
                + "CPERIODO_CODIGO=? AND  "
                + "CMES_CODIGO=? AND "
                + "CMESA_PARTE_TIPO=? AND  "
                + "NMESA_PARTE_CODIGO=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanMesaParte.getPeriodo());
            objPreparedStatement.setString(2, objBeanMesaParte.getMes());
            objPreparedStatement.setString(3, objBeanMesaParte.getTipo());
            objPreparedStatement.setInt(4, Utiles.checkNum(objBeanMesaParte.getNumero()));
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanMesaParte.setNumero(objResultSet.getString("NUMERO"));
                objBeanMesaParte.setInstitucion(objResultSet.getString("NINSTITUCION_CODIGO"));
                objBeanMesaParte.setReferencia(objResultSet.getString("INSTITUCION"));
                objBeanMesaParte.setPrioridad(objResultSet.getString("NPRIORIDAD_CODIGO"));
                objBeanMesaParte.setDocumento(objResultSet.getString("NDOCUMENTO_CODIGO"));
                objBeanMesaParte.setNumeroDocumento(objResultSet.getString("VMESA_PARTE_NUMERO"));
                objBeanMesaParte.setClasificacion(objResultSet.getString("NCLASIFICACION_DOCUMENTO_CODIGO"));
                objBeanMesaParte.setFecha(objResultSet.getDate("DMESA_PARTE_FECHA"));
                objBeanMesaParte.setFechaRegistro(objResultSet.getDate("DMESA_PARTE_RECEPCION"));
                objBeanMesaParte.setAsunto(objResultSet.getString("VMESA_PARTE_ASUNTO"));
                objBeanMesaParte.setPostFirma(objResultSet.getString("VMESA_PARTE_POST_FIRMA"));
                objBeanMesaParte.setLegajo(objResultSet.getInt("NMESA_PARTE_LEGAJOS"));
                objBeanMesaParte.setFolio(objResultSet.getInt("NMESA_PARTE_FOLIOS"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getMesaParte(objBeanMesaParte) : " + e.getMessage());
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
        return objBeanMesaParte;
    }

    @Override
    public String getNumeroMesaParte(BeanMesaParte objBnMesaParte, String usuario) {
        String result = "00001";
        sql = "SELECT LPAD(IFNULL(MAX(NMESA_PARTE_CODIGO)+1,1),5,0) AS CODIGO "
                + "FROM IAFAS_MESA_PARTES WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CMESA_PARTE_TIPO=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBnMesaParte.getPeriodo());
            objPreparedStatement.setString(2, objBnMesaParte.getTipo());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                result = objResultSet.getString("CODIGO");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getNumeroMesaParte(objBnMesaParte) : " + e.getMessage());
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
    public int iduMesaParte(BeanMesaParte objBeanMesaParte, String usuario) {
        sql = "{CALL SP_IDU_MESA_PARTES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanMesaParte.getPeriodo());
            cs.setString(2, objBeanMesaParte.getTipo());
            cs.setString(3, objBeanMesaParte.getNumero());
            cs.setString(4, objBeanMesaParte.getInstitucion());
            cs.setString(5, objBeanMesaParte.getPrioridad());
            cs.setString(6, objBeanMesaParte.getDocumento());
            cs.setString(7, objBeanMesaParte.getNumeroDocumento());
            cs.setString(8, objBeanMesaParte.getClasificacion());
            cs.setDate(9, objBeanMesaParte.getFecha());
            cs.setString(10, objBeanMesaParte.getAsunto());
            cs.setString(11, objBeanMesaParte.getPostFirma());
            cs.setInt(12, objBeanMesaParte.getLegajo());
            cs.setInt(13, objBeanMesaParte.getFolio());
            cs.setString(14, objBeanMesaParte.getArchivo());
            cs.setString(15, usuario);
            cs.setString(16, objBeanMesaParte.getMode().toUpperCase());
            s = cs.executeUpdate();
            cs.close();
            s++;
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduMesaParte : " + e.toString());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(usuario);
            objBnMsgerr.setTabla("IAFAS_MESA_PARTES");
            objBnMsgerr.setTipo(objBeanMesaParte.getMode().toUpperCase());
            objBnMsgerr.setDescripcion(e.toString());
            s = objDsMsgerr.iduMsgerr(objBnMsgerr);
            return 0;
        }
        return s;
    }

    @Override
    public List getListaRemisionMesaParte(BeanMesaParte objBeanMesaParte, String usuario) {
        lista = new LinkedList<>();
        sql = "SELECT CMESA_PARTE_NUMERO AS CODIGO, "
                + "PK_UTIL.FUN_NOMBRE_TIPO_MESA_PARTE(NTIPO_MESA_PARTE_CODIGO)||'-'||VMESA_PARTE_NUMERO AS NUMERO, "
                + "VMESA_PARTE_ASUNTO AS ASUNTO, PK_UTIL.FUN_NOMBRE_PRIORIDAD(NPRIORIDAD_CODIGO) AS PRIORIDAD, "
                + "PK_UTIL.FUN_INSTITUCION(NINSTITUCION_CODIGO) AS INSTITUCION, "
                + "DMESA_PARTE_FECHA AS FECHA_MESA_PARTE, PK_UTIL.FUN_DESCRIPCION_ESTADO(CESTADO_CODIGO) AS ESTADO,"
                + "VMESA_PARTE_POST_FIRMA AS FIRMA, NMESA_PARTE_LEGAJOS AS LEGAJO, NMESA_PARTE_FOLIOS AS FOLIO,"
                + "PK_UTIL.FUN_MESA_PARTE_RESPUESTA(CPERIODO_CODIGO, CMESA_PARTE_TIPO,CMESA_PARTE_NUMERO) AS DOC_RESPUESTA,"
                + "VMESA_PARTE_DIGITAL AS MESA_PARTE  "
                + "FROM OPREFA_MESA_PARTES WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CMES_CODIGO=? AND "
                + "CMESA_PARTE_TIPO=? AND "
                + "VUSUARIO_CREADOR=? AND "
                + "CESTADO_CODIGO NOT IN ('AN')"
                + "ORDER BY ESTADO DESC";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanMesaParte.getPeriodo());
            objPreparedStatement.setString(2, objBeanMesaParte.getMes());
            objPreparedStatement.setString(3, objBeanMesaParte.getTipo());
            objPreparedStatement.setString(4, usuario);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnMesaParte = new BeanMesaParte();
                objBnMesaParte.setNumero(objResultSet.getString("CODIGO"));
                objBnMesaParte.setNumeroDocumento(objResultSet.getString("NUMERO"));
                objBnMesaParte.setAsunto(objResultSet.getString("ASUNTO"));
                objBnMesaParte.setPrioridad(objResultSet.getString("PRIORIDAD"));
                objBnMesaParte.setFecha(objResultSet.getDate("FECHA_MESA_PARTE"));
                objBnMesaParte.setEstado(objResultSet.getString("ESTADO"));
                objBnMesaParte.setPostFirma(objResultSet.getString("FIRMA"));
                objBnMesaParte.setLegajo(objResultSet.getInt("LEGAJO"));
                objBnMesaParte.setFolio(objResultSet.getInt("FOLIO"));
                objBnMesaParte.setReferencia(objResultSet.getString("DOC_RESPUESTA"));
                objBnMesaParte.setArchivo(objResultSet.getString("MESA_PARTE"));
                lista.add(objBnMesaParte);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaRemisionMesaParte(objBeanMesaParte) : " + e.getMessage());
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
    public String getNumeroMesaParteSalida(BeanMesaParte objBnMesaParte, String usuario) {
        String result = "00001";
        sql = "SELECT LPAD(IFNULL(MAX(CNUMERO_MESA_PARTE)+1,1),5,0) AS CODIGO "
                + "FROM OPREFA_MESA_PARTES WHERE "
                + "CPERIODO_CODIGO = ? AND "
                + "NTIPO_MESA_PARTE_CODIGO = ? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBnMesaParte.getPeriodo());
            objPreparedStatement.setString(2, objBnMesaParte.getDocumento());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                result = objResultSet.getString("CODIGO");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getNumeroMesaParteSalida(objBnMesaParte) : " + e.getMessage());
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

}
