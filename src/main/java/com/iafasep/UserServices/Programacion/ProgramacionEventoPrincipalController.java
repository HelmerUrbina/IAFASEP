/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafasep.UserServices.Programacion;

import com.google.gson.Gson;
import com.iafasep.BusinessServices.Beans.BeanProgramacionEventoFinal;
import com.iafasep.BusinessServices.Beans.BeanProgramacionEventoPrincipal;
import com.iafasep.DataService.Service.ProgramacionEventoFinalService;
import com.iafasep.DataService.Service.ProgramacionEventoPrincipalService;
import com.iafasep.Utiles.Utiles;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Controller
@Slf4j
public class ProgramacionEventoPrincipalController {
    
    @Autowired
    private ProgramacionEventoPrincipalService programacionEventoPrincipalService;
    
    @Autowired
    private ProgramacionEventoFinalService programacionEventoFinalService;
    
    @RequestMapping(value = "/ProgramacionEventoPrincipal")
    public String getTipoAsignacion(String mode) {
        switch (mode) {
            case "programacionEventoPrincipal":
                return "Programacion/ProgramacionEventoPrincipal";
            case "programacionEventoSecundario":
                return "Programacion/ProgramacionEventoSecundario";
            case "programacionEventoFinal":
                return "Programacion/ProgramacionEventoFinal";
            default:
                return "redirect:/";
        }
    }
    
    @RequestMapping(value = "/ProgramacionEventoPrincipalDetalle")
    @ResponseBody
    public String getTipoAsignacionDetalle(String mode, String periodo, Integer fuente, Integer tarea, String eventoPrincipal, Integer nivel) {
        switch (mode) {
            case "T":
                return new Gson().toJson(programacionEventoPrincipalService.getCuadroNecesidad(periodo, fuente));
            case "G":
                return new Gson().toJson(programacionEventoPrincipalService.getProgramacionEventoPrincipal(periodo, fuente, tarea));
            case "GS":
                return new Gson().toJson(programacionEventoPrincipalService.getProgramacionEventosSecundarios(periodo, fuente, tarea, eventoPrincipal, nivel));
            case "GF":
                return new Gson().toJson(programacionEventoFinalService.getProgramacionEventoFinal(periodo, fuente, tarea, eventoPrincipal));
            default:
                return "ERROR";
        }
    }
    
    @RequestMapping(value = "/IduProgramacionEventoPrincipal")
    @ResponseBody
    public String setGuardarEventoPrincipal(
            @RequestParam("mode") String mode,
            @RequestParam("periodo") String periodo,
            @RequestParam("fuente") Integer fuente,
            @RequestParam("tarea") Integer tarea,
            @RequestParam("eventoPrincipal") String eventoPrincipal,
            @RequestParam("eventoPrincipalNombre") String eventoPrincipalNombre,
            @RequestParam("eventoPrincipalComentario") String eventoPrincipalComentario,
            @RequestParam("nivel") Integer nivel,
            @RequestParam("niveles") Integer niveles,
            @RequestParam("eventoFinal") String eventoFinal,
            @RequestParam("eventoPrincipalPrincipal") String eventoPrincipalPrincipal) {
        Map<String,String> obj = new HashMap<>();
        obj.put("periodo", periodo);
        obj.put("fuente", fuente.toString());
        obj.put("tarea", tarea.toString());
        obj.put("eventoPrincipal", eventoPrincipal);
        obj.put("eventoPrincipalNombre", eventoPrincipalNombre);
        obj.put("eventoPrincipalComentario", eventoPrincipalComentario);
        obj.put("nivel", nivel.toString());
        obj.put("niveles", niveles.toString());
        obj.put("eventoFinal", eventoFinal);
        obj.put("eventoPrincipalPrincipal", eventoPrincipalPrincipal);
        return "" + programacionEventoPrincipalService.guardarProgramacionEventoPrincipal((HashMap) obj, Utiles.getUsuario(), mode);
    }
    
    @RequestMapping(value = "/IduProgramacionEventoFinal")
    @ResponseBody
    public String setGuardarEventoFinal(
            @RequestParam("mode") String mode,
            @RequestParam("periodo") String periodo,
            @RequestParam("fuente") Integer fuente,
            @RequestParam("tarea") Integer tarea,
            @RequestParam("eventoPrincipal") String eventoPrincipal,
            @RequestParam("eventoFinal") Integer eventoFinal,
            @RequestParam("eventoFinalNombre") String eventoFinalNombre,
            @RequestParam("prioridad") Integer prioridad,
            @RequestParam("meta") Integer meta) {
        Map<String,String> obj = new HashMap<>();
        obj.put("periodo", periodo);
        obj.put("fuente", fuente.toString());
        obj.put("tarea", tarea.toString());
        obj.put("eventoPrincipal", eventoPrincipal);
        obj.put("eventoFinal", eventoFinal.toString());
        obj.put("eventoFinalNombre", eventoFinalNombre);
        obj.put("prioridad", prioridad.toString());
        obj.put("meta", meta.toString());
        return "" + programacionEventoFinalService.guardarProgramacionEventoFinal((HashMap) obj, Utiles.getUsuario(), mode);
    }
    
}
