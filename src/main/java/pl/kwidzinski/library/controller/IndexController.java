package pl.kwidzinski.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // domyślnie reaquest mapping "/"
@RequestMapping(path = "/")
public class IndexController {

    @GetMapping("/") // domyślnie reaquest mapping "/"
    public String index(){
        return "index";
    }

}
