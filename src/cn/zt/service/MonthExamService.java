package cn.zt.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zt.entity.Exam_User;

/**
 * ҵ���߼���ӿ�
 * @author 1142632823
 *
 */
public interface MonthExamService {
	//ע��
	public int regUser(Exam_User user);

	//��¼
	public Exam_User login(Exam_User user);
	
	public Exam_User getUserByName( String name);
	
	public List<Exam_User> userList();
}
