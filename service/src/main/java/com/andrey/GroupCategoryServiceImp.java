package com.andrey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for group category.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@Service
public class GroupCategoryServiceImp extends BaseService<GroupCategory> implements GroupCategoryService{

    @Autowired
    GroupCategoryRepository groupCategoryRepository;


}
