package se.faerie.jasteroids.graphics.model;

import se.faerie.jasteroids.graphics.render.GameRenderingInfo;
import se.faerie.jasteroids.physics.GamePhysics;

public class GameObjectPositionData {

	private double angle;
	private double angleVelocity;

	private double xPosition;
	private double xVelocity;
	private double xAcceleration;

	private double yPosition;
	private double yVelocity;
	private double yAcceleration;

	public GameObjectPositionData(double angle, double angleVelocity,
			double xPosition, double xVelocity, double xAcceleration,
			double yPosition, double yVelocity, double yAcceleration) {
		this.angle = angle;
		this.angleVelocity = angleVelocity;
		this.xPosition = xPosition;
		this.xVelocity = xVelocity;
		this.xAcceleration = xAcceleration;
		this.yPosition = yPosition;
		this.yVelocity = yVelocity;
		this.yAcceleration = yAcceleration;
	}

	public void update(GameRenderingInfo renderingInfo, GamePhysics physics) {

		// update speeds

		double frameSpeedWeight = (double) renderingInfo
				.getStepSize() 
				/ (double) 1000000000;

		if (xAcceleration != 0 || yAcceleration != 0) {
			double[] newSpeeds = physics.addVelocities(new double[] {
					xAcceleration * frameSpeedWeight,
					yAcceleration * frameSpeedWeight }, new double[] {
					xVelocity, yVelocity });
			xVelocity = newSpeeds[0];
			yVelocity = newSpeeds[1];
		}

		// update angle
		angle += angleVelocity * frameSpeedWeight;

		// update x position
		xPosition += xVelocity * frameSpeedWeight;

		// update y position
		yPosition += yVelocity * frameSpeedWeight;

		// wrap edges
		if (xPosition < 0) {
			xPosition = renderingInfo.getXScreenSize();
		} else if (xPosition > renderingInfo.getXScreenSize()) {
			xPosition = 0;
		}
		if (yPosition < 0) {
			yPosition = renderingInfo.getYScreenSize();
		} else if (yPosition > renderingInfo.getYScreenSize()) {
			yPosition = 0;
		}
	}

	public double getXAcceleration() {
		return xAcceleration;
	}

	public void setXAcceleration(double acceleration) {
		xAcceleration = acceleration;
	}

	public double getYAcceleration() {
		return yAcceleration;
	}

	public void setYAcceleration(double acceleration) {
		yAcceleration = acceleration;
	}

	public void setAngleVelocity(double angleVelocity) {
		this.angleVelocity = angleVelocity;
	}

	public double getAngle() {
		return angle;
	}

	public double getXPosition() {
		return xPosition;
	}

	public double getYPosition() {
		return yPosition;
	}

	public double getAngleVelocity() {
		return angleVelocity;
	}

	public double getXVelocity() {
		return xVelocity;
	}

	public double getYVelocity() {
		return yVelocity;
	}
}
