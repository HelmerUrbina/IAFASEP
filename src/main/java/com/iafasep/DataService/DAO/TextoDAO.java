package com.iafasep.DataService.DAO;

import com.iafasep.BusinessServices.Beans.BeanComun;
import java.sql.Blob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Repository
public interface TextoDAO extends JpaRepository<BeanComun, Integer> {

    @Query(nativeQuery = true, value = "SELECT "
            + "LPAD(NVL(MAX(NREMISION_DOCUMENTO_NUMERO),0)+1,5,0) AS CODIGO  "
            + "FROM IAFAS_REMISION_DOCUMENTO WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NTIPO_DOCUMENTO_CODIGO=?2")
    String getNumeroDocumentoTipoDocumento(String periodo, Integer tipoDocumento);

    @Query(nativeQuery = true, value = "SELECT "
            + "BTIPO_DOCUMENTO_PLANTILLA  "
            + "FROM IAFAS_TIPO_DOCUMENTOS_PERIODO WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NTIPO_DOCUMENTO_CODIGO=?2")
    Blob getPlantillaTipoDocumento(String periodo, Integer tipoDocumento);

    @Query(nativeQuery = true, value = "SELECT "
            + "CASE WHEN COUNT(*)=1 THEN 'TRUE' ELSE 'FALSE' END AS RESULTADO "
            + "FROM IAFAS_USUARIOS WHERE "
            + "VUSUARIO_CODIGO=?1 AND "
            + "VUSUARIO_PIN=?2 AND "
            + "CESTADO_CODIGO='AC'")
    Boolean getVerificaPin(String usuario, String pin);

    @Query(nativeQuery = true, value = "SELECT "
            + "BUSUARIO_SELLO "
            + "FROM IAFAS_USUARIOS WHERE "
            + "VUSUARIO_CODIGO=?1 "
            + "CESTADO_CODIGO='AC'")
    Blob getUsuarioSello(String usuario);

}
