package dkeep.logic;

public class GameElement {
	private int [] position = new int[2];
	
	public int [] getPosition() {
		return position;
	}
	
	public void setPosition(int y, int x) {
		position[0] = y;
		position[1] = x;
	}
}
