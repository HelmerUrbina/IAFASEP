package com.iafasep.UserServices.Seguridad;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import com.iafasep.BusinessServices.Beans.BeanUsuarioFirma;
import com.iafasep.Utiles.Utiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.iafasep.DataService.Service.UsuarioFirmaService;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Controller
@Slf4j
public class UsuarioFirmaController {

    @Autowired
    private UsuarioFirmaService firmaUsuarioService;

    @RequestMapping(value = "/UsuarioFirmaDetalle")
    @ResponseBody
    public String getRequestMapping(String mode, String periodo) {
        return switch (mode) {
            case "G" ->
                new Gson().toJson(firmaUsuarioService.getFirmas(periodo));
            default ->
                "ERROR";
        };
    }

    @RequestMapping(value = "/IduUsuarioFirma")
    @ResponseBody
    public String setRequestMapping(
            @RequestParam("mode") String mode,
            @RequestParam("periodo") String periodo,
            @RequestParam("usuario") String usuario,
            @RequestParam("serie") String serie,
            @RequestParam("grado") String grado) {
        BeanUsuarioFirma objBeanUsuarioFirma = new BeanUsuarioFirma();
        objBeanUsuarioFirma.setPeriodo(periodo);
        objBeanUsuarioFirma.setUsuario(usuario);
        objBeanUsuarioFirma.setSerie(serie);
        objBeanUsuarioFirma.setGrado(grado);
        return "" + firmaUsuarioService.guardarFirma(objBeanUsuarioFirma, Utiles.getUsuario(), mode);
    }

}
