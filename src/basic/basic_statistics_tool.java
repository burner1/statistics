package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class basic_statistics_tool {
	
//TODO what's with the zeroes?!
	public static void main(String[] args) {

	}
	public static double[] getCorrelations(int[] values){
		//store correlations
		List<Double> correlations = new ArrayList<>();
		
		//normalize data
		int[] normalizedValues = normalize(values);
		
		//get correlations
		correlations.add(getLinearCorrelation(normalizedValues));
		
		//convert arraylist to array
		return correlations.stream().mapToDouble(Double::doubleValue).toArray();
	}
	
	public static double getLinearCorrelation(double[] normalizedValues){
		double sumXY = 0;
		double sumX = 0;
		double sumY = 0;
		double sumX2 = 0;
		double sumY2 = 0;
		
		for (int i = 0; i < normalizedValues.length; i++){
			sumXY += (i + 1) * normalizedValues[i];
			sumX += i + 1;
			sumY += normalizedValues[i];
			sumX2 += Math.pow(i + 1, 2);
			sumY2 += Math.pow(normalizedValues[i], 2);
		}
		
		double cov = normalizedValues.length * sumXY - sumX * sumY;
		//System.out.println(sumX + " " + sumY + " " + sumXY + " " + normalizedValues.length + " " + cov);
		double sdx = normalizedValues.length * sumX2 - Math.pow(sumX, 2);
		//System.out.println(normalizedValues.length + " " + sumX2 + " " + Math.pow(sumX, 2) + " " + sdx);
		sdx = Math.pow(sdx, 0.5);
		double sdy = normalizedValues.length * sumY2 - Math.pow(sumY, 2);
		sdy = Math.pow(sdy, 0.5);
		
		return cov / sdx / sdy;
	}
	
	public static double getLinearCorrelation(int[] normalizedValues){
		int sumXY = 0;
		int sumX = 0;
		int sumY = 0;
		int sumX2 = 0;
		int sumY2 = 0;
		
		for (int i = 0; i < normalizedValues.length; i++){
			sumXY += (i + 1) * normalizedValues[i];
			sumX += i + 1;
			sumY += normalizedValues[i];
			sumX2 += Math.pow(i + 1, 2);
			sumY2 += Math.pow(normalizedValues[i], 2);
		}
		
		int cov = normalizedValues.length * sumXY - sumX * sumY;
		//System.out.println(sumX + " " + sumY + " " + sumXY + " " + normalizedValues.length + " " + cov);
		double sdx = normalizedValues.length * sumX2 - Math.pow(sumX, 2);
		//System.out.println(normalizedValues.length + " " + sumX2 + " " + Math.pow(sumX, 2) + " " + sdx);
		sdx = Math.pow(sdx, 0.5);
		double sdy = normalizedValues.length * sumY2 - Math.pow(sumY, 2);
		sdy = Math.pow(sdy, 0.5);
		
		return cov / sdx / sdy;
	}
	
	public static double[] normalize(double[] source){
		double[] res = new double[source.length];
		System.arraycopy(source, 0, res, 0, source.length);
		
		double min = res[0];
		for (int i = 1; i < res.length; i++){
			if (res[i] == 0) continue;
			min = (res[i] < min) ? res[i] : min;
		}
		
		for (int i = 0; i < res.length; i++){
			if (res[i] == 0) continue;
			res[i] -= min;
		}
		
		return res;
	}
	public static int[] normalize(int[] source){
		int[] res = new int[source.length];
		System.arraycopy(source, 0, res, 0, source.length);
		
		int min = res[0];
		for (int i = 1; i < res.length; i++)
			if (res[i] < min) 
				min = res[i];
		
		for (int i = 0; i < res.length; i++)
			res[i] -= min;
		
		return res;
	}
}
