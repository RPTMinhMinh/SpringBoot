package vn.com.t3h.controller.guest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping(value = {"/", "/home"})
    public String homePage() {
        return "guest/home-page";
    }
}
