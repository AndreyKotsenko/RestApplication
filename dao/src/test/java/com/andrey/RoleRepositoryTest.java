package com.andrey;

import com.andrey.datatest.DateGeneratorForTest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RunWith(SpringRunner.class)
@MybatisTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    private Comparator<Role> COMPARATOR = (o1, o2) -> (o1.getId() == o2.getId()) ? 0 : 1;


    @Test
    @Rollback(value = false)
    void getById() {

        Role role = DateGeneratorForTest.generateRole();
        role.setId((long)1);

        roleRepository.add(role);


        Role roleOut = roleRepository.getById((long)1);


        assertNotNull(roleOut);
        assertEquals(roleOut.getId(), role.getId());

    }

    @Test
    @Rollback(value = false)
    void save() {
        Role role = DateGeneratorForTest.generateRole();
        role.setId((long)1);
        roleRepository.add(role);


        Role roleOut = roleRepository.getById((long)1);


        assertNotNull(roleOut);
        assertEquals(roleOut.getId(), role.getId());

    }

    @Test
    @Rollback(value = false)
    void deleteById() {

        Role role = DateGeneratorForTest.generateRole();
        role.setId((long)1);
        roleRepository.add(role);

        Role roleOut = roleRepository.getById((long)1);

        assertNotNull(roleOut);

        roleOut = null;
        roleRepository.deleteById((long)1);

        Optional<Role> optionalRole = Optional.ofNullable(roleRepository.getById((long) 1));

        if(optionalRole.isPresent()){
            roleOut = optionalRole.get();
        }

        Assertions.assertThat(roleOut).isNull();
    }

    @Test
    @Rollback(value = false)
    void findAll() {
        List<Role> roles = DateGeneratorForTest.generateRoleList(5);

        int i = 0;
        for(Role role : roles){
            i++;

            role.setId((long)i);
            roleRepository.add(role);

        }

        List<Role> roleList = roleRepository.findAll();


        assertNotNull(roleList);
        Assertions.assertThat(roleList.size()).isEqualTo(5);
        Assertions.assertThat(roles)
                .usingElementComparator(COMPARATOR)
                .isEqualTo(roleList);



    }

}