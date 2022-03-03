package datainterpretation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThresholdList {
	public static final String RAMSOFTCAP = "RAMSOFTCAP";
	public static final String RAMHARDCAP = "RAMHARDCAP";
	public static final String CPUSOFTCAP = "CPUSOFTCAP";
	public static final String CPUHARDCAP = "CPUHARDCAP";
	public static final String DISKSPACEINDIVIDUALDEFAULTSOFTCAP = "DISKSPACEINDIVIDUALDEFAULTSOFTCAP";
	public static final String DISKSPACEINDIVIDUALDEFAULTHARDCAP = "DISKSPACEINDIVIDUALDEFAULTHARDCAP";
	public static final String DISKSPACEOVERALLSOFTCAP = "DISKSPACEOVERALLSOFTCAP";
	public static final String DISKSPACEOVERALLHARDCAP = "DISKSPACEOVERALLHARDCAP";
	public static final String SOFTCAP = "SoftCap";
	public static final String HARDCAP = "HardCap";
	public static HashMap<String, Double> thresholds = new HashMap<>();
	static {	
	 thresholds.put(RAMSOFTCAP,70.0);
	 thresholds.put(RAMHARDCAP,90.0);
	 thresholds.put(CPUSOFTCAP,70.0);
	 thresholds.put(CPUHARDCAP,90.0);
	 thresholds.put(DISKSPACEINDIVIDUALDEFAULTSOFTCAP,90.0);
	 thresholds.put(DISKSPACEINDIVIDUALDEFAULTHARDCAP,98.0);
	 thresholds.put(DISKSPACEOVERALLSOFTCAP,90.0);
	 thresholds.put(DISKSPACEOVERALLHARDCAP,99.0);
	}
	
	public static double getValue(String thresholdName) {
		return getThreshold(thresholdName).value;
	}
	
	private static Threshold getThreshold(String thresholdName) {
		return new Threshold(thresholdName,thresholds.get(thresholdName));
	}
	
	public static void changeValue(String thresholdName, double newValue) {
		if(thresholds.containsKey(thresholdName)) {
			thresholds.put(thresholdName, newValue);
			System.out.println(thresholdName + " has new Value " + newValue);
			return;
		}
		if(thresholdName.endsWith(SOFTCAP)) {
			DiskCaps.updateIndividualSoftCap(thresholdName.substring(0, thresholdName.length()-SOFTCAP.length()),
					newValue);
			return;
		}
		if(thresholdName.endsWith(HARDCAP)) {
			DiskCaps.updateIndividualHardCap(thresholdName.substring(0, thresholdName.length()-HARDCAP.length()),
					newValue);
			return;
		}
			
	}
	
	public static HashMap<String,Double> getThresholds(){
		HashMap<String, Double> allThresholds = (HashMap<String, Double>) thresholds.clone();
		for(DiskDrive drive : DiskCaps.getDriveList()) {
			allThresholds.put(drive.getDiskName()+ SOFTCAP, drive.getSpaceSoftCap());
			allThresholds.put(drive.getDiskName()+ HARDCAP, drive.getSpaceHardCap());
		}
		return allThresholds;
	}
	

}
