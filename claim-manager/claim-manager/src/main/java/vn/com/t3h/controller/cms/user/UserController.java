package vn.com.t3h.controller.cms.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cms")
public class UserController {
    @GetMapping("/user-manager")
    public String userManager(){
        return "cms/user/user-manager";
    }

    @GetMapping("/create-user")
    public String getPageClaimManager(){
        return "cms/user/create-user";
    }

    @GetMapping("/detail-user")
    public String getDetailUser(@RequestParam Long id) {
        return "cms/user/update-user";
    }

}
