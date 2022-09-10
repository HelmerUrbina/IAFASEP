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
public interface AreaLaboralDAO extends JpaRepository<BeanComun, String> {

    @Query(nativeQuery = true, value = "SELECT NAREA_LABORAL_CODIGO AS CODIGO, "
            + "VAREA_LABORAL_DESCRIPCION AS DESCRIPCION, VAREA_LABORAL_ABREVIATURA AS ABREVIATURA, "
            + "PK_UTIL.FUN_ESTADO_DESCRIPCION(CESTADO_CODIGO) ESTADO "
            + "FROM IAFAS_AREA_LABORAL  "
            + "ORDER BY CODIGO")
    @Override
    List<BeanComun> findAll();

    @Transactional
    @Modifying
    @Query(value = "{CALL SP_IDU_AREA_LABORAL(:codigo, :descripcion, :abreviatura, :usuario, :modo)}", nativeQuery = true)
    void sp_areaLaboral(
            @Param("codigo") int codigo,
            @Param("descripcion") String descripcion,
            @Param("abreviatura") String abreviatura,
            @Param("usuario") String usuario,
            @Param("modo") String modo);

}
