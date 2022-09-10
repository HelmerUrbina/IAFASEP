package com.iafasep.Utiles;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author helme
 */
@Service
public class Reportes {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JasperPrint getReporte(String reporte, String periodo, String tipo, String brigada, String fuenteFinanciamiento, String eventoPrincipal, String codigo) throws JRException, SQLException {
        JasperPrint jasperPrint = null;
        String nombre = "";
        switch (reporte) {
            case "MPA0001" ->
                nombre = "MesaPartes/MPA0001.jasper";
            case "EJE0001" ->
                nombre = "Presupuesto/EJE0001.jasper";
            case "EJE0002" ->
                nombre = "Presupuesto/EJE0002.jasper";
            case "EJE0003" ->
                nombre = "Presupuesto/EJE0003.jasper";
            case "EJE0004" ->
                nombre = "Presupuesto/EJE0004.jasper";
            case "EJE0005" ->
                nombre = "Presupuesto/EJE0005.jasper";
            case "CIII0002" ->
                nombre = "ClaseIII/CIII0002.jasper";
            case "CIII0003" ->
                nombre = "ClaseIII/CIII0003.jasper";
            case "CIII0004" ->
                nombre = "ClaseIII/CIII0004.jasper";
            case "CIII0005" ->
                nombre = "ClaseIII/CIII0005.jasper";
            case "CIII0006" ->
                nombre = "ClaseIII/CIII0006.jasper";
            case "CIII0007" ->
                nombre = "ClaseIII/CIII0007.jasper";
            case "CIII0008" ->
                nombre = "ClaseIII/CIII0008.jasper";
            case "DISP0001" ->
                nombre = "Dispensacion/DISP0001.jasper";
            default -> {
            }
        }
        InputStream stream = getClass().getResourceAsStream("/templates/Reportes/" + nombre);
        if (stream == null) {
            throw new IllegalArgumentException("No se encuentra el reporte con nombre : " + nombre);
        }
        Map parameters = new HashMap();
        parameters.put("REPORT_LOCALE", new Locale("en", "US"));
        parameters.put("PERIODO", periodo);
        parameters.put("TIPO", tipo);
        parameters.put("BRIGADA", Utiles.checkStr(brigada).replace("*", "%"));
        parameters.put("FUENTE_FINANCIAMIENTO", fuenteFinanciamiento);
        parameters.put("EVENTO_PRINCIPAL", eventoPrincipal);
        parameters.put("CODIGO", Utiles.checkStr(codigo).replace("*", "%"));
        parameters.put("USUARIO", Utiles.getUsuario());
        //parameters.put("SUBREPORT_DIR", servletContext.getRealPath("\\WEB-INF\\classes\\static\\"));
        parameters.put("SUBREPORT_DIR", "D:\\\\Desarrollo\\\\IAFASEP\\\\src\\\\main\\\\resources\\\\static\\\\");
        parameters.put("SUBREPORT_DIR2", stream);
        jasperPrint = JasperFillManager.fillReport(stream, parameters, jdbcTemplate.getDataSource().getConnection());
        return jasperPrint;
    }

    public String generateReportPDF(String reporte, String periodo, String tipo, String brigada, String fuenteFinanciamiento, String eventoPrincipal, String codigo, String tipoReporte, String destino) throws IOException, JRException, SQLException {
        String filePath = Utiles.getServerPath();
        String fileName = "EP" + periodo + "-" + fuenteFinanciamiento + "-" + codigo + ".pdf";
        filePath = destino.equals("firmados") ? filePath + "\\FirmaDigital" : filePath + "\\FirmaDigital\\Temporal";
        switch (tipoReporte) {
            case "CP" ->
                filePath = filePath + "\\CertificadoPresupuestal\\";
            case "CA" ->
                filePath = filePath + "\\CompromisoAnual\\";
            case "DJ" ->
                filePath = filePath + "\\CompromisoMensual\\";
            case "NM" ->
                filePath = filePath + "\\NotaModificatoria\\";
            case "IDP" ->
                filePath = filePath + "\\InformeDisponibilidadPresupuestal\\";
            default -> {
            }
        }
        try ( OutputStream output = new FileOutputStream(new File(filePath + fileName))) {
            JasperPrint jasperPrint = getReporte(reporte, periodo, tipo, brigada, fuenteFinanciamiento, eventoPrincipal, codigo);
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
        }
        return fileName;
    }

}
