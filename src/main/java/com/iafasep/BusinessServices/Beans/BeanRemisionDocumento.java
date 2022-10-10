package com.iafasep.BusinessServices.Beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.Data;
import org.springframework.data.jpa.repository.Temporal;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Entity
@Data
@Table(name = "IAFAS_REMISION_DOCUMENTO")
public class BeanRemisionDocumento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String periodo;

    @Id
    @Column(name = "NREMISION_DOCUMENTO_CODIGO", nullable = false)
    private Integer codigo;

    @Transient
    private Integer elevacion;

    @Column(name = "NTIPO_DOCUMENTO_CODIGO", nullable = false)
    private String tipoDocumento;

    @Column(name = "NREMISION_DOCUMENTO_NUMERO", nullable = false)
    private String numeroDocumento;

    @Column(name = "NINSTITUCION_CODIGO", nullable = false)
    private String institucion;

    @Column(name = "NCLASIFICACION_DOCUMENTO_CODIG", nullable = false)
    private String clasificacion;

    @Column(name = "DREMISION_DOCUMENTO_FECHA", nullable = false)
    private String fecha;

    @Column(name = "VREMISION_DOCUMENTO_ASUNTO", nullable = false)
    private String asunto;

    @Column(name = "VREMISION_DOCUMENTO_DIRIGIDO", nullable = false)
    private String dirigido;

    @Column(name = "VREMISION_DOCUMENTO_CARGO", nullable = false)
    private String cargo;

    @Column(name = "CESTADO_CODIGO", nullable = false)
    private String estado;

    @Column(name = "VREMISION_DOCUMENTO_DIGITAL", nullable = false)
    private String archivo;

    @Transient
    private String areaLaboral;

}
