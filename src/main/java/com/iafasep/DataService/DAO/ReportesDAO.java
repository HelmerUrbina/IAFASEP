package com.iafasep.DataService.DAO;

import java.util.List;
import com.iafasep.BusinessServices.Beans.BeanReportes;
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
public interface ReportesDAO extends JpaRepository<BeanReportes, String> {

    @Query(nativeQuery = true, value = "SELECT CREPORTE_CODIGO, "
            + "VREPORTE_NOMBRE, CASE CREPORTE_FIRMA WHEN '1' THEN 'SI' ELSE 'NO' END AS CREPORTE_FIRMA, "
            + "UTIL.FUN_ESTADO_DESCRIPCION(CESTADO_CODIGO) CESTADO_CODIGO "
            + "FROM SINTE_REPORTES "
            + "ORDER BY CREPORTE_CODIGO")
    @Override
    List<BeanReportes> findAll();

    @Transactional
    @Modifying
    @Query(value = "{CALL SP_IDU_REPORTES(:codigo, :nombre, :firma, :usuario, :modo)}", nativeQuery = true)
    void sp_reportes(
            @Param("codigo") String codigo,
            @Param("nombre") String nombre,
            @Param("firma") Integer firma,
            @Param("usuario") String usuario,
            @Param("modo") String modo);
}
