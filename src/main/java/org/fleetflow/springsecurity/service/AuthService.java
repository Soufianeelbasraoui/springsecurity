package org.fleetflow.springsecurity.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.fleetflow.springsecurity.config.JwtService;
import org.fleetflow.springsecurity.dto.AuthRequest;
import org.fleetflow.springsecurity.dto.RegisterRequest;
import org.fleetflow.springsecurity.model.User;
import org.fleetflow.springsecurity.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(RegisterRequest request){
        if (userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("nom deja existe");
        }
        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("email deja exists");
        }
        User user=new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return jwtService.generateToken(user.getEmail());

    }
    public String login(AuthRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        return jwtService.generateToken(user.getEmail());
    }
}
