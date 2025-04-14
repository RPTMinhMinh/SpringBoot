package vn.com.t3h.service.impl;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import vn.com.t3h.entity.UserEntity;
import vn.com.t3h.mapper.UserMapper;
import vn.com.t3h.repository.UserRepository;
import vn.com.t3h.service.LoginService;
import vn.com.t3h.service.dto.UserDTO;
import vn.com.t3h.utils.Constant;

import java.util.List;
import vn.com.t3h.utils.ImageUtils;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageUtils imageUtils;

    public LoginServiceImpl(UserRepository userRepository, UserMapper userMapper,
        ImageUtils imageUtils) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.imageUtils = imageUtils;
    }

    @Override
    public String processAfterLoginSuccess(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationServiceException("Authentication required");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        UserEntity userEntity = userRepository.findByUsername(username);
        UserDTO userDTO = userMapper.toDto(userEntity, imageUtils);
        session.setAttribute("username", userDTO.getUsername());
        System.out.println("Session username is " + username);
        session.setAttribute("image", userDTO.getStringBase64Avatar());

        List<String> roleCode = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        System.out.println(String.format("RoleCode: %s", roleCode));
        boolean isAdmin = roleCode.contains(Constant.PREFIX_ROLE + Constant.ROLE_ADMIN_CODE);
        if (isAdmin) {
            return "redirect:/cms/dashboard";
        }
        return "redirect:/home";
    }
}
