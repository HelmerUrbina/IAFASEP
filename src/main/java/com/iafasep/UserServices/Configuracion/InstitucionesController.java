package com.iafasep.UserServices.Configuracion;

import com.google.gson.Gson;
import com.iafasep.BusinessServices.Beans.BeanComun;
import com.iafasep.DataService.Service.InstitucionesService;
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
public class InstitucionesController {

    @Autowired
    private InstitucionesService institucionesService;

    @RequestMapping(value = "/Instituciones")
    @ResponseBody
    public String getRequestMapping(String mode, Integer codigo) {
        return switch (mode) {
            case "G" ->
                new Gson().toJson(institucionesService.getInstituciones());
            default ->
                "ERROR";
        };
    }

    @RequestMapping(value = "/IduInstituciones")
    @ResponseBody
    public String setRequestMapping(
            @RequestParam("mode") String mode,
            @RequestParam("codigo") Integer codigo,
            @RequestParam("abreviatura") String abreviatura,
            @RequestParam("descripcion") String descripcion) {
        BeanComun objBeanComun = new BeanComun();
        objBeanComun.setCodigo(codigo);
        objBeanComun.setDescripcion(descripcion);
        objBeanComun.setAbreviatura(abreviatura);
        return "" + institucionesService.guardarInstitucion(objBeanComun, Utiles.getUsuario(), mode);
    }
}
