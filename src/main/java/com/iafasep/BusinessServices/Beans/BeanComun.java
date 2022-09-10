package com.iafasep.BusinessServices.Beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Entity
@Data
public class BeanComun implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CODIGO", nullable = false)
    private Integer codigo;

    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;

    @Column(name = "ABREVIATURA", nullable = false)
    private String abreviatura;

    @Column(name = "ESTADO")
    private String estado;

}
