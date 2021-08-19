package com.andrey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for role.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@Service
public class RoleServiceImp extends BaseService<Role> implements RoleService{

    @Autowired
    RoleRepository roleRepository;


}
