package br.com.elo7.test.bean;

import br.com.elo7.test.constants.Constants;

/**
 * 
 * @author Susan Braun Rosa
 *
 */
public class Probe {

	private int x;
	private int y;
	private Direction direction;
	private Planet planet;

	public Probe(int x, int y, Direction direction, Planet planet) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.planet = planet;
	}

	/***
	 * Move a  sonda.
	 * Caso a sonda ultrapasse o limite do mapa a ação não será executada, ou seja,
	 * a sonda permanece parada
	 */
	private void move() {

		int xToVerify = getX();
		int yToVerify = getY();

		switch (direction) {
		case N:
		case S:
			yToVerify += direction.getIncrement();
			break;
		case W:
		case E:
			xToVerify += direction.getIncrement();
			break;
		}

		if (planet.canMove(xToVerify, yToVerify)) {
			setX(xToVerify);
			setY(yToVerify);
		} 

	}

	/**
	 * 
	 * @param action
	 */
	public void doAction(String action) {
		if (Constants.MOVE.equalsIgnoreCase(action)) {
			move();
		} else {
			switchDirection(action);
		}
	}

	/**
	 * Define pra qual direção a sonda deve apontar
	 * @param action
	 */
	private void switchDirection(String action) {
		if (Constants.LEFT.equalsIgnoreCase(action)) {
			direction = direction.previous();
		} else {
			direction = direction.next();
		}
	}

	/**
	 * 
	 * @return
	 */
	public String showPosition() {
		return String.format("%d %d %s", getX(), getY(), getDirection().getValue());
	}

	/**
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/***
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/***
	 * 
	 * @return
	 */
	public Direction getDirection() {
		return direction;
	}

	/***
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/***
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

}
