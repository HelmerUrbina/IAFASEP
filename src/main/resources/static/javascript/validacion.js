/* global datos */
function scrollb() {
    window.status = "Institución Administradora de Fondos de Aseguramiento en Salud del Ejército del Perú";
}
//FUNCION PARA VALIDAR LA AUTORIZACION DEL USUSARIO
function fn_validaAutorizacion(autorizacion) {
    if (!autorizacion) {
        alert("USUARIO NO AUTORIZADO PARA ESTE TIPO DE OPERACIÓN");
        location.reload();
    }
}
//FUNCION PARA FINALIZAR LA SESSION DEL USUARIO
function fn_FinalizaSession() {
    window.location = "../login?logout";
}
//FUNCION PARA REGRESAR AL MENU PRINCIPAL
function fn_MenuPrincipal() {
    location.reload();
}
//FUNCION PARA CARGAR LAS OPCIONES DEL MENU
function fn_CargarMenu(mode, url) {
    $("#div_Titulo").remove();
    $("#div_GrillaPrincipal").remove();
    $("#div_GrillaDetalle").remove();
    $("#div_VentanaPrincipal").remove();
    $("#div_VentanaDetalle").remove();
    $("#div_VentanaCerrar").remove();
    $("#div_VentanaDetalleAprobacion").remove();
    $("#div_RegistroDetalle").remove();
    $("#div_ContextMenu").remove();    
    var $contenidoAjax = $('#div_Contenido').html('<div style="text-align: center"><img src="images/Fondos/cargando.gif" th:src="@{images/Fondos/cargando.gif}"/></div>');
    $.ajax({
        type: "GET",
        url: 'OpcionesMenu',
        data: {mode: mode, url: url},
        success: function (data) {
            $contenidoAjax.html(data);
        },
        error: function (e) {
            alert(e);
            console.log("ERROR: ", e);
        },
        done: function (e) {
            console.log("DONE");
            alert(e);
            enableSearchButton(true);
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
    if (val === "0" || val === "" || val === null || val === "undefined") {
        return msg + "<br>";
    }
    return "";
}
//FUNCION PARA CARGAR UN COMBO CON AJAX
function fn_cargarComboAjax(obj, datos) {
    var source = {
        datatype: "json",
        datafields: [
            {name: 'descripcion', type: 'string'},
            {name: 'codigo', type: 'string'}
        ],
        id: 'codigo',
        url: "CombosAjax",
        data: datos,
        async: true
    };
    var dataAdapter = new $.jqx.dataAdapter(source);
    $(obj).jqxDropDownList({source: dataAdapter, placeHolder: "Seleccione", displayMember: "descripcion", valueMember: "codigo"});
}
//FUNCION PARA CARGAR UN COMBO DE CABECERA CON AJAX
function fn_cargarComboxCabecera(obj, datos) {
    var source = {
        datatype: "json",
        datafields: [
            {name: 'descripcion', type: 'string'},
            {name: 'codigo', type: 'string'}
        ],
        id: 'codigo',
        url: "CombosAjax",
        data: datos,
        async: true
    };
    var dataAdapter = new $.jqx.dataAdapter(source);
    $(obj).jqxComboBox({source: dataAdapter, placeHolder: "Seleccione", displayMember: "descripcion", valueMember: "codigo"});
}
//FUNCION PARA CARGAR UN TEXTO CON AJAX
function fn_cargarTextoAjax(obj, datos) {
    $.ajax({
        datatype: "json",
        url: "TextoAjax",
        data: datos,
        success: function (data) {
            var dato = JSON.parse(data);
            $(obj).val(dato.descripcion);
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
//FUNCION PARA VALIDAR SOLO NUMEROS ENTEROS
function validarBoolean(valor) {
    if (valor === '1') {
        return true;
    }
    return false;
}
//FUNCION PAD
function pad(str, max) {
    str = str.toString();
    return str.length < max ? pad("0" + str, max) : str;
}
//FUNCION DEVUELVE EL VALOR EN NUMERO
function fn_extraeNumero(text, simbolo) {
    var monto = "0";
    var pos = text.indexOf(simbolo);
    monto = text.substring(pos + simbolo.length, text.length);
    monto = fn_reemplazarTodo(monto, ',', '');
    return monto = parseFloat(monto);
}
//FUNCION PARA OBTENER LA EXTENCIÓN DE UN ARCHIVO
function fn_getFileExtension(filename) {
    return (/[.]/.exec(filename)) ? /[^.]+$/.exec(filename)[0] : undefined;
}