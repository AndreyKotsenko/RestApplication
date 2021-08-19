package com.andrey;

import com.andrey.filter.Filter;
import java.util.List;

/**
 * Simple interface for service.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

public interface OperationService {


    List<Operation> getAllByFilter(Filter filter);

    Double getFilterSum(Filter filter);

}
