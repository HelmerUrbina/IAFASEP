/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.DataService.Despachadores;

import com.iafas.BusinessServices.Beans.BeanComun;
import java.util.List;

/**
 *
 * @author H-URBINA-M
 */
public interface TareasPresupuestalesDAO {

    public List getListaTareasPresupuestales();

    public BeanComun getTareasPresupuestales(BeanComun objBeanTareas);

    public int iduTareasPresupuestales(BeanComun objBeanTareasPresupuestales, String usuario);

}
