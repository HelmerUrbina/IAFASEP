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
@Table(name = "IAFAS_MODULOS")
public class BeanModulo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NMODULO_CODIGO", nullable = false)
    private Integer modulo;

    @Column(name = "VMODULO_NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "CESTADO_CODIGO")
    private String estado;

}
