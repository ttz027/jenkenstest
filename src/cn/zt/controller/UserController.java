package cn.zt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;


import cn.zt.entity.Exam_User;
import cn.zt.service.MonthExamService;

/**
 * 控制层
 * @author 1142632823
 *
 */
@Controller 
public class UserController {
	@Resource
	private MonthExamService monthExamService;	

	//跳转登录页面
	@RequestMapping("/login")
	public String login(){
		return "login";
	}

	//跳转注册页面
	@RequestMapping("/reg")
	public String reg(){
		return "reg";
	}

	//注册
	@RequestMapping("/doreg")
	public String doreg(Exam_User user,Model model){
		boolean fig = true;
		
		if(user.getUserName() == null || user.getUserName() == ""){
			model.addAttribute("nameError", "用户名不能为空");   
			fig = false;
		}else{
			if(user.getUserName().length() < 3 || user.getUserName().length() > 10){
				model.addAttribute("nameError", "用户名长度为3-10");
				fig = false;
			} 
		} 
		
		if(user.getPassword() == null || user.getPassword() == ""){
			model.addAttribute("pwdError", "密码不能为空");  
			fig = false;
		}else{
			if(user.getPassword().length() < 3 || user.getPassword().length() > 10){
				model.addAttribute("pwdError", "密码长度为3-10"); 
				fig = false;
			}  
		} 
		
		if(fig){ 
			Exam_User userName=monthExamService.getUserByName(user.getUserName());
			if(userName == null){
				int num = monthExamService.regUser(user);
				if(num > 0){
					return "regSuccess";
				} 
			}else{
				model.addAttribute("error", "用户名已经存在"); 
			}
		}  
		model.addAttribute("name", user.getUserName());
		model.addAttribute("pwd", user.getPassword());
		return "reg";
	}

	//登录
	@RequestMapping("/dologin")
	public String doLogin(Model model,HttpSession session,@RequestParam String userName,
			@RequestParam String password){
		Exam_User user = monthExamService.getUserByName(userName);
		if(user != null ){
			if(user.getPassword().equals(password)){
				session.setAttribute("user", user);
				return "loginSuccess";
			}else{
				model.addAttribute("error", "密码不正确");
				return "login";
			}
		}else{
			model.addAttribute("error", "用户名不存在");
			return "login";
		} 
	}

	//查询列表
	@RequestMapping("/userlist")
	public String userList(HttpSession session,Model model){
		if(session.getAttribute("user") != null){
			List<Exam_User> userList = monthExamService.userList();
			session.setAttribute("userList", userList);
			return "userList";
		}else{
			model.addAttribute("error", "对不起，您没有登录,无权访问");
			return "login";
		}
	}

	@RequestMapping("/fream")
	@ResponseBody
	public String fream(String userName){
		System.out.println(userName);
		String str = "";
		if(userName != null && userName != ""){
			Exam_User user = monthExamService.getUserByName(userName); 
			if(user != null){
				str = "该用户名已经存在";
			}
		}
//		Exam_User user = new Exam_User("小王","123");
//		Exam_User user2 = new Exam_User("小王2","1234");
//		List<Exam_User> list = new ArrayList<Exam_User>();
//		list.add(user);
//		list.add(user2);
		str= JSON.toJSONString(str);  
		return str; 
	}

}
