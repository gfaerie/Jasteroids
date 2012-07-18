package se.faerie.jasteroids.player;

import se.faerie.jasteroids.equipment.Equipment;
import se.faerie.jasteroids.graphics.model.GameObject;

public class PlayerInfoImpl implements PlayerInfo {

	private GameObject playerObject;
	private double turnSpeed;
	private double acceleration;
	private Equipment primaryEquipment;
	
	public GameObject getPlayerObject() {
		return playerObject;
	}
	public void setPlayerObject(GameObject playerObject) {
		this.playerObject = playerObject;
	}
	public double getTurnSpeed() {
		return turnSpeed;
	}
	public void setTurnSpeed(double turnSpeed) {
		this.turnSpeed = turnSpeed;
	}
	public double getAcceleration() {
		return acceleration;
	}
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}
	public Equipment getPrimaryEquipment() {
		return primaryEquipment;
	}
	public void setPrimaryEquipment(Equipment primaryEquipment) {
		this.primaryEquipment = primaryEquipment;
	}
	
}
