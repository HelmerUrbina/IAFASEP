package com.iafasep.DataService.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import com.iafasep.BusinessServices.Beans.BeanMenu;
import com.iafasep.BusinessServices.Beans.BeanUsuarioOpciones;
import com.iafasep.DataService.DAO.UsuarioMenuDAO;
import com.iafasep.DataService.DAO.UsuarioOpcionesDAO;
import com.iafasep.DataService.Service.UsuarioMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Service
public class UsuarioMenuServiceImpl implements UsuarioMenuService {

    @Autowired
    private UsuarioMenuDAO usuarioMenuDAO;

    @Autowired
    private UsuarioOpcionesDAO usuarioOpcionesDAO;

    @Override
    public List<BeanMenu> getMenuUsuario(String usuario) {
        return usuarioMenuDAO.getMenuUsuario(usuario);
    }

    @Override
    public List<BeanMenu> getModuloUsuario(String usuario) {
        return usuarioMenuDAO.getModuloUsuario(usuario);
    }

    @Override
    public ArrayList getOpcionesUsuario() {
        ArrayList<String> Arreglo = new ArrayList<>();
        ArrayList<String> Filas = new ArrayList<>();
        try {
            List<BeanUsuarioOpciones> list = usuarioOpcionesDAO.getOpcionesUsuario();
            for (BeanUsuarioOpciones u : list) {
                Filas.clear();
                String arreglo = u.getUsuario() + "+++"
                        + u.getModuloCodigo() + "+++"
                        + u.getUsuarioCreador() + "+++"
                        + u.getMenuCodigo();
                Filas.add(arreglo);
                Arreglo.add("" + Filas.toString());
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        return Arreglo;
    }

    @Override
    public ArrayList getOpcionesOfUsuario(String usuario) {
        ArrayList<String> Arreglo = new ArrayList<>();
        List<BeanUsuarioOpciones> list = usuarioOpcionesDAO.getOpcionesOfUsuario(usuario);
        list.forEach(u -> {
            Arreglo.add(u.getUsuario());
        });
        return Arreglo;
    }

}
