package se.faerie.jasteroids.graphics.model;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import se.faerie.jasteroids.graphics.render.GameRenderingInfo;
import se.faerie.jasteroids.physics.GamePhysics;

public class GameDataImpl implements GameData {

	private GamePhysics gamePhysics;
	private ConcurrentHashMap<Integer, GameObject> gameObjects = new ConcurrentHashMap<Integer, GameObject>();

	public Collection<GameObject> getGameObjects() {
		return gameObjects.values();
	}

	public void addGameObject(GameObject gameObject) {
		gameObjects.put(gameObject.getId(), gameObject);
	}

	public void removeGameObject(int id) {
		gameObjects.remove(id);
	}

	public GameObject getGameObject(int id) {
		return gameObjects.get(id);
	}

	public void update(GameRenderingInfo renderingInfo) {
		for (GameObject gameObject : gameObjects.values()) {
			gameObject.getPositionData().update(renderingInfo, gamePhysics);
		}
	}

	@Override
	public void setGamePhysics(GamePhysics gamePhysics) {
		this.gamePhysics=gamePhysics;
	}
	

}
