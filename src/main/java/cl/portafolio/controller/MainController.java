package cl.portafolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.security.Principal;

@Controller
public class MainController {

    @GetMapping({"/","/index"})
    public String index(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
