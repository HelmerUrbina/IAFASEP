/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.DataService.Despachadores;

import com.iafas.BusinessServices.Beans.BeanMesaParte;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author H-URBINA-M
 */
public interface DecretosDAO {

    public List getListaDocumentosPendientes(BeanMesaParte objBnDecreto, String usuario);

    public List getListaDocumentosDecretados(BeanMesaParte objBnDecreto, String usuario);

    public List getListaDocumentosRespuesta(BeanMesaParte objDocumento, String usuario);

    public int iduDecreto(BeanMesaParte objBnDecreto, String usuario);

    public int iduDecretarTipoDecreto(BeanMesaParte objBnDecreto, String usuario);

    public String getDocumentosPendientes(String usuario);

    public BeanMesaParte getDecreto(BeanMesaParte objBeanDecreto, String usuario);

    public ArrayList getListaDecretoTipoDecretos(BeanMesaParte objBeanDecreto, String usuario);

    public ArrayList getListaDetalleDocumentoDecretado(BeanMesaParte objBeanDecreto, String usuario);

}
