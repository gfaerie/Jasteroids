package se.faerie.jasteroids.graphics.model;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;

public interface GameObjectData {

	public double getCollisionRadius();
	
	public Color getColor();

	public double getMass();
	
	public Shape getGraphics();
	
	public Point getRealtiveCenterOfMass();
	
	public GameObjectType getType();
	
	public GameObjectAllegiance getAlligiance();
	
}
