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
public class User {


    private Long id;

    private String first_name;

    private String last_name;

    private String mobile_number;

    private String password;

    private Long role_id;

    private Collection<Account> accounts;

    public User(){

    }

    public User(Long id, String first_name, String last_name, String mobileNumber){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mobile_number = mobileNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", mobile_number='" + mobile_number + '\'' +
                '}';
    }



}
