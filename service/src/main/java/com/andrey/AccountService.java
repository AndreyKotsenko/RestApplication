package com.andrey;

import java.util.Date;
import java.util.List;

/**
 * Simple interface for service.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

public interface AccountService {

    Double getBalanceByDate(Long id, Date date);

    List<Account> getAllByUser(String mobileNumber);
}
