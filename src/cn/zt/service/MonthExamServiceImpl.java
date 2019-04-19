package cn.zt.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
 
import cn.zt.dao.MonthExamMapper;
import cn.zt.entity.Exam_User;


/**
 * ÒµÎñÂß¼­²ã
 * @author 1142632823
 *
 */
@Service
public class MonthExamServiceImpl implements MonthExamService {
	@Resource
	private MonthExamMapper monthExamMapper;
	@Resource
	private ApplicationContext context;
	
	
	@Override
	public int regUser(Exam_User user) { 
		
		return monthExamMapper.regUser(user);
	}

	@Override 
	public Exam_User login(Exam_User user) { 
		return monthExamMapper.login(user);
	}

	@Override
	public Exam_User getUserByName(String name) { 
		Exam_User user = monthExamMapper.getUserByName(name);
		context.publishEvent(user);
		return user;
	}

	@Override
	public List<Exam_User> userList() {
		
		return monthExamMapper.userList();
	}
	
	@EventListener
	public void handlerEventMsg(Exam_User user) {
		
		System.out.println("this is Event=================");
	}

}
