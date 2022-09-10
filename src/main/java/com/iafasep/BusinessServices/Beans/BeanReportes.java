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
@Table(name = "IAFAS_REPORTES")
public class BeanReportes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CREPORTE_CODIGO", nullable = false)
    private String codigo;

    @Column(name = "VREPORTE_NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "CREPORTE_FIRMA", nullable = false)
    private String firma;

    @Column(name = "CESTADO_CODIGO")
    private String estado;
}
