package com.andrey;

import com.andrey.datatest.DateGeneratorForTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.andrey.datatest.DateGeneratorForTest.ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceImpTest {

    @InjectMocks
    private CurrencyServiceImp currencyService;

    @Mock
    private CurrencyRepository currencyRepository;

    @Mock
    private BaseRepository<Currency> repository;

    @Mock
    HttpServletRequest requestMethod;


    @Test
    void getById() {
        //GIVEN
        Currency currency = DateGeneratorForTest.generateCurrency();
        Mockito.when(repository.getById(ID)).thenReturn(currency);

        //WHEN
        Currency fetchedCurrency = currencyService.getById(ID);
        System.out.println(fetchedCurrency);

        //THEN
        assertThat(fetchedCurrency)
                .isNotNull()
                .isExactlyInstanceOf(Currency.class);

        assertThat(fetchedCurrency).isEqualTo(currency);

        Mockito.verify(repository).getById(ID);
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void save() {

        //GIVEN
        Currency currency = DateGeneratorForTest.generateCurrency();
        Mockito.doNothing().when(repository).add(any());
        Mockito.when(requestMethod.getMethod()).thenReturn("POST");

        //WHEN
        currencyService.save(currency);



        //THEN
        Mockito.verify(repository).add(any());
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void delete() {
        //GIVEN
        Currency currency = DateGeneratorForTest.generateCurrency();
        Mockito.doNothing().when(repository).deleteById(any(Long.class));

        //WHEN
        currencyService.delete(ID);



        //THEN
        Mockito.verify(repository).deleteById(any(Long.class));
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void getAll() {
        //GIVEN
        List<Currency> currencies = DateGeneratorForTest.generateCurrencyList(3);
        Mockito.when(repository.findAll()).thenReturn(currencies);

        //WHEN
        List<Currency> currencyList = currencyService.getAll();
        System.out.println(currencyList);


        //THEN
        assertThat(currencyList)
                .isNotEmpty()
                .isEqualTo(currencies);

        Mockito.verify(repository).findAll();
        Mockito.verifyNoMoreInteractions(repository);
    }
}