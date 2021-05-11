package test;

import java.util.ArrayList;
import java.util.List;

public class SimpleAnomalyDetector implements TimeSeriesAnomalyDetector {
	
	public ArrayList<CorrelatedFeatures> correlatedFeatures = new ArrayList<CorrelatedFeatures>();
	public final float threshold = (float) 0.6;


	@Override
	public void learnNormal(TimeSeries ts) {
		int numOfFeatures = ts.getNumOfFeatures();
		int numOfValues = ts.getNumOfValues();
		int i = 0;
		float maxPearsonVal;
		String f1Name = null, f2Name = null;
		float PearsonCorrVal;
		float[] feature1 = new float[numOfValues];
		float[] feature2 = new float[numOfValues];
		while(i < numOfFeatures) {
			maxPearsonVal = 0;
			for (int j = i + 1; j < numOfFeatures; j++) {

				//convert ArrayLists to Array of floats
				for (int k = 0; k < numOfValues; k++) {
					feature1[k] = ts.getCols()[i].getFloats().get(k);
					feature2[k] = ts.getCols()[j].getFloats().get(k);
				}
				PearsonCorrVal = Math.abs(StatLib.pearson(feature1, feature2));
				//find max Pearson value and save columns names according to comparison
				if (PearsonCorrVal > maxPearsonVal) {
					maxPearsonVal = PearsonCorrVal;
					f1Name = ts.getCols()[i].getName();
					f2Name = ts.getCols()[j].getName();
				}
			}
			//found correlated features -> adding them to list
			if(maxPearsonVal >= threshold){
				Line line = StatLib.linear_reg(getArrayOfPoints(feature1,feature2));
				CorrelatedFeatures cf = new CorrelatedFeatures(f1Name,f2Name,maxPearsonVal,line,threshold);
				correlatedFeatures.add(cf);
			}
			i++;
		}
	}
	public Point[] getArrayOfPoints(float[] f1, float[] f2){
		Point[] points = new Point[f1.length];
		for (int i = 0; i < f1.length; i++) {
			Point p = new Point(f1[i],f2[i]);
			points[i] = p;
		}
		return points;
	}


	@Override
	public List<AnomalyReport> detect(TimeSeries ts) {
		return null;
	}
	
	public List<CorrelatedFeatures> getNormalModel(){
		return correlatedFeatures;
	}
}
