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
public class BeanNotaModificatoria implements Serializable {

    private String Mode;
    private String Periodo;
    private String Mes;
    private String UnidadOperativa;
    private String Dependencia;
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
    private String Unidad;
    private String CategoriaPresupuestal;
    private String Producto;
    private String Actividad;
    private String Importancia;
    private String Financiamiento;
    private String Consecuencia;
    private String Variacion;
    private String Consolidado;
    private String TipoCalendario;
    private String Archivo;
    private String Usuario;
    private String UsuarioCierre;
    private String UsuarioVerifica;
    private String UsuarioRechazo;
    private String UsuarioAprobacion;
    private Integer Detalle;
    private Integer Resolucion;
    private Integer SIAF;
    private Double Importe;
    private Double ImporteAnulacion;
    private Double ImporteCredito;
    private Double Enero;
    private Double Febrero;
    private Double Marzo;
    private Double Abril;
    private Double Mayo;
    private Double Junio;
    private Double Julio;
    private Double Agosto;
    private Double Setiembre;
    private Double Octubre;
    private Double Noviembre;
    private Double Diciembre;
    private Double Saldo;
    private Date Fecha;
    private Date FechaCierre;
    private Date FechaVerifica;
    private Date FechaRechazo;
    private Date FechaAprobacion;

}
