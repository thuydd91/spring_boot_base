package global.techhub.base.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectHome() {
        return "index";
    }

    @GetMapping("login")
    public String redirectLogin() {
        return "login";
    }

    @GetMapping("user")
    public String redirectUser() {
        return "user/index";
    }
}
