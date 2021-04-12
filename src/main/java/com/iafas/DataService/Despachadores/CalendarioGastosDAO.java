/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.DataService.Despachadores;

import com.iafas.BusinessServices.Beans.BeanCalendarioGastos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author H-URBINA-M
 */
public interface CalendarioGastosDAO {

    public List getListaCalendarioGastos(BeanCalendarioGastos objBeanCalendarioGastos);

    public List getListaCalendarioGastosDetalle(BeanCalendarioGastos objBeanCalendarioGastos);

    public ArrayList getListaCalendarioGastoDetalle(BeanCalendarioGastos objBeanCalendarioGastos);

    public int iduCalendarioGastoDetalle(BeanCalendarioGastos objBeanCalendarioGastos, String usuario);
}
