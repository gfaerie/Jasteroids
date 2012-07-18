package se.faerie.jasteroids.graphics.model;

import java.util.Collection;

import se.faerie.jasteroids.graphics.render.GameRenderingInfo;
import se.faerie.jasteroids.physics.GamePhysics;

public interface GameData {

	public Collection<GameObject> getGameObjects();

	public void addGameObject(GameObject gameObject);

	public void removeGameObject(int id);

	public GameObject getGameObject(int id);

	public void update(GameRenderingInfo renderingInfo);
	
	public void setGamePhysics(GamePhysics gamePhysics);
	
}
