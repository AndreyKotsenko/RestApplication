package com.andrey;

import com.andrey.filter.Filter;
import com.andrey.criteria.MySpecification;
import com.andrey.criteria.SearchCriteria;
import com.andrey.criteria.SearchOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service for operation.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@Slf4j
@Service
public class OperationServiceImp extends BaseService<Operation> implements OperationService{

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    AccountRepository accountRepository;



    @Override
    public List<Operation> getAllByFilter(Filter filter) {
        MySpecification<Operation> mySpecification = new MySpecification<Operation>();

        if(filter.getAccount_id() != null){
            mySpecification.add(new SearchCriteria("account_from/account_to", filter.getAccount_id(), SearchOperation.OR_EQUAL));
        }

        if(filter.getTypeOperation_id() != null){
            mySpecification.add(new SearchCriteria("typeOperation", filter.getTypeOperation_id(), SearchOperation.IN));
        }

        if(filter.getCategory_id() != null){
            mySpecification.add(new SearchCriteria("category", filter.getCategory_id(), SearchOperation.IN));
        }

        if(filter.getGroup_category_id() != null){
            mySpecification.add(new SearchCriteria("category/groupCategory", filter.getGroup_category_id(), SearchOperation.JOIN_EQUAL));
        }

        // for date interval
        if(filter.getStartDateOperation() != null && filter.getEndDateOperation() == null){
            log.info("I am here " +filter.getStartDateOperation() + " ---- " + filter.getEndDateOperation());
            mySpecification.add(new SearchCriteria("date_operation", filter.getStartDateOperation(), SearchOperation.GREATER_THAN_EQUAL_FOR_DATE));
        }else if(filter.getStartDateOperation() == null && filter.getEndDateOperation() != null){
            log.info("I am here " +filter.getStartDateOperation() + " ---- " + filter.getEndDateOperation());
            mySpecification.add(new SearchCriteria("date_operation", filter.getEndDateOperation(), SearchOperation.LESS_THAN_EQUAL_FOR_DATE));
        }else if(filter.getStartDateOperation() != null && filter.getEndDateOperation() != null){
            log.info("I am here " +filter.getStartDateOperation() + " ---- " + filter.getEndDateOperation());
            mySpecification.add(new SearchCriteria("date_operation", filter.getStartDateOperation(), SearchOperation.GREATER_THAN_EQUAL_FOR_DATE));
            mySpecification.add(new SearchCriteria("date_operation", filter.getEndDateOperation(), SearchOperation.LESS_THAN_EQUAL_FOR_DATE));
        }




        List<Operation> operationsByFilter = operationRepository.findAll(mySpecification);
        log.info("I am here" + operationsByFilter);
        return operationsByFilter;
    }

    @Override
    public Double getFilterSum(Filter filter) {
        Double sum = 0.0;

        for(Operation operation : getAllByFilter(filter)){
            sum+=operation.getTotal_sum();
        }

        return sum;
    }

}
