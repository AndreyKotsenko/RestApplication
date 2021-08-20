package com.andrey.rest;

import com.andrey.*;
import com.andrey.datatest.DateGeneratorForTest;
import com.andrey.filter.Filter;
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

import java.util.LinkedList;
import java.util.List;

import static com.andrey.datatest.DateGeneratorForTest.ID;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@WebMvcTest({OperationRestController.class})
@RunWith(SpringRunner.class)
@WithMockUser(username = "068 980 34 56", password = "pass", roles = "ADMIN")
class OperationRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OperationServiceImp operationService;

    @MockBean
    private UserServiceImp userService;

    @Test
    void getOperation() throws Exception{
        //GIVEN
        Operation operation = DateGeneratorForTest.generateOperation();
        Mockito.when(operationService.getById(ID)).thenReturn(operation);

        //WHEN
        mockMvc.perform(get("/operation/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.description").value(operation.getDescription()))
                .andExpect(jsonPath("$.total_sum").value(operation.getTotal_sum()));

        //THEN
        Mockito.verify(operationService).getById(ID);
        Mockito.verifyNoMoreInteractions(operationService);
    }

    @Test
    void filter() throws Exception{
        //GIVEN
        Filter filter = DateGeneratorForTest.generateFilter();
        filter.setAccount_id((long)1);


        List<Operation> operations = DateGeneratorForTest.generateOperationList(8);
        List<Operation> operationsTest = new LinkedList<>();
        int i = 0;
        for(Operation operation : operations){

            if(i%2 == 0){
                operation.setAccount_id_from(1l);
                operationsTest.add(operation);
            }else {
                operation.setAccount_id_from(2l);
            }
            i++;
        }
        log.info(operationsTest + " this is my operations");

        Mockito.when(operationService.getAllByFilter(any())).thenReturn(operationsTest);

        //WHEN
        mockMvc.perform(post("/operation/filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(filter)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].id").value(operations.get(0).getId()))
                .andExpect(jsonPath("$[1].id").value(operations.get(2).getId()))
                .andExpect(jsonPath("$[2].id").value(operations.get(4).getId()))
                .andExpect(jsonPath("$[3].id").value(operations.get(6).getId()));

        //THEN
        Mockito.verify(operationService).getAllByFilter(any());
        Mockito.verifyNoMoreInteractions(operationService);
    }

    @Test
    void filterSum() throws Exception{
        //GIVEN
        Filter filter = DateGeneratorForTest.generateFilter();
        filter.setAccount_id((long)1);


        List<Operation> operations = DateGeneratorForTest.generateOperationList(8);
        List<Operation> operationsTest = new LinkedList<>();
        int i = 0;
        double sum = 0;
        for(Operation operation : operations){

            if(i%2 == 0){
                operation.setAccount_id_from(1l);
                operationsTest.add(operation);
                sum+= operation.getTotal_sum();
            }else {
                operation.setAccount_id_from(2l);
            }
            i++;
        }

        Mockito.when(operationService.getFilterSum(any())).thenReturn(sum);

        //WHEN
        mockMvc.perform(post("/operation/sum")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(filter)))
                .andExpect(status().isCreated())
                .andExpect(content().string(String.valueOf(sum/100)));


        //THEN
        Mockito.verify(operationService).getFilterSum(any());
        Mockito.verifyNoMoreInteractions(operationService);
    }

    @Test
    void saveOperation() throws Exception{
        //GIVEN
        Operation operation = DateGeneratorForTest.generateOperation();
        Mockito.doNothing().when(operationService).save(operation);

        //WHEN
        mockMvc.perform(post("/operation/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(operation)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.description").value(operation.getDescription()))
                .andExpect(jsonPath("$.total_sum").value(operation.getTotal_sum()));

        //THEN

        Mockito.verify(operationService).save(any());
        Mockito.verifyNoMoreInteractions(operationService);
    }

    @Test
    void updateOperation() throws Exception{
        //GIVEN
        Operation operation = DateGeneratorForTest.generateOperation();
        Mockito.doNothing().when(operationService).save(operation);

        //WHEN
        mockMvc.perform(put("/operation/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(operation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.description").value(operation.getDescription()))
                .andExpect(jsonPath("$.total_sum").value(operation.getTotal_sum()));

        //THEN

        Mockito.verify(operationService).save(any());
        Mockito.verifyNoMoreInteractions(operationService);
    }

    @Test
    void deleteOperation() throws Exception{
        //GIVEN
        Operation operation = DateGeneratorForTest.generateOperation();
        Mockito.when(operationService.getById(ID)).thenReturn(operation);
        Mockito.doNothing().when(operationService).delete(ID);

        //WHEN
        mockMvc.perform(delete("/operation/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        //THEN

        Mockito.verify(operationService).delete(ID);
        Mockito.verify(operationService).getById(ID);
        Mockito.verifyNoMoreInteractions(operationService);
    }

    @Test
    void getAllOperation() throws Exception{
        //GIVEN
        List<Operation> operations = DateGeneratorForTest.generateOperationList(5);
        Mockito.when(operationService.getAll()).thenReturn(operations);

        //WHEN
        mockMvc.perform(get("/operation/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].id").value(operations.get(0).getId()))
                .andExpect(jsonPath("$[1].id").value(operations.get(1).getId()))
                .andExpect(jsonPath("$[2].id").value(operations.get(2).getId()))
                .andExpect(jsonPath("$[3].id").value(operations.get(3).getId()))
                .andExpect(jsonPath("$[4].id").value(operations.get(4).getId()));

        //THEN
        Mockito.verify(operationService).getAll();
        Mockito.verifyNoMoreInteractions(operationService);
    }
}