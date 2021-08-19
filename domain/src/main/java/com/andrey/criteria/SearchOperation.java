package com.andrey.criteria;

/**
 * List of operations.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

public enum SearchOperation {
    GREATER_THAN,
    LESS_THAN,
    GREATER_THAN_EQUAL,
    LESS_THAN_EQUAL,
    GREATER_THAN_EQUAL_FOR_DATE,
    LESS_THAN_EQUAL_FOR_DATE,
    NOT_EQUAL,
    EQUAL,
    MATCH,
    MATCH_START,
    MATCH_END,
    IN,
    NOT_IN,
    JOIN_EQUAL,
    OR_EQUAL

}
