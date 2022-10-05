/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafasep.DataService.Service.Impl;

import com.iafasep.BusinessServices.Beans.BeanProgramacionEventoFinal;
import com.iafasep.DataService.DAO.ProgramacionEventoFinalDAO;
import com.iafasep.DataService.Service.ProgramacionEventoFinalService;
import com.iafasep.Utiles.Utiles;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Service
public class ProgramacionEventoFinalServiceImpl implements ProgramacionEventoFinalService {

    @Autowired
    private ProgramacionEventoFinalDAO eventoPrincipalDAO;

    @Override
    public List<BeanProgramacionEventoFinal> getProgramacionEventoFinal(String periodo, Integer fuente, Integer tarea, String eventoFinal) {
        return eventoPrincipalDAO.findByPeriodoTipoAsignacionBrigadaEventoPrincipal(periodo, fuente, tarea, eventoFinal);
    }

    @Override
    public String guardarProgramacionEventoFinal(HashMap obj, String usuario, String modo) {
        String result = "GUARDO";
        try {
            eventoPrincipalDAO.sp_programacionEventoFinal(
                    obj.get("periodo").toString(),
                    Utiles.checkNum(obj.get("fuente").toString()),
                    Utiles.checkNum(obj.get("tarea").toString()),
                    obj.get("eventoPrincipal").toString(),
                    Utiles.checkNum(obj.get("eventoFinal").toString()),
                    obj.get("eventoFinalNombre").toString(),
                    Utiles.checkNum(obj.get("prioridad").toString()),
                    Utiles.checkNum(obj.get("meta").toString()),
                    usuario,
                    modo
            );
        } catch (Exception | Error e) {
            result = Utiles.getErrorSQL((Exception) e);
        }
        return result;
    }

}
