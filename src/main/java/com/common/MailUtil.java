package com.common;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class MailUtil
{
	@Autowired
	private JavaMailSender			mailSenderAutowired;
	private static JavaMailSender	mailSender;

	@Autowired
	private TemplateEngine			templateEngineAutowired;
	private static TemplateEngine	templateEngine;

	@PostConstruct
	public void init()
	{
		MailUtil.mailSender = mailSenderAutowired;
		MailUtil.templateEngine = templateEngineAutowired;
	}

	/**
	 * 发送模板邮件
	 * @param deliver 发送者
	 * @param receiver 接收者
	 * @param carbonCopy 抄送
	 * @param subject 主题
	 * @param template 目标名
	 * @param context 上下文
	 * @throws MessagingException
	 */
	public static void sendTemplateEmail(String deliver, String[] receiver, String[] carbonCopy, String subject, String template, Context context) throws MessagingException
	{
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message);

		String content = templateEngine.process(template, context);
		messageHelper.setFrom(deliver);
		messageHelper.setTo(receiver);
		if (carbonCopy != null)
			messageHelper.setCc(carbonCopy);
		messageHelper.setSubject(subject);
		messageHelper.setText(content, true);

		mailSender.send(message);
	}
}
