package exerd.web.ko.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BuyController {

    @Autowired
    // BuyService buyService;

    @GetMapping(value="/buy")
    public String main(){
        return "buy.html";
    }
}
