package com.andrey.rest;

import com.andrey.*;
import com.andrey.datatest.DateGeneratorForTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.andrey.datatest.DateGeneratorForTest.ID;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest({CurrencyRestController.class})
@RunWith(SpringRunner.class)
@WithMockUser(username = "068 980 34 56", password = "pass", roles = "ADMIN")
class CurrencyRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyServiceImp currencyService;

    @MockBean
    private UserServiceImp userService;

    @Test
    void getCurrency() throws Exception{
        //GIVEN
        Currency currency = DateGeneratorForTest.generateCurrency();
        Mockito.when(currencyService.getById(ID)).thenReturn(currency);

        //WHEN
        mockMvc.perform(get("/currency/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.type").value(currency.getType()));

        //THEN
        Mockito.verify(currencyService).getById(ID);
        Mockito.verifyNoMoreInteractions(currencyService);
    }

    @Test
    void saveCurrency() throws Exception{
        //GIVEN
        Currency currency = DateGeneratorForTest.generateCurrency();
        Mockito.doNothing().when(currencyService).save(currency);

        //WHEN
        mockMvc.perform(post("/currency/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(currency)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.type").value(currency.getType()));

        //THEN
        Mockito.verify(currencyService).save(any());
        Mockito.verifyNoMoreInteractions(currencyService);
    }

    @Test
    void updateCurrency() throws Exception{
        //GIVEN
        Currency currency = DateGeneratorForTest.generateCurrency();
        Mockito.doNothing().when(currencyService).save(currency);

        //WHEN
        mockMvc.perform(put("/currency/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(currency)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.type").value(currency.getType()));

        //THEN
        Mockito.verify(currencyService).save(any());
        Mockito.verifyNoMoreInteractions(currencyService);
    }

    @Test
    void deleteCurrency() throws Exception{
        //GIVEN
        Currency currency = DateGeneratorForTest.generateCurrency();
        Mockito.when(currencyService.getById(ID)).thenReturn(currency);
        Mockito.doNothing().when(currencyService).delete(ID);

        //WHEN
        mockMvc.perform(delete("/currency/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        //THEN

        Mockito.verify(currencyService).delete(ID);
        Mockito.verify(currencyService).getById(ID);
        Mockito.verifyNoMoreInteractions(currencyService);
    }

    @Test
    void getAllCurrency() throws Exception{
        //GIVEN
        List<Currency> currencies = DateGeneratorForTest.generateCurrencyList(5);
        Mockito.when(currencyService.getAll()).thenReturn(currencies);

        //WHEN
        mockMvc.perform(get("/currency/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].id").value(currencies.get(0).getId()))
                .andExpect(jsonPath("$[1].id").value(currencies.get(1).getId()))
                .andExpect(jsonPath("$[2].id").value(currencies.get(2).getId()))
                .andExpect(jsonPath("$[3].id").value(currencies.get(3).getId()))
                .andExpect(jsonPath("$[4].id").value(currencies.get(4).getId()));

        //THEN
        Mockito.verify(currencyService).getAll();
        Mockito.verifyNoMoreInteractions(currencyService);
    }
}