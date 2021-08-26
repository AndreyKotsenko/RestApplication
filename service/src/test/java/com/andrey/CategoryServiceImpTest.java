package com.andrey;

import com.andrey.datatest.DateGeneratorForTest;
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

@ExtendWith(MockitoExtension.class)
class CategoryServiceImpTest {

    @InjectMocks
    private CategoryServiceImp categoryService;

    @Mock
    private BaseRepository<Category> repository;

    @Mock
    HttpServletRequest requestMethod;


    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void getById() {
        //GIVEN
        Category category = DateGeneratorForTest.generateCategory();
        Mockito.when(repository.getById(ID)).thenReturn(category);

        //WHEN
        Category fetchedCategory = categoryService.getById(ID);
        System.out.println(fetchedCategory);

        //THEN
        assertThat(fetchedCategory)
                .isNotNull()
                .isExactlyInstanceOf(Category.class);

        assertThat(fetchedCategory).isEqualTo(category);

        Mockito.verify(repository).getById(ID);
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void save() {
        //GIVEN
        Category category = DateGeneratorForTest.generateCategory();
        Mockito.doNothing().when(repository).add(any());
        Mockito.when(requestMethod.getMethod()).thenReturn("POST");

        //WHEN
        categoryService.save(category);



        //THEN
        Mockito.verify(repository).add(any());
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void delete() {
        //GIVEN
        Category category = DateGeneratorForTest.generateCategory();
        Mockito.doNothing().when(repository).deleteById(any(Long.class));

        //WHEN
        categoryService.delete(ID);



        //THEN
        Mockito.verify(repository).deleteById(any(Long.class));
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void getAll() {
        //GIVEN
        List<Category> categories = DateGeneratorForTest.generateCategoryList(3);
        Mockito.when(repository.findAll()).thenReturn(categories);

        //WHEN
        List<Category> categoryList = categoryService.getAll();
        System.out.println(categoryList);


        //THEN
        assertThat(categoryList)
                .isNotEmpty()
                .isEqualTo(categories);

        Mockito.verify(repository).findAll();
        Mockito.verifyNoMoreInteractions(repository);
    }
}