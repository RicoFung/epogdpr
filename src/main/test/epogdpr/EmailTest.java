package epogdpr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;

import com.Application;

import chok.util.FileUtil;

@RunWith(SpringRunner.class) 
@SpringBootTest(classes = Application.class)
public class EmailTest
{
	@Autowired
	private JavaMailSender mailSender;

	@Test
	public void email() throws Exception
	{
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		System.out.println(this.getClass().getClassLoader().getResource("templates/email/notice_of_client_en.html"));
		// 设置收件人，寄件人
		simpleMailMessage.setTo(new String[] { "ricofungcn@icloud.com" });
		simpleMailMessage.setFrom("156812113@qq.com");
		simpleMailMessage.setSubject("Spring Boot Mail 邮件测试【文本】");
		simpleMailMessage.setText("这里是一段简单文本。");
		// 发送邮件
		mailSender.send(simpleMailMessage);

		System.out.println("邮件已发送");
	}

}
