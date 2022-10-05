/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafasep.DataService.Service;

import com.iafasep.BusinessServices.Beans.BeanProgramacionEventoFinal;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
public interface ProgramacionEventoFinalService {

    public List<BeanProgramacionEventoFinal> getProgramacionEventoFinal(String periodo, Integer fuente, Integer tarea, String eventoPrincipal);

    public String guardarProgramacionEventoFinal(HashMap obj, String usuario, String modo);

}
