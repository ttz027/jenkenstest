package cn.zt.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zt.entity.Exam_User;

/**
 * ÒµÎñÂß¼­²ã½Ó¿Ú
 * @author 1142632823
 *
 */
public interface MonthExamService {
	//×¢²á
	public int regUser(Exam_User user);

	//µÇÂ¼
	public Exam_User login(Exam_User user);
	
	public Exam_User getUserByName( String name);
	
	public List<Exam_User> userList();
}
