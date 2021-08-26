package com.andrey;

import com.andrey.datatest.DateGeneratorForTest;
import com.andrey.filter.Filter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import javax.servlet.http.HttpServletRequest;
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
    private BaseRepository<Operation> repository;

    @Mock
    HttpServletRequest requestMethod;


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
        Mockito.doNothing().when(repository).add(any());
        Mockito.when(requestMethod.getMethod()).thenReturn("POST");

        //WHEN
        operationService.save(operation);



        //THEN
        Mockito.verify(repository).add(any());
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
                operation.setAccount_id_from(1l);
                operationsTest.add(operation);
            }else {
                operation.setAccount_id_from(2l);
            }
            i++;
        }
        Mockito.when(operationRepository.findAllByFilter(any(Filter.class))).thenReturn(operationsTest);

        //WHEN
        List<Operation> operationList = operationService.getAllByFilter(filter);
        log.info(" Operation list = " + operationList);

        //THEN
        assertThat(operationList)
                .isNotEmpty()
                .isEqualTo(operationsTest);



        Mockito.verify(operationRepository).findAllByFilter(any(Filter.class));
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
                operation.setAccount_id_from(1l);
                operationsTest.add(operation);
            }else {
                operation.setAccount_id_from(2l);
            }
            i++;
        }
        Mockito.when(operationRepository.findAllByFilter(any(Filter.class))).thenReturn(operationsTest);
        log.info("Operation test list = " + operationsTest);

        //WHEN
        double sum = operationService.getFilterSum(filter);
        log.info(" Sum = " + sum);

        //THEN
        assertThat(sum)
                .isNotNull()
                .isExactlyInstanceOf(Double.class);

        assertThat(sum).isEqualTo(400);



        Mockito.verify(operationRepository).findAllByFilter(any(Filter.class));
        Mockito.verifyNoMoreInteractions(operationRepository);
    }
}