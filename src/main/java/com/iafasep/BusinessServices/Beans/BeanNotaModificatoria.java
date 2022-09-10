package com.iafasep.BusinessServices.Beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author helme
 */
@Data
@Entity
@Table(name = "IAFAS_NOTAS_MODIFICATORIAS")
public class BeanNotaModificatoria implements Serializable {

    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String periodo;

    @Id
    @Column(name = "NNOTA_MODIFICATORIA_CODIGO", nullable = false)
    private String codigo;

    @Column(name = "NTIPO_NOTA_MODIFICATORIA_CODIG", nullable = false)
    private String tipo;

    @Column(name = "VNOTA_MODIFICATORIA_JUSTIFICAC", nullable = false)
    private String justificacion;

    @Column(name = "DNOTA_MODIFICATORIA_FECHA", nullable = false)
    private String fecha;

    @Column(name = "CESTADO_CODIGO", nullable = false)
    private String estado;

    @Column(name = "VNOTA_MODIFICATORIA_ARCHIVO", nullable = false)
    private String archivo;

    /*

    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String descripcion;

    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String Rechazo;

    

    

    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String UsuarioCierre;

    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String UsuarioRechazo;
    private String UsuarioAprobacion;
    private Integer Detalle;
    private Integer Resolucion;
    private Double Importe;
    private Double ImporteAnulacion;
    private Double ImporteCredito;
    private Double Saldo;
    private String FechaCierre;
    private String FechaRechazo;
    private String FechaAprobacion;
     */
}
