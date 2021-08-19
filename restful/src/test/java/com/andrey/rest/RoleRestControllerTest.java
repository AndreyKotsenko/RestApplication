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
@WebMvcTest({RoleRestController.class})
@RunWith(SpringRunner.class)
@WithMockUser(username = "068 980 34 56", password = "pass", roles = "ADMIN")
class RoleRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleServiceImp roleService;

    @MockBean
    private UserServiceImp userService;

    @Test
    void getRole() throws Exception{
        //GIVEN
        Role role = DateGeneratorForTest.generateRole();
        Mockito.when(roleService.getById(ID)).thenReturn(role);

        //WHEN
        mockMvc.perform(get("/role/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.type").value(role.getType()));

        //THEN
        Mockito.verify(roleService).getById(ID);
        Mockito.verifyNoMoreInteractions(roleService);

    }

    @Test
    void saveRole() throws Exception{
        //GIVEN
        Role role = DateGeneratorForTest.generateRole();
        Mockito.doNothing().when(roleService).save(role);

        //WHEN
        mockMvc.perform(post("/role/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(role)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.type").value(role.getType()));
        //THEN

        Mockito.verify(roleService).save(any());
        Mockito.verifyNoMoreInteractions(roleService);
    }

    @Test
    void updateRole() throws Exception{
        //GIVEN
        Role role = DateGeneratorForTest.generateRole();
        Mockito.doNothing().when(roleService).save(role);

        //WHEN
        mockMvc.perform(put("/role/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(role)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.type").value(role.getType()));
        //THEN

        Mockito.verify(roleService).save(any());
        Mockito.verifyNoMoreInteractions(roleService);
    }

    @Test
    void deleteRole() throws Exception{
        //GIVEN
        Role role = DateGeneratorForTest.generateRole();
        Mockito.when(roleService.getById(ID)).thenReturn(role);
        Mockito.doNothing().when(roleService).delete(ID);

        //WHEN
        mockMvc.perform(delete("/role/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        //THEN

        Mockito.verify(roleService).delete(ID);
        Mockito.verify(roleService).getById(ID);
        Mockito.verifyNoMoreInteractions(roleService);
    }

    @Test
    void getAllRole() throws Exception{
        //GIVEN
        List<Role> roles = DateGeneratorForTest.generateRoleList(5);
        Mockito.when(roleService.getAll()).thenReturn(roles);

        //WHEN
        mockMvc.perform(get("/role/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].id").value(roles.get(0).getId()))
                .andExpect(jsonPath("$[1].id").value(roles.get(1).getId()))
                .andExpect(jsonPath("$[2].id").value(roles.get(2).getId()))
                .andExpect(jsonPath("$[3].id").value(roles.get(3).getId()))
                .andExpect(jsonPath("$[4].id").value(roles.get(4).getId()));

        //THEN
        Mockito.verify(roleService).getAll();
        Mockito.verifyNoMoreInteractions(roleService);
    }
}