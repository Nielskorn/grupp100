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
	public static HashMap<String, Double> thresholds = new HashMap<>();
	static {	
	 thresholds.put(RAMSOFTCAP,70.0);
	 thresholds.put(RAMHARDCAP,90.0);
	 thresholds.put(CPUSOFTCAP,70.0);
	 thresholds.put(CPUHARDCAP,90.0);
	 thresholds.put(DISKSPACEINDIVIDUALDEFAULTSOFTCAP,90.0);
	 thresholds.put(DISKSPACEINDIVIDUALDEFAULTHARDCAP,98.0);
	 thresholds.put(DISKSPACEOVERALLSOFTCAP,80.0);
	 thresholds.put(DISKSPACEOVERALLHARDCAP,90.0);
	}
	
	public static double getValue(String thresholdName) {
		return getThreshold(thresholdName).value;
	}
	
	private static Threshold getThreshold(String thresholdName) {
		return new Threshold(thresholdName,thresholds.get(thresholdName));
	}
	
	public static void changeValue(String thresholdName, double newValue) {
		thresholds.put(thresholdName, newValue);
	}
	
	public static ArrayList<Threshold> getThresholdsAsList(){
		ArrayList<Threshold> thresholdList = new ArrayList<Threshold>();
		for(String key : thresholds.keySet()) {
			thresholdList.add(getThreshold(key));
		}
		return thresholdList;
	}
	

}
