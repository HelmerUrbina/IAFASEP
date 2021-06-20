package com.iafas.BusinessServices.Beans;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author H-URBINA-M
 */
@Data
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
