/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafasep.DataService.DAO;

import com.iafasep.BusinessServices.Beans.BeanProgramacionMultianualDetalle;
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
public interface ProgramacionMultiAnualDetalleDao extends JpaRepository<BeanProgramacionMultianualDetalle, String>{
    
    @Query(nativeQuery = true, value = "SELECT ROWNUM CONTADOR,\n" +
                                        "        NCLASIFICADOR_PRESUPUESTAL_COD CLASIFICADOR_CODIGO,\n" +
                                        "        PK_UTIL.FUN_CLASIFICADOR_PRESUPUESTAL(NCLASIFICADOR_PRESUPUESTAL_COD) CLASIFICADOR,\n" +
                                        "        CANIO_REGISTRO ANIO,\n" +
                                        "        NPROGRAMACION_MULTIANUAL_IMPOR IMPOR\n" +
                                        "   FROM IAFAS_PROG_MULTIANUAL_DETALLE\n" +
                                        "  WHERE NFUENTE_FINANCIAMIENTO_CODIGO = ?1\n" +
                                        "    AND CPERIODO_CODIGO = ?2\n" +
                                        "    AND NTAREA_PRESUPUESTAL_CODIGO = ?3\n" +
                                        "  ORDER BY CLASIFICADOR_CODIGO,CANIO_REGISTRO")
    List<BeanProgramacionMultianualDetalle> findAll(Integer fuente, String periodo, Integer tarea);
    
    @Transactional
    @Modifying
    @Query(value = "{CALL SP_IDU_PROG_MULTIANUAL_DET(:periodo, :fuente, :tarea, :cant, :usuario)}", nativeQuery = true)
    void sp_progMultiAnualDetalle(
            @Param("periodo") String periodo,
            @Param("fuente") Integer fuente,
            @Param("tarea") Integer tarea,
            @Param("cant") String cant,
            @Param("usuario") String usuario);
    
}
