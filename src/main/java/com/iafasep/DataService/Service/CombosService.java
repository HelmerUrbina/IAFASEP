package com.iafasep.DataService.Service;

import java.util.List;
import com.iafasep.BusinessServices.Beans.BeanCombos;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
public interface CombosService {

    /*MODULO CONFIGURACION*/
    public List<BeanCombos> getPeriodos();

    public List<BeanCombos> getMeses();

    public List<BeanCombos> getAreaLaboral();

    public List<BeanCombos> getRol();

    public List<BeanCombos> getDepartamentos();

    public List<BeanCombos> getProvincias(String departamento);

    public List<BeanCombos> getUbigeo(String departamento, String provincia);

    public List<BeanCombos> getMonedas();

    /*MODULO MESA DE PARTES*/
    public List<BeanCombos> getInstituciones();

    public List<BeanCombos> getClasificacionDocumentos();

    public List<BeanCombos> getTiposDocumentos();

    public List<BeanCombos> getPrioridades();

    public List<BeanCombos> getTipoDecretos();

    public List<BeanCombos> getUsuarioJefatura();

    public List<BeanCombos> getUsuarioAreaLaboral(String areaLaboral);

    public List<BeanCombos> getRemisionDocumentosInstitucion(String periodo, Integer codigo);

    /*PRESUPUESTO*/
    public List<BeanCombos> getFuenteFinanciamiento();

    public List<BeanCombos> getTipoNotasModificatorias();

    public List<BeanCombos> getResolucionesByPeriodoFuenteFinanciamiento(String periodo, Integer fuenteFinanciamiento);

    public List<BeanCombos> getTareaPresupuestal();

    public List<BeanCombos> getClasificadorPresupuestalByPeriodoTareaPresupuestal(String periodo, Integer tareaPresupuestal);

    public List<BeanCombos> getFuenteFinanciamientoNotaModificatoria(String periodo);

    public List<BeanCombos> getResolucionesNotaModificatoria(String periodo, Integer fuenteFinanciamiento);

    public List<BeanCombos> getTareaPresupuestalNotaModificatoria(String periodo, Integer fuenteFinanciamiento, Integer resolucion);

    public List<BeanCombos> getClasificadorPresupuestalNotaModificatoria(String periodo, Integer fuenteFinanciamiento, Integer resolucion, Integer tareaPresupuestal);

    public List<BeanCombos> getResolucionCertificadoPresupuestal(String periodo, Integer fuenteFinanciamiento);

    public List<BeanCombos> getResolucionCertificadoPresupuestalRebaja(String periodo, Integer fuenteFinanciamiento, Integer certificado);

    public List<BeanCombos> getTareaPresupuestalCertificadoPresupuestal(String periodo, Integer fuenteFinanciamiento, Integer resolucion);

    public List<BeanCombos> getTareaPresupuestalCertificadoPresupuestalRebaja(String periodo, Integer fuenteFinanciamiento, Integer certificado, Integer resolucion);

    public List<BeanCombos> getClasificadorPresupuestalCertificadoPresupuestal(String periodo, Integer fuenteFinanciamiento, Integer resolucion, Integer tareaPresupuestal);

    public List<BeanCombos> getClasificadorPresupuestalCertificadoPresupuestalRebaja(String periodo, Integer fuenteFinanciamiento, Integer certificado, Integer resolucion, Integer tareaPresupuestal);

    public List<BeanCombos> getFuenteByProgramacion();

    public List<BeanCombos> getTareaByProgramacion(String periodo, Integer fuente);

    public List<BeanCombos> getAnioByProgramacion(String periodo);

    public List<BeanCombos> getClasificadorProgByPeriodoFuenteTarea(String periodo, Integer fuente, Integer tarea);

    public List<BeanCombos> getunidadMedida();

    public List<BeanCombos> getItemByUnidadMedida();

}
