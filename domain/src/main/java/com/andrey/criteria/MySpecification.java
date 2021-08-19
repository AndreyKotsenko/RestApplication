package com.andrey.criteria;

import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * My specification .
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

public class MySpecification<T> implements Specification<T> {

    private List<SearchCriteria> list;

    public MySpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        //create a new predicate list
        List<Predicate> predicates = new ArrayList<>();

        //add add criteria to predicates
        for (SearchCriteria criteria : list) {
            if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                predicates.add(builder.greaterThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                predicates.add(builder.lessThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
                predicates.add(builder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), (criteria.getValue().toString())));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                predicates.add(builder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                predicates.add(builder.notEqual(
                        root.get(criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(builder.equal(
                        root.get(criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_START)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase()));
            } else if (criteria.getOperation().equals(SearchOperation.IN)) {
                predicates.add(builder.in(root.get(criteria.getKey())).value(criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.NOT_IN)) {
                predicates.add(builder.not(root.get(criteria.getKey())).in(criteria.getValue()));
            }else if(criteria.getOperation().equals(SearchOperation.JOIN_EQUAL )){
                predicates.add(builder.equal(
                        root.join(criteria.getKey().split("/")[0]).join(criteria.getKey().split("/")[1]).get("id"), criteria.getValue()));
            }else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL_FOR_DATE)) {
                predicates.add(builder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), (Date)(criteria.getValue())));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL_FOR_DATE)) {
                predicates.add(builder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), (Date)criteria.getValue()));
            }else if (criteria.getOperation().equals(SearchOperation.OR_EQUAL)) {
                predicates.add(builder.or(
                        builder.equal(
                                root.get(criteria.getKey().split("/")[0]), criteria.getValue()),
                        builder.equal(
                                root.get(criteria.getKey().split("/")[1]), criteria.getValue())
                ));
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
