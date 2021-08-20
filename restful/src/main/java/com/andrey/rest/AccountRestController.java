package com.andrey.rest;

import com.andrey.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Rest for account.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@Slf4j
@RestController
@RequestMapping("/account/")
public class AccountRestController {

    @Autowired
    private AccountServiceImp accountService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> getAccount(@PathVariable("id") Long accountId){
        if(accountId == null){
            return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
        }



        Account account = this.accountService.getById(accountId);

        if(account == null){
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Account>(account, HttpStatus.OK);

    }

    // for balance by date
    @RequestMapping(value = "balance/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getBalanceByDate(@RequestParam(value = "date", required = false) Date date, @PathVariable("id") Long id){

        if(date == null){
            date = new Date();
        }
        if( id == null){
            return new ResponseEntity<Double>(HttpStatus.BAD_REQUEST);
        }

        Double balance = accountService.getBalanceByDate(id, date);
        log.info("balance првфыаыав = " + balance);

        if(balance == null){
            return new ResponseEntity<Double>(HttpStatus.NOT_FOUND);
        }


        balance = balance/100;

        log.info("balance = " + balance);
        return new ResponseEntity<Double>(balance, HttpStatus.OK);

    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> saveAccount(@RequestBody Account account) {
        HttpHeaders headers = new HttpHeaders();

        if (account == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.accountService.save(account);
        return new ResponseEntity<>(account, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> updateAccount(@RequestBody Account account, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (account == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.accountService.save(account);

        return new ResponseEntity<>(account, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> deleteAccount(@PathVariable("id") Long id) {
        Account account = this.accountService.getById(id);

        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.accountService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Account>> getAllAccount(Authentication authentication) {

        List<Account> accounts = new LinkedList<>();

        if(authentication.getAuthorities().toString().equals("[ROLE_USER]")){
            accounts = this.accountService.getAllByUser(authentication.getName());
        }else{
            accounts = this.accountService.getAll();
        }


        if (accounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

}
