/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafasep.DataService.Service;

import com.iafasep.BusinessServices.Beans.BeanCuadroNecesidad;
import com.iafasep.BusinessServices.Beans.BeanProgramacionEventoPrincipal;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
public interface ProgramacionEventoPrincipalService {

    public List<BeanCuadroNecesidad> getCuadroNecesidad(String periodo, Integer fuente);
    
    public List<BeanProgramacionEventoPrincipal> getProgramacionEventoPrincipal(String periodo, Integer fuente, Integer tarea);

    public List<BeanProgramacionEventoPrincipal> getProgramacionEventosSecundarios(String periodo, Integer fuente,Integer tarea, String eventoPrincipal, Integer nivel);

    public String guardarProgramacionEventoPrincipal(HashMap objBnProgramacionEventPrincipal, String usuario, String modo);

}
