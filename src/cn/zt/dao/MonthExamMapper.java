package cn.zt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
 

import cn.zt.entity.Exam_User;

/**
 * dao�ӿ�
 * @author 1142632823
 *
 */
public interface MonthExamMapper {
	
	//ע��
	public int regUser(Exam_User user); 
	//��¼
	public Exam_User login(Exam_User user);
	
	//��¼������ѯ
	public Exam_User getUserByName(@Param("name") String name);
	//��ѯ���м���
	public List<Exam_User> userList();
}
