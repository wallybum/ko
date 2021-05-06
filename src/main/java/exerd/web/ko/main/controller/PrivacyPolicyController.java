package exerd.web.ko.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrivacyPolicyController {

    @GetMapping(value="/privacyPolicy")
    public String main(){
        return "privacyPolicy.html";
    }
}
