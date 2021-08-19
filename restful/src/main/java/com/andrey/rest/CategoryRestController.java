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
 * Rest for category.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@RestController
@RequestMapping("/category/")
public class CategoryRestController {

    @Autowired
    private CategoryServiceImp categoryService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> getCategory(@PathVariable("id") Long categoryId){
        if(categoryId == null){
            return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
        }

        Category category = this.categoryService.getById(categoryId);

        if(category == null){
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Category>(category, HttpStatus.OK);

    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        HttpHeaders headers = new HttpHeaders();

        if (category == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.categoryService.save(category);
        return new ResponseEntity<>(category, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (category == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.categoryService.save(category);

        return new ResponseEntity<>(category, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") Long id) {
        Category category = this.categoryService.getById(id);

        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.categoryService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = this.categoryService.getAll();

        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
