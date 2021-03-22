/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.DataService.Despachadores;

import com.iafas.BusinessServices.Beans.BeanPACProcesos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author H-URBINA-M
 */
public interface PACProcesosDAO {

    public List getListaPACProcesos(BeanPACProcesos objBeanPACProcesos, String usuario);

    public ArrayList getListaPACProcesosDetalle(BeanPACProcesos objBeanPACProcesos, String usuario);

    public ArrayList getListaPACProcesosAfectacion(BeanPACProcesos objBeanPACProcesos, String usuario);

    public BeanPACProcesos getPACProcesos(BeanPACProcesos objBeanPACProcesos, String usuario);

    public BeanPACProcesos getPACProcesosDetalle(BeanPACProcesos objBeanPACProcesos, String usuario);

    public int iduPACProcesos(BeanPACProcesos objBeanPACProcesos, String usuario);

    public int iduPACProcesosDetalle(BeanPACProcesos objBeanPACProcesos, String usuario);

    public int iduPACProcesosAfectacion(BeanPACProcesos objBeanPACProcesos, String usuario);

    public int iduPACProcesosDetalleContratoAfectacion(BeanPACProcesos objBeanPACProcesos, String usuario);

}
