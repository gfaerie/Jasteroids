package se.faerie.jasteroids.graphics.render;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import se.faerie.jasteroids.graphics.model.GameData;
import se.faerie.jasteroids.graphics.update.GameUpdate;
import se.faerie.jasteroids.graphics.update.GameUpdateSource;
import se.faerie.jasteroids.physics.CollisionHandler;

public class GraphicsEngine implements Runnable {

	private GameObjectRenderer gameRenderer;
	private GameData gameData;
	private GameUpdateSource updateSource;
	private long sleepTime = 10000000; // ns
	private int updatesPerDraw = 10;
	private boolean run = true;
	private Window gameWindow;
	private Color backGround = Color.BLACK;
	private BufferStrategy bufferStrategy;
	private int frameUpdate = (int) (1000 / sleepTime);
	private boolean trackRenderTimes = true;
	private List<Long> renderTimes = new ArrayList<Long>(frameUpdate);
	private double renderTime = 0;
	private GameRenderingInfo gameRenderingInfo = new GameRenderingInfo();
	private CollisionHandler collisionHandler;

	public GraphicsEngine(GameObjectRenderer gameRenderer,
			GameUpdateSource updateSource, Window gameWindow,
			GameData gameData, CollisionHandler collisionHandler) {
		this.collisionHandler = collisionHandler;
		this.gameRenderer = gameRenderer;
		this.gameData = gameData;
		this.updateSource = updateSource;
		gameWindow.createBufferStrategy(2);
		this.gameWindow = gameWindow;
		this.bufferStrategy = gameWindow.getBufferStrategy();
		gameRenderingInfo.setXScreenSize(gameWindow.getWidth());
		gameRenderingInfo.setYScreenSize(gameWindow.getHeight());
		gameRenderingInfo.setStepSize(sleepTime / updatesPerDraw);
	}

	@Override
	public void run() {

		while (run) {
			long startTime = System.nanoTime();

			updateGraphics();
			renderGraphics();
			drawScreen();
			long diff = System.nanoTime() - startTime;
			renderTimes.add(diff);
			if (trackRenderTimes) {
				if (renderTimes.size() >= frameUpdate) {
					renderTime = 0;
					for (long l : renderTimes) {
						renderTime += l;
					}
					renderTime /= renderTimes.size();
					renderTime /= 1000000;
					renderTimes.clear();
				}
			}
			long frameSleepTime = sleepTime - diff;
			if (frameSleepTime > 0) {
				try {
					Thread.sleep(frameSleepTime / 1000000);
				} catch (InterruptedException e) {

				}
			}
		}
	}

	public void stop() {
		run = false;
	}

	private void updateGraphics() {
		for (int i = 0; i < updatesPerDraw; i++) {
			collisionHandler.handleCollisions(gameData, gameRenderingInfo);
			for (GameUpdate update : updateSource.getNewUpdates()) {
				update.applyUpdate(gameData);
			}
			gameData.update(gameRenderingInfo);
		}
	}

	private void drawScreen() {
		bufferStrategy.show();
	}

	private void renderGraphics() {
		Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();

		// draw background
		graphics.setColor(backGround);
		graphics.fillRect(0, 0, gameRenderingInfo.getXScreenSize(),
				gameRenderingInfo.getYScreenSize());

		// render all objects
		this.gameRenderer.renderGraphics(graphics, gameData);

		// render rendertime
		if (renderTime < sleepTime / 2) {
			graphics.setColor(Color.GREEN);
		} else if (renderTime > sleepTime) {
			graphics.setColor(Color.RED);
		} else {
			graphics.setColor(Color.YELLOW);
		}
		graphics.drawString("Rendertime: " + renderTime + " ms", 50, gameWindow
				.getHeight() - 50);
	}
}
