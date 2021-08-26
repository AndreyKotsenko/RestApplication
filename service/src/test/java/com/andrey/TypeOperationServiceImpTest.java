package com.andrey;

import com.andrey.datatest.DateGeneratorForTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.andrey.datatest.DateGeneratorForTest.ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@Slf4j
@ExtendWith(MockitoExtension.class)
class TypeOperationServiceImpTest {

    @InjectMocks
    private TypeOperationServiceImp typeOperationService;

    @Mock
    private TypeOperationRepository typeOperationRepository;

    @Mock
    private BaseRepository<TypeOperation> repository;

    @Mock
    HttpServletRequest requestMethod;

    @Test
    void getById() {
        //GIVEN
        TypeOperation typeOperation = DateGeneratorForTest.generateTypeOperation();
        Mockito.when(repository.getById(ID)).thenReturn(typeOperation);

        //WHEN
        TypeOperation fetchedTypeOperation = typeOperationService.getById(ID);
        log.info("Type operation  = " + fetchedTypeOperation);

        //THEN
        assertThat(fetchedTypeOperation)
                .isNotNull()
                .isExactlyInstanceOf(TypeOperation.class);

        assertThat(fetchedTypeOperation).isEqualTo(typeOperation);

        Mockito.verify(repository).getById(ID);
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void save() {
        //GIVEN
        TypeOperation typeOperation = DateGeneratorForTest.generateTypeOperation();
        Mockito.doNothing().when(repository).add(any());
        Mockito.when(requestMethod.getMethod()).thenReturn("POST");

        //WHEN
        typeOperationService.save(typeOperation);



        //THEN
        Mockito.verify(repository).add(any());
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void delete() {
        //GIVEN
        TypeOperation typeOperation = DateGeneratorForTest.generateTypeOperation();
        Mockito.doNothing().when(repository).deleteById(any(Long.class));

        //WHEN
        typeOperationService.delete(ID);



        //THEN
        Mockito.verify(repository).deleteById(any(Long.class));
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void getAll() {
        //GIVEN
        List<TypeOperation> typeOperations = DateGeneratorForTest.generateTypeOperationList(3);
        Mockito.when(repository.findAll()).thenReturn(typeOperations);

        //WHEN
        List<TypeOperation> typeOperationList = typeOperationService.getAll();
        log.info("Type operation list = " + typeOperationList);


        //THEN
        assertThat(typeOperationList)
                .isNotEmpty()
                .isEqualTo(typeOperations);

        Mockito.verify(repository).findAll();
        Mockito.verifyNoMoreInteractions(repository);
    }
}