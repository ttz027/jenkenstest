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
 * ���Ʋ�
 * @author 1142632823
 *
 */
@Controller 
public class UserController {
	@Resource
	private MonthExamService monthExamService;	

	//��ת��¼ҳ��
	@RequestMapping("/login")
	public String login(){
		return "login";
	}

	//��תע��ҳ��
	@RequestMapping("/reg")
	public String reg(){
		return "reg";
	}

	//ע��
	@RequestMapping("/doreg")
	public String doreg(Exam_User user,Model model){
		boolean fig = true;
		
		if(user.getUserName() == null || user.getUserName() == ""){
			model.addAttribute("nameError", "�û�������Ϊ��");   
			fig = false;
		}else{
			if(user.getUserName().length() < 3 || user.getUserName().length() > 10){
				model.addAttribute("nameError", "�û�������Ϊ3-10");
				fig = false;
			} 
		} 
		
		if(user.getPassword() == null || user.getPassword() == ""){
			model.addAttribute("pwdError", "���벻��Ϊ��");  
			fig = false;
		}else{
			if(user.getPassword().length() < 3 || user.getPassword().length() > 10){
				model.addAttribute("pwdError", "���볤��Ϊ3-10"); 
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
				model.addAttribute("error", "�û����Ѿ�����"); 
			}
		}  
		model.addAttribute("name", user.getUserName());
		model.addAttribute("pwd", user.getPassword());
		return "reg";
	}

	//��¼
	@RequestMapping("/dologin")
	public String doLogin(Model model,HttpSession session,@RequestParam String userName,
			@RequestParam String password){
		Exam_User user = monthExamService.getUserByName(userName);
		if(user != null ){
			if(user.getPassword().equals(password)){
				session.setAttribute("user", user);
				return "loginSuccess";
			}else{
				model.addAttribute("error", "���벻��ȷ");
				return "login";
			}
		}else{
			model.addAttribute("error", "�û���������");
			return "login";
		} 
	}

	//��ѯ�б�
	@RequestMapping("/userlist")
	public String userList(HttpSession session,Model model){
		if(session.getAttribute("user") != null){
			List<Exam_User> userList = monthExamService.userList();
			session.setAttribute("userList", userList);
			return "userList";
		}else{
			model.addAttribute("error", "�Բ�����û�е�¼,��Ȩ����");
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
				str = "���û����Ѿ�����";
			}
		}
//		Exam_User user = new Exam_User("С��","123");
//		Exam_User user2 = new Exam_User("С��2","1234");
//		List<Exam_User> list = new ArrayList<Exam_User>();
//		list.add(user);
//		list.add(user2);
		str= JSON.toJSONString(str);  
		return str; 
	}

}
