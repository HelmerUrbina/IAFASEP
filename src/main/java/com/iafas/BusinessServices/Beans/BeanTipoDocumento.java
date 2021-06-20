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
public class BeanTipoDocumento implements Serializable {

    private Integer Codigo;
    private String Descripcion;
    private String Abreviatura;
    private String Mode;

}
