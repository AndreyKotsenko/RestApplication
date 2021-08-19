package com.andrey;

import com.andrey.datatest.DateGeneratorForTest;
import com.andrey.filter.Filter;
import com.andrey.criteria.MySpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LinkedList;
import java.util.List;

import static com.andrey.datatest.DateGeneratorForTest.ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@Slf4j
@ExtendWith(MockitoExtension.class)
class OperationServiceImpTest {

    @InjectMocks
    private OperationServiceImp operationService;

    @Mock
    private OperationRepository operationRepository;

    @Mock
    private JpaRepository<Operation, Long> repository ;


    @Test
    void getById() {

        //GIVEN
        Operation operation = DateGeneratorForTest.generateOperation();
        Mockito.when(repository.getById(ID)).thenReturn(operation);

        //WHEN
        Operation fetchedOperation = operationService.getById(ID);
        log.info(" Operation = " + fetchedOperation);

        //THEN
        assertThat(fetchedOperation)
                .isNotNull()
                .isExactlyInstanceOf(Operation.class);

        assertThat(fetchedOperation).isEqualTo(operation);

        Mockito.verify(repository).getById(ID);
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void save() {

        //GIVEN
        Operation operation = DateGeneratorForTest.generateOperation();
        Mockito.when(repository.save(operation)).thenReturn(operation);

        //WHEN
        operationService.save(operation);



        //THEN
        Mockito.verify(repository).save(operation);
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void delete() {

        //GIVEN
        Operation operation = DateGeneratorForTest.generateOperation();
        Mockito.doNothing().when(repository).deleteById(any(Long.class));

        //WHEN
        operationService.delete(ID);



        //THEN
        Mockito.verify(repository).deleteById(any(Long.class));
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void getAll() {

        //GIVEN
        List<Operation> operations = DateGeneratorForTest.generateOperationList(3);
        Mockito.when(repository.findAll()).thenReturn(operations);

        //WHEN
        List<Operation> operationList = operationService.getAll();
        log.info(" Operation list  = " + operationList);


        //THEN
        assertThat(operationList)
                .isNotEmpty()
                .isEqualTo(operations);

        Mockito.verify(repository).findAll();
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void getAllByFilter() {

        //GIVEN
        Filter filter = DateGeneratorForTest.generateFilter();
        filter.setAccount_id((long)1);


        List<Operation> operations = DateGeneratorForTest.generateOperationList(8);
        List<Operation> operationsTest = new LinkedList<>();
        int i = 0;
        for(Operation operation : operations){

            if(i%2 == 0){
                operation.setAccount_from(new Account((long)1, "On", 200l));
                operationsTest.add(operation);
            }else {
                operation.setAccount_from(new Account((long)2, "Off", 400l));
            }
            i++;
        }
        Mockito.when(operationRepository.findAll(any(MySpecification.class))).thenReturn(operationsTest);

        //WHEN
        List<Operation> operationList = operationService.getAllByFilter(filter);
        log.info(" Operation list = " + operationList);

        //THEN
        assertThat(operationList)
                .isNotEmpty()
                .isEqualTo(operationsTest);



        Mockito.verify(operationRepository).findAll(any(MySpecification.class));
        Mockito.verifyNoMoreInteractions(operationRepository);
    }

    @Test
    void getFilterSum() {

        //GIVEN
        Filter filter = DateGeneratorForTest.generateFilter();
        filter.setAccount_id((long)1);

        List<Operation> operations = DateGeneratorForTest.generateOperationList(8);
        List<Operation> operationsTest = new LinkedList<>();

        int i = 0;
        for(Operation operation : operations){
            if(i%2 == 0){
                operation.setAccount_from(new Account((long)1, "On", 200l));
                operationsTest.add(operation);
            }else {
                operation.setAccount_from(new Account((long)2, "Off", 400l));
            }
            i++;
        }
        Mockito.when(operationRepository.findAll(any(MySpecification.class))).thenReturn(operationsTest);
        log.info("Operation test list = " + operationsTest);

        //WHEN
        double sum = operationService.getFilterSum(filter);
        log.info(" Sum = " + sum);

        //THEN
        assertThat(sum)
                .isNotNull()
                .isExactlyInstanceOf(Double.class);

        assertThat(sum).isEqualTo(400);



        Mockito.verify(operationRepository).findAll(any(MySpecification.class));
        Mockito.verifyNoMoreInteractions(operationRepository);
    }
}