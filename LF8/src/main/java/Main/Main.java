package Main;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.boot.SpringApplication;

import Webside.Application;
import service.EMailSenderService;


public class Main {
	//static String username="";
	//static String password="";
	public static void main(String[] args) {
		// start Spring server
		SpringApplication.run(Application.class, args);
		//start Databases
		/*EMailSenderService sender =new EMailSenderService();
		sender.login("smtp.gmail.com", 587, username,password );
		try {
			sender.send(username, "LF8","nielskorn20@gmail.com", "LF8Test", "wen diese Email ankommt funktioniert es");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
