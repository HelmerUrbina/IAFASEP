package com.iafasep.DataService.DAO;

import java.util.List;
import com.iafasep.BusinessServices.Beans.BeanUsuarioOpciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
public interface UsuarioOpcionesDAO extends JpaRepository<BeanUsuarioOpciones, String> {

    @Query(nativeQuery = true, value = " SELECT "
            + "MO.NMODULO_CODIGO||M.NMENU_CODIGO VUSUARIO_CODIGO, "
            + "MO.NMODULO_CODIGO NMODULO_CODIGO, MO.VMODULO_NOMBRE VUSUARIO_CREADOR, "
            + "M.VMENU_NOMBRE NMENU_CODIGO "
            + "FROM IAFAS_MENU M INNER JOIN IAFAS_MODULOS MO ON "
            + "(M.NMODULO_CODIGO=MO.NMODULO_CODIGO) "
            + "ORDER BY VUSUARIO_CODIGO")
    List<BeanUsuarioOpciones> getOpcionesUsuario();

    @Query(nativeQuery = true, value = "SELECT "
            + "NMODULO_CODIGO||NMENU_CODIGO VUSUARIO_CODIGO, "
            + "'' NMODULO_CODIGO, '' VUSUARIO_CREADOR, '' NMENU_CODIGO "
            + "FROM IAFAS_USUARIOS_MENU WHERE "
            + "VUSUARIO_CODIGO=?1 "
            + "ORDER BY VUSUARIO_CODIGO")
    List<BeanUsuarioOpciones> getOpcionesOfUsuario(String usuario);
}
