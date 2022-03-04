package utils;

import java.util.HashMap;

public class MessagesSource {
	
	//hashmap for messages
	static HashMap<String, String> hashdata= new HashMap<String,String>();
	//creation of set hashMap 
	public static void createHashMap() {
		hashdata.put("alert","this Message was send automaticly because");
		hashdata.put("hardcapDisk"," is running out  it extends over ur set hardcap with: ");
		hashdata.put("softcapDisk"," is becoming quit full handel it extens over ur set softcap with:");
		hashdata.put("ram","ur ram usage extends over ur set caps with:" );
		hashdata.put("Cpu", "ur cpu usage extends over ur set caps with:");
		hashdata.put("bye", "ur monitioring Programm");
		
	}
	//getter für messages
	public  static String getformHashmap(String key){
		String value=	hashdata.get(key);
		return value;
	}
	
	
}
