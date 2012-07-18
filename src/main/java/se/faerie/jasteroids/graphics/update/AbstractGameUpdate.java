package se.faerie.jasteroids.graphics.update;

public abstract class AbstractGameUpdate implements GameUpdate,
		Comparable<GameUpdate> {

	private long updateTime;

	public AbstractGameUpdate(long updateTime) {
		this.updateTime = updateTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	@Override
	public int compareTo(GameUpdate other) {
		if (this.updateTime > other.getUpdateTime()) {
			return 1;
		} else if (this.updateTime < other.getUpdateTime()) {
			return -1;
		} else {
			return 0;
		}
	}


}
