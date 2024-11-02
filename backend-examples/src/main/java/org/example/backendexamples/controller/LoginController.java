package org.example.backendexamples.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backendexamples.component.JWTComponent;
import org.example.backendexamples.dox.User;
import org.example.backendexamples.exception.Code;
import org.example.backendexamples.service.UserService;
import org.example.backendexamples.vo.ResultVo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
public class LoginController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTComponent jwtComponent;
    @PostMapping("login")
    public ResultVo login(@RequestBody User user, HttpServletResponse response) {
        User userR = userService.getUser(user.getAccount());
        if (userR == null || !passwordEncoder.matches(user.getPassword(), userR.getPassword())) {
            return ResultVo.error(Code.LOGIN_ERROR);
        }
        String token = jwtComponent.encode(Map.of("uid", userR.getId(), "role", userR.getRole()));
        response.setHeader("token", token);
        response.setHeader("role", userR.getRole());
        return ResultVo.success(userR);
    }
}
