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
@Table(name = "IAFAS_NOTAS_MODIFICATORIAS_DET")
public class BeanNotaModificatoriaDetalle implements Serializable {

   
    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String periodo;
    
    @Id
    @Column(name = "NNOTA_MODIFICATORIA_CODIGO", nullable = false)
    private String codigo;
   /* 
    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String tipo;
    
    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String justificacion;
    
    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String notaModificatoria;
    
    @Column(name = "CPERIODO_CODIGO", nullable = false)    
    private String estado;
    
    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String descripcion;
    
    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String FuenteFinanciamiento;
    
    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String SecuenciaFuncional;
    
    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String CadenaGasto;
    
    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String Tarea;
    
    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String Rechazo;
    
    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String Archivo;
    
    @Column(name = "CPERIODO_CODIGO", nullable = false)
    private String Usuario;
    
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
    private String Fecha;
    private String FechaCierre;
    private String FechaRechazo;
    private String FechaAprobacion;
*/
}
