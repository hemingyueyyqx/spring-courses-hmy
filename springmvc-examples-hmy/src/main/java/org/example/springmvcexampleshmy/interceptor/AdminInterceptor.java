package org.example.springmvcexampleshmy.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.springmvcexampleshmy.dox.User;
import org.example.springmvcexampleshmy.exception.Code;
import org.example.springmvcexampleshmy.exception.XException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
@RequiredArgsConstructor
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(User.ADMIN.equals(request.getAttribute("role"))) {
            return true;
        }
        throw XException.builder().code(Code.FORBIDDEN).build();
    }
}
