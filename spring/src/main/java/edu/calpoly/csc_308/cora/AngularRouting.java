package edu.calpoly.csc_308.cora;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AngularRouting {
    
    @RequestMapping({
      "/volunteer",
      "/supplies",
      "/supplies/details/*",
      "/volunteer/details/*",
      "/message",
      "/login",
      "/login/register",
      "/profile/*",
      "/profile/edit",
      "/profile/logout",
      "/post/volunteer",
      "/post/supply"
    })
    public String index() {
        return "forward:/index.html";
    }


}
