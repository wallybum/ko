package exerd.web.ko.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LicenseController {

    @GetMapping(value="/license")
    public String main(){
        return "license.html";
    }
}
