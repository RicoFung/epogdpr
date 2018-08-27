package epogdpr;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EmailTest
{
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	TemplateEngine templateEngine;

//	@Test
	public void sendSimpleEmail() throws Exception
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

	@Test
	public void sendTemplateEmail()
	{
		String deliver = "156812113@qq.com";
		String[] receiver = { "ricofungcn@icloud.com" };
		String[] carbonCopy = { "156812113@qq.com" };
		String subject = "test";
		String template = "email/privacy_policy_en";

		Context context = new Context();

		try
		{
			sendTemplateEmail(deliver, receiver, carbonCopy, subject, template, context);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 发送模板邮件
	 */
	public void sendTemplateEmail(String deliver, String[] receiver, String[] carbonCopy, String subject,
			String template, Context context)
	{
		try
		{
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message);

			String content = templateEngine.process(template, context);
			messageHelper.setFrom(deliver);
			messageHelper.setTo(receiver);
			messageHelper.setCc(carbonCopy);
			messageHelper.setSubject(subject);
			messageHelper.setText(content, true);

			mailSender.send(message);
		}
		catch (MessagingException e)
		{
			e.printStackTrace();
		}
	}
}
