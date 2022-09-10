package com.iafasep.DataService.Service;

import com.iafasep.BusinessServices.Beans.BeanComun;
import java.util.List;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
public interface AreaLaboralService {

    public List<BeanComun> getAreaLaboral();

    public String guardarAreaLaboral(BeanComun objBnComun, String usuario, String modo);
}
