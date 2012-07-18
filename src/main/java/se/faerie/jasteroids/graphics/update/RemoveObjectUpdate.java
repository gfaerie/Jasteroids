package se.faerie.jasteroids.graphics.update;

import se.faerie.jasteroids.graphics.model.GameData;

public class RemoveObjectUpdate extends AbstractGameUpdate {

	private int objectId;
	
	public RemoveObjectUpdate(int objectId, long executionTime) {
		super(executionTime);
		this.objectId = objectId;
	}
	
	@Override
	public void applyUpdate(GameData graphics) {
		graphics.removeGameObject(objectId);
	}
	
	public String toString(){
		return "Removing object "+objectId; 
	}

}
