/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafasep.DataService.DAO;

import com.iafasep.BusinessServices.Beans.BeanCuadroNecesidad;
import com.iafasep.BusinessServices.Beans.BeanProgramacionEventoPrincipal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Repository
public interface CuadroNecesidadDAO extends JpaRepository<BeanCuadroNecesidad, Integer>{
    
    @Query(nativeQuery = true, value = " SELECT P.NTAREA_PRESUPUESTAL_CODIGO,\n" +
                                        "        T.VTAREA_PRESUPUESTAL_ABREVIATUR TAREA,\n" +
                                        "        PK_UTIL.FUN_SUM_META_EVENTO_FINAL(P.CPERIODO_CODIGO,P.NFUENTE_FINANCIAMIENTO_CODIGO,P.NTAREA_PRESUPUESTAL_CODIGO) META,\n" +
                                        "        PK_UTIL.FUN_SUM_EVENTO_CNV(P.CPERIODO_CODIGO,P.NFUENTE_FINANCIAMIENTO_CODIGO,P.NTAREA_PRESUPUESTAL_CODIGO) TOTAL,\n" +
                                        "        SUM(COALESCE(P.NPROGRAMACION_MULTIANUAL_IMPOR,0)) PROGRAMADO \n" +
                                        "   FROM IAFAS_PROG_MULTIANUAL P\n" +
                                        "        INNER JOIN IAFAS_TAREA_PRESUPUESTAL T ON T.NTAREA_PRESUPUESTAL_CODIGO=P.NTAREA_PRESUPUESTAL_CODIGO\n" +
                                        "  WHERE P.CESTADO_CODIGO = 'AC'\n" +
                                        "    AND P.CPERIODO_CODIGO = ?1\n" +
                                        "    AND P.NFUENTE_FINANCIAMIENTO_CODIGO = ?2\n" +
                                        "  GROUP BY T.VTAREA_PRESUPUESTAL_ABREVIATUR,P.CPERIODO_CODIGO,P.NFUENTE_FINANCIAMIENTO_CODIGO,P.NTAREA_PRESUPUESTAL_CODIGO\n" +
                                        "  ORDER BY P.NTAREA_PRESUPUESTAL_CODIGO")
    List<BeanCuadroNecesidad> findByPeriodoFuente(String periodo, Integer fuente);
    
}
