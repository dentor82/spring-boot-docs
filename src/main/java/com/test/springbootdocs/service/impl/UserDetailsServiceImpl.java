package com.test.springbootdocs.service.impl;

import com.test.springbootdocs.dto.UserDto;
import com.test.springbootdocs.entity.User;
import com.test.springbootdocs.repository.UserRepository;
import com.test.springbootdocs.service.UserService;
import com.test.springbootdocs.utils.ObjectMapperUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        if (!this.userRepository.existsById(1L))
            this.userRepository.save(new User("admin", "{noop}admin"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUserName(username);
    }

    @Override
    public List<UserDto> getList() {
        return new ArrayList<>(
                ObjectMapperUtil
                        .mapAll(this.userRepository
                                .findAll(), UserDto.class)
        );
    }
}
