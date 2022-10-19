package com.iafasep.DataService.DAO;

import com.iafasep.BusinessServices.Beans.BeanRemisionDocumento;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
public interface RemisionDocumentoDAO extends JpaRepository<BeanRemisionDocumento, String> {

    @Query(nativeQuery = true, value = "SELECT NREMISION_DOCUMENTO_CODIGO, "
            + "NTIPO_DOCUMENTO_CODIGO, NCLASIFICACION_DOCUMENTO_CODIG, "
            + "PK_UTIL.FUN_TIPO_DOCUMENTO_NOMBRE(NTIPO_DOCUMENTO_CODIGO)||'-'||LPAD(NREMISION_DOCUMENTO_NUMERO,5,0) AS NREMISION_DOCUMENTO_NUMERO, "
            + "UPPER(VREMISION_DOCUMENTO_ASUNTO) AS VREMISION_DOCUMENTO_ASUNTO, "
            + "PK_UTIL.FUN_DOCUMENTO_INSTITUCION(CPERIODO_CODIGO, NREMISION_DOCUMENTO_CODIGO) AS CPERIODO_CODIGO, "
            + "TO_CHAR(DREMISION_DOCUMENTO_FECHA,'YYYY/MM/DD') AS DREMISION_DOCUMENTO_FECHA, "
            + "UPPER(VREMISION_DOCUMENTO_DIRIGIDO) AS VREMISION_DOCUMENTO_DIRIGIDO, UPPER(VREMISION_DOCUMENTO_CARGO) AS VREMISION_DOCUMENTO_CARGO, "
            + "CASE WHEN VREMISION_DOCUMENTO_DIGITAL IS NULL THEN VREMISION_DOCUMENTO_WORD ELSE VREMISION_DOCUMENTO_DIGITAL END AS VREMISION_DOCUMENTO_DIGITAL, "
            + "VREMISION_DOCUMENTO_WORD, PK_UTIL.FUN_ESTADO_DESCRIPCION(CESTADO_CODIGO) AS CESTADO_CODIGO "
            + "FROM IAFAS_REMISION_DOCUMENTO WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "CMES_CODIGO=?2 AND "
            + "VUSUARIO_CREADOR=?3 AND "
            + "CESTADO_CODIGO NOT IN ('AN') "
            + "ORDER BY NREMISION_DOCUMENTO_CODIGO DESC")
    public List<BeanRemisionDocumento> findByPeriodoAndMesAndUsuario(String periodo, String mes, String usuario);

    @Query(nativeQuery = true, value = "SELECT NREMISION_DOCUMENTO_CODIGO, "
            + "NTIPO_DOCUMENTO_CODIGO, NCLASIFICACION_DOCUMENTO_CODIG, "
            + "PK_UTIL.FUN_TIPO_DOCUMENTO_NOMBRE(NTIPO_DOCUMENTO_CODIGO)||'-'||LPAD(NREMISION_DOCUMENTO_NUMERO,5,0) AS NREMISION_DOCUMENTO_NUMERO, "
            + "UPPER(VREMISION_DOCUMENTO_ASUNTO) AS VREMISION_DOCUMENTO_ASUNTO, "
            + "'' AS CPERIODO_CODIGO, "
            + "TO_CHAR(DREMISION_DOCUMENTO_FECHA,'YYYY/MM/DD') AS DREMISION_DOCUMENTO_FECHA, "
            + "VREMISION_DOCUMENTO_WORD, PK_UTIL.FUN_ESTADO_DESCRIPCION(CESTADO_CODIGO) AS CESTADO_CODIGO, "
            + "UPPER(VREMISION_DOCUMENTO_DIRIGIDO) AS VREMISION_DOCUMENTO_DIRIGIDO, UPPER(VREMISION_DOCUMENTO_CARGO) AS VREMISION_DOCUMENTO_CARGO, "
            + "CASE WHEN VREMISION_DOCUMENTO_DIGITAL IS NULL THEN VREMISION_DOCUMENTO_WORD ELSE VREMISION_DOCUMENTO_DIGITAL END AS VREMISION_DOCUMENTO_DIGITAL "
            + "FROM IAFAS_REMISION_DOCUMENTO WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "VUSUARIO_CREADOR=?2 AND "
            + "CESTADO_CODIGO IN ('EL','FI') "
            + "ORDER BY NREMISION_DOCUMENTO_CODIGO DESC")
    public List<BeanRemisionDocumento> findByPeriodoAndUsuarioAndEstado(String periodo, String usuario, String estado);

    public BeanRemisionDocumento findByPeriodoAndCodigo(String periodo, Integer codigo);

    @Query(nativeQuery = true, value = "SELECT "
            + "NVL(MAX(NREMISION_DOCUMENTO_CODIGO)+1,1) AS NREMISION_DOCUMENTO_CODIGO "
            + "FROM IAFAS_REMISION_DOCUMENTO WHERE "
            + "CPERIODO_CODIGO=?1")
    public Integer getCodigoByPeriodo(String periodo);

    @Query(nativeQuery = true, value = "SELECT "
            + "CASE WHEN VREMISION_DOCUMENTO_DIGITAL IS NULL THEN VREMISION_DOCUMENTO_WORD ELSE VREMISION_DOCUMENTO_DIGITAL END AS ARCHIVO "
            + "FROM IAFAS_REMISION_DOCUMENTO WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NREMISION_DOCUMENTO_CODIGO=?2")
    public String getRemisionDocumentoArchivo(String periodo, Integer remisionDocumento);

    @Transactional
    @Modifying
    @Query(value = "{CALL SP_IDU_REMISION_DOCUMENTO(:periodo, :codigo, :institucion, :tipoDocumento, :numeroDocumento, "
            + ":clasificacion, :fecha, :asunto, :dirigido, :cargo, :archivo, :usuario, :modo)}", nativeQuery = true)
    Integer sp_iduRemisionDocumento(
            @Param("periodo") String periodo,
            @Param("codigo") Integer codigo,
            @Param("institucion") String institucion,
            @Param("tipoDocumento") String tipoDocumento,
            @Param("numeroDocumento") String numeroDocumento,
            @Param("clasificacion") String clasificacion,
            @Param("fecha") Date fecha,
            @Param("asunto") String asunto,
            @Param("dirigido") String dirigido,
            @Param("cargo") String cargo,
            @Param("archivo") String archivo,
            @Param("usuario") String usuario,
            @Param("modo") String modo);

    @Transactional
    @Modifying
    @Query(value = "UPDATE IAFAS_REMISION_DOCUMENTO SET "
            + "VREMISION_DOCUMENTO_DIGITAL=:archivo WHERE "
            + "CPERIODO_CODIGO=:periodo AND "
            + "NREMISION_DOCUMENTO_CODIGO=:codigo", nativeQuery = true)
    public void sp_updRemisionDocumentoDigital(
            @Param("periodo") String periodo,
            @Param("codigo") Integer codigo,
            @Param("archivo") String archivo);

    @Query(nativeQuery = true, value = "SELECT "
            + "NVL(MAX(NREMISION_DOCUMENTO_ELEVACION)+1,1) AS NREMISION_DOCUMENTO_ELEVACION "
            + "FROM IAFAS_REMISION_DOCUMENTO_ELEVA WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NREMISION_DOCUMENTO_CODIGO=?2")
    public Integer getCodigoEvelarByPeriodoAndRemisionDocumento(String periodo, Integer remisionDocumento);

    @Transactional
    @Modifying
    @Query(value = "{CALL SP_IDU_REMISION_DOCUMENTO_ELEVAR(:periodo, :codigo, :elevacion, :areaLaboral, :responsable, :usuario, :modo)}", nativeQuery = true)
    Integer sp_iduElevarDocumento(
            @Param("periodo") String periodo,
            @Param("codigo") Integer codigo,
            @Param("elevacion") Integer elevacion,
            @Param("areaLaboral") String areaLaboral,
            @Param("responsable") String responsable,
            @Param("usuario") String usuario,
            @Param("modo") String modo);

    @Transactional
    @Modifying
    @Query(value = "{CALL SP_IDU_REMISION_DOCUMENTO_ADJUNTAR(:periodo, :codigo, :elevacion, :archivo, :usuario, :modo)}", nativeQuery = true)
    void sp_iduAnexoDocumento(
            @Param("periodo") String periodo,
            @Param("codigo") Integer codigo,
            @Param("elevacion") Integer elevacion,
            @Param("archivo") String archivo,
            @Param("usuario") String usuario,
            @Param("modo") String modo);

}
