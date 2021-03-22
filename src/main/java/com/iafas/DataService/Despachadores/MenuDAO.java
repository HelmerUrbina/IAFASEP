package com.iafas.DataService.Despachadores;

import com.iafas.BusinessServices.Beans.BeanMenu;
import java.util.List;

/**
 *
 * @author H-URBINA-M
 */
public interface MenuDAO {

    public List getListaMenu();

    public BeanMenu getMenu(BeanMenu objBeanComun);

    public int iduMenu(BeanMenu objBeanMenu, String usuario);

}
