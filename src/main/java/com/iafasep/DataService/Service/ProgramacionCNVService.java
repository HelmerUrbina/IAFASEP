/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafasep.DataService.Service;

import com.iafasep.BusinessServices.Beans.BeanProgramacionCNV;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
public interface ProgramacionCNVService {

    public List<BeanProgramacionCNV> getProgramacionCNV(String periodo, Integer fuente, Integer tarea, String eventoPrincipal, Integer eventoFinal);

    public String guardarProgramacionCNV(HashMap obj, String usuario, String modo);

}
