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
@WebMvcTest({GroupCategoryRestController.class})
@RunWith(SpringRunner.class)
@WithMockUser(username = "068 980 34 56", password = "pass", roles = "ADMIN")
class GroupCategoryRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupCategoryServiceImp groupCategoryService;

    @MockBean
    private UserServiceImp userService;

    @Test
    void getGroupCategory() throws Exception{
        //GIVEN
        GroupCategory groupCategory = DateGeneratorForTest.generateGroupCategory();
        Mockito.when(groupCategoryService.getById(ID)).thenReturn(groupCategory);

        //WHEN
        mockMvc.perform(get("/groupCategory/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.title").value(groupCategory.getTitle()));

        //THEN
        Mockito.verify(groupCategoryService).getById(ID);
        Mockito.verifyNoMoreInteractions(groupCategoryService);
    }

    @Test
    void saveGroupCategory() throws Exception{
        //GIVEN
        GroupCategory groupCategory = DateGeneratorForTest.generateGroupCategory();
        Mockito.doNothing().when(groupCategoryService).save(groupCategory);

        //WHEN
        mockMvc.perform(post("/groupCategory/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(groupCategory)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.title").value(groupCategory.getTitle()));
        //THEN

        Mockito.verify(groupCategoryService).save(any());
        Mockito.verifyNoMoreInteractions(groupCategoryService);
    }

    @Test
    void updateGroupCategory() throws Exception{
        //GIVEN
        GroupCategory groupCategory = DateGeneratorForTest.generateGroupCategory();
        Mockito.doNothing().when(groupCategoryService).save(groupCategory);

        //WHEN
        mockMvc.perform(put("/groupCategory/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(groupCategory)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.title").value(groupCategory.getTitle()));
        //THEN

        Mockito.verify(groupCategoryService).save(any());
        Mockito.verifyNoMoreInteractions(groupCategoryService);
    }

    @Test
    void deleteGroupCategory() throws Exception{
        //GIVEN
        GroupCategory groupCategory = DateGeneratorForTest.generateGroupCategory();
        Mockito.when(groupCategoryService.getById(ID)).thenReturn(groupCategory);
        Mockito.doNothing().when(groupCategoryService).delete(ID);

        //WHEN
        mockMvc.perform(delete("/groupCategory/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        //THEN

        Mockito.verify(groupCategoryService).delete(ID);
        Mockito.verify(groupCategoryService).getById(ID);
        Mockito.verifyNoMoreInteractions(groupCategoryService);
    }

    @Test
    void getAllGroupCategory() throws Exception{
        //GIVEN
        List<GroupCategory> groupCategories = DateGeneratorForTest.generateGroupCategoryList(5);
        Mockito.when(groupCategoryService.getAll()).thenReturn(groupCategories);

        //WHEN
        mockMvc.perform(get("/groupCategory/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].id").value(groupCategories.get(0).getId()))
                .andExpect(jsonPath("$[1].id").value(groupCategories.get(1).getId()))
                .andExpect(jsonPath("$[2].id").value(groupCategories.get(2).getId()))
                .andExpect(jsonPath("$[3].id").value(groupCategories.get(3).getId()))
                .andExpect(jsonPath("$[4].id").value(groupCategories.get(4).getId()));

        //THEN
        Mockito.verify(groupCategoryService).getAll();
        Mockito.verifyNoMoreInteractions(groupCategoryService);
    }
}