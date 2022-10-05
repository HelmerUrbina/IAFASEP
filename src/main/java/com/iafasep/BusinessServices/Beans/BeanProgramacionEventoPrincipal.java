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
public class BeanProgramacionEventoPrincipal {
    
    
    @Id
    @Column(name = "VEVENTO_PRINCIPAL_CODIGO")
    private String eventoPrincipal;

    @Column(name = "VEVENTO_PRINCIPAL_NOMBRE")
    private String eventoPrincipalNombre;
    
    @Column(name = "VEVENTO_PRINCIPAL_COMENTARIO")
    private String eventoPrincipalComentario;
    
    @Column(name = "NEVENTO_PRINCIPAL_NIVEL")
    private Integer nivel;

    @Column(name = "NEVENTO_PRINCIPAL_NIVELES")
    private Integer niveles;
    
    @Column(name = "CEVENTO_PRINCIPAL_FINAL")
    private String isFinal;
    
    @Column(name = "VEVENTO_PRINCIPAL_PRINCIPAL")
    private String eventoPrincipalPrincipal;

    @Column(name = "CESTADO_CODIGO")
    private String estado;
    
}
