package com.andrey;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;


/**
 * Simple JavaBean domain object.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@Getter
@Setter
@ToString
public class Category {

    private Long id;

    private String title;

    private Long group_id;

    private List<Operation> operations;

    public Category(){

    }

    public Category(Long id, String title){
        this.id = id;
        this.title = title;

    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
