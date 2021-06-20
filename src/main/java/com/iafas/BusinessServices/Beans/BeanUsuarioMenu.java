package com.iafas.BusinessServices.Beans;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author H-URBINA-M
 */
@Data
public class BeanUsuarioMenu implements Serializable {

    private String Mode;
    private String Usuario;
    private String Modulo;
    private String Menu;
    private String Descripcion;
    private String Servlet;
    private String Modo;
    private String Privilegio;

}
