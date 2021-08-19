package com.andrey;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;


/**
 * Simple JavaBean domain object.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@Entity
@Table(name = "operation")
@Getter
@Setter
@ToString
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "date_operation")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date_operation;

    @Column( name = "description")
    private String description;

    @Column( name = "total_sum")
    private Long total_sum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "type_operation_id", referencedColumnName = "id")
    @JsonBackReference( value = "type_operation_operation")
    private TypeOperation typeOperation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "category_id", referencedColumnName = "id")
    @JsonBackReference( value = "category_operation")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "account_id_from", referencedColumnName = "id")
    @JsonBackReference( value = "account_operation_from")
    private Account account_from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "account_id_to", referencedColumnName = "id")
    @JsonBackReference( value = "account_operation_to")
    private Account account_to;

    public Operation(){

    }


    public Operation(Long id, Date date_operation, String description,
                     Long total_sum){
        this.id = id;
        this.date_operation = date_operation;
        this.description = description;
        this.total_sum = total_sum;

    }

    public Operation(Long id, Date date_operation, String description,
                     Long total_sum, TypeOperation typeOperation,
                     Category category, Account account_from, Account account_to){

        this.id = id;
        this.date_operation = date_operation;
        this.description = description;
        this.total_sum = total_sum;
        this.typeOperation = typeOperation;
        this.category = category;
        this.account_from = account_from;
        this.account_to = account_to;

    }


    public Long getTotal_sum() {
        return total_sum;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", date_operation=" + date_operation +
                ", description='" + description + '\'' +
                ", total_sum=" + total_sum +
                '}';
    }
}
