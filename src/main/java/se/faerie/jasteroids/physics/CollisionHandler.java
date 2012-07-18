package se.faerie.jasteroids.physics;

import se.faerie.jasteroids.graphics.model.GameData;
import se.faerie.jasteroids.graphics.render.GameRenderingInfo;

public interface CollisionHandler {

	public void handleCollisions(GameData gameData, GameRenderingInfo gameRenderingInfo);
	
}
