package cn.zt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
 

import cn.zt.entity.Exam_User;

/**
 * dao接口
 * @author 1142632823
 *
 */
public interface MonthExamMapper {
	
	//注册
	public int regUser(Exam_User user); 
	//登录
	public Exam_User login(Exam_User user);
	
	//登录姓名查询
	public Exam_User getUserByName(@Param("name") String name);
	//查询所有集合
	public List<Exam_User> userList();
}
