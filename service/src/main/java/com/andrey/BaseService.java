package com.andrey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public abstract class BaseService<T> {

    @Autowired
    BaseRepository<T> repository;

    @Autowired
    HttpServletRequest requestMethod;


    public T getById(Long id) {
        return repository.getById(id);
    }


    public void save(T object) {
        if ("POST".equalsIgnoreCase(requestMethod.getMethod())) {
            repository.add(object);
        } else {
            repository.update(object);
        }
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }


    public List<T> getAll() {
        return repository.findAll();
    }

}
