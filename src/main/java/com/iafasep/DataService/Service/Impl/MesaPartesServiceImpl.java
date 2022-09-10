package com.iafasep.DataService.Service.Impl;

import com.iafasep.BusinessServices.Beans.BeanMesaPartes;
import com.iafasep.DataService.DAO.MesaPartesDAO;
import com.iafasep.DataService.Service.MesaPartesService;
import com.iafasep.Utiles.UploadFiles;
import com.iafasep.Utiles.Utiles;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MesaPartesServiceImpl implements MesaPartesService {

    @Autowired
    private MesaPartesDAO mesaPartesDAO;

    @Override
    public List getListaMesaPartes(String tipo, String periodo, String mes, String dia) {
        return mesaPartesDAO.findByTipoAndPeriodoAndMesAndDia(tipo, periodo, mes, dia);
    }

    @Override
    public List getListaMesaPartesEstados(String tipo, String periodo, String mes, String estado) {
        return mesaPartesDAO.findByTipoAndPeriodoAndMesAndEstado(tipo, periodo, mes, estado);
    }

    @Override
    public List getListaMesaParteDecretados(String periodo, String usuario) {
        return mesaPartesDAO.findByTipoAndPeriodoAndUsuarioDecretado(periodo, usuario);
    }

    @Override
    public List getListaMesaPartesConsulta(String tipo, String periodo, String mes) {
        return mesaPartesDAO.findByTipoAndPeriodoAndMes(tipo, periodo, mes.replaceAll("00", "%"));
    }

    @Override
    public String getNumeroMesaParte(String tipo, String periodo) {
        return mesaPartesDAO.getNumeroByTipoAndPeriodo(tipo, periodo);
    }

    @Override
    public BeanMesaPartes getMesaParte(String tipo, String periodo, String codigo) {
        return mesaPartesDAO.findByTipoAndPeriodoAndCodigo(tipo, periodo, codigo);
    }

    @Override
    public String iduMesaParte(BeanMesaPartes objBeanMesaParte, MultipartFile file, String usuario, String mode) {
        UploadFiles UploadFiles = new UploadFiles();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false); //No Complaciente en Fecha
            java.util.Date fecha_util = sdf.parse(Utiles.checkFecha(objBeanMesaParte.getFecha()));
            mesaPartesDAO.sp_iduMesaPartes(
                    objBeanMesaParte.getPeriodo(),
                    objBeanMesaParte.getTipo(),
                    objBeanMesaParte.getNumero(),
                    objBeanMesaParte.getInstitucion(),
                    objBeanMesaParte.getPrioridad(),
                    objBeanMesaParte.getTipoDocumento(),
                    objBeanMesaParte.getNumeroDocumento(),
                    objBeanMesaParte.getClasificacion(),
                    new java.sql.Date(fecha_util.getTime()),
                    objBeanMesaParte.getAsunto(),
                    objBeanMesaParte.getPostFirma(),
                    objBeanMesaParte.getLegajos(),
                    objBeanMesaParte.getFolios(),
                    file.getOriginalFilename(),
                    usuario,
                    mode);
            UploadFiles.MesaPartes(objBeanMesaParte.getPeriodo() + "-" + objBeanMesaParte.getTipo() + "-" + objBeanMesaParte.getNumero(), file);
        } catch (Exception | Error e) {
            return Utiles.getErrorSQL((Exception) e);
        }
        return "GUARDO";
    }
}
