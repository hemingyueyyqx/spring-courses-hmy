package org.example.springmvcexampleshmy.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvcexampleshmy.component.JWTComponent;
import org.example.springmvcexampleshmy.component.PasswordEncoderConfig;
import org.example.springmvcexampleshmy.service.UserService;
import org.example.springmvcexampleshmy.dox.User;
import org.example.springmvcexampleshmy.exception.Code;
import org.example.springmvcexampleshmy.vo.ResultVo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
        User userR = userService.getUserByAccount(user.getAccount());
        if (userR == null || !passwordEncoder.matches(user.getPassword(), userR.getPassword())) {
            return ResultVo.error(Code.LOGIN_ERROR);
        }
        String token = jwtComponent.encode(Map.of("uid", userR.getId(), "role", userR.getRole()));
        response.setHeader("token", token);
        response.setHeader("role", userR.getRole());
        return ResultVo.success(userR);
    }
}
