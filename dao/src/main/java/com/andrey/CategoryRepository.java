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
public interface CategoryRepository extends BaseRepository<Category>{


    @Select("SELECT * FROM category WHERE id = #{id}")
    Category getById(Long id);

    @Insert("INSERT INTO category(title, group_id) " +
            " VALUES (#{title}, #{group_id})")
    void add(Category category);

    @Update("Update category set title=#{title}, " +
            " group_id=#{group_id} WHERE id=#{id}")
    void update(Category category);

    @Delete("DELETE FROM category WHERE id = #{id}")
    void deleteById(Long id);

    @Select("select * from category")
    List<Category> findAll();
}
