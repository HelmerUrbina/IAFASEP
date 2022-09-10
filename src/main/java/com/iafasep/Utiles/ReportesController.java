package com.iafasep.Utiles;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Controller
@Slf4j
public class ReportesController {

    @Autowired
    private Reportes reportes;

    @GetMapping("/Reporte")
    public void generateReport(HttpServletResponse response, String reporte, String periodo, String tipo, String brigada, String fuenteFinanciamiento, String eventoPrincipal, String codigo) throws IOException, JRException, SQLException {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("application/pdf");
        JasperPrint jasperPrint = reportes.getReporte(reporte, periodo, tipo, brigada, fuenteFinanciamiento, eventoPrincipal, codigo);
        try ( OutputStream out = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
            out.flush();
        }
    }
}
