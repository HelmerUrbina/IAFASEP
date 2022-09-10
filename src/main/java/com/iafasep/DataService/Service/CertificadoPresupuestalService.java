package com.iafasep.DataService.Service;

import com.iafasep.BusinessServices.Beans.BeanCertificadoPresupuestal;
import com.iafasep.BusinessServices.Beans.BeanCertificadoPresupuestalDetalle;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
public interface CertificadoPresupuestalService {

    public List<BeanCertificadoPresupuestal> getListaCertificadoPresupuestal(String periodo, Integer fuenteFinanciamiento);

    public List<BeanCertificadoPresupuestalDetalle> getListaCertificadoPresupuestalDetalle(String periodo, Integer fuenteFinanciamiento);

    public List<BeanCertificadoPresupuestalDetalle> getCertificadoPresupuestalDetalle(String periodo, Integer fuenteFinanciamiento, Integer certificado);

    public Integer getNroCertificadoPresupuestal(String periodo);

    public BeanCertificadoPresupuestal getCertificadoPresupuestal(String periodo, Integer certificado);

    public String guardarCertificadoPresupuestal(BeanCertificadoPresupuestal objBnCertificado, String usuario, String modo);

    public String guardarCerrarCertificadoPresupuestal(BeanCertificadoPresupuestal objBnCertificado, MultipartFile file, String usuario, String modo);

}
