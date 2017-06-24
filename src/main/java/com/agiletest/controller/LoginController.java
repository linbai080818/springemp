package com.agiletest.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.agiletest.dao.UserDAO;
import com.agiletest.model.User;
import com.agiletest.service.UserService;

@Controller
@SessionAttributes("username")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping("login")
	public ModelAndView login(){
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	

	@RequestMapping("register")
	public ModelAndView register(){
		ModelAndView mv = new ModelAndView("register");
		return mv;
	}
	
	@RequestMapping("userAdd")
	public ModelAndView doAdd(String email,String username,String password) throws Exception {
		userDAO.addUser(email,username,password);
		ModelAndView mv = new ModelAndView("personal");
		return mv;
	}
	
//	@RequestMapping("main")
//	public ModelAndView main(String username,String password){
//		if(userService.login(username, password)) {
//			ModelAndView mv = new ModelAndView("emplist","emps",empDAO.getEmps());
//			return mv;
//		}else{
//			ModelAndView mv = new ModelAndView("login");
//			mv.addObject("msg", "用户名或者密码错误");
//			return mv;
//		}
//	}
	
	@RequestMapping("personal")
	public ModelAndView index(String username,String password) throws Exception{
		if(userService.login(username, password)) {
			ModelAndView mv = new ModelAndView("personal");
			return mv;
		}else{
			ModelAndView mv = new ModelAndView("login");
			mv.addObject("msg", "用户名或者密码错误");
			return mv;
		}
	}
	
	@RequestMapping("/check")
	public @ResponseBody int check(String email) throws Exception {
		return userService.checkEmailExist(email);
	}

}
