package com.andrey;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;


/**
 * Simple JavaBean domain object.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@Entity
@Table(name = "category")
@Getter
@Setter
@ToString
public class Category {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "group_id", referencedColumnName = "id")
    @JsonBackReference( value = "category_group_category")
    private GroupCategory groupCategory;


    @OneToMany( fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    @JsonIgnore
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
