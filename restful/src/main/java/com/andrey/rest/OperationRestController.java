package com.andrey.rest;

import com.andrey.*;
import com.andrey.filter.Filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

/**
 * Rest for operation.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@Slf4j
@RestController
@RequestMapping("/operation/")
public class OperationRestController {

    @Autowired
    private OperationServiceImp operationService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Operation> getOperation(@PathVariable("id") Long operationId){
        if(operationId == null){
            return new ResponseEntity<Operation>(HttpStatus.BAD_REQUEST);
        }

        Operation operation = this.operationService.getById(operationId);

        if(operation == null){
            return new ResponseEntity<Operation>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Operation>(operation, HttpStatus.OK);

    }


    //for filter
    @RequestMapping(value = "filter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Operation>> filter(@RequestBody Filter filter) {
        HttpHeaders headers = new HttpHeaders();


        if (filter == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        List<Operation> operations = operationService.getAllByFilter(filter);
        return new ResponseEntity<>(operations, headers, HttpStatus.CREATED);
    }

    //for filter sum
    @RequestMapping(value = "sum", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> filterSum(@RequestBody Filter filter) {
        HttpHeaders headers = new HttpHeaders();


        if (filter == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        Double sum = operationService.getFilterSum(filter);

        sum = sum/100;
        return new ResponseEntity<>(sum, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Operation> saveOperation(@RequestBody Operation operation) {
        HttpHeaders headers = new HttpHeaders();

        if (operation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.operationService.save(operation);
        return new ResponseEntity<>(operation, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Operation> updateOperation(@RequestBody Operation operation, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (operation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.operationService.save(operation);

        return new ResponseEntity<>(operation, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Operation> deleteOperation(@PathVariable("id") Long id) {
        Operation operation = this.operationService.getById(id);

        if (operation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.operationService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Operation>> getAllOperation() {
        List<Operation> operations = this.operationService.getAll();

        log.info(String.valueOf(operations));
        if (operations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(operations, HttpStatus.OK);
    }

}

