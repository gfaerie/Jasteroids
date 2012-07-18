package se.faerie.jasteroids.graphics.update;

import java.util.List;

public interface GameUpdateSource {
	
	public List<GameUpdate> getNewUpdates();
	public void addUpdate(GameUpdate... updates);
}
