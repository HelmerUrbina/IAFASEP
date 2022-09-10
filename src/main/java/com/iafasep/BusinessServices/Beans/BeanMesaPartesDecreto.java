package com.iafasep.BusinessServices.Beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "IAFAS_MESA_PARTES_DECRETOS")
public class BeanMesaPartesDecreto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "CMESA_PARTE_TIPO", nullable = false)
    private String tipo;

    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String periodo;

    @Column(name = "NMESA_PARTE_CODIGO", nullable = false)
    private String numero;
    
    @Id
    @Column(name = "NMESA_PARTE_DECRETO_CODIGO", nullable = false)
    private String decreto;

    @Column(name = "VUSUARIO_DECRETA", nullable = false)
    private String usuarioDecreta;    
    
    @Column(name = "VUSUARIO_RESPONSABLE", nullable = false)
    private String usuarioResponsable;

    @Column(name = "NPRIORIDAD_CODIGO", nullable = false)
    private String prioridad;

    @Column(name = "VMESA_PARTE_DECRETO_DESCRIPCIO", nullable = false)
    private String descripcion;

    @Column(name = "DMESA_PARTE_DECRETO_FECHA", nullable = false)
    private String fecha;

    @Column(name = "DMESA_PARTE_DECRETO_RECEPCION")
    private String fechaRecepcion;

    @Column(name = "NAREA_LABORAL_CODIGO", nullable = false)
    private String areaLaboral;

    @Column(name = "CESTADO_CODIGO")
    private String estado;

}
