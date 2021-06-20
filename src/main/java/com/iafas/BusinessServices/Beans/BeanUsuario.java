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
public class BeanUsuario implements Serializable {

    private String Mode;
    private String Paterno;
    private String Materno;
    private String Nombres;
    private String Usuario;
    private String Password;
    private String Cargo;
    private String Correo;
    private String Telefono;
    private String Sistema;
    private String Estado;
    private String AreaLaboral;
    private String Modulo;
    private String Menu;
    private Boolean Autorizacion;

}
