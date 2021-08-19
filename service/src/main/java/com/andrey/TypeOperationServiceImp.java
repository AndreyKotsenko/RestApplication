package com.andrey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service for type operation.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@Service
public class TypeOperationServiceImp extends BaseService<TypeOperation> implements TypeOperationService{

    @Autowired
    TypeOperationRepository typeOperationRepository;


}
