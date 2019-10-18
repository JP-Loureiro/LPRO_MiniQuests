package dkeep.logic;

public class Map {
	int [] keyPos;
	int [] heroPos;	
	private static char [][] map;
	
	public Map() {
		map = new char[][] {
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'X', 0, 0, 0, 0, 0, 0, 0, 0, 'X'},
			{'X', 0, 'X', 'X', 0, 'X', 0, 'X', 0, 'X'},
			{'X', 0, 'X', 'X', 0, 'X', 0, 'X', 0, 'X'},
			{'X', 0, 'X', 'X', 0, 'X', 0, 'X', 0, 'X'},
			{'X', 0, 0, 0, 0, 0, 0, 'X', 0, 'E'},
			{'X', 0, 'X', 'X', 0, 'X', 0, 'X', 0, 'X'},
			{'X', 0, 'X', 'X', 0, 'X', 0, 'X', 0, 'X'},
			{'X', 0, 'X', 'X', 0, 0, 0, 0, 0, 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
		};
	}

	public int [] getHeroPos() {
		return heroPos;
	}
	
	public int [] getKeyPos() {
		return keyPos;
	}
	
	public char getEntry(int y, int x) {
		return map[y][x]; 
	}
	
	public void setHeroPos(int [] pos){
		heroPos = pos;
	}
	
	public void setKeyPos(int [] pos){
		keyPos = pos;
	}
	
	public void setEntry(int [] pos, char newValue){
		map[pos[0]][pos[1]] = newValue;
	}
	
	public void setEntry(int y, int x, char newValue){
		map[y][x] = newValue;
	}
	
	public void draw(){
		int i = 0;
		int j = 0;
		
		System.out.println();
		
		for(i=0; i < 10; i++) {
			for(j=0; j < 10; j++) {
				System.out.printf("%c ", map[i][j]);
			}
			System.out.println();
		}
	}
	
}
