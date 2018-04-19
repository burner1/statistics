package basic;

public class ResultYearly {
	double[][] correlations;
	String[] periods;
	
	
	public ResultYearly(double[][] correlations, String[] periods) {
		this.correlations = correlations;
		this.periods = periods;
	}
	
	public double[][] getCorrelations() {
		return correlations;
	}
	public void setCorrelations(double[][] correlations) {
		this.correlations = correlations;
	}
	public String[] getPeriods() {
		return periods;
	}
	public void setPeriods(String[] periods) {
		this.periods = periods;
	}
}
