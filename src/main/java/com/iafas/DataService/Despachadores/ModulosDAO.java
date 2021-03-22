package com.iafas.DataService.Despachadores;

import com.iafas.BusinessServices.Beans.BeanComun;
import java.util.List;

/**
 *
 * @author H-URBINA-M
 */
public interface ModulosDAO {

    public List getListaModulos();

    public BeanComun getModulo(BeanComun objBeanComun);

    public int iduModulo(BeanComun objBeanComun, String usuario);
}
