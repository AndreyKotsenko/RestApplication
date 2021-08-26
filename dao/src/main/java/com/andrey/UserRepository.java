package com.andrey;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Access to CRUD methods.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@Mapper
public interface UserRepository extends BaseRepository<User>{

    @Select("SELECT * FROM users WHERE id = #{id}")
    User getById(Long id);

    @Insert("INSERT INTO users(first_name, last_name, mobile_number, password, role_id) " +
            " VALUES (#{first_name}, #{last_name}, #{mobile_number}, #{password}, #{role_id})")
    void add(User user);

    @Update("Update users set first_name=#{first_name}, " +
            " last_name=#{last_name}, password = #{password} WHERE id=#{id}")
    void update(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteById(Long id);

    @Select("select * from users")
    List<User> findAll();

    @Select("SELECT * FROM users WHERE mobile_number = #{mobileNumber}")
    User findByMobileNumber(String mobileNumber);
}
