package com.iafasep.DataService.DAO;

import com.iafasep.BusinessServices.Beans.BeanMesaPartesDecreto;
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
public interface MesaPartesDecretoDAO extends JpaRepository<BeanMesaPartesDecreto, String> {

    @Query(nativeQuery = true, value = "SELECT IAFAS_MESA_PARTES_DECRETOS.CMESA_PARTE_TIPO AS CMESA_PARTE_TIPO, "
            + "IAFAS_MESA_PARTES_DECRETOS.CPERIODO_CODIGO AS CPERIODO_CODIGO, "
            + "IAFAS_MESA_PARTES_DECRETOS.NMESA_PARTE_CODIGO AS NMESA_PARTE_CODIGO, "
            + "IAFAS_MESA_PARTES_DECRETOS.NMESA_PARTE_DECRETO_CODIGO||'.'||IAFAS_MESA_PARTES_DECRETOS_USU.VUSUARIO_RESPONSABLE AS NMESA_PARTE_DECRETO_CODIGO, "
            + "IAFAS_MESA_PARTES_DECRETOS_USU.NAREA_LABORAL_CODIGO AS NAREA_LABORAL_CODIGO, "
            + "PK_UTIL.FUN_USUARIO_NOMBRE(IAFAS_MESA_PARTES_DECRETOS.VUSUARIO_DECRETA) AS VUSUARIO_DECRETA, "
            + "PK_UTIL.FUN_USUARIO_NOMBRE(IAFAS_MESA_PARTES_DECRETOS_USU.VUSUARIO_RESPONSABLE) AS VUSUARIO_RESPONSABLE, "
            + "PK_UTIL.FUN_PRIORIDAD_NOMBRE(IAFAS_MESA_PARTES_DECRETOS.NPRIORIDAD_CODIGO) AS NPRIORIDAD_CODIGO, "
            + "UPPER(IAFAS_MESA_PARTES_DECRETOS.VMESA_PARTE_DECRETO_DESCRIPCIO) AS VMESA_PARTE_DECRETO_DESCRIPCIO, "
            + "IAFAS_MESA_PARTES_DECRETOS.DMESA_PARTE_DECRETO_FECHA AS DMESA_PARTE_DECRETO_FECHA, "
            + "IAFAS_MESA_PARTES_DECRETOS_USU.DMESA_PARTE_DECRETO_RECEPCION AS DMESA_PARTE_DECRETO_RECEPCION, "
            + "PK_UTIL.FUN_ESTADO_DESCRIPCION(IAFAS_MESA_PARTES_DECRETOS_USU.CESTADO_CODIGO) AS CESTADO_CODIGO "
            + "FROM IAFAS_MESA_PARTES_DECRETOS INNER JOIN IAFAS_MESA_PARTES_DECRETOS_USU ON ("
            + "IAFAS_MESA_PARTES_DECRETOS.CMESA_PARTE_TIPO=IAFAS_MESA_PARTES_DECRETOS_USU.CMESA_PARTE_TIPO AND "
            + "IAFAS_MESA_PARTES_DECRETOS.CPERIODO_CODIGO=IAFAS_MESA_PARTES_DECRETOS_USU.CPERIODO_CODIGO AND "
            + "IAFAS_MESA_PARTES_DECRETOS.NMESA_PARTE_CODIGO=IAFAS_MESA_PARTES_DECRETOS_USU.NMESA_PARTE_CODIGO AND "
            + "IAFAS_MESA_PARTES_DECRETOS.NMESA_PARTE_DECRETO_CODIGO=IAFAS_MESA_PARTES_DECRETOS_USU.NMESA_PARTE_DECRETO_CODIGO) WHERE "
            + "IAFAS_MESA_PARTES_DECRETOS.CMESA_PARTE_TIPO=? AND "
            + "IAFAS_MESA_PARTES_DECRETOS.CPERIODO_CODIGO=? AND "
            + "IAFAS_MESA_PARTES_DECRETOS.NMESA_PARTE_CODIGO=? AND "
            + "IAFAS_MESA_PARTES_DECRETOS.CESTADO_CODIGO!='AN' "
            + "ORDER BY NMESA_PARTE_DECRETO_CODIGO")
    public List<BeanMesaPartesDecreto> findByTipoPeriodoNumero(String tipo, String periodo, String numero);

    @Transactional
    @Modifying
    @Query(value = "{CALL SP_IDU_MESA_PARTES_DECRETO(:periodo, :tipo, :numero, :decreto, :usuarioDecreta, :descripcion, "
            + ":prioridad, :usuarioResponsable, :tipoDecretos, :usuario, :modo)}", nativeQuery = true)
    void sp_iduMesaPartesDecreto(
            @Param("periodo") String periodo,
            @Param("tipo") String tipo,
            @Param("numero") String numero,
            @Param("decreto") String decreto,
            @Param("usuarioDecreta") String usuarioDecreta,
            @Param("descripcion") String descripcion,
            @Param("prioridad") String prioridad,
            @Param("usuarioResponsable") String usuarioResponsable,
            @Param("tipoDecretos") String tipoDecretos,
            @Param("usuario") String usuario,
            @Param("modo") String modo);

}
