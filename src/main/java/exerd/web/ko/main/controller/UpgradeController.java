package exerd.web.ko.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UpgradeController {

    @GetMapping(value="/upgrade")
    public String main(){
        return "upgrade.html";
    }
}
