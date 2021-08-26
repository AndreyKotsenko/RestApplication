package com.andrey;

import com.andrey.filter.Filter;
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

        List<Operation> operationsByFilter = operationRepository.findAllByFilter(filter);
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
