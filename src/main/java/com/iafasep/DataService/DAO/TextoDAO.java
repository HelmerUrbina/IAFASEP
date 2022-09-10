package com.iafasep.DataService.DAO;

import com.iafasep.BusinessServices.Beans.BeanCombos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Repository
public interface TextoDAO extends CrudRepository<BeanCombos, String> {

    @Query(nativeQuery = true, value = "SELECT "
            + "'0'  AS CODIGO, "
            + "TO_CHAR(NPROGRAMACION_TECHOS_PRECIO,'FM999,999,999,999.009') AS DESCRIPCION "
            + "FROM IAFAS_PROG_CLASE_III_TECHOS WHERE "
            + "CPERIODO_CODIGO=?1 AND "
            + "NBRIGADA_CODIGO=?2 AND "
            + "NTIPO_ASIGNACION_CODIGO=?3 AND "
            + "NTIPO_COMBUSTIBLE_CODIGO=?4 ")
    BeanCombos getPrecioTechosByPeriodoAndBrigadaAndTipoAsignacionAndTipoCombustible(String periodo, Integer brigada, Integer tipoAsignacion, Integer tipoCombustible);
    
    
    @Query(nativeQuery = true, value = "SELECT "
            + "'0' AS CODIGO, "
            + "TO_CHAR(PK_SINTE.FUN_SALDO_TECHO_CLASE_III(?1, ?2, ?3, ?4),'FM999,999,999,999.009') AS DESCRIPCION "
            + "FROM DUAL")
    BeanCombos getSaldoTechosByPeriodoAndBrigadaAndTipoAsignacionAndTipoCombustible(String periodo, Integer brigada, Integer tipoAsignacion, Integer tipoCombustible);

}
