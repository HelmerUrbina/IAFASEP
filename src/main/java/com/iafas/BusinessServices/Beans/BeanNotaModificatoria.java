/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.BusinessServices.Beans;

import java.io.Serializable;
import java.sql.Date;
import lombok.Data;

/**
 *
 * @author H-URBINA-M
 */
@Data
public class BeanNotaModificatoria implements Serializable {

    private String Mode;
    private String Periodo;
    private String Mes;
    private String Presupuesto;
    private String Codigo;
    private String Tipo;
    private String Justificacion;
    private String NotaModificatoria;
    private String Estado;
    private String Descripcion;
    private String FuenteFinanciamiento;
    private String SecuenciaFuncional;
    private String CadenaGasto;
    private String Tarea;
    private String Rechazo;
    private String Archivo;
    private String Usuario;
    private String UsuarioCierre;
    private String UsuarioRechazo;
    private String UsuarioAprobacion;
    private Integer Detalle;
    private Integer Resolucion;
    private Double Importe;
    private Double ImporteAnulacion;
    private Double ImporteCredito;
    private Double Saldo;
    private Date Fecha;
    private Date FechaCierre;
    private Date FechaRechazo;
    private Date FechaAprobacion;

}
