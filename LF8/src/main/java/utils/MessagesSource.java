package utils;

import java.util.HashMap;

public class MessagesSource {
	
	//hashmap for messages
	static HashMap<String, String> hashdata= new HashMap<String,String>();
	//creation of set hashMap 
	public static void createHashMap() {
		hashdata.put("alert","this Message was send automaticly because");
		hashdata.put("hardcapDisk"," is running out  it extends over your set hardcap with: ");
		hashdata.put("softcapDisk"," is becoming quit full handel it extens over your set softcap with:");
		hashdata.put("ram","your ram usage extends over ur set caps with:" );
		hashdata.put("Cpu", "your cpu usage extends over ur set caps with:");
		hashdata.put("bye", "your monitioring Programm");
		
	}
	//getter für messages
	public  static String getformHashmap(String key){
		String value=	hashdata.get(key);
		return value;
	}
	
	
}
