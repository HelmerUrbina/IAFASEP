package com.iafasep.DataService.DAO;

import com.iafasep.BusinessServices.Beans.BeanFirmaDigital;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author helme
 */
public interface FirmaDigitalDAO extends CrudRepository<BeanFirmaDigital, String> {

    @Query(nativeQuery = true, value = "SELECT NCERTIFICADO_CODIGO AS CODIGO, "
            + "UPPER(VCERTIFICADO_CONCEPTO) AS DESCRIPCION, UPPER(VCERTIFICADO_DOCUMENTO) AS DOCUMENTO, "
            + "TO_CHAR(DCERTIFICADO_FECHA,'YYYY/MM/DD') AS FECHA, 0 IMPORTE, "
            + "VCERTIFICADO_ARCHIVO AS ARCHIVO, VARCHIVO_FIRMADO AS DIGITAL, "
            + "PK_UTIL.FUN_USUARIO_NOMBRE(VUSUARIO_CREADOR) AS RESPONSABLE, "
            + "'N' YAFIRME, CESTADO_FIRMA ESTADO "
            + "FROM IAFAS_CERTIFICADO_PRESUPUESTAL WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NFUENTE_FINANCIAMIENTO_CODIGO=?2 AND "
            + "CESTADO_FIRMA=?3 AND "
            + "CESTADO_CODIGO='CE' "
            + "ORDER BY CODIGO")
    List<BeanFirmaDigital> getCertificadosPresupuestales(String periodo, String fuenteFinanciamiento, String estado);
}
