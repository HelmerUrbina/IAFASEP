package com.iafasep.UserServices.TramiteDocumentario;

import com.google.gson.Gson;
import com.iafasep.BusinessServices.Beans.BeanRemisionDocumento;
import com.iafasep.DataService.Service.RemisionDocumentoService;
import com.iafasep.Utiles.Utiles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Controller
@Slf4j
public class RemisionDocumentoController {

    @Autowired
    private RemisionDocumentoService remisionDocumentoService;

    @RequestMapping(value = "/RemisionDocumentos")
    @ResponseBody
    public String getRequestMapping(String mode, String periodo, String mes, String codigo) {
        return switch (mode) {
            case "G" ->
                new Gson().toJson(remisionDocumentoService.getListaRemisionDocumento(periodo, mes, Utiles.getUsuario()));
            default ->
                "ERROR";
        };
    }

    @RequestMapping(value = "/IduRemisionDocumentos")
    @ResponseBody
    public String setRemisionDocumento(
            @RequestParam("mode") String mode,
            @RequestParam("periodo") String periodo,
            @RequestParam("codigo") Integer codigo,
            @RequestParam("institucion") String institucion,
            @RequestParam("tipoDocumento") String tipoDocumento,
            @RequestParam("numeroDocumento") String numeroDocumento,
            @RequestParam("clasificacion") String clasificacion,
            @RequestParam("fecha") String fecha,
            @RequestParam("asunto") String asunto,
            @RequestParam("dirigido") String dirigido,
            @RequestParam("cargo") String cargo
    ) {
        BeanRemisionDocumento objRemisionDocumento = new BeanRemisionDocumento();
        objRemisionDocumento.setPeriodo(periodo);
        objRemisionDocumento.setCodigo(codigo);
        objRemisionDocumento.setInstitucion(" ");
        objRemisionDocumento.setTipoDocumento(tipoDocumento);
        objRemisionDocumento.setNumeroDocumento(numeroDocumento);
        objRemisionDocumento.setClasificacion(clasificacion);
        objRemisionDocumento.setFecha(fecha);
        objRemisionDocumento.setAsunto(asunto);
        objRemisionDocumento.setDirigido(dirigido);
        objRemisionDocumento.setCargo(cargo);
        return "" + remisionDocumentoService.iduRemisionDocumento(objRemisionDocumento, Utiles.getUsuario(), mode);
    }

    @RequestMapping(value = "/IduElevarDocumento")
    @ResponseBody
    public String setElevarDocumento(
            @RequestParam("mode") String mode,
            @RequestParam("periodo") String periodo,
            @RequestParam("codigo") Integer codigo,
            @RequestParam("elevacion") Integer elevacion,
            @RequestParam("areaLaboral") String areaLaboral,
            @RequestParam("responsable") String responsable,
            @RequestParam("files") MultipartFile[] files
    ) {
        BeanRemisionDocumento objBeanRemisionDocumento = new BeanRemisionDocumento();
        objBeanRemisionDocumento.setPeriodo(periodo);
        objBeanRemisionDocumento.setCodigo(codigo);
        objBeanRemisionDocumento.setElevacion(elevacion);
        objBeanRemisionDocumento.setAreaLaboral(areaLaboral);
        objBeanRemisionDocumento.setDirigido(responsable);
        return "" + remisionDocumentoService.iduElevarDocumento(objBeanRemisionDocumento, files, Utiles.getUsuario(), mode);
    }

}
