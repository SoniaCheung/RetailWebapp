package com.sonia.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sonia.entities.Order;
import com.sonia.entities.User;
import com.sonia.pageLogics.EditUserLogic;
import com.sonia.pageLogics.ViewHistoryLogic;

@Controller
public class UserCenterController {

	@Resource(name="viewHistoryLogic")
	ViewHistoryLogic viewHistoryLogic;
	@Resource(name="editUserLogic")
	EditUserLogic editUserLogic;
	
	@RequestMapping(value="/userCenter")
	public String goToUserCenter(HttpServletRequest request, HttpSession session) {
		return "userCenter";
	}

	@RequestMapping(value="/viewPersonalOrderHistory", method = RequestMethod.POST)
	public String viewPersonalOrderHistory(HttpServletRequest request, HttpSession session) {
		List<Order> orderList = viewHistoryLogic.personalOrderHistory(session);
		
		request.setAttribute("orders", orderList);
		return "viewHistory";
	}

	@RequestMapping(value="/viewOrder")
	public String viewOrderDetail(@RequestParam("id") int orderId, HttpServletRequest request, HttpSession session){
		Order order = viewHistoryLogic.viewOrderDetail(orderId, session);
		request.setAttribute("order", order);
		
		return "viewOrderDetail";
	}

	@RequestMapping(value="/updateUserInfo")
	public String editUserInfo(ModelMap model, HttpServletRequest request, HttpSession session) {
		User loginedUser = (User) session.getAttribute("user");
		model.addAttribute("editUser", loginedUser);
		
		return "updateUserInfo";
	}

	@RequestMapping(value="/submitEditUserInfo", method = RequestMethod.POST)
	public String submitEditUserInfo(User editUser, HttpServletRequest request, HttpSession session, RedirectAttributes redir) {
		boolean successfullyEdit = editUserLogic.editUser(request, session, editUser);
		
		if ( successfullyEdit == true) {
			redir.addFlashAttribute("message", "Your information was successfully updated.");
		    return "redirect:userCenter";
		} else {
			redir.addFlashAttribute("message", "Your original passowrd is not correct, please try again.");
			String referer = request.getHeader("Referer");
			return "redirect:"+ referer;
		}
	}
	
}
