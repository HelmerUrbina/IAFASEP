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
@Table(name = "IAFAS_CERTIFICADO_PRESUPUESTAL")
public class BeanCertificadoPresupuestal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String periodo;

    @Column(name = "NFUENTE_FINANCIAMIENTO_CODIGO", nullable = false)
    private Integer fuenteFinanciamiento;

    @Id
    @Column(name = "NCERTIFICADO_CODIGO", nullable = false)
    private Integer certificado;

    @Column(name = "CCERTIFICADO_TIPO_REGISTRO", nullable = false)
    private String tipo;

    @Column(name = "NCERTIFICADO_ANEXO", nullable = false)
    private Integer anexo;

    @Column(name = "VCERTIFICADO_DOCUMENTO", nullable = false)
    private String documento;

    @Column(name = "VCERTIFICADO_CONCEPTO", nullable = false)
    private String concepto;

    @Column(name = "VCERTIFICADO_OBSERVACION", nullable = false)
    private String observacion;

    @Column(name = "DCERTIFICADO_FECHA", nullable = false)
    private String fecha;

    @Column(name = "NMONEDA_CODIGO", nullable = false)
    private String tipoMoneda;

    @Column(name = "NCERTIFICADO_IMPORTE", nullable = false)
    private Double importe;

    @Column(name = "NCERTIFICADO_TIPO_CAMBIO", nullable = false)
    private Double tipoCambio;

    @Column(name = "NCERTIFICADO_EXTRANJERA", nullable = false)
    private Double extranjera;

    @Column(name = "VCERTIFICADO_ARCHIVO", nullable = false)
    private String archivo;

    @Column(name = "CESTADO_CODIGO", nullable = false)
    private String estado;

    @Column(name = "NPAC_PROCESOS_CODIGO")
    private String pacProcesos;

   
}
