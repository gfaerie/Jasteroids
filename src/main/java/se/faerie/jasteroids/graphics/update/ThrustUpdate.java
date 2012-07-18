package se.faerie.jasteroids.graphics.update;

import se.faerie.jasteroids.graphics.model.GameData;
import se.faerie.jasteroids.graphics.model.GameObject;
import se.faerie.jasteroids.graphics.model.GameObjectPositionData;

public class ThrustUpdate extends AbstractGameUpdate {

	private int objectId;
	private double thrust;
	
	public ThrustUpdate(int objectId, double thrust, long executionTime) {
		super(executionTime);
		this.objectId = objectId;
		this.thrust=thrust;

	}

	@Override
	public void applyUpdate(GameData data) {
		GameObject object = data.getGameObject(objectId);
		if(object!=null){
			GameObjectPositionData state = object.getPositionData();
			double angle= state.getAngle();
			state.setXAcceleration(thrust*Math.cos(angle));
			state.setYAcceleration(thrust*Math.sin(angle));
		}
		
	}

}
