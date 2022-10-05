/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafasep.DataService.DAO;

import com.iafasep.BusinessServices.Beans.BeanProgramacionEventoPrincipal;
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
public interface ProgramacionEventoPrincipalDAO extends JpaRepository<BeanProgramacionEventoPrincipal, String>{
    
    @Query(nativeQuery = true, value = "  SELECT VEVENTO_PRINCIPAL_CODIGO,\n" +
                                        "        VEVENTO_PRINCIPAL_NOMBRE,\n" +
                                        "        VEVENTO_PRINCIPAL_COMENTARIO,\n" +
                                        "        NEVENTO_PRINCIPAL_NIVEL,\n" +
                                        "        NEVENTO_PRINCIPAL_NIVELES,\n" +
                                        "        CEVENTO_PRINCIPAL_FINAL,\n" +
                                        "        VEVENTO_PRINCIPAL_PRINCIPAL,\n" +
                                        "        PK_UTIL.FUN_ESTADO_DESCRIPCION(CESTADO_CODIGO) CESTADO_CODIGO\n" +
                                        "   FROM IAFAS_PROG_EVENTO_PRINCIPAL\n" +
                                        "  WHERE CPERIODO_CODIGO= ?1\n" +
                                        "    AND NFUENTE_FINANCIAMIENTO_CODIGO= ?2\n" +
                                        "    AND NTAREA_PRESUPUESTAL_CODIGO = ?3\n" +
                                        "    AND NEVENTO_PRINCIPAL_NIVEL=0\n" +
                                        "    AND CESTADO_CODIGO = 'AC'\n" +
                                        "  GROUP BY VEVENTO_PRINCIPAL_CODIGO,VEVENTO_PRINCIPAL_NOMBRE,VEVENTO_PRINCIPAL_COMENTARIO,\n" +
                                        "           NEVENTO_PRINCIPAL_NIVEL,NEVENTO_PRINCIPAL_NIVELES,CEVENTO_PRINCIPAL_FINAL,\n" +
                                        "           VEVENTO_PRINCIPAL_PRINCIPAL,CESTADO_CODIGO\n" +
                                        " ORDER BY VEVENTO_PRINCIPAL_CODIGO")
    List<BeanProgramacionEventoPrincipal> findByPeriodoFuenteTarea(String periodo, Integer fuente, Integer tarea);

    @Query(nativeQuery = true, value = "SELECT VEVENTO_PRINCIPAL_CODIGO,\n" +
                                        "       VEVENTO_PRINCIPAL_NOMBRE,\n" +
                                        "       VEVENTO_PRINCIPAL_COMENTARIO,\n" +
                                        "       NEVENTO_PRINCIPAL_NIVEL,\n" +
                                        "       NEVENTO_PRINCIPAL_NIVELES,\n" +
                                        "       CEVENTO_PRINCIPAL_FINAL,\n" +
                                        "       VEVENTO_PRINCIPAL_PRINCIPAL,\n" +
                                        "       PK_UTIL.FUN_ESTADO_DESCRIPCION(CESTADO_CODIGO) CESTADO_CODIGO\n" +
                                        "  FROM IAFAS_PROG_EVENTO_PRINCIPAL\n" +
                                        " WHERE CPERIODO_CODIGO= ?1\n" +
                                        "   AND NFUENTE_FINANCIAMIENTO_CODIGO= ?2\n" +
                                        "   AND NTAREA_PRESUPUESTAL_CODIGO= ?3\n" +
                                        "   AND VEVENTO_PRINCIPAL_PRINCIPAL= ?4\n" +
                                        "   AND NEVENTO_PRINCIPAL_NIVEL= ?5\n" +
                                        " GROUP BY VEVENTO_PRINCIPAL_CODIGO,VEVENTO_PRINCIPAL_NOMBRE,VEVENTO_PRINCIPAL_COMENTARIO,\n" +
                                        "          NEVENTO_PRINCIPAL_NIVEL,NEVENTO_PRINCIPAL_NIVELES,CEVENTO_PRINCIPAL_FINAL,\n" +
                                        "          VEVENTO_PRINCIPAL_PRINCIPAL,CESTADO_CODIGO\n" +
                                        " ORDER BY VEVENTO_PRINCIPAL_CODIGO")
    List<BeanProgramacionEventoPrincipal> findByPeriodoFuenteEventoPrincipalNivel(String periodo, Integer fuente,Integer tarea, String eventoPrincipal, Integer nivel);

    @Transactional
    @Modifying
    @Query(value = "{CALL SP_IDU_PROG_EVENTO_PRINCIPAL(:periodo, :fuente, :tarea, :eventoCodigo, :eventoNombre, :eventoComentario, :nivel, :niveles, :eventoFinal, :eventoPrincipal, :usuario, :modo)}", nativeQuery = true)
    void sp_programacionEventoPrincipal(
            @Param("periodo") String periodo,
            @Param("fuente") Integer fuente,
            @Param("tarea") Integer tarea,
            @Param("eventoCodigo") String eventoCodigo,
            @Param("eventoNombre") String eventoNombre,
            @Param("eventoComentario") String eventoComentario,
            @Param("nivel") Integer nivel,
            @Param("niveles") Integer niveles,
            @Param("eventoFinal") String eventoFinal,
            @Param("eventoPrincipal") String eventoPrincipal,
            @Param("usuario") String usuario,
            @Param("modo") String modo);
    
}
