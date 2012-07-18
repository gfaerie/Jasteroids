package se.faerie.jasteroids.control;

public interface PlayerControl {

	public void accelerate();

	public void stopAccelerate();

	public void turnLeft();

	public void turnRight();

	public void stopTurn();
	
	public void usePrimary();
}
