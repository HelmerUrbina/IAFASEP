/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.BusinessServices.Beans;

import java.io.Serializable;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author H-URBINA-M
 */
@Getter
@Setter
public class BeanMesaParte implements Serializable {

    private String mode;
    private String Periodo;
    private String Tipo;
    private String Numero;
    private String Mes;
    private String Institucion;
    private String Area;
    private String Prioridad;
    private String Documento;
    private String NumeroDocumento;
    private String Clasificacion;
    private String Estado;
    private String Usuario;
    private String Asunto;
    private String PostFirma;
    private String Cargo;
    private String Hora;
    private String CodigoArea;
    private String UsuarioResponsable;
    private String Archivo;
    private String Referencia;
    private String FechaBus;
    private Date Fecha;
    private Date FechaRegistro;
    private Integer Decreto;
    private Integer Cantidad;
    private Integer Legajo;
    private Integer Folio;
    private Integer DecretoDocumento;
    private String Comentario;

}
