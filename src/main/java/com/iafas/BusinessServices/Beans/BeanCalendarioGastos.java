/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.BusinessServices.Beans;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author H-URBINA-M
 */
@Data
public class BeanCalendarioGastos implements Serializable {

    private String Mode;
    private String Codigo;
    private String Periodo;
    private String GenericaGasto;
    private String Tarea;
    private String CadenaGasto;
    private String Resolucion;
    private Integer Presupuesto;
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
    private Double Programado;
    private Double Importe;
    private Double Total;

}
