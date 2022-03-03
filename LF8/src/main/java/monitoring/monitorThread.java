package monitoring;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Main.MonitorOfPCResorces;

public class monitorThread extends Thread {
		public void run() {
			MonitorOfPCResorces pc = new MonitorOfPCResorces();
			pc.OsInfo();
			
				pc.getCpuload(pc.OsName);
				pc.memoryinfo();
				pc.drivespace();
			
			
			
		}
		public void runInInterval(int intervalInMin) {
		monitorThread mt= new monitorThread();
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(mt, 0, intervalInMin, TimeUnit.SECONDS);
}}

