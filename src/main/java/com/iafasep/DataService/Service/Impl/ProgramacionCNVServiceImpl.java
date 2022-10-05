/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafasep.DataService.Service.Impl;

import com.iafasep.BusinessServices.Beans.BeanProgramacionCNV;
import com.iafasep.DataService.DAO.ProgramacionCNVDAO;
import com.iafasep.DataService.Service.ProgramacionCNVService;
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
public class ProgramacionCNVServiceImpl implements ProgramacionCNVService {

    @Autowired
    private ProgramacionCNVDAO cnvDAO;

    @Override
    public List<BeanProgramacionCNV> getProgramacionCNV(String periodo, Integer fuente, Integer tarea, String eventoPrincipal, Integer eventoFinal) {
        return cnvDAO.findByPeriodoFuenteTareaEventoPrincipalEventoFinal(periodo, fuente, tarea, eventoPrincipal, eventoFinal);
    }
        
    @Override
    public String guardarProgramacionCNV(HashMap obj, String usuario, String modo) {
        String result = "GUARDO";
        try {
            cnvDAO.sp_programacionCNV(
                    obj.get("periodo").toString(),
                    Utiles.checkNum(obj.get("fuente").toString()),
                    Utiles.checkNum(obj.get("tarea").toString()),
                    obj.get("eventoPrincipal").toString(),
                    Utiles.checkNum(obj.get("eventoFinal").toString()),
                    Utiles.checkNum(obj.get("item").toString()),
                    Utiles.checkNum(obj.get("clasificador").toString()),
                    Utiles.checkDouble(obj.get("precio").toString()),
                    obj.get("json").toString(),
                    usuario,
                    modo
            );
        } catch (Exception | Error e) {
            result = Utiles.getErrorSQL((Exception) e);
        }
        return result;
    }

}
