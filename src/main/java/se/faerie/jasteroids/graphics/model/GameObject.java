package se.faerie.jasteroids.graphics.model;

import java.util.concurrent.atomic.AtomicInteger;

public class GameObject {

	private static AtomicInteger IDCOUNTER = new AtomicInteger(0);
	
	private GameObjectData data;
	private GameObjectPositionData positionData;
	private int id;
	private double currentHitPoints;
	private double maxHitPoints;

	public GameObject(GameObjectData data, GameObjectPositionData positionData,
			double hitpoints) {
		this.data = data;
		this.positionData = positionData;
		this.maxHitPoints=hitpoints;
		this.currentHitPoints=hitpoints;
		
		synchronized (GameObject.class) {
			this.id = IDCOUNTER.incrementAndGet();
		}
	}

	public GameObjectData getData() {
		return data;
	}

	public GameObjectPositionData getPositionData() {
		return positionData;
	}

	public int getId() {
		return id;
	}

	public double getCurrentHitPoints() {
		return currentHitPoints;
	}

	public double getMaxHitPoints() {
		return maxHitPoints;
	}

	public void updateCurrentHitPoints(double value) {
		currentHitPoints += value;
		if (currentHitPoints < 0) {
			currentHitPoints = 0;
		} else if (currentHitPoints > maxHitPoints) {
			currentHitPoints = maxHitPoints;
		}
	}

}
