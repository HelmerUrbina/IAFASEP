package com.iafasep.DataService.DAO;

import java.util.List;
import com.iafasep.BusinessServices.Beans.BeanMenu;
import com.iafasep.BusinessServices.Beans.BeanUsuarioMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Repository
public interface UsuarioMenuDAO extends JpaRepository<BeanUsuarioMenu, String> {

    @Query(nativeQuery = true, value = "SELECT "
            + "ME.NMODULO_CODIGO AS MODULO, ME.NMENU_CODIGO AS MENU, ME.VMENU_NOMBRE AS NOMBRE, "
            + "ME.VMENU_URL AS URL, ME.VMENU_MODO AS MODO "
            + "FROM IAFAS_MENU ME, IAFAS_USUARIOS_MENU U WHERE "
            + "U.NMODULO_CODIGO=ME.NMODULO_CODIGO AND "
            + "U.NMENU_CODIGO=ME.NMENU_CODIGO AND "
            + "U.VUSUARIO_CODIGO=?1 "
            + "ORDER BY MODULO, MENU ")
    List<BeanMenu> getMenuUsuario(String usuario);

    @Query(nativeQuery = true, value = "SELECT DISTINCT "
            + "MODU.NMODULO_CODIGO AS MODULO, MODU.VMODULO_NOMBRE AS NOMBRE "
            + "FROM IAFAS_USUARIOS_MENU MENU INNER JOIN IAFAS_MODULOS MODU ON ("
            + "MENU.NMODULO_CODIGO=MODU.NMODULO_CODIGO) WHERE "
            + "MENU.VUSUARIO_CODIGO=?1 "
            + "ORDER BY MODULO")
    List<BeanMenu> getModuloUsuario(String usuario);

}
