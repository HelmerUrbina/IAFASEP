package com.iafasep.UserServices.Login;

import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import com.iafasep.BusinessServices.Beans.BeanUsuario;
import com.iafasep.DataService.Service.CombosService;
import com.iafasep.DataService.Service.UsuarioMenuService;
import com.iafasep.DataService.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *
 * @author MERCANTIL GROUP SAC
 */
@Controller
@Slf4j
public class VerificaUsuario {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMenuService usuarioMenuService;

    @Autowired
    private CombosService combosService;

    @RequestMapping(value = "/")
    public String verificaUsuario(Model model, @AuthenticationPrincipal User user, HttpSession session) {
        BeanUsuario usuario = usuarioService.findByUsernameAndEstado(user.getUsername(), "AC");
        if (usuario != null) {
            if (session.getAttribute("usuario") == null) {
                usuario.setPassword(null);
                session.setAttribute("usuario", usuario);
            }
            session.setAttribute("objMeses", combosService.getMeses());
            session.setAttribute("objModulos", usuarioMenuService.getModuloUsuario(user.getUsername()));
            session.setAttribute("objMenus", usuarioMenuService.getMenuUsuario(user.getUsername()));
            session.setAttribute("objAutorizacion", usuario.getAutorizacion());
            session.setAttribute("objPeriodos", combosService.getPeriodos());
            session.setAttribute("objFuenteFinanciamiento", combosService.getFuenteFinanciamiento());
            log.info("usuario que hizo login : " + user);
            return "Login/Principal";
        } else { 
            model.addAttribute("mensaje", "Password incorrecto");
            return "/login?logout";
        }
    }

    @RequestMapping("/FinalizaSesion")
    public String principal(Model model) {
        return "/login?logout";
    }

    @RequestMapping(value = "/OpcionesMenu")
    public String getMapping(String mode, String url) {
        if (url == null || url.isBlank()) {
            return "Login/Mantenimiento";
        } else {
            return mode + "/" + url;
        }
    }

}
