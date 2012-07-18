package se.faerie.jasteroids.graphics.render;

import java.awt.Graphics2D;

import se.faerie.jasteroids.graphics.model.GameData;

public interface GameObjectRenderer {

	public void renderGraphics(Graphics2D graphics, GameData graphicsData);
	
}
