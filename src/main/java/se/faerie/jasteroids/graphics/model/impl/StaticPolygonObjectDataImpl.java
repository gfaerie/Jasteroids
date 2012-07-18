package se.faerie.jasteroids.graphics.model.impl;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;

import se.faerie.jasteroids.factory.GameObjectCreationHelper;
import se.faerie.jasteroids.graphics.model.GameObjectAllegiance;
import se.faerie.jasteroids.graphics.model.GameObjectData;
import se.faerie.jasteroids.graphics.model.GameObjectType;

public class StaticPolygonObjectDataImpl implements GameObjectData {
	private Color color;

	private Polygon polygon;
	private Point centerOfMass;
	private double collisionRadius;
	private GameObjectType type;
	private GameObjectAllegiance alligiance;
	private double mass;

	public StaticPolygonObjectDataImpl(Color color, Polygon polygon,
			Point centerOfMass, double collisionRadius, GameObjectType type,
			GameObjectAllegiance alligiance, double mass) {
		this.color = color;
		this.polygon = polygon;
		this.centerOfMass = centerOfMass;
		this.collisionRadius = collisionRadius;
		this.type = type;
		this.alligiance = alligiance;
		this.mass = mass;
	}

	public StaticPolygonObjectDataImpl(Color color, int[] xCorners,
			int[] yCorners, GameObjectType type,
			GameObjectAllegiance alligiance, int mass) {
		this.color = color;
		this.polygon = new Polygon(xCorners, yCorners, xCorners.length);
		int[] center = GameObjectCreationHelper.calculateCenterOfMass(xCorners,
				yCorners);
		this.collisionRadius = GameObjectCreationHelper
				.calculateCollisionRadius(xCorners, yCorners, center);
		this.centerOfMass = new Point(center[0], center[1]);
		this.type = type;
		this.alligiance = alligiance;
		this.mass = mass;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public Shape getGraphics() {
		return polygon;
	}

	@Override
	public Point getRealtiveCenterOfMass() {
		return centerOfMass;
	}

	@Override
	public double getCollisionRadius() {
		return collisionRadius;
	}

	public GameObjectType getType() {
		return type;

	}

	public double getMass() {
		return mass;
	}

	public GameObjectAllegiance getAlligiance() {
		return alligiance;
	}
}
