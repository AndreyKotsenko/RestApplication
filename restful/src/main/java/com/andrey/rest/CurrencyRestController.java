package com.andrey.rest;

import com.andrey.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Rest for currency.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@RestController
@RequestMapping("/currency/")
public class CurrencyRestController {

    @Autowired
    private CurrencyServiceImp currencyService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Currency> getCurrency(@PathVariable("id") Long currencyId){
        if(currencyId == null){
            return new ResponseEntity<Currency>(HttpStatus.BAD_REQUEST);
        }

        Currency currency = this.currencyService.getById(currencyId);

        if(currency == null){
            return new ResponseEntity<Currency>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Currency>(currency, HttpStatus.OK);

    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Currency> saveCurrency(@RequestBody Currency currency) {
        HttpHeaders headers = new HttpHeaders();

        if (currency == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.currencyService.save(currency);
        return new ResponseEntity<>(currency, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Currency> updateCurrency(@RequestBody Currency currency, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (currency == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.currencyService.save(currency);

        return new ResponseEntity<>(currency, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> deleteCurrency(@PathVariable("id") Long id) {
        Currency currency = this.currencyService.getById(id);

        if (currency == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.currencyService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Currency>> getAllCurrency() {
        List<Currency> currencies = this.currencyService.getAll();

        if (currencies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }

}
