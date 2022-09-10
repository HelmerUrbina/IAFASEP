package com.iafasep.DataService.Service;

import com.iafasep.BusinessServices.Beans.BeanMesaPartesDecreto;
import java.util.List;

/**
 *
 * @author helme
 */
public interface MesaPartesDecretoService {

    public List getListaMesaPartesDecretos(String tipo, String periodo, String numero);

    public String iduMesaParteDecreto(BeanMesaPartesDecreto objBeanMesaParteDecreto, String usuario, String mode);

}
