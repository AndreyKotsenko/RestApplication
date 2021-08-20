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
class GroupCategoryRepositoryTest {

    @Autowired
    private GroupCategoryRepository groupCategoryRepository;

    private Comparator<GroupCategory> COMPARATOR = (o1, o2) -> (o1.getId() == o2.getId()) ? 0 : 1;


    @Test
    @Rollback(value = false)
    void getById() {

        GroupCategory groupCategory = DateGeneratorForTest.generateGroupCategory();
        groupCategory.setId((long)1);
        groupCategoryRepository.add(groupCategory);


        GroupCategory groupCategoryOut = groupCategoryRepository.getById((long)1);


        assertNotNull(groupCategoryOut);
        assertEquals(groupCategoryOut.getId(), groupCategoryOut.getId());

    }

    @Test
    @Rollback(value = false)
    void save() {
        GroupCategory groupCategory = DateGeneratorForTest.generateGroupCategory();
        groupCategory.setId((long)1);
        groupCategoryRepository.add(groupCategory);


        GroupCategory groupCategoryOut = groupCategoryRepository.getById((long)1);


        assertNotNull(groupCategoryOut);
        assertEquals(groupCategoryOut.getId(), groupCategory.getId());

    }

    @Test
    @Rollback(value = false)
    void deleteById() {

        GroupCategory groupCategory = DateGeneratorForTest.generateGroupCategory();
        groupCategory.setId((long)1);
        groupCategoryRepository.add(groupCategory);

        GroupCategory groupCategoryOut = groupCategoryRepository .getById((long)1);

        assertNotNull(groupCategoryOut);

        groupCategoryOut = null;
        groupCategoryRepository.deleteById((long)1);

        Optional<GroupCategory> optionalGroupCategory = Optional.ofNullable(groupCategoryRepository.getById((long) 1));

        if(optionalGroupCategory.isPresent()){
            groupCategoryOut = optionalGroupCategory.get();
        }

        Assertions.assertThat(groupCategoryOut).isNull();
    }

    @Test
    @Rollback(value = false)
    void findAll() {
        List<GroupCategory> groupCategories = DateGeneratorForTest.generateGroupCategoryList(5);

        int i = 0;
        for(GroupCategory groupCategory : groupCategories){
            i++;

            groupCategory.setId((long)i);
            groupCategoryRepository.add(groupCategory);

        }

        List<GroupCategory> groupCategoryList = groupCategoryRepository.findAll();


        assertNotNull(groupCategoryList);
        Assertions.assertThat(groupCategoryList.size()).isEqualTo(5);
        Assertions.assertThat(groupCategories)
                .usingElementComparator(COMPARATOR)
                .isEqualTo(groupCategoryList);



    }
}