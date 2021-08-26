package com.andrey.rest;

import com.andrey.User;
import com.andrey.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/start")
public class Start {

    @Autowired
    private UserServiceImp userService;

    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> start(){
        List<User> users = userService.getAll();

        log.info("Users = " + users);

        for(User user: users){
            user.setPassword(passwordEncoder.encode(user.getPassword()) );
            userService.save(user);

            log.info("user = " + user);
        }


        log.info("Users after = " + users);

        return new ResponseEntity<String>("Hello", HttpStatus.OK);

    }
}
