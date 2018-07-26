package com.sonia.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sonia.daos.UserDao;
import com.sonia.entities.User;

@Controller
public class LoginController {
	
	@Resource(name="userDao")
	UserDao userDao;
	
	@RequestMapping(value="/userLogin")
	public String goToLoginPage(HttpServletRequest request, HttpSession session) {
		return "userLogin";
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String loginUser(HttpServletRequest request, HttpSession session) {
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		User validUser = userDao.verifyLoginInfo(username, password);
		if(validUser != null) {
			session.setAttribute("user", validUser);
			return "userCenter";
		} else {
			request.setAttribute("loginError", "Invalid inputs. Please try again.");
			return "userLogin";
		}
	}

}
