package com.iafas.DataService.Despachadores;

import com.iafas.BusinessServices.Beans.BeanMsgerr;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author H-URBINA-M
 */
public interface MsgerrDAO {

    public BeanMsgerr getMsgerr(BeanMsgerr objBnMsgerr);

    public Integer iduMsgerr(BeanMsgerr objMsgerr);

    public Integer iduLog(String usuario, String tipo, String mensaje, HttpServletRequest request);

}
