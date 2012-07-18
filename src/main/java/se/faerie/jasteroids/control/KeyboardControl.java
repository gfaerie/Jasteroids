package se.faerie.jasteroids.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardControl implements KeyListener {

	private char accelerateCode;
	private char turnLeftCode;
	private char turnRightCode;
	private char primaryEquipmentCode;
	
	private PlayerControl playerControl;

	public void setPrimaryEquipmentCode(char primaryEquipmentCode) {
		this.primaryEquipmentCode = primaryEquipmentCode;
	}

	public char getAccelerateCode() {
		return accelerateCode;
	}

	public void setAccelerateCode(char accelerateCode) {
		this.accelerateCode = accelerateCode;
	}

	public char getTurnLeftCode() {
		return turnLeftCode;
	}

	public void setTurnLeftCode(char turnLeftCode) {
		this.turnLeftCode = turnLeftCode;
	}

	public char getTurnRightCode() {
		return turnRightCode;
	}

	public void setTurnRightCode(char turnRightCode) {
		this.turnRightCode = turnRightCode;
	}

	public void setPlayerControl(PlayerControl playerControl) {
		this.playerControl = playerControl;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == accelerateCode) {
			playerControl.accelerate();
		} else if (e.getKeyChar() == turnLeftCode) {
			playerControl.turnLeft();
		} else if (e.getKeyChar() == turnRightCode) {
			playerControl.turnRight();
		}
		else if(e.getKeyChar()==primaryEquipmentCode){ 
			playerControl.usePrimary();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == accelerateCode) {
			playerControl.stopAccelerate();
		} else if (e.getKeyChar() == turnLeftCode) {
			playerControl.stopTurn();
		} else if (e.getKeyChar() == turnRightCode) {
			playerControl.stopTurn();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
