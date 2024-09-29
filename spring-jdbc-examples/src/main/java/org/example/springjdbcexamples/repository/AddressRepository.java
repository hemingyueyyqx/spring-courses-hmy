package org.example.springjdbcexamples.repository;

import org.example.springjdbcexamples.dox.Address;
import org.example.springjdbcexamples.dto.AddressUser;
import org.example.springjdbcexamples.mapper.AddressUserRowMapper;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {
    @Query("select * from address a where a.user_id=:userId")
    List<Address> findByUserId(String userId);
    @Modifying
    @Query("update address a set a.detail=:detail where a.id=:aid")
    void updateDetail(String detail, String aid);
    @Modifying
    @Query("delete from address where id=:aid")
    void deleteAddressById(String aid);
    @Query(value = "select * from address a join user u on u.id = a.user_id where a.id=:aid",
            rowMapperClass = AddressUserRowMapper.class)
    AddressUser findAddressUserById(String aid);


}
