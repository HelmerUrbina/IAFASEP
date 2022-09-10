package com.iafasep.UserServices.Configuracion;

import com.google.gson.Gson;
import com.iafasep.BusinessServices.Beans.BeanComun;
import com.iafasep.DataService.Service.PeriodosService;
import com.iafasep.Utiles.Utiles;
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
public class PeriodosController {

    @Autowired
    private PeriodosService periodosService;

    @RequestMapping(value = "/Periodos")
    @ResponseBody
    public String getPeriodosDetalle(String mode, String codigo) {
        return switch (mode) {
            case "G" ->
                new Gson().toJson(periodosService.getPeriodos());
            default ->
                "ERROR";
        };
    }

    @RequestMapping(value = "/IduPeriodos")
    @ResponseBody
    public String iduPeriodos(
            @RequestParam("mode") String mode,
            @RequestParam("codigo") Integer codigo,
            @RequestParam("abreviatura") String abreviatura,
            @RequestParam("descripcion") String descripcion) {
        BeanComun objBeanComun = new BeanComun();
        objBeanComun.setCodigo(codigo);
        objBeanComun.setDescripcion(descripcion);
        objBeanComun.setAbreviatura(abreviatura);
        return "" + periodosService.guardarPeriodos(objBeanComun, Utiles.getUsuario(), mode);
    }
}
