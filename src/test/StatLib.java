package test;


public class StatLib {
	// simple average
	public static float avg(float[] x){
		float sum = 0;
		int len = x.length;
		for(int i = 0; i < x.length; i++)
		{
			sum += x[i];
		}
		return sum / len;
	}
	// Variance is sum of squared differences from the mean divided by number of elements.
	// returns the variance of X and Y
	public static float var(float[] x){
		float mean = avg(x);
		float sum = 0;
		int n = x.length;
		for (int i = 0; i < n; i++) {
			sum += Math.pow((x[i] - mean), 2);
		}
		return sum / n;
	}
	//cov(X, Y) = sum [(xi - E(X))(yi - E(Y))] / (n)
	// returns the covariance of X and Y
	public static float cov(float[] x, float[] y){
		float meanX = avg(x);
		float meanY = avg(y);
		float sum = 0;
		int n = x.length;
		for (int i = 0; i < n; i++) {
			sum += (x[i] - meanX) * (y[i] - meanY);
		}
		return sum / n;
	}

	//Correlation coefficient is an equation that is used to determine the strength of relation between two variables.
	// Correlation coefficient always lies between -1 to +1 where -1 represents X and Y are negatively correlated
	// and +1 represents X and Y are positively correlated.
	// returns the Pearson correlation coefficient of X and Y
	public static float pearson(float[] x, float[] y){
		float sumX = 0, sumY = 0, sum_XY = 0;
		float squareSum_X = 0, squareSum_Y = 0;
		int n = x.length;
		for (int i = 0; i < n; i++)
		{
			// sum of elements of array X.
			sumX += x[i];

			// sum of elements of array Y.
			sumY += y[i];

			// sum of X[i] * Y[i].
			sum_XY += x[i] * y[i];

			// sum of square of array elements.
			squareSum_X += Math.pow(x[i],2);
			squareSum_Y += Math.pow(y[i],2);
		}

		// use formula for calculating correlation coefficient.
		float corr = ((n * sum_XY) - (sumX * sumY))/
				(float)(Math.sqrt((n * squareSum_X - sumX * sumX) * (n * squareSum_Y - sumY * sumY)));
		return corr;

	}

	// performs a linear regression and returns the line equation
	public static Line linear_reg(Point[] points){
		float a = 0 ,b = 0;
		float sumY = 0 , sumX = 0 ,squareSum_X = 0 , sum_XY = 0;
		int n = points.length;
		for (int i = 0; i < n; i++) {
			// sum of elements of array Y.
			sumY += points[i].y;

			// sum of elements of array X.
			sumX += points[i].x;

			// sum of X * Y.
			sum_XY += points[i].x * points[i].y;

			// sum of square of array elements.
			squareSum_X += Math.pow(points[i].x,2);
		}
		a = ((n * sum_XY) - (sumX * sumY)) / ((n * squareSum_X) - (float)Math.pow(sumX , 2));
		b = ((sumY * squareSum_X) - (sumX * sum_XY)) / ((n * squareSum_X) - (float)Math.pow(sumX , 2));

		Line line  = new Line(a,b);
		return line;
	}

	//Standard deviation = square root of ∑(Xi - ų)2 / N
	//The standard deviation is the measure of how spread out numbers are.
	// returns the deviation between point p and the line equation of the points
	public static float dev(Point p,Point[] points){
		float dist;
		Line line = linear_reg(points);
		float y2 = line.f(p.x);
		dist = (float)Math.abs(y2-p.y);
		return dist;
	}
	// returns the deviation between point p and the line
	public static float dev(Point p,Line l){
		float dist;
		float y2 = l.f(p.x);
		dist = (float)Math.abs(y2-p.y);
		return dist;
	}
}