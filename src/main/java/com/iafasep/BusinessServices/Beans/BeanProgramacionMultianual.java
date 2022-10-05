/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafasep.BusinessServices.Beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BeanProgramacionMultianual implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "CONTADOR")
    private String contador;
    
    @Column(name = "TAREA_CODIGO")
    private String tareaCodigo;
    
    @Column(name = "TAREA")
    private String tarea;
    
    @Column(name = "ANIO")
    private String anio;
    
    @Column(name = "PROGRAMADO")
    private String programado;
    
    @Column(name = "DETALLE")
    private String detalle;
    
    @Column(name = "SALDO")
    private String saldo;
    
    @Column(name = "META")
    private String meta;
    
    @Column(name = "ESTADO")
    private String estado;
    
    @Column(name = "UBIGEO_CODIGO")
    private String ubigeoCodigo;
    
    @Column(name = "DEPARTAMENTO")
    private String departamento;
    
    @Column(name = "PROVINCIA")
    private String provincia;
    
    @Column(name = "DISTRITO")
    private String distrito;
    
}
