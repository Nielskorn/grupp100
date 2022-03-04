package monitoring;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import DatabaseInteractors.DatabasePusher;
import Main.MonitorOfPCResorces;
/** class to create montoring thread
 * 
 * 
 * */
public class monitorThread extends Thread {
		public void run() {
			MonitorOfPCResorces pc = new MonitorOfPCResorces();
			pc.OsInfo();
			DatabasePusher.pushCPUUsageToDatabase(pc.getCpuload(pc.OsName));
			DatabasePusher.pushRAMUsageToDatabase(pc.memoryinfo());
			pc.drivespace();
		}
		//methode to call montioring Thread in interval
		public void runInInterval(int intervalInSeconds) {
		monitorThread mt= new monitorThread();
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(mt, 0, intervalInSeconds, TimeUnit.SECONDS);
}}

