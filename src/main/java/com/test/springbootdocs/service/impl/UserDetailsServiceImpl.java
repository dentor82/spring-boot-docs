package com.test.springbootdocs.service.impl;

import com.test.springbootdocs.dto.UserDto;
import com.test.springbootdocs.entity.User;
import com.test.springbootdocs.repository.UserRepository;
import com.test.springbootdocs.service.UserService;
import com.test.springbootdocs.utils.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Override
    public UserDto findByUsername(String userName) {
        User findUser = this.userRepository.findByUserName(userName);
        return Optional.ofNullable(findUser)
                .map(user -> ObjectMapperUtil.map(user, UserDto.class))
                .orElse(null);
    }

    @Override
    public void save(UserDto user) {
        User newUser = ObjectMapperUtil.map(user, User.class);
        newUser.setPassword(this.passwordEncoder.encode(user.getPasswordConfirm()));
        this.userRepository.save(newUser);
    }
}
