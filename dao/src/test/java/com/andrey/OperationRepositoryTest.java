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
class OperationRepositoryTest {

    @Autowired
    private OperationRepository operationRepository;

    private Comparator<Operation> COMPARATOR = (o1, o2) -> (o1.getId() == o2.getId()) ? 0 : 1;


    @Test
    @Rollback(value = false)
    void getById() {

        Operation operation = DateGeneratorForTest.generateOperationWithoutForeignKey();
        operation.setId((long)1);

        operationRepository.save(operation);


        Operation operationOut = operationRepository.getById((long)1);


        assertNotNull(operationOut);
        assertEquals(operationOut.getId(), operation.getId());

    }

    @Test
    @Rollback(value = false)
    void save() {
        Operation operation = DateGeneratorForTest.generateOperationWithoutForeignKey();
        operation.setId((long)1);
        operationRepository.save(operation);


        Operation operationOut = operationRepository.getById((long)1);


        assertNotNull(operationOut);
        assertEquals(operationOut.getId(), operation.getId());

    }

    @Test
    @Rollback(value = false)
    void deleteById() {

        Operation operation = DateGeneratorForTest.generateOperationWithoutForeignKey();
        operation.setId((long)1);
        operationRepository.save(operation);

        Operation operationOut = operationRepository .getById((long)1);

        assertNotNull(operationOut);

        operationOut = null;
        operationRepository.deleteById((long)1);

        Optional<Operation> optionalOperation = operationRepository.findById((long)1);

        if(optionalOperation.isPresent()){
            operationOut = optionalOperation.get();
        }

        Assertions.assertThat(operationOut).isNull();
    }

    @Test
    @Rollback(value = false)
    void findAll() {
        List<Operation> operations = DateGeneratorForTest.generateOperationListWithoutForeignKey(5);

        int i = 0;
        for(Operation operation : operations){
            i++;

            operation.setId((long)i);
            operationRepository.save(operation);

        }

        List<Operation> operationList = operationRepository.findAll();


        assertNotNull(operationList);
        Assertions.assertThat(operationList.size()).isEqualTo(5);
        Assertions.assertThat(operations)
                .usingElementComparator(COMPARATOR)
                .isEqualTo(operationList);



    }

}