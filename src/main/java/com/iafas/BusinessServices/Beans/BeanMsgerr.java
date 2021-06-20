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
public class BeanMsgerr implements Serializable {

    private String Usuario;
    private String Tipo;
    private String Tabla;
    private String Descripcion;

}
