package com.andrey.datatest;

import com.andrey.*;
import com.andrey.Currency;
import com.andrey.filter.Filter;
import java.util.Date;
import java.util.*;


/**
 * Object for generate date for tests.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

public class DateGeneratorForTest {

    public static final Long ID = new Random().nextLong();
    public static final Date DATE = new Date();

    //Generate for Account
    public static Account generateAccount(){
        return new Account(ID, "Test", (long)new Random().nextInt(100)*100 );
    }

    public static List<Account> generateAccountList(int count){
        List<Account> accounts = new LinkedList<>();

        for(int i = 0; i < count; i++){
            accounts.add(new Account((long)i, "something", new Random().nextLong()*100));
        }

        return accounts;
    }


    //Generate for Category
    public static Category generateCategory(){
        return new Category(ID, "Category generate");
    }

    public static List<Category> generateCategoryList(int count){
        List<Category> categories = new LinkedList<>();

        for(int i = 0; i < count; i++){
            categories.add(new Category((long)i, "Category generate"));
        }

        return categories;
    }


    //Generate for Currency
    public static com.andrey.Currency generateCurrency(){
        return new com.andrey.Currency(ID, "Currency generate");
    }

    public static List<com.andrey.Currency> generateCurrencyList(int count){
        List<com.andrey.Currency> currencies = new LinkedList<>();

        for(int i = 0; i < count; i++){
            currencies.add(new Currency((long)i, "Currency generate"));
        }

        return currencies;
    }


    //Generate for GroupCategory
    public static GroupCategory generateGroupCategory(){
        return new GroupCategory(ID, "Group category generate");
    }

    public static List<GroupCategory> generateGroupCategoryList(int count){
        List<GroupCategory> groupCategories = new LinkedList<>();

        for(int i = 0; i < count; i++){
            groupCategories.add(new GroupCategory((long)i, "Group category generate"));
        }

        return groupCategories;
    }


    //Generate for Operation
    public static Operation generateOperation(){
        return new Operation(ID, DATE, "descr", 100l, (long)1,
                1l, 1l, 1l);
    }

    public static List<Operation> generateOperationList(int count){
        List<Operation> operations = new LinkedList<>();

        for( int i = 0; i < count; i++){
            operations.add(new Operation((long)i, DATE, "descr", 100l, 1l,
                    1l, ID, 1l));
        }

        return operations;

    }

    public static Operation generateOperationWithoutForeignKey(){
        return new Operation(ID, DATE, "descr", 100l);
    }

    public static List<Operation> generateOperationListWithoutForeignKey(int count){
        List<Operation> operations = new LinkedList<>();

        for( int i = 0; i < count; i++){
            operations.add(new Operation((long)i, DATE, "descr", 100l));
        }

        return operations;

    }


    //Generate for Role
    public static Role generateRole(){
        return new Role(ID, "generate role");
    }

    public static List<Role> generateRoleList(int count){
        List<Role> roles = new LinkedList<>();

        for(int i = 0; i < count; i++){
            roles.add(new Role( (long)i, "generate role"));
        }

        return roles;
    }

    //Generate for Type operation
    public static TypeOperation generateTypeOperation(){
        return new TypeOperation(ID, "generate type operation");
    }

    public static List<TypeOperation> generateTypeOperationList(int count){
        List<TypeOperation> typeOperations = new LinkedList<>();

        for(int i = 0; i < count; i++){
            typeOperations.add(new TypeOperation( (long)i, "generate type operation"));
        }

        return typeOperations;
    }


    //Generate for User
    public static User generateUser(){
        return new User(ID, "generate name", "generate last name", "generate number");
    }

    public static List<User> generateUserList(int count){
        List<User> users = new LinkedList<>();

        for(int i = 0; i < count; i++){
            users.add(new User( (long)i, "generate name", "generate last name", "generate number"));
            users.get(i).setPassword("1111");
        }

        return users;
    }


    //Generate for Filter
    public static Filter generateFilter(){
        return new Filter();
    }
}
