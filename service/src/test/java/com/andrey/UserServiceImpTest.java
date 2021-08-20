package com.andrey;

import com.andrey.datatest.DateGeneratorForTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.andrey.datatest.DateGeneratorForTest.ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@Slf4j
@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {

    @InjectMocks
    private UserServiceImp userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BaseRepository<User> repository;

    @Mock
    HttpServletRequest requestMethod;

    @Test
    void getById() {
        //GIVEN
        User user = DateGeneratorForTest.generateUser();
        Mockito.when(repository.getById(ID)).thenReturn(user);

        //WHEN
        User fetchedUser = userService.getById(ID);
        log.info("User = " + fetchedUser);

        //THEN
        assertThat(fetchedUser)
                .isNotNull()
                .isExactlyInstanceOf(User.class);

        assertThat(fetchedUser).isEqualTo(user);

        Mockito.verify(repository).getById(ID);
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void save() {
        //GIVEN
        User user = DateGeneratorForTest.generateUser();
        Mockito.doNothing().when(repository).add(any());
        Mockito.when(requestMethod.getMethod()).thenReturn("POST");

        //WHEN
        userService.save(user);



        //THEN
        Mockito.verify(repository).add(any());
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void delete() {
        //GIVEN
        User user = DateGeneratorForTest.generateUser();
        Mockito.doNothing().when(repository).deleteById(any(Long.class));

        //WHEN
        userService.delete(ID);



        //THEN
        Mockito.verify(repository).deleteById(any(Long.class));
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void getAll() {
        //GIVEN
        List<User> users = DateGeneratorForTest.generateUserList(3);
        Mockito.when(repository.findAll()).thenReturn(users);

        //WHEN
        List<User> userList = userService.getAll();
        log.info("User list = " + userList);


        //THEN
        assertThat(userList)
                .isNotEmpty()
                .isEqualTo(users);

        Mockito.verify(repository).findAll();
        Mockito.verifyNoMoreInteractions(repository);
    }
}