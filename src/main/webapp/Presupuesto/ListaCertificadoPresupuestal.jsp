<%-- 
    Document   : ListaCertificadoPresupuestal
    Created on : 08/04/2016, 03:54:51 PM
    Author     : H-URBINA-M
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    var periodo = $("#cbo_Periodo").val();
    var presupuesto = $("#cbo_Presupuesto").val();
    var codigo = null;
    var detalle = null;
    var estado = null;
    var mode = null;
    var tipoSolicitud = null;
    var indiceDetalle = -1;
    var modeDetalle = null;
    var tipoCertificado = null;
    var reporte = null;
    var cobertura = null;
    var firmaJefe = null;
    var firmaSubJefe = null;
    var archivo = '';
    var sustento = null;
    var lista = new Array();
    <c:forEach var="c" items="${objCertificado}">
    var result = {certificado: '${c.certificado}', anexoCertificado: '${c.anexoCertificado}',
        detalle: '${c.concepto}', documentoReferencia: "${c.documentoReferencia}", fecha: '${c.fecha}', paac: '${c.procesoSeleccion}',
        importe: '${c.importe}', tipoCambio: '${c.tipoCambio}', monedaExtranjera: '${c.monedaExtranjera}', estado: '${c.estado}',
        tipo: '${c.tipo}', nroSolicitud: '${c.cadenaGasto}',
        opinion: '${c.dependencia}', firmaJefe: '${c.firmaJefe}', firmaSubJefe: '${c.firmaSubJefe}', fechaAprobacion: '${c.unidad}',
        priorizacion: '${c.tareaPresupuestal}', sectorista: '${c.sectorista}', archivo: '${c.archivo}'};
    lista.push(result);
    </c:forEach>
    var listaDet = new Array();
    <c:forEach var="d" items="${objCertificadoDetalle}">
    var result = {certificado: '${d.certificado}', tareaPresupuestal: '${d.tareaPresupuestal}', cadenaGasto: '${d.cadenaGasto}',
        importe: '${d.importe}', tipoCambio: '${d.tipoCambio}', monedaExtranjera: '${d.monedaExtranjera}'};
    listaDet.push(result);
    </c:forEach>
    $(document).ready(function () {
        var sourceNuevo = {
            datafields: [
                {name: "codigo", type: "string"},
                {name: "tarea", type: "string"},
                {name: "cadena", type: "string"},
                {name: "importe", type: "number"},
                {name: "extranjera", type: "number"},
                {name: "resolucion", type: "string"}
            ],
            pager: function (pagenum, pagesize, oldpagenum) {
                // callback called when a page or page size is changed.
            }
        };
        var dataNuevo = new $.jqx.dataAdapter(sourceNuevo);
        //PARA LA GRILLA DE LA CABECERA
        var sourceCab = {
            localdata: lista,
            datatype: "array",
            datafields: [
                {name: "solicitud", type: "string"},
                {name: "certificado", type: "string"},
                {name: "anexoCertificado", type: "string"},
                {name: "detalle", type: "string"},
                {name: "documentoReferencia", type: "string"},
                {name: "fecha", type: "string"},
                {name: "importe", type: "number"},
                {name: "tipoCambio", type: "number"},
                {name: "monedaExtranjera", type: "number"},
                {name: "estado", type: "string"},
                {name: "tipo", type: "string"},
                {name: "nroSolicitud", type: "string"},
                {name: "opinion", type: "string"},
                {name: "paac", type: "string"},
                {name: "firmaJefe", type: "string"},
                {name: "firmaSubJefe", type: "string"},
                {name: "fechaAprobacion", type: "string"},
                {name: "sectorista", type: "string"},
                {name: "priorizacion", type: "string"},
                {name: "archivo", type: "string"}
            ],
            root: "CertificadoPresupuestal",
            record: "Certificado",
            id: 'certificado'
        };
        //PARA LA GRILLA DEL DETALLE 
        var sourceDet = {
            localdata: listaDet,
            datatype: "array",
            datafields: [
                {name: "certificado", type: "string"},
                {name: "correlativo", type: "string"},
                {name: "tareaPresupuestal", type: "string"},
                {name: "cadenaGasto", type: "string"},
                {name: "importe", type: "number"},
                {name: "tipoCambio", type: "number"},
                {name: "monedaExtranjera", type: "number"}
            ],
            root: "DetalleCertificado",
            record: "Detalle",
            id: 'certificado',
            async: false
        };
        var dataAdapter = new $.jqx.dataAdapter(sourceDet, {autoBind: true});
        nested = dataAdapter.records;
        var nestedGrids = new Array();
        // create nested grid.
        var initRowDetails = function (index, parentElement, gridElement, record) {
            var id = record.uid.toString();
            var grid = $($(parentElement).children()[0]);
            nestedGrids[index] = grid;
            var filtergroup = new $.jqx.filter();
            var filterValue = id;
            var filterCondition = 'equal';
            var filter = filtergroup.createfilter('stringfilter', filterValue, filterCondition);
            // fill the orders depending on the id.
            var ordersbyid = [];
            for (var m = 0; m < nested.length; m++) {
                var result = filter.evaluate(nested[m]["certificado"]);
                if (result)
                    ordersbyid.push(nested[m]);
            }
            var sourceNested = {
                datafields: [
                    {name: "certificado", type: "string"},
                    {name: "codigo", type: "string"},
                    {name: "tareaPresupuestal", type: "string"},
                    {name: "cadenaGasto", type: "string"},
                    {name: "importe", type: "number"},
                    {name: "tipoCambio", type: "number"},
                    {name: "monedaExtranjera", type: "number"}
                ],
                id: 'certificado',
                localdata: ordersbyid
            };
            var nestedGridAdapter = new $.jqx.dataAdapter(sourceNested);
            if (grid !== null) {
                grid.jqxGrid({
                    source: nestedGridAdapter,
                    width: '97%',
                    height: 320,
                    pageable: true,
                    filterable: true,
                    autoshowfiltericon: true,
                    columnsresize: true,
                    showaggregates: true,
                    showfilterrow: true,
                    showstatusbar: true,
                    statusbarheight: 20,
                    columns: [
                        {text: 'NRO', datafield: 'codigo', filterable: false, width: '3%', align: 'center', cellsAlign: 'center'},
                        {text: 'TAREA', datafield: 'tareaPresupuestal', filtertype: 'checkedlist', width: '30%', align: 'center'},
                        {text: 'CADENA', datafield: 'cadenaGasto', filtertype: 'checkedlist', width: '40%', align: 'center', aggregates: [{'<b>Totales : </b>':
                                            function () {
                                                return  "";
                                            }}]},
                        {text: 'IMPORTE', dataField: 'importe', width: '10%', align: 'center', cellsAlign: 'right', cellsFormat: 'f2', cellclassname: cellclass, aggregates: ['sum']},
                        {text: 'T/CAMBIO', dataField: 'tipoCambio', width: '7%', align: 'center', cellsAlign: 'right', cellsFormat: 'f2', cellclassname: cellclass},
                        {text: 'EXTRANJERA', dataField: 'monedaExtranjera', width: '10%', align: 'center', cellsAlign: 'right', cellsFormat: 'f2', cellclassname: cellclass, aggregates: ['sum']}
                    ]
                });
            }
        };
        var cellclass = function (row, datafield, value, rowdata) {
            if (rowdata['estado'] === "ANULADA") {
                return "RowAnulado";
            }
            if (datafield === "codigo" || datafield === "certificado" || datafield === "tipoCambio") {
                return "RowBold";
            }
            if (datafield === "anexoCertificado") {
                return "RowGreen";
            }
            if (datafield === "importe" && rowdata['importe'] >= 0.0) {
                return "RowBlue";
            }
            if (datafield === "importe" && rowdata['importe'] < 0.0) {
                return "RowRed";
            }
            if (datafield === "monedaExtranjera") {
                return "RowBrown";
            }
        };
        $("#div_GrillaPrincipal").jqxGrid({
            width: '99.8%',
            height: ($(window).height() - 100),
            source: sourceCab,
            rowdetails: true,
            autoheight: false,
            autorowheight: false,
            altrows: true,
            sortable: true,
            pageable: true,
            filterable: true,
            autoshowfiltericon: true,
            columnsresize: true,
            showfilterrow: true,
            editable: false,
            showstatusbar: true,
            showtoolbar: true,
            showaggregates: true,
            statusbarheight: 20,
            rendertoolbar: function (toolbar) {
                // Appends buttons to the status bar.
                var container = $("<div style='overflow: hidden; position: relative; margin: 1px;'></div>");
                var addCabeceraButton = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px;' src='../Imagenes/Botones/nuevo42.gif' width=18 height=18 /><span style='margin-left: 4px; position: relative; top: -3px;'></span></div>");
                var exportButton = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px;' src='../Imagenes/Botones/pauf42.gif' width=18 height=18/><span style='margin-left: 4px; position: relative; top: -3px;'></span></div>");
                var reporteButton = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px;' src='../Imagenes/Botones/printer42.gif' width=18 height=18/><span style='margin-left: 4px; position: relative; top: -3px;'> </span></div>");
                container.append(addCabeceraButton);
                container.append(exportButton);
                container.append(reporteButton);
                toolbar.append(container);
                addCabeceraButton.jqxButton({width: 30, height: 22});
                addCabeceraButton.jqxTooltip({position: 'bottom', content: "Nuevo Registro"});
                exportButton.jqxButton({width: 30, height: 22});
                exportButton.jqxTooltip({position: 'bottom', content: "Exportar Datos"});
                reporteButton.jqxButton({width: 30, height: 22});
                reporteButton.jqxTooltip({position: 'bottom', content: "Reportes"});
                // Adicionar un Nuevo Registro en la Cabecera.
                addCabeceraButton.click(function (event) {
                    tipoSolicitud = 'CE';
                    mode = 'I';
                    fn_NuevoCab('');
                });
                //EXPORTAR A EXCEL
                exportButton.click(function (event) {
                    $("#div_GrillaPrincipal").jqxGrid('exportdata', 'xls', 'SolicitudCreditoPresupuestal');
                });
                //ABRIR LOS REPORTES
                reporteButton.click(function (event) {
                    $('#div_Reporte').jqxWindow({isModal: true, modalOpacity: 0.9});
                    $('#div_Reporte').jqxWindow('open');
                });
            },
            initRowDetails: initRowDetails,
            rowDetailsTemplate: {rowdetails: "<div id='grid' style='margin: 3px;'></div>", rowdetailsheight: 350, rowdetailshidden: true},
            columns: [
                {text: 'CERTIFICADO', dataField: 'certificado', width: '6%', align: 'center', cellsAlign: 'center', cellclassname: cellclass},
                {text: 'CONCEPTO', dataField: 'detalle', width: '25%', align: 'center', cellclassname: cellclass},
                {text: 'DOCU. REFERENCIA', dataField: 'documentoReferencia', width: '15%', align: 'center', cellclassname: cellclass, aggregates: [{'<b>Totales : </b>':
                                    function () {
                                        return "";
                                    }}]},
                {text: 'FECHA', dataField: 'fecha', columntype: 'datetimeinput', filtertype: 'date', width: '7%', align: 'center', cellsAlign: 'center', cellsFormat: 'd', cellclassname: cellclass},
                {text: 'IMPORTE', dataField: 'importe', width: '10%', align: 'center', cellsAlign: 'right', cellsFormat: 'f2', cellclassname: cellclass, aggregates: ['sum']},
                {text: 'T/CAMBIO', dataField: 'tipoCambio', width: '4%', align: 'center', cellsAlign: 'right', cellsFormat: 'f2', cellclassname: cellclass},
                {text: 'EXTRANJERA', dataField: 'monedaExtranjera', width: '7%', align: 'center', cellsAlign: 'right', cellsFormat: 'f2', cellclassname: cellclass, aggregates: ['sum']},
                {text: 'ESTADO', dataField: 'estado', filtertype: 'checkedlist', width: '7%', align: 'center', cellsAlign: 'center', cellclassname: cellclass},
                {text: 'TIPO', dataField: 'tipo', filtertype: 'checkedlist', width: '8%', align: 'center', cellsAlign: 'center', cellclassname: cellclass},
                {text: 'NRO ANEXO', dataField: 'anexoCertificado', width: '6%', align: 'center', cellsAlign: 'center', cellclassname: cellclass},
                {text: 'PAC', dataField: 'paac', width: '20%', align: 'center', cellclassname: cellclass},
                {text: 'I.D.P.', dataField: 'opinion', width: '4%', align: 'center', cellsAlign: 'center', cellclassname: cellclass}
            ]
        });
        // DEFINIMOS EL MENU CONTEXTUAL
        var alto = 180;
        var contextMenu = $("#div_ContextMenu").jqxMenu({width: 200, height: alto, autoOpenPopup: false, mode: 'popup'});
        $("#div_GrillaPrincipal").on('contextmenu', function () {
            return false;
        });
        // handle context menu clicks.
        $('#div_GrillaPrincipal').on('rowclick', function (event) {
            if (event.args.rightclick) {
                $("#div_GrillaPrincipal").jqxGrid('selectrow', event.args.rowindex);
                var scrollTop = $(window).scrollTop();
                var scrollLeft = $(window).scrollLeft();
                if (parseInt(event.args.originalEvent.clientY) > 600) {
                    scrollTop = scrollTop - alto;
                }
                contextMenu.jqxMenu('open', parseInt(event.args.originalEvent.clientX) + 5 + scrollLeft, parseInt(event.args.originalEvent.clientY) + 5 + scrollTop);
                return false;
            }
        });
        $('#div_ContextMenu').on('itemclick', function (event) {
            var opcion = event.args;
            if (codigo === null || codigo === '') {
                $.alert({
                    theme: 'material',
                    title: 'AVISO DEL SISTEMA',
                    content: 'Debe Seleccionar un Registro',
                    animation: 'zoom',
                    closeAnimation: 'zoom',
                    type: 'orange',
                    typeAnimated: true
                });
            } else if (estado !== 'ANULADO') {
                if ($.trim($(opcion).text()) === "Editar") {
                    mode = 'U';
                    fn_EditarCab();
                } else if ($.trim($(opcion).text()) === "Anular") {
                    $.confirm({
                        theme: 'material',
                        title: 'AVISO DEL SISTEMA',
                        content: '¿Desea Anular este Registro?',
                        animation: 'zoom',
                        closeAnimation: 'zoom',
                        type: 'orange',
                        typeAnimated: true,
                        buttons: {
                            aceptar: {
                                text: 'Aceptar',
                                btnClass: 'btn-primary',
                                keys: ['enter', 'shift'],
                                action: function () {
                                    mode = 'D';
                                    $.ajax({
                                        type: "POST",
                                        url: "../IduCertificadoPresupuestal",
                                        data: {mode: 'D', periodo: periodo, presupuesto: presupuesto, nroCertificado: codigo},
                                        success: function (data) {
                                            msg = data;
                                            if (msg === "GUARDO") {
                                                $.confirm({
                                                    title: 'AVISO DEL SISTEMA',
                                                    content: 'Datos procesados correctamente',
                                                    type: 'green',
                                                    typeAnimated: true,
                                                    autoClose: 'cerrarAction|1000',
                                                    buttons: {
                                                        cerrarAction: {
                                                            text: 'Cerrar',
                                                            action: function () {
                                                                fn_Refrescar();
                                                            }
                                                        }
                                                    }
                                                });
                                            } else {
                                                $.alert({
                                                    theme: 'material',
                                                    title: 'AVISO DEL SISTEMA',
                                                    content: msg,
                                                    animation: 'zoom',
                                                    closeAnimation: 'zoom',
                                                    type: 'red',
                                                    typeAnimated: true
                                                });
                                            }
                                        }
                                    });
                                }
                            },
                            cancelar: function () {
                            }
                        }
                    });
                } else if ($.trim($(opcion).text()) === "Cerrar") {
                    $('#div_VentanaCerrar').jqxWindow({isModal: true});
                    $('#div_VentanaCerrar').jqxWindow('open');
                } else if ($.trim($(opcion).text()) === "Ver Anexos") {
                    if (archivo !== '') {
                        document.location.target = "_blank";
                        document.location.href = "../Descarga?opcion=CertificadoPresupuestal&periodo=" + periodo + "&presupuesto=" + presupuesto + "&codigo=" + codigo + "&documento=" + archivo;
                    } else {
                        $.alert({
                            theme: 'material',
                            title: 'AVISO DEL SISTEMA',
                            content: 'No existe Archivo a Vizualizar!!!',
                            animation: 'zoom',
                            closeAnimation: 'zoom',
                            type: 'red',
                            typeAnimated: true
                        });
                    }
                } else if ($.trim($(opcion).text()) === "Ampliaciones") {
                    if (estado === 'CERRADO' && tipoCertificado === 'CERTIFICADO') {
                        tipoSolicitud = 'AM';
                        mode = 'I';
                        fn_NuevoCab(codigo);
                    } else {
                        $.alert({
                            theme: 'material',
                            title: 'AVISO DEL SISTEMA',
                            content: 'Debe seleccionar un registro Cerrado y Tipo Certificado.',
                            animation: 'zoom',
                            closeAnimation: 'zoom',
                            type: 'red',
                            typeAnimated: true
                        });
                    }
                } else if ($.trim($(opcion).text()) === "Rebajas") {
                    if (estado === 'CERRADO' && tipoCertificado === 'CERTIFICADO') {
                        tipoSolicitud = 'RE';
                        mode = 'I';
                        fn_NuevoCab(codigo);
                    } else {
                        $.alert({
                            theme: 'material',
                            title: 'AVISO DEL SISTEMA',
                            content: 'Debe seleccionar un registro Cerrado y Tipo Certificado.',
                            animation: 'zoom',
                            closeAnimation: 'zoom',
                            type: 'red',
                            typeAnimated: true
                        });
                    }
                }
            } else {
                $.alert({
                    theme: 'material',
                    title: 'AVISO DEL SISTEMA',
                    content: 'No puede realizar este Tipo de Operación, Registro Anulado',
                    animation: 'zoom',
                    closeAnimation: 'zoom',
                    type: 'red',
                    typeAnimated: true
                });
            }
        });
        //Seleccionar un Registro de la Cabecera
        $("#div_GrillaPrincipal").on('rowselect', function (event) {
            var args = event.args;
            var row = $("#div_GrillaPrincipal").jqxGrid('getrowdata', args.rowindex);
            codigo = row['certificado'];
            estado = row['estado'];
            tipoCertificado = row['tipo'];
            sustento = row['detalle'];
            anexoCertificado = row['anexoCertificado'];
            firmaJefe = row['firmaJefe'];
            firmaSubJefe = row['firmaSubJefe'];
            archivo = row['archivo'];
            tipoSolicitud = tipoCertificado.substring(0, 2);
        });
        $("#div_GrillaRegistro").jqxGrid({
            width: '100%',
            height: '310',
            source: dataNuevo,
            pageable: true,
            columnsresize: true,
            showstatusbar: true,
            autoheight: false,
            autorowheight: false,
            showtoolbar: true,
            altrows: true,
            editable: false,
            sortable: true,
            showaggregates: true,
            statusbarheight: 20,
            rendertoolbar: function (toolbar) {
                // appends buttons to the status bar.
                var container = $("<div style='overflow: hidden; position: relative; margin: 1px;'></div>");
                var addButtonDet = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px;' src='../Imagenes/Botones/nuevo42.gif' width=18 height=18 /><span style='margin-left: 4px; position: relative; top: -3px;'></span></div>");
                var editButtonDet = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px;' src='../Imagenes/Botones/update42.gif' width=18 height=18 /><span style='margin-left: 4px; position: relative; top: -3px;'></span></div>");
                var deleteButtonDet = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px;' src='../Imagenes/Botones/delete42.gif' width=18 height=18/><span style='margin-left: 4px; position: relative; top: -3px;'></span></div>");
                container.append(addButtonDet);
                container.append(editButtonDet);
                container.append(deleteButtonDet);
                toolbar.append(container);
                addButtonDet.jqxButton({width: 30, height: 22});
                addButtonDet.jqxTooltip({position: 'bottom', content: "Nuevo Registro"});
                editButtonDet.jqxButton({width: 30, height: 22});
                editButtonDet.jqxTooltip({position: 'bottom', content: "Editar Registro"});
                deleteButtonDet.jqxButton({width: 30, height: 22});
                deleteButtonDet.jqxTooltip({position: 'bottom', content: "Anular Registro"});
                // add new row.
                addButtonDet.click(function (event) {
                    modeDetalle = 'I';
                    $("#cbo_Resolucion").jqxDropDownList('clear');
                    $("#cbo_Tarea").jqxDropDownList('clear');
                    $("#cbo_CadenaGasto").jqxDropDownList('clear');
                    fn_cargarComboAjax("#cbo_Resolucion", {mode: 'resolucionCertificacion', periodo: periodo, presupuesto: presupuesto, tipoCertificado: tipoSolicitud, solicitudCredito: $("#txt_solicitudCredito").val(), informeDisponibilidad: $("#cbo_InformeDisponibilidad").val()});
                    $('#div_Importe').val(0);
                    $('#div_MonedaExtranjera').val(0);
                    $("#cbo_Resolucion").jqxDropDownList({disabled: false});
                    $("#cbo_Tarea").jqxDropDownList({disabled: false});
                    $("#cbo_CadenaGasto").jqxDropDownList({disabled: false});
                    $('#div_VentanaDetalle').jqxWindow({isModal: true, modalOpacity: 0.9});
                    $('#div_VentanaDetalle').jqxWindow('open');
                });
                editButtonDet.click(function (event) {
                    modeDetalle = 'U';
                    if (indiceDetalle >= 0) {
                        var dataRecord = $("#div_GrillaRegistro").jqxGrid('getrowdata', indiceDetalle);
                        $("#cbo_Resolucion").jqxDropDownList({disabled: true});
                        $("#cbo_Resolucion").jqxDropDownList('setContent', dataRecord.resolucion);
                        $("#cbo_Tarea").jqxDropDownList('clear');
                        $("#cbo_Tarea").jqxDropDownList('setContent', dataRecord.tarea);
                        $("#cbo_Tarea").jqxDropDownList({disabled: true});
                        $("#cbo_CadenaGasto").jqxDropDownList('selectItem', fn_extraerDatos(dataRecord.cadena, ':'));
                        $("#cbo_CadenaGasto").jqxDropDownList({disabled: true});
                        $('#div_Importe').val(dataRecord.importe);
                        $('#div_MonedaExtranjera').val(dataRecord.extranjera);
                        $('#div_VentanaDetalle').jqxWindow({isModal: true, modalOpacity: 0.9});
                        $('#div_VentanaDetalle').jqxWindow('open');
                    } else {
                        $.alert({
                            theme: 'material',
                            title: 'AVISO DEL SISTEMA',
                            content: 'SELECCIONE UN REGISTRO',
                            animation: 'zoom',
                            closeAnimation: 'zoom',
                            type: 'red',
                            typeAnimated: true
                        });
                    }
                });
                // delete selected row.
                deleteButtonDet.click(function (event) {
                    modeDetalle = 'D';
                    if (indiceDetalle >= 0) {
                        var rowid = $("#div_GrillaRegistro").jqxGrid('getrowid', indiceDetalle);
                        $("#div_GrillaRegistro").jqxGrid('deleterow', rowid);
                    } else {
                        $.alert({
                            theme: 'material',
                            title: 'AVISO DEL SISTEMA',
                            content: 'SELECCIONE UN REGISTRO',
                            animation: 'zoom',
                            closeAnimation: 'zoom',
                            type: 'red',
                            typeAnimated: true
                        });
                    }
                    fn_verTotal();
                });
            },
            columns: [
                {text: 'N°', sortable: false, filterable: false, editable: false, groupable: false, draggable: false, resizable: false,
                    datafield: '', align: 'center', columntype: 'number', width: '4%', pinned: true, cellsrenderer: function (row, column, value) {
                        return "<div style='margin:4px; text-align: center;'>" + (value + 1) + "</div>";
                    }
                },
                {text: 'TAREA', datafield: 'tarea', width: "30%", align: 'center'},
                {text: 'CADENA', datafield: 'cadena', width: "33%", align: 'center'},
                {text: 'IMPORTE', dataField: 'importe', width: "15%", align: 'center', cellsAlign: 'right', cellsFormat: 'f2', cellclassname: cellclass, aggregates: ['sum']},
                {text: 'EXTRANJERA', dataField: 'extranjera', width: "12%", align: 'center', cellsAlign: 'right', cellsFormat: 'f2', cellclassname: cellclass, aggregates: ['sum']},
                {text: 'RESOLUCION', datafield: 'resolucion', width: "6%", align: 'center'}
            ]
        });
        $("#div_GrillaRegistro").on('rowselect', function (event) {
            indiceDetalle = event.args.rowindex;
            var dataRecord = $("#div_GrillaRegistro").jqxGrid('getrowdata', indiceDetalle);
            fn_cargarComboAjax("#cbo_CadenaGasto", {mode: 'cadenaGastoCertificacion', periodo: periodo, presupuesto: presupuesto,
                resolucion: fn_extraerDatos(dataRecord.resolucion, ':'),
                tipoCertificado: tipoSolicitud, tarea: fn_extraerDatos(dataRecord.tarea, ':')});
        });
    });
    //Funcion para Insertar un Nuevo Registro.
    function fn_NuevoCab(certificado) {
        fn_cargarComboAjax("#cbo_PAACProcesos", {mode: 'PAACProcesos', periodo: periodo, presupuesto: presupuesto, tipoCertificado: tipoSolicitud});
        $('#div_GrillaRegistro').jqxGrid('clear');
        $.ajax({
            type: "GET",
            url: "../CertificadoPresupuestal",
            data: {mode: mode, periodo: periodo, presupuesto: presupuesto},
            success: function (data) {
                $('#txt_NumeroSolicitud').val(data);
                $('#txt_solicitudCredito').val(certificado);
                if (tipoSolicitud === 'CE') {
                    $('#txt_DocumentoReferencia').val('');
                    $('#txt_Detalle').val('');
                    $('#txt_Observacion').val('');
                    $("#cbo_TipoMoneda").jqxDropDownList('selectItem', '01');
                    $('#div_TipoCambio').val(0);
                    $("#cbo_PAACProcesos").jqxDropDownList('setContent', 'Seleccione');
                } else {
                    $('#txt_Detalle').val(sustento);
                }
            }
        });
        if (tipoSolicitud === 'CE')
            detalle = 'CERTIFICACIÓN';
        if (tipoSolicitud === 'AM')
            detalle = 'AMPLACION';
        if (tipoSolicitud === 'RE')
            detalle = 'REBAJA';
        $('#div_VentanaPrincipal').jqxWindow({title: 'SOLICITUD DE CERTIFICADO DE CREDITO PRESUPUESTAL - ' + detalle});
        $('#div_VentanaPrincipal').jqxWindow({isModal: true, modalOpacity: 0.9});
        $('#div_VentanaPrincipal').jqxWindow('open');
    }
    //Funcion de Refrescar o Actulizar los datos de la Grilla.
    function fn_EditarCab() {
        if (estado !== 'ANULADA') {
            fn_cargarComboAjax("#cbo_PAACProcesos", {mode: 'PAACProcesos', periodo: periodo, presupuesto: presupuesto, tipoCertificado: 'AM'});
            $.ajax({
                type: "GET",
                url: "../CertificadoPresupuestal",
                data: {mode: 'U', periodo: periodo, presupuesto: presupuesto, codigo: codigo, tipsol: 'CE'},
                success: function (data) {
                    var dato = data.split("+++");
                    if (dato.length === 10) {
                        $('#txt_NumeroSolicitud').val(dato[0]);
                        $('#txt_solicitudCredito').val(dato[1]);
                        var d = new Date(dato[2]);
                        d.setDate(d.getDate() + 1);
                        $('#txt_Fecha ').jqxDateTimeInput('setDate', d);
                        $('#txt_DocumentoReferencia').val(dato[3]);
                        $('#txt_Detalle').val(dato[4]);
                        $('#txt_Observacion').val(dato[5].trim());
                        $("#cbo_PAACProcesos").jqxDropDownList('selectItem', dato[6]);
                        $('#div_GrillaRegistro').jqxGrid('clear');
                        //  $("#cbo_InformeDisponibilidad").jqxDropDownList('selectItem', dato[13]);
                        $.ajax({
                            type: "GET",
                            url: "../CertificadoPresupuestal",
                            data: {mode: 'B', periodo: periodo, presupuesto: presupuesto, codigo: codigo},
                            success: function (data) {
                                data = data.replace("[", "");
                                var fila = data.split("[");
                                var rows = new Array();
                                var indice = 5;
                                for (i = 1; i < fila.length; i++) {
                                    var columna = fila[i];
                                    var datos = columna.split("+++");
                                    while (datos[indice].indexOf(']') > 0) {
                                        datos[indice] = datos[indice].replace("]", "");
                                    }
                                    while (datos[indice].indexOf(',') > 0) {
                                        datos[indice] = datos[indice].replace(",", "");
                                    }
                                    var row = {codigo: datos[0], resolucion: datos[1], tarea: datos[2], cadena: datos[3],
                                        importe: parseFloat(datos[4]), extranjera: parseFloat(datos[5])};
                                    rows.push(row);
                                }
                                if (rows.length > 0)
                                    $("#div_GrillaRegistro").jqxGrid('addrow', null, rows);
                                fn_verTotal();
                            }
                        });
                    }
                }
            });
            $('#div_VentanaPrincipal').jqxWindow({isModal: true});
            $('#div_VentanaPrincipal').jqxWindow('open');
        } else {
            $.alert({
                theme: 'material',
                title: 'AVISO DEL SISTEMA',
                content: 'No se puede realizar la Operacion, \nRegistro ANULADO!!',
                animation: 'zoom',
                closeAnimation: 'zoom',
                type: 'red',
                typeAnimated: true
            });
        }
    }
    function fn_Refrescar() {
        $("#div_RegistroDetalle").remove();
        $("#div_GrillaRegistro").remove();
        $("#div_GrillaPrincipal").remove();
        $("#div_VentanaPrincipal").remove();
        $("#div_VentanaDetalle").remove();
        $("#div_VentanaCerrar").remove();
        $("#div_ContextMenu").remove();
        $("#div_Reporte").remove();
        var $contenidoAjax = $('#div_Detalle').html('<img src="../Imagenes/Fondos/cargando.gif">');
        $.ajax({
            type: "POST",
            url: "../CertificadoPresupuestal",
            data: {mode: 'G', periodo: periodo, presupuesto: presupuesto},
            success: function (data) {
                $contenidoAjax.html(data);
            }
        });
    }
    //Crea los Elementos de las Ventanas
    var customButtonsDemo = (function () {
        function _createElements() {
            //Inicia los Valores de Ventana de la Cabecera
            var posicionX, posicionY;
            var ancho = 800;
            var alto = 570;
            posicionX = ($(window).width() / 2) - (ancho / 2);
            posicionY = ($(window).height() / 2) - (alto / 2);
            $('#div_VentanaPrincipal').jqxWindow({
                position: {x: posicionX, y: posicionY},
                width: ancho, height: alto, resizable: false,
                cancelButton: $('#btn_Cancelar'),
                initContent: function () {
                    $("#txt_NumeroSolicitud").jqxInput({width: 130, height: 20, disabled: true});
                    $("#txt_solicitudCredito").jqxInput({width: 130, height: 20, disabled: true});
                    $("#txt_Fecha").jqxDateTimeInput({culture: 'es-PE', animationType: 'fade', width: 150, height: 20});
                    $("#cbo_TipoMoneda").jqxDropDownList({animationType: 'fade', width: 200, dropDownWidth: 250, height: 20});
                    $('#cbo_TipoMoneda').on('change', function () {
                        fn_verTipoMoneda();
                    });
                    $("#div_TipoCambio").jqxNumberInput({width: 80, height: 20, digits: 3, decimalDigits: 3, disabled: true});
                    $("#cbo_PAACProcesos").jqxDropDownList({animationType: 'fade', width: 650, dropDownWidth: 750, height: 20});
                    $("#cbo_InformeDisponibilidad").jqxDropDownList({animationType: 'fade', dropDownWidth: 350, height: 20});
                    $("#txt_DocumentoReferencia").jqxInput({placeHolder: "Ingrese Documento de Referencia", width: 670, height: 20, minLength: 1});
                    $("#txt_Detalle").jqxInput({placeHolder: "Ingrese detalle de la Solicitud", width: 670, height: 20, minLength: 1});
                    $("#txt_Observacion").jqxInput({placeHolder: "Ingrese las Observaciones", width: 670, height: 20, minLength: 1});
                    $("#div_TotalNacional").jqxNumberInput({width: 150, height: 20, max: 999999999, digits: 9, decimalDigits: 2, disabled: true});
                    $("#div_TotalExtranjera").jqxNumberInput({width: 150, height: 20, max: 999999999, digits: 9, decimalDigits: 2, disabled: true});
                    $('#btn_Cancelar').jqxButton({width: '65px', height: 25});
                    $('#btn_Guardar').jqxButton({width: '65px', height: 25});
                    $('#btn_Guardar').on('click', function (event) {
                        $('#frm_Certificado').jqxValidator('validate');
                    });
                    $('#frm_Certificado').jqxValidator({
                        rules: [
                            {input: '#txt_DocumentoReferencia', message: 'Ingrese el Documento de Referencia!', action: 'keyup, blur', rule: 'required'},
                            {input: '#txt_Detalle', message: 'Ingrese el Detalle!', action: 'keyup, blur', rule: 'required'}
                        ]
                    });
                    $('#frm_Certificado').jqxValidator({
                        onSuccess: function () {
                            fn_GrabarDatos();
                        }
                    });
                }
            });
            //Inicia los Valores de Ventana del Detalle
            ancho = 600;
            alto = 170;
            posicionX = ($(window).width() / 2) - (ancho / 2);
            posicionY = ($(window).height() / 2) - (alto / 2);
            $('#div_VentanaDetalle').jqxWindow({
                position: {x: posicionX, y: posicionY},
                width: ancho, height: alto, resizable: false,
                cancelButton: $('#btn_CancelarDetalle'),
                initContent: function () {
                    $("#cbo_Resolucion").jqxDropDownList({animationType: 'fade', width: 480, dropDownWidth: 550, height: 20});
                    $('#cbo_Resolucion').on('change', function () {
                        fn_cargarComboAjax("#cbo_Tarea", {mode: 'tareaCertificacion', periodo: periodo, presupuesto: presupuesto,
                            resolucion: $("#cbo_Resolucion").val(), tipoCertificado: tipoSolicitud, solicitudCredito: $("#txt_solicitudCredito").val(), informeDisponibilidad: $("#cbo_InformeDisponibilidad").val()});
                    });
                    $("#cbo_Tarea").jqxDropDownList({animationType: 'fade', width: 480, dropDownWidth: 600, height: 20});
                    $('#cbo_Tarea').on('change', function () {
                        fn_cargarComboAjax("#cbo_CadenaGasto", {mode: 'cadenaGastoCertificacion', periodo: periodo, presupuesto: presupuesto,
                            resolucion: $("#cbo_Resolucion").val(), tarea: $("#cbo_Tarea").val(), tipoCertificado: tipoSolicitud, solicitudCredito: $("#txt_solicitudCredito").val(), informeDisponibilidad: $("#cbo_InformeDisponibilidad").val()});
                    });
                    $("#cbo_CadenaGasto").jqxDropDownList({animationType: 'fade', width: 480, dropDownWidth: 600, height: 20});
                    $("#div_Importe").jqxNumberInput({width: 150, height: 20, max: 999999999, digits: 9, decimalDigits: 2});
                    $("#div_MonedaExtranjera").jqxNumberInput({width: 150, height: 20, max: 999999999, digits: 9, decimalDigits: 2, disabled: true});
                    $('#div_MonedaExtranjera').on('textchanged', function (event) {
                        fn_verTipoCambio();
                    });
                    $('#btn_CancelarDetalle').jqxButton({width: '65px', height: 25});
                    $('#btn_GuardarDetalle').jqxButton({width: '65px', height: 25});
                    $('#btn_GuardarDetalle').on('click', function (event) {
                        var resolucion = $("#cbo_Resolucion").jqxDropDownList('getSelectedItem');
                        var tarea = $("#cbo_Tarea").jqxDropDownList('getSelectedItem');
                        var cadenaGasto = $("#cbo_CadenaGasto").jqxDropDownList('getSelectedItem');
                        var msg = "";
                        if (msg === "")
                            msg = fn_validaSaldo();
                        if (msg === "" && modeDetalle === 'I') {
                            msg = fn_validaDetalle(fn_extraerDatos(resolucion.label, 'S/.'),
                                    fn_extraerDatos(tarea.label, 'S/.'), fn_extraerDatos(cadenaGasto.label, 'S/.'));
                        }
                        if (msg === "" && modeDetalle === 'U') {
                            var dataRecord = $("#div_GrillaRegistro").jqxGrid('getrowdata', indiceDetalle);
                            msg = fn_validaDetalle(dataRecord.resolucion, dataRecord.tarea, dataRecord.cadena);
                        }
                        if (msg === "") {
                            if (modeDetalle === 'I') {
                                var row = {codigo: resolucion.value + "---" + tarea.value + "---" + cadenaGasto.value,
                                    tarea: fn_extraerDatos(tarea.label, 'S/.'), cadena: fn_extraerDatos(cadenaGasto.label, 'S/.'),
                                    importe: parseFloat($("#div_Importe").jqxNumberInput('decimal')),
                                    extranjera: parseFloat($("#div_MonedaExtranjera").jqxNumberInput('decimal')),
                                    resolucion: fn_extraerDatos(resolucion.label, 'S/.')};
                                $("#div_GrillaRegistro").jqxGrid('addrow', null, row);
                            }
                            if (modeDetalle === 'U') {
                                var dataRecord = $("#div_GrillaRegistro").jqxGrid('getrowdata', indiceDetalle);
                                var row = {codigo: dataRecord.codigo, tarea: dataRecord.tarea, cadena: dataRecord.cadena,
                                    importe: parseFloat($("#div_Importe").jqxNumberInput('decimal')), extranjera: parseFloat($("#div_MonedaExtranjera").jqxNumberInput('decimal')),
                                    resolucion: dataRecord.resolucion};
                                var rowID = $('#div_GrillaRegistro').jqxGrid('getrowid', indiceDetalle);
                                $('#div_GrillaRegistro').jqxGrid('updaterow', rowID, row);
                            }
                            modeDetalle = null;
                            fn_verTotal();
                            $("#div_VentanaDetalle").jqxWindow('hide');
                        } else {
                            $.alert({
                                theme: 'material',
                                title: 'AVISO DEL SISTEMA',
                                content: msg,
                                animation: 'zoom',
                                closeAnimation: 'zoom',
                                type: 'red',
                                typeAnimated: true
                            });
                        }
                    });
                }
            });
            ancho = 500;
            alto = 100;
            posicionX = ($(window).width() / 2) - (ancho / 2);
            posicionY = ($(window).height() / 2) - (alto / 2);
            //ventana cerrar CERTIFICADO PRESUPUESTAL
            $('#div_VentanaCerrar').jqxWindow({
                position: {x: posicionX, y: posicionY},
                width: ancho, height: alto, resizable: false,
                cancelButton: $('#btn_CancelarCerrar'),
                initContent: function () {
                    $("#txt_Fichero").jqxInput({placeHolder: "Seleccione el Documento", width: 400, height: 20, minLength: 1});
                    $('#btn_CancelarCerrar').jqxButton({width: '65px', height: 25});
                    $('#btn_GuardarCerrar').jqxButton({width: '65px', height: 25});
                    $('#btn_GuardarCerrar').on('click', function (event) {
                        fn_GuardarCerrar();
                    });
                }
            });
            /**/
            ancho = 400;
            alto = 220;
            posicionX = ($(window).width() / 2) - (ancho / 2);
            posicionY = ($(window).height() / 2) - (alto / 2);
            $('#div_Reporte').jqxWindow({
                position: {x: posicionX, y: posicionY},
                width: ancho, height: alto, resizable: false,
                cancelButton: $('#btn_CerrarImprimir'),
                initContent: function () {
                    $("#div_EJE0001").jqxRadioButton({width: 200, height: 20});
                    $('#div_EJE0001').on('checked', function (event) {
                        reporte = 'EJE0004';
                    });
                    $("#div_EJE0002").jqxRadioButton({width: 200, height: 20});
                    $('#div_EJE0002').on('checked', function (event) {
                        reporte = 'EJE0004';
                    });
                    $("#div_EJE0003").jqxRadioButton({width: 200, height: 20});
                    $('#div_EJE0003').on('checked', function (event) {
                        reporte = 'EJE0005';
                    });
                    $("#div_EJE0004").jqxRadioButton({width: 200, height: 20});
                    $('#div_EJE0004').on('checked', function (event) {
                        reporte = 'EJE0007';
                    });
                    $("#div_EJE0005").jqxRadioButton({width: 200, height: 20});
                    $('#div_EJE0005').on('checked', function (event) {
                        reporte = 'EJE0039';
                    });
                    $('#btn_CerrarImprimir').jqxButton({width: '65px', height: 25});
                    $('#btn_Imprimir').jqxButton({width: '65px', height: 25});
                    $('#btn_Imprimir').on('click', function (event) {
                        var msg = "";
                        switch (reporte) {
                            case "EJE0001":
                                if (codigo === null)
                                    msg += "Seleccione la Solicitud de Credito Presupuestario.<br>";
                                break;
                            case "EJE0002":
                                if (codigo === null)
                                    msg += "Seleccione la Solicitud de Credito Presupuestario.<br>";
                                if (tipoCertificado !== 'CERTIFICADO')
                                    msg += "Seleccione la S.C.P. Tipo CERTIFICADO.<br>";
                                break;
                            case "EJE0003":
                                if (codigo === null)
                                    msg += "Seleccione la Solicitud de Credito Presupuestario.<br>";
                                if (tipoCertificado !== 'CERTIFICADO')
                                    msg += "Seleccione la S.C.P. Tipo CERTIFICADO.<br>";
                                break;
                            case "EJE0004":
                                if (codigo !== null && tipoCertificado !== 'CERTIFICADO')
                                    msg += "Seleccione la S.C.P. Tipo CERTIFICADO.<br>";
                                break;
                            case "EJE0005":
                                break;
                            case "EJE0039":
                                codigo = cobertura;
                                codigo = codigo.substr(0, codigo.indexOf("-"));
                                if (codigo === null)
                                    msg += "Seleccione la Solicitud de Credito Presupuestario.<br>";
                                if (tipoCertificado !== 'CERTIFICADO')
                                    msg += "Seleccione la S.C.P. Tipo CERTIFICADO.<br>";
                                break;
                            case "EJE0006":
                                codigo = cobertura;
                                codigo = codigo.substr(0, codigo.indexOf("-"));
                                if (codigo === null)
                                    msg += "Seleccione la Solicitud de Credito Presupuestario.<br>";
                                if (!autorizacion)
                                    msg += "Usuario no Autorizado para este Tipo de Reporte.<br>";
                                //  if (firmaJefe !== 'SI')
                                //       msg += "No puede imprimir esta Registro, Falta Firmar el Jefe de la OPRE.<br>";
                                //  if (firmaSubJefe !== 'SI')
                                //       msg += "No puede imprimir esta Registro, Falta Firmar el Sub Jefe de la OPRE.<br>";
                                break;
                            case "EJE0007":
                                codigo = cobertura;
                                codigo = codigo.substr(0, codigo.indexOf("-"));
                                if (codigo === null)
                                    msg += "Seleccione la Solicitud de Credito Presupuestario.<br>";
                                if (!autorizacion)
                                    msg += "Usuario no Autorizado para este Tipo de Reporte.<br>";
                                //  if (firmaJefe !== 'SI')
                                //       msg += "No puede imprimir esta Registro, Falta Firmar el Jefe de la OPRE.<br>";
                                //  if (firmaSubJefe !== 'SI')
                                //       msg += "No puede imprimir esta Registro, Falta Firmar el Sub Jefe de la OPRE.<br>";
                                break;
                            default:
                                msg += "Debe selecciona una opción.<br>";
                                break;
                        }
                        if (msg === "") {
                            var url = '../Reportes?reporte=' + reporte + '&periodo=' + periodo + '&presupuesto=' + presupuesto + "&codigo=" + codigo + "&codigo2=" + codigo;
                            window.open(url, '_blank');
                        } else {
                            $.alert({
                                theme: 'material',
                                title: 'AVISO DEL SISTEMA',
                                content: msg,
                                animation: 'zoom',
                                closeAnimation: 'zoom',
                                type: 'red',
                                typeAnimated: true
                            });
                        }
                    });
                }
            });
        }
        return {
            init: function () {
                _createElements();
            }
        };
    }());
    $(document).ready(function () {
        customButtonsDemo.init();
    });
    function fn_verTipoMoneda() {
        var codmon = $("#cbo_TipoMoneda").val();
        if (codmon === '01') {
            $('#div_MonedaExtranjera').val("0");
            $('#div_TipoCambio').val("0");
            $('#div_TipoCambio').jqxNumberInput({disabled: true});
            $('#div_MonedaExtranjera').jqxNumberInput({disabled: true});
            $('#div_Importe').jqxNumberInput({disabled: false});
            $('#div_Importe').jqxNumberInput('focus');
        } else {
            $('#div_Importe').jqxNumberInput({disabled: true});
            $('#div_TipoCambio').jqxNumberInput({disabled: false});
            $('#div_MonedaExtranjera').jqxNumberInput({disabled: false});
            $('#div_TipoCambio').jqxNumberInput('focus');
        }
    }
    function fn_verTipoCambio() {
        var tipoMoneda = $("#cbo_TipoMoneda").val();
        var tipoCambio = $("#div_TipoCambio").val();
        var extranjera = $("#div_MonedaExtranjera").val();
        tipoCambio = parseFloat(tipoCambio);
        extranjera = parseFloat(extranjera);
        var importe = extranjera * tipoCambio;
        if (tipoMoneda !== '01') {
            if (isNaN(importe))
                importe = '0';
            importe = Math.round(importe * 100) / 100;
            $('#div_Importe').val(importe);
        }
    }
    function fn_GrabarDatos() {
        var msg = "";
        var nroCertificado = $('#txt_NumeroSolicitud').val();
        var fecha = $('#txt_Fecha').val();
        var documentoReferencia = $("#txt_DocumentoReferencia").val();
        var detalle = $("#txt_Detalle").val();
        var observacion = $("#txt_Observacion").val();
        var tipoMoneda = $("#cbo_TipoMoneda").val();
        var tipoCambio = $("#div_TipoCambio").val();
        var solicitudCredito = $("#txt_solicitudCredito").val();
        var importe = $("#div_TotalNacional").val();
        var monedaExtranjera = $("#div_TotalExtranjera").val();
        var informeDisponibilidad = $("#cbo_InformeDisponibilidad").val();
        var paac = $("#cbo_PAACProcesos").val();
        var msg = "";
        var lista = new Array();
        var result;
        var rows = $('#div_GrillaRegistro').jqxGrid('getrows');
        for (var i = 0; i < rows.length; i++) {
            var row = rows[i];
            result = row.uid + "---" + row.codigo + "---" + row.importe + "---" + row.extranjera;
            lista.push(result);
        }
        paac = '0';
        if (tipoSolicitud !== 'RE') {
            //  if (paac === '' || paac === '0')
            //      msg += "Seleccione el PAAC.<br>";
        }
        // if (paac === '0')
        //      msg += "Seleccione el Procedimiento de Selección<br>";
        if (lista.length === 0)
            msg += "Ingrese el Detalle del Certificado <br>";
        if (msg === "") {
            $.ajax({
                type: "POST",
                url: "../IduCertificadoPresupuestal",
                data: {mode: mode, periodo: periodo, presupuesto: presupuesto, certificado: nroCertificado, paac: paac,
                    tipoSolicitud: tipoSolicitud, solicitudCredito: solicitudCredito, fecha: fecha,
                    documentoReferencia: documentoReferencia, detalle: detalle, observacion: observacion, tipoMoneda: tipoMoneda, tipoCambio: tipoCambio,
                    importe: importe, monedaExtranjera: monedaExtranjera, informeDisponibilidad: informeDisponibilidad, lista: JSON.stringify(lista)},
                success: function (data) {
                    msg = data;
                    if (msg === "GUARDO") {
                        $.confirm({
                            title: 'AVISO DEL SISTEMA',
                            content: 'Datos procesados correctamente',
                            type: 'green',
                            typeAnimated: true,
                            autoClose: 'cerrarAction|1000',
                            buttons: {
                                cerrarAction: {
                                    text: 'Cerrar',
                                    action: function () {
                                        $('#div_VentanaPrincipal').jqxWindow('close');
                                        fn_Refrescar();
                                    }
                                }
                            }
                        });
                    } else {
                        $.alert({
                            theme: 'material',
                            title: 'AVISO DEL SISTEMA',
                            content: msg,
                            animation: 'zoom',
                            closeAnimation: 'zoom',
                            type: 'red',
                            typeAnimated: true
                        });
                    }
                }
            });
        } else {
            $.alert({
                theme: 'material',
                title: 'AVISO DEL SISTEMA',
                content: msg,
                animation: 'zoom',
                closeAnimation: 'zoom',
                type: 'red',
                typeAnimated: true
            });
        }
    }
    function fn_GrabarCertificado(nroSIAF) {
        $.ajax({
            type: "POST",
            url: "../IduCertificadoPresupuestal",
            data: {mode: mode, periodo: periodo, presupuesto: presupuesto, nroCertificado: codigo, solicitudCredito: nroSIAF},
            success: function (data) {
                msg = data;
                if (msg === "GUARDO") {
                    $.confirm({
                        title: 'AVISO DEL SISTEMA',
                        content: 'Datos procesados correctamente',
                        type: 'green',
                        typeAnimated: true,
                        autoClose: 'cerrarAction|1000',
                        buttons: {
                            cerrarAction: {
                                text: 'Cerrar',
                                action: function () {
                                    fn_Refrescar();
                                }
                            }
                        }
                    });
                } else {
                    $.alert({
                        theme: 'material',
                        title: 'AVISO DEL SISTEMA',
                        content: msg,
                        animation: 'zoom',
                        closeAnimation: 'zoom',
                        type: 'red',
                        typeAnimated: true
                    });
                }
            }
        });
    }
    function fn_GuardarCerrar() {
        var fichero = $("#txt_Fichero").val();
        if (fichero !== '') {
            var formData = new FormData(document.getElementById("frm_SolicitudCerrar"));
            formData.append("mode", "C");
            formData.append("periodo", periodo);
            formData.append("presupuesto", presupuesto);
            formData.append("certificado", codigo);
            $.ajax({
                type: "POST",
                url: "../IduCertificadoPresupuestal",
                data: formData,
                dataType: "html",
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
                    msg = data;
                    if (msg === "GUARDO") {
                        $('#div_VentanaCerrar').jqxWindow('close');
                        $.confirm({
                            title: 'AVISO DEL SISTEMA',
                            content: 'Datos procesados correctamente',
                            type: 'green',
                            typeAnimated: true,
                            autoClose: 'cerrarAction|1000',
                            buttons: {
                                cerrarAction: {
                                    text: 'Cerrar',
                                    action: function () {
                                        fn_Refrescar();
                                    }
                                }
                            }
                        });
                    } else {
                        $.alert({
                            theme: 'material',
                            title: 'AVISO DEL SISTEMA',
                            content: msg,
                            animation: 'zoom',
                            closeAnimation: 'zoom',
                            type: 'red',
                            typeAnimated: true
                        });
                    }
                }
            });
        } else {
            $.alert({
                theme: 'material',
                title: 'AVISO DEL SISTEMA',
                content: "Debe seleccionar el archivo con la documentacion sustentatoria\n PROCESO CANCELADO!!!.",
                animation: 'zoom',
                closeAnimation: 'zoom',
                type: 'red',
                typeAnimated: true
            });
        }
    }
    function fn_validaSaldo() {
        var cadena = $("#cbo_CadenaGasto").val();
        if (cadena.length !== 0) {
            var saldo = $("#cbo_CadenaGasto").jqxDropDownList('getSelectedItem');
            saldo = saldo.label;
            var importe = parseFloat($("#div_Importe").jqxNumberInput('decimal'));
            var monto = "";
            var len = 0;
            len = saldo.length;
            var pos = saldo.indexOf('S/.');
            monto = saldo.substring(pos + 3, len);
            monto = fn_reemplazarTodo(monto, ',', '');
            monto = parseFloat(monto);
            if (modeDetalle === 'U') {
                var dataRecord = $("#div_GrillaRegistro").jqxGrid('getrowdata', indiceDetalle);
                monto = monto + parseFloat(dataRecord.importe);
            }
            if (importe > monto) {
                return "No puede ingresar un importe superior a S/. " + monto + ". Revise!!!";
            } else if (importe === parseFloat('0')) {
                return "No puede ingresar un importe Cero. Revise!!!";
            } else {
                return "";
            }
        } else {
            return "Seleccione la Cadena de Gasto";
        }
    }
    //FUNCION PARA VALIDAR QUE NO SE REPITAN LOS REGISTROS DEL DETALLE
    function fn_validaDetalle(resolucion, tarea, cadenaGasto) {
        var rows = $('#div_GrillaRegistro').jqxGrid('getrows');
        if (modeDetalle === 'I') {
            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                if (row.resolucion.trim() === resolucion.trim() &&
                        row.tarea.trim() === tarea.trim() &&
                        row.cadena.trim() === cadenaGasto.trim()) {
                    return "Los Datos que desea registrar ya existen, Revise!!";
                }
            }
        }
        if (modeDetalle === 'U') {
            if (rows[indiceDetalle].resolucion.trim() === resolucion.trim() &&
                    rows[indiceDetalle].tarea.trim() === tarea.trim() &&
                    rows[indiceDetalle].cadena.trim() === cadenaGasto.trim()) {
                return "";
            } else {
                for (var i = 0; i < rows.length; i++) {
                    var row = rows[i];
                    if (i !== indiceDetalle && row.resolucion.trim() === resolucion.trim() &&
                            row.tarea.trim() === tarea.trim() &&
                            row.cadena.trim() === cadenaGasto.trim()) {
                        return "Los Datos que desea registrar ya existen, Revise!!";
                    }
                }
            }
        }
        return "";
    }
    //FUNCION PARA OBTENER LA SUMATORIA TOTAL DEL DETALLE
    function fn_verTotal() {
        var totalNacional, totalExtranjera;
        totalNacional = totalExtranjera = 0.0;
        var rows = $('#div_GrillaRegistro').jqxGrid('getrows');
        for (var i = 0; i < rows.length; i++) {
            var row = rows[i];
            totalNacional += parseFloat(row.importe);
            totalExtranjera += parseFloat(row.extranjera);
        }
        $("#div_TotalNacional").val(Math.round(parseFloat(totalNacional) * 100) / 100);
        $("#div_TotalExtranjera").val(Math.round(parseFloat(totalExtranjera) * 100) / 100);
    }
</script>
<div id="div_GrillaPrincipal"></div>
<div id="div_VentanaPrincipal" style="display: none;">
    <div>
        <span style="float: left">SOLICITUD DE CERTIFICADO DE CREDITO PRESUPUESTAL</span>
    </div>
    <div style="overflow: hidden">
        <form id="frm_Certificado" name="frm_Certificado" method="post" >
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="inputlabel">N&deg; Solicitud : </td>
                    <td><input type="text" id="txt_NumeroSolicitud" name="txt_NumeroSolicitud"/></td>
                    <td class="inputlabel">Solicitud : </td>
                    <td><input type="text" id="txt_solicitudCredito" name="txt_solicitudCredito"/></td>
                    <td class="inputlabel">Fecha : </td>
                    <td ><div id="txt_Fecha"></div>
                    </td>
                </tr>
                <tr>
                    <td class="inputlabel">PAAC : </td>
                    <td colspan="5" >
                        <select id="cbo_PAACProcesos" name="cbo_PAACProcesos">
                            <option value="0">Seleccione</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="inputlabel">IDP : </td>
                    <td colspan="5">
                        <select id="cbo_InformeDisponibilidad" name="cbo_InformeDisponibilidad">
                            <option value="0">Seleccione</option>   
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="inputlabel">Doc. Ref. : </td>
                    <td colspan="5"><input type="text" id="txt_DocumentoReferencia" name="txt_DocumentoReferencia" style="text-transform: uppercase;"/></td>
                </tr>
                <tr> 
                    <td class="inputlabel">Concepto : </td>
                    <td colspan="5"><input type="text" id="txt_Detalle" name="txt_Detalle" style="text-transform: uppercase;"/></td>
                </tr>
                <tr>
                    <td class="inputlabel">Observaci&oacute;n : </td>
                    <td colspan="5"><input type="text" id="txt_Observacion" name="txt_Observacion" style="text-transform: uppercase;"/></td>
                </tr> 
                <tr>
                    <td class="inputlabel">Tipo Moneda : </td>
                    <td>
                        <select id="cbo_TipoMoneda" name="cbo_TipoMoneda">
                            <c:forEach var="f" items="${objTipoMoneda}">
                                <option value="${f.codigo}">${f.descripcion}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="inputlabel">Tipo Cambio : </td>
                    <td><div id="div_TipoCambio"></div></td>
                </tr> 
                <tr>
                    <td class="inputlabel">TOTAL S/. : </td>
                    <td><div id="div_TotalNacional"></div></td>
                    <td class="inputlabel">TOTAL ME : </td>
                    <td><div id="div_TotalExtranjera"></div></td>
                </tr> 
                <tr>
                    <td class="Summit" colspan="6">
                        <div>
                            <input type="button" id="btn_Guardar"  value="Guardar" style="margin-right: 20px"/>
                            <input type="button" id="btn_Cancelar" value="Cancelar" style="margin-right: 20px"/>
                        </div>
                    </td>
                </tr>
            </table>
            <div id="div_GrillaRegistro"></div>
            <div id='div_VentanaDetalle' style='display: none;'>
                <div>
                    <span style="float: left">DETALLE DE SOLICITUD DE CERTIFICADO DE CREDITO PRESUPUESTAL</span>
                </div>
                <div style="overflow: hidden">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="inputlabel">Resoluci&oacute;n : </td>
                            <td colspan="3">
                                <select id="cbo_Resolucion" name="cbo_Resolucion">
                                    <option value="0">Seleccione</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="inputlabel">Tarea : </td>
                            <td colspan="3">
                                <select id="cbo_Tarea" name="cbo_Tarea">
                                    <option value="0">Seleccione</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="inputlabel">Cad. Gasto : </td>
                            <td colspan="3">
                                <select id="cbo_CadenaGasto" name="cbo_CadenaGasto">
                                    <option value="0">Seleccione</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="inputlabel">Importe S/. : </td>
                            <td><div id="div_Importe"></div></td>
                            <td class="inputlabel">Extranjera : </td>
                            <td><div id="div_MonedaExtranjera"></div></td>
                        </tr>
                        <tr>
                            <td class="Summit" colspan="4">
                                <div>
                                    <input type="button" id="btn_GuardarDetalle" name="btn_GuardarDetalle" value="Guardar" style="margin-right: 20px"/>
                                    <input type="button" id="btn_CancelarDetalle" name="btn_CancelarDetalle" value="Cancelar" style="margin-right: 20px"/>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
<div id="div_VentanaCerrar" style="display: none">
    <div>
        <span style="float: left">CERRAR SOLICITUD DE CERTIFICADO DE CREDITO PRESUPUESTAL</span>
    </div>
    <div style="overflow: hidden">
        <form id="frm_SolicitudCerrar" name="frm_SolicitudCerrar" enctype="multipart/form-data" action="javascript:fn_GuardarCerrar();" method="post">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="inputlabel">Anexos : </td>
                    <td>
                        <input type="file" id="txt_Fichero" name="txt_Fichero" style="text-transform: uppercase;" accept="application/pdf"/>
                    </td> 
                </tr>
                <tr>
                    <td class="Summit" colspan="2">
                        <div>
                            <input type="button" id="btn_GuardarCerrar"  value="Guardar" style="margin-right: 20px"/>
                            <input type="button" id="btn_CancelarCerrar" value="Cancelar" style="margin-right: 20px"/>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div id="div_Reporte" style="display: none;">
    <div>
        <span style="float: left">LISTADO DE REPORTES</span>
    </div>
    <div style="overflow: hidden">
        <div id='div_EJE0001'>Memorando de Certificación</div>
        <div id='div_EJE0002'>Control de Solicitud</div>
        <div id='div_EJE0003'>Listado de Compromisos Anuales</div>
        <div id='div_EJE0004'>Certificados vs Compromiso Anual</div>
        <div id='div_EJE0005'>Avance Presupuestal del Certificado</div>

        <div class="Summit">
            <input type="submit" id="btn_Imprimir" name="btn_Imprimir" value="Ver" style="margin-right: 20px"/>
            <input type="button" id="btn_CerrarImprimir" name="btn_CerrarImprimir" value="Cerrar" style="margin-right: 20px"/>
        </div>
    </div>
</div>
<div id='div_ContextMenu' style='display: none;'>
    <ul>
        <li style="font-weight: bold;">Editar</li>
        <li style="font-weight: bold;">Anular</li>
        <li type='separator'></li>
        <li style="color: blue; font-weight: bold;">Cerrar</li>
        <li type='separator'></li>
        <li style="color: green; font-weight: bold;">Ampliaciones</li>
        <li style="color: green; font-weight: bold;">Rebajas</li>
        <li type='separator'></li>
        <li style="color: maroon;font-weight: bold;">Ver Anexos</li>
    </ul>
</div>