package com.iafasep.DataService.Service.Impl;

import com.iafasep.DataService.DAO.TextoDAO;
import com.iafasep.DataService.Service.TextoService;
import java.sql.Blob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Service
public class TextoServiceImpl implements TextoService {

    @Autowired
    private TextoDAO textoDAO;

    @Override
    public String getNumeroDocumentoTipoDocumento(String periodo, Integer tipoDocumento) {
        return textoDAO.getNumeroDocumentoTipoDocumento(periodo, tipoDocumento);
    }

    @Override
    public Blob getPlantillaTipoDocumento(String periodo, Integer tipoDocumento) {
        return textoDAO.getPlantillaTipoDocumento(periodo, tipoDocumento);
    }

    @Override
    public Boolean getVerificaPin(String usuario, String pin) {
        return textoDAO.getVerificaPin(usuario, pin);
    }

    @Override
    public Blob getUsuarioSello(String usuario) {
        return textoDAO.getUsuarioSello(usuario);
    }

}
