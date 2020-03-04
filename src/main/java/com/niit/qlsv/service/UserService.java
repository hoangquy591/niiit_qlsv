package com.niit.qlsv.service;

//import com.niit.qlsv.dao.entities.CustomUserDetails;
import com.niit.qlsv.dao.entities.UserEnt;
import com.niit.qlsv.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEnt user = userRepository.findUserByUsername(s);
        return user;
    }

    public UserEnt findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
