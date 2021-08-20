package com.andrey;

import com.andrey.filter.Filter;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * Access to CRUD methods.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@Mapper
public interface OperationRepository extends BaseRepository<Operation>{

    @Select("SELECT * FROM operation WHERE id = #{id}")
    Operation getById(Long id);

    @Insert("INSERT INTO operation(date_operation, description, total_sum, type_operation_id, category_id, account_id_from, account_id_to) " +
            " VALUES (#{date_operation}, #{description}, #{total_sum}, #{type_operation_id}, #{category_id}, #{account_id_from}, #{account_id_to})")
    void add(Operation operation);

    @Update("Update operation set date_operation=#{date_operation}, " +
            " description=#{description} WHERE id=#{id}")
    void update(Operation operation);

    @Delete("DELETE FROM operation WHERE id = #{id}")
    void deleteById(Long id);

    @Select("select * from operation")
    List<Operation> findAll();

    @SelectProvider(type = FilterSqlBuilder.class, method = "buildGetOperationsByFilter")
    List<Operation> findAllByFilter(@Param(value = "filter") Filter filter);


}
