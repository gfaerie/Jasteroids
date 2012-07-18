package se.faerie.jasteroids.graphics.model;

public class GameRenderingInfo {

	private long frameRenderingTime;
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
	public long getFrameRenderingTime() {
		return frameRenderingTime;
	}
	public void setFrameRenderingTime(long frameRenderingTime) {
		this.frameRenderingTime = frameRenderingTime;
	}
}
