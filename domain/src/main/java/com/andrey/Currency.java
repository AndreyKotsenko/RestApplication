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
public class Currency {

    private Long id;

    private String type;

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
