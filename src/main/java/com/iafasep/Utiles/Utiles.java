package com.iafasep.Utiles;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javax.servlet.http.Part;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
public class Utiles {

    public static String checkStr(String dato) {
        if (dato == null) {
            return "";
        }
        return dato;
    }

    public static Integer checkNum(String dato) {
        if (dato == null || dato.equals("null") || dato.equals("false")) {
            return 0;
        }
        if (dato.equals("true")) {
            return 1;
        }
        if (dato.equals("")) {
            return 0;
        }
        dato = dato.replace((char) 127, (char) 48);
        return Integer.valueOf(dato.trim());
    }

    public static String CompletarCeros(String dato, int tamaño) {
        for (int i = tamaño; i > dato.length(); i--) {
            dato = '0' + dato;
        }
        return dato;
    }

    public static double checkDouble(String dato) {
        if (dato == null) {
            return 0.00;
        }
        if (dato.equals("")) {
            return 0.00;
        }
        return Double.parseDouble(dato);
    }

    public static String checkFecha(String dato) {
        if ("".equals(dato) || dato == null) {
            return fechaServidor();
        }
        return dato;
    }

    public static String fechaServidor() {
        java.util.Date fechaSistema = new java.util.Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(fechaSistema);
    }

    public static String[][] generaLista(String cadena, int tamaño) {
        String[][] matriz;
        String datos;
        cadena = cadena.replace("[", "");
        cadena = cadena.replace("]", "");
        List<String> vector = new ArrayList<>(Arrays.asList(cadena.split("\",\"")));
        matriz = new String[vector.size()][tamaño];
        for (int i = 0; i < vector.size(); i++) {
            datos = vector.get(i);
            datos = datos.replace((char) 34, (char) 0);
            List<String> arreglo = new ArrayList<>(Arrays.asList(datos.split("---")));
            for (int j = 0; j < tamaño; j++) {
                matriz[i][j] = arreglo.get(j);
            }
        }
        return matriz;
    }

    public static Boolean checkBoolean(String dato) {
        if (dato == null) {
            return false;
        }
        return dato.equals("true");
    }

    public static String getFileName(Part part) {
        String contentHeader = part.getHeader("content-disposition");
        String[] subHeaders = contentHeader.split(";");
        for (String current : subHeaders) {
            if (current.trim().startsWith("filename")) {
                int pos = current.indexOf('=');
                String fileName = current.substring(pos + 1);
                return fileName.replace("\"", "");
            }
        }
        return null;
    }

    public static String encriptarPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static Boolean validaPassword(String password, String passwordEncriptado) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, passwordEncriptado);
    }

    public static String getUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails userDetails1) {
            userDetails = userDetails1;
        }
        return userDetails.getUsername();
    }

    public static String getErrorSQL(Exception e) {
        String msj = null;
        if (e != null) {
            msj = e.getCause().getCause().getLocalizedMessage();
        }
        return "<span style='color: white;'>" + msj + "</span>";
    }

    public static String getServerPath() {
        String serverDocs = "";
        try {
            ResourceBundle res = ResourceBundle.getBundle("configuration");
            serverDocs = res.getObject("server").toString();
        } catch (Exception er) {
        }
        return serverDocs;
    }

    public static String getParametrosReFirma(String opcion) {
        String serverDocs = "";
        try {
            ResourceBundle res = ResourceBundle.getBundle("configuration");
            switch (opcion) {
                case "clientId" ->
                    serverDocs = res.getObject("clientId").toString();
                case "clientSecret" ->
                    serverDocs = res.getObject("clientSecret").toString();
                case "hostName" ->
                    serverDocs = res.getObject("hostName").toString();
                default ->
                    serverDocs = "";
            }
        } catch (Exception er) {
        }
        return serverDocs;
    }

    public static String comprimirDocumentos(String itemNameDocs) {
        String json = "";
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String zipName = "tmp" + sdf1.format(timestamp) + ".7z";
            String server = getServerPath();
            List<File> listFiles = new ArrayList();
            StringTokenizer tok = new StringTokenizer(itemNameDocs, "|");
            String temporal = "";
            while (tok.hasMoreTokens()) {
                String key = tok.nextToken();
                if (key.contains("SIGNED")) {
                    key = key.replace("SIGNED", "");
                } else {
                    temporal = "si";
                }
                File resource1 = new File(server + "/" + key);
                listFiles.add(resource1);
            }
            SevenZ.compress(server + File.separator + zipName, listFiles);
            json = json = zipName;
            if (!temporal.equals("si")) {
                json = json + "SIGNED";
            }
        } catch (IOException ex) {
            json = "";
        }
        return json;
    }

    public static String getSeparador(String itemsFileName) {
        return itemsFileName = (itemsFileName.length() > 0 && !itemsFileName.substring(itemsFileName.length() - 1, itemsFileName.length()).equals("|")) ? "|" : "";
    }

    public static String comprimirDocumentos(String itemNameDocs, String tipoReporte) {
        String json = "";
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String zipName = "tmp" + sdf1.format(timestamp) + ".7z";
            String serverpath = getServerPath();
            List<File> listFiles = new ArrayList();
            StringTokenizer tok = new StringTokenizer(itemNameDocs, "|");
            String server = "";
            String temporal = "";
            while (tok.hasMoreTokens()) {
                String key = tok.nextToken();
                if (key.contains("SIGNED")) {//era:[R]
                    key = key.replace("SIGNED", "");
                    server = serverpath + "FirmaDigital";
                } else {
                    server = serverpath + "FirmaDigital\\Temporal";
                    temporal = "si";
                }
                switch (tipoReporte) {
                    case "CP" ->
                        server = server + "\\CertificadoPresupuestal";
                    case "CA" ->
                        server = server + "\\CompromisoAnual";
                    case "DJ" ->
                        server = server + "\\CompromisoMensual";
                    case "NM" ->
                        server = server + "\\NotaModificatoria";
                    case "IDP" ->
                        server = server + "\\InformeDisponibilidadPresupuestal";
                    default -> {
                    }
                }
                File resource1 = new File(server + "/" + key);
                listFiles.add(resource1);
            }
            SevenZ.compress(server + File.separator + zipName, listFiles);
            json = json = zipName;
            if (!temporal.equals("si")) {
                json = json + "SIGNED";
            }
        } catch (IOException ex) {
            System.out.println("" + ex.getMessage());
            json = "";
        }        
        return json;
    }

}
