package vn.com.t3h.service;

import jakarta.servlet.http.HttpSession;

public interface LoginService {
    public String processAfterLoginSuccess(HttpSession session);
}
