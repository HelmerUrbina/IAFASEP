package com.iafas.BusinessServices.Beans;

import java.io.Serializable;
import java.sql.Date;
import lombok.Data;

/**
 *
 * @author H-URBINA-M
 */
@Data
public class BeanEjecucionPresupuestal implements Serializable {

    private String mode;
    private String Periodo;
    private String Certificado;
    private String CompromisoAnual;
    private String CompromisoMensual;
    private String AnexoCertificado;
    private String DocumentoReferencia;
    private String Concepto;
    private String Observacion;
    private String Estado;
    private String Dependencia;
    private String DisponibilidadPresupuestal;
    private String Unidad;
    private String Tipo;
    private String TareaPresupuestal;
    private String CadenaGasto;
    private String TipoMoneda;
    private String FirmaJefe;
    private String FirmaSubJefe;
    private String Archivo;
    private String Mes;
    private String ProcesoSeleccion;
    private String Sectorista;
    private String Resolucion;
    private Date Fecha;
    private Integer Presupuesto;
    private Integer Correlativo;
    private Double Importe;
    private Double TipoCambio;
    private Double MonedaExtranjera;
    private Double Saldo;

}
