package se.faerie.jasteroids.graphics.render;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import se.faerie.jasteroids.graphics.model.GameData;
import se.faerie.jasteroids.graphics.model.GameObject;

public class GameObjectRendererImpl implements GameObjectRenderer {

	@Override
	public void renderGraphics(Graphics2D graphics, GameData graphicsData) {

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                   RenderingHints.VALUE_ANTIALIAS_ON);

		// loop over all objects
		for (GameObject gameObject : graphicsData.getGameObjects()) {

			// backup original transform
			AffineTransform orginal= graphics.getTransform();
			
			// set transform
			graphics.translate(gameObject.getPositionData()
					.getXPosition(), gameObject.getPositionData().getYPosition());
			graphics.rotate(gameObject.getPositionData().getAngle());
			graphics.translate(-gameObject.getData().getRealtiveCenterOfMass().getX(),-
					gameObject.getData().getRealtiveCenterOfMass().getY());
			graphics.setColor(gameObject.getData().getColor());
			
			//draw
			graphics.fill(gameObject.getData().getGraphics());
			//graphics.draw(gameObject.getData().getGraphics());
			
			// restore transform for next shape
			graphics.setTransform(orginal);
		}

	}

}
