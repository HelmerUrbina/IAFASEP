/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.DataService.Despachadores.Impl;

import com.iafas.BusinessServices.Beans.BeanMsgerr;
import com.iafas.BusinessServices.Beans.BeanUsuario;
import com.iafas.BusinessServices.Beans.BeanUsuarioMenu;
import com.iafas.DataService.Despachadores.MsgerrDAO;
import com.iafas.DataService.Despachadores.UsuarioDAO;
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
public class UsuarioDAOImpl implements UsuarioDAO {

    private final Connection objConnection;
    private PreparedStatement objPreparedStatement;
    private ResultSet objResultSet;
    private String sql;
    private List lista;
    private BeanUsuario objBnUsuario;
    private BeanUsuarioMenu objBnUsuarioMenu;
    private BeanMsgerr objBnMsgerr;
    private MsgerrDAO objDsMsgerr;
    private Integer s = 0;

    public UsuarioDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    @Override
    public BeanUsuario autentica(String usuario, String password, String periodo) {
     //    password = EncriptarCadena(password, true);
        sql = "SELECT VUSUARIO_CODIGO, UPPER(VUSUARIO_PATERNO) AS VUSUARIO_PATERNO, "
                + "UPPER(VUSUARIO_MATERNO) AS VUSUARIO_MATERNO, UPPER(VUSUARIO_NOMBRES) AS VUSUARIO_NOMBRES, "
                + "CASE NUSUARIO_AUTORIZACION WHEN 1 THEN 'TRUE' ELSE 'FALSE' END AS AUTORIZACION "
                + "FROM IAFAS_USUARIOS WHERE "
                + "VUSUARIO_CODIGO=? AND "
                + "VUSUARIO_PASSWORD=? AND "
                + "CESTADO_CODIGO=?";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, usuario);
            objPreparedStatement.setString(2, password);
            objPreparedStatement.setString(3, "AC");
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBnUsuario = new BeanUsuario();
                objBnUsuario.setUsuario(objResultSet.getString("VUSUARIO_CODIGO"));
                objBnUsuario.setPaterno(objResultSet.getString("VUSUARIO_PATERNO"));
                objBnUsuario.setMaterno(objResultSet.getString("VUSUARIO_MATERNO"));
                objBnUsuario.setNombres(objResultSet.getString("VUSUARIO_NOMBRES"));
                objBnUsuario.setAutorizacion(objResultSet.getBoolean("AUTORIZACION"));
                objBnUsuario.setSistema("IAFAS");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
        return objBnUsuario;
    }

    @Override
    public List getModulos(String usuario) {
        lista = new LinkedList<>();
        sql = "SELECT DISTINCT MODULO.CMODULO_CODIGO AS CODIGO, MODULO.VMODULO_NOMBRE AS DESCRIPCION "
                + "FROM IAFAS_USUARIOS_MENU MENU INNER JOIN IAFAS_MODULOS MODULO ON "
                + "(MENU.CMODULO_CODIGO=MODULO.CMODULO_CODIGO) WHERE "
                + "MENU.VUSUARIO_CODIGO=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, usuario);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnUsuarioMenu = new BeanUsuarioMenu();
                objBnUsuarioMenu.setModulo(objResultSet.getString("CODIGO"));
                objBnUsuarioMenu.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(objBnUsuarioMenu);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener Modulos('" + usuario + "') : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objPreparedStatement.close();
                    objResultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return lista;
    }

    @Override
    public List getMenu(String usuario) {
        lista = new LinkedList<>();
        sql = "SELECT IAFAS_USUARIOS_MENU.CMODULO_CODIGO AS MODULO, IAFAS_MENU.CMENU_CODIGO AS MENU, "
                + "IAFAS_MENU.VMENU_NOMBRE AS DESCRIPCION, IAFAS_MENU.VMENU_SERVLET AS SERVLET, "
                + "IAFAS_MENU.VMENU_MODO AS MODO "
                + "FROM IAFAS_USUARIOS_MENU INNER JOIN IAFAS_MENU ON (IAFAS_USUARIOS_MENU.CMODULO_CODIGO=IAFAS_MENU.CMODULO_CODIGO AND "
                + "IAFAS_USUARIOS_MENU.CMENU_CODIGO=IAFAS_MENU.CMENU_CODIGO) WHERE "
                + "IAFAS_USUARIOS_MENU.VUSUARIO_CODIGO=? "
                + "ORDER BY 1, 2";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, usuario);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnUsuarioMenu = new BeanUsuarioMenu();
                objBnUsuarioMenu.setModulo(objResultSet.getString("MODULO"));
                objBnUsuarioMenu.setMenu(objResultSet.getString("MENU"));
                objBnUsuarioMenu.setDescripcion(objResultSet.getString("DESCRIPCION"));
                objBnUsuarioMenu.setServlet(objResultSet.getString("SERVLET"));
                objBnUsuarioMenu.setModo(objResultSet.getString("MODO"));
                lista.add(objBnUsuarioMenu);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getMenu('" + usuario + "') : " + e.getMessage());
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
    public ArrayList getMenu() {
        ArrayList<String> Arreglo = new ArrayList<>();
        ArrayList<String> Filas = new ArrayList<>();
        sql = "SELECT MO.CMODULO_CODIGO||ME.CMENU_CODIGO AS CODIGO, MO.CMODULO_CODIGO, "
                + "MO.VMODULO_NOMBRE AS MODULO, ME.VMENU_NOMBRE AS MENU "
                + "FROM IAFAS_MODULOS MO INNER JOIN IAFAS_MENU ME ON "
                + "(MO.CMODULO_CODIGO=ME.CMODULO_CODIGO)  "
                + "ORDER BY CODIGO ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                Filas.clear();
                String arreglo = objResultSet.getString("CODIGO") + "+++"
                        + objResultSet.getString("CMODULO_CODIGO") + "+++"
                        + objResultSet.getString("MODULO") + "+++"
                        + objResultSet.getString("MENU");
                Filas.add(arreglo);
                Arreglo.add("" + Filas);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getMenu() : " + e.getMessage());
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
    public ArrayList getOpciones(BeanUsuario objBeanUsuario, String usuario) {
        ArrayList<String> Arreglo = new ArrayList<>();
        sql = "SELECT CMODULO_CODIGO||CMENU_CODIGO AS CODIGO "
                + "FROM IAFAS_USUARIOS_MENU "
                + "WHERE VUSUARIO_CODIGO=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanUsuario.getUsuario());
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                Arreglo.add(objResultSet.getString("CODIGO"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getOpciones(objBeanUsuario, usuario) : " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objPreparedStatement.close();
                    objResultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return Arreglo;
    }

    @Override
    public List getListaUsuarios(String usuario) {
        lista = new LinkedList<>();
        sql = "SELECT VUSUARIO_CODIGO AS USUARIO, "
                + "VUSUARIO_PATERNO||' '||VUSUARIO_MATERNO||', '||VUSUARIO_NOMBRES AS APELLIDOS_NOMBRES, "
                + "PK_UTIL.FUN_AREA_LABORAL(CAREA_LABORAL_CODIGO) AS AREA_LABORAL, "
                + "VUSUARIO_CARGO AS CARGO, VUSUARIO_CORREO AS CORREO, VUSUARIO_TELEFONO AS TELEFONO, "
                + "CASE NUSUARIO_AUTORIZACION WHEN 1 THEN 'TRUE' ELSE 'FALSE' END AS AUTORIZACION, "
                + "PK_UTIL.FUN_DESCRIPCION_ESTADO(CESTADO_CODIGO) AS ESTADO "
                + "FROM IAFAS_USUARIOS "
                + "ORDER BY APELLIDOS_NOMBRES";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objBnUsuario = new BeanUsuario();
                objBnUsuario.setUsuario(objResultSet.getString("USUARIO"));
                objBnUsuario.setNombres(objResultSet.getString("APELLIDOS_NOMBRES"));
                objBnUsuario.setAreaLaboral(objResultSet.getString("AREA_LABORAL"));
                objBnUsuario.setCargo(objResultSet.getString("CARGO"));
                objBnUsuario.setCorreo(objResultSet.getString("CORREO"));
                objBnUsuario.setTelefono(objResultSet.getString("TELEFONO"));
                objBnUsuario.setAutorizacion(objResultSet.getBoolean("AUTORIZACION"));
                objBnUsuario.setEstado(objResultSet.getString("ESTADO"));
                lista.add(objBnUsuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getListaUsuarios(" + usuario + ") : " + e.getMessage());
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
    public BeanUsuario getUsuario(BeanUsuario objBeanUsuario, String usuario) {
        sql = "SELECT VUSUARIO_PATERNO, VUSUARIO_MATERNO, VUSUARIO_NOMBRES, "
                + "CAREA_LABORAL_CODIGO, VUSUARIO_CARGO, VUSUARIO_CORREO, VUSUARIO_TELEFONO, "
                + "NUSUARIO_AUTORIZACION, CESTADO_CODIGO "
                + "FROM IAFAS_USUARIOS WHERE "
                + "VUSUARIO_CODIGO=? ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, objBeanUsuario.getUsuario());
            objResultSet = objPreparedStatement.executeQuery();
            if (objResultSet.next()) {
                objBeanUsuario.setPaterno(objResultSet.getString("VUSUARIO_PATERNO"));
                objBeanUsuario.setMaterno(objResultSet.getString("VUSUARIO_MATERNO"));
                objBeanUsuario.setNombres(objResultSet.getString("VUSUARIO_NOMBRES"));
                objBeanUsuario.setAreaLaboral(objResultSet.getString("CAREA_LABORAL_CODIGO"));
                objBeanUsuario.setCargo(objResultSet.getString("VUSUARIO_CARGO"));
                objBeanUsuario.setCorreo(objResultSet.getString("VUSUARIO_CORREO"));
                objBeanUsuario.setTelefono(objResultSet.getString("VUSUARIO_TELEFONO"));
                objBeanUsuario.setAutorizacion(objResultSet.getBoolean("NUSUARIO_AUTORIZACION"));
                objBeanUsuario.setEstado(objResultSet.getString("CESTADO_CODIGO"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getUsuario(" + usuario + ") : " + e.getMessage());
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
        return objBeanUsuario;
    }

    @Override
    public int iduUsuario(BeanUsuario objBeanUsuario, String usuario) {
        /*
         * EJECUTAMOS EL PROCEDIMIENTO ALMACENADO PARA LOS USUARIOS DEL SISTEMA, 
         * EN EL CUAL PODEMOS INSERTAR, MODIFICAR O ELIMINAR UN REGISTRO DE LA TABLA
         * USUARIO, EN CASO DE OBTENER ALGUN ERROR ACTIVARA LA OPCION DE
         * EXCEPCIONES EN LA CUAL SE REGISTRARA EL ERROR EL MOTIVO DEL ERROR.
         */
        String password = "";
        if (objBeanUsuario.getMode().equals("I")) {
            objBeanUsuario.setPassword(com.iafas.Utiles.Utiles.generarAleatorio(8));
            password = EncriptarCadena(objBeanUsuario.getPassword(), true);
        }
        if (objBeanUsuario.getMode().equals("P")) {
            password = EncriptarCadena(objBeanUsuario.getPassword(), true);
        }
        sql = "{CALL SP_IDU_USUARIOS(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanUsuario.getUsuario());
            cs.setString(2, password);
            cs.setString(3, objBeanUsuario.getPaterno());
            cs.setString(4, objBeanUsuario.getMaterno());
            cs.setString(5, objBeanUsuario.getNombres());
            cs.setString(6, objBeanUsuario.getAreaLaboral());
            cs.setString(7, objBeanUsuario.getCargo());
            cs.setString(8, objBeanUsuario.getCorreo());
            cs.setString(9, objBeanUsuario.getTelefono());
            cs.setBoolean(10, objBeanUsuario.getAutorizacion());
            cs.setString(11, objBeanUsuario.getEstado());
            cs.setString(12, usuario);
            cs.setString(13, objBeanUsuario.getMode());
            s = cs.executeUpdate();
            cs.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduUsuario : " + e.getMessage());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(usuario);
            objBnMsgerr.setTabla("IAFAS_USUARIOS");
            objBnMsgerr.setTipo(objBeanUsuario.getMode().toUpperCase());
            objBnMsgerr.setDescripcion(e.getMessage());
            s = objDsMsgerr.iduMsgerr(objBnMsgerr);
            return 0;
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al ejecutar iduUsuario : " + e.toString());
            }
        }
        return s;
    }

    @Override
    public int iduOpciones(BeanUsuario objBeanUsuario, String usuario) {
        /*
         * EJECUTAMOS EL PROCEDIMIENTO ALMACENADO PARA LOS PROVEEDORES, EN EL
         * CUAL PODEMOS INSERTAR, MODIFICAR O ELIMINAR UN REGISTRO DE LA TABLA
         * USUARIO, EN CASO DE OBTENER ALGUN ERROR ACTIVARA LA OPCION DE
         * EXCEPCIONES EN LA CUAL SE REGISTRARA EL ERROR EL MOTIVO DEL ERROR.
         */
        sql = "{CALL SP_IDU_USUARIOS_MENU(?,?,?,?,?)}";
        try (CallableStatement cs = objConnection.prepareCall(sql)) {
            cs.setString(1, objBeanUsuario.getUsuario());
            cs.setString(2, objBeanUsuario.getModulo());
            cs.setString(3, objBeanUsuario.getMenu());
            cs.setString(4, usuario);
            cs.setString(5, objBeanUsuario.getMode());
            s = cs.executeUpdate();
            cs.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar iduOpciones : " + e.getMessage());
            objDsMsgerr = new MsgerrDAOImpl(objConnection);
            objBnMsgerr = new BeanMsgerr();
            objBnMsgerr.setUsuario(usuario);
            objBnMsgerr.setTabla("IAFAS_USUARIOS");
            objBnMsgerr.setTipo(objBeanUsuario.getMode().toUpperCase());
            objBnMsgerr.setDescripcion(e.getMessage());
            s = objDsMsgerr.iduMsgerr(objBnMsgerr);
            return 0;
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al ejecutar iduOpciones : " + e.toString());
            }
        }
        return s;
    }

    public String EncriptarCadena(String cadena, boolean a) {
        String retorno = "";
        int it = cadena.length();
        int temp;
        int[] textoascii = new int[it];
        if (a) {
            for (int i = 0; i < it; i++) {
                textoascii[i] = cadena.charAt(i);
                temp = textoascii[i];
                temp = temp + 200 % 27;
                if (temp > 127) {
                    int v = temp / 127;
                    temp = temp - 128 * v;
                }
                retorno = retorno + (char) temp;
            }
        } else {
            for (int i = 0; i < it; i++) {
                textoascii[i] = cadena.charAt(i);
                temp = textoascii[i];
                temp = temp - 200 % 27;
                if (temp > 127) {
                    int v = temp / 127;
                    temp = temp - 128 * v;
                }
                retorno = retorno + (char) temp;
            }
        }
        return retorno;
    }
}
