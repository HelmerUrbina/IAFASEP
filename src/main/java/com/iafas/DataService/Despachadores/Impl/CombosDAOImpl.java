/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.DataService.Despachadores.Impl;

import com.iafas.BusinessServices.Beans.BeanComun;
import com.iafas.DataService.Despachadores.CombosDAO;
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
public class CombosDAOImpl implements CombosDAO {

    private final Connection objConnection;
    private ResultSet objResultSet;
    private PreparedStatement objPreparedStatement;
    private String sql;
    private List lista;
    private BeanComun comun;

    public CombosDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    //CONSULTAS GENERALES
    @Override
    public List getPeriodos() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA PERIODO POR ORDEN DESCENDENTES Y LO
         * ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CPERIODO_CODIGO AS CODIGO "
                + "FROM IAFAS_PERIODOS WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO DESC";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getPeriodos() " + e.getMessage());
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
    public List getMeses() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA MESES POR ORDEN ASCENDENTE Y
         * LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CMES_CODIGO AS CODIGO, VMES_DESCRIPCION AS DESCRIPCION "
                + "FROM IAFAS_MESES "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getMeses() " + e.getMessage());
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
    public List getFuenteFinanciamiento() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL PRESUPUESTO O FUENTE DE FINANCIAMIENTO POR
         * ORDEN DESCENDENTES Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NFUENTE_FINANCIAMIENTO_CODIGO AS CODIGO, "
                + "CONCAT(NFUENTE_FINANCIAMIENTO_CODIGO,':',VFUENTE_FINANCIAMIENTO_NOMBRE) AS DESCRIPCION "
                + "FROM IAFAS_FUENTE_FINANCIAMIENTO WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getFuenteFinanciamiento() " + e.getMessage());
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
    public List getProductos() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL PRESUPUESTO O FUENTE DE FINANCIAMIENTO POR
         * ORDEN DESCENDENTES Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CPRODUCTO_CODIGO AS CODIGO, "
                + "CPRODUCTO_CODIGO||':'||VPRODUCTO_NOMBRE AS DESCRIPCION "
                + "FROM IAFAS_PRODUCTOS WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getProductos() " + e.getMessage());
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
    public List getActividades() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL PRESUPUESTO O FUENTE DE FINANCIAMIENTO POR
         * ORDEN DESCENDENTES Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CACTIVIDAD_CODIGO AS CODIGO, "
                + "CACTIVIDAD_CODIGO||':'||VACTIVIDAD_NOMBRE AS DESCRIPCION "
                + "FROM IAFAS_ACTIVIDADES WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getActividades() " + e.getMessage());
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
    public List getCategoriaPresupuestal() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA DISTRITO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CCATEGORIA_PRESUPUESTAL_CODIGO AS CODIGO, "
                + "CCATEGORIA_PRESUPUESTAL_CODIGO||':'||VCATEGORIA_PRESUPUESTAL_NOMBRE AS DESCRIPCION "
                + "FROM IAFAS_CATEGORIA_PRESUPUESTAL WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getCategoriaPresupuestal() " + e.getMessage());
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
    public List getTarea() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL PRESUPUESTO O FUENTE DE FINANCIAMIENTO POR
         * ORDEN DESCENDENTES Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NTAREA_PRESUPUESTAL_CODIGO AS CODIGO, "
                + "CONCAT(NTAREA_PRESUPUESTAL_CODIGO,':',VTAREA_PRESUPUESTAL_ABREVIATURA) AS DESCRIPCION "
                + "FROM IAFAS_TAREA_PRESUPUESTAL WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY NTAREA_PRESUPUESTAL_CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTarea() " + e.getMessage());
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
    public List getGenericaGasto() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL PRESUPUESTO O FUENTE DE FINANCIAMIENTO POR
         * ORDEN DESCENDENTES Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT COGEGA AS CODIGO, COGEGA||'-'||NOGEGA AS DESCRIPCION "
                + "FROM GENGAS WHERE "
                + "COTITR='2' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getGenericaGasto() " + e.getMessage());
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
    public List getGenericaGasto(String periodo, Integer presupuesto) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL PRESUPUESTO O FUENTE DE FINANCIAMIENTO POR
         * ORDEN DESCENDENTES Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT TRIM(CODGEN) AS CODIGO, TRIM(CODGEN)||':'||UTIL_NEW.FUN_NOMGEN(TRIM(CODGEN)) AS DESCRIPCION "
                + "FROM MODEPA WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? "
                + "GROUP BY TRIM(CODGEN) "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getGenericaGasto(" + periodo + ", " + presupuesto + ") " + e.getMessage());
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
    public List getTipoMonedas() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA DE TIPO DE MONEDA 
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NMONEDA_CODIGO CODIGO, CONCAT(SUBSTRING(NMONEDA_DESCRIPCION,0,20),' ',VMONEDA_SIMBOLO) AS DESCRIPCION "
                + "FROM IAFAS_MONEDAS WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
            objResultSet.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener getTipoMonedas() " + e.getMessage());
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
    public List getProveedores() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA DE UNIDAD OPERATIVA 
         * ORDEN DESCENDENTES Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT COUUOO CODIGO, COUUOO||':'||ABUUOO AS DESCRIPCION "
                + "FROM TAUUOO "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getProveedores() " + e.getMessage());
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
    public List getUnidadMedida() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA DE UNIDAD OPERATIVA 
         * ORDEN DESCENDENTES Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CUNIDAD_MEDIDA_CODIGO AS CODIGO, VUNIDAD_MEDIDA_NOMBRE AS DESCRIPCION "
                + "FROM IAFAS_UNIDAD_MEDIDA WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY DESCRIPCION";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getUnidadMedida() " + e.getMessage());
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
    public List getCadenaGasto() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NCLASIFICADOR_PRESUPUESTAL_CODIGO AS CODIGO, "
                + "CONCAT(VCLASIFICADOR_PRESUPUESTAL_CADENA,':',VCLASIFICADOR_PRESUPUESTAL_NOMBRE) AS DESCRIPCION "
                + "FROM IAFAS_CLASIFICADOR_PRESUPUESTAL WHERE "
                + "CTIPO_TRANSACCION_CODIGO='2' "
                + "ORDER BY VCLASIFICADOR_PRESUPUESTAL_CADENA ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getCadenaGasto() " + e.getMessage());
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
    public List getCadenaIngreso() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT COTITR||'.'||COGEGA||'.'||COGEG1||'.'||COGEG2||'.'||COESG1||'.'||COESG2 AS CODIGO, "
                + "COTITR||'.'||COGEGA||'.'||COGEG1||'.'||COGEG2||'.'||COESG1||'.'||COESG2||':'||NOCAGA AS DESCRIPCION "
                + "FROM CADGAS WHERE "
                + "COTITR='1' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getCadenaIngreso() " + e.getMessage());
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
    public List getAreaLaboral() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA AREA LABORAL
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CAREA_LABORAL_CODIGO AS CODIGO, VAREA_LABORAL_DESCRIPCION AS DESCRIPCION "
                + "FROM IAFAS_AREA_LABORAL WHERE "
                + "CESTADO_CODIGO!='AN' "
                + "ORDER BY DESCRIPCION";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getAreaLaboral() " + e.getMessage());
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
    public List getTipoResolucion() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA AREA LABORAL
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CTIPO_RESOLUCION_CODIGO AS CODIGO, VDESCRIPCION_RESOLUCION AS DESCRIPCION "
                + "FROM SIPE_TIPO_RESOLUCION WHERE "
                + "CTIPO_RESOLUCION_ESTADO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTipoResolucion() " + e.getMessage());
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
    public List getGrado() {
        lista = new LinkedList<>();
        sql = "SELECT CODGRD AS CODIGO, ABREVITURA AS DESCRIPCION "
                + "FROM TABGRD "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getGrado() " + e.getMessage());
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
    public List getGrados(String nivel) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA AREA LABORAL
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CODGRD AS CODIGO, DESCRIPCION AS DESCRIPCION "
                + "FROM TABGRD WHERE "
                + "NNIVEL_GRADO_CODIGO=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, nivel);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getGrados(" + nivel + ") " + e.getMessage());
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
    public List getParentesco() {
        lista = new LinkedList<>();
        sql = "SELECT CCODIGO_PARENTESCO AS CODIGO,"
                + "VDESCRIPCION_PARENTESCO AS DESCRIPCION "
                + "FROM SIPE_PARENTESCO "
                + "ORDER BY CODIGO ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getParentesco() " + e.getMessage());
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
    public List getDistritoMovilidad() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA DISTRITO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CODUBI AS CODIGO,"
                + "CASE CODUBI WHEN '150101' THEN 'CERCADO DE LIMA' ELSE DESUBI END AS DESCRIPCION "
                + "FROM TABUBI WHERE "
                + " CODUBI IN ('150108','150113',"
                + "'150101','150128','070101') "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDistritoMovilidad() " + e.getMessage());
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

    //SEGURIDAD
    @Override
    public List getModulos() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA DISTRITO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CMODULO_CODIGO AS CODIGO, VMODULO_NOMBRE AS DESCRIPCION "
                + "FROM IAFAS_MODULOS WHERE "
                + "CESTADO_CODIGO!='AN' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDirecciones() " + e.getMessage());
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

    //PLANEAMIENTO
    @Override
    public List getObjetivosEstrategicos(String periodo) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NOEI_CODIGO AS CODIGO, NOEI_PRIORIDAD||'-'||VOEI_ABREVIATURA AS DESCRIPCION "
                + "FROM IAFAS_PEI_OBJETIVOS_ESTRATEGI WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CESTADO_CODIGO!='AN' "
                + "ORDER BY NOEI_PRIORIDAD";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getObjetivosEstrategicos(" + periodo + ") " + e.getMessage());
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
    public List getAccionesEstrategicas(String periodo, String objetivo) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NAEI_CODIGO AS CODIGO, NAEI_PRIORIDAD||'-'||VAEI_ABREVIATURA AS DESCRIPCION "
                + "FROM IAFAS_PEI_ACCIONES_ESTRATEGIC WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NOEI_CODIGO=? AND "
                + "CESTADO_CODIGO!='AN' "
                + "ORDER BY NAEI_PRIORIDAD";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, objetivo);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getAccionesEstrategicas(" + periodo + "," + objetivo + ") " + e.getMessage());
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
    public List getTareaOperativa() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NTAREA_OPERATIVA_CODIGO AS CODIGO, VTAREA_OPERATIVA_ABREVIATURA AS DESCRIPCION "
                + "FROM IAFAS_TAREA_OPERATIVA "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTareaOperativa() " + e.getMessage());
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
    public List getUbigeoActividadPresupuestal(String periodo, String categoriaPresupuestal, String producto, String actividad) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT DISTINCT CODUBI AS CODIGO, NOMDEP||' - '||NOMUBI AS "
                + "DESCRIPCION FROM TACAFU_PRG WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "COPRES=? AND "
                + "CODCOM=? AND "
                + "CODACT=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, categoriaPresupuestal);
            objPreparedStatement.setString(3, producto);
            objPreparedStatement.setString(4, actividad);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getUbigeoActividadPresupuestal(" + periodo + ", " + categoriaPresupuestal + ", " + producto + ", " + actividad + ") " + e.getMessage());
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
    public List getPAACProcesos(String periodo, Integer presupuesto, String tipoCertificado) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        if (tipoCertificado.equals("CE")) {
            sql = "SELECT NPAC_PROCESOS_CODIGO AS CODIGO, CONCAT(VPAC_PROCESOS_NUMERO_PAAC,':',VPAC_PROCESOS_DESCRIPCION,' S/ ',FORMAT(NPAC_PROCESOS_MONTO_PROCESO,2))  AS DESCRIPCION "
                    + "FROM IAFAS_PAC_PROCESOS WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "CESTADO_CODIGO='AC' AND "
                    + "NPAC_PROCESOS_CODIGO NOT IN (SELECT DISTINCT NPAC_PROCESOS_CODIGO FROM IAFAS_CERTIFICADO_PRESUPUESTAL WHERE "
                    + "CPERIODO_CODIGO='" + periodo + "' AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO='" + presupuesto + "' ) "
                    + "ORDER BY DESCRIPCION";
        }
        if (tipoCertificado.equals("AM") || tipoCertificado.equals("RE")) {
            sql = "SELECT NPAC_PROCESOS_CODIGO AS CODIGO, CONCAT(VPAC_PROCESOS_NUMERO_PAAC,':',VPAC_PROCESOS_DESCRIPCION,' S/ ',FORMAT(NPAC_PROCESOS_MONTO_PROCESO,2)) AS DESCRIPCION "
                    + "FROM IAFAS_PAC_PROCESOS WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "CESTADO_CODIGO='AC' "
                    + "ORDER BY DESCRIPCION ";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getPAACProcesos(" + periodo + ", " + presupuesto + ", " + tipoCertificado + ") " + e.getMessage());
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
    public List getIDPCertificado(String periodo, Integer presupuesto, String unidadOperativa) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NUMOPI AS CODIGO, NUMOPI||':'||UTIL_NEW.FUN_CONCEPTO_INFORME(CPERIODO_CODIGO, NUMOPI) "
                + "||' S/. '||TO_CHAR(SUM(IMPORTE),'FM999,999,999,999.009') AS DESCRIPCION "
                + "FROM V_INFORME_SOLICITUD WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "COUUOO=? "
                + "GROUP BY CPERIODO_CODIGO, NUMOPI "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objPreparedStatement.setString(3, unidadOperativa);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getIDPCertificado(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ") " + e.getMessage());
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
    public List getResolucionesCertificado(String periodo, Integer presupuesto,
            String tipoCertificado, String solicitudCredito, String opinionPresupuestal) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        if (tipoCertificado.equals("CE") || tipoCertificado.equals("AM")) {
            if (opinionPresupuestal == null || opinionPresupuestal.trim().equals("0")) {
                sql = "SELECT NRESOLUCION_CODIGO AS CODIGO, CONCAT(NRESOLUCION_CODIGO,':',`PK_UTIL.FUN_RESOLUCION_DESCRIPCION`(CPERIODO_CODIGO, NRESOLUCION_CODIGO), "
                        + "' S/. ',FORMAT(SUM(PIM-CERTIFICADO),2)) AS DESCRIPCION "
                        + "FROM V_CERTIFICADO_PRESUPUESTAL WHERE "
                        + "CPERIODO_CODIGO=? AND "
                        + "NFUENTE_FINANCIAMIENTO_CODIGO=? "
                        + "GROUP BY CPERIODO_CODIGO, NRESOLUCION_CODIGO "
                        + "HAVING SUM(PIM-CERTIFICADO)>0.0  "
                        + "ORDER BY CODIGO";
            } else {
                sql = "SELECT RESOLUCION AS CODIGO, RESOLUCION||':'||UTIL.FUN_DESCRIPCION_RESOLUCION(CPERIODO_CODIGO,RESOLUCION) "
                        + "||' S/. '||TO_CHAR(SUM(IMPORTE),'FM999,999,999,999.009') AS DESCRIPCION "
                        + "FROM V_INFORME_SOLICITUD WHERE "
                        + "CPERIODO_CODIGO=? AND "
                        + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                        + "NUMOPI='" + opinionPresupuestal + "' "
                        + "GROUP BY CPERIODO_CODIGO,RESOLUCION "
                        + "ORDER BY CODIGO";
            }
        } else {
            sql = "SELECT RESOL AS CODIGO, RESOL||':'||UTIL.FUN_DESCRIPCION_RESOLUCION(CPERIODO_CODIGO, RESOL) "
                    + "||' S/. '||TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                    + "FROM V_TASOCP WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "COUUOO=? AND "
                    + "TRIM(CODTIP)=TRIM(?) AND "
                    + "NROCER='" + solicitudCredito + "'  "
                    + "GROUP BY RESOL,CPERIODO_CODIGO "
                    + "ORDER BY CODIGO ";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getResolucionesCertificado(" + periodo + ", " + presupuesto + ", " + tipoCertificado + ", " + solicitudCredito + ", " + opinionPresupuestal + ") " + e.getMessage());
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
    public List getTareaCertificado(String periodo, Integer presupuesto, Integer resolucion,
            String tipoCertificado, String solicitudCredito, String opinionPresupuestal) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        if (tipoCertificado.equals("CE") || tipoCertificado.equals("AM")) {
            if (opinionPresupuestal == null || opinionPresupuestal.trim().equals("0")) {
                sql = "SELECT NTAREA_PRESUPUESTAL_CODIGO AS CODIGO, "
                        + "CONCAT(NTAREA_PRESUPUESTAL_CODIGO,':',`PK_UTIL.FUN_TAREA_PRESUPUESTAL_NOMBRE`(NTAREA_PRESUPUESTAL_CODIGO),' S/. ',"
                        + "FORMAT(SUM(PIM-CERTIFICADO),2)) AS DESCRIPCION "
                        + "FROM V_CERTIFICADO_PRESUPUESTAL WHERE "
                        + "CPERIODO_CODIGO=? AND "
                        + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                        + "NRESOLUCION_CODIGO=?  "
                        + "GROUP BY NTAREA_PRESUPUESTAL_CODIGO "
                        + "HAVING SUM(PIM-CERTIFICADO)>0.0  "
                        + "ORDER BY CODIGO";
            } else {
                sql = "SELECT NTAREA_PRESUPUESTAL_CODIGO AS CODIGO, "
                        + "NTAREA_PRESUPUESTAL_CODIGO||':'||UTIL_NEW.FUN_NTAREA(NTAREA_PRESUPUESTAL_CODIGO)||' S/. '||"
                        + "TO_CHAR(SUM(IMPORTE),'FM999,999,999,999.009') AS DESCRIPCION "
                        + "FROM V_INFORME_SOLICITUD WHERE "
                        + "CPERIODO_CODIGO=? AND "
                        + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                        + "COUUOO=? AND "
                        + "TRIM(2)=? AND "
                        + "RESOLUCION=? AND "
                        + "CODDEP=? AND "
                        + "SECFUN=? AND "
                        + "NUMOPI='" + opinionPresupuestal + "' "
                        + "GROUP BY NTAREA_PRESUPUESTAL_CODIGO "
                        + "ORDER BY CODIGO";
            }
        } else {
            sql = "SELECT NTAREA_PRESUPUESTAL_CODIGO AS CODIGO, "
                    + "NTAREA_PRESUPUESTAL_CODIGO||':'||UTIL_NEW.FUN_NTAREA(NTAREA_PRESUPUESTAL_CODIGO)||' S/. '||"
                    + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                    + "FROM V_TASOCP WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "COUUOO=? AND "
                    + "TRIM(CODTIP)=TRIM(?) AND "
                    + "RESOL=? AND "
                    + "CODDEP=? AND "
                    + "SECFUN=? AND "
                    + "NROCER='" + solicitudCredito + "'  "
                    + "GROUP BY NTAREA_PRESUPUESTAL_CODIGO "
                    + "ORDER BY CODIGO ";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objPreparedStatement.setInt(3, resolucion);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTareaCertificado(" + periodo + ", " + presupuesto + ", "
                    + resolucion + ", " + tipoCertificado + ", "
                    + solicitudCredito + ", " + opinionPresupuestal + ") " + e.getMessage());
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
    public List getCadenaGastoCertificado(String periodo, Integer presupuesto, Integer resolucion,
            String tarea, String tipoCertificado, String solicitudCredito, String opinionPresupuestal) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        if (tipoCertificado.equals("CE") || tipoCertificado.equals("AM")) {
            if (opinionPresupuestal == null || opinionPresupuestal.trim().equals("0")) {
                sql = "SELECT NCLASIFICADOR_PRESUPUESTAL_CODIGO AS CODIGO, "
                        + "CONCAT(`PK_UTIL.FUN_CLASIFICA_PRESUPUESTAL_NOMBRE`(NCLASIFICADOR_PRESUPUESTAL_CODIGO),' S/. ',"
                        + "FORMAT(SUM(PIM-CERTIFICADO),2)) AS DESCRIPCION "
                        + "FROM V_CERTIFICADO_PRESUPUESTAL WHERE "
                        + "CPERIODO_CODIGO=? AND "
                        + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                        + "NRESOLUCION_CODIGO=? AND "
                        + "NTAREA_PRESUPUESTAL_CODIGO=? "
                        + "GROUP BY NCLASIFICADOR_PRESUPUESTAL_CODIGO "
                        + "ORDER BY CODIGO";
            } else {
                sql = "SELECT NCLASIFICADOR_PRESUPUESTAL_CODIGO AS CODIGO, "
                        + "NCLASIFICADOR_PRESUPUESTAL_CODIGO||':'||UTIL_NEW.FUN_NOCLAS(NCLASIFICADOR_PRESUPUESTAL_CODIGO)||' S/. '||"
                        + "TO_CHAR(SUM(IMPORTE),'FM999,999,999,999.009') AS DESCRIPCION "
                        + "FROM V_INFORME_SOLICITUD WHERE "
                        + "CPERIODO_CODIGO=? AND "
                        + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                        + "COUUOO=? AND "
                        + "TRIM(2)=? AND "
                        + "RESOLUCION=? AND "
                        + "CODDEP=? AND "
                        + "SECFUN=? AND "
                        + "NTAREA_PRESUPUESTAL_CODIGO=? AND "
                        + "NUMOPI='" + opinionPresupuestal + "' "
                        + "GROUP BY NCLASIFICADOR_PRESUPUESTAL_CODIGO "
                        + "ORDER BY CODIGO";
            }
        } else {
            sql = "SELECT NCLASIFICADOR_PRESUPUESTAL_CODIGO AS CODIGO, "
                    + "NCLASIFICADOR_PRESUPUESTAL_CODIGO||':'||PK_UTIL.FUN_CLASIFICA_PRESUPUESTAL_NOM(NCLASIFICADOR_PRESUPUESTAL_CODIGO)||' S/. '||"
                    + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                    + "FROM V_TASOCP WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "COUUOO=? AND "
                    + "TRIM(CODTIP)=TRIM(?) AND "
                    + "RESOL=? AND "
                    + "CODDEP=? AND "
                    + "SECFUN=? AND "
                    + "NTAREA_PRESUPUESTAL_CODIGO=? AND "
                    + "NROCER='" + solicitudCredito + "'  "
                    + "GROUP BY NCLASIFICADOR_PRESUPUESTAL_CODIGO "
                    + "ORDER BY CODIGO ";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objPreparedStatement.setInt(3, resolucion);
            objPreparedStatement.setString(4, tarea);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getCadenaGastoCertificado(" + periodo + ", " + presupuesto
                    + resolucion + ", " + tarea + ", " + tipoCertificado + ", "
                    + solicitudCredito + ", " + opinionPresupuestal + ") " + e.getMessage());
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
    public List getSolicitudCreditoUnidad(String periodo, Integer presupuesto, String unidadOperativa) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NROCER AS CODIGO,"
                + "UTIL_NEW.FUN_TAMTEXT_COMBO(NROCER||':'||"
                + "UTIL_NEW.FUN_DOCREF_TASOCP(CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, COUUOO, NROCER),100)||' S/. '||TRIM(TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009')) AS DESCRIPCION "
                + "FROM V_COMPROMISO_ANUAL WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "COUUOO=? "
                + "GROUP BY CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, COUUOO, NROCER "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objPreparedStatement.setString(3, unidadOperativa);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getSolicitudCreditoUnidad(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ") " + e.getMessage());
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
    public List getProveedorCompromiso(String periodo, Integer presupuesto, String unidadOperativa, String compromisoAnual, String tipo) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        if (tipo.equals("RE")) {
            sql = "SELECT COUOAB AS CODIGO, COUOAB||':'||UTIL.FUN_ABUUOO(COUOAB) AS DESCRIPCION "
                    + "FROM V_TASOCP_CA WHERE "
                    + "CPERIODO_CODIGO='" + periodo + "' AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO='" + presupuesto + "' AND "
                    + "COUUOO='" + unidadOperativa + "' AND "
                    + "NROCER='" + compromisoAnual + "' "
                    + "GROUP BY COUOAB "
                    + "ORDER BY CODIGO";
        } else {
            if (unidadOperativa.equals("0003") || unidadOperativa.equals("6999") || unidadOperativa.equals("0820") || unidadOperativa.equals("0790") || unidadOperativa.equals("0750") || unidadOperativa.equals("0794")
                    || unidadOperativa.equals("0702") || unidadOperativa.equals("0770") || unidadOperativa.equals("0772") || unidadOperativa.equals("0732") || unidadOperativa.equals("0730") || unidadOperativa.equals("0752")
                    || unidadOperativa.equals("0720") || unidadOperativa.equals("0734") || unidadOperativa.equals("0728") || unidadOperativa.equals("0810") || unidadOperativa.equals("0824") || unidadOperativa.equals("0787")
                    || unidadOperativa.equals("0750") || unidadOperativa.equals("0846") || unidadOperativa.equals("0822") || unidadOperativa.equals("0754") || unidadOperativa.equals("0756") || unidadOperativa.equals("0775")
                    || unidadOperativa.equals("0832") || unidadOperativa.equals("0865") || unidadOperativa.equals("0872") || unidadOperativa.equals("0868") || unidadOperativa.equals("0738") || unidadOperativa.equals("0870")
                    || unidadOperativa.equals("0797") || unidadOperativa.equals("0858") || unidadOperativa.equals("6998") || unidadOperativa.equals("0785") || unidadOperativa.equals("0777") || unidadOperativa.equals("0833")) {
                sql = "SELECT COUUOO AS CODIGO, COUUOO||':'||ABUUOO AS DESCRIPCION "
                        + "FROM TAUUOO WHERE COUUOO NOT IN ('4109') "
                        + "ORDER BY COUUOO";
            } else {
                sql = "SELECT COUUOO AS CODIGO, COUUOO||':'||ABUUOO AS DESCRIPCION "
                        + "FROM TAUUOO WHERE "
                        + "TIUUOO <> 'P' AND "
                        + "COUUOO IN ('" + unidadOperativa + "','4001','1000','4288','4363') ORDER BY COUUOO";
            }
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getProveedorCompromiso(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ") " + e.getMessage());
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
    public List getResolucionesCompromiso(String periodo, Integer presupuesto, String unidadOperativa, String tipoCalendario, String solicitudCredito,
            String proveedor, String tipo, String compromisoAnual) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        if (tipo.equals("RE")) {
            sql = "SELECT RESOL AS CODIGO, UTIL_NEW.FUN_TAMTEXT_COMBO(RESOL||':'||UTIL.FUN_DESCRIPCION_RESOLUCION(CPERIODO_CODIGO,RESOL),60)||' S/. '||"
                    + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                    + "FROM V_TASOCP_CA WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "COUUOO=? AND "
                    + "TRIM(CODTIP)=? AND "
                    + "NROCER=? AND "
                    + "COUOAB='" + proveedor + "' "
                    + "GROUP BY RESOL, CPERIODO_CODIGO "
                    + "ORDER BY CODIGO ";
            solicitudCredito = compromisoAnual;
        } else {
            sql = "SELECT RESOL AS CODIGO, UTIL_NEW.FUN_TAMTEXT_COMBO(RESOL||':'||UTIL.FUN_DESCRIPCION_RESOLUCION(CPERIODO_CODIGO,RESOL),60)||' S/. '||"
                    + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                    + "FROM V_COMPROMISO_ANUAL WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "COUUOO=? AND "
                    + "TRIM(CODTIP)=? AND "
                    + "NROCER=? "
                    + "GROUP BY RESOL, CPERIODO_CODIGO "
                    + "ORDER BY CODIGO ";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objPreparedStatement.setString(3, unidadOperativa);
            objPreparedStatement.setString(4, tipoCalendario);
            objPreparedStatement.setString(5, solicitudCredito);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getResolucionesCompromiso(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ", "
                    + tipoCalendario + ", " + solicitudCredito + ", " + proveedor + ", " + tipo + ", " + compromisoAnual + ")" + e.getMessage());
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
    public List getDependenciaCompromiso(String periodo, Integer presupuesto, String unidadOperativa, String tipoCalendario, Integer resolucion,
            String solicitudCredito, String proveedor, String tipo, String compromisoAnual) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        if (tipo.equals("RE")) {
            sql = "SELECT CODDEP AS CODIGO, "
                    + "CODDEP||':'||UTIL_NEW.FUN_ABRDEP(COUUOO, CODDEP)||' S/. '||"
                    + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                    + "FROM V_TASOCP_CA WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "COUUOO=? AND "
                    + "TRIM(CODTIP)=? AND "
                    + "NROCER=? AND "
                    + "RESOL=? AND "
                    + "COUOAB='" + proveedor + "' "
                    + "GROUP BY COUUOO, CODDEP "
                    + "ORDER BY CODIGO ";
            solicitudCredito = compromisoAnual;
        } else {
            sql = "SELECT  CODDEP AS CODIGO, "
                    + "CODDEP||':'||UTIL_NEW.FUN_ABRDEP(COUUOO, CODDEP)||' S/. '||"
                    + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                    + "FROM V_COMPROMISO_ANUAL WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "COUUOO=? AND "
                    + "TRIM(CODTIP)=TRIM(?) AND "
                    + "NROCER=? AND "
                    + "RESOL=? "
                    + "GROUP BY COUUOO, CODDEP "
                    + "ORDER BY CODIGO ";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objPreparedStatement.setString(3, unidadOperativa);
            objPreparedStatement.setString(4, tipoCalendario);
            objPreparedStatement.setString(5, solicitudCredito);
            objPreparedStatement.setInt(6, resolucion);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDependenciaCompromiso(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ", "
                    + tipoCalendario + ", " + solicitudCredito + "," + resolucion + ", " + proveedor + ", " + tipo + ", " + compromisoAnual + ")" + e.getMessage());
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
    public List getSecuenciaFuncionalCompromiso(String periodo, Integer presupuesto, String unidadOperativa, String tipoCalendario, Integer resolucion,
            String dependencia, String solicitudCredito, String proveedor, String tipo, String compromisoAnual) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        if (tipo.equals("RE")) {
            sql = "SELECT SECFUN AS CODIGO, "
                    + "SECFUN||':'||UTIL_NEW.FUN_DESMET(CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, SECFUN)||' S/. '||"
                    + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                    + "FROM V_TASOCP_CA WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "COUUOO=? AND "
                    + "TRIM(CODTIP)=TRIM(?) AND "
                    + "NROCER=? AND "
                    + "RESOL=? AND "
                    + "CODDEP=? AND "
                    + "COUOAB='" + proveedor + "' "
                    + "GROUP BY CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, SECFUN "
                    + "ORDER BY CODIGO ";
            solicitudCredito = compromisoAnual;
        } else {
            sql = "SELECT SECFUN AS CODIGO, "
                    + "SECFUN||':'||UTIL_NEW.FUN_DESMET(CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, SECFUN)||' S/. '||"
                    + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                    + "FROM V_COMPROMISO_ANUAL WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "COUUOO=? AND "
                    + "TRIM(CODTIP)=TRIM(?) AND "
                    + "NROCER=? AND "
                    + "RESOL=? AND "
                    + "CODDEP=? "
                    + "GROUP BY CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, SECFUN "
                    + "ORDER BY CODIGO ";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objPreparedStatement.setString(3, unidadOperativa);
            objPreparedStatement.setString(4, tipoCalendario);
            objPreparedStatement.setString(5, solicitudCredito);
            objPreparedStatement.setInt(6, resolucion);
            objPreparedStatement.setString(7, dependencia);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getSecuenciaFuncionalCompromiso(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ", "
                    + tipoCalendario + ", " + resolucion + ", " + dependencia + ", " + solicitudCredito + ", " + proveedor + ", " + tipo + ", " + compromisoAnual + ")" + e.getMessage()
            );
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
    public List getTareaCompromiso(String periodo, Integer presupuesto, String unidadOperativa, String tipoCalendario, Integer resolucion,
            String dependencia, String secuenciaFuncional, String solicitudCredito, String proveedor, String tipo, String compromisoAnual) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        if (tipo.equals("RE")) {
            sql = "SELECT NTAREA_PRESUPUESTAL_CODIGO AS CODIGO, "
                    + "NTAREA_PRESUPUESTAL_CODIGO||':'||UTIL_NEW.FUN_NTAREA(NTAREA_PRESUPUESTAL_CODIGO)||' S/. '||"
                    + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                    + "FROM V_TASOCP_CA WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "COUUOO=? AND "
                    + "TRIM(CODTIP)=TRIM(?) AND "
                    + "NROCER=? AND "
                    + "RESOL=? AND "
                    + "CODDEP=? AND "
                    + "SECFUN=? AND "
                    + "COUOAB='" + proveedor + "' "
                    + "GROUP BY NTAREA_PRESUPUESTAL_CODIGO "
                    + "ORDER BY CODIGO ";
            solicitudCredito = compromisoAnual;
        } else {
            sql = "SELECT NTAREA_PRESUPUESTAL_CODIGO AS CODIGO, "
                    + "NTAREA_PRESUPUESTAL_CODIGO||':'||UTIL_NEW.FUN_NTAREA(NTAREA_PRESUPUESTAL_CODIGO)||' S/. '||"
                    + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                    + "FROM V_COMPROMISO_ANUAL WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "COUUOO=? AND "
                    + "TRIM(CODTIP)=TRIM(?) AND "
                    + "NROCER=? AND "
                    + "RESOL=? AND "
                    + "CODDEP=? AND "
                    + "SECFUN=? "
                    + "GROUP BY NTAREA_PRESUPUESTAL_CODIGO "
                    + "ORDER BY CODIGO ";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objPreparedStatement.setString(3, unidadOperativa);
            objPreparedStatement.setString(4, tipoCalendario);
            objPreparedStatement.setString(5, solicitudCredito);
            objPreparedStatement.setInt(6, resolucion);
            objPreparedStatement.setString(7, dependencia);
            objPreparedStatement.setString(8, secuenciaFuncional);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTareaCompromiso(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ", "
                    + tipoCalendario + ", " + resolucion + ", " + dependencia + ", " + secuenciaFuncional + ", " + solicitudCredito + ", "
                    + proveedor + ", " + tipo + ", " + compromisoAnual + ")" + e.getMessage()
            );
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
    public List getCadenaGastoCompromiso(String periodo, Integer presupuesto, String unidadOperativa, String tipoCalendario, Integer resolucion,
            String dependencia, String secuenciaFuncional, String tarea, String solicitudCredito, String proveedor, String tipo, String compromisoAnual) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        if (tipo.equals("RE")) {
            sql = "SELECT NCLASIFICADOR_PRESUPUESTAL_CODIGO AS CODIGO, "
                    + "NCLASIFICADOR_PRESUPUESTAL_CODIGO||':'||UTIL_NEW.FUN_NOCLAS(NCLASIFICADOR_PRESUPUESTAL_CODIGO)||' S/. '||"
                    + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                    + "FROM V_TASOCP_CA WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "COUUOO=? AND "
                    + "TRIM(CODTIP)=TRIM(?) AND "
                    + "NROCER=? AND "
                    + "RESOL=? AND "
                    + "CODDEP=? AND "
                    + "SECFUN=? AND "
                    + "NTAREA_PRESUPUESTAL_CODIGO=? AND "
                    + "COUOAB='" + proveedor + "' "
                    + "GROUP BY NCLASIFICADOR_PRESUPUESTAL_CODIGO "
                    + "ORDER BY CODIGO ";
            solicitudCredito = compromisoAnual;
        } else {
            sql = "SELECT NCLASIFICADOR_PRESUPUESTAL_CODIGO AS CODIGO, "
                    + "NCLASIFICADOR_PRESUPUESTAL_CODIGO||':'||UTIL_NEW.FUN_NOCLAS(NCLASIFICADOR_PRESUPUESTAL_CODIGO)||' S/. '||"
                    + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                    + "FROM V_COMPROMISO_ANUAL WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "COUUOO=? AND "
                    + "TRIM(CODTIP)=TRIM(?) AND "
                    + "NROCER=? AND "
                    + "RESOL=? AND "
                    + "CODDEP=? AND "
                    + "SECFUN=? AND "
                    + "NTAREA_PRESUPUESTAL_CODIGO=? "
                    + "GROUP BY NCLASIFICADOR_PRESUPUESTAL_CODIGO "
                    + "ORDER BY CODIGO ";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objPreparedStatement.setString(3, unidadOperativa);
            objPreparedStatement.setString(4, tipoCalendario);
            objPreparedStatement.setString(5, solicitudCredito);
            objPreparedStatement.setInt(6, resolucion);
            objPreparedStatement.setString(7, dependencia);
            objPreparedStatement.setString(8, secuenciaFuncional);
            objPreparedStatement.setString(9, tarea);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getCadenaGastoCompromiso(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ", "
                    + tipoCalendario + ", " + resolucion + ", " + dependencia + ", " + secuenciaFuncional + ", " + tarea + ", "
                    + ", " + solicitudCredito + ", " + proveedor + ", " + tipo + ", " + compromisoAnual + ")" + e.getMessage()
            );
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

    //EJECUCION PRESUPUESTAL - DECLARACION JURADA
    @Override
    public List getCompromisoAnualUnidad(String periodo, Integer presupuesto, String unidadOperativa) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NROCER AS CODIGO,"
                + "NROCER||':'||UTIL_NEW.FUN_DOCUMENTO_COMPROMISO(CPERIODO_CODIGO, COUUOO, NFUENTE_FINANCIAMIENTO_CODIGO, NROCER)||"
                + "' S/. '||TRIM(TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009')) AS DESCRIPCION "
                + "FROM V_DECLA_JURA_CA WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "COUUOO=? AND "
                + "NROCER IS NOT NULL "
                + "GROUP BY NROCER, CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, COUUOO "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objPreparedStatement.setString(3, unidadOperativa);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getCompromisoAnualUnidad(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ") " + e.getMessage());
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
    public List getProveedorDeclaracionJurada(String periodo, Integer presupuesto, String unidadOperativa, String compromisoAnual, String tipoCalendario,
            String cobertura) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT COUOAB AS CODIGO, UTIL_NEW.FUN_TAMTEXT_COMBO(COUOAB||':'||UTIL.FUN_ABUUOO(COUOAB),60)||' S/. '||"
                + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                + "FROM V_TASOCP_CA WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "COUUOO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "NROCER=? AND "
                + "TRIM(CODTIP)='" + tipoCalendario + "' "
                + "GROUP BY COUOAB "
                + "ORDER BY CODIGO";
        if (tipoCalendario.equals("50")) {
            compromisoAnual = cobertura;
            sql = "SELECT UUOO AS CODIGO, UTIL_NEW.FUN_TAMTEXT_COMBO(UUOO||':'||UTIL.FUN_ABUUOO(UUOO),60)||' S/. '||"
                    + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                    + "FROM V_COMCAB_REVERSION WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "COUUOO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "NROCOB=? "
                    + "GROUP BY UUOO "
                    + "ORDER BY CODIGO";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, unidadOperativa);
            objPreparedStatement.setInt(3, presupuesto);
            objPreparedStatement.setString(4, compromisoAnual);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getProveedorDeclaracionJurada(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ", " + compromisoAnual + ", " + tipoCalendario + ", " + cobertura + ") " + e.getMessage());
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
    public List getResolucionesDeclaracionJurada(String periodo, Integer presupuesto, String unidadOperativa, String compromisoAnual, String tipoCalendario,
            String cobertura, String proveedor) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT RESOL AS CODIGO, UTIL_NEW.FUN_TAMTEXT_COMBO(RESOL||':'||UTIL.FUN_DESCRIPCION_RESOLUCION(CPERIODO_CODIGO, RESOL),60)||' S/. '||"
                + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                + "FROM V_TASOCP_CA WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "COUUOO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "NROCER=? AND "
                + "TRIM(CODTIP)='" + tipoCalendario + "' AND "
                + "COUOAB=? "
                + "GROUP BY RESOL, CPERIODO_CODIGO "
                + "ORDER BY CODIGO";
        if (tipoCalendario.equals("50")) {
            compromisoAnual = cobertura;
            sql = "SELECT RESOLUCION AS CODIGO, UTIL_NEW.FUN_TAMTEXT_COMBO(RESOLUCION||':'||UTIL.FUN_DESCRIPCION_RESOLUCION(CPERIODO_CODIGO, RESOLUCION),60)||' S/. '||"
                    + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                    + "FROM V_COMCAB_REVERSION WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "COUUOO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "NROCOB=? AND "
                    + "UUOO=? "
                    + "GROUP BY CPERIODO_CODIGO, RESOLUCION "
                    + "ORDER BY CODIGO";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, unidadOperativa);
            objPreparedStatement.setInt(3, presupuesto);
            objPreparedStatement.setString(4, compromisoAnual);
            objPreparedStatement.setString(5, proveedor);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getResolucionesDeclaracionJurada(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ", " + compromisoAnual + ", " + tipoCalendario + ", "
                    + cobertura + ", " + proveedor + ")" + e.getMessage());
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
    public List getDependenciaDeclaracionJurada(String periodo, Integer presupuesto, String unidadOperativa, String compromisoAnual, String tipoCalendario,
            String cobertura, String proveedor, Integer resolucion) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CODDEP AS CODIGO, CODDEP||':'||UTIL_NEW.FUN_ABRDEP(COUUOO, CODDEP)||' S/. '||"
                + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                + "FROM V_TASOCP_CA WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "COUUOO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "NROCER=? AND "
                + "TRIM(CODTIP)='" + tipoCalendario + "' AND "
                + "COUOAB=? AND "
                + "RESOL=? "
                + "GROUP BY COUUOO, CODDEP "
                + "ORDER BY CODIGO";
        if (tipoCalendario.equals("50")) {
            compromisoAnual = cobertura;
            sql = "SELECT CODDEP AS CODIGO, CODDEP||':'||UTIL_NEW.FUN_ABRDEP(COUUOO, CODDEP)||' S/. '||"
                    + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                    + "FROM V_COMCAB_REVERSION WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "COUUOO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "NROCOB=? AND "
                    + "UUOO=? AND "
                    + "RESOLUCION=? "
                    + "GROUP BY COUUOO, CODDEP "
                    + "ORDER BY CODIGO";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, unidadOperativa);
            objPreparedStatement.setInt(3, presupuesto);
            objPreparedStatement.setString(4, compromisoAnual);
            objPreparedStatement.setString(5, proveedor);
            objPreparedStatement.setInt(6, resolucion);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDependenciaDeclaracionJurada(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ", " + compromisoAnual + ", " + tipoCalendario + ", "
                    + cobertura + ", " + proveedor + ", " + resolucion + ")" + e.getMessage());
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
    public List getSecuenciaFuncionalDeclaracionJurada(String periodo, Integer presupuesto, String unidadOperativa, String compromisoAnual, String tipoCalendario,
            String cobertura, String proveedor, Integer resolucion, String dependencia) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT SECFUN AS CODIGO, SECFUN||':'||UTIL_NEW.FUN_DESMET(CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, SECFUN)||' S/. '||"
                + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                + "FROM V_TASOCP_CA WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "COUUOO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "NROCER=? AND "
                + "TRIM(CODTIP)='" + tipoCalendario + "' AND "
                + "COUOAB=? AND "
                + "RESOL=? AND "
                + "CODDEP=? "
                + "GROUP BY CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, SECFUN "
                + "ORDER BY CODIGO";
        if (tipoCalendario.equals("50")) {
            compromisoAnual = cobertura;
            sql = "SELECT SECFUN AS CODIGO, SECFUN||':'||UTIL_NEW.FUN_DESMET(CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, SECFUN)||' S/. '||"
                    + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                    + "FROM V_COMCAB_REVERSION WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "COUUOO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "NROCOB=? AND "
                    + "UUOO=? AND "
                    + "RESOLUCION=? AND "
                    + "CODDEP=? "
                    + "GROUP BY CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, SECFUN "
                    + "ORDER BY CODIGO";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, unidadOperativa);
            objPreparedStatement.setInt(3, presupuesto);
            objPreparedStatement.setString(4, compromisoAnual);
            objPreparedStatement.setString(5, proveedor);
            objPreparedStatement.setInt(6, resolucion);
            objPreparedStatement.setString(7, dependencia);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getSecuenciaFuncionalDeclaracionJurada(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ", " + compromisoAnual + ", " + tipoCalendario + ", "
                    + cobertura + ", " + proveedor + ", " + resolucion + ", " + dependencia + ")" + e.getMessage()
            );
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
    public List getTareaDeclaracionJurada(String periodo, Integer presupuesto, String unidadOperativa, String compromisoAnual, String tipoCalendario,
            String cobertura, String proveedor, Integer resolucion, String dependencia, String secuenciaFuncional) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NTAREA_PRESUPUESTAL_CODIGO AS CODIGO, NTAREA_PRESUPUESTAL_CODIGO||':'||UTIL_NEW.FUN_NTAREA(NTAREA_PRESUPUESTAL_CODIGO)||' S/. '||"
                + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                + "FROM V_TASOCP_CA WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "COUUOO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "NROCER=? AND "
                + "TRIM(CODTIP)='" + tipoCalendario + "' AND "
                + "COUOAB=? AND "
                + "RESOL=? AND "
                + "CODDEP=? AND "
                + "SECFUN=? "
                + "GROUP BY NTAREA_PRESUPUESTAL_CODIGO "
                + "ORDER BY CODIGO";
        if (tipoCalendario.equals("50")) {
            compromisoAnual = cobertura;
            sql = "SELECT NTAREA_PRESUPUESTAL_CODIGO AS CODIGO, NTAREA_PRESUPUESTAL_CODIGO||':'||UTIL_NEW.FUN_NTAREA(NTAREA_PRESUPUESTAL_CODIGO)||' S/. '||"
                    + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                    + "FROM V_COMCAB_REVERSION WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "COUUOO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "NROCOB=? AND "
                    + "UUOO=? AND "
                    + "RESOLUCION=? AND "
                    + "CODDEP=? AND "
                    + "SECFUN=? "
                    + "GROUP BY NTAREA_PRESUPUESTAL_CODIGO "
                    + "ORDER BY CODIGO";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, unidadOperativa);
            objPreparedStatement.setInt(3, presupuesto);
            objPreparedStatement.setString(4, compromisoAnual);
            objPreparedStatement.setString(5, proveedor);
            objPreparedStatement.setInt(6, resolucion);
            objPreparedStatement.setString(7, dependencia);
            objPreparedStatement.setString(8, secuenciaFuncional);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTareaDeclaracionJurada(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ", " + compromisoAnual + ", " + tipoCalendario + ", "
                    + cobertura + ", " + proveedor + ", " + resolucion + ", " + dependencia + ", " + secuenciaFuncional + ")" + e.getMessage()
            );
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
    public List getCadenaGastoDeclaracionJurada(String periodo, Integer presupuesto, String unidadOperativa, String compromisoAnual, String tipoCalendario,
            String cobertura, String proveedor, Integer resolucion, String dependencia, String secuenciaFuncional, String tarea) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NCLASIFICADOR_PRESUPUESTAL_CODIGO AS CODIGO, NCLASIFICADOR_PRESUPUESTAL_CODIGO||':'||UTIL_NEW.FUN_NOCLAS(NCLASIFICADOR_PRESUPUESTAL_CODIGO)||' S/. '||"
                + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                + "FROM V_TASOCP_CA WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "COUUOO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "NROCER=? AND "
                + "TRIM(CODTIP)='" + tipoCalendario + "' AND "
                + "COUOAB=? AND "
                + "RESOL=? AND "
                + "CODDEP=? AND "
                + "SECFUN=? AND "
                + "NTAREA_PRESUPUESTAL_CODIGO=? "
                + "GROUP BY NCLASIFICADOR_PRESUPUESTAL_CODIGO "
                + "ORDER BY CODIGO ";
        if (tipoCalendario.equals("50")) {
            compromisoAnual = cobertura;
            sql = "SELECT NCLASIFICADOR_PRESUPUESTAL_CODIGO AS CODIGO, NCLASIFICADOR_PRESUPUESTAL_CODIGO||':'||UTIL_NEW.FUN_NOCLAS(NCLASIFICADOR_PRESUPUESTAL_CODIGO)||' S/. '||"
                    + "TO_CHAR(SUM(IMPORT),'FM999,999,999,999.009') AS DESCRIPCION "
                    + "FROM V_COMCAB_REVERSION WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "COUUOO=? AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                    + "NROCOB=? AND "
                    + "UUOO=? AND "
                    + "RESOLUCION=? AND "
                    + "CODDEP=? AND "
                    + "SECFUN=? AND "
                    + "NTAREA_PRESUPUESTAL_CODIGO=? "
                    + "GROUP BY NCLASIFICADOR_PRESUPUESTAL_CODIGO "
                    + "ORDER BY CODIGO";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, unidadOperativa);
            objPreparedStatement.setInt(3, presupuesto);
            objPreparedStatement.setString(4, compromisoAnual);
            objPreparedStatement.setString(5, proveedor);
            objPreparedStatement.setInt(6, resolucion);
            objPreparedStatement.setString(7, dependencia);
            objPreparedStatement.setString(8, secuenciaFuncional);
            objPreparedStatement.setString(9, tarea);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getCadenaGastoDeclaracionJurada(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ", " + compromisoAnual + ", " + tipoCalendario + ", "
                    + cobertura + ", " + proveedor + ", " + resolucion + ", " + dependencia + ", " + secuenciaFuncional + ", " + tarea + ")" + e.getMessage()
            );
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
    public List getPeriodoResolucion(String tipo) {
        lista = new LinkedList<>();
        if (tipo.equals("J")) {
            sql = "SELECT ANO_RESOL AS CODIGO "
                    + "FROM BDATOS.V_RESOLUCION_JADPE@DBLINK_ECON "
                    + "GROUP BY ANO_RESOL "
                    + "ORDER BY CODIGO DESC";
        } else {
            sql = "SELECT ANO_RESOL AS CODIGO "
                    + "FROM BDATOS.V_RESOLUCION_TES_COPERE@DBLINK_ECON "
                    + "GROUP BY ANO_RESOL "
                    + "ORDER BY CODIGO DESC";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("CODIGO"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getPeriodoResolucion(" + tipo + ") " + e.getMessage());
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
    public List getBeneficioJADPE(String tipo, String periodo) {
        lista = new LinkedList<>();
        if (tipo.equals("J")) {
            sql = "SELECT COD_BENEFICIO AS CODIGO, DES_BENEFICIO AS DESCRIPCION "
                    + "FROM V_RESOLUCION_JADPE@DBLINK_ECON WHERE "
                    + "ANO_RESOL=? "
                    + "GROUP BY COD_BENEFICIO,DES_BENEFICIO "
                    + "ORDER BY CODIGO";
        } else {
            sql = "SELECT COD_CONCEPTO AS CODIGO, CONCEPTO AS DESCRIPCION "
                    + "FROM BDATOS.V_RESOLUCION_TES_COPERE@DBLINK_ECON WHERE "
                    + "ANO_RESOL=? "
                    + "GROUP BY COD_CONCEPTO,CONCEPTO "
                    + "ORDER BY CODIGO ";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getBeneficioJADPE(" + tipo + "," + periodo + ") " + e.getMessage());
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

    //EJECUCION PRESUPUESTAL - NOTA MODIFICATORIA  
    @Override
    public List getTipoNotaModificatoria() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NTIPO_NOTA_MODIFICATORIA_CODIGO AS CODIGO, VTIPO_NOTA_MODIFICATORIA_DESCRIPCION AS DESCRIPCION "
                + "FROM IAFAS_TIPO_NOTAS_MODIFICATORIAS WHERE "
                + "CESTADO_CODIGO='AC'  "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTipoNotaModificatoria() " + e.getMessage());
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
    public List getPresupuestoNotaModificatoria(String periodo, String tipoNota, String tipo) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        if (tipoNota.equals("2")) {
            sql = "SELECT NFUENTE_FINANCIAMIENTO_CODIGO AS CODIGO, "
                    + "VFUENTE_FINANCIAMIENTO_NOMBRE AS DESCRIPCION "
                    + "FROM IAFAS_FUENTE_FINANCIAMIENTO WHERE "
                    + "CESTADO_CODIGO='AC' "
                    + "ORDER BY CODIGO";
        } else {
            sql = "SELECT NFUENTE_FINANCIAMIENTO_CODIGO AS CODIGO, "
                    + "`PK_UTIL.FUN_FUENTE_FINANCIAMIENTO_NOMBRE`(NFUENTE_FINANCIAMIENTO_CODIGO) AS DESCRIPCION "
                    + "FROM IAFAS_CALENDARIO_GASTOS WHERE "
                    + "CPERIODO_CODIGO='" + periodo + "' "
                    + "GROUP BY NFUENTE_FINANCIAMIENTO_CODIGO "
                    + "ORDER BY CODIGO";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getPresupuestoNotaModificatoria(" + periodo + ", " + tipoNota + ", " + tipo + ") " + e.getMessage());
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
    public List getResolucionNotaModificatoria(String periodo, String tipoNota, String tipo, Integer presupuesto) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        if (tipoNota.equals("2") && tipo.equals("C")) {
            sql = "SELECT NRESOLUCION_CODIGO AS CODIGO,"
                    + "VRESOLUCION_DESCRIPCION AS DESCRIPCION "
                    + "FROM IAFAS_RESOLUCIONES RESOL WHERE "
                    + "CPERIODO_CODIGO='" + periodo + "' AND NFUENTE_FINANCIAMIENTO_CODIGO='" + presupuesto + "' AND "
                    + "NRESOLUCION_CODIGO NOT IN (SELECT NRESOLUCION_CODIGO FROM IAFAS_CALENDARIO_GASTOS WHERE "
                    + "CPERIODO_CODIGO=? AND NFUENTE_FINANCIAMIENTO_CODIGO=? "
                    + "GROUP BY NRESOLUCION_CODIGO) "
                    + "GROUP BY CPERIODO_CODIGO, NRESOLUCION_CODIGO, VRESOLUCION_DESCRIPCION "
                    + "ORDER BY CODIGO";
        } else {
            if (tipo.equals("A") || tipoNota.equals("5")) {
                sql = "SELECT NRESOLUCION_CODIGO AS CODIGO,"
                        + "CONCAT(`PK_UTIL.FUN_RESOLUCION_DESCRIPCION`(CPERIODO_CODIGO,NRESOLUCION_CODIGO),' S/. ', "
                        + "FORMAT(SUM(PIM-(EJECUTADO+NOTA)),2)) AS DESCRIPCION "
                        + "FROM V_NOTAS_MODIFICATORIAS WHERE "
                        + "CPERIODO_CODIGO=? AND "
                        + "NFUENTE_FINANCIAMIENTO_CODIGO=?  "
                        + "GROUP BY CPERIODO_CODIGO, NRESOLUCION_CODIGO "
                        + "ORDER BY CODIGO";
            } else if (tipo.equals("C")) {
                sql = "SELECT NRESOLUCION_CODIGO AS CODIGO,"
                        + "`PK_UTIL.FUN_RESOLUCION_DESCRIPCION`(CPERIODO_CODIGO,NRESOLUCION_CODIGO) AS DESCRIPCION "
                        + "FROM V_NOTAS_MODIFICATORIAS WHERE "
                        + "CPERIODO_CODIGO=? AND "
                        + "NFUENTE_FINANCIAMIENTO_CODIGO=? "
                        + "GROUP BY CPERIODO_CODIGO, NRESOLUCION_CODIGO "
                        + "ORDER BY CODIGO";
            }
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getResolucionNotaModificatoria(" + periodo + ", " + tipoNota + ", " + presupuesto + ") " + e.getMessage());
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
    public List getTareaNotaModificatoria(String periodo, String tipoNota, String tipo,
            Integer presupuesto, Integer resolucion) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        if (tipo.equals("A") || tipoNota.equals("5")) {
            sql = "SELECT NTAREA_PRESUPUESTAL_CODIGO AS CODIGO, "
                    + "CONCAT(`PK_UTIL.FUN_TAREA_PRESUPUESTAL_NOMBRE`(NTAREA_PRESUPUESTAL_CODIGO), ' S/. ', FORMAT(SUM(PIM-(EJECUTADO+NOTA)),2)) DESCRIPCION "
                    + "FROM V_NOTAS_MODIFICATORIAS WHERE "
                    + "CPERIODO_CODIGO='" + periodo + "' AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO='" + presupuesto + "' AND "
                    + "NRESOLUCION_CODIGO='" + resolucion + "' "
                    + "GROUP BY NTAREA_PRESUPUESTAL_CODIGO "
                    + "ORDER BY CODIGO";
        } else if (tipo.equals("C")) {
            sql = "SELECT NTAREA_PRESUPUESTAL_CODIGO AS CODIGO, "
                    + "`PK_UTIL.FUN_TAREA_PRESUPUESTAL_NOMBRE`(NTAREA_PRESUPUESTAL_CODIGO) AS DESCRIPCION "
                    + "FROM IAFAS_TAREA_PRESUPUESTAL WHERE "
                    + "CESTADO_CODIGO='AC' "
                    + "ORDER BY NTAREA_PRESUPUESTAL_CODIGO";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTareaNotaModificatoria(" + periodo + ",  " + tipoNota + ", " + tipo + ", "
                    + presupuesto + ", " + resolucion + ") " + e.getMessage());
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
    public List getCadenaGastoNotaModificatoria(String periodo, String tipoNota, String tipo,
            Integer presupuesto, Integer resolucion, String tarea) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        if (tipo.equals("A") || tipoNota.equals("5")) {
            sql = "SELECT NCLASIFICADOR_PRESUPUESTAL_CODIGO AS CODIGO, "
                    + "CONCAT(`PK_UTIL.FUN_CLASIFICA_PRESUPUESTAL_NOMBRE`(NCLASIFICADOR_PRESUPUESTAL_CODIGO),' S/. ', "
                    + "FORMAT(SUM(PIM-(EJECUTADO+NOTA)), 2)) AS DESCRIPCION "
                    + "FROM V_NOTAS_MODIFICATORIAS WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "NRESOLUCION_CODIGO='" + resolucion + "' AND "
                    + "NFUENTE_FINANCIAMIENTO_CODIGO='" + presupuesto + "' AND "
                    + "NTAREA_PRESUPUESTAL_CODIGO=? "
                    + "GROUP BY NCLASIFICADOR_PRESUPUESTAL_CODIGO "
                    + "ORDER BY CODIGO";
        } else if (tipo.equals("C")) {
            sql = "SELECT NCLASIFICADOR_PRESUPUESTAL_CODIGO AS CODIGO, "
                    + "`PK_UTIL.FUN_CLASIFICA_PRESUPUESTAL_NOMBRE`(NCLASIFICADOR_PRESUPUESTAL_CODIGO) AS DESCRIPCION "
                    + "FROM IAFAS_TAREA_CLASIFICADOR_PRESPUESTAL WHERE "
                    + "CPERIODO_CODIGO=? AND "
                    + "NTAREA_PRESUPUESTAL_CODIGO=? "
                    + "ORDER BY CODIGO ";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, tarea);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getCadenaGastoNotaModificatoria(" + periodo + ", " + tipoNota + ", " + tipo + ", "
                    + presupuesto + ", " + resolucion + ", " + tarea + ") " + e.getMessage());
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

    // EJECUCION PRESUPUESTAL - DISPONIBILIDAD PRESUPUESTAL
    @Override
    public List getEntidadSolicitante() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT COUUOO AS CODIGO, COUUOO||'-'||ABUUOO AS DESCRIPCION "
                + "FROM TAUUOO WHERE "
                + "TIUUOO IN ('U','A') AND "
                + "COUUOO NOT IN ('9000') AND "
                + "ESTREG='AC' "
                + "ORDER BY COUUOO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getEntidadSolicitante() " + e.getMessage());
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
    public List getInformeResolucionCargo(String periodo, Integer presupuesto, String unidadOperativa) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT RESOLUCION AS CODIGO,"
                + "RESOLUCION||':'||UTIL.FUN_DESCRIPCION_RESOLUCION(CPERIODO_CODIGO, RESOLUCION)||' S/. '||"
                + "TO_CHAR(SUM(IMPORT-ABS(CERTIFICADO)),'FM999,999,999,999.009') AS DESCRIPCION "
                + "FROM V_INFORME WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "COUUOO=? "
                + "GROUP BY CPERIODO_CODIGO, RESOLUCION "
                + "HAVING SUM(IMPORT-ABS(CERTIFICADO)) != 0.0 "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objPreparedStatement.setString(3, unidadOperativa);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getInformeResolucionCargo(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ")" + e.getMessage());
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
    public List getInformeDependenciaCargo(String periodo, Integer presupuesto, String unidadOperativa, Integer resolucion) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CODDEP AS CODIGO, "
                + "CODDEP||':'||UTIL_NEW.FUN_ABRDEP(COUUOO, CODDEP)||' S/. '||"
                + "TO_CHAR(SUM(IMPORT-ABS(CERTIFICADO)),'FM999,999,999,999.009') AS DESCRIPCION "
                + "FROM V_INFORME WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "COUUOO=? AND "
                + "RESOLUCION=? "
                + "GROUP BY COUUOO, CODDEP "
                + "HAVING SUM(IMPORT-ABS(CERTIFICADO)) != 0.0 "
                + "ORDER BY CODIGO ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objPreparedStatement.setString(3, unidadOperativa);
            objPreparedStatement.setInt(4, resolucion);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getInformeDependenciaCargo(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ", " + resolucion + ") " + e.getMessage());
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
    public List getInformeSecuenciaFuncionalCargo(String periodo, Integer presupuesto, String unidadOperativa, Integer resolucion, String dependencia) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT SECFUN AS CODIGO, "
                + "SECFUN||':'||UTIL_NEW.FUN_DESMET(CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, SECFUN)||' S/. '||"
                + "TO_CHAR(SUM(IMPORT-ABS(CERTIFICADO)),'FM999,999,999,999.009') AS DESCRIPCION "
                + "FROM V_INFORME WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "COUUOO=? AND "
                + "RESOLUCION=? AND "
                + "CODDEP=? "
                + "GROUP BY SECFUN, CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO "
                + "HAVING SUM(IMPORT-ABS(CERTIFICADO)) != 0.0 "
                + "ORDER BY CODIGO ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objPreparedStatement.setString(3, unidadOperativa);
            objPreparedStatement.setInt(4, resolucion);
            objPreparedStatement.setString(5, dependencia);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getInformeDependenciaCargo(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ", " + resolucion + ", "
                    + dependencia + ") " + e.getMessage());
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
    public List getInformeTareaCargo(String periodo, Integer presupuesto, String unidadOperativa, Integer resolucion, String dependencia,
            String secuenciaFuncional) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NTAREA_PRESUPUESTAL_CODIGO AS CODIGO, "
                + "NTAREA_PRESUPUESTAL_CODIGO||':'||UTIL_NEW.FUN_NTAREA(NTAREA_PRESUPUESTAL_CODIGO)||' S/. '||"
                + "TO_CHAR(SUM(IMPORT-ABS(CERTIFICADO)),'FM999,999,999,999.009') AS DESCRIPCION "
                + "FROM V_INFORME WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "COUUOO=? AND "
                + "RESOLUCION=? AND "
                + "CODDEP=? AND "
                + "SECFUN=? "
                + "GROUP BY SECFUN, CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, NTAREA_PRESUPUESTAL_CODIGO "
                + "HAVING SUM(IMPORT-ABS(CERTIFICADO)) != 0.0 "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objPreparedStatement.setString(3, unidadOperativa);
            objPreparedStatement.setInt(4, resolucion);
            objPreparedStatement.setString(5, dependencia);
            objPreparedStatement.setString(6, secuenciaFuncional);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getInformeTareaCargo(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ", " + resolucion + ", "
                    + dependencia + ", " + secuenciaFuncional + ") " + e.getMessage());
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
    public List getInformeCadenaGastoCargo(String periodo, Integer presupuesto, String unidadOperativa, Integer resolucion, String dependencia,
            String secuenciaFuncional, String tarea) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NCLASIFICADOR_PRESUPUESTAL_CODIGO AS CODIGO,"
                + "NCLASIFICADOR_PRESUPUESTAL_CODIGO||':'||UTIL_NEW.FUN_NOCLAS(NCLASIFICADOR_PRESUPUESTAL_CODIGO)||' S/. '||"
                + "TO_CHAR(SUM(IMPORT-ABS(CERTIFICADO)),'FM999,999,999,999.009') AS DESCRIPCION "
                + "FROM V_INFORME WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "COUUOO=? AND "
                + "RESOLUCION=? AND "
                + "CODDEP=? AND "
                + "SECFUN=? AND "
                + "NTAREA_PRESUPUESTAL_CODIGO=? "
                + "GROUP BY NCLASIFICADOR_PRESUPUESTAL_CODIGO "
                + "HAVING SUM(IMPORT-ABS(CERTIFICADO)) != 0.0 "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objPreparedStatement.setString(3, unidadOperativa);
            objPreparedStatement.setInt(4, resolucion);
            objPreparedStatement.setString(5, dependencia);
            objPreparedStatement.setString(6, secuenciaFuncional);
            objPreparedStatement.setString(7, tarea);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getInformeCadenaGastoCargo(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ", " + resolucion + ", "
                    + dependencia + ", " + secuenciaFuncional + ", " + tarea + ") " + e.getMessage());
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

    //EJECUCION PRESUPUESTAL - ACTIVIDAD - TAREA PRESUPUESTAL
    @Override
    public List getCategoriaPresupuestalEjecucion(String periodo) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA DISTRITO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT DISTINCT COPRES AS CODIGO, COPRES||':'||UTIL_NEW.FUN_NOMBRE_CATEGORIA_PRESUPUES(COPRES) AS DESCRIPCION "
                + "FROM TACAFU WHERE "
                + "CPERIODO_CODIGO=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getCategoriaPresupuestalEjecucion(" + periodo + ") " + e.getMessage());
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
    public List getProductoEjecucion(String periodo, String categoriaPresupuestal) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA DISTRITO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT DISTINCT CODCOM AS CODIGO, CODCOM||':'||UTIL_NEW.FUN_NOMBRE_PRODUCTO(TRIM(CODCOM)) AS DESCRIPCION "
                + "FROM TACAFU WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "COPRES IN (" + categoriaPresupuestal.replaceFirst(",", "").trim() + ") "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getProductoEjecucion(" + periodo + ", " + categoriaPresupuestal + ") " + e.getMessage());
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
    public List getActividadEjecucion(String periodo, String categoriaPresupuestal, String producto) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA DISTRITO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT DISTINCT CODACT AS CODIGO, CODACT||':'||UTIL_NEW.FUN_NOMBRE_ACTIVIDAD(TRIM(CODACT)) AS DESCRIPCION "
                + "FROM TACAFU WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "COPRES=? AND "
                + "CODCOM=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, categoriaPresupuestal);
            objPreparedStatement.setString(3, producto);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getActividadEjecucion(" + periodo + ", " + categoriaPresupuestal + ", " + producto + ") " + e.getMessage());
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
    public List getFinalidadEjecucion(String periodo, String categoriaPresupuestal, String producto, String actividad) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA DISTRITO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT DISTINCT COFINA AS CODIGO, COFINA||':'||DEFINA AS DESCRIPCION "
                + "FROM TACAFU WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "COPRES=? AND "
                + "CODCOM=? AND "
                + "CODACT=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, categoriaPresupuestal);
            objPreparedStatement.setString(3, producto);
            objPreparedStatement.setString(4, actividad);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getFinalidadEjecucion(" + periodo + ", " + categoriaPresupuestal + ", " + producto + ", " + actividad + ") " + e.getMessage());
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

    //PROGRAMACIÓN PRESUPUESTAL
    @Override
    public List getDependenciaFuerzaOperativa(String periodo, Integer presupuesto, String unidadOperativa) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL PRESUPUESTO O FUENTE DE FINANCIAMIENTO POR
         * ORDEN DESCENDENTES Y LO ALMACENAMOS EN UNA LISTA
         */
        if (presupuesto != 1 || (unidadOperativa.equals("0702") || unidadOperativa.equals("0714") || unidadOperativa.equals("0720") || unidadOperativa.equals("0728") || unidadOperativa.equals("0730")
                || unidadOperativa.equals("0732") || unidadOperativa.equals("0734") || unidadOperativa.equals("0738") || unidadOperativa.equals("0750") || unidadOperativa.equals("0752")
                || unidadOperativa.equals("0754") || unidadOperativa.equals("0756") || unidadOperativa.equals("0770") || unidadOperativa.equals("0772") || unidadOperativa.equals("0774")
                || unidadOperativa.equals("0775") || unidadOperativa.equals("0777") || unidadOperativa.equals("0785") || unidadOperativa.equals("0787") || unidadOperativa.equals("0790")
                || unidadOperativa.equals("0794") || unidadOperativa.equals("0795") || unidadOperativa.equals("0796") || unidadOperativa.equals("0797"))) {
            sql = "SELECT CODDEP AS CODIGO, CODDEP||':'||ABRDEP AS DESCRIPCION "
                    + "FROM TABDEP WHERE "
                    + "COUUOO=? "
                    + "ORDER BY CODIGO";
        } else {
            sql = "SELECT DISTINCT CDEPENDENCIA_CODIGO AS CODIGO, CDEPENDENCIA_CODIGO||':'||UTIL_NEW.FUN_ABRDEP(CUNIDAD_OPERATIVA_CODIGO, CDEPENDENCIA_CODIGO) AS DESCRIPCION "
                    + "FROM SIPE_PROGRAMACION_FUERZA_OPE WHERE "
                    + "CPERIODO_CODIGO='" + periodo + "' AND "
                    + "CUNIDAD_OPERATIVA_CODIGO=? AND "
                    + "NNIVEL_DETALLE='2' AND "
                    + "CESTADO_CODIGO!='AN' "
                    + "ORDER BY CODIGO";
        }
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, unidadOperativa);
            objResultSet = objPreparedStatement.executeQuery();
            comun = new BeanComun();
            comun.setCodigo("0");
            comun.setDescripcion("Seleccione");
            lista.add(comun);
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDependencia(" + unidadOperativa + ") " + e.getMessage());
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
    public List getItemCadenaGasto(String codigo) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NCLASIFICADOR_PRESUPUESTAL_CODIGO AS CODIGO, NCLASIFICADOR_PRESUPUESTAL_CODIGO||'-'||UTIL_NEW.FUN_NOMBRE_CADENA_GASTO(NCLASIFICADOR_PRESUPUESTAL_CODIGO) DESCRIPCION "
                + "FROM SIPE_ITEM_ESPECIFICA_GASTO WHERE "
                + "VITEM_CODIGO=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, codigo);
            objResultSet = objPreparedStatement.executeQuery();
            comun = new BeanComun();
            comun.setCodigo("0");
            comun.setDescripcion("Seleccione");
            lista.add(comun);
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getItemCadenaGasto(" + codigo + ") " + e.getMessage());
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
    public List getCadenaGastoTarea(String periodo, String tarea) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NCLASIFICADOR_PRESUPUESTAL_CODIGO AS CODIGO, NCLASIFICADOR_PRESUPUESTAL_CODIGO||':'||UTIL_NEW.FUN_NOCLAS(NCLASIFICADOR_PRESUPUESTAL_CODIGO) AS DESCRIPCION "
                + "FROM SIPE_TAREA_ESPECIFICA WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NTAREA_PRESUPUESTAL_CODIGO=? "
                + "ORDER BY CODIGO ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, tarea);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getCadenaGastoTarea(" + periodo + ", " + tarea + ") " + e.getMessage());
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
    public List getCadenaIngresoEstimacion(String periodo, String unidadOperativa, Integer presupuesto) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = " SELECT EU.NESTIMACION_INGRESO_CODIGO AS CODIGO, "
                + "EI.VCADENA_INGRESO_CODIGO||':'||EI.VESTIMACION_INGRESO_DESCRIP AS DESCRIPCION "
                + "FROM SIPE_ESTIMACION_UNIDAD EU INNER JOIN SIPE_ESTIMACION_INGRESOS EI ON ("
                + "EU.CPERIODO_CODIGO=EI.CPERIODO_CODIGO AND "
                + "EU.NESTIMACION_INGRESO_CODIGO=EI.NESTIMACION_INGRESO_CODIGO) WHERE  "
                + "EU.CPERIODO_CODIGO=? AND "
                + "EU.CUNIDAD_OPERATIVA_CODIGO=? AND "
                + "EU.NPRESUPUESTO_CODIGO=? "
                + "ORDER BY 1";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, unidadOperativa);
            objPreparedStatement.setInt(3, presupuesto);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getCadenaIngresoEstimacion() " + e.getMessage());
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
    public List getSecuenciaFuncionalProgramacion(String periodo, Integer presupuesto, String tarea) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT DISTINCT SECFUN AS CODIGO, SECFUN||':'||DESMET||' '||NOMDEP||'-'||NOMUBI||'-'||NOMDIS AS DESCRIPCION "
                + "FROM TACMEOP TA INNER JOIN TACAFU_PRG T ON ("
                + "TA.CPERIODO_CODIGO=T.CPERIODO_CODIGO AND TA.NFUENTE_FINANCIAMIENTO_CODIGO=T.NFUENTE_FINANCIAMIENTO_CODIGO AND TA.CODACT=T.CODACT AND TA.COFINA=T.COFINA) WHERE "
                + "TA.CPERIODO_CODIGO=? AND "
                + "TA.NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "TA.NTAREA_PRESUPUESTAL_CODIGO=? "
                + "ORDER BY 1";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objPreparedStatement.setString(3, tarea);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getSecuenciaFuncionalProgramacion(periodo, presupuesto, tarea) " + e.getMessage());
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
    public List getCadenaGastoProgramacionMultianual(String periodo, Integer presupuesto, String unidadOperativa,
            String tarea, String dependencia) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NCLASIFICADOR_PRESUPUESTAL_CODIGO AS CODIGO, NCLASIFICADOR_PRESUPUESTAL_CODIGO||':'||UTIL_NEW.FUN_NOMBRE_CADENA_GASTO(NCLASIFICADOR_PRESUPUESTAL_CODIGO) AS DESCRIPCION "
                + "FROM SIPE_PROGRAMACION_MULTI_DETALL WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CUNIDAD_OPERATIVA_CODIGO=? AND "
                + "NPRESUPUESTO_CODIGO=? AND "
                + "CTAREA_CODIGO=? AND "
                + "CDEPENDENCIA_CODIGO=? "
                + "GROUP BY NCLASIFICADOR_PRESUPUESTAL_CODIGO "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, unidadOperativa);
            objPreparedStatement.setInt(3, presupuesto);
            objPreparedStatement.setString(4, tarea);
            objPreparedStatement.setString(5, dependencia);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getCadenaGastoProgramacionMultianual(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ", "
                    + dependencia + ",  " + tarea + ") " + e.getMessage());
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
    public List getDependenciaProgramacionMultianual(String periodo, Integer presupuesto, String unidadOperativa, String tarea) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CDEPENDENCIA_CODIGO AS CODIGO, CDEPENDENCIA_CODIGO||':'||UTIL_NEW.FUN_ABRDEP(CUNIDAD_OPERATIVA_CODIGO, CDEPENDENCIA_CODIGO) AS DESCRIPCION "
                + "FROM SIPE_PROGRAMACION_MULTI_DETALL WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CUNIDAD_OPERATIVA_CODIGO=? AND "
                + "NPRESUPUESTO_CODIGO=? AND "
                + "CTAREA_CODIGO=?  "
                + "GROUP BY CUNIDAD_OPERATIVA_CODIGO, CDEPENDENCIA_CODIGO "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, unidadOperativa);
            objPreparedStatement.setInt(3, presupuesto);
            objPreparedStatement.setString(4, tarea);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDependenciaProgramacionMultianual(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ", "
                    + tarea + ")" + e.getMessage());
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

    //UBIGEO
    @Override
    public List getDepartamento() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CODDPT AS CODIGO, NOMDPT AS DESCRIPCION "
                + "FROM TABDPT "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDepartamento() " + e.getMessage());
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
    public List getProvincia(String departamento) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL TIPO DE CALENDARIO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CODPRA AS CODIGO, NOMPRA AS DESCRIPCION "
                + "FROM TABPRA WHERE "
                + "CODDPT=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, departamento);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getProvincia(departamento) " + e.getMessage());
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
    public List getDistrito(String departamento, String provincia) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA DISTRITO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CODUBI AS CODIGO, DESUBI AS DESCRIPCION "
                + "FROM TABUBI WHERE "
                + "CODDPT=? AND "
                + "CODPRA=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, departamento);
            objPreparedStatement.setString(2, provincia);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDistrito(departamento, provincia) " + e.getMessage());
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
    public List getTipoFuerzaOperativa() {
        lista = new LinkedList<>();
        sql = "SELECT NTIPO_FUERZA_CODIGO AS CODIGO,"
                + "VTIPO_FUERZA_DESCRIPCION AS DESCRIPCION "
                + "FROM SIPE_PROGRAMACION_TIPO_FUERZA "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTipoFuerzaOperativa() " + e.getMessage());
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

    //ACTIVIDAD - TAREA PROGRAMACIÓN
    @Override
    public List getCategoriaPresupuestalProgramacion(String periodo) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA DISTRITO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT DISTINCT COPRES AS CODIGO, COPRES||':'||UTIL_NEW.FUN_NOMBRE_CATEGORIA_PRESUPUES(COPRES) AS DESCRIPCION "
                + "FROM TACAFU_PRG WHERE "
                + "CPERIODO_CODIGO=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getCategoriaPresupuestal(periodo) " + e.getMessage());
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
    public List getProductoProgramacion(String periodo, String categoriaPresupuestal) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA DISTRITO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT DISTINCT CODCOM AS CODIGO, CODCOM||':'||UTIL_NEW.FUN_NOMBRE_PRODUCTO(TRIM(CODCOM)) AS DESCRIPCION "
                + "FROM TACAFU_PRG WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "COPRES=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, categoriaPresupuestal);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getProducto(periodo, categoriaPresupuestal) " + e.getMessage());
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
    public List getActividadProgramacion(String periodo, String categoriaPresupuestal, String producto) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA DISTRITO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT DISTINCT CODACT AS CODIGO, CODACT||':'||UTIL_NEW.FUN_NOMBRE_ACTIVIDAD(TRIM(CODACT)) AS DESCRIPCION "
                + "FROM TACAFU_PRG WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "COPRES=? AND "
                + "CODCOM=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, categoriaPresupuestal);
            objPreparedStatement.setString(3, producto);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getActividad(periodo, categoriaPresupuestal, producto) " + e.getMessage());
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
    public List getFinalidadProgramacion(String periodo, String categoriaPresupuestal, String producto, String actividad) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA DISTRITO ASIGNADOS A LA UNIDAD OPERATIVA
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT DISTINCT COFINA AS CODIGO, COFINA||':'||DEFINA AS DESCRIPCION "
                + "FROM TACAFU_PRG WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "COPRES=? AND "
                + "CODCOM=? AND "
                + "CODACT=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, categoriaPresupuestal);
            objPreparedStatement.setString(3, producto);
            objPreparedStatement.setString(4, actividad);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getFinalidad(periodo, categoriaPresupuestal, producto, actividad) " + e.getMessage());
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
    public List getActividadUbigeo(String periodo, String categoriaPresupuestal, String producto, String actividad) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA DISTRITO ASIGNADOS A LA ACTIVIDAD PRESUPUESTAL
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT DISTINCT CODUBI AS CODIGO, NOMDIS||'-'||NOMUBI AS DESCRIPCION "
                + "FROM TACAFU_PRG WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "COPRES=? AND "
                + "CODCOM=? AND "
                + "CODACT=? "
                + "ORDER BY CODUBI";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, categoriaPresupuestal);
            objPreparedStatement.setString(3, producto);
            objPreparedStatement.setString(4, actividad);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getFinalidad(periodo, categoriaPresupuestal, producto, actividad) " + e.getMessage());
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
    public List getConceptoRemuneraciones() {
        lista = new LinkedList<>();
        sql = "SELECT NCONCEPTO_INGRESOS_COD AS CODIGO,"
                + "VCONCEPTO_INGRESOS_DESCRIP AS DESCRIPCION "
                + "FROM SIPE_CONCEP_INGRESOS_PERS "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getConceptoRemuneraciones() " + e.getMessage());
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
    public List getNivelGrado() {
        lista = new LinkedList<>();
        sql = "SELECT NG.NNIVEL_GRD_CODIGO AS CODIGO,"
                + "NG.VNIVEL_GRD_DESCRIPCION AS DESCRIPCION "
                + "FROM SIPE_NIVEL_GRADO NG  "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getNivelGrado() " + e.getMessage());
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
    public List getCadenaGastoPerS(String periodo) {
        lista = new LinkedList<>();
        sql = "SELECT NCLASIFICADOR_PRESUPUESTAL_CODIGO AS CODIGO,"
                + "NCLASIFICADOR_PRESUPUESTAL_CODIGO||':'||UTIL_NEW.FUN_NOCLAS(NCLASIFICADOR_PRESUPUESTAL_CODIGO) AS  DESCRIPCION "
                + "FROM SIPE_TAREA_ESPECIFICA "
                + "WHERE CPERIODO_CODIGO=? AND "
                + "SUBSTR(NCLASIFICADOR_PRESUPUESTAL_CODIGO,3,1)='1' "
                + "GROUP BY NCLASIFICADOR_PRESUPUESTAL_CODIGO "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getCadenaGastoPerS() " + e.getMessage());
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
    public List getTareaPersonal(String periodo, String cadenaGasto) {
        lista = new LinkedList<>();
        sql = "SELECT NTAREA_PRESUPUESTAL_CODIGO AS CODIGO,"
                + "NTAREA_PRESUPUESTAL_CODIGO||':'||UTIL_NEW.FUN_NTAREA(NTAREA_PRESUPUESTAL_CODIGO) AS  DESCRIPCION "
                + "FROM SIPE_TAREA_ESPECIFICA "
                + "WHERE CPERIODO_CODIGO=? AND "
                + "NCLASIFICADOR_PRESUPUESTAL_CODIGO=? "
                + "GROUP BY NTAREA_PRESUPUESTAL_CODIGO "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, cadenaGasto);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTareaPersonal() " + e.getMessage());
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
    public List getNivelGradoPersonal(String periodo, String codConcepto) {
        lista = new LinkedList<>();
        sql = "SELECT NG.NNIVEL_GRD_CODIGO AS CODIGO,"
                + "NG.VNIVEL_GRD_DESCRIPCION AS DESCRIPCION "
                + "FROM SIPE_INGRESOS_PERSONAL_EP IP JOIN SIPE_NIVEL_GRADO NG ON "
                + "(IP.NNIVEL_GRADO_CODIGO=NG.NNIVEL_GRD_CODIGO) "
                + "WHERE IP.CPERIODO_CODIGO=? AND "
                + "IP.NCONCEPTO_INGRESOS_COD=? "
                + "GROUP BY NG.NNIVEL_GRD_CODIGO,NG.VNIVEL_GRD_DESCRIPCION "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, codConcepto);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getNivelGradoPersonal() " + e.getMessage());
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
    public List getGradoPersonal(String periodo, String codConcepto, String nivelGrado) {
        lista = new LinkedList<>();
        sql = "SELECT IP.CODGRD AS CODIGO,"
                + "TG.ABREVITURA AS DESCRIPCION "
                + "FROM SIPE_INGRESOS_PERSONAL_EP IP JOIN TABGRD TG ON "
                + "(IP.CODGRD=TG.CODGRD) "
                + "WHERE "
                + "IP.CPERIODO_CODIGO=? AND "
                + "IP.NCONCEPTO_INGRESOS_COD=? AND "
                + "IP.NNIVEL_GRADO_CODIGO=? "
                + "GROUP BY IP.CODGRD,TG.ABREVITURA "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, codConcepto);
            objPreparedStatement.setString(3, nivelGrado);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getGradoPersonal() " + e.getMessage());
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
    public List getperiodoReePersonal(String periodo, String codConcepto, String nivelGrado, String grado) {
        lista = new LinkedList<>();
        sql = "SELECT CPERIODO_REE AS CODIGO,"
                + "CASE WHEN CPERIODO_REE='P1' THEN '1er Periodo' "
                + "WHEN CPERIODO_REE='P2' THEN '2do Periodo' "
                + "WHEN CPERIODO_REE='P3' THEN '3er Periodo' "
                + "WHEN CPERIODO_REE='P4' THEN '4to Periodo' "
                + "WHEN CPERIODO_REE='P5' THEN '5to Periodo' ELSE '' END AS DESCRIPCION "
                + "FROM SIPE_INGRESOS_PERSONAL_EP "
                + "WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NCONCEPTO_INGRESOS_COD=? AND "
                + "NNIVEL_GRADO_CODIGO=? AND "
                + "CODGRD=? "
                + "GROUP BY CPERIODO_REE "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, codConcepto);
            objPreparedStatement.setString(3, nivelGrado);
            objPreparedStatement.setString(4, grado);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getperiodoReePersonal() " + e.getMessage());
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
    public List getDependenciaFuerzaOperativa(String periodo, String departamento) {
        lista = new LinkedList<>();
        sql = "SELECT UNIDAD||'.'||DEPENDENCIA AS CODIGO,"
                + "UTIL_NEW.FUN_ABRDEP(UNIDAD,DEPENDENCIA) AS DESCRIPCION "
                + "FROM V_UNIDADES_FUERZA_OPERATIVA "
                + "WHERE "
                + "PERIODO=? AND "
                + "DEPARTAMENTO=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, departamento);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDependenciaFuerzaOperativa() " + e.getMessage());
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
    public List getCadenaGastoHorasVuelo() {
        lista = new LinkedList<>();

        sql = "SELECT COTITR||'.'||COGEGA||'.'||COGEG1||'.'||COGEG2||'.'||COESG1||'.'||COESG2 AS CODIGO, "
                + " COTITR||'.'||COGEGA||'.'||COGEG1||'.'||COGEG2||'.'||COESG1||'.'||COESG2||':'||NOCAGA AS DESCRIPCION "
                + " FROM CADGAS WHERE "
                + " COTITR||'.'||COGEGA||'.'||COGEG1||'.'||COGEG2||'.'||COESG1||'.'||COESG2 "
                + " IN ('2.3.1.3.1.1','2.3.1.3.1.3','2.3.2.4.1.3','2.3.1.6.1.1','2.3.2.4.1.3','2.3.1.11.1.2',"
                + " '2.3.1.3.1.2','2.3.2.7.11.99','2.3.2.1.2.1','2.3.2.6.3.99')"
                + " ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getCadenaGastoHorasVuelo() " + e.getMessage());
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
    public List getListaTareaHorasVuelo(String periodo) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL PRESUPUESTO O FUENTE DE FINANCIAMIENTO POR
         * ORDEN DESCENDENTES Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NTAREA_PRESUPUESTAL_CODIGO AS CODIGO, NTAREA_PRESUPUESTAL_CODIGO||'-'||UTIL_NEW.FUN_NTAREA(NTAREA_PRESUPUESTAL_CODIGO) AS DESCRIPCION "
                + "FROM TAPAIN WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=1 AND "
                + "COUUOO='0822' AND "
                + "NTAREA_PRESUPUESTAL_CODIGO IN ('0089','0693','0715','0719','0228','0718','0748','0685') "
                + "ORDER BY NTAREA_PRESUPUESTAL_CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaTareaHorasVuelo() " + e.getMessage());
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

    //MESA DE PARTES
    @Override
    public List getInstituciones() {
        lista = new LinkedList<>();
        sql = "SELECT NINSTITUCION_CODIGO AS CODIGO, VINSTITUCION_ABREVIATURA AS DESCRIPCION "
                + "FROM IAFAS_INSTITUCIONES WHERE "
                + "CESTADO_CODIGO!='AN'  "
                + "ORDER BY DESCRIPCION ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getInstitucion() " + e.getMessage());
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
    public List getUsuarioAreaLaboral(String areaLaboral) {
        lista = new LinkedList<>();
        sql = "SELECT VUSUARIO_CODIGO AS CODIGO, "
                + "CONCAT(VUSUARIO_PATERNO,' ',VUSUARIO_MATERNO,', ',VUSUARIO_NOMBRES) AS DESCRIPCION "
                + "FROM IAFAS_USUARIOS WHERE "
                + "CAREA_LABORAL_CODIGO=? AND "
                + "CESTADO_CODIGO!='AN' "
                + "ORDER BY DESCRIPCION ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, areaLaboral);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getUsuarioAreaLaboral('" + areaLaboral + "') " + e.getMessage());
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
    public List getUsuarioJefatura() {
        lista = new LinkedList<>();
        sql = "SELECT VUSUARIO_CODIGO AS CODIGO, "
                + "CONCAT(VUSUARIO_PATERNO,' ',VUSUARIO_MATERNO,', ',VUSUARIO_NOMBRES) AS DESCRIPCION "
                + "FROM IAFAS_USUARIOS WHERE "
                + "CAREA_LABORAL_CODIGO IN ('01','02') AND "
                + "CESTADO_CODIGO!='AN' "
                + "ORDER BY CODIGO ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getUsuarioJefatura() " + e.getMessage());
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
    public List getPrioridades() {
        lista = new LinkedList<>();
        sql = "SELECT NPRIORIDAD_CODIGO AS CODIGO, VPRIORIDAD_DESCRIPCION AS DESCRIPCION "
                + "FROM IAFAS_PRIORIDADES WHERE "
                + "CESTADO_CODIGO!='AN' "
                + "ORDER BY DESCRIPCION";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getPrioridades() " + e.getMessage());
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
    public List getDocumentos() {
        lista = new LinkedList<>();
        sql = "SELECT NDOCUMENTO_CODIGO AS CODIGO, VDOCUMENTO_DESCRIPCION AS DESCRIPCION "
                + "FROM IAFAS_DOCUMENTOS WHERE "
                + "CESTADO_CODIGO!='AN' "
                + "ORDER BY DESCRIPCION";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDocumentos() " + e.getMessage());
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
    public List getClasificacionDocumento() {
        lista = new LinkedList<>();
        sql = "SELECT NCLASIFICACION_DOCUMENTO_CODIGO AS CODIGO, VCLASIFICACION_DOCUMENTO_DESCRIPCION AS DESCRIPCION "
                + "FROM IAFAS_CLASIFICACION_DOCUMENTOS WHERE "
                + "CESTADO_CODIGO!='AN' "
                + "ORDER BY DESCRIPCION";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getClasificacionDocumento() " + e.getMessage());
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
    public List getTipoDecretos() {
        lista = new LinkedList<>();
        sql = "SELECT NTIPO_DECRETO_CODIGO AS CODIGO, VTIPO_DECRETO_DESCRIPCION AS DESCRIPCION "
                + "FROM IAFAS_TIPO_DECRETOS WHERE "
                + "CESTADO_CODIGO!='AN' "
                + "ORDER BY DESCRIPCION";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTipoDecretos() " + e.getMessage());
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
    public List getDocumentoReferencia(String periodo, String tipo) {
        lista = new LinkedList<>();
        String add = "";
        if (tipo.equals("I")) {
            add = " CDOCUMENTO_ESTADO='DE' ";
        } else {
            add = " CDOCUMENTO_ESTADO  IN ('DE','RE') ";
        }
        sql = "SELECT CDOCUMENTO_NUMERO AS CODIGO,"
                + "PK_MESA_PARTES.FUN_NOMBRE_TIPO_DOCUMENTO(NTIPO_DOCUMENTO_CODIGO)||'-'||CDOCUMENTO_NRO_DOCUMENTO AS DESCRIPCION "
                + "FROM SIPE_DOCUMENTO WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CDOCUMENTO_TIPO='E' AND "
                + add
                + "ORDER BY DESCRIPCION";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDocumentoReferencia() " + e.getMessage());
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
    public List getUsuarioDocumento(String usuario) {
        lista = new LinkedList<>();
        sql = "SELECT CODUSU AS CODIGO, NOMUSU||' '||APEUSU AS USUARIO FROM TABUSU "
                + "WHERE COUUOO='0003' AND DIPPTO='S' AND ESTREG='AC' "
                + "AND CODUSU=? "
                + "ORDER BY USUARIO ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, usuario);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("USUARIO"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getUsuarioDocumento(usuario) " + e.getMessage());
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
    public List getDocumentoReferencia(String periodo, String tipo, String usuario) {
        lista = new LinkedList<>();
        String add = "";
        if (tipo.equals("I")) {
            add = " DD.CESTADO_DOCUMENTO IN ('RE') ";
        } else {
            add = " DD.CESTADO_DOCUMENTO  IN ('DE','RE','RS') ";
        }
        sql = "SELECT D.CDOCUMENTO_NUMERO AS CODIGO,"
                + "PK_MESA_PARTES.FUN_NOMBRE_TIPO_DOCUMENTO(D.NTIPO_DOCUMENTO_CODIGO)||'-'||TRIM(D.CDOCUMENTO_NRO_DOCUMENTO)||' '||TRIM(VDOCUMENTO_ASUNTO) AS DESCRIPCION "
                + "FROM SIPE_DOCUMENTO D INNER JOIN SIPE_DECRETO_DOCUMENTO DD ON"
                + "(D.CPERIODO_CODIGO=DD.CPERIODO_CODIGO AND "
                + "D.CDOCUMENTO_NUMERO=DD.CDOCUMENTO_NUMERO) "
                + " WHERE "
                + "D.CPERIODO_CODIGO=? AND "
                + "DD.VUSUARIO_RECEPCION=? AND "
                + "D.CDOCUMENTO_TIPO='E' AND "
                + add
                + "ORDER BY DESCRIPCION";

        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, usuario);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDocumentoReferencia() " + e.getMessage());
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
    public List getUsuarioSubDecreto(String area, String codusu) {
        lista = new LinkedList<>();
        sql = "SELECT CODUSU AS CODIGO,"
                + "NOMUSU||' '||APEUSU AS DESCRIPCION "
                + "FROM TABUSU WHERE "
                + "COUUOO='0003' AND ESTREG='AC' AND "
                + "USUTROPA='N' AND "
                + "COARLB='" + area + "' AND "
                + "CODUSU NOT IN (?)"
                + "ORDER BY DESCRIPCION ";

        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, codusu);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getUsuarioSubDecreto(String area) " + e.getMessage());
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
    public List getGenericaGastoUnidad(String periodo, Integer presupuesto, String unidadOperativa) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DEL PRESUPUESTO O FUENTE DE FINANCIAMIENTO POR
         * ORDEN DESCENDENTES Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT TRIM(CODGEN) AS CODIGO, TRIM(CODGEN)||':'||UTIL_NEW.FUN_NOMGEN(TRIM(CODGEN)) AS DESCRIPCION "
                + "FROM MODEPA WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "COUUOO=? "
                + "GROUP BY TRIM(CODGEN) "
                + "UNION ALL "
                + "SELECT DISTINCT '*' AS CODIGO, 'TODAS LAS GENERICAS' AS DESCRIPCION "
                + "FROM DUAL "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objPreparedStatement.setString(3, unidadOperativa);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getGenericaGastoUnidad(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ") " + e.getMessage());
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

    //PERSONAL
    @Override
    public List getPersonalOpre() {
        lista = new LinkedList<>();
        sql = "SELECT VDNI_PERSONAL AS CODIGO,"
                + "VNOMBRES_PERSONAL||' '||VAPELLIDOS_PERSONAL AS DESCRIPCION "
                + " FROM SIPE_REGISTRO_PERSONAL "
                + "WHERE "
                + "CESTADO_PERSONAL='AC' ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getPersonalOpre() " + e.getMessage());
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

    //LOGISTICA
    @Override
    public List getTipoProcesoContratacion() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA AREA LABORAL
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NTIPO_PROCESO_CONTRATACION_CODIGO AS CODIGO, "
                + "VTIPO_PROCESO_CONTRATACION_DESCRIPCION AS DESCRIPCION "
                + "FROM IAFAS_TIPO_PROCESO_CONTRATACION WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTipoProcesoContratacion() " + e.getMessage());
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
    public List getProcesoEtapa(Integer tipoProcesoContratacion) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA AREA LABORAL
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NPROCESO_ETAPA_CODIGO AS CODIGO, VPROCESO_ETAPA_DESCRIPCION AS DESCRIPCION "
                + "FROM IAFAS_PROCESO_ETAPA WHERE "
                + "NTIPO_PROCESO_CONTRATACION_CODIGO=? AND "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setInt(1, tipoProcesoContratacion);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getProcesoEtapa(" + tipoProcesoContratacion + ") " + e.getMessage());
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
    public List getProcesoDocumento() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA AREA LABORAL
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NPROCESO_DOCUMENTO_CODIGO AS CODIGO, VPROCESO_DOCUMENTO_DESCRIPCION AS DESCRIPCION "
                + "FROM IAFAS_PROCESO_DOCUMENTO WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getProcesoDocumento() " + e.getMessage());
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
    public List getTipoProcedimiento() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA AREA LABORAL
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CTIPO_PROCEDIMIENTO_CODIGO AS CODIGO, VTIPO_PROCEDIMIENTO_DESCRIPCION AS DESCRIPCION "
                + "FROM IAFAS_TIPO_PROCEDIMIENTO_SELEC WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTipoProcedimiento() " + e.getMessage());
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
    public List getTipoProcedimientoTipoDocumento(String tipoDocumento) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA AREA LABORAL
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CTIPO_PROCEDIMIENTO_CODIGO AS CODIGO, `PK_UTIL.FUN_NOMBRE_TIPO_PROCEDIMIENTO`(CTIPO_PROCEDIMIENTO_CODIGO) AS DESCRIPCION "
                + "FROM IAFAS_PROCESO_DOCUMENTO_TIPO_PROCEDIMIENTO WHERE "
                + "NPROCESO_DOCUMENTO_CODIGO=? AND "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, tipoDocumento);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTipoProcedimientoTipoDocumento(" + tipoDocumento + ") " + e.getMessage());
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
    public List getCompromisoAnualCertificado(String periodo, Integer presupuesto, String unidadOperativa, String certificado) {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA AREA LABORAL
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT NROCER_CA AS CODIGO, NROCER_CA||':'||SUBSTR(DESOCE,1,120)||' S/ '||TO_CHAR(SUM(SALDO_SDPPTO.IMPORTE_COMPROMISO_ANUAL(CPERIODO_CODIGO, COUUOO, NFUENTE_FINANCIAMIENTO_CODIGO, NROCER_CA)) ,'FM999,999,999,999.009') AS DESCRIPCION "
                + "FROM TASOCP_CA WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "NFUENTE_FINANCIAMIENTO_CODIGO=? AND "
                + "COUUOO=? AND "
                + "TIPSOL IN ('CE') AND "
                + "NROCER=(SELECT DISTINCT NROCER FROM COMCAB WHERE COMCAB.CPERIODO_CODIGO=TASOCP_CA.CPERIODO_CODIGO AND COMCAB.NFUENTE_FINANCIAMIENTO_CODIGO=TASOCP_CA.NFUENTE_FINANCIAMIENTO_CODIGO AND COMCAB.COUUOO=TASOCP_CA.COUUOO AND "
                + "TO_NUMBER(UTIL_NEW.FUN_NUM_CERTIFICADO_SIAF(COMCAB.CPERIODO_CODIGO, COMCAB.NFUENTE_FINANCIAMIENTO_CODIGO, COMCAB.COUUOO, COMCAB.NROCOB))=TO_NUMBER(?) AND "
                + "UTIL_NEW.FUN_TIPO_CERTIFICADO(COMCAB.CPERIODO_CODIGO, COMCAB.NFUENTE_FINANCIAMIENTO_CODIGO, COMCAB.NROCOB)='CE') AND "
                + "ESTCCP!='AN' "
                + "GROUP BY NROCER_CA, DESOCE "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objPreparedStatement.setString(3, unidadOperativa);
            objPreparedStatement.setString(4, certificado);
            objResultSet = objPreparedStatement.executeQuery();
            comun = new BeanComun();
            comun.setCodigo("0");
            comun.setDescripcion("Selección");
            lista.add(comun);
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getCompromisoAnualCertificado(" + periodo + "," + presupuesto + ", " + unidadOperativa + "," + certificado + " ) " + e.getMessage());
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
}
