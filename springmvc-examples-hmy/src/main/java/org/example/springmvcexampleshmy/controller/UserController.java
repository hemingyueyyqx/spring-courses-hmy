package org.example.springmvcexampleshmy.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.springmvcexampleshmy.vo.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/user/")
public class UserController {

    @GetMapping("info")
    public ResultVo getInfo(@RequestAttribute("uid") String uid) {
        log.debug(uid);
        return ResultVo.success(Map.of("uid", uid));
    }
}
