package org.example.backendexamples.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backendexamples.dox.User;
import org.example.backendexamples.exception.Code;
import org.example.backendexamples.exception.XException;
import org.example.backendexamples.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private  final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //通过账号查找用户
    public User getUser(String account) {
        return userRepository.findByAccount(account);
    }
    //管理员重置密码
    @Transactional
    public void updateUserPassword(String account) {
        User user = userRepository.findByAccount(account);
        if(user == null) {
           throw  XException.builder().number(Code.ERROR).message("用户不存在").build();
        }
        user.setPassword(passwordEncoder.encode(account));
        //保存
        userRepository.save(user);
    }
    //指定用户改密码
    @Transactional
    public void updateUserPassword(String uid, String password) {
//        log.debug(uid);
//        log.debug(password);
        User user = userRepository.findById(uid).orElse(null);
        if(user == null) {
            throw  XException.builder().number(Code.ERROR).message("用户不存在").build();
        }
        user.setPassword(passwordEncoder.encode(password));
        //保存
        userRepository.save(user);
    }
    //管理员添加用户
    @Transactional
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getAccount()));
        userRepository.save(user);
    }
    //列出全部用户
    public List<User> listUsers() {
        return userRepository.findAll();
    }


}
