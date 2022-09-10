package com.iafasep.UserServices.MesaPartes;

import com.google.gson.Gson;
import com.iafasep.BusinessServices.Beans.BeanMesaPartes;
import com.iafasep.DataService.Service.MesaPartesService;
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
public class MesaPartesController {

    @Autowired
    private MesaPartesService mesaPartesService;

    @RequestMapping(value = "/MesaPartes")
    @ResponseBody
    public String getRequestMapping(String mode, String tipo, String periodo, String mes, String codigo) {
        return switch (mode) {
            case "G" ->
                new Gson().toJson(mesaPartesService.getListaMesaPartes(tipo, periodo, mes, codigo));
            case "L" ->
                new Gson().toJson(mesaPartesService.getListaMesaPartesConsulta(tipo, periodo, mes));
            case "I" ->
                new Gson().toJson(mesaPartesService.getNumeroMesaParte(tipo, periodo));
            case "U" ->
                new Gson().toJson(mesaPartesService.getMesaParte(tipo, periodo, codigo));
            default ->
                "ERROR";
        };
    }

    @RequestMapping(value = "/IduMesaPartes")
    @ResponseBody
    public String setRequestMapping(
            @RequestParam("mode") String mode,
            @RequestParam("periodo") String periodo,
            @RequestParam("tipo") String tipo,
            @RequestParam("numero") String numero,
            @RequestParam("institucion") String institucion,
            @RequestParam("prioridad") String prioridad,
            @RequestParam("tipoDocumento") String tipoDocumento,
            @RequestParam("numeroDocumento") String numeroDocumento,
            @RequestParam("clasificacion") String clasificacion,
            @RequestParam("fechaDocumento") String fechaDocumento,
            @RequestParam("asunto") String asunto,
            @RequestParam("postFirma") String postFirma,
            @RequestParam("legajos") Integer legajos,
            @RequestParam("folios") Integer folios,
            @RequestParam("txt_Archivo") MultipartFile file
    ) {
        BeanMesaPartes objBeanMesaPartes = new BeanMesaPartes();
        objBeanMesaPartes.setPeriodo(periodo);
        objBeanMesaPartes.setTipo(tipo);
        objBeanMesaPartes.setNumero(numero);
        objBeanMesaPartes.setInstitucion(institucion);
        objBeanMesaPartes.setPrioridad(prioridad);
        objBeanMesaPartes.setTipoDocumento(tipoDocumento);
        objBeanMesaPartes.setNumeroDocumento(numeroDocumento);
        objBeanMesaPartes.setClasificacion(clasificacion);
        objBeanMesaPartes.setFecha(fechaDocumento);
        objBeanMesaPartes.setAsunto(asunto);
        objBeanMesaPartes.setPostFirma(postFirma);
        objBeanMesaPartes.setLegajos(legajos);
        objBeanMesaPartes.setFolios(folios);
        return "" + mesaPartesService.iduMesaParte(objBeanMesaPartes, file, Utiles.getUsuario(), mode);
    }

}
