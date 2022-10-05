/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafasep.BusinessServices.Beans;

import java.io.Serializable;
import lombok.Data;

@Data
public class BeanProgramacionMultianualDto implements Serializable{
    private static final long serialVersionUID = 1L;
    private String tareaCodigo;
    private String tarea;
    private String progracion1;
    private String detalle1;
    private String saldo1;
    private String meta1;
    private String progracion2;
    private String detalle2;
    private String saldo2;
    private String meta2;
    private String progracion3;
    private String detalle3;
    private String saldo3;
    private String meta3;
    private String estado;
    private String ubigeo;
    private String departamento;
    private String provincia;
    private String distrito;
    
}
