package se.faerie.jasteroids.equipment;

import se.faerie.jasteroids.graphics.model.GameObject;
import se.faerie.jasteroids.graphics.update.AddObjectUpdate;
import se.faerie.jasteroids.graphics.update.GameUpdate;
import se.faerie.jasteroids.graphics.update.RemoveObjectUpdate;

public abstract class BasicProjectileProducer extends CooldownEquipment {

	private long milliSecondLifeTime;
	
	public BasicProjectileProducer(long milliSecondCooldown,
			long milliSecondLifeTime) {
		super(milliSecondCooldown);
		this.milliSecondLifeTime = milliSecondLifeTime;
	}

	@Override
	public GameUpdate[] internalExecutionUpdates(GameObject owner) {
		long time = System.currentTimeMillis();
		GameObject[] projectile = getProjectiles(owner);
		GameUpdate[] updates = new GameUpdate[projectile.length*2];
		for(int i=0; i<projectile.length; i++){
			updates[2*i]=new AddObjectUpdate(projectile[i], time);
			updates[2*i+1]=new RemoveObjectUpdate(projectile[i].getId(), time+milliSecondLifeTime);
		}
		return updates;
	}
	
	public abstract GameObject[] getProjectiles(GameObject owner) ;

}
