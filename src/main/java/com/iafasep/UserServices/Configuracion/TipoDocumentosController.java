package com.iafasep.UserServices.Configuracion;

import com.google.gson.Gson;
import com.iafasep.BusinessServices.Beans.BeanComun;
import com.iafasep.DataService.Service.AreaLaboralService;
import com.iafasep.DataService.Service.TipoDocumentosService;
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
public class TipoDocumentosController {

    @Autowired
    private TipoDocumentosService tipoDocumentosService;

    @RequestMapping(value = "/TipoDocumentos")
    @ResponseBody
    public String getRequestMapping(String mode, Integer codigo) {
        return switch (mode) {
            case "G" ->
                new Gson().toJson(tipoDocumentosService.getTipoDocumentos());
            default ->
                "ERROR";
        };
    }

    @RequestMapping(value = "/IduTipoDocumentos")
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
        return "" + tipoDocumentosService.guardarTipoDocumento(objBeanComun, Utiles.getUsuario(), mode);
    }
}
