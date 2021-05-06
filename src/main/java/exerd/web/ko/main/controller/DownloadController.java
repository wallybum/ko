package exerd.web.ko.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DownloadController {

    @Autowired
//    DownloadService downloadService;

    @GetMapping(value="/down")
    public String main(){
        return "download.html";
    }
}
