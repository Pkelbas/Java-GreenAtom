package com.pkelbas.forumservice.security.auth;

import com.pkelbas.forumservice.security.auth.model.AuthRequest;
import com.pkelbas.forumservice.security.auth.model.AuthResponse;
import com.pkelbas.forumservice.security.auth.model.RegRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/reg")
  @Cacheable("register")
  public ResponseEntity<AuthResponse> register(@RequestBody RegRequest request) {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/auth")
  @Cacheable("authenticate")
  public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }


}