package com.andrey;

import com.andrey.datatest.DateGeneratorForTest;
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

@ExtendWith(MockitoExtension.class)
class GroupCategoryServiceImpTest {

    @InjectMocks
    private GroupCategoryServiceImp groupCategoryService;

    @Mock
    private GroupCategoryRepository groupCategoryRepository;

    @Mock
    private JpaRepository<GroupCategory, Long> repository ;


    @Test
    void getById() {

        //GIVEN
        GroupCategory groupCategory = DateGeneratorForTest.generateGroupCategory();
        Mockito.when(repository.getById(ID)).thenReturn(groupCategory);

        //WHEN
        GroupCategory fetchedGroupCategory = groupCategoryService.getById(ID);
        System.out.println(fetchedGroupCategory);

        //THEN
        assertThat(fetchedGroupCategory)
                .isNotNull()
                .isExactlyInstanceOf(GroupCategory.class);

        assertThat(fetchedGroupCategory).isEqualTo(groupCategory);

        Mockito.verify(repository).getById(ID);
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void save() {

        //GIVEN
        GroupCategory groupCategory = DateGeneratorForTest.generateGroupCategory();
        Mockito.when(repository.save(groupCategory)).thenReturn(groupCategory);

        //WHEN
        groupCategoryService.save(groupCategory);



        //THEN
        Mockito.verify(repository).save(groupCategory);
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void delete() {

        //GIVEN
        GroupCategory groupCategory = DateGeneratorForTest.generateGroupCategory();
        Mockito.doNothing().when(repository).deleteById(any(Long.class));

        //WHEN
        groupCategoryService.delete(ID);



        //THEN
        Mockito.verify(repository).deleteById(any(Long.class));
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void getAll() {

        //GIVEN
        List<GroupCategory> groupCategories = DateGeneratorForTest.generateGroupCategoryList(3);
        Mockito.when(repository.findAll()).thenReturn(groupCategories);

        //WHEN
        List<GroupCategory> groupCategoryList = groupCategoryService.getAll();
        System.out.println(groupCategoryList);


        //THEN
        assertThat(groupCategoryList)
                .isNotEmpty()
                .isEqualTo(groupCategories);

        Mockito.verify(repository).findAll();
        Mockito.verifyNoMoreInteractions(repository);
    }
}