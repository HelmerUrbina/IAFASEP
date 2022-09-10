package com.iafasep.DataService.Service.Impl;

import com.iafasep.BusinessServices.Beans.BeanComun;
import com.iafasep.DataService.DAO.PeriodosDAO;
import com.iafasep.DataService.Service.PeriodosService;
import com.iafasep.Utiles.Utiles;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Service
public class PeriodosServiceImpl implements PeriodosService {

    @Autowired
    private PeriodosDAO periodoDAO;

    @Override
    public List<BeanComun> getPeriodos() {
        return periodoDAO.findAll();
    }

    @Override
    public String guardarPeriodos(BeanComun objBnComun, String usuario, String modo) {
        String result = "GUARDO";
        try {
            periodoDAO.sp_periodo(
                    "" + objBnComun.getCodigo(),
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
