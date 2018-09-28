package com.gxk.demo.sbsecurity.demo.decide;

import com.gxk.demo.sbsecurity.demo.auth.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@Component
public class DecideManager {

  // some rules
  List<BiFunction<HttpServletRequest, User, Boolean>> ruleFuns = Arrays.asList(
    (HttpServletRequest request, User user) -> {
      if (request.getRequestURI().endsWith("home")) {
        return user.getAuthorities().contains("user");
      }
      return false;
    },
    (HttpServletRequest request, User user) -> {
      if (request.getRequestURI().endsWith("hello")) {
        return user.getAuthorities().contains("admin");
      }
      return false;
    },
    (HttpServletRequest request, User user) -> {
      String uri = request.getRequestURI();

      // permitAll
      List<String> permitAll = Arrays.asList(
        "/",
        "/login",
        "/favicon.ico"
      );

      return permitAll.contains(uri);
    }
  );


  public boolean decide(HttpServletRequest request, User user) {
    Optional<Boolean> opt = ruleFuns.stream()
      .map(it -> it.apply(request, user))
      .filter(it -> it)
      .findFirst();

    return opt.orElse(false);
  }
}
