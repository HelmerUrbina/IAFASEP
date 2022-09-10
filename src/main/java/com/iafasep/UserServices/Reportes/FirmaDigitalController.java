package com.iafasep.UserServices.Reportes;

import com.google.gson.Gson;
import com.iafasep.DataService.Service.FirmaDigitalService;
import com.iafasep.Utiles.Reportes;
import com.iafasep.Utiles.Utiles;
import java.io.IOException;
import java.sql.SQLException;
import java.util.StringTokenizer;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Controller
@Slf4j
public class FirmaDigitalController {

    @Autowired
    private FirmaDigitalService firmaDigitalService;

    @Autowired
    private Reportes reportes;

    @RequestMapping(value = "/FirmaDigital")
    @ResponseBody
    public String getRequestMapping(String mode, String codigo, String codigo1, String codigo2) {
        return switch (mode) {
            /*CONFIGURACION*/
            case "CP" ->
                new Gson().toJson(firmaDigitalService.getCertificadoPresupuestal(codigo, codigo1, codigo2));
            default ->
                "ERROR";
        };
    }

    @GetMapping("/GenerarArchivoRefirma")
    @ResponseBody
    public String generarArchivoAFirmar(String mode, String periodo, String fuenteFinanciamiento, String tipoReporte, String items, String reporte) throws IOException, JRException, SQLException {
        String itemsFileName = "";
        StringTokenizer tok = new StringTokenizer(items, "|");
        while (tok.hasMoreTokens()) {
            String row[] = tok.nextToken().split("-");
            String codigo = row[0];
            String cfirma_estado = row[1];
            switch (tipoReporte) {
                case "CP" -> {
                    if (cfirma_estado.equals("PE")) {
                        itemsFileName += Utiles.getSeparador(itemsFileName) + reportes.generateReportPDF(reporte, periodo, "", "", fuenteFinanciamiento, "", codigo, tipoReporte, "");
                    } else {
                        itemsFileName += Utiles.getSeparador(itemsFileName) + "EP" + periodo + "-" + fuenteFinanciamiento + "-" + codigo + "SIGNED.pdf";
                    }
                }
                case "CA" -> {
                    if (cfirma_estado.equals("PE")) {
                        itemsFileName += Utiles.getSeparador(itemsFileName) + reportes.generateReportPDF(reporte, periodo, "", "", fuenteFinanciamiento, "", codigo, tipoReporte, "");
                    } else {
                        itemsFileName += Utiles.getSeparador(itemsFileName) + "EP" + periodo + "-" + fuenteFinanciamiento + "-" + codigo + "SIGNED.pdf";
                    }
                }
                case "DJ" -> {
                    if (cfirma_estado.equals("PE")) {
                        itemsFileName += Utiles.getSeparador(itemsFileName) + reportes.generateReportPDF(reporte, periodo, "", "", fuenteFinanciamiento, "", codigo, tipoReporte, "");
                    } else {
                        itemsFileName += Utiles.getSeparador(itemsFileName) + "EP" + periodo + "-" + fuenteFinanciamiento + "-" + codigo + "SIGNED.pdf";
                    }
                }
                case "IDP" -> {
                    if (cfirma_estado.equals("PE")) {
                        itemsFileName += Utiles.getSeparador(itemsFileName) + reportes.generateReportPDF(reporte, periodo, "", "", fuenteFinanciamiento, "", codigo, tipoReporte, "");
                    } else {
                        itemsFileName += Utiles.getSeparador(itemsFileName) + "EP" + periodo + "-" + fuenteFinanciamiento + codigo + "SIGNED.pdf";
                    }
                }
                case "NM" -> {
                    if (cfirma_estado.equals("PE")) {
                        itemsFileName += Utiles.getSeparador(itemsFileName) + reportes.generateReportPDF(reporte, periodo, "", "", fuenteFinanciamiento, "", codigo, tipoReporte, "");
                    } else {
                        itemsFileName += Utiles.getSeparador(itemsFileName) + "EP" + periodo + "-" + fuenteFinanciamiento + "-" + codigo + "SIGNED.pdf";
                    }
                }
                default -> {
                }
            }
        }
        if (itemsFileName.contains("|")) {
            itemsFileName = Utiles.comprimirDocumentos(itemsFileName, tipoReporte);
        }
        return "{\"result\":\"1\",\"fileNameToSig\":\"" + itemsFileName + "\"}";
    }

}
