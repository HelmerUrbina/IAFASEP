package com.iafasep.DataService.Service.Impl;

import java.util.List;
import com.iafasep.BusinessServices.Beans.BeanUsuario;
import com.iafasep.DataService.DAO.UsuarioDAO;
import com.iafasep.DataService.Service.UsuarioService;
import com.iafasep.Utiles.Utiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public BeanUsuario findByUsername(String usuario) {
        return usuarioDAO.findByUsuario(usuario);
    }

    @Override
    public BeanUsuario findByUsernameAndEstado(String usuario, String estado) {
        return usuarioDAO.findByUsuarioAndEstado(usuario, estado);
    }

    @Override
    public List<BeanUsuario> findAll() {
        return usuarioDAO.findAll();
    }

    @Override
    public String guardarUsuario(BeanUsuario objBeanUsuario, String opciones, String usuario, String modo) {
        String result = "GUARDO";
        try {
            if (modo.equals("R")) {
                if (objBeanUsuario.getPassword().equals(null) || objBeanUsuario.getPassword().equals("")) {
                    objBeanUsuario.setPassword(objBeanUsuario.getUsuario());
                }
            }
            usuarioDAO.sp_iduUsuario(
                    objBeanUsuario.getUsuario(),
                    Utiles.encriptarPassword(objBeanUsuario.getPassword()),
                    objBeanUsuario.getPaterno(),
                    objBeanUsuario.getMaterno(),
                    objBeanUsuario.getNombres(),
                    Utiles.checkNum(objBeanUsuario.getAreaLaboral()),
                    objBeanUsuario.getCargo(),
                    objBeanUsuario.getTelefono(),
                    objBeanUsuario.getCorreo(),
                    Utiles.checkNum(objBeanUsuario.getRol()),
                    objBeanUsuario.getAutorizacion(),
                    opciones,
                    usuario,
                    modo
            );
        } catch (Exception | Error e) {
            result = Utiles.getErrorSQL((Exception) e);
        }
        return result;
    }

}
