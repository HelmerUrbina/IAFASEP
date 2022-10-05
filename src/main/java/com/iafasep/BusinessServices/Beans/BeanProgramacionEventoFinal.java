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
public class BeanProgramacionEventoFinal {
    
    @Column(name = "VEVENTO_PRINCIPAL_CODIGO", nullable = false)
    private String eventoPrincipal;

    @Id
    @Column(name = "NEVENTO_FINAL_CODIGO", nullable = false)
    private Integer eventoFinal;

    @Column(name = "VEVENTO_FINAL_NOMBRE")
    private String eventoFinalNombre;

    @Column(name = "NEVENTO_FINAL_PRIORIDAD")
    private Integer prioridad;
    
    @Column(name = "NEVENTO_FINAL_META_FISICA")
    private Integer meta;

    @Column(name = "CESTADO_CODIGO")
    private String estado;
    
}
