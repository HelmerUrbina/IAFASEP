package com.iafasep.DataService.Service;

import com.iafasep.BusinessServices.Beans.BeanComun;
import java.util.List;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
public interface InstitucionesService {

    public List<BeanComun> getInstituciones();

    public String guardarInstitucion(BeanComun objBnComun, String usuario, String modo);
}
