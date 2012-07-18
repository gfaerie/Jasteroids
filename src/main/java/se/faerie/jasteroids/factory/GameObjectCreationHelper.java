package se.faerie.jasteroids.factory;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.util.Random;

import se.faerie.jasteroids.graphics.model.GameObject;
import se.faerie.jasteroids.graphics.model.GameObjectAllegiance;
import se.faerie.jasteroids.graphics.model.GameObjectData;
import se.faerie.jasteroids.graphics.model.GameObjectPositionData;
import se.faerie.jasteroids.graphics.model.GameObjectType;
import se.faerie.jasteroids.graphics.model.impl.StaticPolygonObjectDataImpl;

public class GameObjectCreationHelper {

	
	private static Random random = new Random();
	private static double axisVariance = 0.2;
	private static double pointRadiusVariance = 0.1;
	private static double rotationVariance=0.2;
	
	public static int[] calculateCenterOfMass(int[] xCorners, int[] yCorners) {
		int xSum = 0;
		for (int i : xCorners) {
			xSum += i;
		}
		int ySum = 0;
		for (int i : yCorners) {
			ySum += i;
		}
		return new int[] { xSum / xCorners.length, ySum / yCorners.length };
	}

	public static double calculateCollisionRadius(int[] xCorners,
			int[] yCorners, int[] centerOfMass) {
		double sumRadius = 0;
		for (int i = 0; i < xCorners.length; i++) {
			sumRadius += Math.sqrt((xCorners[i] - centerOfMass[0])
					* (xCorners[i] - centerOfMass[0])
					+ (yCorners[i] - centerOfMass[1])
					* (yCorners[i] - centerOfMass[1]));
		}
		return sumRadius / xCorners.length;
	}

	public static GameObjectPositionData calculateProjectilePath(
			GameObjectPositionData owner, double speed) {
		double xIncrease = Math.cos(owner.getAngle()) * speed;
		double yIncrease = Math.sin(owner.getAngle()) * speed;
		return new GameObjectPositionData(owner.getAngle(), 0, owner
				.getXPosition(), xIncrease, 0, owner.getYPosition(),
				yIncrease, 0);

	}
	
	public static GameObject createAsteroid(double mass, double hitpoints, double radius,
			double speed[], double[] position) {
		int numberOfpoints = radius < 3 ? 3 : (int) Math.round(radius);
		double stepSize = (double) 2 * Math.PI / (double) numberOfpoints;
		int[] xCorners = new int[numberOfpoints];
		int[] yCorners = new int[numberOfpoints];
		double xAxisRadius = radius
				* (random.nextGaussian() * axisVariance + 1);
		double yAxisRadius = radius
				* (random.nextGaussian() * axisVariance + 1);
		for (int i = 0; i < numberOfpoints; i++) {
			xCorners[i] = (int) (xAxisRadius
					* (random.nextGaussian() * pointRadiusVariance + 1) * Math
					.cos(stepSize * i));
			yCorners[i] = (int) (yAxisRadius
					* (random.nextGaussian() * pointRadiusVariance + 1) * Math
					.sin(stepSize * i));
		}
		Color color = Color.GRAY;
		int[] centerOfMass = GameObjectCreationHelper.calculateCenterOfMass(xCorners, yCorners);
		double collisionRadius = GameObjectCreationHelper.calculateCollisionRadius(xCorners, yCorners, centerOfMass);

		GameObjectData data = new StaticPolygonObjectDataImpl(color,
				new Polygon(xCorners, yCorners, numberOfpoints), new Point(
						centerOfMass[0], centerOfMass[1]), collisionRadius,
				GameObjectType.ASTEROID, GameObjectAllegiance.ENEMIES,
				mass);
		GameObjectPositionData  state = new GameObjectPositionData(0, random.nextGaussian()*rotationVariance, position[0], speed[0], 0, position[1], speed[1], 0);
		return new GameObject(data, state, hitpoints);
		
	}
}
