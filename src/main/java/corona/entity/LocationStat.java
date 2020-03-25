package corona.entity;

public class LocationStat {
	private String state;
	private String country;
	private int lastestReportedCases;
	private int differentFromPreviousDay;

	public LocationStat() {
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getLastestReportedCases() {
		return lastestReportedCases;
	}

	public void setLastestReportedCases(int lastestReportedCases) {
		this.lastestReportedCases = lastestReportedCases;
	}

	public int getDifferentFromPreviousDay() {
		return differentFromPreviousDay;
	}

	public void setDifferentFromPreviousDay(int differentFromPreviousDay) {
		this.differentFromPreviousDay = differentFromPreviousDay;
	}

	@Override
	public String toString() {
		return "LocationStat [state=" + state + ", country=" + country + ", lastestReportedCases="
				+ lastestReportedCases + ", differentFromPreviousDay=" + differentFromPreviousDay + "]";
	}

}
