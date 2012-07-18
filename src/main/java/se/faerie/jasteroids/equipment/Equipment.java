package se.faerie.jasteroids.equipment;

import se.faerie.jasteroids.graphics.model.GameObject;
import se.faerie.jasteroids.graphics.update.GameUpdate;

public interface Equipment {

	public GameUpdate[] getExecutionUpdates(GameObject owner);
	
}
