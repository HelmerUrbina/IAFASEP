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
@Table(name = "IAFAS_MENU")
public class BeanUsuarioOpciones implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "VUSUARIO_CODIGO", nullable = false)
    private String usuario;

    @Column(name = "NMODULO_CODIGO")
    private String moduloCodigo;

    @Column(name = "NMENU_CODIGO")
    private String menuCodigo;

    @Column(name = "VUSUARIO_CREADOR")
    private String usuarioCreador;

}
