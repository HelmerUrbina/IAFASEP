/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafasep.BusinessServices.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Entity
@Data
public class BeanProgramacionCNV{
    
    @Id
    @Column(name = "CODIGO", nullable = false)
    private String codigo;
    
    @Column(name = "NITEM_CODIGO", nullable = false)
    private String item;

    @Column(name = "NCLASIFICADOR_PRESUPUESTAL_COD", nullable = false)
    private String clasificadorPresupuestal;

    @Column(name = "CUNIDAD_MEDIDA_CODIGO", nullable = false)
    private String unidadMedida;

    @Column(name = "NCNV_PRECIO")
    private String precio;
    
    @Column(name = "CANT_A")
    private String cantidadA;
    
    @Column(name = "CANT_B")
    private String cantidadB;
    
    @Column(name = "CANT_C")
    private String cantidadC;
    
    @Column(name = "TOTAL_A")
    private String totalA;
    
    @Column(name = "TOTAL_B")
    private String totalB;
    
    @Column(name = "TOTAL_C")
    private String totalC;
    
    @Column(name = "DEM_A")
    private String demandaA;
    
    @Column(name = "DEM_B")
    private String demandaB;
    
    @Column(name = "DEM_C")
    private String demandaC;
    
    @Column(name = "SALDO_A")
    private String saldoA;
    
    @Column(name = "SALDO_B")
    private String saldoB;
    
    @Column(name = "SALDO_C")
    private String saldoC;

    @Column(name = "CESTADO_CODIGO")
    private String estado;
    
}
