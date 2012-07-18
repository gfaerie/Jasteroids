package se.faerie.jasteroids.physics;

public class RelativisticGamePhysics implements GamePhysics {

	private double c;
	
	public RelativisticGamePhysics(double speedofLight) {
		this.c = speedofLight;
	}

	@Override
	public double[] addVelocities(double[] v, double[] u) {
		
		double vuScalar = v[0] * u[0] + v[1]
				* u[1];
		double vvScalar = v[0] *  v[0] +  v[1]
				*  v[1];
		double alpha = Math.sqrt(1 - (vvScalar) / (c * c));

		double parallellWeight = vuScalar / vvScalar;
		double[] parallellToV = new double[2];
		parallellToV[0] = parallellWeight * v[0];
		parallellToV[1] = parallellWeight * v[1];

		double[] ortogonalToV = new double[2];
		ortogonalToV[0] = u[0] - parallellToV[0];
		ortogonalToV[1] = u[1] - parallellToV[1];

		double xResult = (v[0] + parallellToV[0] + alpha
				* ortogonalToV[0])
				/ (1 + vuScalar / (c * c));
		double yResult = (v[1] + parallellToV[1] + alpha
				* ortogonalToV[1])
				/ (1 + vuScalar / (c * c));
		
		return new double[]{xResult, yResult};
	}
}
