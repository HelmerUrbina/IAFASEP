/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafasep.DataService.DAO;

import com.iafasep.BusinessServices.Beans.BeanProgramacionCNV;
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
public interface ProgramacionCNVDAO extends JpaRepository<BeanProgramacionCNV, String> {

    @Query(nativeQuery = true, value = "SELECT NITEM_CODIGO||'-'||NCLASIFICADOR_PRESUPUESTAL_COD CODIGO, \n" +
                                        "       PK_UTIL.FUN_ITEM(NITEM_CODIGO) NITEM_CODIGO, \n" +
                                        "       PK_UTIL.FUN_CLASIFICADOR_PRESUPUESTAL(NCLASIFICADOR_PRESUPUESTAL_COD) NCLASIFICADOR_PRESUPUESTAL_COD, \n" +
                                        "       PK_UTIL.FUN_UNIDAD_MEDIDA_ABRE(CUNIDAD_MEDIDA_CODIGO) CUNIDAD_MEDIDA_CODIGO,\n" +
                                        "       NCNV_PRECIO,\n" +
                                        "       SUM(CASE TO_NUMBER(CANIO_REGISTRO) WHEN TO_NUMBER(CPERIODO_CODIGO) THEN NCNV_DEMANDA_GLOBAL ELSE 0 END) AS DEM_A,\n" +
                                        "       SUM(CASE TO_NUMBER(CANIO_REGISTRO) WHEN TO_NUMBER(CPERIODO_CODIGO)+1 THEN NCNV_DEMANDA_GLOBAL ELSE 0 END) AS DEM_B,\n" +
                                        "       SUM(CASE TO_NUMBER(CANIO_REGISTRO) WHEN TO_NUMBER(CPERIODO_CODIGO)+2 THEN NCNV_DEMANDA_GLOBAL ELSE 0 END) AS DEM_C,\n" +
                                        "       SUM(CASE TO_NUMBER(CANIO_REGISTRO) WHEN TO_NUMBER(CPERIODO_CODIGO) THEN NCNV_CANTIDAD ELSE 0 END) AS CANT_A,\n" +
                                        "       SUM(CASE TO_NUMBER(CANIO_REGISTRO) WHEN TO_NUMBER(CPERIODO_CODIGO)+1 THEN NCNV_CANTIDAD ELSE 0 END) AS CANT_B,\n" +
                                        "       SUM(CASE TO_NUMBER(CANIO_REGISTRO) WHEN TO_NUMBER(CPERIODO_CODIGO)+2 THEN NCNV_CANTIDAD ELSE 0 END) AS CANT_C,\n" +
                                        "       SUM(CASE TO_NUMBER(CANIO_REGISTRO) WHEN TO_NUMBER(CPERIODO_CODIGO) THEN (NCNV_PRECIO*NCNV_CANTIDAD) ELSE 0 END) AS TOTAL_A,\n" +
                                        "       SUM(CASE TO_NUMBER(CANIO_REGISTRO) WHEN TO_NUMBER(CPERIODO_CODIGO)+1 THEN (NCNV_PRECIO*NCNV_CANTIDAD) ELSE 0 END) AS TOTAL_B,\n" +
                                        "       SUM(CASE TO_NUMBER(CANIO_REGISTRO) WHEN TO_NUMBER(CPERIODO_CODIGO)+2 THEN (NCNV_PRECIO*NCNV_CANTIDAD) ELSE 0 END) AS TOTAL_C,\n" +
                                        "       SUM(CASE TO_NUMBER(CANIO_REGISTRO) WHEN TO_NUMBER(CPERIODO_CODIGO) THEN PK_UTIL.FUN_SALDO_PROG_CLAS_ANIO(CPERIODO_CODIGO,NFUENTE_FINANCIAMIENTO_CODIGO,NTAREA_PRESUPUESTAL_CODIGO,NCLASIFICADOR_PRESUPUESTAL_COD,CANIO_REGISTRO) ELSE 0 END) AS SALDO_A,\n" +
                                        "       SUM(CASE TO_NUMBER(CANIO_REGISTRO) WHEN TO_NUMBER(CPERIODO_CODIGO)+1 THEN PK_UTIL.FUN_SALDO_PROG_CLAS_ANIO(CPERIODO_CODIGO,NFUENTE_FINANCIAMIENTO_CODIGO,NTAREA_PRESUPUESTAL_CODIGO,NCLASIFICADOR_PRESUPUESTAL_COD,CANIO_REGISTRO) ELSE 0 END) AS SALDO_B,\n" +
                                        "       SUM(CASE TO_NUMBER(CANIO_REGISTRO) WHEN TO_NUMBER(CPERIODO_CODIGO)+2 THEN PK_UTIL.FUN_SALDO_PROG_CLAS_ANIO(CPERIODO_CODIGO,NFUENTE_FINANCIAMIENTO_CODIGO,NTAREA_PRESUPUESTAL_CODIGO,NCLASIFICADOR_PRESUPUESTAL_COD,CANIO_REGISTRO) ELSE 0 END) AS SALDO_C,\n" +
                                        "       PK_UTIL.FUN_ESTADO_DESCRIPCION(CESTADO_CODIGO) CESTADO_CODIGO \n" +
                                        "  FROM IAFAS_PROG_CNV \n" +
                                        " WHERE CPERIODO_CODIGO= ?1\n" +
                                        "   AND NFUENTE_FINANCIAMIENTO_CODIGO= ?2\n" +
                                        "   AND NTAREA_PRESUPUESTAL_CODIGO= ?3\n" +
                                        "   AND VEVENTO_PRINCIPAL_CODIGO= ?4 \n" +
                                        "   AND NEVENTO_FINAL_CODIGO= ?5\n" +
                                        "GROUP BY NITEM_CODIGO,NCLASIFICADOR_PRESUPUESTAL_COD,CUNIDAD_MEDIDA_CODIGO,CESTADO_CODIGO,NCNV_PRECIO\n" +
                                        "ORDER BY NITEM_CODIGO,NCLASIFICADOR_PRESUPUESTAL_COD")
    List<BeanProgramacionCNV> findByPeriodoFuenteTareaEventoPrincipalEventoFinal(
            String periodo, Integer fuente, Integer tarea, String eventoPrincipal, Integer eventoFinal);

    @Transactional
    @Modifying
    @Query(value = "{CALL SP_IDU_PROG_CNV(:periodo, :fuente, :tarea, :eventoPrincipal, :eventoFinal, "
            + ":item, :clasificador, :precio, :json, :usuario, :modo)}", nativeQuery = true)
    void sp_programacionCNV(
            @Param("periodo") String periodo,
            @Param("fuente") Integer fuente,
            @Param("tarea") Integer tarea,
            @Param("eventoPrincipal") String eventoPrincipal,
            @Param("eventoFinal") Integer eventoFinal,
            @Param("item") Integer item,
            @Param("clasificador") Integer clasificador,
            @Param("precio") Double precio,
            @Param("json") String json,
            @Param("usuario") String usuario,
            @Param("modo") String modo);
}