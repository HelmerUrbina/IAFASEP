package com.iafasep.DataService.Service;

import com.iafasep.BusinessServices.Beans.BeanMesaPartes;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface MesaPartesService {

    public List getListaMesaPartes(String tipo, String periodo, String mes, String dia);

    public List getListaMesaPartesEstados(String tipo, String periodo, String mes, String estado);

    public List getListaMesaParteDecretados(String periodo, String usuario);

    public List getListaMesaPartesConsulta(String tipo, String periodo, String mes);

    public String getNumeroMesaParte(String tipo, String periodo);

    public BeanMesaPartes getMesaParte(String tipo, String periodo, String codigo);

    public String iduMesaParte(BeanMesaPartes objBeanMesaParte, MultipartFile file, String usuario, String mode);

}
