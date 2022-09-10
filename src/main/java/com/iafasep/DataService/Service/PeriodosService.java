package com.iafasep.DataService.Service;

import com.iafasep.BusinessServices.Beans.BeanComun;
import java.util.List;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
public interface PeriodosService {

    public List<BeanComun> getPeriodos();

    public String guardarPeriodos(BeanComun objBnComun, String usuario, String modo);
}
