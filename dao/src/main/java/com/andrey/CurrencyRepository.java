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
public interface CurrencyRepository extends BaseRepository<Currency> {

    @Select("SELECT * FROM currency WHERE id = #{id}")
    Currency getById(Long id);

    @Insert("INSERT INTO currency(type) " +
            " VALUES (#{type})")
    void add(Currency currency);

    @Update("Update currency set type=#{type} WHERE id=#{id}")
    void update(Currency currency);

    @Delete("DELETE FROM currency WHERE id = #{id}")
    void deleteById(Long id);

    @Select("select * from currency")
    List<Currency> findAll();
}
