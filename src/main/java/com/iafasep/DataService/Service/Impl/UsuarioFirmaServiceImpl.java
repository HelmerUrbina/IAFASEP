package com.iafasep.DataService.Service.Impl;

import java.util.List;
import com.iafasep.BusinessServices.Beans.BeanUsuarioFirma;
import com.iafasep.Utiles.Utiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iafasep.DataService.DAO.UsuarioFirmaDAO;
import com.iafasep.DataService.Service.UsuarioFirmaService;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Service
public class UsuarioFirmaServiceImpl implements UsuarioFirmaService {

    @Autowired
    private UsuarioFirmaDAO firmaUsuarioDAO;

    @Override
    public List<BeanUsuarioFirma> getFirmas(String periodo) {
        return firmaUsuarioDAO.findAll(periodo);
    }

    @Override
    public String guardarFirma(BeanUsuarioFirma obj, String usuario, String modo) {
        String result = "GUARDO";
        try {
            firmaUsuarioDAO.sp_firma(
                    obj.getPeriodo(),
                    obj.getUsuario(),
                    obj.getSerie(),
                    Integer.parseInt(obj.getGrado()),
                    usuario,
                    modo
            );
        } catch (Exception | Error e) {
            result = Utiles.getErrorSQL((Exception) e);
        }
        return result;
    }

}
