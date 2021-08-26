package com.andrey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for group rates.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@Service
public class RatesServiceImp extends BaseService<Rates> implements RatesService{

    @Autowired
    RatesRepository ratesRepository;
}
