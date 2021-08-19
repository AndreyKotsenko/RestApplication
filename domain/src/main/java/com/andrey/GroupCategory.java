package com.andrey;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;


/**
 * Simple JavaBean domain object.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@Entity
@Table(name = "group_category")
@Getter
@Setter
@ToString
public class GroupCategory {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "title")
    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groupCategory", cascade = CascadeType.ALL)
    @JsonIgnore
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
