package com.madeleine.madeleine.service;

import com.madeleine.madeleine.DTO.UserDTO.UserRequest;
import com.madeleine.madeleine.model.CustomUserDetails;
import com.madeleine.madeleine.model.NewsLetter;
import com.madeleine.madeleine.model.User;
import com.madeleine.madeleine.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private NewsLetterService newsLetterService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("잘못된 유저네임"));
        return new CustomUserDetails(user);
    }

    public CustomUserDetails createUser(UserRequest userDTO){
        String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
        log.info(encryptedPassword);
        User user = new User(userDTO.getUsername(), encryptedPassword, "User");
        userRepository.save(user);
        return new CustomUserDetails(user);
    } 

    public CustomUserDetails subscribeNewsLetter(Long userId, Long newsletterId) throws NotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("잘못된 유저 아이디"));
        NewsLetter newsLetter = newsLetterService.findById(newsletterId);
        user.getSubscribes().add(newsLetter);
        userRepository.save(user);
        return new CustomUserDetails(user);
    }

    public CustomUserDetails findById(Long userId){
        User user =  userRepository.findById(userId).orElseThrow(()->new NotFoundException("잘못된 유저 아이디"));
        return new CustomUserDetails(user);
    }
}
