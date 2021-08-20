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
public interface TypeOperationRepository extends BaseRepository<TypeOperation>{

    @Select("SELECT * FROM type_operation WHERE id = #{id}")
    TypeOperation getById(Long id);

    @Insert("INSERT INTO type_operation(type) " +
            " VALUES (#{type})")
    void add(TypeOperation typeOperation);

    @Update("Update type_operation set type=#{type} WHERE id=#{id}")
    void update(TypeOperation typeOperation);

    @Delete("DELETE FROM type_operation WHERE id = #{id}")
    void deleteById(Long id);

    @Select("select * from type_operation")
    List<TypeOperation> findAll();
}
