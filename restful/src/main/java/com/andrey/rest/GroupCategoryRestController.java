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
 * Rest for group category.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@RestController
@RequestMapping("/groupCategory/")
public class GroupCategoryRestController {

    @Autowired
    private GroupCategoryServiceImp groupCategoryService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupCategory> getGroupCategory(@PathVariable("id") Long groupCategoryId){
        if(groupCategoryId == null){
            return new ResponseEntity<GroupCategory>(HttpStatus.BAD_REQUEST);
        }

        GroupCategory groupCategory = this.groupCategoryService.getById(groupCategoryId);

        if(groupCategory == null){
            return new ResponseEntity<GroupCategory>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<GroupCategory>(groupCategory, HttpStatus.OK);

    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupCategory> saveGroupCategory(@RequestBody GroupCategory groupCategory) {
        HttpHeaders headers = new HttpHeaders();

        if (groupCategory == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.groupCategoryService.save(groupCategory);
        return new ResponseEntity<>(groupCategory, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupCategory> updateGroupCategory(@RequestBody GroupCategory groupCategory, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (groupCategory == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.groupCategoryService.save(groupCategory);

        return new ResponseEntity<>(groupCategory, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupCategory> deleteGroupCategory(@PathVariable("id") Long id) {
        GroupCategory groupCategory = this.groupCategoryService.getById(id);

        if (groupCategory == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.groupCategoryService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GroupCategory>> getAllGroupCategory() {
        List<GroupCategory> groupCategories = this.groupCategoryService.getAll();

        if (groupCategories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(groupCategories, HttpStatus.OK);
    }

}

