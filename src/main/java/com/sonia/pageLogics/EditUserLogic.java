package com.sonia.pageLogics;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sonia.daos.UserDao;
import com.sonia.entities.User;

public class EditUserLogic {

	@Resource(name="userDao")
	UserDao userDao;
	
	public boolean editUser(HttpServletRequest request, HttpSession session, User editUser) {
		User loginedUser = (User) session.getAttribute("user");
		
		String originalPw = request.getParameter("currentPassword");
		String expectedCurrentPw = loginedUser.getPassword();
		
		if(originalPw.equals(expectedCurrentPw)) {
			User user = userDao.addOrUpdateEntity(editUser);
			session.setAttribute("user", user);
			return true;
		} else {
			return false;
		}
	}
}
