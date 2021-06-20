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
public class BeanMenu implements Serializable {

    private String Mode;
    private String Modulo;
    private String Codigo;
    private String Nombre;
    private String Servlet;
    private String Modo;
    private String Estado;

}
