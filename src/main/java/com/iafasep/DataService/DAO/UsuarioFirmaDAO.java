package com.iafasep.DataService.DAO;

import java.util.List;
import com.iafasep.BusinessServices.Beans.BeanUsuarioFirma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Repository
public interface UsuarioFirmaDAO extends JpaRepository<BeanUsuarioFirma, String> {

    @Query(nativeQuery = true, value = "SELECT "
            + "CPERIODO_CODIGO PERIODO, VUSUARIO_CODIGO USUARIO, VUSUARIO_NRO_SERIE SERIE, "
            + "PK_UTIL.FUN_USUARIO_NOMBRE(VUSUARIO_CODIGO) NOMBRE, NGRADO_CODIGO CODIGO_GRADO, "
            + "PK_UTIL.FUN_GRADO_ABREVIATURA(NGRADO_CODIGO) GRADO, "
            + "PK_UTIL.FUN_USUARIO_CARGO(VUSUARIO_CODIGO) CARGO, "
            + "PK_UTIL.FUN_ESTADO_DESCRIPCION(CESTADO_CODIGO) ESTADO "
            + "FROM IAFAS_USUARIOS_FIRMAS WHERE "
            + "CPERIODO_CODIGO=?1 "
            + "ORDER BY VUSUARIO_CODIGO")
    List<BeanUsuarioFirma> findAll(String periodo);

    @Transactional
    @Modifying
    @Query(value = "{CALL SP_IDU_USUARIO_FIRMA(:periodo, :usuario, :serie, :grado, :usuarioM, :modo)}", nativeQuery = true)
    void sp_firma(
            @Param("periodo") String periodo,
            @Param("usuario") String usuario,
            @Param("serie") String serie,
            @Param("grado") Integer grado,
            @Param("usuarioM") String usuarioM,
            @Param("modo") String modo);

}
