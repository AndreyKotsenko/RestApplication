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
@WebMvcTest({TypeOperationRestController.class})
@RunWith(SpringRunner.class)
@WithMockUser(username = "068 980 34 56", password = "pass", roles = "ADMIN")
class TypeOperationRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TypeOperationServiceImp typeOperationService;

    @MockBean
    private UserServiceImp userService;

    @Test
    void getTypeOperation() throws Exception{
        //GIVEN
        TypeOperation typeOperation = DateGeneratorForTest.generateTypeOperation();
        Mockito.when(typeOperationService.getById(ID)).thenReturn(typeOperation);

        //WHEN
        mockMvc.perform(get("/typeOperation/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.type").value(typeOperation.getType()));

        //THEN
        Mockito.verify(typeOperationService).getById(ID);
        Mockito.verifyNoMoreInteractions(typeOperationService);
    }

    @Test
    void saveTypeOperation() throws Exception{
        //GIVEN
        TypeOperation typeOperation = DateGeneratorForTest.generateTypeOperation();
        Mockito.doNothing().when(typeOperationService).save(typeOperation);

        //WHEN
        mockMvc.perform(post("/typeOperation/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(typeOperation)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.type").value(typeOperation.getType()));
        //THEN

        Mockito.verify(typeOperationService).save(any());
        Mockito.verifyNoMoreInteractions(typeOperationService);
    }



    @Test
    void updateTypeOperation() throws Exception{
        //GIVEN
        TypeOperation typeOperation = DateGeneratorForTest.generateTypeOperation();
        Mockito.doNothing().when(typeOperationService).save(typeOperation);

        //WHEN
        mockMvc.perform(put("/typeOperation/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(typeOperation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.type").value(typeOperation.getType()));
        //THEN

        Mockito.verify(typeOperationService).save(any());
        Mockito.verifyNoMoreInteractions(typeOperationService);
    }

    @Test
    void deleteTypeOperation() throws Exception{
        //GIVEN
        TypeOperation typeOperation = DateGeneratorForTest.generateTypeOperation();
        Mockito.when(typeOperationService.getById(ID)).thenReturn(typeOperation);
        Mockito.doNothing().when(typeOperationService).delete(ID);

        //WHEN
        mockMvc.perform(delete("/typeOperation/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        //THEN

        Mockito.verify(typeOperationService).delete(ID);
        Mockito.verify(typeOperationService).getById(ID);
        Mockito.verifyNoMoreInteractions(typeOperationService);
    }

    @Test
    void getAllTypeOperation() throws Exception{
        //GIVEN
        List<TypeOperation> typeOperations = DateGeneratorForTest.generateTypeOperationList(5);
        Mockito.when(typeOperationService.getAll()).thenReturn(typeOperations);

        //WHEN
        mockMvc.perform(get("/typeOperation/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].id").value(typeOperations.get(0).getId()))
                .andExpect(jsonPath("$[1].id").value(typeOperations.get(1).getId()))
                .andExpect(jsonPath("$[2].id").value(typeOperations.get(2).getId()))
                .andExpect(jsonPath("$[3].id").value(typeOperations.get(3).getId()))
                .andExpect(jsonPath("$[4].id").value(typeOperations.get(4).getId()));

        //THEN
        Mockito.verify(typeOperationService).getAll();
        Mockito.verifyNoMoreInteractions(typeOperationService);
    }
}