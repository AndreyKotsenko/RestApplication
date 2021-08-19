package com.andrey;

import com.andrey.datatest.DateGeneratorForTest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private Comparator<User> COMPARATOR = (o1, o2) -> (o1.getId() == o2.getId()) ? 0 : 1;


    @Test
    @Rollback(value = false)
    void getById() {

        User user = DateGeneratorForTest.generateUser();
        user.setId((long)1);

        userRepository.save(user);


        User userOut = userRepository.getById((long)1);


        assertNotNull(userOut);
        assertEquals(userOut.getId(), user.getId());

    }

    @Test
    @Rollback(value = false)
    void save() {
        User user = DateGeneratorForTest.generateUser();
        user.setId((long)1);
        userRepository.save(user);


        User userOut = userRepository.getById((long)1);


        assertNotNull(userOut);
        assertEquals(userOut.getId(), user.getId());

    }

    @Test
    @Rollback(value = false)
    void deleteById() {

        User user = DateGeneratorForTest.generateUser();
        user.setId((long)1);
        userRepository.save(user);

        User userOut = userRepository.getById((long)1);

        assertNotNull(userOut);

        userOut = null;
        userRepository.deleteById((long)1);

        Optional<User> optionalUser = userRepository.findById((long)1);

        if(optionalUser.isPresent()){
            userOut = optionalUser.get();
        }

        Assertions.assertThat(userOut).isNull();
    }

    @Test
    @Rollback(value = false)
    void findAll() {
        List<User> users = DateGeneratorForTest.generateUserList(5);

        int i = 0;
        for(User user : users){
            i++;

            user.setId((long)i);
            userRepository.save(user);

        }

        List<User> userList = userRepository.findAll();


        assertNotNull(userList);
        Assertions.assertThat(userList.size()).isEqualTo(5);
        Assertions.assertThat(users)
                .usingElementComparator(COMPARATOR)
                .isEqualTo(userList);



    }

}