package com.iafasep.UserServices.TramiteDocumentario;

import com.google.gson.Gson;
import com.iafasep.BusinessServices.Beans.BeanMesaPartesDecreto;
import com.iafasep.DataService.Service.MesaPartesDecretoService;
import com.iafasep.DataService.Service.MesaPartesService;
import com.iafasep.Utiles.Utiles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author helme
 */
@Controller
@Slf4j
public class MesaPartesDecretoController {

    @Autowired
    private MesaPartesDecretoService mesaPartesDecretoService;

    @Autowired
    private MesaPartesService mesaPartesService;

    @RequestMapping(value = "/Decreto")
    @ResponseBody
    public String getRequestMapping(String mode, String tipo, String periodo, String mes, String codigo) {
        return switch (mode) {
            case "G" ->
                new Gson().toJson(mesaPartesService.getListaMesaPartesEstados(tipo, periodo, mes, codigo));
            case "L" ->
                new Gson().toJson(mesaPartesService.getListaMesaParteDecretados(periodo, codigo));
            case "S" ->
                new Gson().toJson(mesaPartesDecretoService.getListaMesaPartesDecretos(tipo, periodo, codigo));
            default ->
                "ERROR";
        };
    }

    @RequestMapping(value = "/IduMesaPartesDecreto")
    @ResponseBody
    public String setRequestMapping(
            @RequestParam("mode") String mode,
            @RequestParam("periodo") String periodo,
            @RequestParam("tipo") String tipo,
            @RequestParam("numero") String numero,
            @RequestParam("decreto") String decreto,
            @RequestParam("usuarioEmision") String usuarioEmision,
            @RequestParam("usuario") String usuarioResponsable,
            @RequestParam("comentario") String comentario,
            @RequestParam("prioridad") String prioridad,
            @RequestParam("tipoDecretos") String tipoDecretos
    ) {
        BeanMesaPartesDecreto objBeanMesaPartesDecreto = new BeanMesaPartesDecreto();
        objBeanMesaPartesDecreto.setPeriodo(periodo);
        objBeanMesaPartesDecreto.setTipo(tipo);
        objBeanMesaPartesDecreto.setNumero(numero);
        objBeanMesaPartesDecreto.setDecreto(decreto);
        objBeanMesaPartesDecreto.setUsuarioDecreta(usuarioEmision);
        objBeanMesaPartesDecreto.setUsuarioResponsable(usuarioResponsable);
        objBeanMesaPartesDecreto.setPrioridad(prioridad);
        objBeanMesaPartesDecreto.setEstado(tipoDecretos);
        objBeanMesaPartesDecreto.setDescripcion(comentario);
        return "" + mesaPartesDecretoService.iduMesaParteDecreto(objBeanMesaPartesDecreto, Utiles.getUsuario(), mode);
    }
}
