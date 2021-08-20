package com.andrey;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Mapper
public interface BaseRepository<T> {

    T getById(Long id);

    void add(T object);

    void update(T object);

    void deleteById(Long id);

    List<T> findAll();
}
