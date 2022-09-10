package com.iafasep.BusinessServices.Beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author helme
 */
@Entity
@Data
public class BeanFirmaDigital implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CODIGO", nullable = false)
    private Integer codigo;

    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;

    @Column(name = "DOCUMENTO", nullable = false)
    private String documento;

    @Column(name = "DIGITAL")
    private String digital;
    
    @Column(name = "ARCHIVO")
    private String archivo;

    @Column(name = "RESPONSABLE")
    private String responsable;

    @Column(name = "FECHA")
    private String fecha;
    
    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "YAFIRME")
    private String yaFirme;

    @Column(name = "IMPORTE")
    private Double importe;

}
