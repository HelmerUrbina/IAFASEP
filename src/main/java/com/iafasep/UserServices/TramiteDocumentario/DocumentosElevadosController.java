package com.iafasep.UserServices.TramiteDocumentario;

import com.google.gson.Gson;
import com.iafasep.DataService.Service.RemisionDocumentoService;
import com.iafasep.Utiles.Utiles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Controller
@Slf4j
public class DocumentosElevadosController {

    @Autowired
    private RemisionDocumentoService remisionDocumentoService;

    @RequestMapping(value = "/DocumentosElevados")
    @ResponseBody
    public String getRequestMapping(String mode, String periodo, String estado, String codigo) {
        return switch (mode) {
            case "G" ->
                new Gson().toJson(remisionDocumentoService.getListaDocumentosElevados(periodo, Utiles.getUsuario(), estado));
            default ->
                "ERROR";
        };
    }

}
