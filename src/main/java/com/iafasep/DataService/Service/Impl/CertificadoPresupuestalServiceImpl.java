package com.iafasep.DataService.Service.Impl;

import com.iafasep.BusinessServices.Beans.BeanCertificadoPresupuestal;
import com.iafasep.BusinessServices.Beans.BeanCertificadoPresupuestalDetalle;
import com.iafasep.DataService.DAO.CertificadoPresupuestalDAO;
import com.iafasep.DataService.DAO.CertificadoPresupuestalDetalleDAO;
import com.iafasep.DataService.Service.CertificadoPresupuestalService;
import com.iafasep.Utiles.UploadFiles;
import com.iafasep.Utiles.Utiles;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Service
public class CertificadoPresupuestalServiceImpl implements CertificadoPresupuestalService {

    @Autowired
    private CertificadoPresupuestalDAO certificadoDAO;

    @Autowired
    private CertificadoPresupuestalDetalleDAO certificadoDetalleDAO;

    @Override
    public List<BeanCertificadoPresupuestal> getListaCertificadoPresupuestal(String periodo, Integer fuenteFinanciamiento) {
        return certificadoDAO.findByPeriodoAndFuenteFinanciamiento(periodo, fuenteFinanciamiento);
    }

    @Override
    public List<BeanCertificadoPresupuestalDetalle> getListaCertificadoPresupuestalDetalle(String periodo, Integer fuenteFinanciamiento) {
        return certificadoDetalleDAO.findDetalleByPeriodoAndFuenteFinanciamiento(periodo, fuenteFinanciamiento);
    }

    @Override
    public List<BeanCertificadoPresupuestalDetalle> getCertificadoPresupuestalDetalle(String periodo, Integer fuenteFinanciamiento, Integer certificado) {
        return certificadoDetalleDAO.findDetalleByPeriodoAndFuenteFinanciamientoAndCertificado(periodo, fuenteFinanciamiento, certificado);
    }

    @Override
    public Integer getNroCertificadoPresupuestal(String periodo) {
        return certificadoDAO.getNroCertificadoByPeriodo(periodo);
    }

    @Override
    public BeanCertificadoPresupuestal getCertificadoPresupuestal(String periodo, Integer certificado) {
        return certificadoDAO.findByPeriodoAndCertificado(periodo, certificado);
    }

    @Override
    public String guardarCertificadoPresupuestal(BeanCertificadoPresupuestal objBnCertificado, String usuario, String modo) {
        String result = "GUARDO";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false); //No Complaciente en Fecha
            java.util.Date fecha_util = sdf.parse(Utiles.checkFecha(objBnCertificado.getFecha()));
            certificadoDAO.sp_certificadoPresupuestal(
                    objBnCertificado.getPeriodo(),
                    objBnCertificado.getFuenteFinanciamiento(),
                    objBnCertificado.getCertificado(),
                    objBnCertificado.getTipo(),
                    objBnCertificado.getAnexo(),
                    objBnCertificado.getDocumento(),
                    objBnCertificado.getConcepto(),
                    objBnCertificado.getObservacion(),
                    new java.sql.Date(fecha_util.getTime()),
                    Utiles.checkNum(objBnCertificado.getTipoMoneda()),
                    objBnCertificado.getTipoCambio(),
                    objBnCertificado.getEstado(),
                    usuario,
                    modo);
        } catch (Exception | Error e) {
            result = Utiles.getErrorSQL((Exception) e);
        }
        return result;
    }

    @Override
    public String guardarCerrarCertificadoPresupuestal(BeanCertificadoPresupuestal objBnCertificado, MultipartFile file, String usuario, String modo) {
        String result = "GUARDO";
        try {
            UploadFiles UploadFiles = new UploadFiles();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false); //No Complaciente en Fecha
            java.util.Date fecha_util = sdf.parse(Utiles.checkFecha(objBnCertificado.getFecha()));
            certificadoDAO.sp_certificadoPresupuestal(
                    objBnCertificado.getPeriodo(),
                    objBnCertificado.getFuenteFinanciamiento(),
                    objBnCertificado.getCertificado(),
                    objBnCertificado.getTipo(),
                    objBnCertificado.getAnexo(),
                    objBnCertificado.getDocumento(),
                    objBnCertificado.getConcepto(),
                    objBnCertificado.getObservacion(),
                    new java.sql.Date(fecha_util.getTime()),
                    Utiles.checkNum(objBnCertificado.getTipoMoneda()),
                    objBnCertificado.getTipoCambio(),
                    objBnCertificado.getEstado(),
                    usuario,
                    modo);
            UploadFiles.CertificacionPresupuestal(objBnCertificado.getPeriodo() + "-" + objBnCertificado.getFuenteFinanciamiento() + "-" + objBnCertificado.getCertificado(), file);
        } catch (Exception | Error e) {
            result = Utiles.getErrorSQL((Exception) e);
        }
        return result;
    }

}
