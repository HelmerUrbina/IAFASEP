/* global datos */
function scrollb() {
    window.status = "IAFAS - Institución Administradora de Fondos de Aseguramiento en Salud del Ejército del Perú";
}
//FUNCION PARA VALIDAR LA AUTORIZACION DEL USUSARIO
function fn_validaAutorizacion(autorizacion) {
    if (!autorizacion) {
        alert("USUARIO NO AUTORIZADO PARA ESTE TIPO DE OPERACIÓN");
        location.reload();
    }
}
//FUNCION PARA FINALIZAR LA SESSION DEL USUARIO
function fn_FinalizaSession(){
    window.location = "../FinalizaSesion";
}
//FUNCION PARA REGRESAR AL MENU PRINCIPAL
function fn_MenuPrincipal() {
    location.reload();
}
//FUNCION PARA CARGAR LAS OPCIONES DEL MENU
function fn_CargarMenu(servlet, mode) {
    var $contenidoAjax = $('#div_Contenido').html('<img src="../Imagenes/Fondos/cargando.gif">');
    $.ajax({
        type: "GET",
        url: "../" + servlet,
        data: {mode: mode},
        success: function (data) {
            $contenidoAjax.html(data);
        }
    });
}
//FUNCION PARA VALIDAR UN CAMPO
function fn_validaCampo(campo, msg) {
    if (campo === null || campo === "" || $.trim(campo).length === 0) {
        return msg + "<br>";
    }
    return "";
}
//FUNCION PARA VALIDAR QUE SE HAYAN SELECCIONADO DATOS DEL COMBO
function fn_validaCombos(obj, msg) {
    var val = $(obj).val();
    if (val === "0" || val === "" || val === null) {
        return msg + "<br>";
    }
    return "";
}
//FUNCION PARA CARGAR UN COMBO CON AJAX
function fn_cargarComboAjax(obj, datos) {
    $.ajax({
        type: "GET",
        url: "../CombosAjax",
        data: datos,
        success: function (data) {
            var source = {
                localdata: data,
                datatype: "json",
                datafields:
                        [
                            {name: 'descripcion', type: 'string'},
                            {name: 'codigo', type: 'string'}
                        ],
                async: true
            };
            var dataAdapter = new $.jqx.dataAdapter(source);
            $(obj).jqxDropDownList({source: dataAdapter, placeHolder: "Seleccione", displayMember: "descripcion", valueMember: "codigo"});
        }
    });
}
//FUNCION PARA CARGAR UN COMBO DE CABECERA CON AJAX
function fn_cargarComboxCabecera(obj, datos) {
    $.ajax({
        type: "GET",
        url: "../CombosAjax",
        data: datos,
        success: function (data) {
            var source = {
                localdata: data,
                datatype: "json",
                datafields:
                        [
                            {name: 'descripcion', type: 'string'},
                            {name: 'codigo', type: 'string'}
                        ],
                async: true
            };
            var dataAdapter = new $.jqx.dataAdapter(source);
            $(obj).jqxComboBox({source: dataAdapter, placeHolder: "Seleccione", displayMember: "descripcion", valueMember: "codigo"});
        }
    });
}
//FUNCION PARA CARGAR UN TEXTO CON AJAX
function fn_cargarTextoAjax(obj, mode, codigo) {
    $.ajax({
        type: "GET",
        url: "../TextoAjax",
        data: {mode: mode, codigo: codigo},
        success: function (data) {
            $(obj).val(data);
        }
    });
}
//FUNCION PARA EXTRAER DATOS DE UN TEXTO 
function fn_extraerDatos(text, simbolo) {
    return text.substring(0, text.indexOf(simbolo));
}
//FUNCTION PARA REEMPLAZAR TODO 
function fn_reemplazarTodo(text, busca, reemplaza) {
    while (text.toString().indexOf(busca) !== - 1)
        text = text.toString().replace(busca, reemplaza);
    return text;
}
//FUNCION PARA VALIDAR SOLO NUMEROS ENTEROS
function validarSiNumero(numero) {
    if (!/^([0-9])*$/.test(numero)) {
        return "El valor " + numero + " no es un número valido.";
    }
    return "";
}