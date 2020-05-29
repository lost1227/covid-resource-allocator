package edu.calpoly.csc_308.cora;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AngularRouting {
    
    @RequestMapping({
      "/volunteer",
      "/supplies",
      "/message",
      "/login",
      "/login/register",
      "/post/volunteer",
      "/post/supply",
      "/supplies/details/*"
    })
    public String index() {
        return "forward:/index.html";
    }


}
