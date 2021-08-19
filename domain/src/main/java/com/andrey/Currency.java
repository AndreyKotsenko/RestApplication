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
@Table(name = "currency")
@Getter
@Setter
@ToString
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "type")
    private String type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "currency", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<Account> accounts;

    public Currency(){

    }

    public Currency(Long id, String type){
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
