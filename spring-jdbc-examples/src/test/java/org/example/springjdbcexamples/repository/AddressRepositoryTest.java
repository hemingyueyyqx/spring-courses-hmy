package org.example.springjdbcexamples.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.springjdbcexamples.dox.Address;
import org.example.springjdbcexamples.dto.AddressUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class AddressRepositoryTest {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUserId() {
        for(Address a : addressRepository.findByUserId("1283955919788601344")) {
            log.debug("address : {}", a);
        }
    }
    @Test
    void updateDetail() {
        Address address = Address.builder().id("4").detail("888").build();
        addressRepository.updateDetail(address.getDetail(), address.getId());
    }
    @Test
    void deleteAddressById() {
        addressRepository.deleteAddressById("3");
    }
    @Test
    void findAddressUserTest() {
        AddressUser addressUser = addressRepository.findAddressUserById("2");
        log.debug("{}",addressUser.getUser());
        log.debug("{}", addressUser.getAddress());
    }

}