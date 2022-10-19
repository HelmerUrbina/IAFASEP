package com.iafasep.DataService.DAO;

import java.util.List;
import com.iafasep.BusinessServices.Beans.BeanCombos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Repository
public interface CombosDAO extends CrudRepository<BeanCombos, String> {

    /*CONFIGURACION*/
    @Query(nativeQuery = true, value = "SELECT "
            + "CPERIODO_CODIGO AS CODIGO, CPERIODO_CODIGO AS DESCRIPCION "
            + "FROM IAFAS_PERIODOS WHERE "
            + "CESTADO_CODIGO='AC' "
            + "ORDER BY CODIGO")
    List<BeanCombos> getPeriodos();

    @Query(nativeQuery = true, value = "SELECT "
            + "CMES_CODIGO CODIGO, VMES_DESCRIPCION DESCRIPCION "
            + "FROM IAFAS_MESES WHERE "
            + "CESTADO_CODIGO = 'AC' "
            + "ORDER BY TO_NUMBER(CODIGO)")
    List<BeanCombos> getMeses();

    @Query(nativeQuery = true, value = "SELECT "
            + "NAREA_LABORAL_CODIGO CODIGO, VAREA_LABORAL_DESCRIPCION DESCRIPCION "
            + "FROM IAFAS_AREA_LABORAL WHERE "
            + "CESTADO_CODIGO='AC' "
            + "ORDER BY CODIGO")
    List<BeanCombos> getAreaLaboral();

    @Query(nativeQuery = true, value = "SELECT "
            + "NUSUARIO_ROL_CODIGO CODIGO, VUSUARIO_ROL_NOMBRE DESCRIPCION "
            + "FROM IAFAS_USUARIOS_ROLES WHERE "
            + "CESTADO_CODIGO='AC' "
            + "ORDER BY CODIGO")
    List<BeanCombos> getRol();

    @Query(nativeQuery = true, value = "SELECT "
            + "CDEPARTAMENTO_CODIGO AS CODIGO, VDEPARTAMENTO_NOMBRE AS DESCRIPCION "
            + "FROM IAFAS_DEPARTAMENTO WHERE "
            + "CESTADO_CODIGO='AC' "
            + "ORDER BY CODIGO")
    List<BeanCombos> getDepartamentos();

    @Query(nativeQuery = true, value = "SELECT "
            + "CPROVINCIA_CODIGO AS CODIGO, VPROVINCIA_NOMBRE AS DESCRIPCION "
            + "FROM IAFAS_PROVINCIA WHERE "
            + "CDEPARTAMENTO_CODIGO=?1 AND "
            + "CESTADO_CODIGO='AC' "
            + "ORDER BY CODIGO")
    List<BeanCombos> getProvincias(String departamento);

    @Query(nativeQuery = true, value = "SELECT "
            + "CUBIGEO_CODIGO AS CODIGO, VUBIGEO_NOMBRE AS DESCRIPCION "
            + "FROM IAFAS_UBIGEO WHERE "
            + "CDEPARTAMENTO_CODIGO=?1 AND "
            + "CPROVINCIA_CODIGO=?2 AND "
            + "CESTADO_CODIGO='AC' "
            + "ORDER BY CODIGO")
    List<BeanCombos> getUbigeo(String departamento, String provincia);

    @Query(nativeQuery = true, value = "SELECT "
            + "NMONEDA_CODIGO AS CODIGO, VMONEDA_SIMBOLO AS DESCRIPCION "
            + "FROM IAFAS_MONEDAS WHERE "
            + "CESTADO_CODIGO='AC' "
            + "ORDER BY CODIGO")
    List<BeanCombos> getMonedas();

    /*MESA DE PARTES*/
    @Query(nativeQuery = true, value = "SELECT "
            + "NINSTITUCION_CODIGO AS CODIGO, VINSTITUCION_DESCRIPCION AS DESCRIPCION "
            + "FROM IAFAS_INSTITUCIONES WHERE "
            + "CESTADO_CODIGO='AC' "
            + "ORDER BY CODIGO")
    List<BeanCombos> getInstituciones();

    @Query(nativeQuery = true, value = "SELECT "
            + "NCLASIFICACION_DOCUMENTO_CODIG AS CODIGO, VCLASIFICACION_DOCUMENTO_ABREV AS DESCRIPCION "
            + "FROM IAFAS_CLASIFICACION_DOCUMENTOS WHERE "
            + "CESTADO_CODIGO='AC' "
            + "ORDER BY CODIGO")
    List<BeanCombos> getClasificacionDocumentos();

    @Query(nativeQuery = true, value = "SELECT "
            + "NTIPO_DOCUMENTO_CODIGO AS CODIGO, VTIPO_DOCUMENTO_ABREVIATURA AS DESCRIPCION "
            + "FROM IAFAS_TIPO_DOCUMENTOS WHERE "
            + "CESTADO_CODIGO='AC' "
            + "ORDER BY CODIGO")
    List<BeanCombos> getTiposDocumentos();

    @Query(nativeQuery = true, value = "SELECT "
            + "NPRIORIDAD_CODIGO AS CODIGO, VPRIORIDAD_ABREVIATURA AS DESCRIPCION "
            + "FROM IAFAS_PRIORIDADES WHERE "
            + "CESTADO_CODIGO='AC' "
            + "ORDER BY CODIGO")
    List<BeanCombos> getPrioridades();

    @Query(nativeQuery = true, value = "SELECT "
            + "NTIPO_DECRETO_CODIGO AS CODIGO, "
            + "VTIPO_DECRETO_ABREVIATURA AS DESCRIPCION "
            + "FROM IAFAS_TIPO_DECRETOS WHERE "
            + "CESTADO_CODIGO!='AN' "
            + "ORDER BY CODIGO")
    List<BeanCombos> getTipoDecretos();

    @Query(nativeQuery = true, value = "SELECT "
            + "VUSUARIO_CODIGO AS CODIGO, "
            + "VUSUARIO_PATERNO||' '||VUSUARIO_MATERNO||', '||VUSUARIO_NOMBRES AS DESCRIPCION "
            + "FROM IAFAS_USUARIOS WHERE "
            + "NAREA_LABORAL_CODIGO IN (1,2) AND "
            + "CESTADO_CODIGO!='AN' "
            + "ORDER BY NAREA_LABORAL_CODIGO")
    List<BeanCombos> getUsuarioJefatura();

    @Query(nativeQuery = true, value = "SELECT "
            + "VUSUARIO_CODIGO AS CODIGO, "
            + "VUSUARIO_PATERNO||' '||VUSUARIO_MATERNO||', '||VUSUARIO_NOMBRES AS DESCRIPCION "
            + "FROM IAFAS_USUARIOS WHERE "
            + "NAREA_LABORAL_CODIGO IN (SELECT TO_NUMBER(column_value) as IDs FROM xmltable(?1)) AND "
            + "CESTADO_CODIGO!='AN' "
            + "ORDER BY DESCRIPCION")
    List<BeanCombos> getUsuarioAreaLaboral(String codigo);

    @Query(nativeQuery = true, value = "SELECT "
            + "NINSTITUCION_CODIGO AS CODIGO, "
            + "PK_UTIL.FUN_INSTITUCION_NOMBRE(NINSTITUCION_CODIGO) AS DESCRIPCION "
            + "FROM IAFAS_REMISION_DOCUMENTO_INSTI WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NREMISION_DOCUMENTO_CODIGO=?2 "
            + "ORDER BY DESCRIPCION")
    List<BeanCombos> getRemisionDocumentosInstitucion(String periodo, Integer codigo);

    /*PRESUPUESTO*/
    @Query(nativeQuery = true, value = "SELECT "
            + "NFUENTE_FINANCIAMIENTO_CODIGO CODIGO, VFUENTE_FINANCIAMIENTO_ABREVIA AS DESCRIPCION "
            + "FROM IAFAS_FUENTE_FINANCIAMIENTO WHERE "
            + "CESTADO_CODIGO!='AN' "
            + "ORDER BY CODIGO")
    List<BeanCombos> getFuenteFinanciamiento();

    //NOTAS MODIFICATORIAS
    @Query(nativeQuery = true, value = "SELECT "
            + "NTIPO_NOTA_MODIFICATORIA_CODIG CODIGO, VTIPO_NOTA_MODIFICATORIA_DESCR AS DESCRIPCION "
            + "FROM IAFAS_TIPO_NOTAS_MODIFICATORIA WHERE "
            + "CESTADO_CODIGO!='AN' "
            + "ORDER BY CODIGO")
    List<BeanCombos> getTipoNotasModificatorias();

    @Query(nativeQuery = true, value = "SELECT "
            + "NRESOLUCION_CODIGO CODIGO, "
            + "NRESOLUCION_CODIGO||':'||VRESOLUCION_DESCRIPCION AS DESCRIPCION "
            + "FROM IAFAS_RESOLUCIONES WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NFUENTE_FINANCIAMIENTO_CODIGO=?2 AND "
            + "CESTADO_CODIGO!='AN' "
            + "ORDER BY CODIGO")
    List<BeanCombos> getResolucionesByPeriodoFuenteFinanciamiento(String periodo, Integer fuenteFinanciamiento);

    @Query(nativeQuery = true, value = "SELECT "
            + "NTAREA_PRESUPUESTAL_CODIGO CODIGO, "
            + "NTAREA_PRESUPUESTAL_CODIGO||':'||VTAREA_PRESUPUESTAL_ABREVIATUR AS DESCRIPCION "
            + "FROM IAFAS_TAREA_PRESUPUESTAL WHERE "
            + "CESTADO_CODIGO!='AN' "
            + "ORDER BY CODIGO")
    List<BeanCombos> getTareaPresupuestal();

    @Query(nativeQuery = true, value = "SELECT "
            + "NCLASIFICADOR_PRESUPUESTAL_COD CODIGO, "
            + "PK_UTIL.FUN_CLASIFICADOR_PRESUPUESTAL(NCLASIFICADOR_PRESUPUESTAL_COD) AS DESCRIPCION "
            + "FROM IAFAS_TAREA_CLASIFICADOR_PRESP WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NTAREA_PRESUPUESTAL_CODIGO=?2 AND "
            + "CESTADO_CODIGO!='AN' "
            + "ORDER BY CODIGO")
    List<BeanCombos> getClasificadorPresupuestalByPeriodoTareaPresupuestal(String periodo, Integer tareaPresupuestal);

    @Query(nativeQuery = true, value = "SELECT "
            + "NFUENTE_FINANCIAMIENTO_CODIGO CODIGO, "
            + "PK_UTIL.FUN_FUENTE_FINANCIAMIENTO_ABRE(NFUENTE_FINANCIAMIENTO_CODIGO)||"
            + "' S/ '||TO_CHAR(SUM(PIM-(EJECUTADO-NOTA)),'FM999,999,999,999.009') AS DESCRIPCION "
            + "FROM V_NOTAS_MODIFICATORIAS WHERE "
            + "CPERIODO_CODIGO=?1 "
            + "GROUP BY NFUENTE_FINANCIAMIENTO_CODIGO "
            + "ORDER BY CODIGO")
    List<BeanCombos> getFuenteFinanciamientoNotaModificatoria(String periodo);

    @Query(nativeQuery = true, value = "SELECT "
            + "NRESOLUCION_CODIGO CODIGO, "
            + "PK_UTIL.FUN_RESOLUCION_DESCRIPCION(CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, NRESOLUCION_CODIGO)||"
            + "' S/ '||TO_CHAR(SUM(PIM-(EJECUTADO-NOTA)),'FM999,999,999,999.009') AS DESCRIPCION "
            + "FROM V_NOTAS_MODIFICATORIAS WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NFUENTE_FINANCIAMIENTO_CODIGO=?2 "
            + "GROUP BY CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, NRESOLUCION_CODIGO "
            + "ORDER BY CODIGO")
    List<BeanCombos> getResolucionesNotaModificatoria(String periodo, Integer fuenteFinanciamiento);

    @Query(nativeQuery = true, value = "SELECT "
            + "NTAREA_PRESUPUESTAL_CODIGO CODIGO, "
            + "PK_UTIL.FUN_TAREA_PRESUPUESTAL_ABREVIATURA(NTAREA_PRESUPUESTAL_CODIGO)||"
            + "' S/ '||TO_CHAR(SUM(PIM-(EJECUTADO-NOTA)),'FM999,999,999,999.009') AS DESCRIPCION "
            + "FROM V_NOTAS_MODIFICATORIAS WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NFUENTE_FINANCIAMIENTO_CODIGO=?2 AND "
            + "NRESOLUCION_CODIGO=?3 "
            + "GROUP BY NTAREA_PRESUPUESTAL_CODIGO "
            + "ORDER BY CODIGO")
    List<BeanCombos> getTareaPresupuestalNotaModificatoria(String periodo, Integer fuenteFinanciamiento, Integer resolucion);

    @Query(nativeQuery = true, value = "SELECT "
            + "NCLASIFICADOR_PRESUPUESTAL_CODIGO CODIGO, "
            + "PK_UTIL.FUN_CLASIFICADOR_PRESUPUESTAL(NCLASIFICADOR_PRESUPUESTAL_CODIGO)||"
            + "' S/ '||TO_CHAR(SUM(PIM-(EJECUTADO-NOTA)),'FM999,999,999,999.009') AS DESCRIPCION "
            + "FROM V_NOTAS_MODIFICATORIAS WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NFUENTE_FINANCIAMIENTO_CODIGO=?2 AND "
            + "NRESOLUCION_CODIGO=?3 AND "
            + "NTAREA_PRESUPUESTAL_CODIGO=?4 "
            + "GROUP BY NCLASIFICADOR_PRESUPUESTAL_CODIGO "
            + "ORDER BY CODIGO")
    List<BeanCombos> getClasificadorPresupuestalNotaModificatoria(String periodo, Integer fuenteFinanciamiento, Integer resolucion, Integer tareaPresupuestal);

    //CERTIFICACIÓN
    @Query(nativeQuery = true, value = "SELECT "
            + "NRESOLUCION_CODIGO AS CODIGO, "
            + "PK_UTIL.FUN_RESOLUCION_DESCRIPCION(CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, NRESOLUCION_CODIGO)||"
            + "' S/ '||TO_CHAR(SUM(PIM-CERTIFICADO),'FM999,999,999,999.009') AS DESCRIPCION "
            + "FROM V_CERTIFICADO_PRESUPUESTAL WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NFUENTE_FINANCIAMIENTO_CODIGO=?2 "
            + "GROUP BY CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, NRESOLUCION_CODIGO "
            + "HAVING SUM(PIM-CERTIFICADO)>0 "
            + "ORDER BY CODIGO")
    List<BeanCombos> getResolucionCertificadoPresupuestal(String periodo, Integer fuenteFinanciamiento);

    @Query(nativeQuery = true, value = "SELECT "
            + "NRESOLUCION_CODIGO AS CODIGO, "
            + "PK_UTIL.FUN_RESOLUCION_DESCRIPCION(CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, NRESOLUCION_CODIGO)||"
            + "' S/ '||TO_CHAR(SUM(IMPORTE),'FM999,999,999,999.009')  AS DESCRIPCION "
            + "FROM V_CERTIFICADO_REBAJAS WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NFUENTE_FINANCIAMIENTO_CODIGO=?2 AND "
            + "NCERTIFICADO_CODIGO=?3 "
            + "GROUP BY CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, NRESOLUCION_CODIGO "
            + "ORDER BY CODIGO")
    List<BeanCombos> getResolucionCertificadoPresupuestalRebaja(String periodo, Integer fuenteFinanciamiento, Integer certificado);

    @Query(nativeQuery = true, value = "SELECT "
            + "NTAREA_PRESUPUESTAL_CODIGO AS CODIGO, "
            + "PK_UTIL.FUN_TAREA_PRESUPUESTAL_ABREVIATURA(NTAREA_PRESUPUESTAL_CODIGO)||"
            + "' S/ '||TO_CHAR(SUM(PIM-CERTIFICADO),'FM999,999,999,999.009') AS DESCRIPCION "
            + "FROM V_CERTIFICADO_PRESUPUESTAL WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NFUENTE_FINANCIAMIENTO_CODIGO=?2 AND "
            + "NRESOLUCION_CODIGO=?3 "
            + "GROUP BY NTAREA_PRESUPUESTAL_CODIGO "
            + "HAVING SUM(PIM-CERTIFICADO)>0 "
            + "ORDER BY CODIGO")
    List<BeanCombos> getTareaPresupuestalCertificadoPresupuestal(String periodo, Integer fuenteFinanciamiento, Integer resolucion);

    @Query(nativeQuery = true, value = "SELECT "
            + "NTAREA_PRESUPUESTAL_CODIGO AS CODIGO, "
            + "PK_UTIL.FUN_TAREA_PRESUPUESTAL_ABREVIATURA(NTAREA_PRESUPUESTAL_CODIGO)||"
            + "' S/ '||TO_CHAR(SUM(IMPORTE),'FM999,999,999,999.009')  AS DESCRIPCION "
            + "FROM V_CERTIFICADO_REBAJAS WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NFUENTE_FINANCIAMIENTO_CODIGO=?2 AND "
            + "NCERTIFICADO_CODIGO=?3 AND "
            + "NRESOLUCION_CODIGO=?4 "
            + "GROUP BY NTAREA_PRESUPUESTAL_CODIGO "
            + "ORDER BY CODIGO")
    List<BeanCombos> geTareaPresupuestalCertificadoPresupuestalRebaja(String periodo, Integer fuenteFinanciamiento, Integer certificado, Integer resolucion);

    @Query(nativeQuery = true, value = "SELECT "
            + "NCLASIFICADOR_PRESUPUESTAL_CODIGO AS CODIGO, "
            + "PK_UTIL.FUN_CLASIFICADOR_PRESUPUESTAL(NCLASIFICADOR_PRESUPUESTAL_CODIGO)||"
            + "' S/ '||TO_CHAR(SUM(PIM-CERTIFICADO),'FM999,999,999,999.009') AS DESCRIPCION "
            + "FROM V_CERTIFICADO_PRESUPUESTAL WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NFUENTE_FINANCIAMIENTO_CODIGO=?2 AND "
            + "NRESOLUCION_CODIGO=?3 AND "
            + "NTAREA_PRESUPUESTAL_CODIGO=?4 "
            + "GROUP BY NCLASIFICADOR_PRESUPUESTAL_CODIGO "
            + "HAVING SUM(PIM-CERTIFICADO)>0 "
            + "ORDER BY CODIGO")
    List<BeanCombos> getClasificadorPresupuestalCertificadoPresupuestal(String periodo, Integer fuenteFinanciamiento, Integer resolucion, Integer tareaPresupuestal);

    @Query(nativeQuery = true, value = "SELECT "
            + "NCLASIFICADOR_PRESUPUESTAL_CODIGO AS CODIGO, "
            + "PK_UTIL.FUN_CLASIFICADOR_PRESUPUESTAL(NCLASIFICADOR_PRESUPUESTAL_CODIGO)||"
            + "' S/ '||TO_CHAR(SUM(IMPORTE),'FM999,999,999,999.009')  AS DESCRIPCION "
            + "FROM V_CERTIFICADO_REBAJAS WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NFUENTE_FINANCIAMIENTO_CODIGO=?2 AND "
            + "NCERTIFICADO_CODIGO=?3 AND "
            + "NRESOLUCION_CODIGO=?4 AND "
            + "NTAREA_PRESUPUESTAL_CODIGO=?5 "
            + "GROUP BY NCLASIFICADOR_PRESUPUESTAL_CODIGO "
            + "ORDER BY CODIGO")
    List<BeanCombos> geClasificadorPresupuestalCertificadoPresupuestalRebaja(String periodo, Integer fuenteFinanciamiento, Integer certificado, Integer resolucion, Integer tareaPresupuestal);

    @Query(nativeQuery = true, value = "SELECT NFUENTE_FINANCIAMIENTO_CODIGO CODIGO,\n"
            + "         PK_UTIL.FUN_FUENTE_FINANCIAMIENTO_ABRE(NFUENTE_FINANCIAMIENTO_CODIGO) DESCRIPCION\n"
            + "    FROM IAFAS_PROG_MULTIANUAL \n"
            + "   WHERE CESTADO_CODIGO = 'AC'\n"
            + "   GROUP BY NFUENTE_FINANCIAMIENTO_CODIGO\n"
            + "   ORDER BY NFUENTE_FINANCIAMIENTO_CODIGO")
    List<BeanCombos> getFuenteByProgramacion();

    @Query(nativeQuery = true, value = "SELECT P.NTAREA_PRESUPUESTAL_CODIGO CODIGO,\n"
            + "         T.VTAREA_PRESUPUESTAL_NOMBRE DESCRIPCION\n"
            + "    FROM IAFAS_PROG_MULTIANUAL P\n"
            + "         INNER JOIN IAFAS_TAREA_PRESUPUESTAL T ON T.NTAREA_PRESUPUESTAL_CODIGO = P.NTAREA_PRESUPUESTAL_CODIGO\n"
            + "   WHERE P.CESTADO_CODIGO = 'AC'\n"
            + "     AND P.CPERIODO_CODIGO = ?1\n"
            + "     AND P.NFUENTE_FINANCIAMIENTO_CODIGO = ?2\n"
            + "   GROUP BY P.NTAREA_PRESUPUESTAL_CODIGO ,VTAREA_PRESUPUESTAL_NOMBRE\n"
            + "   ORDER BY P.NTAREA_PRESUPUESTAL_CODIGO")
    List<BeanCombos> getTareaByProgramacion(String periodo, Integer fuente);

    @Query(nativeQuery = true, value = "SELECT CANIO_REGISTRO CODIGO,\n"
            + "         CANIO_REGISTRO DESCRIPCION\n"
            + "    FROM IAFAS_PROG_MULTIANUAL \n"
            + "   WHERE CESTADO_CODIGO = 'AC'\n"
            + "     AND CPERIODO_CODIGO = ?1\n"
            + "   GROUP BY CANIO_REGISTRO \n"
            + "   ORDER BY CANIO_REGISTRO")
    List<BeanCombos> getAnioByProgramacion(String periodo);

    @Query(nativeQuery = true, value = "SELECT NCLASIFICADOR_PRESUPUESTAL_COD||','||PK_UTIL.FUN_SALDO_PROG_CLASIFICADOR(CPERIODO_CODIGO,NFUENTE_FINANCIAMIENTO_CODIGO,NTAREA_PRESUPUESTAL_CODIGO,NCLASIFICADOR_PRESUPUESTAL_COD) CODIGO,\n"
            + "       PK_UTIL.FUN_CLASIFICADOR_PRESUPUESTAL(NCLASIFICADOR_PRESUPUESTAL_COD) DESCRIPCION\n"
            + "  FROM IAFAS_PROG_MULTIANUAL_DETALLE\n"
            + " WHERE CPERIODO_CODIGO = ?1\n"
            + "   AND NFUENTE_FINANCIAMIENTO_CODIGO = ?2\n"
            + "   AND NTAREA_PRESUPUESTAL_CODIGO = ?3\n"
            + " GROUP BY NCLASIFICADOR_PRESUPUESTAL_COD,CPERIODO_CODIGO,NFUENTE_FINANCIAMIENTO_CODIGO,NTAREA_PRESUPUESTAL_CODIGO,NCLASIFICADOR_PRESUPUESTAL_COD\n"
            + " ORDER BY NCLASIFICADOR_PRESUPUESTAL_COD")
    List<BeanCombos> getClasificadorProgByPeriodoFuenteTarea(String periodo, Integer fuente, Integer tarea);

    @Query(nativeQuery = true, value = "SELECT CUNIDAD_MEDIDA_CODIGO CODIGO,\n"
            + "       VUNIDAD_MEDIDA_NOMBRE DESCRIPCION\n"
            + "  FROM IAFAS_UNIDAD_MEDIDA\n"
            + "  WHERE CESTADO_CODIGO = 'AC'\n"
            + "  ORDER BY VUNIDAD_MEDIDA_NOMBRE")
    List<BeanCombos> getunidadMedida();

    @Query(nativeQuery = true, value = "SELECT NITEM_CODIGO||','||PK_UTIL.FUN_UNIDAD_MEDIDA_ABRE(CUNIDAD_MEDIDA_CODIGO) CODIGO,\n"
            + "      VITEM_DESCRIPCION DESCRIPCION\n"
            + "  FROM IAFAS_ITEM \n"
            + " WHERE CESTADO_CODIGO = 'AC'\n"
            + " ORDER BY VITEM_DESCRIPCION")
    List<BeanCombos> getItemByUnidadMedida();

}
