/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafasep.UserServices.Programacion;

import com.google.gson.Gson;
import com.iafasep.DataService.Service.ProgramacionMultiAnualService;
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
public class ProgramacionController {
    
    @Autowired
    private ProgramacionMultiAnualService programacionMultiAnualService;
    
    @RequestMapping(value = "/Programacion")
    @ResponseBody
    public String getProgramacionDetalle(String mode, String codigo, String codigo2, String codigo3) {
        return switch (mode) {
            case "G" ->
                new Gson().toJson(programacionMultiAnualService.getProgramacionMultianual(Integer.parseInt(codigo), codigo2));
            case "D" ->
                new Gson().toJson(programacionMultiAnualService.getProgramacionMultianualDetalle(Integer.parseInt(codigo), codigo2, Integer.parseInt(codigo3)));    
            default ->
                "ERROR";
        };
    }
    
    
    
    @RequestMapping(value = "/IduProgramacion")
    @ResponseBody
    public String iduPeriodos(
            @RequestParam("periodo") String periodo,
            @RequestParam("fuente") String fuente,
            @RequestParam("tarea") String tarea,
            @RequestParam("ubigeo") String ubigeo,
            @RequestParam("cant") String cant,
            @RequestParam("mode") String mode) {
        Map<String,String> obj = new HashMap<>();
        obj.put("periodo", periodo);
        obj.put("fuente", fuente);
        obj.put("tarea", tarea);
        obj.put("ubigeo", ubigeo);
        obj.put("cant", cant);
        return "" + programacionMultiAnualService.guardarProgramacionMulti((HashMap) obj, Utiles.getUsuario(), mode);
    }
    
    @RequestMapping(value = "/IduProgramacionDetalle")
    @ResponseBody
    public String iduPeriodosDetalle(
            @RequestParam("periodo") String periodo,
            @RequestParam("fuente") String fuente,
            @RequestParam("tarea") String tarea,
            @RequestParam("cant") String cant) {
        Map<String,String> obj = new HashMap<>();
        obj.put("periodo", periodo);
        obj.put("fuente", fuente);
        obj.put("tarea", tarea);
        obj.put("cant", cant);
        return "" + programacionMultiAnualService.guardarProgramacionMultiDetalle((HashMap) obj, Utiles.getUsuario());
    }
    
}
