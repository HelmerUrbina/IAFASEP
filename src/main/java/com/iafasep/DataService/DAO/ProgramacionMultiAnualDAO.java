/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafasep.DataService.DAO;

import com.iafasep.BusinessServices.Beans.BeanComun;
import com.iafasep.BusinessServices.Beans.BeanProgramacionMultianual;
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
public interface ProgramacionMultiAnualDAO extends JpaRepository<BeanProgramacionMultianual, String>{
    
    @Query(nativeQuery = true, value = "SELECT ROWNUM CONTADOR,\n" +
                                        "       NTAREA_PRESUPUESTAL_CODIGO TAREA_CODIGO,\n" +
                                        "       PK_UTIL.FUN_TAREA_PRESUPUESTAL_ABREVIATURA(NTAREA_PRESUPUESTAL_CODIGO) TAREA,\n" +
//                                        "       PK_UTIL.FUN_TAREA_PRESUPUESTAL_ABRE(NTAREA_PRESUPUESTAL_CODIGO) TAREA,\n" +
                                        "       CANIO_REGISTRO ANIO,\n" +
                                        "       NPROGRAMACION_MULTIANUAL_IMPOR PROGRAMADO,\n" +
                                        "       COALESCE(PK_UTIL.FUN_PROGRAMACION_MULTI_DET(CPERIODO_CODIGO,CANIO_REGISTRO,NFUENTE_FINANCIAMIENTO_CODIGO,NTAREA_PRESUPUESTAL_CODIGO),0) DETALLE,\n" +
                                        "       NPROGRAMACION_MULTIANUAL_IMPOR - COALESCE(PK_UTIL.FUN_PROGRAMACION_MULTI_DET(CPERIODO_CODIGO,CANIO_REGISTRO,NFUENTE_FINANCIAMIENTO_CODIGO,NTAREA_PRESUPUESTAL_CODIGO),0) SALDO, \n" +
                                        "       NPROGRAMACION_MULTIANUAL_META META,\n" +
                                        "       PK_UTIL.FUN_ESTADO_DESCRIPCION(CESTADO_CODIGO) ESTADO,\n" +
                                        "       CUBIGEO_CODIGO UBIGEO_CODIGO,\n" +
                                        "       PK_UTIL.FUN_DEPARTAMENTO_NOMBRE(SUBSTR(150129,1,2)) DEPARTAMENTO,\n" +
                                        "       PK_UTIL.FUN_PROVINCIA_NOMBRE(SUBSTR(150129,1,2),SUBSTR(150129,3,2)) PROVINCIA,\n" +
                                        "       PK_UTIL.FUN_UBIGEO_NOMBRE(CUBIGEO_CODIGO) DISTRITO\n" +
                                        "  FROM IAFAS_PROG_MULTIANUAL\n" +
                                        " WHERE NFUENTE_FINANCIAMIENTO_CODIGO = ?1\n" +
                                        "   AND CPERIODO_CODIGO = ?2\n" +
                                        " ORDER BY NTAREA_PRESUPUESTAL_CODIGO, CANIO_REGISTRO")
    List<BeanProgramacionMultianual> findAll(Integer fuente, String periodo);
    
    @Transactional
    @Modifying
    @Query(value = "{CALL SP_IDU_PROG_MULTIANUAL(:periodo, :fuente, :tarea, :ubigeo, :cant, :usuario, :modo)}", nativeQuery = true)
    void sp_progMultiAnual(
            @Param("periodo") String periodo,
            @Param("fuente") Integer fuente,
            @Param("tarea") Integer tarea,
            @Param("ubigeo") String ubigeo,
            @Param("cant") String cant,
            @Param("usuario") String usuario,
            @Param("modo") String modo);
    
}
