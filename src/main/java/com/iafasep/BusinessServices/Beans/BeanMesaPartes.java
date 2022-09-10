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
@Entity
@Data
@Table(name = "IAFAS_MESA_PARTES")
public class BeanMesaPartes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "CMESA_PARTE_TIPO", nullable = false)
    private String tipo;

    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String periodo;

    @Id
    @Column(name = "NMESA_PARTE_CODIGO", nullable = false)
    private String numero;

    @Column(name = "CMES_CODIGO", nullable = false)
    private String mes;

    @Column(name = "NINSTITUCION_CODIGO", nullable = false)
    private String institucion;

    @Column(name = "NTIPO_DOCUMENTO_CODIGO", nullable = false)
    private String tipoDocumento;

    @Column(name = "VMESA_PARTE_NUMERO", nullable = false)
    private String numeroDocumento;

    @Column(name = "NCLASIFICACION_DOCUMENTO_CODIG", nullable = false)
    private String clasificacion;

    @Column(name = "CESTADO_CODIGO", nullable = false)
    private String estado;

    @Column(name = "VMESA_PARTE_ASUNTO", nullable = false)
    private String asunto;

    @Column(name = "VMESA_PARTE_POST_FIRMA", nullable = false)
    private String postFirma;

    @Column(name = "VMESA_PARTE_CORREO")
    private String correo;

    @Column(name = "VMESA_PARTE_DIGITAL", nullable = false)
    private String digital;

    @Column(name = "DMESA_PARTE_FECHA", nullable = false)
    private String fecha;

    @Column(name = "DMESA_PARTE_RECEPCION", nullable = false)
    private String fechaRegistro;

    @Column(name = "NPRIORIDAD_CODIGO", nullable = false)
    private String prioridad;

    @Column(name = "NMESA_PARTE_LEGAJOS", nullable = false)
    private Integer legajos;

    @Column(name = "NMESA_PARTE_FOLIOS", nullable = false)
    private Integer folios;

    @Column(name = "VMESA_PARTE_ANULACION")
    private String comentario;

}
