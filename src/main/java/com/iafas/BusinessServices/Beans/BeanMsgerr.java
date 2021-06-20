package com.iafas.BusinessServices.Beans;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author H-URBINA-M
 */
@Data
public class BeanMsgerr implements Serializable {

    private String Usuario;
    private String Tipo;
    private String Tabla;
    private String Descripcion;

}
