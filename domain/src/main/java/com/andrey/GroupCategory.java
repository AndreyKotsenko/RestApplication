package com.andrey;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Collection;


/**
 * Simple JavaBean domain object.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */


@Getter
@Setter
@ToString
public class GroupCategory {

    private Long id;

    private String title;

    private Collection<Category> categories;

    public GroupCategory(){

    }

    public GroupCategory(Long id, String title){
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "GroupCategory{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
