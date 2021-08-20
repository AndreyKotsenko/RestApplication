package com.andrey;

import com.andrey.datatest.DateGeneratorForTest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
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
class TypeOperationRepositoryTest {
    @Autowired
    private TypeOperationRepository typeOperationRepository;

    private Comparator<TypeOperation> COMPARATOR = (o1, o2) -> (o1.getId() == o2.getId()) ? 0 : 1;


    @Test
    @Rollback(value = false)
    void getById() {

        TypeOperation typeOperation = DateGeneratorForTest.generateTypeOperation();
        typeOperation.setId((long)1);

        typeOperationRepository.add(typeOperation);


        TypeOperation typeOperationOut = typeOperationRepository.getById((long)1);


        assertNotNull(typeOperationOut);
        assertEquals(typeOperationOut.getId(), typeOperation.getId());

    }

    @Test
    @Rollback(value = false)
    void save() {
        TypeOperation typeOperation = DateGeneratorForTest.generateTypeOperation();
        typeOperation.setId((long)1);
        typeOperationRepository.add(typeOperation);


        TypeOperation typeOperationOut = typeOperationRepository.getById((long)1);


        assertNotNull(typeOperationOut);
        assertEquals(typeOperationOut.getId(), typeOperation.getId());

    }

    @Test
    @Rollback(value = false)
    void deleteById() {

        TypeOperation typeOperation = DateGeneratorForTest.generateTypeOperation();
        typeOperation.setId((long)1);
        typeOperationRepository.add(typeOperation);

        TypeOperation typeOperationOut = typeOperationRepository.getById((long)1);

        assertNotNull(typeOperationOut);

        typeOperationOut = null;
        typeOperationRepository.deleteById((long)1);

        Optional<TypeOperation> optionalTypeOperation = Optional.ofNullable(typeOperationRepository.getById((long) 1));

        if(optionalTypeOperation.isPresent()){
            typeOperationOut = optionalTypeOperation.get();
        }

        Assertions.assertThat(typeOperationOut).isNull();
    }

    @Test
    @Rollback(value = false)
    void findAll() {
        List<TypeOperation> typeOperations = DateGeneratorForTest.generateTypeOperationList(5);

        int i = 0;
        for(TypeOperation typeOperation : typeOperations){
            i++;

            typeOperation.setId((long)i);
            typeOperationRepository.add(typeOperation);

        }

        List<TypeOperation> typeOperationList = typeOperationRepository.findAll();


        assertNotNull(typeOperationList);
        Assertions.assertThat(typeOperationList.size()).isEqualTo(5);
        Assertions.assertThat(typeOperations)
                .usingElementComparator(COMPARATOR)
                .isEqualTo(typeOperationList);



    }

}