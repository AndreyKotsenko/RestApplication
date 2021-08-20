package com.andrey;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;


/**
 * Simple JavaBean domain object.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */


@Getter
@Setter
@ToString
public class Account {

    private Long id;

    private String account_name;

    private Long balance;

    private Long user_id;

    private Long currency_id;

    private List<Operation> operations;

    public Account(){
    }


    public Account(Long id, String account_name, Long balance){
        this.id = id;
        this.account_name = account_name;
        this.balance = balance;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", account_name='" + account_name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
