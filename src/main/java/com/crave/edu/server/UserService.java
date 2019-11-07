package com.crave.edu.server;

import com.crave.edu.bean.Users;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    Users backLogin(String account, String password, HttpServletRequest request);

    Users getBackLoginParkInfo(HttpServletRequest request);
}
