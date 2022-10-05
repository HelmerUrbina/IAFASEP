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
public class BeanProgramacionMultianualDetalle {
    
    @Id
    @Column(name = "CONTADOR")
    private String contador;
    
    @Column(name = "CLASIFICADOR_CODIGO")
    private String clasificadorCodigo;
    
    @Column(name = "CLASIFICADOR")
    private String clasificador;
    
    @Column(name = "ANIO")
    private String anio;
    
    @Column(name = "IMPOR")
    private String importe;
    
}
