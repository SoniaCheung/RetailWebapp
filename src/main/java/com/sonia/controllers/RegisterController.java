package com.sonia.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sonia.daos.UserDao;
import com.sonia.entities.User;
import com.sonia.factories.UserFactory;

@Controller
public class RegisterController {

	@Resource(name="userFactory")
	UserFactory userFactory;
	@Resource(name="userDao")
	UserDao userDao;
	
	@RequestMapping(value="/register")
	public ModelAndView goToRegisterForm() {
		return new ModelAndView("register", "user", userFactory.createUser());
	}

	@RequestMapping(value="/registerUser", method = RequestMethod.POST)
	public String registerUser(User user, BindingResult result, ModelMap model, HttpSession session) {		
		System.out.println("Correct call!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		User newUser = null;
		newUser = 	userDao.addOrUpdateEntity(user);
		if(newUser != null) {
			session.setAttribute("user", newUser);
			return "userCenter";
		}else {
			return "errorPage";
		}
	}

}
