package se.faerie.jasteroids.graphics.render;

public class GameRenderingInfo {

	private long stepSize;
	private int xScreenSize;
	private int yScreenSize;
	
	public void setXScreenSize(int screenSize) {
		xScreenSize = screenSize;
	}
	public void setYScreenSize(int screenSize) {
		yScreenSize = screenSize;
	}
	public int getXScreenSize() {
		return xScreenSize;
	}
	public int getYScreenSize() {
		return yScreenSize;
	}
	public long getStepSize() {
		return stepSize;
	}
	public void setStepSize(long stepSize) {
		this.stepSize = stepSize;
	}
	
}
