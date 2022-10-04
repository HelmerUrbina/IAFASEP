/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafasep.DataService.DAO;

import com.iafasep.BusinessServices.Beans.BeanProgramacionEventoFinal;
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
public interface ProgramacionEventoFinalDAO extends JpaRepository<BeanProgramacionEventoFinal, String> {

    @Query(nativeQuery = true, value = "SELECT VEVENTO_PRINCIPAL_CODIGO, \n" +
                                        "      NEVENTO_FINAL_CODIGO, \n" +
                                        "      VEVENTO_FINAL_NOMBRE, \n" +
                                        "      NEVENTO_FINAL_PRIORIDAD,\n" +
                                        "      NEVENTO_FINAL_META_FISICA,\n" +
                                        "      PK_UTIL.FUN_ESTADO_DESCRIPCION(CESTADO_CODIGO) CESTADO_CODIGO \n" +
                                        " FROM IAFAS_PROG_EVENTO_FINAL \n" +
                                        "WHERE CPERIODO_CODIGO= ?1\n" +
                                        "  AND NFUENTE_FINANCIAMIENTO_CODIGO= ?2\n" +
                                        "  AND NTAREA_PRESUPUESTAL_CODIGO= ?3\n" +
                                        "  AND VEVENTO_PRINCIPAL_CODIGO= ?4\n" +
                                        "GROUP BY VEVENTO_PRINCIPAL_CODIGO,NEVENTO_FINAL_CODIGO,VEVENTO_FINAL_NOMBRE,\n" +
                                        "         NEVENTO_FINAL_PRIORIDAD,CESTADO_CODIGO,NEVENTO_FINAL_META_FISICA\n" +
                                        "ORDER BY NEVENTO_FINAL_CODIGO")
    List<BeanProgramacionEventoFinal> findByPeriodoTipoAsignacionBrigadaEventoPrincipal(String periodo, Integer fuente, Integer tarea, String eventoPrincipal);

    @Transactional
    @Modifying
    @Query(value = "{CALL SP_IDU_PROG_EVENTO_FINAL(:periodo, :fuente, :tarea, :eventoPrincipal, :eventoFinal, :eventoFinalNombre, :prioridad, :meta, :usuario, :modo)}", nativeQuery = true)
    void sp_programacionEventoFinal(
            @Param("periodo") String periodo,
            @Param("fuente") Integer fuente,
            @Param("tarea") Integer tarea,
            @Param("eventoPrincipal") String eventoPrincipal,
            @Param("eventoFinal") Integer eventoFinal,
            @Param("eventoFinalNombre") String eventoFinalNombre,
            @Param("prioridad") Integer prioridad,
            @Param("meta") Integer meta,
            @Param("usuario") String usuario,
            @Param("modo") String modo);
}
