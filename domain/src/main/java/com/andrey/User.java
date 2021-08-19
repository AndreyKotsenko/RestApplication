package com.andrey;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "first_name")
    private String first_name;

    @Column( name = "last_name")
    private String last_name;

    @Column( name = "mobile_number")
    private String mobileNumber;

    @Column( name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @JsonBackReference(value = "user_role")
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<Account> accounts;

    public User(){

    }

    public User(Long id, String first_name, String last_name, String mobileNumber){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", mobile_number='" + mobileNumber + '\'' +
                '}';
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
