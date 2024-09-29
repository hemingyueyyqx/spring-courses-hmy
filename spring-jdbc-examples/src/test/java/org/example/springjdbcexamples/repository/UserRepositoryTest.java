package org.example.springjdbcexamples.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.springjdbcexamples.dox.User;
import org.example.springjdbcexamples.dto.UserAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j

class UserRepositoryTest {
  @Autowired
    private UserRepository userRepository;

  @Test
  public void saveUser() {
    User user = User.builder().name("Zhang").build();
    userRepository.save(user);
  }
  @Test
  void findById() {
    userRepository.findById("1283955919788601344")
            .ifPresent(user -> log.debug("{}", user));
  }
  @Test
  void findUserAddressByUidTest() {
    UserAddress userAddress = userRepository.findUserAddress("1283955919788601344");
    log.debug("{}", userAddress.getUser());
    userAddress.getAddresses().forEach(a -> log.debug("{}", a));
  }


}