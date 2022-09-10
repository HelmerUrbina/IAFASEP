
package com.iafasep.UserServices.Reportes;

import com.iafasep.Utiles.SevenZ;
import com.iafasep.Utiles.Utiles;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author helme
 */
@Controller
@Slf4j
public class RecibeDocFirmado {

    @RequestMapping(value = "/RecibeDocFirmado")
    @ResponseBody
    public String getRequestMapping(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("RECIBE DOCUMENTOS FIRMADOS");
        response.setCharacterEncoding("UTF-8");
        String serverpath = Utiles.getServerPath() + "\\FirmaDigital";
        String type = request.getParameter("type");
        String documentName = request.getParameter("documentName");
        String docAFirmar = request.getParameter("docAFirmar");
        String docId = request.getParameter("docId");
        String opcion = request.getParameter("opcion");
        try {
            serverpath += switch (opcion) {
                case "CP" ->
                    serverpath + "\\CertificadoPresupuestal\\";
                case "CA" ->
                    serverpath + "\\CompromisoAnual\\";
                case "DJ" ->
                    serverpath + "\\CompromisoMensual\\";
                case "NM" ->
                    serverpath + "\\NotaModificatoria\\";
                case "IDP" ->
                    serverpath + "\\InformeDisponibilidadPresupuestal\\";
                case "TramiteDocumentario" ->
                    serverpath + "\\TramiteDocumentario\\";
                default ->
                    Utiles.getServerPath();
            };
            String fileName = "";
            if (request.getParts().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
                return "";
            }
            for (Part part : request.getParts()) {
                fileName = getFileName(part);
                File archivo = new File(serverpath + File.separator + fileName);
                if (archivo.exists()) {
                    archivo.delete();
                }
                if (!fileName.equals("unnamed")) {
                    if (fileName.contains(".7z")) {
                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        fileName = "tmp" + sdf1.format(timestamp) + ".7z";
                        part.write(serverpath + File.separator + fileName);
                    } else {
                        part.write(serverpath + File.separator + fileName);
                    }
                }
            }
            response.setStatus(HttpServletResponse.SC_OK);
            //----------descomprime en caso sea zip---
            if (fileName.contains(".7z")) {
                boolean isOk = SevenZ.decompress(serverpath + File.separator + fileName, new File(serverpath));
                File archivo = new File(serverpath + File.separator + fileName);
                if (archivo.exists()) {
                    archivo.delete();
                }
            }
        } catch (IOException er) {
            System.out.println("Error al Descargar RENIEC" + er.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return "";
    }

    private String getFileName(Part part) {
        String filename = "unnamed";
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                filename = content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return filename;
    }
}
