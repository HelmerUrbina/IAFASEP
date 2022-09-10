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
public interface PeriodosDAO extends JpaRepository<BeanComun, String> {

    @Query(nativeQuery = true, value = "SELECT CPERIODO_CODIGO AS CODIGO, "
            + "VPERIODO_DESCRIPCION AS DESCRIPCION, VPERIODO_ABREVIATURA AS ABREVIATURA, "
            + "PK_UTIL.FUN_ESTADO_DESCRIPCION(CESTADO_CODIGO) ESTADO "
            + "FROM IAFAS_PERIODOS "
            + "ORDER BY CODIGO")
    @Override
    List<BeanComun> findAll();

    @Transactional
    @Modifying
    @Query(value = "{CALL SP_IDU_PERIODOS(:codigo, :descripcion, :abreviatura, :usuario, :modo)}", nativeQuery = true)
    void sp_periodo(
            @Param("codigo") String codigo,
            @Param("descripcion") String descripcion,
            @Param("abreviatura") String abreviatura,
            @Param("usuario") String usuario,
            @Param("modo") String modo);
}
