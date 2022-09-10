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
@Table(name = "IAFAS_ESTADOS")
@Data
public class BeanEstado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CESTADO_CODIGO", nullable = false)
    private String codigo;

    @Column(name = "VESTADO_DESCRIPCION")
    private String descripcion;

}
