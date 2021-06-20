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
public class BeanTareaEspecifica implements Serializable {

    private String Mode;
    private String Codigo;
    private String Periodo;
    private String Tarea;
    private String CadenaGasto;

}
