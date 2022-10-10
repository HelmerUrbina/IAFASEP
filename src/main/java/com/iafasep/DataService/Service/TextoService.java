package com.iafasep.DataService.Service;

import java.sql.Blob;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
public interface TextoService {

    public String getNumeroDocumentoTipoDocumento(String periodo, Integer tipoDocumento);

    public Blob getPlantillaTipoDocumento(String periodo, Integer tipoDocumento);

    public Boolean getVerificaPin(String usuario, String pin);

    public Blob getUsuarioSello(String usuario);

}
