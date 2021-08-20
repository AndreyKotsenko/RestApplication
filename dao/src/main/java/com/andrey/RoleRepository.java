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
public interface RoleRepository extends BaseRepository<Role>{

    @Select("SELECT * FROM role WHERE id = #{id}")
    Role getById(Long id);

    @Insert("INSERT INTO role(type) " +
            " VALUES (#{type})")
    void add(Role role);

    @Update("Update role set type=#{type} WHERE id=#{id}")
    void update(Role role);

    @Delete("DELETE FROM role WHERE id = #{id}")
    void deleteById(Long id);

    @Select("select * from role")
    List<Role> findAll();
}
