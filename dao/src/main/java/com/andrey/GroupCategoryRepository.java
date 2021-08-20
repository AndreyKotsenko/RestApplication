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
public interface GroupCategoryRepository extends BaseRepository<GroupCategory>{

    @Select("SELECT * FROM group_category WHERE id = #{id}")
    GroupCategory getById(Long id);

    @Insert("INSERT INTO group_category(title) " +
            " VALUES (#{title})")
    void add(GroupCategory groupCategory);

    @Update("Update group_category set title=#{title} WHERE id=#{id}")
    void update(GroupCategory groupCategory);

    @Delete("DELETE FROM group_category WHERE id = #{id}")
    void deleteById(Long id);

    @Select("select * from group_category")
    List<GroupCategory> findAll();
}
