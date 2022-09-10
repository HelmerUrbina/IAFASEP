package com.iafasep.UserServices.Seguridad;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import com.iafasep.BusinessServices.Beans.BeanUsuario;
import com.iafasep.DataService.Service.UsuarioMenuService;
import com.iafasep.DataService.Service.UsuarioService;
import com.iafasep.Utiles.Utiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Controller
@Slf4j
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMenuService usuarioMenuService;

    @RequestMapping(value = "/Usuarios")
    @ResponseBody
    public String getRequestMapping(String mode, String codigo) {
        return switch (mode) {
            case "G" ->
                new Gson().toJson(usuarioService.findAll());
            case "U" ->
                new Gson().toJson(usuarioService.findByUsername(codigo));
            case "M" ->
                new Gson().toJson(usuarioMenuService.getOpcionesUsuario());
            case "MU" ->
                new Gson().toJson(usuarioMenuService.getOpcionesOfUsuario(codigo));
            default ->
                "ERROR";
        };
    }

    @RequestMapping(value = "/IduUsuarios")
    @ResponseBody
    public String setRequestMapping(
            @RequestParam("mode") String mode,
            @RequestParam("codigo") String codigo,
            @RequestParam("password") String password,
            @RequestParam("areaLaboral") String areaLaboral,
            @RequestParam("rol") String rol,
            @RequestParam("paterno") String paterno,
            @RequestParam("materno") String materno,
            @RequestParam("nombres") String nombres,
            @RequestParam("correo") String correo,
            @RequestParam("telefono") String telefono,
            @RequestParam("cargo") String cargo,
            @RequestParam("autorizacion") Integer autorizacion,
            @RequestParam("lista") String lista
    ) {
        BeanUsuario objBeanUsuario = new BeanUsuario();
        objBeanUsuario.setUsuario(codigo);
        objBeanUsuario.setPassword(password);
        objBeanUsuario.setAreaLaboral(areaLaboral);
        objBeanUsuario.setRol(rol);
        objBeanUsuario.setPaterno(paterno);
        objBeanUsuario.setMaterno(materno);
        objBeanUsuario.setNombres(nombres);
        objBeanUsuario.setCorreo(correo);
        objBeanUsuario.setTelefono(telefono);
        objBeanUsuario.setCargo(cargo);
        objBeanUsuario.setAutorizacion(autorizacion);
        return "" + usuarioService.guardarUsuario(objBeanUsuario, lista, Utiles.getUsuario(), mode);
    }
}
