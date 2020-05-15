package edu.calpoly.csc_308.cora;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AngularRouting {
    
    @RequestMapping({ "/volunteer", "/message", "/message/new"})
    public String index() {
        return "forward:/index.html";
    }


}
