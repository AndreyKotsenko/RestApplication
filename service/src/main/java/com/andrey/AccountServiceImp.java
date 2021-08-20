package com.andrey;

import com.andrey.criteria.MySpecification;
import com.andrey.criteria.SearchCriteria;
import com.andrey.criteria.SearchOperation;
import com.andrey.filter.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * Service for account.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@Slf4j
@Service
@Component
public class AccountServiceImp extends BaseService<Account> implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    UserRepository userRepository;



    @Override
    public Double getBalanceByDate(Long id, Date date) {

        Filter filter = new Filter();
        filter.setAccount_id(id);
        filter.setEndDateOperation(date);

        List<Operation> operationsByDate = operationRepository.findAllByFilter(filter);
        log.info("Operation by date = " + String.valueOf(operationsByDate));

        double balance = accountRepository.getById(id).getBalance();

        log.info("My balance = " + balance);
        for(Operation operation : operationsByDate){
            if(operation.getAccount_id_from() != null && operation.getAccount_id_from() == id){
                balance-=operation.getTotal_sum();
                log.info("Balance = " + balance + ", total sum = " + operation.getTotal_sum());
            } else if(operation.getAccount_id_to() != null && operation.getAccount_id_to() == id){
                balance+=operation.getTotal_sum();
            }

        }

        return balance;
    }

    @Override
    public List<Account> getAllByUser(String mobileNumber) {

        List<Account> accounts = accountRepository.findAllByMobileNumber(mobileNumber);


        return accounts;
    }


}
