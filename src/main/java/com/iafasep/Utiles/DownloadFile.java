package com.iafasep.Utiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Controller
@Slf4j
public class DownloadFile {

    private static final long serialVersionUID = 4440011247408877539L;
    private String filePath;

    @RequestMapping("/Download")
    public void generateDescarga(HttpServletResponse response, String opcion, String filename) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        String extension = filename.substring(filename.lastIndexOf("."), filename.length());
        extension = extension.toLowerCase();
        filePath = switch (opcion) {
            case "MesaPartes" ->
                Utiles.getServerPath() + "MesaPartes\\" + filename;
            case "RemisionDocumento" ->
                Utiles.getServerPath() + "RemisionDocumento\\" + filename;
            case "TramiteDocumentario" ->
                Utiles.getServerPath() + "TramiteDocumentario\\" + filename;
            case "TramiteDocumentarioFirmado" ->
                Utiles.getServerPath() + "FirmaDigital\\TramiteDocumentario\\" + filename;
            case "CertificadoPresupuestal" ->
                Utiles.getServerPath() + "Presupuesto\\CertificadoPresupuestal\\" + filename;
            case "CP" ->
                Utiles.getServerPath() + "FirmaDigital\\Temporal\\CertificadoPresupuestal\\" + filename;
            default ->
                "";
        };
        File file = new File(filePath);
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
        switch (extension) {
            case ".rtf" -> {
                response.setContentType("text/html;charset=UTF-8");
                response.setContentType("application/msword");
                response.setHeader("Content-disposition", "inline; filename=" + file.getName());
            }
            case ".pdf" -> {
                response.setHeader("Content-Length", String.valueOf(file.length()));
                response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
            }
            case ".7z" -> {
                response.setHeader("Content-Length", String.valueOf(file.length()));
                response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
            }
            default -> {
            }
        }
        try ( ServletOutputStream out = response.getOutputStream()) {
            Files.copy(file.toPath(), out);
            out.flush();
        }
    }

}
