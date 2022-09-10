package com.iafasep.BusinessServices.Beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Entity
@Table(name = "IAFAS_USUARIOS_FIRMAS")
@Data
public class BeanUsuarioFirma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "PERIODO", nullable = false)
    private String periodo;

    @Id
    @Column(name = "USUARIO", nullable = false)
    private String usuario;

    @Column(name = "SERIE", nullable = false)
    private String serie;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "CODIGO_GRADO", nullable = false)
    private String codigoGrado;

    @Column(name = "GRADO")
    private String grado;

    @Column(name = "CARGO")
    private String cargo;

    @Column(name = "ESTADO")
    private String estado;

}
