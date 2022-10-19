package com.iafasep.Utiles;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Controller
@Slf4j
public class ReFirmaInvokerController {

    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "/ReFirmaInvoker")
    @ResponseBody
    public void getRequestMapping(HttpServletRequest request, HttpServletResponse response, String mode, String periodo, String opcion) throws IOException {
        /*Inicia Refirma Invoker*/
        String pathServlet = request.getServletPath();
        String fullPathServlet = request.getRequestURL().toString();
        int resInt = fullPathServlet.length() - pathServlet.length();
        String serverPath = fullPathServlet.substring(0, resInt + 1);
        if (!serverPath.contains("localhost")) {
            // EN PRODUCCIÓN: define el protocolo.
            serverPath = Utiles.getParametrosReFirma("hostName") + "://" + serverPath.replace("http://", "").replace("https://", "");
        }
        PrintWriter writer = response.getWriter();
        try {
            String type = request.getParameter("type");
            String docAFirmar = request.getParameter("docAFirmar");
            String documentName = request.getParameter("documentName");
            String docId = request.getParameter("docId");
            opcion = request.getParameter("opcion");
            String posxy = request.getParameter("posxy") == null ? "" : request.getParameter("posxy");
            String razon = "Soy el autor del documento";
            String arguments = "";
            String protocol = "";
            if (serverPath.contains("https://")) {
                protocol = "S";
            } else {
                protocol = "T";
            }
            if (type.equals("W")) {
                arguments = paramWeb(protocol, serverPath, docId, docAFirmar, documentName, posxy, razon, opcion);
            }
            writer.write(arguments);
            writer.close();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    public String paramWeb(String protocol, String serverPath, String docId, String docAFirmar, String documentName, String posxy, String razon, String opcion) {
        String param = "";
        ObjectMapper mapper = new ObjectMapper();
        //Java to JSON
        try {
            String x = "410", y = "10";
            if (!posxy.equals("")) {
                String arr[] = posxy.split("-");
                x = arr[0];
                y = arr[1];
            }
            Map<String, String> map = new HashMap<>();
            map.put("app", "pdf");
            map.put("clientId", Utiles.getParametrosReFirma("clientId"));
            map.put("clientSecret", Utiles.getParametrosReFirma("clientSecret"));
            map.put("idFile", docId);
            map.put("type", "W");
            map.put("protocol", protocol);
            map.put("fileDownloadUrl", serverPath + "Download?opcion=" + opcion + "&filename=" + docAFirmar);
            map.put("fileDownloadLogoUrl", serverPath + "images/Logos/logo.jpg");
            map.put("fileDownloadStampUrl", serverPath + "images/Logos/logoPeru.png");
            map.put("fileUploadUrl", serverPath + "ReFirmaInvokerUpload?opcion=" + opcion);
            map.put("contentFile", opcion);
            map.put("reason", razon);
            map.put("isSignatureVisible", "true");
            map.put("stampAppearanceId", "0");
            map.put("pageNumber", "0");
            map.put("posx", x);
            map.put("posy", y);
            map.put("fontSize", "7");
            map.put("dcfilter", ".*FIR.*|.*FAU.*");
            map.put("timestamp", "false");
            map.put("signatureLevel", "0");
            map.put("outputFile", documentName);
            map.put("maxFileSize", "5242880");
            //JSON
            param = mapper.writeValueAsString(map);
            //Base64 (JAVA 8)
            param = Base64.getEncoder().encodeToString(param.getBytes(StandardCharsets.UTF_8));
        } catch (JsonProcessingException ex) {
        }
        return param;
    }

}
