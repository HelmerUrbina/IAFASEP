/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.DataService.Despachadores;

import com.iafas.BusinessServices.Beans.BeanMesaParte;
import java.util.List;

/**
 *
 * @author H-URBINA-M
 */
public interface MesaParteDAO {

    public List getListaMesaPartes(BeanMesaParte objBeanMesaParte, String usuario);

    public List getListaMesaPartesConsulta(BeanMesaParte objBeanMesaParte, String usuario);

    public BeanMesaParte getMesaParte(BeanMesaParte objBeanMesaParte, String usuario);

    public String getNumeroMesaParte(BeanMesaParte objBnMesaParte, String usuario);

    public int iduMesaParte(BeanMesaParte objBeanMesaParte, String usuario);

    public List getListaRemisionMesaParte(BeanMesaParte objBeanMesaParte, String usuario);

    public String getNumeroMesaParteSalida(BeanMesaParte objBnMesaParte, String usuario);
}
