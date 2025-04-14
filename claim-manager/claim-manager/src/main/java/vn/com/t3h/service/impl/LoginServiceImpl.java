package vn.com.t3h.service.impl;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import vn.com.t3h.service.LoginService;
import vn.com.t3h.utils.Constant;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public String processAfterLoginSuccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationServiceException("Authentication required");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        System.out.println(String.format("UserDetails: %s", username));
        List<String> roleCode = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        System.out.println(String.format("RoleCode: %s", roleCode));
        boolean isAdmin = roleCode.contains(Constant.PREFIX_ROLE + Constant.ROLE_ADMIN_CODE);
        if (isAdmin) {
            return "redirect:/cms/dashboard";
        }
        return "redirect:/home";
    }
}
