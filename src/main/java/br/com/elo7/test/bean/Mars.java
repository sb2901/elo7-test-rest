package br.com.elo7.test.bean;

public class Mars extends Planet {

	public Mars(int width, int height) {
		super(0, 0, width, height);
	}

	@Override
	public boolean canMove(int x, int y) {
		return x <= getWidth() && y <= getHeight() && x >= getStartX() && y >= getStartY();
	}

}
