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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RunWith(SpringRunner.class)
@MybatisTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Comparator<Category> COMPARATOR = (o1, o2) -> (o1.getId() == o2.getId()) ? 0 : 1;


    @Test
    @Rollback(value = false)
    void getById() {

        Category category = DateGeneratorForTest.generateCategory();
        category.setId((long)1);
        categoryRepository.add(category);


        Category categoryOut = categoryRepository.getById((long)1);


        assertNotNull(categoryOut);
        assertEquals(categoryOut.getId(), category.getId());

    }

    @Test
    @Rollback(value = false)
    void save() {
        Category category = DateGeneratorForTest.generateCategory();
        category.setId((long)1);
        categoryRepository.add(category);


        Category categoryOut = categoryRepository.getById((long)1);


        assertNotNull(categoryOut);
        assertEquals(categoryOut.getId(), category.getId());

    }

    @Test
    @Rollback(value = false)
    void deleteById() {

        Category category = DateGeneratorForTest.generateCategory();
        category.setId((long)1);
        categoryRepository.add(category);

        Category categoryOut = categoryRepository.getById((long)1);

        assertNotNull(categoryOut);

        categoryOut = null;
        categoryRepository.deleteById((long)1);

        Optional<Category> optionalCategory = Optional.ofNullable(categoryRepository.getById((long) 1));

        if(optionalCategory.isPresent()){
            categoryOut = optionalCategory.get();
        }

        Assertions.assertThat(categoryOut).isNull();
    }

    @Test
    @Rollback(value = false)
    void findAll() {
        List<Category> categories = DateGeneratorForTest.generateCategoryList(5);

        int i = 0;
        for(Category category : categories){
            i++;

            category.setId((long)i);
            categoryRepository.add(category);

        }

        List<Category> categoryList = categoryRepository.findAll();


        assertNotNull(categoryList);
        Assertions.assertThat(categoryList.size()).isEqualTo(5);
        Assertions.assertThat(categories)
                .usingElementComparator(COMPARATOR)
                .isEqualTo(categoryList);



    }

}