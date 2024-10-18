package org.example.springmvcexampleshmy.service;

import org.example.springmvcexampleshmy.dox.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    private static final List<User> USERS = create();
    private static List<User> create() {
        User u = User.builder()
                .id("1")
                .name("HE")
                .account("1001")
                .password("123456")
                .build();
        return List.of(u);
    }

    public List<User> listUsers() {
      return USERS;
    }
    public User getUserByAccount(String account, String password) {
        return USERS.stream()
                .filter(u -> u.getAccount().equals(account))
                .filter(u -> u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
    public User getUserByAccount(String account) {
        return USERS.stream()
                .filter(u -> u.getAccount().equals(account))
                .findFirst()
                .orElse(null);
    }

}
