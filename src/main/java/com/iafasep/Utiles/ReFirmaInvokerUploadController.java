package com.iafasep.Utiles;

import java.io.File;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Controller
@Slf4j
public class ReFirmaInvokerUploadController {

    private static final long serialVersionUID = 1L;
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; 	// MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 100; // MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 110; // MB 
    private static final String filePath = Utiles.getServerPath() + "FirmaDigital";

    @RequestMapping(value = "/ReFirmaInvokerUpload")
    @ResponseBody
    public void getRequestMapping(HttpServletRequest request, HttpServletResponse response, String opcion, MultipartFile[] files) {
        System.out.println("INGRESO Upload");
        /*Inicia Refirma Invoker*/
        String pathServlet = request.getServletPath();
        String fullPathServlet = request.getRequestURL().toString();
        int resInt = fullPathServlet.length() - pathServlet.length();
        String serverPath = fullPathServlet.substring(0, resInt + 1);
        System.out.println("filePath : " + filePath);
        System.out.println("Servlet Path : " + serverPath);
        System.out.println("OPCION : " + opcion);
        String serverpath = "";
        String fileName = "";
        try {
            serverpath = switch (opcion) {
                case "CP" ->
                    filePath + "\\CertificadoPresupuestal";
                case "CA" ->
                    filePath + "\\CompromisoAnual";
                case "DJ" ->
                    filePath + "\\CompromisoMensual";
                case "NM" ->
                    filePath + "\\NotaModificatoria";
                case "IDP" ->
                    filePath + "\\InformeDisponibilidadPresupuestal";
                case "TramiteDocumentario" ->
                    filePath + "\\TramiteDocumentario";
                default ->
                    filePath;
            };
            System.out.println("serverpath : " + serverpath);
            if (!ServletFileUpload.isMultipartContent(request)) {
                response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
                return;
            }
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(THRESHOLD_SIZE);
            factory.setRepository(new File(serverpath));
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);
            File uploadDir = new File(serverpath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            System.out.println("FILEs");    

            if (request.getParts().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);

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

            List<FileItem> formItems = upload.parseRequest(request);
            System.out.println("formItems" + formItems.size());
            Iterator<FileItem> iter = formItems.iterator();
            System.out.println("" + iter.toString());
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (!item.isFormField()) {
                    System.out.println("item " + item);
                    System.out.println("contu type " + item.getContentType());
                    System.out.println("Field Name " + item.getFieldName());
                    String idFile = item.getFieldName();//idFile asignado en los argumentos, se puede utilizar como un id.
                    System.out.println("idFile : " + idFile);
                    fileName = URLDecoder.decode(item.getName(), "UTF-8");
                    System.out.println("fileName : " + fileName);
                    if (fileName.contains(".7z")) {
                        System.out.println("INGRESO zip");
                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        fileName = "tmp" + sdf1.format(timestamp) + ".7z";
                    }
                    File storeFile = new File(serverpath + File.separator + fileName);
                    if (storeFile.exists()) {
                        storeFile.delete();
                    }
                    item.write(storeFile);
                }
            }
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private String getFileName(Part part) {
        String filename = "unnamed";
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                System.out.println("content" + content);
                filename = content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return filename;
    }

}
