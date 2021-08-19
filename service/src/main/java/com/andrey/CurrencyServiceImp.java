package com.andrey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for currency.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@Service
public class CurrencyServiceImp extends BaseService<Currency> implements CurrencyService{

    @Autowired
    CurrencyRepository currencyRepository;



}
