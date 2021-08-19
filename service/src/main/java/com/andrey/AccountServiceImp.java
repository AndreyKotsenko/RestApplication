package com.andrey;

import com.andrey.criteria.MySpecification;
import com.andrey.criteria.SearchCriteria;
import com.andrey.criteria.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.sql.Date;
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

        MySpecification<Operation> mySpecification = new MySpecification<Operation>();
        mySpecification.add(new SearchCriteria("account_from/account_to", id, SearchOperation.OR_EQUAL));
        mySpecification.add(new SearchCriteria("date_operation", date, SearchOperation.LESS_THAN_EQUAL_FOR_DATE));

        List<Operation> operationsByDate = operationRepository.findAll(mySpecification);
        log.info("Operation by date = " + String.valueOf(operationsByDate));

        double balance = accountRepository.getById(id).getBalance();

        log.info("My balance = " + balance);
        for(Operation operation : operationsByDate){
            if(operation.getAccount_from() != null && operation.getAccount_from().getId() == id){
                balance-=operation.getTotal_sum();
                log.info("Balance = " + balance + ", total sum = " + operation.getTotal_sum());
            } else if(operation.getAccount_to() != null && operation.getAccount_to().getId() == id){
                balance+=operation.getTotal_sum();
            }

        }

        return balance;
    }

    @Override
    public List<Account> getAllByUser(String mobileNumber) {
        MySpecification<Account> mySpecification = new MySpecification<Account>();
        mySpecification.add(new SearchCriteria("user", userRepository.findByMobileNumber(mobileNumber).getId(), SearchOperation.EQUAL));


        List<Account> accounts = accountRepository.findAll(mySpecification);


        return accounts;
    }


}
