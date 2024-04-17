package com.open.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.open.shop.repository.UserRepository;

@Service
public class ReactiveUserDetailsService implements UserDetailsService {

  @Autowired
  @NonNull
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    com.open.shop.model.db.User userDbRecord = userRepository.findByPhoneNumber(username).block();

    if (userDbRecord == null) {
      throw new UsernameNotFoundException("User not found");
    }

    return User.builder()
        .username(userDbRecord.phone())
        .password(userDbRecord.password())
        .roles("ADMIN")
        .accountLocked(false)
        .accountExpired(false)
        .credentialsExpired(false)
        .disabled(false)
        .build();
  }

}
