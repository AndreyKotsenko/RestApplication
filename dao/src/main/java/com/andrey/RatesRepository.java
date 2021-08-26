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
public interface RatesRepository extends BaseRepository<Rates>{

    @Select("SELECT * FROM rates WHERE id = #{id}")
    Rates getById(Long id);

    @Insert("INSERT INTO rates(id_currency_from, id_currency_to, rate_value) " +
            " VALUES (#{id_currency_from}, #{id_currency_from}, #{id_currency_to}, #{rate_value})")
    void add(Rates rates);

    @Update("Update rates set rate_value=#{rate_value} WHERE id=#{id}")
    void update(Rates rates);

    @Delete("DELETE FROM rates WHERE id = #{id}")
    void deleteById(Long id);

    @Select("select * from rates")
    List<Rates> findAll();

    @Select("select rate_value from rates where (id_currency_from = #{id_currency_from} AND id_currency_to = #{id_currency_to} )")
    double getByCurrencies(Long id_currency_from, Long id_currency_to);


}
