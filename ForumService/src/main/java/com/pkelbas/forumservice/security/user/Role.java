package com.pkelbas.forumservice.security.user;


import static com.pkelbas.forumservice.security.user.Permission.ADMIN_CREATE;
import static com.pkelbas.forumservice.security.user.Permission.ADMIN_DELETE;
import static com.pkelbas.forumservice.security.user.Permission.ADMIN_READ;
import static com.pkelbas.forumservice.security.user.Permission.ADMIN_UPDATE;
import static com.pkelbas.forumservice.security.user.Permission.USER_CREATE;
import static com.pkelbas.forumservice.security.user.Permission.USER_DELETE;
import static com.pkelbas.forumservice.security.user.Permission.USER_READ;
import static com.pkelbas.forumservice.security.user.Permission.USER_UPDATE;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@RequiredArgsConstructor
public enum Role {
  ADMIN(
      Set.of(
          ADMIN_READ,
          ADMIN_UPDATE,
          ADMIN_DELETE,
          ADMIN_CREATE,
          USER_READ,
          USER_CREATE,
          USER_UPDATE,
          USER_DELETE
      )
  ),
  USER(
      Set.of(
          USER_READ,
          USER_CREATE,
          USER_UPDATE,
          USER_DELETE
      )
  );

  @Getter
  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
        .stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
        .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }


  public String roleName(){
    return "ROLE_" + this.name();
  }
}