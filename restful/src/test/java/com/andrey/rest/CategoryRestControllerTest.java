package com.andrey.rest;

import com.andrey.*;
import com.andrey.datatest.DateGeneratorForTest;
import com.fasterxml.jackson.core.JsonProcessingException;
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
@WebMvcTest({CategoryRestController.class})
@RunWith(SpringRunner.class)
@WithMockUser(username = "068 980 34 56", password = "pass", roles = "ADMIN")
class CategoryRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryServiceImp categoryService;

    @MockBean
    private UserServiceImp userService;

    @Test
    void getCategory() throws Exception {
        //GIVEN
        Category category = DateGeneratorForTest.generateCategory();
        Mockito.when(categoryService.getById(ID)).thenReturn(category);

        //WHEN
        mockMvc.perform(get("/category/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.title").value(category.getTitle()));

        //THEN
        Mockito.verify(categoryService).getById(ID);
        Mockito.verifyNoMoreInteractions(categoryService);
    }

    @Test
    void saveCategory() throws Exception {
        //GIVEN
        Category category = DateGeneratorForTest.generateCategory();
        Mockito.doNothing().when(categoryService).save(category);

        //WHEN
        mockMvc.perform(post("/category/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(category)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.title").value(category.getTitle()));
        //THEN

        Mockito.verify(categoryService).save(any());
        Mockito.verifyNoMoreInteractions(categoryService);
    }

    @Test
    void updateCategory() throws Exception{
        //GIVEN
        Category category = DateGeneratorForTest.generateCategory();
        Mockito.doNothing().when(categoryService).save(category);

        //WHEN
        mockMvc.perform(put("/category/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.title").value(category.getTitle()));
        //THEN

        Mockito.verify(categoryService).save(any());
        Mockito.verifyNoMoreInteractions(categoryService);
    }

    @Test
    void deleteCategory() throws Exception{
        //GIVEN
        Category category = DateGeneratorForTest.generateCategory();
        Mockito.when(categoryService.getById(ID)).thenReturn(category);
        Mockito.doNothing().when(categoryService).delete(ID);

        //WHEN
        mockMvc.perform(delete("/category/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        //THEN

        Mockito.verify(categoryService).delete(ID);
        Mockito.verify(categoryService).getById(ID);
        Mockito.verifyNoMoreInteractions(categoryService);
    }

    @Test
    void getAllCategory() throws Exception{
        //GIVEN
        List<Category> categories = DateGeneratorForTest.generateCategoryList(5);
        Mockito.when(categoryService.getAll()).thenReturn(categories);

        //WHEN
        mockMvc.perform(get("/category/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].id").value(categories.get(0).getId()))
                .andExpect(jsonPath("$[1].id").value(categories.get(1).getId()))
                .andExpect(jsonPath("$[2].id").value(categories.get(2).getId()))
                .andExpect(jsonPath("$[3].id").value(categories.get(3).getId()))
                .andExpect(jsonPath("$[4].id").value(categories.get(4).getId()));

        //THEN
        Mockito.verify(categoryService).getAll();
        Mockito.verifyNoMoreInteractions(categoryService);
    }
}