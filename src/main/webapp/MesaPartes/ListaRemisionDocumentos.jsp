<%-- 
    Document   : ListaRemisionDocumento
    Created on : 24/07/2017, 04:15:13 PM
    Author     : H-TECCSI-V
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    var periodo = $("#cbo_Periodo").val();
    var tipo = "S";
    var mes = $("#cbo_Mes").val();
    var codigo = null;
    var archivo = null;
    var institucion = null;
    var estado = '';
    var mode = null;
    var msg = '';
    var lista = new Array();
    <c:forEach var="d" items="${objRemisionDocumento}">
    var result = {numero: '${d.numero}', numeroDocumento: '${d.numeroDocumento}', asunto: '${d.asunto}',
        institucion: '${d.institucion}', prioridad: '${d.prioridad}', fecha: '${d.fecha}', estado: '${d.estado}', firma: '${d.postFirma}',
        legajo: '${d.legajo}', folio: '${d.folio}', referencia: '${d.referencia}', cargo: '${d.cargo}',
        archivo: '${d.archivo}'};
    lista.push(result);
    </c:forEach>
    var listaInstitucion = new Array();
    var rows = '${objInstitucion}';
    <c:forEach var="c" items="${objInstitucion}">
        <c:set value="${c.descripcion}" var="descripcion"></c:set>
        <c:set value="${c.codigo}" var="codigo"></c:set>
    var result = {label: "${descripcion}", value: "${codigo}"};
    listaInstitucion.push(result);
    </c:forEach>
    $(document).ready(function () {
        //PARA CARGAR LOS ELEMENTOS DE LA INSTITUCION
        var sourceInstitucion = {
            datafields: [{name: 'label'}, {name: 'value'}],
            localdata: listaInstitucion
        };
        var dataAdapterInstitucion = new $.jqx.dataAdapter(sourceInstitucion);
        //PARA CARGAR LOS ELEMENTOS DE LA GRILLA
        var source = {
            localdata: lista,
            datatype: "array",
            datafields:
                    [
                        {name: 'numero', type: "string"},
                        {name: 'numeroDocumento', type: "string"},
                        {name: 'asunto', type: "string"},
                        {name: 'institucion', type: "string"},
                        {name: 'prioridad', type: "string"},
                        {name: 'fecha', type: "string"},
                        {name: 'estado', type: "string"},
                        {name: 'firma', type: "string"},
                        {name: 'cargo', type: "string"},
                        {name: 'legajo', type: "number"},
                        {name: 'folio', type: "string"},
                        {name: 'referencia', type: "string"},
                        {name: 'archivo', type: "string"}
                    ],
            root: "RemisionDocumentos",
            record: "RemisionDocumentos",
            id: 'numero'
        };
        var dataAdapter = new $.jqx.dataAdapter(source);
        //ESTILOS A LAS CELDAS DE LA GRILLA 
        var cellclass = function (row, datafield, value, rowdata) {
            if (datafield === "numero") {
                return "RowBold";
            }
            if (datafield === "usuarioResponsable") {
                return "RowBlue";
            }
            if (datafield === "institucion") {
                return "RowPurple";
            }
        };
        //DEFINIMOS LOS CAMPOS Y DATOS DE LA GRILLA
        $("#div_GrillaPrincipal").jqxGrid({
            width: '99.8%',
            height: ($(window).height() - 100),
            source: dataAdapter,
            autoheight: false,
            autorowheight: false,
            altrows: true,
            showtoolbar: true,
            sortable: true,
            pageable: true,
            filterable: true,
            autoshowfiltericon: true,
            columnsresize: true,
            showfilterrow: true,
            editable: false,
            rendertoolbar: function (toolbar) {
                var container = $("<div style='overflow: hidden; position: relative; margin: 1px;'></div>");
                var ButtonNuevo = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px;' src='../Imagenes/Botones/nuevo42.gif' width=18 height=18 /><span style='margin-left: 4px; position: relative; top: -3px;'> </span></div>");
                var ButtonExportar = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px;' src='../Imagenes/Botones/pauf42.gif' width=18 height=18/><span style='margin-left: 4px; position: relative; top: -3px;'> </span></div>");
                container.append(ButtonNuevo);
                container.append(ButtonExportar);
                toolbar.append(container);
                ButtonNuevo.jqxButton({width: 30, height: 22});
                ButtonNuevo.jqxTooltip({position: 'bottom', content: "Nuevo Registro"});
                ButtonExportar.jqxButton({width: 30, height: 22});
                ButtonExportar.jqxTooltip({position: 'bottom', content: "Exportar Datos"});
                ButtonNuevo.click(function (event) {
                    mode = 'I';
                    codigo = 0;
                    fn_NuevoRegistro();
                });
                //ASIGNAMOS LAS FUNCIONES PARA EL BOTON EXPORTAR
                ButtonExportar.click(function (event) {
                    $("#div_GrillaPrincipal").jqxGrid('exportdata', 'xls', 'RemisionDocumento');
                });
            },
            columns: [
                {text: ' ', sortable: false, filterable: false, editable: false, groupable: false, draggable: false, resizable: false,
                    datafield: '', columntype: 'number', width: 10, pinned: true, cellsrenderer: function (row, column, value) {
                        return "<div style='margin:4px; text-align: center;'>" + (value + 1) + "</div>";
                    }
                },
                {text: 'DOCUMENTO', dataField: 'numeroDocumento', width: '8%', align: 'center', cellsAlign: 'left', cellclassname: cellclass},
                {text: 'ASUNTO', dataField: 'asunto', width: '25%', align: 'center', cellsAlign: 'left', cellclassname: cellclass},
                {text: 'INSTITUCI?N', dataField: 'institucion', width: '15%', align: 'center', cellsAlign: 'center', cellclassname: cellclass},
                {text: 'DIRIGIDO A', dataField: 'firma', width: '20%', align: 'center', cellsAlign: 'left', cellclassname: cellclass},
                {text: 'CARGO', dataField: 'cargo', width: '15%', align: 'center', cellsAlign: 'left', cellclassname: cellclass},
                {text: 'FEC. DOC', dataField: 'fecha', width: '8%', align: 'center', cellsAlign: 'center', cellclassname: cellclass},
                {text: 'PRIORIDAD', dataField: 'prioridad', filtertype: 'checkedlist', width: '7%', align: 'center', cellsAlign: 'center', cellclassname: cellclass},
                {text: 'ESTADO', dataField: 'estado', filtertype: 'list', width: '9%', align: 'center', cellsAlign: 'center', cellclassname: cellclass},
                {text: 'LEGAJO', dataField: 'legajo', width: '5%', align: 'center', cellsAlign: 'center', cellsFormat: 'f', cellclassname: cellclass},
                {text: 'FOLIO', dataField: 'folio', width: '5%', align: 'center', cellsAlign: 'center', cellsFormat: 'f', cellclassname: cellclass},
                {text: 'REFERENCIA', dataField: 'referencia', width: '9%', align: 'center', cellsAlign: 'center', cellclassname: cellclass}
            ]
        });
        // DEFINIMOS EL MENU CONTEXTUAL 
        var contextMenu = $("#div_ContextMenu").jqxMenu({width: 200, height: 85, autoOpenPopup: false, mode: 'popup'});
        $("#div_GrillaPrincipal").on('contextmenu', function () {
            return false;
        });
        // HABILITAMOS LA OPCION DE CLICK DEL MENU CONTEXTUAL.
        $("#div_GrillaPrincipal").on('rowclick', function (event) {
            if (event.args.rightclick) {
                $("#div_GrillaPrincipal").jqxGrid('selectrow', event.args.rowindex);
                var scrollTop = $(window).scrollTop();
                var scrollLeft = $(window).scrollLeft();
                contextMenu.jqxMenu('open', parseInt(event.args.originalEvent.clientX) + 5 + scrollLeft, parseInt(event.args.originalEvent.clientY) + 5 + scrollTop);
                return false;
            }
        });
        //DEFINIMOS LOS EVENTOS SEGUN LA OPCION DEL MENU CONTEXTUAL      
        $("#div_ContextMenu").on('itemclick', function (event) {
            var opcion = event.args;
            if ($.trim($(opcion).text()) === "Editar") {
                if (estado !== "PENDIENTE") {
                    $.alert({
                        theme: 'material',
                        title: 'AVISO DEL SISTEMA',
                        content: 'El registro no puede ser editado, se encuentra ' + estado,
                        animation: 'zoom',
                        closeAnimation: 'zoom',
                        type: 'red',
                        typeAnimated: true
                    });
                } else {
                    mode = 'U';
                    fn_EditarRegistro();
                }
            } else if ($.trim($(opcion).text()) === "Anular") {
                if (estado !== "PENDIENTE") {
                    $.alert({
                        theme: 'material',
                        title: 'AVISO DEL SISTEMA',
                        content: 'El registro no puede ser editado, se encuentra ' + estado,
                        animation: 'zoom',
                        closeAnimation: 'zoom',
                        type: 'red',
                        typeAnimated: true
                    });
                } else {
                    if (codigo !== null || codigo === '') {
                        $.confirm({
                            title: 'AVISO DEL SISTEMA',
                            content: 'Desea Anular este registro!',
                            theme: 'material',
                            animation: 'zoom',
                            closeAnimation: 'zoom',
                            type: 'red',
                            typeAnimated: true,
                            buttons: {
                                aceptar: {
                                    text: 'Aceptar',
                                    btnClass: 'btn-primary',
                                    keys: ['enter', 'shift'],
                                    action: function () {
                                        $('#txt_FechaDocumento').val('01/01/2021');
                                        $('#txt_FechaRecepcion').val('01/01/2021');
                                        $('#txt_Numero').val(codigo);
                                        mode = 'D';
                                        fn_GrabarDatos();
                                    }
                                },
                                cancelar: function () {
                                }
                            }
                        });
                    } else {
                        $.alert({
                            theme: 'material',
                            title: 'AVISO DEL SISTEMA',
                            content: 'Debe Seleccionar un Registro',
                            animation: 'zoom',
                            closeAnimation: 'zoom',
                            type: 'red',
                            typeAnimated: true
                        });
                    }
                }
            } else if ($.trim($(opcion).text()) === "Ver Documento") {
                if (archivo !== null && archivo !== '') {
                    document.location.target = "_blank";
                    document.location.href = "../Descarga?opcion=MesaParte&periodo=" + periodo + "&codigo=" + tipo + "-" + codigo + "&documento=" + archivo;
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
            } else {
                $.alert({
                    theme: 'material',
                    title: 'AVISO DEL SISTEMA',
                    content: "No hay Opci?n a mostrar",
                    animation: 'zoom',
                    closeAnimation: 'zoom',
                    type: 'orange',
                    typeAnimated: true
                });
            }
        });
        //SELECCIONAMOS UN REGISTRO DE LA GRILLA
        $("#div_GrillaPrincipal").on('rowselect', function (event) {
            var args = event.args;
            var row = $("#div_GrillaPrincipal").jqxGrid('getrowdata', args.rowindex);
            codigo = row['numero'];
            estado = row['estado'];
            archivo = row['archivo'];
        });
        //CREA LOS ELEMENTOS DE LAS VENTANAS
        var customButtonsDemo = (function () {
            function _createElements() {
                //INICIA LOS VALORES DE LA VENTANA
                var posicionX, posicionY;
                var ancho = 600;
                var alto = 345;
                posicionX = ($(window).width() / 2) - (ancho / 2);
                posicionY = ($(window).height() / 2) - (alto / 2);
                $('#div_VentanaPrincipal').jqxWindow({
                    position: {x: posicionX, y: posicionY},
                    width: ancho, height: alto, resizable: false,
                    cancelButton: $('#btn_Cancelar'),
                    initContent: function () {
                        $("#txt_Numero").jqxInput({width: 120, height: 20, disabled: true});
                        $("#txt_Institucion").jqxInput({height: 20, width: 450, minLength: 1, items: 15, source: dataAdapterInstitucion});
                        $('#txt_Institucion').on('select', function (event) {
                            if (event.args) {
                                var item = event.args.item;
                                if (item) {
                                    institucion = item.value;
                                }
                            }
                        });
                        $("#cbo_Prioridad").jqxDropDownList({animationType: 'fade', width: 200, dropDownWidth: 250, height: 20});
                        $("#cbo_TipoDocumento").jqxDropDownList({animationType: 'fade', width: 200, dropDownWidth: 250, height: 20});
                        $('#cbo_TipoDocumento').on('change', function () {
                            if (mode === 'I')
                                fn_CorrelativoDocumento($("#cbo_TipoDocumento").val());
                        });
                        $("#txt_NumeroDocumento").jqxInput({width: 100, height: 20});
                        $("#cbo_Clasificacion").jqxDropDownList({animationType: 'fade', width: 200, dropDownWidth: 250, height: 20});
                        $("#txt_FechaDocumento").jqxDateTimeInput({culture: 'es-PE', animationType: 'fade', width: 120, height: 20});
                        $("#txt_FechaRecepcion").jqxDateTimeInput({culture: 'es-PE', width: 120, height: 20, disabled: true});
                        $("#txt_Asunto").jqxInput({placeHolder: "Ingrese el asunto", width: 450, height: 20});
                        $("#txt_PostFirma").jqxInput({placeHolder: "Ingrese a quien va Diridigo", width: 450, height: 20});
                        $("#txt_Cargo").jqxInput({placeHolder: "Ingrese el Cargo", width: 450, height: 20});
                        $("#div_Legajos").jqxNumberInput({width: 50, height: 20, max: 99, digits: 2, decimalDigits: 0});
                        $("#div_Folios").jqxNumberInput({width: 50, height: 20, max: 99, digits: 2, decimalDigits: 0});
                        $("#cbo_Referencia").jqxDropDownList({animationType: 'fade', checkboxes: true, width: 460, dropDownWidth: 650, height: 20});
                        $('#btn_Cancelar').jqxButton({width: '65px', height: 25});
                        $('#btn_Guardar').jqxButton({width: '65px', height: 25});
                        $('#btn_Guardar').on('click', function () {
                            var msg = "";
                            msg += fn_validaCombos('#cbo_Prioridad', "Seleccione la Prioridad.");
                            msg += fn_validaCombos('#cbo_TipoDocumento', "Seleccione la Tipo de Documento.");
                            msg += fn_validaCombos('#cbo_Clasificacion', "Seleccione la Clasificacion del Documento.");
                            if (msg === "")
                                msg = fn_verificarInstitucion();
                            if (msg === "") {
                                $('#frm_MesaParte').jqxValidator('validate');
                            } else {
                                $.alert({
                                    theme: 'material',
                                    title: 'AVISO DEL SISTEMA',
                                    content: msg,
                                    animation: 'zoom',
                                    closeAnimation: 'zoom',
                                    type: 'orange',
                                    typeAnimated: true
                                });
                            }
                        });
                        $('#frm_MesaParte').jqxValidator({
                            rules: [
                                {input: '#txt_Institucion', message: 'Ingrese el nombre de la instituci?n', action: 'keyup, blur', rule: 'required'},
                                {input: '#txt_NumeroDocumento', message: 'Ingrese el Numero de Documento!', action: 'keyup, blur', rule: 'required'},
                                {input: '#txt_Asunto', message: 'Ingrese el Asunto!', action: 'keyup, blur', rule: 'required'},
                                {input: '#txt_FechaDocumento', message: 'Ingrese la fecha de documento', action: 'keyup, blur', rule: 'required'},
                                {input: '#txt_FechaRecepcion', message: 'Ingrese la fecha de recepci?n!', action: 'keyup, blur', rule: 'required'},
                                {input: '#txt_FechaDocumento', message: 'Ingrese una fecha valida de documento', action: 'valueChanged',
                                    rule: function (input, commit) {
                                        var date = $('#txt_FechaDocumento').jqxDateTimeInput('value');
                                        var result = date.getFullYear() >= 2015 && date.getFullYear() <= 2040;
                                        return result;
                                    }
                                },
                                {input: '#txt_PostFirma', message: 'Ingrese la firma!', action: 'keyup, blur', rule: 'required'}
                            ]
                        });
                        $('#frm_MesaParte').jqxValidator({
                            onSuccess: function () {
                                fn_GrabarDatos();
                            }
                        });
                    }
                });
            }
            return {init: function () {
                    _createElements();
                }
            };
        }());
        $(document).ready(function () {
            customButtonsDemo.init();
        });
        function fn_verificarInstitucion() {
            var dato = "";
            dato = institucion;
            if (dato === "" || (dato.trim()).length === 0 || dato === null) {
                $.alert({
                    theme: 'material',
                    title: 'AVISO DEL SISTEMA',
                    content: msg,
                    animation: 'zoom',
                    closeAnimation: 'zoom',
                    type: 'orange',
                    typeAnimated: true
                });
                return "Ingrese el nombre de la Institucion";
            } else {
                return "";
            }
        }
        //FUNCION PARA ACTUALIZAR DATOS DE LA GRILLA
        function fn_Refrescar() {
            $("#div_GrillaPrincipal").remove();
            $("#div_VentanaPrincipal").remove();
            $("#div_ContextMenu").remove();
            var $contenidoAjax = $('#div_Detalle').html('<img src="../Imagenes/Fondos/cargando.gif">');
            $.ajax({
                type: "GET",
                url: "../RemisionDocumentos",
                data: {mode: "G", periodo: periodo, mes: mes, tipo: tipo},
                success: function (data) {
                    $contenidoAjax.html(data);
                }
            });
        }
        //FUNCION PARA GENERAR NUMERO CORRELATIVO DE DOCUMENTO
        function fn_CorrelativoDocumento(cod) {
            $('#txt_NumeroDocumento').val('');
            $.ajax({
                type: "GET",
                url: "../RemisionDocumentos",
                data: {mode: 'B', periodo: periodo, tipoDocumento: cod},
                success: function (data) {
                    $('#txt_NumeroDocumento').val(data);
                }
            });
        }
        //FUNCION PARA CARGAR VENTANA DE NUEVO REGISTRO
        function fn_NuevoRegistro() {
            $("#txt_NumeroDocumento").val('');
            $('#cbo_Referencia').jqxDropDownList({disabled: false});
            fn_cargarComboAjax("#cbo_Referencia", {mode: 'referenciaDoc', periodo: periodo, unidadOperativa: mode});
            $('#cbo_Referencia').jqxDropDownList('addItem', {label: 'Seleccione', value: '0'});
            $.ajax({
                type: "GET",
                url: "../RemisionDocumentos",
                data: {mode: mode, periodo: periodo, mes: mes, tipo: tipo},
                success: function (data) {
                    $('#txt_Numero').val(data);
                }
            });
            $("#cbo_Prioridad").jqxDropDownList('selectItem', 1);
            $("#cbo_TipoDocumento").jqxDropDownList('selectItem', 0);
            $("#cbo_Clasificacion").jqxDropDownList('selectItem', 1);
            //$("#cbo_Referencia").jqxDropDownList('selectItem', 0);
            $("#div_FechaRecepcion").val(new Date);
            $("#txt_FechaRecepcion").val(new Date);
            $("#txt_Institucion").val('');
            $("#txt_NumeroDocumento").val('');
            $("#txt_Asunto").val('');
            $("#txt_PostFirma").val('');
            $("#div_Legajos").val('0');
            $("#div_Folios").val('0');
            $('#div_VentanaPrincipal').jqxWindow({isModal: true, modalOpacity: 0.9});
            $('#div_VentanaPrincipal').jqxWindow('open');
        }
        //FUNCION PARA CARGAR VENTANA PARA EDITAR REGISTRO
        function fn_EditarRegistro() {
            //fn_cargarComboAjax("#cbo_Referencia",{mode: 'referenciaDoc',periodo: periodo,unidadOperativa: mode,codigo: usuRemitente});
            $("#txt_Numero").val('');
            $.ajax({
                type: "GET",
                url: "../RemisionDocumentos",
                data: {mode: mode, periodo: periodo, mes: mes, tipo: tipo, codigo: codigo},
                success: function (data) {
                    var dato = data.split("+++");
                    if (dato.length === 13) {
                        $("#txt_Numero").val(codigo);
                        $("#cbo_TipoDocumento").jqxDropDownList('selectItem', dato[0]);
                        $("#txt_NumeroDocumento").val(dato[1]);
                        institucion = dato[2];
                        $("#txt_Institucion").val(dato[3]);
                        $("#txt_PostFirma").val(dato[4]);
                        $("#txt_Cargo").val(dato[5]);
                        $("#txt_Asunto").val(dato[6]);
                        $("#cbo_Prioridad").jqxDropDownList('selectItem', dato[7]);
                        $("#cbo_Clasificacion").jqxDropDownList('selectItem', dato[8]);
                        $('#txt_FechaDocumento').jqxDateTimeInput('setDate', dato[9]);
                        $('#txt_FechaRecepcion').jqxDateTimeInput('setDate', dato[10]);
                        $("#div_Legajos").val(dato[11]);
                        $("#div_Folios").val(dato[12]);
                        // $("#cbo_Referencia").jqxDropDownList('addItem', {label: docReferencia, value: dato[15]});
                        // $("#cbo_Referencia").jqxDropDownList('selectItem', dato[15]);
                    }
                }
            });
            $('#cbo_Referencia').jqxDropDownList({disabled: true});
            $('#div_VentanaPrincipal').jqxWindow({isModal: true});
            $('#div_VentanaPrincipal').jqxWindow('open');
        }
        //FUNCION PARA GRABAR LOS DATOS DE LA VENTANA PRINCIPAL
        function fn_GrabarDatos() {
            var numero = $("#txt_Numero").val();
            var prioridad = $("#cbo_Prioridad").val();
            var documento = $("#cbo_TipoDocumento").val();
            var numeroDocumento = $("#txt_NumeroDocumento").val();
            var clasificacion = $("#cbo_Clasificacion").val();
            var fechaDocumento = $("#txt_FechaDocumento").val();
            var fechaRecepcion = $("#txt_FechaRecepcion").val();
            var asunto = $("#txt_Asunto").val();
            var postFirma = $("#txt_PostFirma").val();
            var cargo = $("#txt_Cargo").val();
            var legajos = $("#div_Legajos").val();
            var folios = $("#div_Folios").val();
            var items = $("#cbo_Referencia").jqxDropDownList('getCheckedItems');
            var lista = new Array();
            $.each(items, function (index) {
                lista.push(this.value);
            });
            $.ajax({
                type: "POST",
                url: "../IduMesaPartes",
                data: {mode: mode, periodo: periodo, tipo: tipo, mes: mes, numero: numero, prioridad: prioridad,
                    documento: documento, numeroDocumento: numeroDocumento, clasificacion: clasificacion,
                    fechaDocumento: fechaDocumento, fechaRecepcion: fechaRecepcion, asunto: asunto,
                    postFirma: postFirma, cargo: cargo, legajos: legajos, folios: folios,
                    referencia: JSON.stringify(lista), institucion: institucion},
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
        }
    });
</script>
<div id="div_GrillaPrincipal"></div>
<div id="div_VentanaPrincipal" style="display: none">
    <div>
        <span style="float: left">REMISION DE DOCUMENTOS</span>
    </div>
    <div style="overflow: hidden">
        <form id="frm_MesaParte" name="frm_MesaParte" method="post">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="inputlabel">Correlativo : </td>
                    <td colspan="3"><input type="text" id="txt_Numero" name="txt_Numero"/></td>
                </tr>
                <tr>
                    <td class="inputlabel">Tipo Documento : </td>
                    <td>
                        <select id="cbo_TipoDocumento" name="cbo_TipoDocumento">
                            <option value="0">Seleccione</option>
                            <c:forEach var="d" items="${objTipoDocumento}">   
                                <option value="${d.codigo}">${d.descripcion}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="inputlabel">Nro Documento : </td>
                    <td><input type="text" id="txt_NumeroDocumento" name="txt_NumeroDocumento"/></td>
                </tr>
                <tr>
                    <td class="inputlabel">Instituci?n : </td>
                    <td colspan="3"><input type="text" id="txt_Institucion" name="txt_Institucion" style="text-transform: uppercase;" autocomplete="off"/></td>
                </tr> 
                <tr>
                    <td class="inputlabel">Dirigido a : </td>
                    <td colspan="3"><input type="text" id="txt_PostFirma" name="txt_PostFirma" style="text-transform: uppercase;"/></td>
                </tr>
                <tr>
                    <td class="inputlabel">Cargo : </td>
                    <td colspan="3"><input type="text" id="txt_Cargo" name="txt_Cargo" style="text-transform: uppercase;"/></td>
                </tr>
                <tr>
                    <td class="inputlabel">Asunto : </td>
                    <td colspan="3"><input type="text" id="txt_Asunto" name="txt_Asunto" style="text-transform: uppercase;"/></td>
                </tr>
                <tr>
                    <td class="inputlabel">Doc. Referencia : </td>
                    <td colspan="3">
                        <select id="cbo_Referencia" name="cbo_Referencia">
                            <option value="0">Seleccione</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="inputlabel">Prioridad : </td>
                    <td colspan="3">
                        <select id="cbo_Prioridad" name="cbo_Prioridad">
                            <option value="0">Seleccione</option>
                            <c:forEach var="d" items="${objPrioridad}">
                                <option value="${d.codigo}">${d.descripcion}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="inputlabel">Clasificaci&oacute;n : </td>
                    <td colspan="3">
                        <select id="cbo_Clasificacion" name="cbo_Clasificacion">
                            <option value="0">Seleccione</option>
                            <c:forEach var="d" items="${objClasificacion}">
                                <option value="${d.codigo}">${d.descripcion}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="inputlabel">Fec. Documento : </td>
                    <td ><div id="txt_FechaDocumento"></div></td>
                    <td class="inputlabel">Fec. Emisi&oacute;n : </td>
                    <td><div id="txt_FechaRecepcion"></div></td>
                </tr>
                <tr>
                    <td class="inputlabel">Legajos : </td>
                    <td colspan="3"><div id="div_Legajos"></div></td>
                </tr>
                <tr>
                    <td class="inputlabel">Folios : </td>
                    <td colspan="3"><div id="div_Folios"></div></td>
                </tr>
                <tr>
                    <td class="Summit" colspan="4">
                        <div>
                            <input type="button" id="btn_Guardar"  value="Guardar" style="margin-right: 20px"/>
                            <input type="button" id="btn_Cancelar" value="Cancelar" style="margin-right: 20px"/>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div id='div_ContextMenu' style='display:none;'>
    <ul>
        <li>Editar</li>
        <li>Anular</li> 
        <li type='separator'></li>
        <li>Ver Documento</li>
    </ul>
</div>