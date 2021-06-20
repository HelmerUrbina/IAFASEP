/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.BusinessServices.Beans;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author H-URBINA-M
 */
@Getter
@Setter
public class BeanReporte implements Serializable {

    private String Codigo;
    private String Codigo2;
    private String Reporte;
    private String Periodo;
    private String Tipo;
    private String Unidad;
    private Integer Presupuesto;
    private String Generica;

}
