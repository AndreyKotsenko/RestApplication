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
@Table(name = "account")
@Getter
@Setter
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "account_name")
    private String account_name;

    @Column( name = "balance")
    private Long balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "user_id", referencedColumnName = "id")
    @JsonBackReference( value = "user_account")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "currency_id", referencedColumnName = "id")
    @JsonBackReference( value = "account_currency")
    private Currency currency;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account_from", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Operation> operations;

    public Account(){
    }


    public Account(Long id, String account_name, Long balance){
        this.id = id;
        this.account_name = account_name;
        this.balance = balance;
    }


    public Long getBalance(){
        return balance ;
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
