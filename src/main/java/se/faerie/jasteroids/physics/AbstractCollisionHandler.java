package se.faerie.jasteroids.physics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import se.faerie.jasteroids.factory.GameObjectCreationHelper;
import se.faerie.jasteroids.graphics.model.GameData;
import se.faerie.jasteroids.graphics.model.GameObject;
import se.faerie.jasteroids.graphics.model.GameObjectType;
import se.faerie.jasteroids.graphics.render.GameRenderingInfo;
import se.faerie.jasteroids.graphics.update.AddObjectUpdate;
import se.faerie.jasteroids.graphics.update.GameUpdateSource;
import se.faerie.jasteroids.graphics.update.RemoveObjectUpdate;

public abstract class AbstractCollisionHandler implements CollisionHandler {

	private int numberOfXCells;
	private int numberOfYCells;
	private GameUpdateSource gameUpdateSource;
	private double splitSpeedVariance = 35;
	private double massCollisionLimit = 5;
	private double radiusCollisionLimit = 15;
	private int maxNumberOfSplits = 5;
	private double collisionReduction = 0.85;
	private static final Random random = new Random();

	public AbstractCollisionHandler(int numberOfXCells, int numberOfYCells) {
		super();
		this.numberOfXCells = numberOfXCells;
		this.numberOfYCells = numberOfYCells;
	}

	public void setGameUpdateSource(GameUpdateSource gameUpdateSource) {
		this.gameUpdateSource = gameUpdateSource;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handleCollisions(GameData gameData,
			GameRenderingInfo gameRenderingInfo) {

		Collection<GameObject>[][] indexedObjects = new Collection[numberOfXCells][numberOfYCells];

		for (GameObject gameObject : gameData.getGameObjects()) {
			double xCellDouble = numberOfXCells
					* gameObject.getPositionData().getXPosition()
					/ gameRenderingInfo.getXScreenSize();

			double yCellDouble = numberOfYCells
					* gameObject.getPositionData().getYPosition()
					/ gameRenderingInfo.getYScreenSize();

			int xCell = (int) xCellDouble;
			int yCell = (int) yCellDouble;

			if (xCell >= numberOfXCells) {
				xCell = numberOfXCells - 1;
			}
			if (yCell >= numberOfYCells) {
				yCell = numberOfYCells - 1;
			}

			Collection<GameObject> previousObjects = indexedObjects[xCell][yCell];
			if (previousObjects == null) {
				previousObjects = new ArrayList<GameObject>();
				indexedObjects[xCell][yCell] = previousObjects;
			}
			previousObjects.add(gameObject);
		}

		for (int x = 0; x < numberOfXCells; x++) {
			for (int y = 0; y < numberOfYCells; y++) {

				// check to the right
				if (x < numberOfXCells - 1) {
					int yStart = y - 1 < 0 ? 0 : y - 1;
					int yEnd = (y + 1 < numberOfYCells) ? numberOfYCells
							: y + 1;

					for (int yCheck = yStart; yCheck < yEnd; yCheck++) {
						checkCollision(indexedObjects, x + 1, yCheck, x, y);
					}
				}
				// check up
				if (y < numberOfYCells - 1) {
					checkCollision(indexedObjects, x, y + 1, x, y);
				}

			}
		}
	}

	private void checkCollision(Collection<GameObject>[][] indexedObjects,
			int x1, int y1, int x2, int y2) {
		Collection<GameObject> arrayOne = indexedObjects[x1][y1];
		Collection<GameObject> arrayTwo = indexedObjects[x2][y2];
		if (arrayOne == null || arrayOne.isEmpty()) {
			return;
		}
		if (arrayTwo == null || arrayTwo.isEmpty()) {
			return;
		}
		for (GameObject objectOne : arrayOne) {
			for (GameObject objectTwo : arrayTwo) {
				if (isCollision(objectOne, objectTwo)) {
					handleCollision(objectOne, objectTwo);
				}
			}
		}
	}

	public abstract boolean isCollision(GameObject one, GameObject two);
	
	private void handleCollision(GameObject one, GameObject two) {
		one.updateCurrentHitPoints(-two.getData().getMass());
		two.updateCurrentHitPoints(-one.getData().getMass());

		handleObjectDestruction(one);
		handleObjectDestruction(two);
	}

	private void handleObjectDestruction(GameObject gameObject) {
		// debug dont remove player
		if (gameObject.getData().getType() == GameObjectType.SHIP) {
			return;
		}

		// all projectiles are remove in collisions
		if (gameObject.getData().getType() == GameObjectType.WEAPON) {
			this.gameUpdateSource.addUpdate(new RemoveObjectUpdate(gameObject
					.getId(), System.currentTimeMillis()));
		}
		// all objects with less than 0 hp are destroyed
		if (gameObject.getCurrentHitPoints() <= 0) {
			this.gameUpdateSource.addUpdate(new RemoveObjectUpdate(gameObject
					.getId(), System.currentTimeMillis()));

			// if object was is an asteroid it might split
			if (gameObject.getData().getType() == GameObjectType.ASTEROID) {
				handleAsteroidSplit(gameObject);
			}
		}
	}

	private void handleAsteroidSplit(GameObject asteroid) {
		int splits = random.nextInt(maxNumberOfSplits);

		if (splits > 0) {
			double splitMass = asteroid.getData().getMass()
					* collisionReduction / splits;
			double splitRadius = asteroid.getData().getCollisionRadius()
					* collisionReduction / Math.sqrt(splits);
			double splitHitPoints = asteroid.getMaxHitPoints()
					* collisionReduction / splits;
			// asteroid has to have a certain mass and radius to split
			if (splitMass > this.massCollisionLimit
					&& splitRadius > this.radiusCollisionLimit) {

				for (int i = 0; i < splits; i++) {
					GameObject child = GameObjectCreationHelper
							.createAsteroid(splitMass, splitHitPoints,
									splitRadius, new double[] {
											asteroid.getPositionData()
													.getXVelocity()
													+ random.nextGaussian()
													* splitSpeedVariance,
											asteroid.getPositionData()
													.getYVelocity()
													+ random.nextGaussian()
													* splitSpeedVariance },
									new double[] {
											asteroid.getPositionData()
													.getXPosition(),
											asteroid.getPositionData()
													.getYPosition() });
					this.gameUpdateSource.addUpdate(new AddObjectUpdate(child,
							System.currentTimeMillis()));
				}
			}
		}
	}
}
