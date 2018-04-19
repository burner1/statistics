package basic;

import java.util.GregorianCalendar;

public class Record {
	GregorianCalendar[] dates;
	double[] x;
	double[] y;
	
	public Record(){		
	}
	public Record(GregorianCalendar[] dates, double[] x, double[] y) {
		this.dates = dates;
		this.x = x;
		this.y = y;
	}
	
	public GregorianCalendar[] getDates() {
		return dates;
	}
	public void setDates(GregorianCalendar[] dates) {
		this.dates = dates;
	}
	public double[] getX() {
		return x;
	}
	public void setX(double[] x) {
		this.x = x;
	}
	public double[] getY() {
		return y;
	}
	public void setY(double[] y) {
		this.y = y;
	}
}
