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
public interface AccountRepository extends BaseRepository<Account>{

    @Select("SELECT * FROM account WHERE id = #{id}")
    Account getById(Long id);

    @Insert("INSERT INTO account(account_name, balance, user_id, currency_id) " +
            " VALUES (#{account_name}, #{balance}, #{user_id}, #{currency_id})")
    void add(Account account);

    @Update("Update account set account_name=#{account_name}, " +
            " balance=#{balance} WHERE id=#{id}")
    void update(Account account);

    @Delete("DELETE FROM account WHERE id = #{id}")
    void deleteById(Long id);

    @Select("select * from account")
    List<Account> findAll();

    @Select("select * from account INNER JOIN users c ON (c.id = user_id) WHERE mobile_number = #{mobileNumber}")
    List<Account> findAllByMobileNumber(String mobileNumber);


}
