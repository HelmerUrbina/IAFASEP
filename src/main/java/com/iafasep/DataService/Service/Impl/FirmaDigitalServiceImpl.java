package com.iafasep.DataService.Service.Impl;

import com.iafasep.BusinessServices.Beans.BeanFirmaDigital;
import com.iafasep.DataService.DAO.FirmaDigitalDAO;
import com.iafasep.DataService.Service.FirmaDigitalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author helme
 */
@Service
public class FirmaDigitalServiceImpl implements FirmaDigitalService {

    @Autowired
    private FirmaDigitalDAO firmaDigitalDAO;

    @Override
    public List<BeanFirmaDigital> getCertificadoPresupuestal(String periodo, String fuenteFinanciamiento, String estado) {
        return firmaDigitalDAO.getCertificadosPresupuestales(periodo, fuenteFinanciamiento, estado);
    }

}
