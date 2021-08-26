package com.andrey;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Simple JavaBean domain object.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */


@Getter
@Setter
@ToString
public class Rates {

    private Long id;

    private Long id_currency_from;

    private Long id_currency_to;

    private double rate_value;

    public Rates(){
    }


    public Rates(Long id, Long id_currency_from, Long id_currency_to, double rate_value){
        this.id = id;
        this.id_currency_from = id_currency_from;
        this.id_currency_to = id_currency_to;
        this.rate_value = rate_value;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", id_currency_from='" + id_currency_from + '\'' +
                ", id_currency_to='" + id_currency_to + '\'' +
                ", rate_value=" + rate_value +
                '}';
    }
}
