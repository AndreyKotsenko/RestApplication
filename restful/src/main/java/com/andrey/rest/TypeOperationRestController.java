package com.andrey.rest;

import com.andrey.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Rest for type operation.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@RestController
@RequestMapping("/typeOperation/")
public class TypeOperationRestController {

    @Autowired
    private TypeOperationServiceImp typeOperationService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TypeOperation> getTypeOperation(@PathVariable("id") Long typeOperationId){
        if(typeOperationId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        TypeOperation typeOperation = this.typeOperationService.getById(typeOperationId);

        if(typeOperationId == null){
            return new ResponseEntity<TypeOperation>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<TypeOperation>(typeOperation, HttpStatus.OK);

    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TypeOperation> saveTypeOperation(@RequestBody TypeOperation typeOperation) {
        HttpHeaders headers = new HttpHeaders();

        if (typeOperation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.typeOperationService.save(typeOperation);
        return new ResponseEntity<>(typeOperation, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TypeOperation> updateTypeOperation(@RequestBody TypeOperation typeOperation, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (typeOperation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.typeOperationService.save(typeOperation);

        return new ResponseEntity<>(typeOperation, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TypeOperation> deleteTypeOperation(@PathVariable("id") Long id) {
        TypeOperation typeOperation = this.typeOperationService.getById(id);

        if (typeOperation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.typeOperationService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TypeOperation>> getAllTypeOperation() {
        List<TypeOperation> typeOperations = this.typeOperationService.getAll();

        if (typeOperations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(typeOperations, HttpStatus.OK);
    }

}

