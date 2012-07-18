package se.faerie.jasteroids.graphics.update;

import se.faerie.jasteroids.graphics.model.GameData;
import se.faerie.jasteroids.graphics.model.GameObject;
import se.faerie.jasteroids.graphics.model.GameObjectPositionData;

public class TurnUpdate extends AbstractGameUpdate {

	private double turn;
	private int objectId;
	
	public TurnUpdate(int objectId, double turn, long executionTime) {
		super(executionTime);
		this.turn = turn;
		this.objectId = objectId;
	}

	@Override
	public void applyUpdate(GameData graphics) {
		GameObject object = graphics.getGameObject(objectId);
		if(object!=null){
			GameObjectPositionData state = object.getPositionData();
			state.setAngleVelocity(turn);
		}
	}

}
