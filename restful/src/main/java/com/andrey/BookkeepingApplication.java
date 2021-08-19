package com.andrey;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * Main class.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@Slf4j
@SpringBootApplication
public class BookkeepingApplication {


	public static void main(String[] args) {

		SpringApplication.run(BookkeepingApplication.class, args);


	}

}
