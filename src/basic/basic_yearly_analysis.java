package basic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class basic_yearly_analysis {
	public static void main(String[] args) throws ParseException{
		
	}
	
	public static Record[] format(Object[][] values) throws ParseException{
		List<Record> records = new ArrayList<Record>();
		
		int start = getYear(values[0][0]);
		int end = getYear(values[values.length - 1][0]);
		int oldPos = 0;
		int newPos = 1;
		
		GregorianCalendar date = new GregorianCalendar();
		while (start <= end){
			if (start == end)
				newPos = values.length;
			else
				while (start == getYear(values[newPos][0]))
					newPos++;
			start++;
			
			List<GregorianCalendar> dates = new ArrayList<>();
			List<Double> x = new ArrayList<>();
			List<Double> y = new ArrayList<>();
			
			for (int i = oldPos; i < newPos; i++){
				date.setTime(new SimpleDateFormat("yyyy.MM.dd").parse((String)values[i][0]));
				dates.add(date);
				x.add((double)values[i][1]);
				y.add((double)values[i][0]);				
			}

			records.add(new Record(dates.toArray(new GregorianCalendar[0]),
					x.stream().mapToDouble(Double::doubleValue).toArray(),
				    y.stream().mapToDouble(Double::doubleValue).toArray()));
			
			oldPos = newPos;
		}
		
		return records.toArray(new Record[0]);
	}
	
	public static int getYear(Object dateObj){
		return Integer.parseInt(((String)dateObj).substring(0, ((String)dateObj).indexOf('.')));
	}
	
	public static ResultYearly[] getCorrelationByStep(Record[] records, int step){
		int stepCount = 365 / step;
		
		double[][] correlations = new double[stepCount][2];
		String[] periods = new String[stepCount];
		
		for (int i = 0; i < records.length; i++){
			for (int j = 0; j < stepCount; j++){
				double[] y = new double[step];
				System.arraycopy(records[i].getY(), j * step, y, 0, step);
				
				double r = basic_statistics_tool.getLinearCorrelation(y);
				correlations[j][0] += r;
				correlations[j][1] += Math.pow(r, 2);				
			}
		}
		
		for (int i = 0; i < correlations.length; i++){
			correlations[i][0] /= records.length;
			correlations[i][1] = Math.pow(correlations[i][1] / records.length - correlations[i][0], 0.5);
		}
		
		ResultYearly res = new ResultYearly(correlations, periods);
				
		return new ResultYearly[]{res};
	}
	
	//TODO weekly starting from first monday
}
