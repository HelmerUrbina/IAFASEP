package com.iafasep.BusinessServices.Beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Entity
@Data
@Table(name = "IAFAS_USUARIOS_MENU")
public class BeanUsuarioMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "VUSUARIO_CODIGO", nullable = false)
    private String usuario;
    private String moduloNombre;
    private String menuNombre;
    private String menuOpcion;
    private String menuMode;
    private Integer modulo;
    private Integer menu;

    public BeanUsuarioMenu() {
    }

    public BeanUsuarioMenu(String usuario, Integer modulo, Integer menu, String menuNombre, String menuOpcion, String menuMode) {
        this.usuario = usuario;
        this.modulo = modulo;
        this.menu = menu;
        this.menuNombre = menuNombre;
        this.menuOpcion = menuOpcion;
        this.menuMode = menuMode;
    }

    public BeanUsuarioMenu(Integer modulo, String moduloNombre) {
        this.modulo = modulo;
        this.moduloNombre = moduloNombre;
    }

}
