package exerd.web.ko.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReleaseNoteController {

    @GetMapping(value="/releaseNote")
    public String main(){
        return "releaseNote.html";
    }
}
