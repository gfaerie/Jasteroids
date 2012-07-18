package se.faerie.jasteroids.graphics.update;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class GameUpdateSourceImpl implements GameUpdateSource {

	private PriorityQueue<GameUpdate> updates = new PriorityQueue<GameUpdate>();

	@Override
	public synchronized List<GameUpdate> getNewUpdates() {
		long time = System.currentTimeMillis();
		List<GameUpdate> returnUpdates = new ArrayList<GameUpdate>();
		while (!updates.isEmpty() && updates.peek().getUpdateTime() <= time) {
			returnUpdates.add(updates.poll());
		}
		return returnUpdates;
	}

	@Override
	public synchronized void addUpdate(GameUpdate... updateArgs) {
		for (GameUpdate update : updateArgs) {
			updates.offer(update);
		}
	}

}
