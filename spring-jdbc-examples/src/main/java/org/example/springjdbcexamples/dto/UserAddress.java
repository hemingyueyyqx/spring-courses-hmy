package org.example.springjdbcexamples.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springjdbcexamples.dox.Address;
import org.example.springjdbcexamples.dox.User;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddress {
    private User user;
    private List<Address> addresses;
}
