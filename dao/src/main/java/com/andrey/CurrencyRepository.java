package com.andrey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Access to CRUD methods.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

public interface CurrencyRepository extends JpaRepository<Currency, Long>, JpaSpecificationExecutor<Account> {
}
