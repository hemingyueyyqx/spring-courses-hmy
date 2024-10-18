package org.example.springmvcexampleshmy.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.springmvcexampleshmy.service.UserService;
import org.example.springmvcexampleshmy.vo.ResultVo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/admin/")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public ResultVo getUsers() {
        return ResultVo.success(userService.listUsers());
    }

    @GetMapping("users/{account}")
    public ResultVo getUser(@PathVariable String account) {
        return ResultVo.success(userService.getUserByAccount(account));
    }
}
