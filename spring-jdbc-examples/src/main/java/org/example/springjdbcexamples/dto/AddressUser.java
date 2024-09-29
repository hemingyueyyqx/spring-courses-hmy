package org.example.springjdbcexamples.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springjdbcexamples.dox.Address;
import org.example.springjdbcexamples.dox.User;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressUser {
    private User user;
    private Address address;
}
