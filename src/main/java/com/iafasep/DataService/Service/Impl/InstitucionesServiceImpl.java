package com.iafasep.DataService.Service.Impl;

import com.iafasep.BusinessServices.Beans.BeanComun;
import com.iafasep.DataService.DAO.InstitucionesDAO;
import com.iafasep.DataService.Service.InstitucionesService;
import com.iafasep.Utiles.Utiles;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Service
public class InstitucionesServiceImpl implements InstitucionesService {

    @Autowired
    private InstitucionesDAO institucionDAO;

    @Override
    public List<BeanComun> getInstituciones() {
        return institucionDAO.findAll();
    }

    @Override
    public String guardarInstitucion(BeanComun objBnComun, String usuario, String modo) {
        String result = "GUARDO";
        try {
            institucionDAO.sp_instituciones(
                    objBnComun.getCodigo(),
                    objBnComun.getDescripcion(),
                    objBnComun.getAbreviatura(),
                    usuario,
                    modo);
        } catch (Exception | Error e) {
            result = Utiles.getErrorSQL((Exception) e);
        }
        return result;
    }
}
