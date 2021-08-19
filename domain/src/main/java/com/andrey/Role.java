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
@Table(name = "role")
@Getter
@Setter
@ToString
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "type")
    private String type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<User> users;

    public Role(){

    }

    public Role(Long id, String type){
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }


}
