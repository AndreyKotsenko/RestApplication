package com.andrey.filter;

import com.andrey.Operation;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;


/**
 * Filter for app.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

public class Filter extends Operation {

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date startDateOperation;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date endDateOperation;

    private Long category_id;

    private Long group_category_id;

    private Long account_id;

    private Long currency_id;

    private Long typeOperation_id;

    public Filter() {
        super();
    }

    public Long getTypeOperation_id() {
        return typeOperation_id;
    }

    public void setTypeOperation_id(Long typeOperation_id) {
        this.typeOperation_id = typeOperation_id;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public Long getGroup_category_id() {
        return group_category_id;
    }

    public void setGroup_category_id(Long group_category_id) {
        this.group_category_id = group_category_id;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id_from) {
        this.account_id = account_id_from;
    }

    public Long getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(Long currency_id) {
        this.currency_id = currency_id;
    }

    public Date getStartDateOperation() {
        return startDateOperation;
    }

    public void setStartDateOperation(Date startDateOperation) {
        this.startDateOperation = startDateOperation;
    }

    public Date getEndDateOperation() {
        return endDateOperation;
    }

    public void setEndDateOperation(Date endDateOperation) {
        this.endDateOperation = endDateOperation;
    }
}
