package se.faerie.jasteroids.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.swing.JFrame;

import se.faerie.jasteroids.control.KeyboardControl;
import se.faerie.jasteroids.control.PlayerControlImpl;
import se.faerie.jasteroids.equipment.BeamProjectileProducer;
import se.faerie.jasteroids.factory.GameObjectCreationHelper;
import se.faerie.jasteroids.graphics.model.GameData;
import se.faerie.jasteroids.graphics.model.GameDataImpl;
import se.faerie.jasteroids.graphics.model.GameObject;
import se.faerie.jasteroids.graphics.model.GameObjectAllegiance;
import se.faerie.jasteroids.graphics.model.GameObjectData;
import se.faerie.jasteroids.graphics.model.GameObjectPositionData;
import se.faerie.jasteroids.graphics.model.GameObjectType;
import se.faerie.jasteroids.graphics.model.impl.StaticPolygonObjectDataImpl;
import se.faerie.jasteroids.graphics.render.GameObjectRendererImpl;
import se.faerie.jasteroids.graphics.render.GraphicsEngine;
import se.faerie.jasteroids.graphics.update.GameUpdateSource;
import se.faerie.jasteroids.graphics.update.GameUpdateSourceImpl;
import se.faerie.jasteroids.physics.JavaStandardApiCollisionHandler;
import se.faerie.jasteroids.physics.RelativisticGamePhysics;
import se.faerie.jasteroids.physics.AbstractCollisionHandler;
import se.faerie.jasteroids.player.PlayerInfoImpl;

public class SimpleGraphicsTest {

	private static int numberOfObjects = 50;
	private static GameObject player;
	private static  GameUpdateSourceImpl graphicsUpdateSourceImpl = new GameUpdateSourceImpl();
	public static void main(String[] args) {
		GraphicsDevice graphicsDevice = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		player = setupPlayer(graphicsDevice);
		JFrame frame = getGamePanel(graphicsDevice);
		GraphicsEngine engine = setupGraphicsEngine(frame, graphicsDevice);
		graphicsDevice.setFullScreenWindow(frame);
		

		frame.pack();
		frame.setVisible(true);
		engine.run();
	}

	private static GameData setupGameData(GraphicsDevice graphicsDevice) {
		GameData gameData = new GameDataImpl();
		gameData.setGamePhysics(new RelativisticGamePhysics(1000));
		for (GameObject gameObject : setupObjects(graphicsDevice)) {
			gameData.addGameObject(gameObject);
		}

		gameData.addGameObject(player);
		return gameData;
	}

	private static GameObject setupPlayer(GraphicsDevice graphicsDevice) {

		int[] xCorners = new int[] { 0, 75, 0 };
		int[] yCorners = new int[] { 20, 0, -20 };
		GameObjectData squareData = new StaticPolygonObjectDataImpl(Color.RED,
				xCorners, yCorners, GameObjectType.SHIP,
				GameObjectAllegiance.PLAYERS, 10);
		GameObjectPositionData squareState = new GameObjectPositionData(0, 0,
				100, 0, 0, 100, 0, 0);

		return new GameObject(squareData, squareState, 10);
	}

	private static Collection<GameObject> setupObjects(
			GraphicsDevice graphicsDevice) {
		Random random = new Random();
		Collection<GameObject> objects = new ArrayList<GameObject>();
		for (int i = 0; i < numberOfObjects; i++) {

			double xSpeed = (random.nextDouble() - 0.5) * random.nextInt(300);
			double ySpeed = (random.nextDouble() - 0.5) * random.nextInt(300);
			int xpos = random.nextInt(graphicsDevice.getDisplayMode()
					.getWidth());
			int ypos = random.nextInt(graphicsDevice.getDisplayMode()
					.getHeight());

			GameObject asteroid = GameObjectCreationHelper.createAsteroid(100, 10, (double) random
					.nextInt(50)+15, new double[] { xSpeed, ySpeed },
					new double[] { xpos, ypos });

			objects.add(asteroid);
		}

		return objects;
	}

	private static JFrame getGamePanel(GraphicsDevice graphicsDevice) {
		JFrame mainGame = new JFrame();
		mainGame.setVisible(true);
		mainGame.setMaximumSize(new Dimension(graphicsDevice.getDisplayMode()
				.getWidth(), graphicsDevice.getDisplayMode().getHeight()));
		mainGame.setPreferredSize(new Dimension(graphicsDevice.getDisplayMode()
				.getWidth(), graphicsDevice.getDisplayMode().getHeight()));
		mainGame.setBackground(Color.BLACK);

		mainGame.pack();
		return mainGame;
	}

	private static GameUpdateSource setupUpdateSource(Window gameWindow) {

		BeamProjectileProducer primaryEquipment = new BeamProjectileProducer(100, 1000, 10, Color.YELLOW, 40, 1000); 
		
		PlayerInfoImpl playerInfoImpl = new PlayerInfoImpl();
		playerInfoImpl.setAcceleration(200);
		playerInfoImpl.setTurnSpeed(10);
		playerInfoImpl.setPlayerObject(player);
		playerInfoImpl.setPrimaryEquipment(primaryEquipment);

		PlayerControlImpl playerControl = new PlayerControlImpl();
		playerControl.setGameUpdateSource(graphicsUpdateSourceImpl);
		playerControl.setPlayerInfo(playerInfoImpl);
		
		KeyboardControl keyboardControl = new KeyboardControl();
		keyboardControl.setAccelerateCode('w');
		keyboardControl.setTurnLeftCode('d');
		keyboardControl.setTurnRightCode('a');
		keyboardControl.setPrimaryEquipmentCode(' ');
		keyboardControl.setPlayerControl(playerControl);

		gameWindow.addKeyListener(keyboardControl);

		return graphicsUpdateSourceImpl;
	}

	private static GraphicsEngine setupGraphicsEngine(Window gameWindow,
			GraphicsDevice graphicsDevice) {
		AbstractCollisionHandler collisionHandler = new JavaStandardApiCollisionHandler(16,
				10);
		collisionHandler.setGameUpdateSource(graphicsUpdateSourceImpl);
		return new GraphicsEngine(new GameObjectRendererImpl(),
				setupUpdateSource(gameWindow), gameWindow,
				setupGameData(graphicsDevice), collisionHandler);
	}

}
