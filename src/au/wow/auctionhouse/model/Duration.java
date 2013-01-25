package au.wow.auctionhouse.model;

/**
 * Enum representing the remaining duration of an auction.
 * 
 * @author James Whelan
 *
 */
public enum Duration {

	SHORT(15, 0, 0), MEDIUM(0, 2, 0), LONG(0, 12, 0), VERY_LONG(0, 0, 1);
	
	private Integer minutes;
	private Integer hours;
	private Integer days;
	
	private Duration(Integer minutes, Integer hours, Integer days) {
		this.minutes = minutes;
		this.hours = hours;
		this.days = days;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}
}
