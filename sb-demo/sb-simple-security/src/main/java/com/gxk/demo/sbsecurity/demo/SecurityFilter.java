package com.gxk.demo.sbsecurity.demo;

import com.gxk.demo.sbsecurity.demo.auth.AuthManager;
import com.gxk.demo.sbsecurity.demo.auth.User;
import com.gxk.demo.sbsecurity.demo.decide.DecideManager;
import com.gxk.demo.sbsecurity.demo.token.Token;
import com.gxk.demo.sbsecurity.demo.token.TokenParser;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityFilter extends GenericFilterBean {
  @Resource
  private TokenParser tokenParser;
  @Resource
  private AuthManager authManager;
  @Resource
  private DecideManager decideManager;

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;

    Token token = tokenParser.parseToken(request);

    // auth
    User user = authManager.auth(token);

    // decide
    boolean access = decideManager.decide(request, user);

    if (!access) {
      response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
      return;
    }

    // continue
    continueChain(req, res, chain);
  }

  private void continueChain(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    chain.doFilter(req, res);
  }
}
