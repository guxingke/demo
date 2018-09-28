package com.gxk.demo.sbsecurity.demo.token;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class TokenParser {

  public Token parseToken(HttpServletRequest request) {
    String username = request.getParameter("username");
    if (username == null) {
      username = "";
    }
    String password= request.getParameter("password");
    if (password == null) {
      password = "";
    }
    // special case
    return new Token(username, password);
  }
}
