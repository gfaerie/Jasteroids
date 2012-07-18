package se.faerie.jasteroids.physics;

import java.awt.Polygon;

public class MathHelper {

	public static double[] projectPolygonToLine(Polygon polygon, int[] line) {
		double[] result = new double[2];
		result[0] = Double.MAX_VALUE;
		result[1] = Double.MIN_VALUE;
		for (int i = 0; i < polygon.npoints; i++) {
			double dotProduct = dotScalar(new int[] { polygon.xpoints[i],
					polygon.ypoints[i] }, line);
			if (dotProduct < result[0]) {
				result[0] = dotProduct;
			}
			if (dotProduct > result[1]) {
				result[1] = dotProduct;
			}
		}
		return result;
	}

	public static double dotScalar(int[] u, int[] v) {
		double result=0;
		for(int i=0; i<u.length; i++){
			result+=u[i]*v[i];
		}
		return result;
	}

}
