<%-- 
    Document   : MainPrincipal
    Created on : 16/01/2017, 08:35:38 AM
    Author     : H-URBINA-M
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true" %>
<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/session.jspf" %>
<html lang="en">
    <head>
        <link type="text/css" rel="stylesheet" href="../css/main.css">
        <link type="text/css" rel="stylesheet" href="../css/styles/jqx.base.css">
        <link type="text/css" rel="stylesheet" href="../css/grid.css">
        <link type="text/css" rel="stylesheet" href="../css/bundled.css">
        <link type="text/css" rel="stylesheet" href="../css/jquery-confirm.css">
        <link type="text/css" rel="stylesheet" href="../css/scaf.css">
        <script type="text/javascript" src="../javascript/validacion.js"></script>
        <script type="text/javascript" src="../javascript/bundled.js"></script>
        <script type="text/javascript" src="../javascript/jquery.js"></script>
        <script type="text/javascript" src="../javascript/jquery-confirm.js"></script>
        <script type="text/javascript" src="../javascript/theme.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxcore.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxmenu.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxsplitter.js"></script> 
        <script type="text/javascript" src="../javascript/jqwidgets/jqxbuttons.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxscrollbar.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxpanel.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxtree.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxexpander.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxlistbox.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxcombobox.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxtooltip.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxdropdownlist.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxdata.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxdata.export.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxgrid.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxgrid.columnsresize.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxgrid.columnsreorder.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxgrid.pager.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxgrid.sort.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxgrid.filter.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxgrid.storage.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxgrid.selection.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxgrid.export.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxgrid.filter.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxgrid.edit.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxgrid.aggregates.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxgrid.grouping.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxnumberinput.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxcheckbox.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxinput.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxmaskedinput.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxpasswordinput.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxtextarea.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxdatetimeinput.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxcalendar.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxwindow.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxvalidator.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxradiobutton.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxnotification.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxpopover.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/jqxtabs.js"></script> 
        <script type="text/javascript" src="../javascript/jqwidgets/jqxfileupload.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/globalization/globalize.js"></script>
        <script type="text/javascript" src="../javascript/jqwidgets/globalization/globalize.culture.es-PE.js"></script>
        <script type="text/javascript" src="../javascript/pdfobject.js" ></script>
        <script type="text/javascript">
            $(document).ready(function () {
                var theme = getTheme();
                var posicionX, posicionY;
                var ancho = 400;
                var alto = 210;
                posicionX = ($(window).width() / 2) - (ancho / 2);
                posicionY = ($(window).height() / 2) - (alto / 2);
                $('#div_WindowPassword').jqxWindow({
                    position: {x: posicionX, y: posicionY},
                    width: ancho, height: alto, resizable: false,
                    cancelButton: $('#btn_CancelarPassword'),
                    initContent: function () {
                        $("#txt_UsuarioPassword").jqxInput({theme: theme, placeHolder: "USUARIO", height: 20, width: 200, disabled: true});
                        $("#txt_NombresPassword").jqxInput({theme: theme, placeHolder: "NOMBRES", height: 20, width: 200, disabled: true});
                        $("#txt_ApellidosPassword").jqxInput({theme: theme, placeHolder: "APELLIDOS", height: 20, width: 200, disabled: true});
                        $("#txt_CargoPassword").jqxInput({theme: theme, placeHolder: "CARGO", height: 20, width: 200, disabled: true});
                        $("#txt_PasswordRegistro").jqxPasswordInput({theme: theme, placeHolder: "CONTRASE??A", width: 200, height: 20, maxLength: 50, showStrength: true, showStrengthPosition: "right"});
                        $("#txt_CompruebaPassword").jqxPasswordInput({theme: theme, placeHolder: "COMPROBAR CONTRASE??A", width: 200, height: 20, maxLength: 50, showStrength: true, showStrengthPosition: "right"});
                        $('#btn_CancelarPassword').jqxButton({width: '65px', height: 22});
                        $('#btn_GuardarPassword').jqxButton({width: '65px', height: 22});
                        $('#btn_GuardarPassword').on('click', function (event) {
                            $('#frm_Password').jqxValidator('validate');
                        });
                        $('#frm_Password').jqxValidator({
                            rules: [
                                {input: '#txt_UsuarioPassword', message: 'Ingrese el Usuario (CIP o DNI)!', action: 'keyup, blur', rule: 'required'},
                                {input: '#txt_UsuarioPassword', message: 'Ingrese Solo Numeros (CIP o DNI)!', action: 'keyup, blur', rule: 'number'},
                                {input: '#txt_NombresPassword', message: 'Ingrese sus Nombres!', action: 'keyup, blur', rule: 'required'},
                                {input: '#txt_ApellidosPassword', message: 'Ingrese sus Apellidos!', action: 'keyup, blur', rule: 'required'},
                                {input: '#txt_PasswordRegistro', message: 'Ingrese una Contrase??a!', action: 'keyup, blur', rule: 'required'},
                                {input: '#txt_CompruebaPassword', message: 'Confirme Contrase??a!', action: 'keyup, blur', rule: 'required'},
                                {input: '#txt_CompruebaPassword', message: 'Contrase??a no coincide!', action: 'keyup, focus', rule: function (input, commit) {
                                        if (input.val() === $('#txt_PasswordRegistro').val()) {
                                            return true;
                                        }
                                        return false;
                                    }
                                }
                            ]
                        });
                        $('#frm_Password').jqxValidator({
                            onSuccess: function () {
                                fn_cambiarPassword();
                            }
                        });
                    }
                });
                var rss = (function ($) {
                    var createWidgets = function () {
                        var posicionX, posicionY;
                        var ancho = 900;
                        var alto = 600;
                        posicionX = ($(window).width() / 2) - (ancho / 2);
                        posicionY = ($(window).height() / 2) - (alto / 2);
                        $('#window').jqxWindow({
                            position: {x: 200, y: 200},
                            resizable: true,
                            width: ancho,
                            height: alto,
                            minWidth: ancho - 200,
                            minHeight: alto - 200
                        });
                        ancho = 400;
                        alto = 200;
                        posicionX = ($(window).width() / 2) - (ancho / 2);
                        posicionY = ($(window).height() / 2) - (alto / 2);
                        $('#div_Mensaje').jqxWindow({
                            position: {x: posicionX, y: posicionY},
                            resizable: true,
                            width: ancho,
                            height: alto,
                            minWidth: ancho - 200,
                            minHeight: alto - 200
                        });
                        $('#div_Mensaje').jqxWindow('focus');
                    };
                    var addEventListeners = function () {
                        $('#div_Tree').on('select', function (event) {
                            var item = $('#div_Tree').jqxTree('getItem', event.args.element).id;
                            if (item !== "0") {
                                if (item === '1') {
                                    $.ajax({
                                        type: "GET",
                                        url: "../Usuarios",
                                        data: {mode: 'U', usuario: '${usuario}'},
                                        success: function (data) {
                                            var dato = data.split("+++");
                                            if (dato.length === 9) {
                                                $("#txt_UsuarioPassword").val('${usuario}');
                                                $("#txt_NombresPassword").val(dato[2]);
                                                $("#txt_ApellidosPassword").val(dato[0] + " " + dato[1]);
                                                $("#txt_CargoPassword").val(dato[4]);
                                                $("#txt_PasswordRegistro").val("");
                                                $("#txt_CompruebaPassword").val("");
                                            }
                                        }
                                    });
                                    $('#div_WindowPassword').jqxWindow({isModal: true, modalOpacity: 0.9});
                                    $('#div_WindowPassword').jqxWindow('open');
                                } else if (item === '2') {
                                    window.location = "../FinalizaSesion";
                                } else {
                                    $('#div_Splitter').jqxSplitter('collapse');
                                    var index = item.indexOf("/");
                                    fn_CargarMenu(item.substr(0, index), item.substr(index + 1));
                                }
                            }
                        });
                    };

                    return {
                        init: function () {
                            createWidgets();
                            addEventListeners();
                        }
                    };
                }(jQuery));
                rss.init();
            });
            function fn_cambiarPassword() {
                var usuario = $("#txt_UsuarioPassword").val();
                var password = $("#txt_PasswordRegistro").val();
                $.ajax({
                    type: "POST",
                    url: "../IduUsuarios",
                    data: {mode: 'P', usuario: usuario, password: password},
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
                                            $('#div_WindowPassword').jqxWindow('close');
                                            $("#div_WindowPassword").remove();
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
            function fn_verDocumento() {
                var options = {
                    pdfOpenParams: {
                        pagemode: "thumbs",
                        navpanes: 1,
                        toolbar: 1,
                        statusbar: 0,
                        view: "FitH"
                    }
                };
                var myPDF = PDFObject.embed("../Descarga/FaxMultiple2019-0097-OPRE.pdf", "#div_ViewerPDF");
            }
            function fn_verMensaje() {
                $('#div_Mensaje').jqxWindow({isModal: true, modalOpacity: 0.9});
                $('#div_Mensaje').jqxWindow('open');
            }
        </script>
    </head> 
    <body oncontextmenu='return false'>
        <div id="div_Menu" ><%@ include file="MenuOpciones.jsp"%></div>
        <div id="div_Contenido"><%@ include file="LogoPresentacion.jsp"%></div>
    </body>
</html>
<div id="div_WindowPassword" style="display: none">
    <div>
        <span style="float: left">REGISTRO DE USUARIOS</span>
    </div>
    <div style="overflow: hidden">
        <form id="frm_Password" name="frm_Password" method="POST" >
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="inputlabel">Usuario : </td>
                    <td style="text-align: left"><input type="text" id="txt_UsuarioPassword" name="txt_UsuarioPassword"/></td>
                </tr>
                <tr>
                    <td class="inputlabel">Nombres : </td>
                    <td style="text-align: left"><input type="text" id="txt_NombresPassword" name="txt_NombresPassword" style="text-transform: uppercase;"/></td>
                </tr>
                <tr>
                    <td class="inputlabel" >Apellidos : </td>
                    <td style="text-align: left"><input type="text" id="txt_ApellidosPassword" name="txt_ApellidosPassword" style="text-transform: uppercase;"/></td>
                </tr>
                <tr>
                    <td class="inputlabel" >Cargo : </td>
                    <td style="text-align: left"><input type="text" id="txt_CargoPassword" name="txt_CargoPassword" style="text-transform: uppercase;"/></td>
                </tr>
                <tr>
                    <td class="inputlabel">Nuevo Password : </td>
                    <td style="text-align: left"><input type="password" name="txt_PasswordRegistro" id="txt_PasswordRegistro"/></td>
                </tr>
                <tr>
                    <td class="inputlabel">Comprobar  password : </td>
                    <td style="text-align: left"><input type="password" name="txt_CompruebaPassword" id="txt_CompruebaPassword"/></td>
                </tr>
                <tr>
                    <td class="Summit" colspan="2">
                        <div>
                            <input type="button" id="btn_GuardarPassword" value="Guardar" style="margin-right: 20px" />
                            <input type="button" id="btn_CancelarPassword" value="Cancelar" style="margin-right: 20px"/>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div id="window" style="display: none">
    <div>
        Fax Multiple 0097-OPRE
    </div>
    <div>
        <div id="div_ViewerPDF" style="width: 100%; height: 100%"> </div> 
    </div>
</div>
<div id="div_Mensaje" style="display: none">
    <div style="font-size: 20px;">
        COMUNICADO
    </div>
    <div>
        <div style="font-size: 20px;">LAS UU/OO QUE NO HAN CUMPLIDO CON EL REGISTRO DE INFORMACI??N EN EL M??DULO PAC-PROCESOS, <strong>NO PODR??N REALIZAR LA EJECUCI??N PRESUPUESTARIA.</strong></div> 
    </div>
</div>
<script type="text/javascript">
    //fn_verMensaje();
</script>