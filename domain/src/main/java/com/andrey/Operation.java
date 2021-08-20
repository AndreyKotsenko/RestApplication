package com.andrey;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;


/**
 * Simple JavaBean domain object.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */


@Getter
@Setter
@ToString
public class Operation {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date_operation;

    private String description;

    private Long total_sum;

    private Long type_operation_id;

    private Long category_id;

    private Long account_id_from;

    private Long account_id_to;

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
                     Long total_sum, Long typeOperation,
                     Long category, Long account_from, Long account_to){

        this.id = id;
        this.date_operation = date_operation;
        this.description = description;
        this.total_sum = total_sum;
        this.type_operation_id = typeOperation;
        this.category_id = category;
        this.account_id_from = account_from;
        this.account_id_to = account_to;

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
