package Gruppe100.LF8.Database;

public class DatabaseProperties {
	
	private static final String URL = "jdbc:h2:file:~/database/databaseData";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "password";
	
	public static String URL() {
		return URL;
	}
	
	public static String USERNAME() {
		return USERNAME;
	}
	
	public static String PASSWORD() {
		return PASSWORD;
	}

}
