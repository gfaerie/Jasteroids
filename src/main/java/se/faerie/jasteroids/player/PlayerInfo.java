package se.faerie.jasteroids.player;

import se.faerie.jasteroids.equipment.Equipment;
import se.faerie.jasteroids.graphics.model.GameObject;

public interface PlayerInfo {

	public GameObject getPlayerObject();
	public double getAcceleration();
	public double getTurnSpeed();
	public Equipment getPrimaryEquipment();
}
