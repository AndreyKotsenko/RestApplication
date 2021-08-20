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

import static com.andrey.datatest.DateGeneratorForTest.DATE;
import static com.andrey.datatest.DateGeneratorForTest.ID;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Slf4j
@WebMvcTest({AccountRestController.class})
@RunWith(SpringRunner.class)
@WithMockUser(username = "068 980 34 56", password = "pass", roles = "ADMIN")
class AccountRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountServiceImp accountService;

    @MockBean
    private UserServiceImp userService;


    @Test
    void getAccount() throws Exception {
        //GIVEN
        Account account = DateGeneratorForTest.generateAccount();
        Mockito.when(accountService.getById(ID)).thenReturn(account);

        //WHEN
        mockMvc.perform(get("/account/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.account_name").value(account.getAccount_name()))
                .andExpect(jsonPath("$.balance").value(account.getBalance()));

        //THEN
        Mockito.verify(accountService).getById(ID);
        Mockito.verifyNoMoreInteractions(accountService);

    }

    @Test
    void getBalanceByDate() throws Exception {
        //GIVEN
        Account account = DateGeneratorForTest.generateAccount();
        List<Operation> operations = DateGeneratorForTest.generateOperationList(5);

        double balance = account.getBalance();

        log.info("balance = " + balance);
        for(Operation operation : operations){
            if(operation.getType_operation_id() == 1){
                balance += operation.getTotal_sum();
            }else{
                balance -= operation.getTotal_sum();
            }
        }

        log.info("balance = " + balance);
        Mockito.when(accountService.getBalanceByDate(any(), any())).thenReturn(balance);

        log.info("balance = " + balance);
        log.info(mockMvc.perform(get("/account/balance/" + ID + "?date=" + DATE)) + "- this it");
        //WHEN
        mockMvc.perform(get("/account/balance/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(balance/100)));

        //THEN
        Mockito.verify(accountService, Mockito.times(1)).getBalanceByDate(any(), any());
        Mockito.verifyNoMoreInteractions(accountService);
    }

    @Test
    void saveAccount() throws Exception {
        //GIVEN
        Account account = DateGeneratorForTest.generateAccount();
        Mockito.doNothing().when(accountService).save(account);

        //WHEN
        mockMvc.perform(post("/account/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(account)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.account_name").value(account.getAccount_name()))
                .andExpect((jsonPath("$.balance")).value(account.getBalance()));
        //THEN

        Mockito.verify(accountService).save(any());
        Mockito.verifyNoMoreInteractions(accountService);
    }

    @Test
    void updateAccount() throws Exception {
        //GIVEN
        Account account = DateGeneratorForTest.generateAccount();
        Mockito.doNothing().when(accountService).save(account);

        //WHEN
        mockMvc.perform(put("/account/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(account)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.account_name").value(account.getAccount_name()))
                .andExpect((jsonPath("$.balance")).value(account.getBalance()));
        //THEN

        Mockito.verify(accountService).save(any());
        Mockito.verifyNoMoreInteractions(accountService);
    }

    @Test
    void deleteAccount() throws Exception {
        //GIVEN
        Account account = DateGeneratorForTest.generateAccount();
        Mockito.when(accountService.getById(ID)).thenReturn(account);
        Mockito.doNothing().when(accountService).delete(ID);

        //WHEN
        mockMvc.perform(delete("/account/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        //THEN

        Mockito.verify(accountService).delete(ID);
        Mockito.verify(accountService).getById(ID);
        Mockito.verifyNoMoreInteractions(accountService);
    }

    @Test
    void getAllAccount() throws Exception {
        //GIVEN
        List<Account> accounts = DateGeneratorForTest.generateAccountList(5);
        Mockito.when(accountService.getAll()).thenReturn(accounts);

        //WHEN
        mockMvc.perform(get("/account/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].id").value(accounts.get(0).getId()))
                .andExpect(jsonPath("$[1].id").value(accounts.get(1).getId()))
                .andExpect(jsonPath("$[2].id").value(accounts.get(2).getId()))
                .andExpect(jsonPath("$[3].id").value(accounts.get(3).getId()))
                .andExpect(jsonPath("$[4].id").value(accounts.get(4).getId()));

        //THEN
        Mockito.verify(accountService).getAll();
        Mockito.verifyNoMoreInteractions(accountService);
    }
}