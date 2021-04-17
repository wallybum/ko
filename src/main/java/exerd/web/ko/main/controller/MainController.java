package exerd.web.ko.main.controller;

import exerd.web.ko.main.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainController {

    @Autowired
    CustomerService customerService;

    @GetMapping(value="/")
    public String main(Model model) {
        model.addAttribute("customerList",customerService.getCustomerList());
        return "index.html";
    }
}
