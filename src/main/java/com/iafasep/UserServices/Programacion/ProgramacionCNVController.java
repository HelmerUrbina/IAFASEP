/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafasep.UserServices.Programacion;

import com.google.gson.Gson;
import com.iafasep.BusinessServices.Beans.BeanProgramacionCNV;
import com.iafasep.DataService.Service.ProgramacionCNVService;
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
public class ProgramacionCNVController {

    @Autowired
    private ProgramacionCNVService programacionCNVService;

    @RequestMapping(value = "/ProgramacionCNV")
    public String getTipoAsignacion(String mode) {
        switch (mode) {
            case "programacionCNV":
                return "Programacion/ProgramacionCNV";
            default:
                return "redirect:/";
        }
    }
    
    @RequestMapping(value = "/ProgramacionCNVDetalle")
    @ResponseBody
    public String getTipoAsignacionDetalle(String mode, String periodo, Integer fuente, Integer tarea, String eventoPrincipal, Integer eventoFinal) {
        switch (mode) {
            case "G":
                return new Gson().toJson(programacionCNVService.getProgramacionCNV(periodo, fuente, tarea, eventoPrincipal, eventoFinal));
            default:
                return "ERROR";
        }
    }

    @RequestMapping(value = "/IduProgramacionCNV")
    @ResponseBody
    public String setGuardarCNV(
            @RequestParam("mode") String mode,
            @RequestParam("periodo") String periodo,
            @RequestParam("fuente") Integer fuente,
            @RequestParam("tarea") Integer tarea,
            @RequestParam("eventoPrincipal") String eventoPrincipal,
            @RequestParam("eventoFinal") Integer eventoFinal,
            @RequestParam("item") Integer item,
            @RequestParam("clasificador") Integer clasificador,
            @RequestParam("precio") Double precio,
            @RequestParam("json") String json
            ) {
        Map<String,String> obj = new HashMap<>();
        obj.put("periodo", periodo);
        obj.put("fuente", fuente.toString());
        obj.put("tarea", tarea.toString());
        obj.put("eventoPrincipal", eventoPrincipal);
        obj.put("eventoFinal", eventoFinal.toString());
        obj.put("item", item.toString());
        obj.put("clasificador", clasificador.toString());
        obj.put("precio", precio.toString());
        obj.put("json", json);
        return "" + programacionCNVService.guardarProgramacionCNV((HashMap) obj, Utiles.getUsuario(), mode);
    }

}
