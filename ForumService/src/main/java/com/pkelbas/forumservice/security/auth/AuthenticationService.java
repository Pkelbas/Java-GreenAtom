package com.pkelbas.forumservice.security.auth;


import com.pkelbas.forumservice.security.jwt.JwtService;
import com.pkelbas.forumservice.security.user.User;
import com.pkelbas.forumservice.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.pkelbas.forumservice.security.auth.model.AuthRequest;
import com.pkelbas.forumservice.security.auth.model.AuthResponse;
import com.pkelbas.forumservice.security.auth.model.RegRequest;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository repository;
  private final PasswordEncoder encoder;
  private final AuthenticationManager manager;
  private final JwtService service;

  public AuthResponse register(RegRequest request) {
    var user = User.builder()
        .username(request.getUsername())
        .password(encoder.encode(request.getPassword()))
        .role(request.getRole())
        .build();
    repository.save(user);
    var jwtToken = service.generateToken(user);
    return AuthResponse.builder().token(jwtToken).build();
  }

  public AuthResponse authenticate(AuthRequest request) {
    manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    var user = repository.findByUsername(request.getUsername()).orElseThrow();
    var jwtToken = service.generateToken(user);
    return AuthResponse.builder().token(jwtToken).build();
  }
}