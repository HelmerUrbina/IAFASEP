package com.iafasep.UserServices.Presupuesto;

import com.google.gson.Gson;
import com.iafasep.BusinessServices.Beans.BeanCertificadoPresupuestal;
import com.iafasep.DataService.Service.CertificadoPresupuestalService;
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
 * @author helme
 */
@Controller
@Slf4j
public class CertificadoPresupuestalController {

    @Autowired
    private CertificadoPresupuestalService certificadoService;

    @RequestMapping(value = "/CertificadoPresupuestal")
    @ResponseBody
    public String getRequestMapping(String mode, String periodo, Integer fuenteFinanciamiento, Integer codigo) {
        return switch (mode) {
            case "G" ->
                new Gson().toJson(certificadoService.getListaCertificadoPresupuestal(periodo, fuenteFinanciamiento));
            case "GD" ->
                new Gson().toJson(certificadoService.getListaCertificadoPresupuestalDetalle(periodo, fuenteFinanciamiento));
            case "GC" ->
                new Gson().toJson(certificadoService.getCertificadoPresupuestalDetalle(periodo, fuenteFinanciamiento, codigo));
            case "N" ->
                new Gson().toJson(certificadoService.getNroCertificadoPresupuestal(periodo));
            case "U" ->
                new Gson().toJson(certificadoService.getCertificadoPresupuestal(periodo, codigo));
            default ->
                "ERROR";
        };
    }

    @RequestMapping(value = "/IduCertificadoPresupuestal")
    @ResponseBody
    public String setRequestMapping(
            @RequestParam("mode") String mode,
            @RequestParam("periodo") String periodo,
            @RequestParam("fuenteFinanciamiento") Integer fuenteFinanciamiento,
            @RequestParam("certificado") Integer certificado,
            @RequestParam("tipoSolicitud") String tipoSolicitud,
            @RequestParam("anexoCertificado") Integer anexoCertificado,
            @RequestParam("pacProcesos") String pacProcesos,
            @RequestParam("documento") String documento,
            @RequestParam("concepto") String concepto,
            @RequestParam("observacion") String observacion,
            @RequestParam("fecha") String fecha,
            @RequestParam("tipoMoneda") String tipoMoneda,
            @RequestParam("tipoCambio") Double tipoCambio,
            @RequestParam("detalle") String detalle
    ) {
        BeanCertificadoPresupuestal objBeanCertificado = new BeanCertificadoPresupuestal();
        objBeanCertificado.setPeriodo(periodo);
        objBeanCertificado.setFuenteFinanciamiento(fuenteFinanciamiento);
        objBeanCertificado.setCertificado(certificado);
        objBeanCertificado.setTipo(tipoSolicitud);
        objBeanCertificado.setAnexo(anexoCertificado);
        objBeanCertificado.setPacProcesos(pacProcesos);
        objBeanCertificado.setDocumento(documento);
        objBeanCertificado.setConcepto(concepto);
        objBeanCertificado.setObservacion(observacion);
        objBeanCertificado.setFecha(fecha);
        objBeanCertificado.setTipoMoneda(tipoMoneda);
        objBeanCertificado.setTipoCambio(tipoCambio);
        objBeanCertificado.setEstado(detalle);
        return "" + certificadoService.guardarCertificadoPresupuestal(objBeanCertificado, Utiles.getUsuario(), mode);
    }

    @RequestMapping(value = "/IduCertificadoPresupuestalCerrar")
    @ResponseBody
    public String setRequestMappingCerrar(
            @RequestParam("mode") String mode,
            @RequestParam("periodo") String periodo,
            @RequestParam("fuenteFinanciamiento") Integer fuenteFinanciamiento,
            @RequestParam("certificado") Integer certificado,
            @RequestParam("txt_Archivo") MultipartFile file
    ) {
        BeanCertificadoPresupuestal objBeanCertificado = new BeanCertificadoPresupuestal();
        objBeanCertificado.setPeriodo(periodo);
        objBeanCertificado.setFuenteFinanciamiento(fuenteFinanciamiento);
        objBeanCertificado.setCertificado(certificado);
        objBeanCertificado.setDocumento(file.getOriginalFilename());
        return "" + certificadoService.guardarCerrarCertificadoPresupuestal(objBeanCertificado, file, Utiles.getUsuario(), mode);
    }

}
