package com.andrey.rest;


import com.andrey.User;
import com.andrey.UserServiceImp;
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
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@WebMvcTest({UserRestController.class})
@RunWith(SpringRunner.class)
@WithMockUser(username = "068 980 34 56", password = "pass", roles = "ADMIN")
class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImp userService;



    @Test
    void getUser() throws Exception{
        //GIVEN
        User user = DateGeneratorForTest.generateUser();
        Mockito.when(userService.getById(ID)).thenReturn(user);

        //WHEN
        mockMvc.perform(get("/users/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.mobileNumber").value(user.getMobileNumber()));

        //THEN
        Mockito.verify(userService).getById(ID);
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    void saveUser() throws Exception{
        //GIVEN
        User user = DateGeneratorForTest.generateUser();
        Mockito.doNothing().when(userService).save(user);

        //WHEN
        mockMvc.perform(post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.mobileNumber").value(user.getMobileNumber()));
        //THEN

        Mockito.verify(userService).save(any());
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    void updateUser() throws Exception{
        //GIVEN
        User user = DateGeneratorForTest.generateUser();
        Mockito.doNothing().when(userService).save(user);

        //WHEN
        mockMvc.perform(put("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.mobileNumber").value(user.getMobileNumber()));
        //THEN

        Mockito.verify(userService).save(any());
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    void deleteUser() throws Exception{
        //GIVEN
        User user = DateGeneratorForTest.generateUser();
        Mockito.when(userService.getById(ID)).thenReturn(user);
        Mockito.doNothing().when(userService).delete(ID);

        //WHEN
        mockMvc.perform(delete("/users/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        //THEN

        Mockito.verify(userService).delete(ID);
        Mockito.verify(userService).getById(ID);
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    void getAllUser() throws Exception{
        //GIVEN
        List<User> users = DateGeneratorForTest.generateUserList(5);
        Mockito.when(userService.getAll()).thenReturn(users);

        //WHEN
        mockMvc.perform(get("/users/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].id").value(users.get(0).getId()))
                .andExpect(jsonPath("$[1].id").value(users.get(1).getId()))
                .andExpect(jsonPath("$[2].id").value(users.get(2).getId()))
                .andExpect(jsonPath("$[3].id").value(users.get(3).getId()))
                .andExpect(jsonPath("$[4].id").value(users.get(4).getId()));

        //THEN
        Mockito.verify(userService).getAll();
        Mockito.verifyNoMoreInteractions(userService);
    }
}