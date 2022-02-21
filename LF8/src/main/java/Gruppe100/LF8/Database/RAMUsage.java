package Gruppe100.LF8.Database;
	
	import java.sql.Timestamp;
	import java.time.LocalDateTime;

	import javax.persistence.Entity;

	@Entity
	public class RAMUsage {
		
		private String timeInString;
		private Double usage;
		private Timestamp date;

		public RAMUsage(Timestamp Date,double usage) {
			this.usage = usage;
			this.date = Date;
			LocalDateTime localTime = Date.toLocalDateTime();
			setTimeInString(localTime);
		}
		
		public double getUsage() {
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
