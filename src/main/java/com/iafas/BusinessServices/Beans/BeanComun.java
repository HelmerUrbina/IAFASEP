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
public class BeanComun implements Serializable {

    private String Mode;
    private String Codigo;
    private String Descripcion;
    private String Abreviatura;
    private String UnidadMedida;
    private String Estado;
    private double Importe;
    private double Cantidad;

}
