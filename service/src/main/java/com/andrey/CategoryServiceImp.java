package com.andrey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for category.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@Service
public class CategoryServiceImp extends BaseService<Category> implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

}
