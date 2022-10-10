package com.iafasep.DataService.Service;

import com.iafasep.BusinessServices.Beans.BeanRemisionDocumento;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
public interface RemisionDocumentoService {

    public List<BeanRemisionDocumento> getListaRemisionDocumento(String periodo, String mes, String usuario);

    public String iduRemisionDocumento(BeanRemisionDocumento objBeanRemisionDocumento, String usuario, String mode);

    public String iduElevarDocumento(BeanRemisionDocumento objBeanRemisionDocumento, MultipartFile[] file, String usuario, String mode);

}
