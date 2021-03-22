<%-- 
    Document   : ListaUsuario
    Created on : 23/03/2017, 01:27:09 PM
    Author     : H-URBINA-M
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    var usuario = null;
    var mode = null;
    var msg = "";
    var lista = new Array();
    <c:forEach var="d" items="${objUsuario}">
    var result = {usuario: '${d.usuario}', apellido: '${d.apellido}', iniciales: '${d.iniciales}', areaLaboral: '${d.areaLaboral}',
        correo: '${d.correo}', telefono: '${d.telefono}', cargo: '${d.sistema}', estado: '${d.estado}'};
    lista.push(result);
    </c:forEach>
    $(document).ready(function () {
        //PARA CARGAR LOS ELEMENTOS DE LA GRILLA
        var source = {
            localdata: lista,
            datatype: "array",
            datafields:
                    [
                        {name: 'usuario', type: "string"},
                        {name: 'apellido', type: "string"},
                        {name: 'iniciales', type: "string"},
                        {name: 'areaLaboral', type: "string"},
                        {name: 'cargo', type: "string"},
                        {name: 'correo', type: "string"},
                        {name: 'telefono', type: "string"},
                        {name: 'estado', type: "string"}
                    ],
            root: "Usuarios",
            record: "Usuarios",
            id: 'usuario'
        };
        var dataAdapter = new $.jqx.dataAdapter(source);
        //ESTILOS A LAS CELDAS DE LA GRILLA 
        var cellclass = function (row, datafield, value, rowdata) {
            if (datafield === "apellido") {
                return "RowBold";
            }
        };
        //DEFINIMOS LOS CAMPOS Y DATOS DE LA GRILLA
        $("#div_GrillaPrincipal").jqxGrid({
            width: '99.8%',
            height: ($(window).height() - 32),
            source: dataAdapter,
            autoheight: false,
            autorowheight: false,
            altrows: true,
            sortable: true,
            pageable: true,
            filterable: true,
            autoshowfiltericon: true,
            columnsresize: true,
            showfilterrow: true,
            showtoolbar: true,
            editable: false,
            rendertoolbar: function (toolbar) {
                // ADICIONAMOS BOTONES A LA BARRA DE ESTADOS
                var container = $("<div style='overflow: hidden; position: relative; margin: 1px;'></div>");
                var ButtonExportar = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px;' src='../Imagenes/Botones/pauf42.gif' width=18 height=18/><span style='margin-left: 4px; position: relative; top: -3px;'> </span></div>");
                var ButtonRecargar = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px;' src='../Imagenes/Botones/refresh42.gif' width=18 height=18/><span style='margin-left: 4px; position: relative; top: -3px;'> </span></div>");
                var ButtonSalir = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px;' src='../Imagenes/Botones/exit42.gif' width=18 height=18/><span style='margin-left: 4px; position: relative; top: -3px;'> </span></div>");
                container.append(ButtonExportar);
                container.append(ButtonRecargar);
                container.append(ButtonSalir);
                toolbar.append(container);
                ButtonExportar.jqxButton({width: 30, height: 22});
                ButtonExportar.jqxTooltip({position: 'bottom', content: "Exportar Datos"});
                ButtonRecargar.jqxButton({width: 30, height: 22});
                ButtonRecargar.jqxTooltip({position: 'bottom', content: "Recargar"});
                ButtonSalir.jqxButton({width: 30, height: 22});
                ButtonSalir.jqxTooltip({position: 'bottom', content: "Salir de la Pantalla"});
                //ASIGNAMOS LAS FUNCIONES PARA EL BOTON EXPORTAR
                ButtonExportar.click(function (event) {
                    $("#div_GrillaPrincipal").jqxGrid('exportdata', 'xls', 'ActividadTarea');
                });
                //ASIGNAMOS LAS FUNCIONES PARA EL BOTON RECARGAR
                ButtonRecargar.click(function (event) {
                    fn_Refrescar();
                });
                //ASIGNAMOS LAS FUNCIONES PARA EL BOTON SALIR
                ButtonSalir.click(function (event) {
                    window.location = "../Login/Principal.jsp";
                });
            },
            columns: [
                {text: ' ', sortable: false, filterable: false, editable: false, groupable: false, draggable: false, resizable: false,
                    datafield: '', columntype: 'number', width: '2%', pinned: true, cellsrenderer: function (row, column, value) {
                        return "<div style='margin:4px; text-align: center;'>" + (value + 1) + "</div>";
                    }
                },
                {text: 'USUARIO', dataField: 'usuario', width: '8%', align: 'center', cellsAlign: 'center', cellclassname: cellclass},
                {text: 'APELLIDOS Y NOMBRES', dataField: 'apellido', width: '32%', align: 'center', cellsAlign: 'left', cellclassname: cellclass},
                {text: 'INICIALES', dataField: 'iniciales', width: '7%', align: 'center', cellsAlign: 'center', cellclassname: cellclass},
                {text: 'AREA LABORAL', dataField: 'areaLaboral', filtertype: 'checkedlist', width: '10%', align: 'center', cellsAlign: 'center', cellclassname: cellclass},
                {text: 'CARGO', dataField: 'cargo', filtertype: 'checkedlist', width: '15%', align: 'center', cellsAlign: 'center', cellclassname: cellclass},
                {text: 'TELEFONO', dataField: 'telefono', filtertype: 'checkedlist', width: '8%', align: 'center', cellsAlign: 'center', cellclassname: cellclass},
                {text: 'CORREO', dataField: 'correo', filtertype: 'checkedlist', width: '10%', align: 'center', cellsAlign: 'center', cellclassname: cellclass},
                {text: 'ESTADO', dataField: 'estado', filtertype: 'checkedlist', width: '8%', align: 'center', cellsAlign: 'center', cellclassname: cellclass}
            ]
        });
        // DEFINIMOS EL MENU CONTEXTUAL
        var contextMenu = $("#div_ContextMenu").jqxMenu({width: 200, height: 79, autoOpenPopup: false, mode: 'popup'});
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
            if (usuario === null || usuario === '') {
                $.alert({
                    theme: 'material',
                    title: 'AVISO DEL SISTEMA',
                    content: 'Debe Seleccionar un Registro',
                    animation: 'zoom',
                    closeAnimation: 'zoom',
                    type: 'orange',
                    typeAnimated: true
                });
            } else {
                if ($.trim($(opcion).text()) === "Editar") {
                    mode = 'U';
                    fn_EditarRegistro();
                } else if ($.trim($(opcion).text()) === "Inactivar") {
                    $.confirm({
                        theme: 'material',
                        title: 'AVISO DEL SISTEMA',
                        content: '¿Desea Inactivar este Usuario?',
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
                                    mode = 'D';
                                    fn_GrabarDatos();
                                }
                            },
                            cancelar: function () {
                            }
                        }
                    });
                } else if ($.trim($(opcion).text()) === "Opciones") {
                    fn_UsuarioOpciones();
                }
            }
        });
        //SELECCIONAMOS UN REGISTRO DE LA GRILLA
        $("#div_GrillaPrincipal").on('rowselect', function (event) {
            var args = event.args;
            var row = $("#div_GrillaPrincipal").jqxGrid('getrowdata', args.rowindex);
            usuario = row['usuario'];
        });
        //CREA LOS ELEMENTOS DE LAS VENTANAS
        var customButtonsDemo = (function () {
            function _createElements() {
                //INICIA LOS VALORES DE LA VENTANA
                var posicionX, posicionY;
                var ancho = 400;
                var alto = 240;
                posicionX = ($(window).width() / 2) - (ancho / 2);
                posicionY = ($(window).height() / 2) - (alto / 2);
                $('#div_VentanaPrincipal').jqxWindow({
                    position: {x: posicionX, y: posicionY},
                    width: ancho, height: alto, resizable: false,
                    cancelButton: $('#btn_Cancelar'),
                    initContent: function () {
                        $("#txt_UsuarioApellidos").jqxInput({placeHolder: 'APELLIDOS', width: 300, height: 20});
                        $("#txt_UsuarioNombres").jqxInput({placeHolder: 'NOMBRES', width: 300, height: 20});
                        $("#txt_UsuarioIniciales").jqxInput({placeHolder: 'INICIALES', width: 100, height: 20});
                        $("#cbo_AreaLaboral").jqxDropDownList({width: 200, height: 20, promptText: "Seleccione"});
                        $("#txt_UsuarioCargo").jqxInput({placeHolder: 'CARGO', width: 300, height: 20});
                        $("#txt_UsuarioTelefono").jqxMaskedInput({ width: 150, height: 20, mask: '###-###-###'});
                        $("#txt_UsuarioCorreo").jqxInput({placeHolder: 'CORREO', width: 300, height: 20});
                        $("#cbo_Estado").jqxDropDownList({width: 100, height: 20, promptText: "Seleccione"});
                        $('#btn_Cancelar').jqxButton({width: '65px', height: 25});
                        $('#btn_Guardar').jqxButton({width: '65px', height: 25});
                        $('#btn_Guardar').on('click', function () {
                            fn_GrabarDatos();
                        });
                    }
                });
                //INICIA LOS VALORES DE LA VENTANA OPCIONES
                ancho = 400;
                alto = 465;
                posicionX = ($(window).width() / 2) - (ancho / 2);
                posicionY = ($(window).height() / 2) - (alto / 2);
                $('#div_VentanaOpciones').jqxWindow({
                    position: {x: posicionX, y: posicionY},
                    width: ancho, height: alto, resizable: false,
                    cancelButton: $('#btn_CancelarOpciones'),
                    initContent: function () {
                        $('#div_Opciones').jqxTree({width: 390, height: 400, hasThreeStates: true, checkboxes: true});
                        $('#div_Opciones').css('visibility', 'visible');
                        $('#btn_CancelarOpciones').jqxButton({width: '65px', height: 25});
                        $('#btn_GuardarOpciones').jqxButton({width: '65px', height: 25});
                        $('#btn_GuardarOpciones').on('click', function () {
                            fn_GrabarOpciones();
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
            fn_cargarComboAjax("#cbo_AreaLaboral", {mode: 'areaLaboral'});
        });
        //FUNCION PARA ACTUALIZAR DATOS DE LA GRILLA
        function fn_Refrescar() {
            $("#div_VentanaPrincipal").remove();
            $("#div_ContextMenu").remove();
            $("#div_VentanaOpciones").remove();
            var $contenidoAjax = $('#div_Detalle').html('<img src="../Imagenes/Fondos/cargando.gif">');
            $.ajax({
                type: "GET",
                url: "../Usuario",
                data: {mode: 'G'},
                success: function (data) {
                    $contenidoAjax.html(data);
                }
            });
        }
        //FUNCION PARA CARGAR VENTANA PARA EDITAR REGISTRO
        function fn_EditarRegistro() {
            $.ajax({
                type: "GET",
                url: "../Usuario",
                data: {mode: mode, usuario: usuario},
                success: function (data) {
                    var dato = data.split("+++");
                    if (dato.length === 8) {
                        $("#txt_UsuarioApellidos").val(dato[0]);
                        $("#txt_UsuarioNombres").val(dato[1]);
                        $("#txt_UsuarioIniciales").val(dato[2]);
                        $("#cbo_AreaLaboral").jqxDropDownList('selectItem', dato[3]);
                        $("#txt_UsuarioCargo").val(dato[4]);
                        $("#txt_UsuarioTelefono").val(dato[5]);
                        $("#txt_UsuarioCorreo").val(dato[6]);
                        $("#cbo_Estado").jqxDropDownList('selectItem', dato[7]);
                        }
                }
            });
            $('#div_VentanaPrincipal').jqxWindow({isModal: true, modalOpacity: 0.8});
            $('#div_VentanaPrincipal').jqxWindow('open');
        }
        //FUNCION PARA GRABAR LOS DATOS DE LA VENTANA PRINCIPAL
        function fn_GrabarDatos() {
            var apellidos = $("#txt_UsuarioApellidos").val();
            var nombres = $("#txt_UsuarioNombres").val();
            var iniciales = $("#txt_UsuarioIniciales").val();
            var areaLaboral = $("#cbo_AreaLaboral").val();
            var estado = $("#cbo_Estado").val();
            var opre = $("#cbo_Opre").val();
            var acta = $("#cbo_Acta").val();
            $.ajax({
                type: "POST",
                url: "../IduUsuario",
                data: {mode: mode, usuario: usuario, unidadOperativa: unidadOperativa,
                    apellidos: apellidos, nombres: nombres, iniciales: iniciales, areaLaboral: areaLaboral,
                    estado: estado, opre: opre, acta: acta},
                success: function (data) {
                    msg = data;
                    if (msg === "GUARDO") {
                        $.confirm({
                            title: 'AVISO DEL SISTEMA',
                            content: 'Datos procesados correctamente!!',
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
        function fn_UsuarioOpciones() {
            $.ajax({
                type: "GET",
                url: "../Usuario",
                data: {mode: 'M', usuario: usuario},
                success: function (data) {
                    data = data.replace("[", "");
                    var fila = data.split("[");
                    var data = new Array();
                    for (i = 1; i < fila.length; i++) {
                        var columna = fila[i];
                        var datos = columna.split("+++");
                        while (datos[3].indexOf(']') > 0) {
                            datos[3] = datos[3].replace("]", "");
                        }
                        while (datos[3].indexOf(',') > 0) {
                            datos[3] = datos[3].replace(",", "");
                        }
                        var row = {id: datos[0],
                            parentid: datos[1],
                            text: datos[3],
                            value: datos[0]};
                        data.push(row);
                        var mod = {id: datos[1],
                            parentid: -1,
                            text: datos[2],
                            value: datos[1]};
                        data.push(mod);
                    }
                    var source = {
                        datatype: "json",
                        datafields: [
                            {name: 'id'},
                            {name: 'parentid'},
                            {name: 'text'},
                            {name: 'value'}
                        ],
                        id: 'id',
                        localdata: data
                    };
                    var dataAdapter = new $.jqx.dataAdapter(source);
                    dataAdapter.dataBind();
                    var records = dataAdapter.getRecordsHierarchy('id', 'parentid', 'items', [{name: 'text', map: 'label'}]);
                    $('#div_Opciones').jqxTree({source: records});
                    $('#div_Opciones').jqxTree('expandAll');
                    $.ajax({
                        type: "GET",
                        url: "../Usuario",
                        data: {mode: 'O', usuario: usuario},
                        success: function (data) {
                            data = data.replace("[", "");
                            data = data.replace("]", "");
                            var fila = data.split(",");
                            for (i = 0; i < fila.length; i++) {
                                var dato = "#" + fila[i].trim();
                                $("#div_Opciones").jqxTree('checkItem', $(dato)[0], true);
                            }
                        }
                    });
                }
            });
            $('#div_VentanaOpciones').jqxWindow({isModal: true, modalOpacity: 0.8});
            $('#div_VentanaOpciones').jqxWindow('open');
        }
        function fn_GrabarOpciones() {
            var lista = new Array();
            var items = $('#div_Opciones').jqxTree('getCheckedItems');
            for (var i = 0; i < items.length; i++) {
                var item = items[i];
                lista.push(item.id);
            }
            $.ajax({
                type: "POST",
                url: "../IduUsuario",
                data: {mode: 'O', usuario: usuario, lista: JSON.stringify(lista)},
                success: function (data) {
                    msg = data;
                    if (msg === "GUARDO") {
                        $.confirm({
                            title: 'AVISO DEL SISTEMA',
                            content: 'Datos procesados correctamente!!',
                            type: 'green',
                            typeAnimated: true,
                            autoClose: 'cerrarAction|1000',
                            buttons: {
                                cerrarAction: {
                                    text: 'Cerrar',
                                    action: function () {
                                        $('#div_VentanaOpciones').jqxWindow('close');
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
        <span style="float: left">USUARIO DEL SISTEMA</span>
    </div>
    <div style="overflow: hidden">
        <form id="frm_Usuario" name="frm_Usuario" method="post" >
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="inputlabel">Apellidos : </td>
                    <td><input type="text" id="txt_UsuarioApellidos" name="txt_UsuarioApellidos" style="text-transform: uppercase;"/></td>
                </tr>
                <tr>
                    <td class="inputlabel">Nombres : </td>
                    <td><input type="text" id="txt_UsuarioNombres" name="txt_UsuarioNombres" style="text-transform: uppercase;"/></td>
                </tr> 
                <tr>
                    <td class="inputlabel">Iniciales : </td>
                    <td><input type="text" id="txt_UsuarioIniciales" name="txt_UsuarioIniciales" style="text-transform: uppercase;"/></td>
                </tr> 
                <tr>
                    <td class="inputlabel">Area Lab. : </td>
                    <td>
                        <select id="cbo_AreaLaboral" name="cbo_AreaLaboral">
                            <option value="0">Seleccione</option>
                        </select>
                    </td> 
                </tr>
                <tr>
                    <td class="inputlabel">Cargo : </td>
                    <td><input type="text" id="txt_UsuarioCargo" name="txt_UsuarioCargo" style="text-transform: uppercase;"/></td>
                </tr>
                <tr>
                    <td class="inputlabel">Teléfono : </td>
                    <td><input type="text" id="txt_UsuarioTelefono" name="txt_UsuarioTelefono" style="text-transform: uppercase;"/></td>
                </tr>
                <tr>
                    <td class="inputlabel">Correo : </td>
                    <td><input type="email" id="txt_UsuarioCorreo" name="txt_UsuarioCorreo" style="text-transform: uppercase;"/></td>
                </tr>
                <tr>
                    <td class="inputlabel">Estado : </td>
                    <td>
                        <select id="cbo_Estado" name="cbo_Estado">
                            <option value="AC">ACTIVO</option> 
                            <option value="IN">INACTIVO</option> 
                        </select>
                    </td> 
                </tr>
                <tr>
                    <td class="Summit" colspan="4">
                        <div >
                            <input type="button" id="btn_Guardar"  value="Guardar" style="margin-right: 20px"/>
                            <input type="button" id="btn_Cancelar" value="Cancelar" style="margin-right: 20px"/>
                        </div>
                    </td>
                </tr>
            </table>  
        </form>
    </div>
</div>
<div id="cbo_Ajax" style='display: none;' ></div>
<div id='div_ContextMenu' style='display: none;'>
    <ul>
        <li>Editar</li>
        <li>Inactivar</li>   
        <li>Opciones</li>
    </ul>
</div>
<div id="div_VentanaOpciones" style="display: none">
    <div>
        <span style="float: left">SELECCIONE LAS OPCIONES DEL USUARIO</span>
    </div>
    <div style="overflow: hidden">
        <div id='div_Opciones'></div>  
        <div class="Summit">
            <input type="button" id="btn_GuardarOpciones"  value="Guardar" style="margin-right: 20px"/>
            <input type="button" id="btn_CancelarOpciones" value="Cancelar" style="margin-right: 20px"/>
        </div>
    </div>
</div>