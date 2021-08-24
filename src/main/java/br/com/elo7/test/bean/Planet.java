package br.com.elo7.test.bean;

public abstract class Planet {
	private int width;
	private int height;
	private int startX;
	private int startY;

	public Planet(int startX, int startY, int width, int height) {
		this.startX = startX;
		this.startY = startY;
		this.height = height;
		this.width = width;
	}

	public abstract boolean canMove(int x, int y);

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}
}
