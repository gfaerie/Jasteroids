package se.faerie.jasteroids.graphics.update;

import se.faerie.jasteroids.graphics.model.GameData;

public interface GameUpdate {

	public long getUpdateTime();
	
	public void applyUpdate(GameData data);

}
