<%-- 
    Document   : index.jsp
    Created on : 18/03/2016, 12:11:54 PM
    Author     : H-URBINA-M
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="WEB-INF/jspf/browser.jspf" %>
<%    session.removeAttribute("objUsuario" + request.getSession().getId());
    session.removeAttribute("ID");
    session.removeAttribute("autorizacion");
    session.invalidate();
%>
<!DOCTYPE html> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="Expires" content="0"/>
        <meta http-equiv="Last-Modified" content="0"/>
        <meta http-equiv="Cache-Control" content="no-cache, mustrevalidate"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="refresh" content="300; url=index.jsp">
        <title>.:: IAFAS - Institución Administradora de Fondos de Aseguramiento en Salud del Ejército del Perú ::.</title>
        <link type="text/css" rel="stylesheet" href="css/styles/jqx.base.css">
        <link type="text/css" rel="stylesheet" href="css/login.css">
        <link type="text/css" rel="stylesheet" href="css/scaf.css">
        <link type="text/css" rel="stylesheet" href="css/bundled.css">
        <link type="text/css" rel="stylesheet" href="css/jquery-confirm.css"> 
        <script type="text/javascript" src="javascript/theme.js"></script>
        <script type="text/javascript" src="javascript/bundled.js"></script>
        <script type="text/javascript" src="javascript/jquery.js"></script>
        <script type="text/javascript" src="javascript/jquery-confirm.js"></script>
        <script type="text/javascript" src="javascript/jquery.hoverplay.js"></script>
        <script type="text/javascript" src="javascript/jqwidgets/jqxcore.js"></script>
        <script type="text/javascript" src="javascript/jqwidgets/jqxinput.js"></script>
        <script type="text/javascript" src="javascript/jqwidgets/jqxpasswordinput.js"></script>
        <script type="text/javascript" src="javascript/jqwidgets/jqxtooltip.js"></script>
        <script type="text/javascript" src="javascript/jqwidgets/jqxbuttons.js"></script>
        <script type="text/javascript" src="javascript/jqwidgets/jqxvalidator.js"></script>
        <script type="text/javascript" src="javascript/jqwidgets/jqxwindow.js"></script>
        <script type="text/javascript" src="javascript/jqwidgets/jqxcombobox.js"></script>
        <script type="text/javascript" src="javascript/jqwidgets/jqxdropdownlist.js"></script>
        <script type="text/javascript" src="javascript/jqwidgets/jqxscrollbar.js"></script>
        <script type="text/javascript" src="javascript/jqwidgets/jqxlistbox.js"></script>
        <script type="text/javascript" src="javascript/jqwidgets/jqxexpander.js"></script>
        <script type="text/javascript" src="javascript/pdfobject.js" ></script>
        <script type="text/javascript">
            $(document).ready(function () {
                fn_Refrescar();
                var theme = getTheme();
                $("#div_login").jqxExpander({toggleMode: 'none', width: '320px', showArrow: false});
                $("#txt_Usuario").jqxInput({theme: theme, placeHolder: "Usuario", height: 25, width: 250});
                $("#txt_Password").jqxPasswordInput({theme: theme, placeHolder: "Contraseña", width: 250, height: 25, showStrength: true, showStrengthPosition: "right"});
                $("#txt_Verificacion").jqxInput({theme: theme, placeHolder: "Escriba estos caracteres", height: 25, width: 250, minLength: 1});
                $("#btn_Ingresar").jqxButton({theme: theme, width: 150, height: 25});
                $("#btn_Ingresar").on('click', function () {
                    $('#frm_Login').jqxValidator('validate');
                });
                $('#frm_Login').jqxValidator({
                    rules: [
                        {input: '#txt_Usuario', message: 'Ingrese el Usuario!', action: 'keyup, blur', rule: 'required'},
                        {input: '#txt_Usuario', message: 'Ingrese Solo Numeros!', action: 'keyup, blur', rule: 'number'},
                        {input: '#txt_Password', message: 'Ingrese la Contraseña!', action: 'keyup, blur', rule: 'required'},
                        {input: '#txt_Verificacion', message: 'Ingrese los Caracteres de Verificacion!', action: 'keyup, blur', rule: 'required'}]
                });
                $('#frm_Login').jqxValidator({
                    onSuccess: function () {
                        fn_login();
                    }
                });
            });
            function fn_login() {
                var usuario = $("#txt_Usuario").val();
                var password = $("#txt_Password").val();
                var verificacion = $("#txt_Verificacion").val();
                $.ajax({
                    type: "POST",
                    url: "VerificaUsuario",
                    data: {accion: "LOGIN", usuario: usuario, password: password, verificacion: verificacion},
                    success: function (data) {
                        if (data === "VerificaSession") {
                            window.location = data;
                            fn_Refrescar();
                        } else {
                            $.alert({
                                theme: 'material',
                                title: 'Mensaje!',
                                content: data,
                                animation: 'zoom',
                                closeAnimation: 'zoom',
                                type: 'orange',
                                typeAnimated: true
                            });
                            fn_Refrescar();
                            $("#txt_Verificacion").val('');
                        }
                    }
                });
            }
            function fn_Refrescar() {
                var d = new Date();
                $.ajax({
                    type: "POST",
                    url: "captcha.jsp?" + d.getTime(),
                    data: {},
                    success: function (data) {
                        $("#img_stickyImg").html(data);
                    }
                });
            }
        </script>
    </head>
    <body oncontextmenu='return false' style="background: url(Imagenes/Logos/fondo.jpg); background-repeat: repeat;">
        <div style="padding: 50px">
            <table style=" margin: 0 auto;" >
                <tr>
                    <td>
                        <div id="div_login">
                            <div > <div style="text-align: center">INGRESO AL SISTEMA</div></div>
                            <div > 
                                <form method="post" id="frm_Login">
                                    <table style="margin-top: 10px; margin-bottom: 10px; margin: 0 auto;">
                                        <tr>
                                            <td style="text-align: center;">
                                                <img src="Imagenes/Logos/logo.png" alt="Icon-Login" style="border-radius: 25%;" width="200px" height="200px"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="padding: 5px;"><input type="text" name="txt_Usuario" id="txt_Usuario" autocomplete="off" /></td>
                                        </tr>
                                        <tr>
                                            <td style="padding: 5px;"><input type="password" name="txt_Password" id="txt_Password" autocomplete="off"/></td>
                                        </tr>
                                        <tr>
                                            <td style="padding: 5px;"><div id="img_stickyImg"></div>
                                                <div class="link" style="text-align: right"> <a href="javascript:fn_Refrescar();">Refrescar</a></div></td>
                                        </tr>
                                        <tr>
                                            <td style="padding: 5px;"><input type="text" name="txt_Verificacion" id="txt_Verificacion" autocomplete="off"/></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="text-align: center;padding: 5px; padding-bottom: 20px"><input name="btn_Ingresar" id="btn_Ingresar" type="button" value="Ingresar" /></td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                        </div>
                        <div class="footer">
                            <span style="font-weight: bold;">IAFAS-EP</span><br/>
                            &copy; Todos los derechos reservados
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>