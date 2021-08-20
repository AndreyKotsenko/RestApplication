package com.andrey;

import com.andrey.datatest.DateGeneratorForTest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;



@Slf4j
@RunWith(SpringRunner.class)
@MybatisTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    private Comparator<Account> COMPARATOR = (o1, o2) -> (o1.getId() == o2.getId()) ? 0 : 1;


    @Test
    void getById() {


        Account account = DateGeneratorForTest.generateAccount();
        account.setId(1l);

        accountRepository.add(account);

        log.info("My account = " + account);
        Account accountOut = accountRepository.getById((long)1);

        log.info("My accountOut = " + accountOut);
        assertNotNull(accountOut);
        assertEquals(accountOut.getId(), account.getId());

//        Account account = accountRepository.getById(1l);
        log.info("My acc = " + account);
        assertEquals(account.getId(), 1l);

    }

    @Test
    @Rollback(value = false)
    void save() {
        Account account = DateGeneratorForTest.generateAccount();
        account.setId((long)1);
        accountRepository.add(account);

        log.info("My account = " + account);
        Account accountOut = accountRepository.getById((long)1);

        log.info("My accountOut = " + accountOut);
        assertNotNull(accountOut);
        assertEquals(accountOut.getId(), account.getId());

    }

    @Test
    @Rollback(value = false)
    void deleteById() {

        Account account = DateGeneratorForTest.generateAccount();
        account.setId((long)1);
        accountRepository.add(account);

        Account accountOut = accountRepository.getById((long)1);
        log.info("AccountOut = " + accountOut);
        assertNotNull(accountOut);

        accountOut = null;
        accountRepository.deleteById((long)1);

        Optional<Account> optionalAccount = Optional.ofNullable(accountRepository.getById((long) 1));

        if(optionalAccount.isPresent()){
            accountOut = optionalAccount.get();
        }

        Assertions.assertThat(accountOut).isNull();
    }

    @Test
    @Rollback(value = false)
    void findAll() {
        List<Account> accounts = DateGeneratorForTest.generateAccountList(5);

        int i = 0;
        for(Account account : accounts){
            i++;

            account.setId((long)i);
            accountRepository.add(account);

        }

        List<Account> accountList = accountRepository.findAll();


        assertNotNull(accountList);
        Assertions.assertThat(accountList.size()).isEqualTo(5);
        Assertions.assertThat(accounts)
                .usingElementComparator(COMPARATOR)
                .isEqualTo(accountList);



    }

}