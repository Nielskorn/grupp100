package Main;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.boot.SpringApplication;


import DatabaseInteractors.DatabaseMaintenanceThread;
import Webside.Application;
import database.RAMUsage;
import datainterpretation.CPUUsageAnalyser;
import datainterpretation.DiskDriveDataAnalyser;
import datainterpretation.DiskSpaceDataAcceptor;
import datainterpretation.RAMUsageAnalyser;
import monitoring.monitorThread;
import service.EMailSenderService;
import utils.MessagesSource;


public class Main {
 

	//static String username="";
	//static String password="";
	public static void main(String[] args) {
		// start Spring server
		SpringApplication.run(Application.class, args);
		MessagesSource.createHashMap();
		monitorThread mt=new monitorThread();
		mt.run();
		//DatabaseMaintenanceThread Dmt=new DatabaseMaintenanceThread();
		//Dmt.run();
	
		
		
	}
}
