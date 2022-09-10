package com.iafasep.DataService.DAO;

import com.iafasep.BusinessServices.Beans.BeanCertificadoPresupuestal;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author helme
 */
@Repository
public interface CertificadoPresupuestalDAO extends JpaRepository<BeanCertificadoPresupuestal, String> {

    @Query(nativeQuery = true, value = "SELECT CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, NCERTIFICADO_CODIGO, "
            + "NCERTIFICADO_ANEXO, UPPER(VCERTIFICADO_DOCUMENTO) AS VCERTIFICADO_DOCUMENTO, "
            + "UPPER(VCERTIFICADO_CONCEPTO) AS VCERTIFICADO_CONCEPTO, UPPER(VCERTIFICADO_OBSERVACION) AS VCERTIFICADO_OBSERVACION, "
            + "TO_CHAR(DCERTIFICADO_FECHA,'YYYY/MM/DD') AS DCERTIFICADO_FECHA, NMONEDA_CODIGO, 0 NCERTIFICADO_IMPORTE, "
            //     + "CASE CCERTIFICADO_TIPO_REGISTRO WHEN 'RE' THEN (-1)*NCERTIFICADO_IMPORTE ELSE NCERTIFICADO_IMPORTE END AS IMPORTE, "
            + "NCERTIFICADO_TIPO_CAMBIO, 0 NCERTIFICADO_EXTRANJERA, "
            // + "CASE CCERTIFICADO_TIPO_REGISTRO WHEN 'RE' THEN (-1)*NCERTIFICADO_EXTRANJERA ELSE NCERTIFICADO_EXTRANJERA END AS EXTRANJERA, "
            + "PK_UTIL.FUN_ESTADO_DESCRIPCION(CESTADO_CODIGO) AS CESTADO_CODIGO, NPAC_PROCESOS_CODIGO, "
            + "CASE CCERTIFICADO_TIPO_REGISTRO WHEN 'CE' THEN 'CERTIFICADO' WHEN 'AM' THEN 'AMPLIACION' WHEN 'RE' THEN 'REBAJA' ELSE '' END AS CCERTIFICADO_TIPO_REGISTRO, "
            + "VCERTIFICADO_ARCHIVO AS VCERTIFICADO_ARCHIVO "
            + "FROM IAFAS_CERTIFICADO_PRESUPUESTAL WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NFUENTE_FINANCIAMIENTO_CODIGO=?2 "
            + "ORDER BY NCERTIFICADO_CODIGO DESC")
    List<BeanCertificadoPresupuestal> findByPeriodoAndFuenteFinanciamiento(String periodo, Integer fuenteFinanciamiento);

    @Query(nativeQuery = true, value = "SELECT "
            + "NVL(MAX(NCERTIFICADO_CODIGO)+1,1) AS NCERTIFICADO_CODIGO  "
            + "FROM IAFAS_CERTIFICADO_PRESUPUESTAL WHERE "
            + "CPERIODO_CODIGO=?1")
    public Integer getNroCertificadoByPeriodo(String periodo);

    @Query(nativeQuery = true, value = "SELECT CPERIODO_CODIGO, NFUENTE_FINANCIAMIENTO_CODIGO, NCERTIFICADO_CODIGO, "
            + "NCERTIFICADO_ANEXO, UPPER(VCERTIFICADO_DOCUMENTO) AS VCERTIFICADO_DOCUMENTO, NPAC_PROCESOS_CODIGO, "
            + "UPPER(VCERTIFICADO_CONCEPTO) AS VCERTIFICADO_CONCEPTO, UPPER(VCERTIFICADO_OBSERVACION) AS VCERTIFICADO_OBSERVACION, "
            + "DCERTIFICADO_FECHA, NMONEDA_CODIGO, 0 NCERTIFICADO_IMPORTE, "
            + "NCERTIFICADO_TIPO_CAMBIO, 0 NCERTIFICADO_EXTRANJERA, CESTADO_CODIGO, "
            + "CCERTIFICADO_TIPO_REGISTRO, VCERTIFICADO_ARCHIVO "
            + "FROM IAFAS_CERTIFICADO_PRESUPUESTAL WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NCERTIFICADO_CODIGO=?2")
    public BeanCertificadoPresupuestal findByPeriodoAndCertificado(String periodo, Integer certificado);

    @Transactional
    @Modifying
    @Query(value = "{CALL SP_IDU_CERTIFICADO_PRESUPUESTAL(:periodo, :fuenteFinanciamiento, :certificado, :tipoCertificado, "
            + ":anexo, :documento, :concepto, :observacion, :fecha, :moneda, :tipoCambio, :array, :usuario, :modo)}", nativeQuery = true)
    void sp_certificadoPresupuestal(
            @Param("periodo") String periodo,
            @Param("fuenteFinanciamiento") Integer fuenteFinanciamiento,
            @Param("certificado") Integer certificado,
            @Param("tipoCertificado") String tipoCertificado,
            @Param("anexo") Integer anexo,
            @Param("documento") String documento,
            @Param("concepto") String concepto,
            @Param("observacion") String observacion,
            @Param("fecha") Date fecha,
            @Param("moneda") Integer moneda,
            @Param("tipoCambio") Double tipoCambio,
            @Param("array") String array,
            @Param("usuario") String usuario,
            @Param("modo") String modo);

}
