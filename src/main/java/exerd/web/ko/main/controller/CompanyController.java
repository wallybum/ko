package exerd.web.ko.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyController {

    @GetMapping(value="/company")
    public String main(){
        return "company.html";
    }
}
