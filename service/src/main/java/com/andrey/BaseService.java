package com.andrey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class BaseService<T> {

    @Autowired
    JpaRepository<T, Long> repository;


    public T getById(Long id) {
        return repository.getById(id);
    }


    public void save(T object) {
        repository.save(object);
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }


    public List<T> getAll() {
        return repository.findAll();
    }

}
