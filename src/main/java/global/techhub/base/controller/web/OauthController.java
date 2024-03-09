package global.techhub.base.controller.web;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("oauth")
@SessionAttributes("authorizationRequest")
public class OauthController {

    @GetMapping("confirm_access")
    public String redirectOauthAccept(@ModelAttribute("authorizationRequest") AuthorizationRequest clientAuth) {
        return "oauth-accept";
    }
}
