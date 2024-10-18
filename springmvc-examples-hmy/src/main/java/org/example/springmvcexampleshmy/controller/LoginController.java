package org.example.springmvcexampleshmy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvcexampleshmy.service.UserService;
import org.example.springmvcexampleshmy.dox.User;
import org.example.springmvcexampleshmy.exception.Code;
import org.example.springmvcexampleshmy.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
public class LoginController {

    private final UserService userService;
    @PostMapping("login")
    public ResultVo login(@RequestBody User user) {
        User userR = userService.getUserByAccount(user.getAccount(), user.getPassword());
        if (userR == null) {
            return ResultVo.error(Code.LOGIN_ERROR);
        }
        return ResultVo.success(userR);
    }
}
