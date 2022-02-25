package database;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;

@Entity
public class CPUUsage {
	
	private String timeInString;
	private int usage;
	private Timestamp date;

	public CPUUsage(Timestamp date,int usage) {
		this.usage = usage;
		this.date =date;
		LocalDateTime localTime = date.toLocalDateTime();
		setTimeInString(localTime);
	}
	
	public int getUsage() {
		return usage;
	}
	
	public String getDay() {
		return timeInString;
	}

	public Timestamp getDate() {
		return date;
	}

	private void setTimeInString(LocalDateTime localTime) {
		this.timeInString = ""+localTime.getYear()+":"+localTime.getMonthValue()+":"+localTime.getDayOfMonth() +
				":" + localTime.getHour() + ":" + localTime.getMinute() + ":" + localTime.getSecond();
	}
	
	public void setDate(Timestamp date) {
		this.date = date;
		LocalDateTime localTime = date.toLocalDateTime();
		setTimeInString(localTime);
	}

}
