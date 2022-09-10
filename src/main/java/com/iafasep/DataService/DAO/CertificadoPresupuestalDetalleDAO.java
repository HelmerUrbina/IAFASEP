package com.iafasep.DataService.DAO;

import com.iafasep.BusinessServices.Beans.BeanCertificadoPresupuestalDetalle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author helme
 */
@Repository
public interface CertificadoPresupuestalDetalleDAO extends JpaRepository<BeanCertificadoPresupuestalDetalle, String> {

    @Query(nativeQuery = true, value = "SELECT CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, NCERTIFICADO_CODIGO, "
            + "NCERTIFICADO_CODIGO||'-'||NRESOLUCION_CODIGO||'-'||NTAREA_PRESUPUESTAL_CODIGO||'-'||NCLASIFICADOR_PRESUPUESTAL_COD AS NCERTIFICADO_DETALLE, "
            + "PK_UTIL.FUN_RESOLUCION_DESCRIPCION(CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, NRESOLUCION_CODIGO) NRESOLUCION_CODIGO, "
            + "PK_UTIL.FUN_TAREA_PRESUPUESTAL_ABREVIATURA(NTAREA_PRESUPUESTAL_CODIGO) AS NTAREA_PRESUPUESTAL_CODIGO, "
            + "PK_UTIL.FUN_CLASIFICADOR_PRESUPUESTAL(NCLASIFICADOR_PRESUPUESTAL_COD) NCLASIFICADOR_PRESUPUESTAL_COD, "
            + "NCERTIFICADO_DETALLE_IMPORTE, NCERTIFICADO_DETALLE_EXTRANJER  "
            + "FROM IAFAS_CERTIFI_PRESUPUESTAL_DET WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NFUENTE_FINANCIAMIENTO_CODIGO=?2 "
            + "ORDER BY NCERTIFICADO_CODIGO, NCERTIFICADO_DETALLE DESC")
    List<BeanCertificadoPresupuestalDetalle> findDetalleByPeriodoAndFuenteFinanciamiento(String periodo, Integer fuenteFinanciamiento);

    @Query(nativeQuery = true, value = "SELECT CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, NCERTIFICADO_CODIGO, "
            + "NRESOLUCION_CODIGO||'---'||NTAREA_PRESUPUESTAL_CODIGO||'---'||NCLASIFICADOR_PRESUPUESTAL_COD AS NCERTIFICADO_DETALLE, "
            + "PK_UTIL.FUN_RESOLUCION_DESCRIPCION(CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, NRESOLUCION_CODIGO) NRESOLUCION_CODIGO, "
            + "PK_UTIL.FUN_TAREA_PRESUPUESTAL_ABREVIATURA(NTAREA_PRESUPUESTAL_CODIGO) AS NTAREA_PRESUPUESTAL_CODIGO, "
            + "PK_UTIL.FUN_CLASIFICADOR_PRESUPUESTAL(NCLASIFICADOR_PRESUPUESTAL_COD) NCLASIFICADOR_PRESUPUESTAL_COD, "
            + "NCERTIFICADO_DETALLE_IMPORTE, NCERTIFICADO_DETALLE_EXTRANJER "
            + "FROM IAFAS_CERTIFI_PRESUPUESTAL_DET WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NFUENTE_FINANCIAMIENTO_CODIGO=?2 AND "
            + "NCERTIFICADO_CODIGO=?3 "
            + "ORDER BY NCERTIFICADO_DETALLE")
    List<BeanCertificadoPresupuestalDetalle> findDetalleByPeriodoAndFuenteFinanciamientoAndCertificado(String periodo, Integer fuenteFinanciamiento, Integer certificado);

}
