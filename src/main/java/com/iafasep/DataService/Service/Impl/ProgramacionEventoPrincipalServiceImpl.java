/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafasep.DataService.Service.Impl;

import com.iafasep.BusinessServices.Beans.BeanCuadroNecesidad;
import com.iafasep.BusinessServices.Beans.BeanProgramacionEventoPrincipal;
import com.iafasep.DataService.DAO.CuadroNecesidadDAO;
import com.iafasep.DataService.DAO.ProgramacionEventoPrincipalDAO;
import com.iafasep.DataService.Service.ProgramacionEventoPrincipalService;
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
public class ProgramacionEventoPrincipalServiceImpl implements ProgramacionEventoPrincipalService {

    @Autowired
    private ProgramacionEventoPrincipalDAO eventoPrincipalDAO;
    
    @Autowired
    private CuadroNecesidadDAO cuadroNecesidadDAO;

    @Override
    public List<BeanProgramacionEventoPrincipal> getProgramacionEventoPrincipal(String periodo, Integer fuente, Integer tarea) {
        return eventoPrincipalDAO.findByPeriodoFuenteTarea(periodo, fuente, tarea);
    }

    @Override
    public List<BeanProgramacionEventoPrincipal> getProgramacionEventosSecundarios(String periodo, Integer fuente, Integer tarea, String eventoPrincipal, Integer nivel) {
        return eventoPrincipalDAO.findByPeriodoFuenteEventoPrincipalNivel(periodo, fuente, tarea, eventoPrincipal, nivel);
    }
 
    @Override
    public String guardarProgramacionEventoPrincipal(HashMap obj, String usuario, String modo) {
        String result = "GUARDO";
        try {
            eventoPrincipalDAO.sp_programacionEventoPrincipal(
                    obj.get("periodo").toString(),
                    Utiles.checkNum(obj.get("fuente").toString()),
                    Utiles.checkNum(obj.get("tarea").toString()),
                    obj.get("eventoPrincipal").toString(),
                    obj.get("eventoPrincipalNombre").toString(),
                    obj.get("eventoPrincipalComentario").toString(),
                    Utiles.checkNum(obj.get("nivel").toString()),
                    Utiles.checkNum(obj.get("niveles").toString()),
                    obj.get("eventoFinal").toString(),
                    obj.get("eventoPrincipalPrincipal").toString(),
                    usuario,
                    modo
            );
        } catch (Exception | Error e) {
            result = Utiles.getErrorSQL((Exception) e);
        }
        return result;
    }

    @Override
    public List<BeanCuadroNecesidad> getCuadroNecesidad(String periodo, Integer fuente) {
        return cuadroNecesidadDAO.findByPeriodoFuente(periodo, fuente);
    }

}
