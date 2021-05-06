package exerd.web.ko.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServiceTermController {

    @GetMapping(value="/serviceTerm")
    public String main(){
        return "serviceTerm.html";
    }
}
