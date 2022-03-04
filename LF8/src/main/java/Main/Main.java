package Main;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.springframework.boot.SpringApplication;


import Webside.Application;
import monitoring.monitorThread;
import utils.MessagesSource;


public class Main {
 

	//static String username="";
	//static String password="";
	public static void main(String[] args) {
		// start Spring server
		SpringApplication.run(Application.class, args);
		MessagesSource.createHashMap();
		monitorThread mt=new monitorThread();
		mt.runInInterval(3);
		System.out.println("yolo");
		//DatabaseMaintenanceThread Dmt=new DatabaseMaintenanceThread();
		//Dmt.run();
	
		
		
	}
}
