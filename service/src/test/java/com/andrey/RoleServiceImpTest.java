package com.andrey;

import com.andrey.datatest.DateGeneratorForTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static com.andrey.datatest.DateGeneratorForTest.ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@Slf4j
@ExtendWith(MockitoExtension.class)
class RoleServiceImpTest {

    @InjectMocks
    private RoleServiceImp roleService;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private JpaRepository<Role, Long> repository ;


    @Test
    void getById() {
        //GIVEN
        Role role = DateGeneratorForTest.generateRole();
        Mockito.when(repository.getById(ID)).thenReturn(role);

        //WHEN
        Role fetchedRole = roleService.getById(ID);
        log.info("Role = " + fetchedRole);

        //THEN
        assertThat(fetchedRole)
                .isNotNull()
                .isExactlyInstanceOf(Role.class);

        assertThat(fetchedRole).isEqualTo(role);

        Mockito.verify(repository).getById(ID);
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void save() {

        //GIVEN
        Role role = DateGeneratorForTest.generateRole();
        Mockito.when(repository.save(role)).thenReturn(role);

        //WHEN
        roleService.save(role);



        //THEN
        Mockito.verify(repository).save(role);
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void delete() {
        //GIVEN
        Role role = DateGeneratorForTest.generateRole();
        Mockito.doNothing().when(repository).deleteById(any(Long.class));

        //WHEN
        roleService.delete(ID);



        //THEN
        Mockito.verify(repository).deleteById(any(Long.class));
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void getAll() {

        //GIVEN
        List<Role> roles = DateGeneratorForTest.generateRoleList(3);
        Mockito.when(repository.findAll()).thenReturn(roles);

        //WHEN
        List<Role> roleList = roleService.getAll();
        log.info("Role list = " + roleList);


        //THEN
        assertThat(roleList)
                .isNotEmpty()
                .isEqualTo(roles);

        Mockito.verify(repository).findAll();
        Mockito.verifyNoMoreInteractions(repository);
    }
}