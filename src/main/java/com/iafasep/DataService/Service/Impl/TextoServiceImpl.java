package com.iafasep.DataService.Service.Impl;

import com.iafasep.BusinessServices.Beans.BeanCombos;
import com.iafasep.DataService.DAO.TextoDAO;
import com.iafasep.DataService.Service.TextoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Service
public class TextoServiceImpl implements TextoService {

    @Autowired
    private TextoDAO textoDAO;

    @Override
    public BeanCombos getPrecioTechosByPeriodoAndBrigadaAndTipoAsignacionAndTipoCombustible(String periodo, Integer brigada, Integer tipoAsignacion, Integer tipoCombustible) {
        return textoDAO.getPrecioTechosByPeriodoAndBrigadaAndTipoAsignacionAndTipoCombustible(periodo, brigada, tipoAsignacion, tipoCombustible);
    }

    @Override
    public BeanCombos getSaldoTechosByPeriodoAndBrigadaAndTipoAsignacionAndTipoCombustible(String periodo, Integer brigada, Integer tipoAsignacion, Integer tipoCombustible) {
        return textoDAO.getSaldoTechosByPeriodoAndBrigadaAndTipoAsignacionAndTipoCombustible(periodo, brigada, tipoAsignacion, tipoCombustible);
    }
}
