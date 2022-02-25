package javatestThread;

public class CpuLoadThread extends Thread {
	
	public boolean started = false;
	
	@Override
	public void run() {
		String lul = "";
		for(int i = 0 ; i < Integer.MAX_VALUE; i++) {
			started = true;
			lul += i + Math.random();
		}
		System.out.println(lul.charAt(lul.length()/2));
	}

}
