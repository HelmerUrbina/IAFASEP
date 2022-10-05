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

@Entity
@Data
public class BeanCuadroNecesidad {
    
    @Id
    @Column(name = "NTAREA_PRESUPUESTAL_CODIGO", nullable = false)
    private Integer tareaCodigo;

    @Column(name = "TAREA", nullable = false)
    private String tarea;

    @Column(name = "META", nullable = false)
    private Integer meta;
    
    @Column(name = "TOTAL", nullable = false)
    private Integer total;
    
    @Column(name = "PROGRAMADO", nullable = false)
    private Integer programado;
    
}
