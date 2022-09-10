package com.iafasep.DataService.DAO;

import com.iafasep.BusinessServices.Beans.BeanComun;
import java.util.List;
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
public interface TipoDocumentosDAO extends JpaRepository<BeanComun, String> {

    @Query(nativeQuery = true, value = "SELECT NTIPO_DOCUMENTO_CODIGO AS CODIGO, "
            + "VTIPO_DOCUMENTO_DESCRIPCION AS DESCRIPCION, VTIPO_DOCUMENTO_ABREVIATURA AS ABREVIATURA, "
            + "PK_UTIL.FUN_ESTADO_DESCRIPCION(CESTADO_CODIGO) ESTADO "
            + "FROM IAFAS_TIPO_DOCUMENTOS  "
            + "ORDER BY CODIGO")
    @Override
    List<BeanComun> findAll();

    @Transactional
    @Modifying
    @Query(value = "{CALL SP_IDU_TIPO_DOCUMENTOS(:codigo, :descripcion, :abreviatura, :usuario, :modo)}", nativeQuery = true)
    void sp_tipoDocumento(
            @Param("codigo") int codigo,
            @Param("descripcion") String descripcion,
            @Param("abreviatura") String abreviatura,
            @Param("usuario") String usuario,
            @Param("modo") String modo);

}
