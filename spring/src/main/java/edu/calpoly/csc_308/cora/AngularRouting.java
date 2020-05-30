package edu.calpoly.csc_308.cora;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AngularRouting {
    
    @RequestMapping(value = {
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
    }, method = RequestMethod.GET)
    public String index() {
        return "forward:/index.html";
    }


}
