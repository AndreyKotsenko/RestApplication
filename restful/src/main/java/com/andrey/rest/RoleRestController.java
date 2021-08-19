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
 * Rest for role.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@RestController
@RequestMapping("/role/")
public class RoleRestController {

    @Autowired
    private RoleServiceImp roleService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> getRole(@PathVariable("id") Long roleId){
        if(roleId == null){
            return new ResponseEntity<Role>(HttpStatus.BAD_REQUEST);
        }

        Role role = this.roleService.getById(roleId);

        if(role == null){
            return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Role>(role, HttpStatus.OK);

    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        HttpHeaders headers = new HttpHeaders();

        if (role == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.roleService.save(role);
        return new ResponseEntity<>(role, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> updateRole(@RequestBody Role role, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (role == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.roleService.save(role);

        return new ResponseEntity<>(role, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> deleteRole(@PathVariable("id") Long id) {
        Role role = this.roleService.getById(id);

        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.roleService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Role>> getAllRole() {
        List<Role> roles = this.roleService.getAll();

        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

}

