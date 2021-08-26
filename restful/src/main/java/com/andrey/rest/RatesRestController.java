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
 * Rest for rates.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@RestController
@RequestMapping("/rates/")
public class RatesRestController {

    @Autowired
    private RatesServiceImp ratesService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rates> getRates(@PathVariable("id") Long ratesId){
        if(ratesId == null){
            return new ResponseEntity<Rates>(HttpStatus.BAD_REQUEST);
        }

        Rates rates = this.ratesService.getById(ratesId);

        if(rates == null){
            return new ResponseEntity<Rates>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Rates>(rates, HttpStatus.OK);

    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rates> saveRates(@RequestBody Rates rates) {
        HttpHeaders headers = new HttpHeaders();

        if (rates == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.ratesService.save(rates);
        return new ResponseEntity<>(rates, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rates> updateRates(@RequestBody Rates rates, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (rates == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.ratesService.save(rates);

        return new ResponseEntity<>(rates, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rates> deleteRates(@PathVariable("id") Long id) {
        Rates rates = this.ratesService.getById(id);

        if (rates == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.ratesService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Rates>> getAllRates() {
        List<Rates> ratesList = this.ratesService.getAll();

        if (ratesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ratesList, HttpStatus.OK);
    }

}

