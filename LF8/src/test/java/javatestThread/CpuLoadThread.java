package javatestThread;

public class CpuLoadThread extends Thread {
	
	@Override
	public void run() {
		String lul = "";
		for(int i = 0 ; i < Integer.MAX_VALUE; i++) {
			lul += i + Math.random();
		}
		System.out.println(lul.charAt(lul.length()/2));
	}

}
