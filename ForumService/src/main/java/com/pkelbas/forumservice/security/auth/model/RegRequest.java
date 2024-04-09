package com.pkelbas.forumservice.security.auth.model;

import com.pkelbas.forumservice.security.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegRequest {

  private String username;
  private String password;

  private Role role;
}