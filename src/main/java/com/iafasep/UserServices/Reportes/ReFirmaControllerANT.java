package com.iafasep.UserServices.Reportes;

import com.iafasep.Utiles.Utiles;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Controller
@Slf4j
public class ReFirmaControllerANT {

    String CLIENTID = Utiles.getParametrosReFirma("clientId");
    String CLIENTSECRET = Utiles.getParametrosReFirma("clientSecret");
    String SERVER_PATH = Utiles.getParametrosReFirma("hostName");
    // private static final long serialVersionUID = 1L;

    @RequestMapping(value = "/ReFirmaControlleraaa")
    @ResponseBody
    public String getRequestMapping(HttpServletRequest request, HttpServletResponse response, String mode, String periodo, String opcion) throws IOException {
        System.out.println("INGRESO ");

        String pathServlet = request.getServletPath();
        System.out.println(" Path server " + pathServlet);
        String fullPathServlet = request.getRequestURL().toString();
        System.out.println("full Path server " + fullPathServlet);
        int resInt = fullPathServlet.length() - pathServlet.length();
        String serverPath = fullPathServlet.substring(0, resInt + 1);
        System.out.println("server Path " + serverPath);
        /*if (!serverPath.contains("localhost")) {
            // EN PRODUCCIÓN: config.getProtocol() define el protocolo.
            serverPath = config.getProtocol() + "://" + serverPath.replace("http://", "").replace("https://", "");
        }*/
        String arguments = "";

        //String type = request.getParameter("type").toString();
        String type = request.getParameter("type");
        String documentName = request.getParameter("documentName");
        String docAFirmar = request.getParameter("docAFirmar");
        String docId = request.getParameter("docId");
        opcion = request.getParameter("opcion");
        System.out.println(type + "+++" + opcion + " +++ " + docAFirmar + " +++ " + docId + " +++ " + documentName);
        String posxy = request.getParameter("posxy") == null ? "" : request.getParameter("posxy");
        String razon = "Soy el autor del documento";

        String protocol = "";
        if (serverPath.contains("https://")) {
            protocol = "S";
        } else {
            protocol = "T";
        }

        arguments = paramWeb(protocol, type, documentName, opcion, docAFirmar, docId, posxy, razon);

        return arguments;
        /*
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String cadena = "";

        // try ( PrintWriter out = response.getWriter()) {
        String metodo = request.getParameter("method");
        if (metodo.equals("getArguments")) {//devuelve nombre de archivo ejm: 1s2ds1df.pdf
            cadena = shuffle("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
            cadena = cadena.substring(0, 40) + ".PDF";
        }
        if (metodo.equals("postArguments")) {
            String type = request.getParameter("type");
            String documentName = request.getParameter("documentName");
            String docAFirmar = request.getParameter("docAFirmar");
            String docId = request.getParameter("docId");
            opcion = request.getParameter("opcion");
            System.out.println(type + "+++" + opcion + " +++ " + docAFirmar + " +++ " + docId + " +++ " + documentName);
            String posxy = request.getParameter("posxy") == null ? "" : request.getParameter("posxy");
            String razon = "Soy el autor del documento";

            cadena = getParams(type, documentName, opcion, docAFirmar, docId, posxy, razon);
            System.out.println(cadena);
        }
        //out.println(cadena);
        return cadena;
        //}*/
    }

    public String paramWeb(String protocol, String type, String documentName, String opcion, String docAFirmar, String docId, String posxy, String razon) throws JsonProcessingException, UnsupportedEncodingException {
        String param = "";
        ObjectMapper mapper = new ObjectMapper();
        //Java to JSON

        String x = "20", y = "700";
        if (!posxy.equals("")) {
            String arr[] = posxy.split("-");
            x = arr[0];
            y = arr[1];
        }
        Map<String, String> map = new HashMap<>();
        map.put("app", "pdf");
        map.put("clientId", CLIENTID);
        map.put("clientSecret", CLIENTSECRET);
        map.put("idFile", docId);
        map.put("type", "W");
        map.put("protocol", protocol);
        map.put("fileDownloadUrl", SERVER_PATH + "Download?opcion=" + opcion + "&filename=" + docAFirmar);
        map.put("fileDownloadLogoUrl", SERVER_PATH + "images/Logos/logo.jpg");
        map.put("fileDownloadStampUrl", SERVER_PATH + "images/Logos/logoPeru.png");
        map.put("fileUploadUrl", SERVER_PATH + "RecibeDocFirmado?opcion=" + opcion);
        map.put("contentFile", "demo.pdf");
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
        System.out.println(param);
        //Base64 (JAVA 8)
        param = new String(Base64.encodeBase64(param.getBytes(StandardCharsets.UTF_8)));
        //param = Base64.getEncoder().encodeToString(param.getBytes(StandardCharsets.UTF_8));
        System.out.println(param);

        return param;
    }

    public String getParams(String type, String documentName, String opcion, String docAFirmar, String docId, String posxy, String razon) {

        String strBase64 = "";
        try {
            switch (type) {
                case "W" -> {
                    String param = "{";
                    param += " \"app\":\"pdf\",";
                    param += " \"fileUploadUrl\":\"" + SERVER_PATH + "/RecibeDocFirmado?opcion=" + opcion + "\",";
                    param += " \"reason\":\"" + razon + "\",";
                    param += " \"type\":\"W\",";
                    param += " \"clientId\":\"" + CLIENTID + "\",";
                    param += " \"clientSecret\":\"" + CLIENTSECRET + "\",";
                    param += " \"dcfilter\":\".*FIR.*|.*FAU.*\",";
                    param += " \"fileDownloadUrl\":\"" + SERVER_PATH + "/Download?opcion=" + opcion + "&filename=" + docAFirmar + "\",";
                    param += " \"posx\":\"150\",";
                    param += " \"posy\":\"650\",";
                    param += " \"outputFile\":\"" + documentName + "\",";
                    param += " \"protocol\":\"T\",";
                    param += " \"contentFile\":\"demo.pdf\",";
                    param += " \"stampAppearanceId\":\"0\",";
                    param += " \"isSignatureVisible\":\"true\",";
                    param += " \"idFile\":\"" + docId + "\",";
                    param += " \"fileDownloadLogoUrl\":\"" + SERVER_PATH + "/images/Logos/logo.png\",";
                    param += " \"fileDownloadStampUrl\":\"" + SERVER_PATH + "/images/Logos/logoEjercito.png\",";
                    param += " \"pageNumber\":\"0\",";
                    param += " \"maxFileSize\":\"5242880\",";//the request was rejected because its size (107060) exceds the configured maximum(2024)
                    param += " \"fontSize\":\"8\",";
                    param += " \"timestamp\":\"false\"";
                    param += " }";
                    strBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
                }
                case "W7z" -> {
                    String param = "{";
                    param += " \"app\":\"pcx\",";
                    param += " \"mode\":\"lot-p\",";
                    param += " \"clientId\":\"" + CLIENTID + "\",";
                    param += " \"clientSecret\":\"" + CLIENTSECRET + "\",";
                    param += " \"idFile\":\"" + docId + "\",";
                    param += " \"type\":\"W\",";
                    param += " \"protocol\":\"T\",";
                    param += " \"fileDownloadUrl\":\"" + SERVER_PATH + "/Download/" + docAFirmar + "\",";
                    param += " \"fileDownloadLogoUrl\":\"" + SERVER_PATH + "/Imagenes/Logos/logo-ejercito.png\",";
                    param += " \"fileDownloadStampUrl\":\"" + SERVER_PATH + "/Imagenes/Logos/escudoOpreFirma.png\",";
                    param += " \"fileUploadUrl\":\"" + SERVER_PATH + "/RecibeDocFirmado?opcion=" + opcion + "\",";
                    param += " \"outputFile\":\"" + documentName + "\",";
                    param += " \"contentFile\":\"documentoFirmados.7z\",";
                    param += " \"reason\":\"" + razon + "\",";
                    param += " \"isSignatureVisible\":\"true\",";
                    param += " \"stampAppearanceId\":\"0\",";
                    param += " \"pageNumber\":\"0\",";
                    param += " \"posx\":\"150\",";
                    param += " \"posy\":\"650\",";
                    param += " \"fontSize\":\"7\",";
                    param += " \"dcfilter\":\".*FIR.*|.*FAU.*\",";
                    param += " \"signatureLevel\":\"0\",";
                    param += " \"maxFileSize\":\"5242880\"";
                    param += " }";
                    strBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
                } //==================para la ejecucion presupuestal===========
                case "ep-pdf" -> {
                    String x = "20", y = "700";
                    if (!posxy.equals("")) {
                        String arr[] = posxy.split("-");
                        x = arr[0];
                        y = arr[1];
                    }
                    String param = "{";
                    param += " \"app\":\"pdf\",";
                    param += " \"clientId\":\"" + CLIENTID + "\",";
                    param += " \"clientSecret\":\"" + CLIENTSECRET + "\",";
                    param += " \"idFile\":\"" + docId + "\",";
                    param += " \"type\":\"W\",";
                    param += " \"protocol\":\"T\",";
                    param += " \"fileDownloadUrl\":\"" + SERVER_PATH + "Download?opcion=" + opcion + "&filename=" + docAFirmar + "\",";
                    param += " \"fileDownloadLogoUrl\":\"" + SERVER_PATH + "images/Logos/logo.jpg\",";
                    param += " \"fileDownloadStampUrl\":\"" + SERVER_PATH + "images/Logos/logoPeru.png\",";
                    param += " \"fileUploadUrl\":\"" + SERVER_PATH + "RecibeDocFirmado?opcion=" + opcion + "\",";
                    param += " \"contentFile\":\"demo.pdf\",";
                    param += " \"reason\":\"" + razon + "\",";
                    param += " \"isSignatureVisible\":\"true\",";
                    param += " \"stampAppearanceId\":\"0\",";
                    param += " \"pageNumber\":\"0\",";
                    param += " \"posx\":\"" + x + "\",";
                    param += " \"posy\":\"" + y + "\",";
                    param += " \"fontSize\":\"7\",";
                    param += " \"dcfilter\":\".*FIR.*|.*FAU.*\",";
                    param += " \"timestamp\":\"false\",";
                    param += " \"signatureLevel\":\"0\",";
                    param += " \"outputFile\":\"" + documentName + "\",";
                    param += " \"maxFileSize\":\"5242880\"";
                    param += " }";
                    System.out.println("archivo " + param);
                    strBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
                }
                case "ep-7z" -> {
                    String x = "20", y = "700";
                    if (!posxy.equals("")) {
                        String arr[] = posxy.split("-");
                        x = arr[0];
                        y = arr[1];
                    }
                    String param = "{";
                    param += " \"app\":\"pcx\",";
                    param += " \"mode\":\"lot-p\",";
                    param += " \"clientId\":\"" + CLIENTID + "\",";
                    param += " \"clientSecret\":\"" + CLIENTSECRET + "\",";
                    param += " \"idFile\":\"" + docId + "\",";
                    param += " \"type\":\"W\",";
                    param += " \"protocol\":\"T\",";
                    param += " \"fileDownloadUrl\":\"" + SERVER_PATH + "Download?opcion=" + opcion + "&filename=" + docAFirmar + "\",";
                    param += " \"fileDownloadLogoUrl\":\"" + SERVER_PATH + "images/Logos/logo.jpg\",";
                    param += " \"fileDownloadStampUrl\":\"" + SERVER_PATH + "images/Logos/logoPeru.png\",";
                    param += " \"fileUploadUrl\":\"" + SERVER_PATH + "RecibeDocFirmado?opcion=" + opcion + "\",";
                    param += " \"contentFile\":\"documentoFirmados.7z\",";
                    param += " \"reason\":\"" + razon + "\",";
                    param += " \"isSignatureVisible\":\"true\",";
                    param += " \"stampAppearanceId\":\"0\",";
                    param += " \"pageNumber\":\"0\",";
                    param += " \"posx\":\"" + x + "\",";
                    param += " \"posy\":\"" + y + "\",";
                    param += " \"fontSize\":\"7\",";
                    param += " \"dcfilter\":\".*FIR.*|.*FAU.*\",";
                    param += " \"signatureLevel\":\"0\",";
                    param += " \"maxFileSize\":\"5242880\"";
                    param += " }";
                    System.out.println("archivo " + param);
                    strBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
                }
                default -> {
                }
            }
        } catch (UnsupportedEncodingException err) {
        }
        return strBase64;
    }

    public String shuffle(String input) {
        List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while (!characters.isEmpty()) {
            int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }

}
