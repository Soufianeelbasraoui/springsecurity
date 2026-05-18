package org.fleetflow.springsecurity.service;

import lombok.AllArgsConstructor;
import org.fleetflow.springsecurity.model.User;
import org.fleetflow.springsecurity.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private  final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user= userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("user note found"));
       return org.springframework.security.core.userdetails.User.builder()
               .username(user.getUsername())
               .password(user.getPassword())
               .build();
    }
}
