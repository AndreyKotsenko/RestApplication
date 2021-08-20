package com.andrey;

import com.andrey.criteria.MySpecification;
import com.andrey.datatest.DateGeneratorForTest;
import com.andrey.filter.Filter;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Request;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import static com.andrey.datatest.DateGeneratorForTest.DATE;
import static com.andrey.datatest.DateGeneratorForTest.ID;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@Slf4j
@ExtendWith(MockitoExtension.class)
class AccountServiceImpTest {

    @InjectMocks
    @Autowired
    private AccountServiceImp accountService;

    @Mock
    private BaseRepository<Account> repository;

    @Mock
    HttpServletRequest requestMethod;


    @Mock
    private AccountRepository accountRepository;
    @Mock
    private OperationRepository operationRepository;

    @Test
    void getById() {

        //GIVEN
        Account account = DateGeneratorForTest.generateAccount();
        Mockito.when(repository.getById(ID)).thenReturn(account);

        //WHEN
        Account fetchedAccount = accountService.getById(ID);
        log.info("Account = " + fetchedAccount);

        //THEN
        assertThat(fetchedAccount)
                .isNotNull()
                .isExactlyInstanceOf(Account.class);

        assertThat(fetchedAccount).isEqualTo(account);

        Mockito.verify(repository).getById(ID);
        Mockito.verifyNoMoreInteractions(repository);

    }

    @Test
    void save() {

        //GIVEN
        Account account = DateGeneratorForTest.generateAccount();
        Mockito.doNothing().when(repository).add(any());
        Mockito.when(requestMethod.getMethod()).thenReturn("POST");


        //WHEN
        accountService.save(account);



        //THEN
        Mockito.verify(repository).add(any());
        Mockito.verifyNoMoreInteractions(repository);

    }

    @Test
    void delete() {
        //GIVEN
        Account account = DateGeneratorForTest.generateAccount();
        Mockito.doNothing().when(repository).deleteById(any(Long.class));

        //WHEN
        accountService.delete(ID);



        //THEN
        Mockito.verify(repository).deleteById(any(Long.class));
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void getAll() {

        //GIVEN
        List<Account> accounts = DateGeneratorForTest.generateAccountList(3);
        Mockito.when(repository.findAll()).thenReturn(accounts);

        //WHEN
        List<Account> accountList = accountService.getAll();
        log.info("Account list = " + accountList);


        //THEN
        assertThat(accountList)
                .isNotEmpty()
                .isEqualTo(accounts);

        Mockito.verify(repository).findAll();
        Mockito.verifyNoMoreInteractions(repository);

    }

    @Test
    void getBalanceByDate() {
        //GIVEN
        Account account = DateGeneratorForTest.generateAccount();
        account.setOperations(DateGeneratorForTest.generateOperationList( 5));
        Mockito.when(accountRepository.getById(ID)).thenReturn(account);
        Mockito.when(operationRepository.findAllByFilter(any(Filter.class))).thenReturn(account.getOperations());
        log.info("Account = " + account);

        //WHEN
        Double balance = accountService.getBalanceByDate(ID, DATE);

        //THEN
        assertThat(balance).isEqualTo(account.getBalance() - 100 * 5);
        log.info("Operations =" + account.getOperations()  );
        log.info("balance = " + balance + " dbgdfbhd = " + (account.getBalance() - 100 * 5) );

        Mockito.verify(accountRepository, times(1)).getById(ID);
        verifyNoMoreInteractions(accountRepository);

    }
}