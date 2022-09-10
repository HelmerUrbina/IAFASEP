package com.iafasep.BusinessServices.Beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import javax.persistence.Id;

/**
 *
 * @author H-URBINA-M
 */
@Entity
@Data
@Table(name = "IAFAS_CERTIFI_PRESUPUESTAL_DET")
public class BeanCertificadoPresupuestalDetalle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String periodo;

    @Column(name = "NFUENTE_FINANCIAMIENTO_CODIGO", nullable = false)
    private String fuenteFinanciamiento;

    @Column(name = "NCERTIFICADO_CODIGO", nullable = false)
    private String certificado;

    @Id
    @Column(name = "NCERTIFICADO_DETALLE", nullable = false)
    private String detalle;

    @Column(name = "NRESOLUCION_CODIGO", nullable = false)
    private String resolucion;

    @Column(name = "NTAREA_PRESUPUESTAL_CODIGO", nullable = false)
    private String tarea;

    @Column(name = "NCLASIFICADOR_PRESUPUESTAL_COD", nullable = false)
    private String clasificador;

    @Column(name = "NCERTIFICADO_DETALLE_IMPORTE", nullable = false)
    private Double importe;

    @Column(name = "NCERTIFICADO_DETALLE_EXTRANJER", nullable = false)
    private Double extranjera;

}
