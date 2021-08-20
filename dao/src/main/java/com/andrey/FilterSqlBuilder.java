package com.andrey;

import com.andrey.filter.Filter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;

@Slf4j
public class FilterSqlBuilder {

    public static String buildGetOperationsByFilter(Filter filter) {

        return new SQL(){{
            SELECT("*");
            FROM("operation");
            if (filter.getAccount_id() != null) {
                WHERE("account_id_from = " + filter.getAccount_id() + " or account_id_to = "+ filter.getAccount_id());
            }

            if(filter.getTypeOperation_id() != null){
                AND();
                WHERE("type_operation_id = " + filter.getTypeOperation_id());
            }

            if(filter.getCategory_id() != null){
                AND();
                WHERE("category_id = " + filter.getCategory_id());
            }

            if(filter.getStartDateOperation() != null && filter.getEndDateOperation() != null){
                AND();
                WHERE("date_operation >= '" + filter.getStartDateOperation().toInstant() + "' and date_operation <= '" + filter.getEndDateOperation().toInstant() + "'");
            } else if(filter.getStartDateOperation() == null && filter.getEndDateOperation() != null){
                AND();
                WHERE(" date_operation <= '" + filter.getEndDateOperation().toInstant() + "'");

            } else if (filter.getStartDateOperation() != null && filter.getEndDateOperation() == null){
                AND();
                WHERE("date_operation >= '" + filter.getStartDateOperation().toInstant() + "'" );

            }

            if(filter.getGroup_category_id() != null) {
                AND();
                INNER_JOIN("category c ON (c.id = category_id)");
                WHERE("group_id = " + filter.getGroup_category_id());
            }


            ORDER_BY("operation.id");
        }}.toString();
    }

}
