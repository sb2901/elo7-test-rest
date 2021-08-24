package br.com.elo7.test.bean;
/***
 * 
 * @author Susan Braun Rosa
 *
 */
public enum Direction {

	N("N", 1), E("E", 1), S("S", -1), W("W", -1);

	private final String value;
	private final int increment;

	private Direction(String value, int increment) {
		this.value = value;
		this.increment = increment;
	}

	public int getIncrement() {
		return increment;
	}

	public Direction previous() {
		return values()[(ordinal() - 1 + values().length) % values().length];
	}

	public Direction next() {
		return values()[(ordinal() + 1) % values().length];
	}

	public String getValue() {
		return value;
	}

}
