package com.iafasep.DataService.Service;

import com.iafasep.BusinessServices.Beans.BeanComun;
import java.util.List;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
public interface TipoDocumentosService {

    public List<BeanComun> getTipoDocumentos();

    public String guardarTipoDocumento(BeanComun objBnComun, String usuario, String modo);
}
