package gui;

import java.time.Instant;
import java.time.temporal.TemporalAmount;

public class UpdateGUIThread extends Thread{
	
	private GUI gui ;
	private static Instant lastUpdate;
	private TemporalAmount frequency;
	public UpdateGUIThread(GUI gui, TemporalAmount frequency){
		this.gui = gui;
		this.frequency = frequency;
	}
	
	@Override
	public void run() {
		while(true) {
			if(lastUpdate == null || Instant.now().minus(frequency).isAfter(lastUpdate)){
				lastUpdate = Instant.now();
				if(gui.serverConnection!= null)
					gui.updateMonitoringScreen();
			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
			
		}
	}
	
	public void updateFrequency(TemporalAmount frequency) {
		this.frequency = frequency;
	}
	
	public TemporalAmount getUpdateFrequency() {
		return frequency;
	}

}
