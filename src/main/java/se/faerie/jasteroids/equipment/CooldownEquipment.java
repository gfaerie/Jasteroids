package se.faerie.jasteroids.equipment;

import se.faerie.jasteroids.graphics.model.GameObject;
import se.faerie.jasteroids.graphics.update.GameUpdate;

public abstract class CooldownEquipment implements Equipment {

	private long lastExecutionTime;
	private long milliSecondCooldown;
	private static final GameUpdate[] EMPTYARRAY= new GameUpdate[0];
	
	public CooldownEquipment(long milliSecondCooldown) {
		this.lastExecutionTime = System.nanoTime();
		this.milliSecondCooldown = milliSecondCooldown;
	}

	@Override
	public GameUpdate[] getExecutionUpdates(GameObject owner) {
		long time = System.nanoTime();
		if (time > lastExecutionTime + milliSecondCooldown * 1000000) {
			this.lastExecutionTime = time;
			return internalExecutionUpdates(owner);
		} else {
			return EMPTYARRAY;
		}
	}

	public abstract GameUpdate[] internalExecutionUpdates(GameObject owner);

}
