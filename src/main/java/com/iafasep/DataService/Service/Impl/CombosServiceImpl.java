package com.iafasep.DataService.Service.Impl;

import java.util.List;
import com.iafasep.BusinessServices.Beans.BeanCombos;
import com.iafasep.DataService.DAO.CombosDAO;
import com.iafasep.DataService.Service.CombosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Service
public class CombosServiceImpl implements CombosService {

    @Autowired
    private CombosDAO combosDAO;

    /*MODULO CONFIGURACION*/
    @Override
    public List<BeanCombos> getPeriodos() {
        return combosDAO.getPeriodos();
    }

    @Override
    public List<BeanCombos> getMeses() {
        return combosDAO.getMeses();
    }

    @Override
    public List<BeanCombos> getAreaLaboral() {
        return combosDAO.getAreaLaboral();
    }

    @Override
    public List<BeanCombos> getRol() {
        return combosDAO.getRol();
    }

    @Override
    public List<BeanCombos> getDepartamentos() {
        return combosDAO.getDepartamentos();
    }

    @Override
    public List<BeanCombos> getProvincias(String departamento) {
        return combosDAO.getProvincias(departamento);
    }

    @Override
    public List<BeanCombos> getUbigeo(String departamento, String provincia) {
        return combosDAO.getUbigeo(departamento, provincia);
    }

    @Override
    public List<BeanCombos> getMonedas() {
        return combosDAO.getMonedas();
    }


    /*MODULO MESA DE PARTES*/
    @Override
    public List<BeanCombos> getInstituciones() {
        return combosDAO.getInstituciones();
    }

    @Override
    public List<BeanCombos> getClasificacionDocumentos() {
        return combosDAO.getClasificacionDocumentos();
    }

    @Override
    public List<BeanCombos> getTiposDocumentos() {
        return combosDAO.getTiposDocumentos();
    }

    @Override
    public List<BeanCombos> getPrioridades() {
        return combosDAO.getPrioridades();
    }

    @Override
    public List<BeanCombos> getTipoDecretos() {
        return combosDAO.getTipoDecretos();
    }

    @Override
    public List<BeanCombos> getUsuarioJefatura() {
        return combosDAO.getUsuarioJefatura();
    }

    @Override
    public List<BeanCombos> getUsuarioAreaLaboral(String areaLaboral) {
        return combosDAO.getUsuarioAreaLaboral(areaLaboral);
    }

    /*PRESUPUESTO*/
    @Override
    public List<BeanCombos> getFuenteFinanciamiento() {
        return combosDAO.getFuenteFinanciamiento();
    }

    @Override
    public List<BeanCombos> getTipoNotasModificatorias() {
        return combosDAO.getTipoNotasModificatorias();
    }

    @Override
    public List<BeanCombos> getResolucionesByPeriodoFuenteFinanciamiento(String periodo, Integer fuenteFinanciamiento) {
        return combosDAO.getResolucionesByPeriodoFuenteFinanciamiento(periodo, fuenteFinanciamiento);
    }

    @Override
    public List<BeanCombos> getTareaPresupuestal() {
        return combosDAO.getTareaPresupuestal();
    }

    @Override
    public List<BeanCombos> getClasificadorPresupuestalByPeriodoTareaPresupuestal(String periodo, Integer tareaPresupuestal) {
        return combosDAO.getClasificadorPresupuestalByPeriodoTareaPresupuestal(periodo, tareaPresupuestal);
    }

    @Override
    public List<BeanCombos> getFuenteFinanciamientoNotaModificatoria(String periodo) {
        return combosDAO.getFuenteFinanciamientoNotaModificatoria(periodo);
    }

    @Override
    public List<BeanCombos> getResolucionesNotaModificatoria(String periodo, Integer fuenteFinanciamiento) {
        return combosDAO.getResolucionesNotaModificatoria(periodo, fuenteFinanciamiento);
    }

    @Override
    public List<BeanCombos> getTareaPresupuestalNotaModificatoria(String periodo, Integer fuenteFinanciamiento, Integer resolucion) {
        return combosDAO.getTareaPresupuestalNotaModificatoria(periodo, fuenteFinanciamiento, resolucion);
    }

    @Override
    public List<BeanCombos> getClasificadorPresupuestalNotaModificatoria(String periodo, Integer fuenteFinanciamiento, Integer resolucion, Integer tareaPresupuestal) {
        return combosDAO.getClasificadorPresupuestalNotaModificatoria(periodo, fuenteFinanciamiento, resolucion, tareaPresupuestal);
    }

    @Override
    public List<BeanCombos> getResolucionCertificadoPresupuestal(String periodo, Integer fuenteFinanciamiento) {
        return combosDAO.getResolucionCertificadoPresupuestal(periodo, fuenteFinanciamiento);
    }

    @Override
    public List<BeanCombos> getResolucionCertificadoPresupuestalRebaja(String periodo, Integer fuenteFinanciamiento, Integer certificado) {
        return combosDAO.getResolucionCertificadoPresupuestalRebaja(periodo, fuenteFinanciamiento, certificado);
    }

    @Override
    public List<BeanCombos> getTareaPresupuestalCertificadoPresupuestal(String periodo, Integer fuenteFinanciamiento, Integer resolucion) {
        return combosDAO.getTareaPresupuestalCertificadoPresupuestal(periodo, fuenteFinanciamiento, resolucion);
    }

    @Override
    public List<BeanCombos> getTareaPresupuestalCertificadoPresupuestalRebaja(String periodo, Integer fuenteFinanciamiento, Integer certificado, Integer resolucion) {
        return combosDAO.geTareaPresupuestalCertificadoPresupuestalRebaja(periodo, fuenteFinanciamiento, certificado, resolucion);
    }

    @Override
    public List<BeanCombos> getClasificadorPresupuestalCertificadoPresupuestal(String periodo, Integer fuenteFinanciamiento, Integer resolucion, Integer tareaPresupuestal) {
        return combosDAO.getClasificadorPresupuestalCertificadoPresupuestal(periodo, fuenteFinanciamiento, resolucion, tareaPresupuestal);
    }

    @Override
    public List<BeanCombos> getClasificadorPresupuestalCertificadoPresupuestalRebaja(String periodo, Integer fuenteFinanciamiento, Integer certificado, Integer resolucion, Integer tareaPresupuestal) {
        return combosDAO.geClasificadorPresupuestalCertificadoPresupuestalRebaja(periodo, fuenteFinanciamiento, certificado, resolucion, tareaPresupuestal);
    }

}
