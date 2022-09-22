/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafasep.DataService.Service;

//import com.iafasep.BusinessServices.Beans.BeanComun;
import com.iafasep.BusinessServices.Beans.BeanProgramacionMultianualDetalleDto;
import com.iafasep.BusinessServices.Beans.BeanProgramacionMultianualDto;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
public interface ProgramacionMultiAnualService {
    
    public List<BeanProgramacionMultianualDto> getProgramacionMultianual(Integer fuente, String periodo);
    
    public List<BeanProgramacionMultianualDetalleDto> getProgramacionMultianualDetalle(Integer fuente, String periodo, Integer tarea);

    public String guardarProgramacionMulti(HashMap obj, String usuario, String modo);
    
    public String guardarProgramacionMultiDetalle(HashMap obj, String usuario);
    
}
