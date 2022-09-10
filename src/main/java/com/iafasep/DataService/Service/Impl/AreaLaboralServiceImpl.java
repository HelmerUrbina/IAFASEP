package com.iafasep.DataService.Service.Impl;

import com.iafasep.BusinessServices.Beans.BeanComun;
import com.iafasep.DataService.DAO.AreaLaboralDAO;
import com.iafasep.DataService.Service.AreaLaboralService;
import com.iafasep.Utiles.Utiles;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Service
public class AreaLaboralServiceImpl implements AreaLaboralService {

    @Autowired
    private AreaLaboralDAO areaLaboralDAO;

   
    @Override
    public List<BeanComun> getAreaLaboral() {
        return areaLaboralDAO.findAll();
    }

    @Override
    public String guardarAreaLaboral(BeanComun objBnComun, String usuario, String modo) {
        String result = "GUARDO";
        try {
            areaLaboralDAO.sp_areaLaboral(
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
