package com.andrey;

import com.andrey.datatest.DateGeneratorForTest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RunWith(SpringRunner.class)
@MybatisTest
class CurrencyRepositoryTest {

    @Autowired
    private CurrencyRepository currencyRepository;

    private Comparator<Currency> COMPARATOR = (o1, o2) -> (o1.getId() == o2.getId()) ? 0 : 1;


    @Test
    @Rollback(value = false)
    void getById() {

        Currency currency = DateGeneratorForTest.generateCurrency();
        currency.setId((long)1);
        currencyRepository.add(currency);


        Currency currencyOut = currencyRepository.getById((long)1);


        assertNotNull(currencyOut);
        assertEquals(currencyOut.getId(), currency.getId());

    }

    @Test
    @Rollback(value = false)
    void save() {
        Currency currency = DateGeneratorForTest.generateCurrency();
        currency.setId((long)1);
        currencyRepository.add(currency);


        Currency currencyOut = currencyRepository.getById((long)1);


        assertNotNull(currencyOut);
        assertEquals(currencyOut.getId(), currency.getId());

    }

    @Test
    @Rollback(value = false)
    void deleteById() {

        Currency currency = DateGeneratorForTest.generateCurrency();
        currency.setId((long)1);
        currencyRepository.add(currency);

        Currency currencyOut = currencyRepository.getById((long)1);

        assertNotNull(currencyOut);

        currencyOut = null;
        currencyRepository.deleteById((long)1);

        Optional<Currency> optionalCurrency = Optional.ofNullable(currencyRepository.getById((long) 1));

        if(optionalCurrency.isPresent()){
            currencyOut = optionalCurrency.get();
        }

        Assertions.assertThat(currencyOut).isNull();
    }

    @Test
    @Rollback(value = false)
    void findAll() {
        List<Currency> currencies = DateGeneratorForTest.generateCurrencyList(5);

        int i = 0;
        for(Currency currency : currencies){
            i++;

            currency.setId((long)i);
            currencyRepository.add(currency);

        }

        List<Currency> currencyList = currencyRepository.findAll();


        assertNotNull(currencyList);
        Assertions.assertThat(currencyList.size()).isEqualTo(5);
        Assertions.assertThat(currencies)
                .usingElementComparator(COMPARATOR)
                .isEqualTo(currencyList);



    }
}