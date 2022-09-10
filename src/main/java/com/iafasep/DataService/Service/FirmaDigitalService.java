package com.iafasep.DataService.Service;

import com.iafasep.BusinessServices.Beans.BeanFirmaDigital;
import java.util.List;

/**
 *
 * @author helme
 */
public interface FirmaDigitalService {

    public List<BeanFirmaDigital> getCertificadoPresupuestal(String periodo, String fuenteFinanciamiento, String estado);

}
