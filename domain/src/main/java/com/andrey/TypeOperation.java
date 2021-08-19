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
@Table(name = "type_operation")
@Getter
@Setter
@ToString
public class TypeOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "type")
    private String type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "typeOperation", cascade = CascadeType.ALL)
    @JsonIgnore
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
