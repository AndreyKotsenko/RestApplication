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
public class TypeOperation {

    private Long id;

    private String type;

    private Collection<Operation> operations;

    public TypeOperation(){

    }

    public TypeOperation(Long id){
        this.id = id;
    }

    public TypeOperation(Long id, String type){
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return "TypeOperation{" +
                "id=" + id +
                '}';
    }

}
