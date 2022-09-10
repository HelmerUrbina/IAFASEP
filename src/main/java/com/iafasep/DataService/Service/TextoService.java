package com.iafasep.DataService.Service;

import com.iafasep.BusinessServices.Beans.BeanCombos;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
public interface TextoService {

    public BeanCombos getPrecioTechosByPeriodoAndBrigadaAndTipoAsignacionAndTipoCombustible(String periodo, Integer brigada, Integer tipoAsignacion, Integer tipoCombustible);
    
    public BeanCombos getSaldoTechosByPeriodoAndBrigadaAndTipoAsignacionAndTipoCombustible(String periodo, Integer brigada, Integer tipoAsignacion, Integer tipoCombustible);

}
