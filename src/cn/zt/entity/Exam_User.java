package cn.zt.entity;

import org.springframework.context.ApplicationEvent;

/**
 *  µÃÂ¿‡
 * @author 1142632823
 *
 */
public class Exam_User  extends ApplicationEvent {
	
	public Exam_User(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	private int uId;
	private String userName;
	private String password;
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/*public Exam_User() {
	}
	public Exam_User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}*/
	
}
