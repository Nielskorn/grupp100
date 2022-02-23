package service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class EMailSenderService {
	protected Session mailSesion;
	public void login(String smtpHost , int smtpPort, String username ,String pasword) {
	Properties props =new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.host", smtpHost);
	props.put("mail.transport.protocol", "smtp");
	props.put("mail.smtp.port", smtpPort);
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.ssl.protocols", "TLSv1.2");
	props.put("mail.debug", "true");
//props.put("mail.smtps.socketFactory.port", smtpPort);
	Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, pasword);
                }
            });
	this.mailSesion= session;
	System.out.println("login erfolgreich");
}
	public void send(String fromMail,String from, String receiver,String subject,String message ) throws MessagingException, UnsupportedEncodingException {
		if (mailSesion==null) {
			throw new IllegalStateException("nicht eingelogt");
		}
		MimeMessage msg=new MimeMessage(mailSesion);
		MimeBodyPart body=new MimeBodyPart();
		body.setContent(message,"text/html; charset=utf-8");
		
		
		msg.setFrom(new InternetAddress(fromMail,from));
		msg.setReplyTo(InternetAddress.parse(fromMail, false));
		msg.setSubject(subject);
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(body);
		msg.setContent(multipart);
		msg.setSentDate(new Date());
		msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(receiver, false));
		Transport.send(msg);
		
	}
	     
}
