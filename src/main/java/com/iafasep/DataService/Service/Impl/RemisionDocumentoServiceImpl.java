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
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;
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
    public String iduRemisionDocumento(BeanRemisionDocumento objBeanRemision, String usuario, String mode) {
        Integer remision = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false); //No Complaciente en Fecha
            java.util.Date fecha_util = sdf.parse(Utiles.checkFecha(objBeanRemision.getFecha()));
            if (mode.equals("I")) {
                objBeanRemision.setCodigo(remisionDocumentoDAO.getCodigoByPeriodo(objBeanRemision.getPeriodo()));
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
                try ( BufferedReader in = new BufferedReader(new InputStreamReader(textoDAO.getPlantillaTipoDocumento(objBeanRemision.getPeriodo(), Utiles.checkNum(objBeanRemision.getTipoDocumento())).getBinaryStream()))) {
                    String linea = null;
                    StringBuilder sb = new StringBuilder();
                    while ((linea = in.readLine()) != null) {
                        sb.append(linea);
                    }
                    plantilla = sb.toString();
                }
                if (!plantilla.equals("")) {
                    String serverDocs = "D:\\IAFASEP\\RemisionDocumento";
                    String fileName = objBeanRemision.getPeriodo() + "-" + objBeanRemision.getTipoDocumento() + "-" + objBeanRemision.getCodigo() + ".rtf";
                    if (mode.equals("I")) { // en caso no existe data anterior creamos un nuevo doc
                        SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
                        java.util.Date dFecha = formato2.parse(objBeanRemision.getFecha());
                        SimpleDateFormat formatoEsMX = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("ES", "PE"));
                        String fechaDoc = formatoEsMX.format(dFecha);
                        plantilla = plantilla.replace("<<numdoc>>", objBeanRemision.getPeriodo() + "-" + objBeanRemision.getTipoDocumento() + "-" + objBeanRemision.getNumeroDocumento());
                        plantilla = plantilla.replace("<<tipodoc>>", "" + objBeanRemision.getTipoDocumento());
                        plantilla = plantilla.replace("<<asunto>>", objBeanRemision.getAsunto());
                        plantilla = plantilla.replace("<<fechadoc>>", fechaDoc);
                        plantilla = plantilla.replace("<<nombredirigidoa>>", objBeanRemision.getDirigido());
                        plantilla = plantilla.replace("<<cargo>>", objBeanRemision.getCargo());
                        try ( FileWriter myWriter = new FileWriter(serverDocs + "\\" + fileName)) {
                            myWriter.write(plantilla);
                        }
                    } else if (mode.equals("U")) {//--------------- en caso si existe data anterior modificamos el doc
                        // plantilla = Utiles.getFileRtf(serverDocs + "\\" + fileName);
                        //if (!plantilla.equals("")) {
                        //StringTokenizer tok = new StringTokenizer(objBnRemision.getData_old(), "|");
                        /* while (tok.hasMoreTokens()) {
                        String row[] = tok.nextToken().split("##");
                        if (row[0].equals("fecha")) {
                        SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
                        java.util.Date dFecha = formato2.parse(row[1]);
                        SimpleDateFormat formatoEsMX = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("ES", "PE"));
                        String fechaDoc = formatoEsMX.format(dFecha);
                        formato2 = new SimpleDateFormat("dd/MM/yyyy");
                        dFecha = formato2.parse(objBnRemision.getFecha());
                        String fechaDocNew = formatoEsMX.format(dFecha);
                        plantilla = plantilla.replace(fechaDoc, fechaDocNew);
                        } else if (row[0].equals("dirigidoa")) {
                        plantilla = plantilla.replaceAll(row[1], objBnRemision.getDirigido());
                        } else if (row[0].equals("cargo")) {
                        plantilla = plantilla.replaceAll(row[1], objBnRemision.getCargo());
                        } else if (row[0].equals("asunto")) {
                        plantilla = plantilla.replaceAll(row[1], objBnRemision.getAsunto());
                        }
                        }*/
                        //   try ( FileWriter myWriter = new FileWriter(serverDocs + "\\" + fileName)) {
                        //        myWriter.write(plantilla);
                        //    }
                        //  } else {
                        //      return "No existe Plantilla para este Tipo de Documento";
                        //   }
                    }
                } else {
                    return "No existe Plantilla para este Tipo de Documento";
                }
            }

        } catch (Exception | Error e) {
            return Utiles.getErrorSQL((Exception) e);
        }
        return "" + objBeanRemision.getCodigo();
    }

    @Override
    public String iduElevarDocumento(BeanRemisionDocumento objBeanRemision, MultipartFile[] files, String usuario, String mode) {
        Integer elevacion = 0;
        if (mode.equals("I")) {
            objBeanRemision.setElevacion(remisionDocumentoDAO.getCodigoEvelarByPeriodoAndRemisionDocumento(objBeanRemision.getPeriodo(), objBeanRemision.getCodigo()));
        }
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
                objBeanRemision.setArchivo(remisionDocumentoDAO.getRemisionDocumentoArchivo(objBeanRemision.getPeriodo(), objBeanRemision.getCodigo()));
                if (is != null) {
                    String filenamePdf = "";
                    if (objBeanRemision.getElevacion() == 1) {
                        filenamePdf = objBeanRemision.getArchivo().substring(0, objBeanRemision.getArchivo().indexOf(".rtf")) + ".pdf";
                        //--------convertimos el rtf a pdf-------------
                        Converter converter = new Converter(serverDocs + objBeanRemision.getArchivo());
                        ConvertOptions convertOptions = FileType.fromExtension("pdf").getConvertOptions();
                        converter.convert(destino + filenamePdf, convertOptions);
                    }
                    //---------------obtiene sello para pdf----
                    byte[] targetArray = IOUtils.toByteArray(is);
                    //---------------insert image in pdf----
                    File file = new File(destino + "\\" + filenamePdf);
                    try ( PDDocument doc = PDDocument.load(file)) {
                        PDPage page = doc.getPage(0);
                        PDImageXObject pdImage = PDImageXObject.createFromByteArray(doc, targetArray, "firma");
                        try ( PDPageContentStream contents = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, false)) {
                            contents.drawImage(pdImage, 10, 700 - (objBeanRemision.getElevacion() * 90), 50, 50);//posicion variable segun nr de elevaciones
                        }
                        doc.save(destino + "\\" + filenamePdf);
                    }
                }
                MultipartFile[] parts = files;
                for (MultipartFile part : parts) {
                    UploadFiles.MesaPartes(objBeanRemision.getPeriodo() + "-" + objBeanRemision.getCodigo(), part);
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
