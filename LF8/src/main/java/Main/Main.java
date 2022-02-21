package Main;

import org.springframework.boot.SpringApplication;

import Webside.Application;


public class Main {

	public static void main(String[] args) {
		// start Spring server
		SpringApplication.run(Application.class, args);
		//start Databases
	}
}
