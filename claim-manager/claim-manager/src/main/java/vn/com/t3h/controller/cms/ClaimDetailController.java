package vn.com.t3h.controller.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ClaimDetailController {
    @GetMapping("/cms/claim-detail")
    public String claimDetail() {
        return "cms/detail-claim";
    }
}
