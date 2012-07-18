package se.faerie.jasteroids.equipment;

import java.awt.Color;

import se.faerie.jasteroids.factory.GameObjectCreationHelper;
import se.faerie.jasteroids.graphics.model.GameObject;
import se.faerie.jasteroids.graphics.model.GameObjectData;
import se.faerie.jasteroids.graphics.model.GameObjectPositionData;
import se.faerie.jasteroids.graphics.model.GameObjectType;
import se.faerie.jasteroids.graphics.model.impl.StaticPolygonObjectDataImpl;

public class BeamProjectileProducer extends BasicProjectileProducer {

	private int mass;
	private Color color;
	private int length;
	private double speed;

	public BeamProjectileProducer(long milliSecondCooldown, long milliSecondLifeTime,
			int mass, Color color, int length, double speed) {
		super(milliSecondCooldown, milliSecondLifeTime);
		this.mass = mass;
		this.color = color;
		this.length = length;
		this.speed = speed;
	}

	@Override
	public GameObject[] getProjectiles(GameObject owner) {
		int[] xCorners = new int[] { 0, length, length, 0 };
		int[] yCorners = new int[] { 0, 0 ,1,1};
		GameObjectData data = new StaticPolygonObjectDataImpl(color, xCorners,
				yCorners, GameObjectType.WEAPON, owner.getData().getAlligiance(),
				mass);
		GameObjectPositionData state = GameObjectCreationHelper
				.calculateProjectilePath(owner.getPositionData(), speed);
		return new GameObject[] { new GameObject(data, state, 1) };
	}

}
