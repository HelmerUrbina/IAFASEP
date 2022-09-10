package com.iafasep.DataService.Service.Impl;

import com.iafasep.BusinessServices.Beans.BeanComun;
import com.iafasep.DataService.DAO.TipoDocumentosDAO;
import com.iafasep.DataService.Service.TipoDocumentosService;
import com.iafasep.Utiles.Utiles;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Service
public class TipoDocumentosServiceImpl implements TipoDocumentosService {

    @Autowired
    private TipoDocumentosDAO tipoDocumentoDAO;

    @Override
    public List<BeanComun> getTipoDocumentos() {
        return tipoDocumentoDAO.findAll();
    }

    @Override
    public String guardarTipoDocumento(BeanComun objBnComun, String usuario, String modo) {
        String result = "GUARDO";
        try {
            tipoDocumentoDAO.sp_tipoDocumento(
                    objBnComun.getCodigo(),
                    objBnComun.getDescripcion(),
                    objBnComun.getAbreviatura(),
                    usuario,
                    modo);
        } catch (Exception | Error e) {
            result = Utiles.getErrorSQL((Exception) e);
        }
        return result;
    }
}
