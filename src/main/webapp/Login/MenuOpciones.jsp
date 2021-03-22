<%-- 
    Document   : MenuOpciones
    Created on : 05/03/2015, 09:10:27 AM
    Author     : H-URBINA-M
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(document).ready(function () {
        //var theme = getTheme();
        $("#div_MenuOpciones").jqxMenu({width: '100%', height: 30, showTopLevelArrows: true, keyboardNavigation: true});
        $("#div_MenuOpciones").css('visibility', 'visible');
        $("#div_MenuOpciones").jqxMenu('focus');
        //fn_mensaje();
        $("#div_Aviso").jqxNotification({width: "700", position: "top-right", opacity: 0.9, autoOpen: false, animationOpenDelay: 800, autoClose: true, autoCloseDelay: 10000, template: "info"});
        $("#div_Aviso").jqxNotification({width: "700", position: "top-right", opacity: 0.9, autoOpen: false, animationOpenDelay: 800, autoClose: true, autoCloseDelay: 10000, template: "info"});
        $("#div_Notificacion").jqxNotification({width: "auto", position: "top-right", opacity: 0.9, autoOpen: false, animationOpenDelay: 800, autoClose: false, autoCloseDelay: 3000, template: "error"});
        $('#div_Notificacion').click(function () {
            $('#div_Splitter').jqxSplitter('collapse');
            var $contenidoAjax = $('#div_Contenido').html('<img src="../Imagenes/Fondos/cargando.gif">');
            $.ajax({
                type: "POST",
                url: "../Decretos",
                data: {mode: 'documentoDecretado'},
                success: function (data) {
                    $contenidoAjax.html(data);
                }
            });
        });
    });
    function fn_mensaje() {
        var interval = setInterval(function () {
            $.ajax({
                type: "POST",
                url: "../Decretos",
                data: {mode: 'C'},
                success: function (data) {
                    if (parseInt(data) > 0) {
                        $("#div_Notificacion").jqxNotification("closeLast");
                        $('#div_Notificacion').html("USTED TIENE " + parseInt(data) + " DOCUMENTOS(S) PENDIENTE(S) DE RESPUESTA");
                        $("#div_Notificacion").jqxNotification("open");
                    }
                }
            });
        }, 8000);
    }
</script>
<div id='div_MenuOpciones' style='visibility: hidden; margin-left: 5px;'>
    <ul>
        <li><a href="#Home">Home</a></li>
            <c:forEach var="m" items="${objModulo}">
            <li>${m.descripcion}
                <ul style='width: 200px;'>
                    <d:forEach var="o" items="${objMenu}">
                        <c:if test="${m.modulo==o.modulo}">
                            <li ><a href="javascript: fn_CargarMenu('${o.servlet}','${o.modo}')">${o.descripcion}</a></li>
                            </c:if>
                        </d:forEach>
                </ul>
            </li>
        </c:forEach>
        <li id="2"><span item-title="true" style="font-weight: bold; color: red">SALIR</span></li>
    </ul>
</div>
<div id="div_Aviso"></div>
<div id="div_Notificacion"></div>