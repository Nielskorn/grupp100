package Main;

import org.springframework.boot.SpringApplication;

import DatabaseInteractors.DatabaseMaintenanceThread;
import Webside.Application;
import monitoring.monitorThread;
import utils.MessagesSource;


public class Main {
 
	public static final int MONITORINTERVAL = 3;
	//static String username="";
	//static String password="";
	public static void main(String[] args) {
		// start Spring server
		SpringApplication.run(Application.class, args);
		MessagesSource.createHashMap();
		monitorThread mt=new monitorThread();
		mt.runInInterval(MONITORINTERVAL);
		new DatabaseMaintenanceThread().start();
		//DatabaseMaintenanceThread Dmt=new DatabaseMaintenanceThread();
		//Dmt.run();
	
		
		
	}
}
