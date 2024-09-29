package org.example.springjdbcexamples.mapper;


import lombok.extern.slf4j.Slf4j;
import org.example.springjdbcexamples.dox.Address;
import org.example.springjdbcexamples.dox.User;
import org.example.springjdbcexamples.dto.AddressUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Slf4j
public class AddressUserRowMapper implements RowMapper<AddressUser> {

    @Override
    public AddressUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = User.builder()
                .id(rs.getString("u.id"))
                .name(rs.getString("name"))
                .createTime(rs.getObject("u.create_time", LocalDateTime.class))
                .updateTime(rs.getObject("u.update_time", LocalDateTime.class))
                .build();
        Address address = Address.builder()
                .id(rs.getString("a.id"))
                .detail(rs.getString("detail"))
                .userId(rs.getString("user_id"))
                .createTime(rs.getObject("a.create_time", LocalDateTime.class))
                .updateTime(rs.getObject("a.update_time", LocalDateTime.class))
                .build();
        return AddressUser.builder()
                .user(user)
                .address(address)
                .build();
    }
}