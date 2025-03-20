package vn.com.t3h.controller.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class DashBoardController {
    @GetMapping("/cms/dashboard")
    public String dashboard() {
        return "cms/index";
    }
}
