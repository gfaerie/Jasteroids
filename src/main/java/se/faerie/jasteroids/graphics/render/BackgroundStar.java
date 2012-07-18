package se.faerie.jasteroids.graphics.render;

import java.awt.Color;

public class BackgroundStar {

	private Color color;
	private int radius;
	private int twinkleRadius;
	private int xPos;
	private int yPos;
	
	public int getXPos() {
		return xPos;
	}
	public void setXPos(int pos) {
		xPos = pos;
	}
	public int getYPos() {
		return yPos;
	}
	public void setYPos(int pos) {
		yPos = pos;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public int getTwinkleRadius() {
		return twinkleRadius;
	}
	public void setTwinkleRadius(int twinkleRadius) {
		this.twinkleRadius = twinkleRadius;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}	
}
