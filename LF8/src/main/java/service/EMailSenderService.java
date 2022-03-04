package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

import org.hibernate.hql.internal.ast.tree.InLogicOperatorNode;

import kotlin.jvm.Throws;
import net.bytebuddy.build.Plugin.Engine.Summary;
/**{@summary Class for sending alerts} */
public class EMailSenderService {
	protected Session mailSesion;
	private String SenderEmail;
	private String Passwort;

	/**
	 * @author niels {@summary  sets up Login für mailSesion}
	 * @param smtphost die hosting address of email's server
	 * @param smtpport den SMTP port des Servers
	 * @param username die Email des Senders
	 * @param passwort das passwort Sets Session
	 */

	public void login(String smtpHost, int smtpPort, String username, String pasword) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", smtpPort);

		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		// for debugging
		props.put("mail.debug", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, pasword);
			}
		});
		this.mailSesion = session;
		System.out.println("login erfolgreich");
	}

	/**
	 * @author niels
	 * @throws MessagingException , UnsupportedEncodingException,
	 *                            IllegalStateException
	 * @param fromMail the sender email
	 * @param from     name of the sender
	 * @param receiver recipent/s of The email
	 * @param subject  subject line of the email
	 * @param message  message text of the email 
	 * {@summary this Methode sets up
	 *                 emails and sends them }
	 */

	public void send(String fromMail, String personalName, String receiver, String subject, String message)
			throws MessagingException, UnsupportedEncodingException {
		if (mailSesion == null) {
			throw new IllegalStateException("nicht eingelogt");
		}
		MimeMessage msg = new MimeMessage(mailSesion);
		MimeBodyPart body = new MimeBodyPart();
		body.setContent(message, "text/html; charset=utf-8");

		msg.setFrom(new InternetAddress(fromMail, personalName));
		msg.setReplyTo(InternetAddress.parse(fromMail, false));
		msg.setSubject(subject);
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(body);
		msg.setContent(multipart);
		msg.setSentDate(new Date());
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver, false));
		Transport.send(msg);
	}
  /** {@Summary Methode to send alert}
   * @param subject subject of the alert
   * @param message message of the alert  */
	public void sendAlert(String subject, String message) {
		Credentials();
		login("smtp.gmail.com", 587, SenderEmail, Passwort);
		try {
			send(SenderEmail, "bot", "nielskorn20@gmail.com", subject, message);
		} catch (UnsupportedEncodingException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/***  {@Summary Methode to get credentials}
 * */
	private void Credentials() {
		File credentials = new File("dummyFile.txt");

		if (credentials.exists()) {
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(credentials));
				String line;
				line = reader.readLine();
				SenderEmail = line;
				Passwort = reader.readLine();

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
