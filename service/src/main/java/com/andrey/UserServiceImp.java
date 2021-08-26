package com.andrey;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * Service for user.
 *
 * @author Andrey Kotsenko
 * @version 1.0
 */

@Slf4j
@Service
public class UserServiceImp extends BaseService<User> implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {

        log.info(" Mobile from form  = " + mobileNumber);

        User myUser= userRepository.findByMobileNumber(mobileNumber);

        log.info("User = " + myUser + " role = " + myUser.getRole_id());
        if (myUser == null) {
            throw new UsernameNotFoundException("Unknown user with mobile number: " + mobileNumber);
        }


        UserDetails user = org.springframework.security.core.userdetails.User.builder()
                .username(myUser.getMobile_number())
                .password(myUser.getPassword())
                .roles(roleRepository.getById(myUser.getRole_id()).getType())
                .build();
        return user;
    }

    @Override
    public void save(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if ("POST".equalsIgnoreCase(requestMethod.getMethod())) {
            repository.add(user);
        } else {
            repository.update(user);
        }
    }

}
