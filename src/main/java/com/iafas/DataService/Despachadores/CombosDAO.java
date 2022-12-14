/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iafas.DataService.Despachadores;

import java.util.List;

/**
 *
 * @author H-URBINA-M
 */
public interface CombosDAO {

    //CONSULTAS GENERALES
    public List getPeriodos();

    public List getMeses();

    public List getFuenteFinanciamiento();

    public List getCategoriaPresupuestal();

    public List getProductos();

    public List getActividades();

    public List getTarea();

    public List getGenericaGasto();

    public List getGenericaGasto(String periodo, Integer presupuesto);

    public List getTipoMonedas();

    public List getProveedores();

    public List getUnidadMedida();

    public List getCadenaGasto();

    public List getCadenaIngreso();

    public List getAreaLaboral();

    public List getTipoResolucion();

    public List getGrado();

    public List getGrados(String nivel);

    public List getParentesco();

    public List getDistritoMovilidad();

    //SEGURIDAD
    public List getModulos();

    //PLANEAMIENTO 
    public List getObjetivosEstrategicos(String periodo);

    public List getAccionesEstrategicas(String periodo, String objetivo);

    public List getTareaOperativa();

    public List getUbigeoActividadPresupuestal(String periodo, String categoriaPresupuestal, String producto, String actividad);

    //EJECUCION PRESUPUESTAL - CERTIFICACION    
    public List getPAACProcesos(String periodo, Integer presupuesto, String tipoCertificado);

    public List getIDPCertificado(String periodo, Integer presupuesto, String unidadOperativa);

    public List getResolucionesCertificado(String periodo, Integer presupuesto,
            String tipoCertificado, String solicitudCredito, String opinionPresupuestal);

    public List getTareaCertificado(String periodo, Integer presupuesto, Integer resolucion,
            String tipoCertificado, String solicitudCredito, String opinionPresupuestal);

    public List getCadenaGastoCertificado(String periodo, Integer presupuesto, Integer resolucion,
            String tarea, String tipoCertificado, String solicitudCredito, String opinionPresupuestal);

    //EJECUCION PRESUPUESTAL - COMPROMISO ANUAL
    public List getSolicitudCreditoUnidad(String periodo, Integer presupuesto, String unidadOperativa);

    public List getProveedorCompromiso(String periodo, Integer presupuesto, String unidadOperativa, String compromisoAnual, String tipo);

    public List getResolucionesCompromiso(String periodo, Integer presupuesto, String unidadOperativa, String tipoCalendario, String solicitudCredito,
            String proveedor, String tipo, String compromisoAnual);

    public List getDependenciaCompromiso(String periodo, Integer presupuesto, String unidadOperativa, String tipoCalendario, Integer resolucion,
            String solicitudCredito, String proveedor, String tipo, String compromisoAnual);

    public List getSecuenciaFuncionalCompromiso(String periodo, Integer presupuesto, String unidadOperativa, String tipoCalendario, Integer resolucion,
            String dependencia, String solicitudCredito, String proveedor, String tipo, String compromisoAnual);

    public List getTareaCompromiso(String periodo, Integer presupuesto, String unidadOperativa, String tipoCalendario, Integer resolucion,
            String dependencia, String secuenciaFuncional, String solicitudCredito, String proveedor, String tipo, String compromisoAnual);

    public List getCadenaGastoCompromiso(String periodo, Integer presupuesto, String unidadOperativa, String tipoCalendario, Integer resolucion,
            String dependencia, String secuenciaFuncional, String tarea, String solicitudCredito, String proveedor, String tipo, String compromisoAnual);

    //EJECUCION PRESUPUESTAL - DECLARACION JURADA
    public List getCompromisoAnualUnidad(String periodo, Integer presupuesto, String unidadOperativa);

    public List getProveedorDeclaracionJurada(String periodo, Integer presupuesto, String unidadOperativa, String compromisoAnual, String tipoCalendario,
            String cobertura);

    public List getResolucionesDeclaracionJurada(String periodo, Integer presupuesto, String unidadOperativa, String compromisoAnual, String tipoCalendario,
            String cobertura, String proveedor);

    public List getDependenciaDeclaracionJurada(String periodo, Integer presupuesto, String unidadOperativa, String compromisoAnual, String tipoCalendario,
            String cobertura, String proveedor, Integer resolucion);

    public List getSecuenciaFuncionalDeclaracionJurada(String periodo, Integer presupuesto, String unidadOperativa, String compromisoAnual, String tipoCalendario,
            String cobertura, String proveedor, Integer resolucion, String dependencia);

    public List getTareaDeclaracionJurada(String periodo, Integer presupuesto, String unidadOperativa, String compromisoAnual, String tipoCalendario,
            String cobertura, String proveedor, Integer resolucion, String dependencia, String secuenciaFuncional);

    public List getCadenaGastoDeclaracionJurada(String periodo, Integer presupuesto, String unidadOperativa, String compromisoAnual, String tipoCalendario,
            String cobertura, String proveedor, Integer resolucion, String dependencia, String secuenciaFuncional, String tarea);

    public List getPeriodoResolucion(String tipo);

    public List getBeneficioJADPE(String tipo, String periodo);

    //EJECUCION PRESUPUESTAL - NOTA MODIFICATORIA
    public List getTipoNotaModificatoria();

    public List getPresupuestoNotaModificatoria(String periodo, String tipoNota, String tipo);

    public List getResolucionNotaModificatoria(String periodo, String tipoNota, String tipo, Integer presupuesto);

    public List getTareaNotaModificatoria(String periodo, String tipoNota, String tipo, Integer presupuesto, Integer resolucion);

    public List getCadenaGastoNotaModificatoria(String periodo, String tipoNota, String tipo, Integer presupuesto,
            Integer resolucion, String tarea);

    //EJECUCION PRESUPUESTAL - DISPONIBILIDAD PRESUPUESTAL
    public List getEntidadSolicitante();

    public List getInformeResolucionCargo(String periodo, Integer presupuesto, String unidadOperativa);

    public List getInformeDependenciaCargo(String periodo, Integer presupuesto, String unidadOperativa, Integer resolucion);

    public List getInformeSecuenciaFuncionalCargo(String periodo, Integer presupuesto, String unidadOperativa, Integer resolucion,
            String dependencia);

    public List getInformeTareaCargo(String periodo, Integer presupuesto, String unidadOperativa, Integer resolucion, String dependencia,
            String secuenciaFuncional);

    public List getInformeCadenaGastoCargo(String periodo, Integer presupuesto, String unidadOperativa, Integer resolucion, String dependencia,
            String secuenciaFuncional, String tarea);

    //EJECUCION PRESUPUESTAL - ACTIVIDAD - TAREA PRESUPUESTAL
    public List getCategoriaPresupuestalEjecucion(String periodo);

    public List getProductoEjecucion(String periodo, String categoriaPresupuestal);

    public List getActividadEjecucion(String periodo, String categoriaPresupuestal, String producto);

    public List getFinalidadEjecucion(String periodo, String categoriaPresupuestal, String producto, String Actividad);

    //PROGRAMACI??N PRESUPUESTAL
    public List getDependenciaFuerzaOperativa(String periodo, Integer presupuesto, String unidadOperativa);

    public List getItemCadenaGasto(String codigo);

    public List getCadenaGastoTarea(String periodo, String tarea);

    public List getCadenaIngresoEstimacion(String periodo, String unidadOperativa, Integer presupuesto);

    public List getSecuenciaFuncionalProgramacion(String periodo, Integer presupuesto, String tarea);

    public List getCadenaGastoProgramacionMultianual(String periodo, Integer presupuesto, String unidadOperativa,
            String tarea, String dependencia);

    public List getDependenciaProgramacionMultianual(String periodo, Integer presupuesto, String unidadOperativa, String tarea);

    //UBIGEO
    public List getDepartamento();

    public List getProvincia(String departamento);

    public List getDistrito(String departamento, String provincia);

    public List getTipoFuerzaOperativa();

    //ACTIVIDAD - TAREA PROGRAMACI??N
    public List getCategoriaPresupuestalProgramacion(String periodo);

    public List getProductoProgramacion(String periodo, String categoriaPresupuestal);

    public List getActividadProgramacion(String periodo, String categoriaPresupuestal, String producto);

    public List getFinalidadProgramacion(String periodo, String categoriaPresupuestal, String producto, String Actividad);

    public List getActividadUbigeo(String periodo, String categoriaPresupuestal, String producto, String Actividad);

    public List getConceptoRemuneraciones();

    public List getNivelGrado();

    public List getCadenaGastoPerS(String periodo);

    public List getTareaPersonal(String periodo, String cadenaGasto);

    public List getNivelGradoPersonal(String periodo, String codConcepto);

    public List getGradoPersonal(String periodo, String codConcepto, String nivelGrado);

    public List getperiodoReePersonal(String periodo, String codConcepto, String nivelGrado, String grado);

    public List getDependenciaFuerzaOperativa(String periodo, String departamento);

    public List getCadenaGastoHorasVuelo();

    public List getListaTareaHorasVuelo(String periodo);

    //MESA DE PARTES
    public List getInstituciones();

    public List getUsuarioJefatura();

    public List getUsuarioAreaLaboral(String areaLaboral);

    public List getPrioridades();

    public List getDocumentos();

    public List getClasificacionDocumento();

    public List getTipoDecretos();

    public List getDocumentoReferencia(String periodo, String tipo);

    public List getUsuarioDocumento(String usuario);

    public List getDocumentoReferencia(String periodo, String tipo, String usuario);

    public List getUsuarioSubDecreto(String area, String codusu);

    public List getGenericaGastoUnidad(String periodo, Integer presupuesto, String unidadOperativa);

    //PERSONAL
    public List getPersonalOpre();

    //LOGISTICA
    public List getTipoProcesoContratacion();

    public List getProcesoEtapa(Integer tipoProcesoContratacion);

    public List getProcesoDocumento();

    public List getTipoProcedimiento();

    public List getTipoProcedimientoTipoDocumento(String tipoDocumento);

    public List getCompromisoAnualCertificado(String periodo, Integer presupuesto, String unidadOperativa, String cerificado);
}
