package com.gxk.demo.sbsecurity.demo.auth;

import com.gxk.demo.sbsecurity.demo.token.Token;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
public class AuthManager {
  public User auth(Token token) {
    if (token.getUsername().equals("admin")) {
      return new User(Arrays.asList("admin", "user"));
    }
    if (token.getUsername().equals("test")) {
      return new User(Arrays.asList("user"));
    }
    return new User(Collections.emptyList());
  }
}
