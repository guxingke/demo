package com.gxk.demo.sbsecurity.demo.auth;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class User {
  List<String> authorities;
}
