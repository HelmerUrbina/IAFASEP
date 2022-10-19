package com.iafasep.DataService.Service.Impl;

import com.groupdocs.conversion.Converter;
import com.groupdocs.conversion.filetypes.FileType;
import com.groupdocs.conversion.options.convert.ConvertOptions;
import com.iafasep.DataService.Service.RemisionDocumentoService;
import com.iafasep.DataService.DAO.RemisionDocumentoDAO;
import com.iafasep.BusinessServices.Beans.BeanRemisionDocumento;
import com.iafasep.DataService.DAO.TextoDAO;
import com.iafasep.Utiles.UploadFiles;
import com.iafasep.Utiles.Utiles;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Service
public class RemisionDocumentoServiceImpl implements RemisionDocumentoService {

    @Autowired
    private RemisionDocumentoDAO remisionDocumentoDAO;

    @Autowired
    private TextoDAO textoDAO;

    @Override
    public List<BeanRemisionDocumento> getListaRemisionDocumento(String periodo, String mes, String usuario) {
        return remisionDocumentoDAO.findByPeriodoAndMesAndUsuario(periodo, mes, usuario);
    }

    @Override
    public List<BeanRemisionDocumento> getListaDocumentosElevados(String periodo, String usuario, String estado) {
        return remisionDocumentoDAO.findByPeriodoAndUsuarioAndEstado(periodo, usuario, estado);
    }

    @Override
    public BeanRemisionDocumento getDocumentosElevados(String periodo, Integer codigo) {
        return remisionDocumentoDAO.findByPeriodoAndCodigo(periodo, codigo);
    }

    @Override
    public String iduRemisionDocumento(BeanRemisionDocumento objBeanRemision, String usuario, String mode) {
        Integer remision = 0;
        String fileName = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false); //No Complaciente en Fecha
            java.util.Date fecha_util = sdf.parse(Utiles.checkFecha(objBeanRemision.getFecha()));
            // BeanRemisionDocumento dataOld = new BeanRemisionDocumento();
            if (mode.equals("I")) {
                objBeanRemision.setCodigo(remisionDocumentoDAO.getCodigoByPeriodo(objBeanRemision.getPeriodo()));
            } else {
                //  dataOld = remisionDocumentoDAO.findByPeriodoAndCodigo(objBeanRemision.getPeriodo(), objBeanRemision.getCodigo());
            }
            remision = remisionDocumentoDAO.sp_iduRemisionDocumento(
                    objBeanRemision.getPeriodo(),
                    objBeanRemision.getCodigo(),
                    objBeanRemision.getInstitucion(),
                    objBeanRemision.getTipoDocumento(),
                    objBeanRemision.getNumeroDocumento(),
                    objBeanRemision.getClasificacion(),
                    new java.sql.Date(fecha_util.getTime()),
                    objBeanRemision.getAsunto(),
                    objBeanRemision.getDirigido(),
                    objBeanRemision.getCargo(),
                    objBeanRemision.getArchivo(),
                    usuario,
                    mode);
            if (remision == 1) {
                String plantilla = "";
                String serverDocs = "D:\\IAFASEP\\RemisionDocumento";
                if (mode.equals("I")) { // en caso no existe data anterior creamos un nuevo doc
                    try ( BufferedReader in = new BufferedReader(new InputStreamReader(textoDAO.getPlantillaTipoDocumento(objBeanRemision.getPeriodo(), Utiles.checkNum(objBeanRemision.getTipoDocumento())).getBinaryStream()))) {
                        String linea = null;
                        StringBuilder sb = new StringBuilder();
                        while ((linea = in.readLine()) != null) {
                            sb.append(linea);
                        }
                        plantilla = sb.toString();
                    } catch (Exception e) {
                        return e.getMessage();
                    }
                    SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date dFecha = formato2.parse(objBeanRemision.getFecha());
                    SimpleDateFormat formatoEsMX = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("ES", "PE"));
                    String fechaDoc = formatoEsMX.format(dFecha);
                    fileName = objBeanRemision.getPeriodo() + "-" + objBeanRemision.getCodigo() + "-" + objBeanRemision.getCodigo() + ".rtf";
                    plantilla = plantilla.replace("<<numdoc>>", objBeanRemision.getNumeroDocumento() + "-" + objBeanRemision.getPeriodo() + "-" + objBeanRemision.getTipoDocumento());
                    plantilla = plantilla.replace("<<tipodoc>>", textoDAO.getTipoDocumento(Utiles.checkNum(objBeanRemision.getTipoDocumento())));
                    plantilla = plantilla.replace("<<asunto>>", objBeanRemision.getAsunto());
                    plantilla = plantilla.replace("<<fechadoc>>", fechaDoc);
                    plantilla = plantilla.replace("<<nombredirigidoa>>", objBeanRemision.getDirigido());
                    plantilla = plantilla.replace("<<cargo>>", objBeanRemision.getCargo());
                    try ( FileWriter myWriter = new FileWriter(serverDocs + "\\" + fileName)) {
                        myWriter.write(plantilla);
                    }
                } else if (mode.equals("U")) {//--------------- en caso si existe data anterior modificamos el doc
                    /*try {
                        File myObj = new File(serverDocs + "\\" + dataOld.getWord());
                        try ( Scanner myReader = new Scanner(myObj)) {
                            while (myReader.hasNextLine()) {
                                plantilla += myReader.nextLine();
                            }
                        }
                    } catch (FileNotFoundException e) {
                        return "A ocurrido un error " + e.getMessage();
                    }
                    if (!plantilla.equals("")) {
                        StringTokenizer tok = new StringTokenizer(
                                "fecha##" + dataOld.getFecha()
                                + "|dirigidoa##" + dataOld.getDirigido()
                                + "|cargo##" + dataOld.getCargo()
                                + "|asunto##" + dataOld.getAsunto(), "|");
                        while (tok.hasMoreTokens()) {
                            String row[] = tok.nextToken().split("##");
                            switch (row[0]) {*/
 /* case "fecha" -> {
                                    SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
                                    System.out.println("FECHA " + row[1]);
                                    java.util.Date dFecha = formato2.parse(row[1]);
                                    System.out.println("paso 2 ");
                                    SimpleDateFormat formatoEsMX = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("ES", "PE"));
                                    System.out.println("paso 3 ");
                                    String fechaDoc = formatoEsMX.format(dFecha);
                                    System.out.println("paso 4 ");
                                    formato2 = new SimpleDateFormat("dd/MM/yyyy");
                                    System.out.println("NEW FECHA" + objBeanRemision.getFecha());
                                    dFecha = formato2.parse(objBeanRemision.getFecha());
                                    System.out.println("paso 6 ");
                                    String fechaDocNew = formatoEsMX.format(dFecha);
                                    System.out.println("new fecha : " + fechaDocNew);
                                    plantilla = plantilla.replace(fechaDoc, fechaDocNew);
                                    System.out.println("reemplazo : " + fechaDoc + " " + fechaDocNew);
                                }*/
 /*case "dirigidoa" ->
                                    plantilla = plantilla.replaceAll(row[1], objBeanRemision.getDirigido());
                                case "cargo" ->
                                    plantilla = plantilla.replaceAll(row[1], objBeanRemision.getCargo());
                                case "asunto" ->
                                    plantilla = plantilla.replaceAll(row[1], objBeanRemision.getAsunto());
                                default -> {
                                }
                            }
                        }
                        try ( FileWriter myWriter = new FileWriter(serverDocs + "\\" + fileName)) {
                            myWriter.write(plantilla);
                        }
                    } else {
                        return "No existe Plantilla para este Tipo de Documento";
                    }*/
                }

            }
        } catch (Exception | Error e) {
            return Utiles.getErrorSQL((Exception) e);
        }
        return "" + objBeanRemision.getCodigo();
    }

    @Override
    public String iduRemisionDocumentoFirmado(BeanRemisionDocumento objBeanRemision, String usuario, String mode) {
        try {
            remisionDocumentoDAO.sp_iduRemisionDocumento(
                    objBeanRemision.getPeriodo(),
                    objBeanRemision.getCodigo(),
                    objBeanRemision.getInstitucion(),
                    objBeanRemision.getTipoDocumento(),
                    objBeanRemision.getNumeroDocumento(),
                    objBeanRemision.getClasificacion(),
                    new java.sql.Date(new Date().getTime()),
                    objBeanRemision.getAsunto(),
                    objBeanRemision.getDirigido(),
                    objBeanRemision.getCargo(),
                    objBeanRemision.getArchivo(),
                    usuario,
                    mode);
        } catch (Exception | Error e) {
            return Utiles.getErrorSQL((Exception) e);
        }
        return "GUARDO" ;
    }

    @Override
    public String iduRemisionDocumentoAdjuntar(BeanRemisionDocumento objBeanRemision, MultipartFile file, String usuario, String mode) {
        String result = "GUARDO";
        UploadFiles UploadFiles = new UploadFiles();
        try {
            remisionDocumentoDAO.sp_iduRemisionDocumento(
                    objBeanRemision.getPeriodo(),
                    objBeanRemision.getCodigo(),
                    objBeanRemision.getInstitucion(),
                    objBeanRemision.getTipoDocumento(),
                    objBeanRemision.getNumeroDocumento(),
                    objBeanRemision.getClasificacion(),
                    new java.sql.Date(new Date().getTime()),
                    objBeanRemision.getAsunto(),
                    objBeanRemision.getDirigido(),
                    objBeanRemision.getCargo(),
                    objBeanRemision.getArchivo(),
                    usuario,
                    mode);
            UploadFiles.RemisionDocumentario(objBeanRemision.getPeriodo() + "-" + objBeanRemision.getCodigo(), file);
        } catch (IOException ex) {
            result = Utiles.getErrorSQL((Exception) ex);
        }
        return result;
    }

    @Override
    public String iduElevarDocumento(BeanRemisionDocumento objBeanRemision, MultipartFile[] files,
            String usuario, String mode
    ) {
        Integer elevacion = 0;
        if (mode.equals("I")) {
            objBeanRemision.setElevacion(remisionDocumentoDAO.getCodigoEvelarByPeriodoAndRemisionDocumento(objBeanRemision.getPeriodo(), objBeanRemision.getCodigo()));
        }
        objBeanRemision.setArchivo(remisionDocumentoDAO.getRemisionDocumentoArchivo(objBeanRemision.getPeriodo(), objBeanRemision.getCodigo()));
        elevacion = remisionDocumentoDAO.sp_iduElevarDocumento(
                objBeanRemision.getPeriodo(),
                objBeanRemision.getCodigo(),
                objBeanRemision.getElevacion(),
                objBeanRemision.getAreaLaboral(),
                objBeanRemision.getDirigido(),
                usuario,
                mode);
        if (elevacion == 1) {
            InputStream is = null;
            String serverDocs = "D:\\IAFASEP\\RemisionDocumento\\";
            String destino = "D:\\IAFASEP\\TramiteDocumentario\\";
            UploadFiles UploadFiles = new UploadFiles();
            try {
                is = textoDAO.getUsuarioSello(Utiles.getUsuario()).getBinaryStream();
                if (is != null) {
                    String filenamePdf = "";
                    if (objBeanRemision.getElevacion() == 1) {
                        filenamePdf = objBeanRemision.getArchivo().substring(0, objBeanRemision.getArchivo().indexOf(FilenameUtils.getExtension(objBeanRemision.getArchivo()))) + "pdf";
                        //--------convertimos el rtf a pdf-------------

                        Converter converter = new Converter(serverDocs + objBeanRemision.getPeriodo() + "-" + objBeanRemision.getCodigo() + "-" + objBeanRemision.getArchivo());
                        ConvertOptions convertOptions = FileType.fromExtension("pdf").getConvertOptions();
                        converter.convert(destino + objBeanRemision.getPeriodo() + "-" + objBeanRemision.getCodigo() + "-" + filenamePdf, convertOptions);
                        remisionDocumentoDAO.sp_updRemisionDocumentoDigital(objBeanRemision.getPeriodo(), objBeanRemision.getCodigo(), filenamePdf);
                    } else {
                        filenamePdf = objBeanRemision.getArchivo();
                    }
                    //---------------obtiene sello para pdf----
                    byte[] targetArray = IOUtils.toByteArray(is);
                    //---------------insert image in pdf----
                    File file = new File(destino + "\\" + objBeanRemision.getPeriodo() + "-" + objBeanRemision.getCodigo() + "-" + filenamePdf);
                    try ( PDDocument doc = PDDocument.load(file)) {
                        PDPage page = doc.getPage(0);
                        PDImageXObject pdImage = PDImageXObject.createFromByteArray(doc, targetArray, "firma");
                        try ( PDPageContentStream contents = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, false)) {
                            contents.drawImage(pdImage, 10, 700 - (objBeanRemision.getElevacion() * 90), 50, 50);//posicion variable segun nr de elevaciones
                        }
                        doc.save(destino + objBeanRemision.getPeriodo() + "-" + objBeanRemision.getCodigo() + "-" + filenamePdf);
                    }
                }
                MultipartFile[] parts = files;
                for (MultipartFile part : parts) {
                    objBeanRemision.setArchivo(part.getOriginalFilename());
                    UploadFiles.TramiteDocumentario(objBeanRemision.getPeriodo() + "-" + objBeanRemision.getCodigo(), part);
                    remisionDocumentoDAO.sp_iduAnexoDocumento(
                            objBeanRemision.getPeriodo(),
                            objBeanRemision.getCodigo(),
                            objBeanRemision.getElevacion(),
                            objBeanRemision.getArchivo(),
                            usuario,
                            mode);
                }
            } catch (SQLException | IOException ex) {
                Logger.getLogger(RemisionDocumentoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    is.close();
                } catch (IOException ex) {
                    Logger.getLogger(RemisionDocumentoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return "GUARDO";
    }

}
