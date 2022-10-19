package com.iafasep.Utiles;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import com.iafasep.DataService.Service.CombosService;
import com.iafasep.DataService.Service.TextoService;
////import mil.sinte.DataService.Service.TextoService;
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
public class CombosController {

    @Autowired
    private CombosService combosService;

    @Autowired
    private TextoService textoService;

    @RequestMapping(value = "/CombosAjax")
    @ResponseBody
    public String getCombos(String mode, String codigo, String codigo2, Integer codigo3, String codigo4, String codigo5, String codigo6) {
        return switch (mode) {
            /*CONFIGURACION*/
            case "periodos" ->
                new Gson().toJson(combosService.getPeriodos());
            case "meses" ->
                new Gson().toJson(combosService.getMeses());
            case "areaLaboral" ->
                new Gson().toJson(combosService.getAreaLaboral());
            case "rol" ->
                new Gson().toJson(combosService.getRol());
            case "departamentos" ->
                new Gson().toJson(combosService.getDepartamentos());
            case "provincias" ->
                new Gson().toJson(combosService.getProvincias(codigo));
            case "ubigeo" ->
                new Gson().toJson(combosService.getUbigeo(codigo, codigo2));
            case "monedas" ->
                new Gson().toJson(combosService.getMonedas());
            /*MESA DE PARTES*/
            case "instituciones" ->
                new Gson().toJson(combosService.getInstituciones());
            case "clasificacionDocumentos" ->
                new Gson().toJson(combosService.getClasificacionDocumentos());
            case "tipoDocumentos" ->
                new Gson().toJson(combosService.getTiposDocumentos());
            case "prioridades" ->
                new Gson().toJson(combosService.getPrioridades());
            case "tipoDecretos" ->
                new Gson().toJson(combosService.getTipoDecretos());
            case "usuarioJefatura" ->
                new Gson().toJson(combosService.getUsuarioJefatura());
            case "usuarioAreaLaboral" ->
                new Gson().toJson(combosService.getUsuarioAreaLaboral(codigo));
            case "remisionDocumentoInstitucion" ->
                new Gson().toJson(combosService.getRemisionDocumentosInstitucion(codigo, Utiles.checkNum(codigo2)));
            /*PRESUPUESTO*/
            case "fuenteFinanciamiento" ->
                new Gson().toJson(combosService.getFuenteFinanciamiento());
            case "tipoNotasModificatorias" ->
                new Gson().toJson(combosService.getTipoNotasModificatorias());
            case "resoluciones" ->
                new Gson().toJson(combosService.getResolucionesByPeriodoFuenteFinanciamiento(codigo, Utiles.checkNum(codigo2)));
            case "tareasPresupuestales" ->
                new Gson().toJson(combosService.getTareaPresupuestal());
            case "clasificadorPresupuestalByPeriodoTareaPresupuestal" ->
                new Gson().toJson(combosService.getClasificadorPresupuestalByPeriodoTareaPresupuestal(codigo, Utiles.checkNum(codigo2)));
            case "fuenteFinanciamientoNotaModificatoria" ->
                new Gson().toJson(combosService.getFuenteFinanciamientoNotaModificatoria(codigo));
            case "resolucionNotaModificatoria" ->
                new Gson().toJson(combosService.getResolucionesNotaModificatoria(codigo, Utiles.checkNum(codigo2)));
            case "tareasPresupuestalesNotaModificatoria" ->
                new Gson().toJson(combosService.getTareaPresupuestalNotaModificatoria(codigo, Utiles.checkNum(codigo2), codigo3));
            case "clasificadorPresupuestalesNotaModificatoria" ->
                new Gson().toJson(combosService.getClasificadorPresupuestalNotaModificatoria(codigo, Utiles.checkNum(codigo2), codigo3, Utiles.checkNum(codigo4)));
            case "resolucionCertificado" ->
                new Gson().toJson(combosService.getResolucionCertificadoPresupuestal(codigo, Utiles.checkNum(codigo2)));
            case "resolucionCertificadoRE" ->
                new Gson().toJson(combosService.getResolucionCertificadoPresupuestalRebaja(codigo, Utiles.checkNum(codigo2), codigo3));
            case "tareaPresupuestalCertificado" ->
                new Gson().toJson(combosService.getTareaPresupuestalCertificadoPresupuestal(codigo, Utiles.checkNum(codigo2), codigo3));
            case "tareaPresupuestalCertificadoRE" ->
                new Gson().toJson(combosService.getTareaPresupuestalCertificadoPresupuestalRebaja(codigo, Utiles.checkNum(codigo2), codigo3, Utiles.checkNum(codigo4)));
            case "clasificadorPresupuestalCertificado" ->
                new Gson().toJson(combosService.getClasificadorPresupuestalCertificadoPresupuestal(codigo, Utiles.checkNum(codigo2), codigo3, Utiles.checkNum(codigo4)));
            case "clasificadorPresupuestalCertificadoRE" ->
                new Gson().toJson(combosService.getClasificadorPresupuestalCertificadoPresupuestalRebaja(codigo, Utiles.checkNum(codigo2), codigo3, Utiles.checkNum(codigo4), Utiles.checkNum(codigo5)));
            case "fuenteByProgramacion" ->
                new Gson().toJson(combosService.getFuenteByProgramacion());
            case "tareaByProgramacion" ->
                new Gson().toJson(combosService.getTareaByProgramacion(codigo, Utiles.checkNum(codigo2)));
            case "anioByProgramacion" ->
                new Gson().toJson(combosService.getAnioByProgramacion(codigo));
            case "clasificadorProgByPeriodoFuenteTarea" ->
                new Gson().toJson(combosService.getClasificadorProgByPeriodoFuenteTarea(codigo, Utiles.checkNum(codigo2), codigo3));
            case "unidadMedida" ->
                new Gson().toJson(combosService.getunidadMedida());
            case "itemByUnidadMedida" ->
                new Gson().toJson(combosService.getItemByUnidadMedida());
            default ->
                "ERROR";
        };
    }

    @RequestMapping(value = "/TextoAjax")
    @ResponseBody
    public String getTexto(String mode, String codigo, Integer codigo2, String codigo3, String codigo4, String codigo5, String codigo6) {
        return switch (mode) {
            case "nroDocumento" ->
                new Gson().toJson(textoService.getNumeroDocumentoTipoDocumento(codigo, codigo2));
            case "verificaPin" ->
                new Gson().toJson(textoService.getVerificaPin(Utiles.getUsuario(), Encryptor.md5(codigo)));
            default ->
                "ERROR";
        };
    }
}
