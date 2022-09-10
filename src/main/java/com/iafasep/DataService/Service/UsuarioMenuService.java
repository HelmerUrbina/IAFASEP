package com.iafasep.DataService.Service;

import java.util.ArrayList;
import java.util.List;
import com.iafasep.BusinessServices.Beans.BeanMenu;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
public interface UsuarioMenuService {

    public List<BeanMenu> getMenuUsuario(String usuario);

    public List<BeanMenu> getModuloUsuario(String usuario);

    public ArrayList getOpcionesUsuario();

    public ArrayList getOpcionesOfUsuario(String usuario);

}
