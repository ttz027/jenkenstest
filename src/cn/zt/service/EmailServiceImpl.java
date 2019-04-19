package cn.zt.service;

import java.io.File;
import java.io.IOException;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.SerializationUtils;
 
import cn.zt.entity.Exam_User;

@Service
public class EmailServiceImpl {
	public void sendEmail(Exam_User user) {
		
		try {
			FileCopyUtils.copy(SerializationUtils.serialize(user)
					,new File("E:/email/iamemail"));
		} catch (IOException e) {
			throw new RuntimeException("∑¢ÀÕºŸ” º˛ ß∞‹");
		}
	}


	@EventListener
	public void handlerEventMsg(Exam_User user) {
		/*User user = new User();
		user.setAddress("Œ‰∫∫");*/
		sendEmail(user);
	}

}
