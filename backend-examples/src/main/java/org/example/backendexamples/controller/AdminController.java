package org.example.backendexamples.controller;

import lombok.RequiredArgsConstructor;
import org.example.backendexamples.dox.User;
import org.example.backendexamples.service.UserService;
import org.example.backendexamples.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    //添加用户
    @PostMapping("users")
    public ResultVo postUser(@RequestBody User user) {
        userService.addUser(user);
        return ResultVo.ok();
    }
    //重置密码
    @PutMapping("users/{account}/password")
    public ResultVo putPassword(@PathVariable String account) {
        userService.updateUserPassword(account);
        return ResultVo.ok();
    }
    //获取全部用户信息
    @GetMapping("users")
    public ResultVo getUsers() {
        return ResultVo.success(userService.listUsers());
    }
}
