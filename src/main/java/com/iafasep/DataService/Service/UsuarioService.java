package com.iafasep.DataService.Service;

import java.util.List;
import com.iafasep.BusinessServices.Beans.BeanUsuario;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
public interface UsuarioService {

    public BeanUsuario findByUsername(String usuario);

    public BeanUsuario findByUsernameAndEstado(String usuario, String estado);

    public List<BeanUsuario> findAll();

    public String guardarUsuario(BeanUsuario objBeanUsuario, String opciones, String usuario, String modo);

}
