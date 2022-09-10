package com.iafasep.DataService.Service;

import java.util.List;
import com.iafasep.BusinessServices.Beans.BeanUsuarioFirma;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
public interface UsuarioFirmaService {

    public List<BeanUsuarioFirma> getFirmas(String periodo);

    public String guardarFirma(BeanUsuarioFirma obj, String usuario, String modo);

}
