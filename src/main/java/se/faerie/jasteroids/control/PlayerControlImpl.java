package se.faerie.jasteroids.control;

import se.faerie.jasteroids.graphics.update.GameUpdateSource;
import se.faerie.jasteroids.graphics.update.ThrustUpdate;
import se.faerie.jasteroids.graphics.update.TurnUpdate;
import se.faerie.jasteroids.player.PlayerInfo;

public class PlayerControlImpl implements PlayerControl {

	private GameUpdateSource gameUpdateSource;
	private PlayerInfo playerInfo;

	public void setGameUpdateSource(GameUpdateSource gameUpdateSource) {
		this.gameUpdateSource = gameUpdateSource;
	}

	public void setPlayerInfo(PlayerInfo playerInfo) {
		this.playerInfo = playerInfo;
	}

	@Override
	public void accelerate() {
		ThrustUpdate thrustUpdate = new ThrustUpdate(playerInfo
				.getPlayerObject().getId(), playerInfo.getAcceleration(),
				System.currentTimeMillis());
		gameUpdateSource.addUpdate(thrustUpdate);

	}

	@Override
	public void stopAccelerate() {
		ThrustUpdate thrustUpdate = new ThrustUpdate(playerInfo
				.getPlayerObject().getId(), 0, System.currentTimeMillis());
		gameUpdateSource.addUpdate(thrustUpdate);
	}

	@Override
	public void stopTurn() {
		TurnUpdate turnUpdate = new TurnUpdate(playerInfo.getPlayerObject()
				.getId(), 0, System.currentTimeMillis());
		gameUpdateSource.addUpdate(turnUpdate);
	}

	@Override
	public void turnLeft() {
		TurnUpdate turnUpdate = new TurnUpdate(playerInfo.getPlayerObject()
				.getId(), playerInfo.getTurnSpeed(), System.currentTimeMillis());
		gameUpdateSource.addUpdate(turnUpdate);
	}

	@Override
	public void turnRight() {
		TurnUpdate turnUpdate = new TurnUpdate(playerInfo.getPlayerObject()
				.getId(), -playerInfo.getTurnSpeed(), System
				.currentTimeMillis());
		gameUpdateSource.addUpdate(turnUpdate);

	}

	@Override
	public synchronized void usePrimary() {
		gameUpdateSource.addUpdate(playerInfo.getPrimaryEquipment()
				.getExecutionUpdates(playerInfo.getPlayerObject()));
	}

}
