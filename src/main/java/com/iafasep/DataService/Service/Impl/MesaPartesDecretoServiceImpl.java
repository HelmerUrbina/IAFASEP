package com.iafasep.DataService.Service.Impl;

import com.iafasep.BusinessServices.Beans.BeanMesaPartesDecreto;
import com.iafasep.DataService.DAO.MesaPartesDecretoDAO;
import com.iafasep.DataService.Service.MesaPartesDecretoService;
import com.iafasep.Utiles.Utiles;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MesaPartesDecretoServiceImpl implements MesaPartesDecretoService {

    @Autowired
    private MesaPartesDecretoDAO mesaPartesDecretoDAO;

    @Override
    public List getListaMesaPartesDecretos(String tipo, String periodo, String numero) {
        return mesaPartesDecretoDAO.findByTipoPeriodoNumero(tipo, periodo, numero);
    }

    @Override
    public String iduMesaParteDecreto(BeanMesaPartesDecreto objBeanMesaParteDecreto, String usuario, String mode) {
        try {
            mesaPartesDecretoDAO.sp_iduMesaPartesDecreto(
                    objBeanMesaParteDecreto.getPeriodo(),
                    objBeanMesaParteDecreto.getTipo(),
                    objBeanMesaParteDecreto.getNumero(),
                    objBeanMesaParteDecreto.getDecreto(),
                    objBeanMesaParteDecreto.getUsuarioDecreta(),
                    objBeanMesaParteDecreto.getDescripcion(),
                    objBeanMesaParteDecreto.getPrioridad(),
                    objBeanMesaParteDecreto.getUsuarioResponsable(),
                    objBeanMesaParteDecreto.getEstado(),
                    usuario,
                    mode);
        } catch (Exception | Error e) {
            return Utiles.getErrorSQL((Exception) e);
        }
        return "GUARDO";
    }
}
