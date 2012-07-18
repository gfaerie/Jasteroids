package se.faerie.jasteroids.graphics.update;

import se.faerie.jasteroids.graphics.model.GameData;
import se.faerie.jasteroids.graphics.model.GameObject;

public class AddObjectUpdate extends AbstractGameUpdate {

	private GameObject gameObject;
	
	public AddObjectUpdate(GameObject gameObject, long updateTime) {
		super(updateTime);
		this.gameObject = gameObject;
	}

	@Override
	public void applyUpdate(GameData graphics) {
		graphics.addGameObject(gameObject);
		
	}

	
	
}
